CREATE TABLE IF NOT EXISTS company
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(100)
);


CREATE TABLE IF NOT EXISTS employee
(
    id         SERIAL PRIMARY KEY,
    name       VARCHAR(100),
    company_id INT,
    FOREIGN KEY (company_id)
        REFERENCES company (id)
)

