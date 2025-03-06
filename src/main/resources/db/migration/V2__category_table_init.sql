create table if not exists category (
    category_id SERIAL PRIMARY KEY,
    category_name VARCHAR(50) NOT NULL,
    type VARCHAR(50) NOT NULL
);
