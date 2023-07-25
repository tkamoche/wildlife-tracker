import models.dao.AnimalImplementationDAO;
import models.dao.EndangeredAnimalImplementationDAO;
import models.dao.SightingImplementationDAO;
import models.pojos.Animals;
import models.pojos.EndangeredAnimals;
import models.pojos.Sightings;
import org.sql2o.Sql2o;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.gson.Gson;
import spark.template.handlebars.HandlebarsTemplateEngine;
import spark.ModelAndView;
import spark.utils.IOUtils;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {

        String connectionString = "jdbc:postgresql://localhost:5432/wildlife_tracker"; // connect to postgres test database
        Sql2o sql2o = new Sql2o(connectionString, "postgres", "");

        AnimalImplementationDAO animalImplementationDAO = new AnimalImplementationDAO(sql2o);
        EndangeredAnimalImplementationDAO endangeredAnimalImplementationDAO = new EndangeredAnimalImplementationDAO(sql2o);
        SightingImplementationDAO sightingImplementationDAO = new SightingImplementationDAO(sql2o);
         get("/", (request, response) -> {
             Map<String, Object> model = new HashMap<>();
             return new ModelAndView(model, "index.hbs");
         }, new HandlebarsTemplateEngine());
        get("/animals", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("animals",animalImplementationDAO.getAll());
            return new ModelAndView(model, "animals.hbs");
        }, new HandlebarsTemplateEngine());
        get("/endangeredAnimals", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("animals",endangeredAnimalImplementationDAO.getAll());
            return new ModelAndView(model, "endangered-animals.hbs");
        }, new HandlebarsTemplateEngine());
         get("/animal-form", (request, response) -> {
             Map<String, Object> model = new HashMap<>();
             return new ModelAndView(model, "animal-form.hbs");
         }, new HandlebarsTemplateEngine());
         get("/endangered-animal-form", (request, response) -> {
             Map<String, Object> model = new HashMap<>();
             return new ModelAndView(model, "endangered-animal-form.hbs");
         }, new HandlebarsTemplateEngine());
        get("/sighting-form", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "sighting-form.hbs");
        }, new HandlebarsTemplateEngine());
        post ("/animal-form", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int id = Integer.parseInt(request.queryParams("id"));
            String name = request.queryParams("name");
            Animals animal = new Animals(id,name);
            animalImplementationDAO.add(animal);
            return new ModelAndView(model, "animal-form.hbs");
        }, new HandlebarsTemplateEngine());
        post ("/sighting-form", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int id = Integer.parseInt(request.queryParams("id"));
            String location = request.queryParams("location");
            String name = request.queryParams("name");
            Sightings sighting = new Sightings(id,location,name);
            sightingImplementationDAO.add(sighting);
            return new ModelAndView(model, "sighting-form.hbs");
        }, new HandlebarsTemplateEngine());
        post ("/endangered-animal-form", (request, response) -> {
            Map<String, Object> model = new HashMap<>();
            int id = Integer.parseInt(request.queryParams("id"));
            String name = request.queryParams("name");
            String health = request.queryParams("health");
            int age = Integer.parseInt(request.queryParams("age"));
            EndangeredAnimals endangeredAnimal = new EndangeredAnimals(id,name,health,age);
            endangeredAnimalImplementationDAO.add(endangeredAnimal);
            return new ModelAndView(model, "endangered-animal-form.hbs");
        }, new HandlebarsTemplateEngine());
    }
}
