CREATE TABLE dish_statuses (
    id INTEGER,
    id_dish INTEGER,
    status VARCHAR(20)
);

CREATE TABLE cooking_time (
    id INTEGER,
    title VARCHAR(50),
    cooking_time INTEGER
);

CREATE TABLE recipes (
    id INTEGER,
    title VARCHAR(50),
    weight INTEGER
);

CREATE TABLE ingredients (
    title VARCHAR(50),
    weight INTEGER
);