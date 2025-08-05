-- =================================================================
-- ESQUEMA DE BASE DE DATOS BASADO EN LA ESTRUCTURA DEL USUARIO
-- =================================================================

CREATE DATABASE IF NOT EXISTS arbol_db_custom;
USE arbol_db_custom;

-- Tabla 'ambiente'
CREATE TABLE IF NOT EXISTS ambiente (
    id_ambiente INT AUTO_INCREMENT PRIMARY KEY,
    ambiente_name VARCHAR(100)
);

-- Tabla 'especie'
CREATE TABLE IF NOT EXISTS especie (
    id_especie INT AUTO_INCREMENT PRIMARY KEY,
    id_ambiente INT,
    tipo_de_suelo VARCHAR(100),
    nombre VARCHAR(100),
    FOREIGN KEY (id_ambiente) REFERENCES ambiente(id_ambiente)
);

-- Tabla 'arbol'
CREATE TABLE IF NOT EXISTS arbol (
    id_arbol INT AUTO_INCREMENT PRIMARY KEY,
    id_especie INT,
    nombre VARCHAR(150),
    id_ambiente INT,
    ubicacion VARCHAR(255),
    FOREIGN KEY (id_especie) REFERENCES especie(id_especie),
    FOREIGN KEY (id_ambiente) REFERENCES ambiente(id_ambiente)
);

-- Tabla 'registro'
CREATE TABLE IF NOT EXISTS registro (
    id_registro INT AUTO_INCREMENT PRIMARY KEY,
    tipo VARCHAR(100)
);

-- Tabla 'reporte'
CREATE TABLE IF NOT EXISTS reporte (
    id_reporte INT AUTO_INCREMENT PRIMARY KEY,
    numero_vistas INT,
    id_registro INT,
    id_arbol INT,
    FOREIGN KEY (id_registro) REFERENCES registro(id_registro),
    FOREIGN KEY (id_arbol) REFERENCES arbol(id_arbol)
);

-- Tabla 'cuidado'
CREATE TABLE IF NOT EXISTS cuidado (
    id_cuidado INT AUTO_INCREMENT PRIMARY KEY,
    fertilizante VARCHAR(100),
    insecticidas VARCHAR(100)
);

-- Tabla 'cuidador'
-- NOTA: La estructura provista era confusa. Se ha creado una versión simplificada y lógica.
CREATE TABLE IF NOT EXISTS cuidador (
    id_cuidador INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(150),
    id_arbol INT,
    id_cuidado INT,
    FOREIGN KEY (id_arbol) REFERENCES arbol(id_arbol),
    FOREIGN KEY (id_cuidado) REFERENCES cuidado(id_cuidado)
);

-- Fin del script.
