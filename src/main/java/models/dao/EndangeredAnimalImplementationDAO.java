package models.dao;

import models.pojos.EndangeredAnimals;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class EndangeredAnimalImplementationDAO implements EndangeredAnimalDAO{
    private final Sql2o sql2o;
    public EndangeredAnimalImplementationDAO(Sql2o sql2o){
        this.sql2o = sql2o;
    }
    @Override
    public void add(EndangeredAnimals endangeredAnimal) {
        String sql = "INSERT INTO endangeredanimals (name,id,health,age) VALUES (:name,:id,:health,:age)";
        try(Connection con = sql2o.open()){
            int id  = (int) con.createQuery(sql,true)
                    .bind(endangeredAnimal)
                    .executeUpdate()
                    .getKey();
            endangeredAnimal.setId(id);
        }catch (Sql2oException sql2oException){
            sql2oException.printStackTrace();
        }
    }

    @Override
    public List<EndangeredAnimals> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM endangeredanimals")
                    .executeAndFetch(EndangeredAnimals.class);
        }
    }

    @Override
    public void update(int id, String name, String health, int age) {

    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from endangeredanimals WHERE id=:id"; //raw sql
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void clearAll() {
        String sql = "DELETE from endangeredanimals"; //raw sql
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

}
