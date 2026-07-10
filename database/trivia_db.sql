-- =========================================================
-- Base de datos: Trivia de Cultura General
-- =========================================================

CREATE DATABASE IF NOT EXISTS trivia_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE trivia_db;

-- Tabla de preguntas
CREATE TABLE IF NOT EXISTS preguntas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    pregunta VARCHAR(500) NOT NULL,
    opcion_a VARCHAR(255) NOT NULL,
    opcion_b VARCHAR(255) NOT NULL,
    opcion_c VARCHAR(255) NOT NULL,
    opcion_d VARCHAR(255) NOT NULL,
    respuesta_correcta CHAR(1) NOT NULL, -- 'A', 'B', 'C' o 'D'
    categoria VARCHAR(100) DEFAULT 'General',
    dificultad VARCHAR(20) DEFAULT 'Media',
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabla de ranking / puntajes
CREATE TABLE IF NOT EXISTS ranking (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre_jugador VARCHAR(100) NOT NULL,
    puntaje INT NOT NULL,
    preguntas_correctas INT NOT NULL,
    preguntas_totales INT NOT NULL,
    fecha_partida TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- =========================================================
-- Set de preguntas iniciales (cultura general)
-- =========================================================
INSERT INTO preguntas (pregunta, opcion_a, opcion_b, opcion_c, opcion_d, respuesta_correcta, categoria, dificultad) VALUES
('¿Cuál es el planeta más grande del sistema solar?', 'Tierra', 'Júpiter', 'Saturno', 'Marte', 'B', 'Ciencia', 'Facil'),
('¿En qué año llegó el ser humano a la Luna por primera vez?', '1965', '1969', '1972', '1959', 'B', 'Historia', 'Media'),
('¿Cuál es la capital de Australia?', 'Sídney', 'Melbourne', 'Canberra', 'Perth', 'C', 'Geografia', 'Media'),
('¿Quién pintó la Mona Lisa?', 'Miguel Ángel', 'Rafael', 'Leonardo da Vinci', 'Donatello', 'C', 'Arte', 'Facil'),
('¿Cuál es el río más largo del mundo?', 'Nilo', 'Amazonas', 'Yangtsé', 'Mississippi', 'B', 'Geografia', 'Media'),
('¿Cuántos huesos tiene el cuerpo humano adulto?', '186', '206', '226', '246', 'B', 'Ciencia', 'Media'),
('¿Cuál es el idioma más hablado del mundo como lengua materna?', 'Inglés', 'Español', 'Mandarín', 'Hindi', 'C', 'General', 'Media'),
('¿En qué país se originaron los Juegos Olímpicos?', 'Italia', 'Grecia', 'Egipto', 'Francia', 'B', 'Historia', 'Facil'),
('¿Cuál es el metal más abundante en la corteza terrestre?', 'Hierro', 'Aluminio', 'Cobre', 'Oro', 'B', 'Ciencia', 'Dificil'),
('¿Qué órgano del cuerpo humano produce la insulina?', 'Hígado', 'Riñón', 'Páncreas', 'Bazo', 'C', 'Ciencia', 'Media'),
('¿Cuál es el océano más grande del planeta?', 'Atlántico', 'Índico', 'Ártico', 'Pacífico', 'D', 'Geografia', 'Facil'),
('¿Quién escribió "Cien años de soledad"?', 'Mario Vargas Llosa', 'Gabriel García Márquez', 'Julio Cortázar', 'Jorge Luis Borges', 'B', 'Literatura', 'Media'),
('¿Cuál es la moneda oficial de Japón?', 'Yuan', 'Won', 'Yen', 'Ringgit', 'C', 'General', 'Facil'),
('¿En qué continente se encuentra Egipto?', 'Asia', 'África', 'Europa', 'Oceanía', 'B', 'Geografia', 'Facil'),
('¿Cuál es el elemento químico con símbolo "O"?', 'Oro', 'Osmio', 'Oxígeno', 'Ozono', 'C', 'Ciencia', 'Facil'),
('¿Qué instrumento mide la temperatura?', 'Barómetro', 'Termómetro', 'Higrómetro', 'Anemómetro', 'B', 'Ciencia', 'Facil'),
('¿Cuál es la montaña más alta del mundo?', 'K2', 'Aconcagua', 'Everest', 'Kilimanjaro', 'C', 'Geografia', 'Facil'),
('¿Quién formuló la teoría de la relatividad?', 'Isaac Newton', 'Albert Einstein', 'Nikola Tesla', 'Galileo Galilei', 'B', 'Ciencia', 'Media'),
('¿Cuál es el país más poblado del mundo (2024)?', 'China', 'Estados Unidos', 'India', 'Indonesia', 'C', 'Geografia', 'Dificil'),
('¿En qué año cayó el Muro de Berlín?', '1985', '1989', '1991', '1993', 'B', 'Historia', 'Media');
