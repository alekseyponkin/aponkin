-- Drop database
--DROP DATABASE company;

-- Create database Company
CREATE DATABASE company;

-- Create table company
CREATE TABLE company(
  id integer NOT NULL,
  name character varying,
  CONSTRAINT company_pkey PRIMARY KEY (id)
);
INSERT INTO company(id, name) VALUES (1, 'Tele2');
INSERT INTO company(id, name) VALUES (5, 'MTS');

-- Create table person
CREATE TABLE person(
  id integer NOT NULL,
  name character varying,
  company_id integer,
  CONSTRAINT person_pkey PRIMARY KEY (id)
);

INSERT INTO person(id, name, company_id ) VALUES (1, 'Kevin', (SELECT id FROM company c WHERE c.name='MTS'));
INSERT INTO person(id, name, company_id ) VALUES (2, 'Mark', (SELECT id FROM company c WHERE c.name='MTS'));
INSERT INTO person(id, name, company_id ) VALUES (3, 'Paul', (SELECT id FROM company c WHERE c.name='Tele2'));
INSERT INTO person(id, name, company_id ) VALUES (4, 'John', (SELECT id FROM company c WHERE c.name='MTS'));
INSERT INTO person(id, name, company_id ) VALUES (5, 'Adam', (SELECT id FROM company c WHERE c.name='Tele2'));
INSERT INTO person(id, name, company_id ) VALUES (6, 'Carl', (SELECT id FROM company c WHERE c.name='MTS'));

-- Select
-- 1
SELECT p.name, c.name  FROM person AS p
  JOIN company AS c ON p.company_id=c.id
  WHERE NOT c.id = 5;

-- 2
SELECT t1.name, count_persone FROM (SELECT c.name, count(p) as count_persone FROM person AS p
  JOIN company AS c On p.company_id=c.id
  GROUP BY c.name) AS t1 WHERE count_persone = (SELECT max(count_persone) FROM (SELECT c.name, count(p) as count_persone FROM person AS p
  JOIN company AS c On p.company_id=c.id
GROUP BY c.name) t2);