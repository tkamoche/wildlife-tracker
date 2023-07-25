CREATE DATABASE wildlife_tracker;
\c wildlife_tracker;
CREATE TABLE animals (id INT PRIMARY KEY, name VARCHAR);
CREATE TABLE endangeredAnimals (id INT PRIMARY KEY, name VARCHAR, health VARCHAR, age VARCHAR);
CREATE TABLE sightings (id INT PRIMARY KEY, location VARCHAR, rangername VARCHAR, sightingtime VARCHAR );
CREATE DATABASE wildlife_tracker_test WITH TEMPLATE wildlife_tracker;