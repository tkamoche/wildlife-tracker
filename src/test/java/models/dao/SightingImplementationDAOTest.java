package models.dao;

import models.pojos.Sightings;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.jupiter.api.Assertions.*;

class SightingImplementationDAOTest {
    private static SightingImplementationDAO sightingImplementationDAO;
    private static Connection conn;

    @BeforeClass      //changed to @BeforeClass (run once before running any tests in this file)
    public static void setUp() throws Exception {   //changed to static
        String connectionString = "jdbc:postgresql://localhost:5432/wildlife_tracker"; // connect to postgres test database
        Sql2o sql2o = new Sql2o(connectionString, "postgres", "");          // changed user and pass to null
        sightingImplementationDAO = new SightingImplementationDAO(sql2o);
        conn = sql2o.open();                        // open connection once before this test file is run
    }

    @After                                          // run after every test
    public void tearDown() throws Exception {
        System.out.println("clearing database");
        sightingImplementationDAO.clearAll();
    }

    @AfterClass                                     //run once after all tests in this file completed
    public static void shutDown() throws Exception{
        conn.close();                               // close connection once after this entire test file is finished
        System.out.println("connection closed");
    }

    @Test
    public void addingSightingSetsID() throws Exception {
        Sightings sighting = new Sightings(1,  "Amazon","Kolum" );
        int sightingId = sighting.getId();
        assertEquals(1, sightingId);
    }
    @Test
    public void addingSightingSetsLocation() throws Exception{
        Sightings sighting = new Sightings(1, "Amazon","Kolum" );
        String location = sighting.getLocation();
        assertEquals("Amazon", location);
    }
}