DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS product_category;
DROP TABLE IF EXISTS supplier;

CREATE TABLE product_category (
    id serial PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    department VARCHAR(100),
    description VARCHAR(100) NOT NULL
);

CREATE TABLE supplier (
    id serial PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(100) NOT NULL
);

CREATE TABLE product (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    default_price INTEGER,
    currency_string VARCHAR(100) NOT NULL,
    description VARCHAR(200) NOT NULL,
    product_category_id INTEGER REFERENCES product_category(id),
    supplier_id INTEGER REFERENCES supplier(id)
);

INSERT INTO product_category (name,department,description) VALUES
('Protein powder', 'Supplement', 'yes'),
('Creatine', 'Supplement', 'no'),
('Pre-Workout', 'Supplement', 'yeah')
;

INSERT INTO supplier (name,description) VALUES
('Scitec Nutrition', 'Nutritional Supplements'),
('Biotech USA', 'Nutritional Supplements'),
('MHP', 'Nutritional Supplement')
  
;

INSERT INTO product (name, default_price, currency_string,description,product_category_id,supplier_id) VALUES
('Scitec 100% Whey Protein Professional 920g', 6800, 'HUF', 'This product is combination of Whey Concentrate and Whey Isolate provides you with a balanced protein blend, with added digestive enzymes...', 1, 1),
('Biotech USA Iso Whey Zero 2.27Kg', 18000, 'HUF', 'Premium quality sugar-free*, Lactose-free* and Gluten-free whey protein isolate with native whey isolate basic ingredient, added BCAA and Glutamine amino acids.', 1, 2),
('MHP Super Premium Whey Protein 920g', 7300, 'HUF', 'Super Premium Whey Protein + is an ultra premium formula driven by a superior absorbing hydrolyzed whey protein and anti-catabolic bran...', 1, 3),

('Scitec Micronized Mega Creatine 150 capsules', 5500,'HUF', 'By supplementing your diet with Creatine, your muscles will retain more Creatine', 2, 1),
('Biotech 100% Micronized Creatine Monohydrate 300g', 3500,'HUF', 'Creatine increases your performance in a successive burst of short-term, high ...', 2, 2),
('MHP Pure Micronized Creatine Monohydrate 300g', 3700, 'HUF', 'Creatine is a naturally occurring substance in our bodies; it helps volumize', 2, 3),

('Scitec Big Bang Pre-Workout Stimulant', 5800, 'HUF', 'flavor: mango', 3, 1),
('Biotech USA Concentrated Pre-Workout Formula', 5400, 'HUF', 'flavor: droid', 3, 2),
('MHP Max Pre-Workout 300g alazat', 5500, 'HUF', 'flavor: chocolate', 3, 3)
;

