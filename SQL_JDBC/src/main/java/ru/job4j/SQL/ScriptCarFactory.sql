-- Drop database
--DROP DATABASE car_factory;

-- Create database CarFactory
CREATE DATABASE car_factory;

-- Create table Engine
CREATE TABLE engine(
  id SERIAL PRIMARY KEY,
  name VARCHAR(200)
);
INSERT INTO engine(name) VALUES ('1.6 MPI');
INSERT INTO engine(name) VALUES ('1.4 TSI');
INSERT INTO engine(name) VALUES ('1.2 TSI');

-- Create table Transmission
CREATE TABLE transmission(
  id SERIAL PRIMARY KEY,
  name VARCHAR(200)
);
INSERT INTO transmission(name) VALUES ('7DSG');
INSERT INTO transmission(name) VALUES ('6AT');
INSERT INTO transmission(name) VALUES ('4st ZF4HP');
INSERT INTO transmission(name) VALUES ('5MT');

-- Create table Car body
CREATE TABLE car_body(
  id SERIAL PRIMARY KEY,
  name VARCHAR(200)
);
INSERT INTO car_body(name) VALUES ('sedan 4dr');
INSERT INTO car_body(name) VALUES ('hatchback 5dr');
INSERT INTO car_body(name) VALUES ('pickup truck');

-- Create table Car
CREATE TABLE car(
  id SERIAL PRIMARY KEY,
  name VARCHAR(200),
  engine_id INTEGER REFERENCES engine(id),
  transmission_id INTEGER REFERENCES transmission(id),
  car_body_id INTEGER REFERENCES car_body(id)
);
INSERT INTO car(name, engine_id, transmission_id, car_body_id)
VALUES ('Skoda Octavia',
        (SELECT id FROM engine WHERE name='1.4 TSI'),
        (SELECT id FROM transmission WHERE name='7DSG'),
        (SELECT id FROM car_body WHERE name='sedan 4dr')
);
INSERT INTO car(name, engine_id, transmission_id, car_body_id)
VALUES ('Kia Ceed',
        (SELECT id FROM engine WHERE name='1.6 MPI'),
        (SELECT id FROM transmission WHERE name='6AT'),
        (SELECT id FROM car_body WHERE name='hatchback 5dr')
);

-- Select
-- 1
SELECT c.name, t.name, e.name, cb.name FROM car AS c
  JOIN car_body AS cb ON c.car_body_id = cb.id
  JOIN transmission AS t ON c.transmission_id = t.id
  JOIN engine AS e ON c.engine_id = e.id;

-- 2
SELECT t.name, 'transmission' AS type FROM car AS c RIGHT JOIN transmission AS t ON c.transmission_id = t.id WHERE c.name IS NULL
UNION
SELECT cb.name, 'car body' AS type FROM car AS c RIGHT JOIN car_body AS cb ON c.car_body_id = cb.id WHERE c.name IS NULL
UNION
SELECT e.name, 'engine' AS type FROM car AS c RIGHT JOIN engine e ON c.engine_id = e.id WHERE c.name IS NULL;