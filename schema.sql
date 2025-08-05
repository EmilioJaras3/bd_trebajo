CREATE DATABASE IF NOT EXISTS arbol_db_;
USE arbol_db_;

CREATE TABLE IF NOT EXISTS ambiente (
    id_ambiente INT AUTO_INCREMENT PRIMARY KEY,
    ambiente_name VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS especie (
    id_especie INT AUTO_INCREMENT PRIMARY KEY,
    id_ambiente INT,
    tipo_de_suelo VARCHAR(100),
    nombre VARCHAR(100),
    FOREIGN KEY (id_ambiente) REFERENCES ambiente(id_ambiente)
);

CREATE TABLE IF NOT EXISTS arbol (
    id_arbol INT AUTO_INCREMENT PRIMARY KEY,
    id_especie INT,
    nombre VARCHAR(150),
    id_ambiente INT,
    ubicacion VARCHAR(255),
    FOREIGN KEY (id_especie) REFERENCES especie(id_especie),
    FOREIGN KEY (id_ambiente) REFERENCES ambiente(id_ambiente)
);

CREATE TABLE IF NOT EXISTS registro (
    id_registro INT AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS reporte (
    id_reporte INT AUTO_INCREMENT PRIMARY KEY,
    numero_vistas INT,
    id_registro INT,
    id_arbol INT,
    FOREIGN KEY (id_registro) REFERENCES registro(id_registro),
    FOREIGN KEY (id_arbol) REFERENCES arbol(id_arbol)
);

CREATE TABLE IF NOT EXISTS cuidado (
    id_cuidado INT AUTO_INCREMENT PRIMARY KEY,
    fertilizante VARCHAR(100),
    insecticidas VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS cuidador (
    id_cuidador INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150),
    id_arbol INT,
    id_cuidado INT,
    FOREIGN KEY (id_arbol) REFERENCES arbol(id_arbol),
    FOREIGN KEY (id_cuidado) REFERENCES cuidado(id_cuidado)
);

-- Seleccionar la base de datos
USE arbol_db_;

-- Insertar datos en la tabla 'ambiente'
INSERT INTO ambiente (ambiente_name) VALUES
('Bosque Tropical'),
('Zona Urbana');


INSERT INTO especie (id_ambiente, tipo_de_suelo, nombre) VALUES
(1, 'Suelo arcilloso', 'Caoba'),
(2, 'Suelo urbano compactado', 'Plátano Oriental');


INSERT INTO arbol (id_especie, nombre, id_ambiente, ubicacion) VALUES
(1, 'Árbol de Caoba #1', 1, 'Parque Central, Sección A'),
(2, 'Plátano de la Calle Principal', 2, 'Avenida Siempre Viva, Esquina 123');

INSERT INTO registro (tipo) VALUES
('Inspección Semanal'),
('Riego Programado');


INSERT INTO reporte (numero_vistas, id_registro, id_arbol) VALUES
(150, 1, 1),
(300, 1, 2);

INSERT INTO cuidado (fertilizante, insecticidas) VALUES
('Fertilizante orgánico NPK', 'Neem Oil'),
('Compost natural', 'Piretrinas');


INSERT INTO cuidador (nombre, id_arbol, id_cuidado) VALUES
('Juan Pérez', 1, 1),
('Ana López', 2, 2);
