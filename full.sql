BEGIN;

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (id bigserial PRIMARY KEY, title VARCHAR(255), cost int);
INSERT INTO products (title, cost) VALUES
('bread', 10),
('milk', 20),
('cheese', 100),
('orange', 50),
('beer', 200),
('potato', 30),
('laptop', 1550),
('phone', 950),
('TV', 550),
('drums', 2550),
('guitar', 1950),
('violin', 3550),
('bass', 2150),
('vine', 650),
('absent', 850),
('porto', 450);

DROP TABLE IF EXISTS buyers CASCADE;
CREATE TABLE buyers (id bigserial PRIMARY KEY, name VARCHAR(255), old int);
INSERT INTO buyers (name, old) VALUES
('Mike', 17),
('Oleg', 25),
('Serg', 99),
('Konstantin', 35),
('Olga', 15),
('Maria', 25),
('Ekaterina', 33);

COMMIT;