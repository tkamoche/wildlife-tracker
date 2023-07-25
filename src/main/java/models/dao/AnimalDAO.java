package models.dao;

import models.pojos.Animals;

import java.util.List;

public interface AnimalDAO {
    //create
    void add(Animals animal);
    //read
    List<Animals> getAll();
    //update
    void update(int id, String name);
    //delete
    void deleteById(int id);
    void clearAll();
}
