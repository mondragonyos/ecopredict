-- schema.sql
-- Creación de la base de datos para EcoPredict
CREATE DATABASE IF NOT EXISTS ecopredict;
USE ecopredict;

-- Tabla de Usuarios
CREATE TABLE IF NOT EXISTS usuarios (
    id_usuario BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    correo VARCHAR(100) NOT NULL UNIQUE,
    contrasena_hash VARCHAR(255) NOT NULL,
    num_habitantes INT NOT NULL DEFAULT 1
);

-- Tabla de Recibos CFE (Historial y Datos para el Modelo de IA)
CREATE TABLE IF NOT EXISTS recibos_cfe (
    id_recibo BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_usuario BIGINT NOT NULL,
    kwh INT NOT NULL,
    costo DECIMAL(10, 2) NOT NULL,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES usuarios(id_usuario) ON DELETE CASCADE
);