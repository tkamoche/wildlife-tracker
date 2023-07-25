package models.dao;

import models.pojos.Animals;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.jupiter.api.Assertions.*;

class AnimalImplementationDAOTest {
    private static AnimalImplementationDAO animalImplementationDAO;
    private static Connection conn;

    @BeforeClass      //changed to @BeforeClass (run once before running any tests in this file)
    public static void setUp() throws Exception {   //changed to static
        String connectionString = "jdbc:postgresql://localhost:5432/wildlife_tracker"; // connect to postgres test database
        Sql2o sql2o = new Sql2o(connectionString, "postgres", "");          // changed user and pass to null
        animalImplementationDAO = new AnimalImplementationDAO(sql2o);
        conn = sql2o.open();                        // open connection once before this test file is run
    }

    @After                                          // run after every test
    public void tearDown() throws Exception {
        System.out.println("clearing database");
        animalImplementationDAO.clearAll();
    }

    @AfterClass                                     //run once after all tests in this file completed
    public static void shutDown() throws Exception{
        conn.close();                               // close connection once after this entire test file is finished
        System.out.println("connection closed");
    }

    @Test
    public void addingAnimalSetsID() throws Exception {
        Animals animal = new Animals(1, "Tiger");
        int animalId = animal.getId();
        assertEquals(1, animalId);
    }
    @Test
    public void addingAnimalSetsName() throws Exception{
        Animals animal = new Animals(1, "Tiger");
        String animalName = animal.getName();
        assertEquals("Tiger", animalName);
    }
}