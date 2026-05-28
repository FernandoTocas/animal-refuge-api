CREATE TABLE IF NOT EXISTS shelters (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    capacity INTEGER NOT NULL,
    created_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS animals (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    species VARCHAR(255) NOT NULL,
    breed VARCHAR(255),
    age INTEGER NOT NULL,
    status VARCHAR(50) NOT NULL,
    arrival_date TIMESTAMP,
    registered_by VARCHAR(255),
    shelter_id BIGINT NOT NULL,
    CONSTRAINT fk_animals_shelter
        FOREIGN KEY (shelter_id)
        REFERENCES shelters(id)
);

CREATE TABLE IF NOT EXISTS care_plans (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    frequency VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS animal_care_plans (
    animal_id BIGINT NOT NULL,
    care_plan_id BIGINT NOT NULL,
    PRIMARY KEY (animal_id, care_plan_id),
    CONSTRAINT fk_animal_care_plans_animal
        FOREIGN KEY (animal_id)
        REFERENCES animals(id),
    CONSTRAINT fk_animal_care_plans_care_plan
        FOREIGN KEY (care_plan_id)
        REFERENCES care_plans(id)
);

INSERT INTO shelters (name, city, capacity, created_at) VALUES
('Refugio Huellas Felices', 'Madrid', 2, CURRENT_TIMESTAMP),
('Protectora Nueva Vida', 'Toledo', 5, CURRENT_TIMESTAMP),
('Centro Animal Esperanza', 'Getafe', 3, CURRENT_TIMESTAMP);

INSERT INTO care_plans (name, description, frequency) VALUES
('Vacunación', 'Control y actualización de vacunas.', 'Anual'),
('Desparasitación', 'Tratamiento antiparasitario.', 'Trimestral'),
('Revisión veterinaria', 'Control veterinario general.', 'Mensual'),
('Dieta especial', 'Alimentación adaptada a necesidades concretas.', 'Diaria');