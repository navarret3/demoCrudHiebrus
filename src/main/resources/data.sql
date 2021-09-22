DROP TABLE IF EXISTS products;

CREATE TABLE products (
  prod_id INT AUTO_INCREMENT  PRIMARY KEY,
  prod_name VARCHAR(20) NOT NULL,
  prod_description VARCHAR(250)
);

INSERT INTO products (prod_name, prod_description) VALUES
  ('pera', 'Fruta del peral'),
  ('manzana', 'Manzana'),
  ('mandarina', 'Naranja pequeñita'),
  ('piña', 'pincha');