CREATE TABLE Patient (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    age INT NOT NULL
);

CREATE TABLE Medicine (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description TEXT
);

CREATE TABLE Treatment (
    id INT PRIMARY KEY AUTO_INCREMENT,
    advice TEXT,
    medicine_id INT,
    patient_id INT,
    FOREIGN KEY (medicine_id) REFERENCES Medicine(id),
    FOREIGN KEY (patient_id) REFERENCES Patient(id)
);

INSERT INTO Patient (name, age) VALUES ('John Doe', 30);
INSERT INTO Medicine (name, description) VALUES ('Aspirin', 'Pain reliever');