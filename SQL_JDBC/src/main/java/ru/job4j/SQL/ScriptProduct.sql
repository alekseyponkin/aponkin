-- Drop database
--DROP DATABASE product;

-- Create database Products
CREATE DATABASE products;

-- Create table Type
CREATE TABLE type(
  id SERIAL PRIMARY KEY,
  name CHAR(200)
);
INSERT INTO type(name) VALUES ('Молоко');
INSERT INTO type(name) VALUES ('Сыр');
INSERT INTO type(name) VALUES ('Хлеб');
INSERT INTO type(name) VALUES ('Масло');
INSERT INTO type(name) VALUES ('Мороженное');

-- Create table Product
CREATE TABLE products(
  id SERIAL PRIMARY KEY,
  type_id INTEGER REFERENCES type(id),
  name CHAR(200) NOT NULL,
  expired_date TIMESTAMP,
  price INTEGER NOT NULL
);
INSERT INTO products(type_id, name, expired_date, price) VALUES ((SELECT id FROM type WHERE name='Молоко'), 'Простоквашино', '2018-04-01 19:11', 75);
INSERT INTO products(type_id, name, expired_date, price) VALUES ((SELECT id FROM type WHERE name='Молоко'), 'Домик в деревне', '2018-04-15 11:11', 80);
INSERT INTO products(type_id, name, expired_date, price) VALUES ((SELECT id FROM type WHERE name='Сыр'), 'Мааздам', '2018-12-10 20:30', 900);
INSERT INTO products(type_id, name, expired_date, price) VALUES ((SELECT id FROM type WHERE name='Сыр'), 'Дорблю', '2018-09-01 10:00', 1400);
INSERT INTO products(type_id, name, expired_date, price) VALUES ((SELECT id FROM type WHERE name='Хлеб'), 'Батон', '2018-05-01 08:23', 32);
INSERT INTO products(type_id, name, expired_date, price) VALUES ((SELECT id FROM type WHERE name='Хлеб'), 'Бородинский', '2018-04-21 19:11', 50);
INSERT INTO products(type_id, name, expired_date, price) VALUES ((SELECT id FROM type WHERE name='Масло'), 'Сливочное', '2018-06-29 22:22', 150);
INSERT INTO products(type_id, name, expired_date, price) VALUES ((SELECT id FROM type WHERE name='Масло'), 'Viola', '2018-10-01 08:55', 200);
INSERT INTO products(type_id, name, expired_date, price) VALUES ((SELECT id FROM type WHERE name='Мороженное'), 'Сливочное мороженное', '2018-10-01 08:55', 35);
INSERT INTO products(type_id, name, expired_date, price) VALUES ((SELECT id FROM type WHERE name='Мороженное'), 'Мороженное шоколадное', '2018-10-01 08:55', 45);
INSERT INTO products(type_id, name, expired_date, price) VALUES ((SELECT id FROM type WHERE name='Мороженное'), 'Сливочное мороженное фруктовое', '2018-10-01 08:55', 60);


-- Select
-- 1
SELECT t.name, p.name, expired_date, price FROM products AS p JOIN type AS t ON p.type_id = t.id WHERE t.name='Сыр';
-- 2
SELECT t.name, p.name, expired_date, price FROM products AS p JOIN type AS t ON p.type_id = t.id WHERE  p.name LIKE '%мороженное%';
-- 3
SELECT t.name, p.name, expired_date, price FROM products AS p JOIN type AS t ON p.type_id = t.id WHERE p.expired_date BETWEEN '2018-04-01' AND '2018-05-01';
-- 4
SELECT t.name, p.name, expired_date, price FROM products AS p JOIN type AS t ON p.type_id = t.id WHERE  p.price=(SELECT max(price) FROM products);
-- 5
SELECT t.name, count(p.name) FROM products AS p JOIN type AS t ON p.type_id = t.id GROUP BY t.name;
-- 6
SELECT t.name, p.name, expired_date, price FROM products AS p JOIN type AS t ON p.type_id = t.id WHERE t.name IN ('Сыр', 'Молоко');
-- 7
SELECT t.name, count(p.name) FROM products AS p JOIN type AS t ON p.type_id = t.id GROUP BY t.name HAVING count(p.name) < 10;
-- 8
SELECT p.name, t.name FROM products AS p JOIN type AS t ON p.type_id = t.id;