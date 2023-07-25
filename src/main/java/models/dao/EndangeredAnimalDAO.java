package models.dao;

import models.pojos.EndangeredAnimals;

import java.util.List;

public interface EndangeredAnimalDAO {
    //create
    void add(EndangeredAnimals endangeredAnimal);
    //read
    List<EndangeredAnimals> getAll();
    //update
    void update(int id, String name, String health, int age);
    //delete
    void deleteById(int id);
    void clearAll();
}
