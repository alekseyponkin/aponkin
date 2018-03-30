-- Drop database
--DROP DATABASE job4j;

-- Create database
CREATE DATABASE job4j;

-- Roles
CREATE TABLE roles(
  id SERIAL PRIMARY KEY,
  role CHAR(100)
);
INSERT INTO roles(role) VALUES ('Admin');
INSERT INTO roles(role) VALUES ('User');

-- Rules
CREATE TABLE rules(
  id SERIAL PRIMARY KEY,
  rule CHAR(100)
);
INSERT INTO rules(rule) VALUES ('Create');
INSERT INTO rules(rule) VALUES ('Update');
INSERT INTO rules(rule) VALUES ('Remove');
INSERT INTO rules(rule) VALUES ('Read');

-- Link many-to-many table Roles with Rules
CREATE TABLE roles_rules(
  roles_id INT REFERENCES roles(id) NOT NULL ,
  rules_id INT REFERENCES rules(id) NOT NULL
);
INSERT INTO roles_rules(roles_id, rules_id) VALUES ((SELECT id FROM roles WHERE role='Admin'), (SELECT id FROM rules WHERE rule='Create'));
INSERT INTO roles_rules(roles_id, rules_id) VALUES ((SELECT id FROM roles WHERE role='Admin'), (SELECT id FROM rules WHERE rule='Update'));
INSERT INTO roles_rules(roles_id, rules_id) VALUES ((SELECT id FROM roles WHERE role='Admin'), (SELECT id FROM rules WHERE rule='Remove'));
INSERT INTO roles_rules(roles_id, rules_id) VALUES ((SELECT id FROM roles WHERE role='Admin'), (SELECT id FROM rules WHERE rule='Read'));
INSERT INTO roles_rules(roles_id, rules_id) VALUES ((SELECT id FROM roles WHERE role='User'), (SELECT id FROM rules WHERE rule='Read'));
INSERT INTO roles_rules(roles_id, rules_id) VALUES ((SELECT id FROM roles WHERE role='User'), (SELECT id FROM rules WHERE rule='Create'));

-- Users
CREATE TABLE users(
  id SERIAL PRIMARY KEY,
  name TEXT,
  roles_id INT REFERENCES roles(id)
);
INSERT INTO users(name, roles_id) VALUES ('Ivan', (SELECT id FROM roles WHERE role='Admin'));
INSERT INTO users(name, roles_id) VALUES ('Alex', (SELECT id FROM roles WHERE role='User'));
INSERT INTO users(name, roles_id) VALUES ('Felix', (SELECT id FROM roles WHERE role='User'));

-- Category
CREATE TABLE category(
  id SERIAL PRIMARY KEY,
  category CHAR(100)
);
INSERT INTO category(category) VALUES ('Critical');
INSERT INTO category(category) VALUES ('High');
INSERT INTO category(category) VALUES ('Low');

-- States
CREATE TABLE states(
  id SERIAL PRIMARY KEY,
  state CHAR(100)
);
INSERT INTO states(state) VALUES ('Open');
INSERT INTO states(state) VALUES ('Closed');

-- Items
CREATE TABLE items(
  id SERIAL PRIMARY KEY,
  users_id INT REFERENCES users(id) UNIQUE,
  name CHAR(100),
  text TEXT,
  date TIMESTAMP NOT NULL DEFAULT now(),
  category_id INT REFERENCES category(id),
  states_id INT REFERENCES states(id)
);
INSERT INTO items(users_id, name, text, category_id, states_id)
VALUES (
  (SELECT id FROM users WHERE name='Ivan'),
  'First item',
  'Test',
  (SELECT id FROM category WHERE category.category='Critical'),
  (SELECT id FROM states WHERE state='Closed')
);
INSERT INTO items(users_id, name, text, category_id, states_id)
VALUES (
  (SELECT id FROM users WHERE name='Alex'),
  'Second item',
  'Test',
  (SELECT id FROM category WHERE category.category='High'),
  (SELECT id FROM states WHERE state='Open')
);
INSERT INTO items(users_id, name, text, category_id, states_id)
VALUES (
  (SELECT id FROM users WHERE name='Felix'),
  'Third item',
  'Test',
  (SELECT id FROM category WHERE category.category='Low'),
  (SELECT id FROM states WHERE state='Open')
);

-- Comments
CREATE TABLE comments(
  id SERIAL PRIMARY KEY,
  items_id INTEGER REFERENCES comments(id) NOT NULL,
  date TIMESTAMP NOT NULL DEFAULT now(),
  text TEXT
);
INSERT INTO comments(items_id, text) VALUES ((SELECT id FROM items WHERE name='First item'), 'First comment');
INSERT INTO comments(items_id, text) VALUES ((SELECT id FROM items WHERE name='Second item'), 'Second comment');
INSERT INTO comments(items_id, text) VALUES ((SELECT id FROM items WHERE name='Third item'), 'Third comment');

-- Attachs
CREATE TABLE attachs(
  id SERIAL PRIMARY KEY,
  items_id INTEGER REFERENCES comments(id) NOT NULL,
  attach CHAR(100)
);
INSERT INTO attachs(items_id, attach) VALUES ((SELECT id FROM items WHERE name='First item'), 'photo.jpg');
INSERT INTO attachs(items_id, attach) VALUES ((SELECT id FROM items WHERE name='Second item'), 'photo2.jpg');