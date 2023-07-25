package models.dao;

import models.pojos.Animals;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class AnimalImplementationDAO implements AnimalDAO{
    private final Sql2o sql2o;
    public AnimalImplementationDAO(Sql2o sql2o){
        this.sql2o = sql2o;
    }
    @Override
    public void add(Animals animal) {
        String sql = "INSERT INTO animals (name,id) VALUES (:name,:id)";
        try(Connection con = sql2o.open()){
            int id  = (int) con.createQuery(sql,true)
                    .bind(animal)
                    .executeUpdate()
                    .getKey();
            animal.setId(id);
        }catch (Sql2oException sql2oException){
            sql2oException.printStackTrace();
        }
    }

    @Override
    public List<Animals> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM animals")
                    .executeAndFetch(Animals.class);
        }

    }

    @Override
    public void update(int id, String name) {
        String sql = "UPDATE animals SET (name,id) = (:name,:id)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("id", id)
                    .executeUpdate();
        }catch (Sql2oException sql2oException){
            sql2oException.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from animals WHERE id=:id"; //raw sql
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
        String sql = "DELETE from animals"; //raw sql
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
}
