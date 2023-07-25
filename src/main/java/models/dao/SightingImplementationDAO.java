package models.dao;

import models.pojos.EndangeredAnimals;
import models.pojos.Sightings;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class SightingImplementationDAO implements SightingDAO{
    private final Sql2o sql2o;

    public SightingImplementationDAO(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Sightings sighting) {
        String sql = "INSERT INTO sightings (id,location,rangername,sightingtime) VALUES (:id,:location,:rangername,:sightingname)";
        try(Connection con = sql2o.open()){
            int id  = (int) con.createQuery(sql,true)
                    .bind(sighting)
                    .executeUpdate()
                    .getKey();
            sighting.setId(id);
        }catch (Sql2oException sql2oException){
            sql2oException.printStackTrace();
        }
    }

    @Override
    public List<Sightings> getAll() {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM sightings")
                    .executeAndFetch(Sightings.class);
        }
    }

    @Override
    public void update(int id, String name, String location, String rangerName) {
        String sql = "UPDATE animals SET (id,location,rangername) = (:id,:location,:rangername)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("location", location)
                    .addParameter("rangername", rangerName)
                    .executeUpdate();
        }catch (Sql2oException sql2oException){
            sql2oException.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from sightings WHERE id=:id"; //raw sql
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
        String sql = "DELETE from sightings"; //raw sql
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }
}
