-- Migration 03: Comprehensive schema corrections and normalization auxiliaries
-- Applies safe, idempotent changes to types, adds auxiliary tables, constraints and indexes

BEGIN;

SET search_path TO sicat, public;

-- ====== 1) Fix numeric-like columns in nautic table ======
-- Some incoming data used text fields for numeric values (e.g. "12 embarcaciones", "~5").
-- Convert them to SMALLINT stripping non-digits when possible; invalid/empty -> NULL.
DO $$
BEGIN
  IF EXISTS (SELECT 1 FROM information_schema.columns
             WHERE table_schema='sicat' AND table_name='turismo_nautico_deportivo' AND column_name='cant_embarcaciones') THEN
    EXECUTE 'ALTER TABLE sicat.turismo_nautico_deportivo
      ALTER COLUMN cant_embarcaciones TYPE SMALLINT
      USING (NULLIF(REGEXP_REPLACE(cant_embarcaciones, ''[^0-9]'', '''', ''g''), '''')::smallint)';
  END IF;
  IF EXISTS (SELECT 1 FROM information_schema.columns
             WHERE table_schema='sicat' AND table_name='turismo_nautico_deportivo' AND column_name='cant_equipo_nautico') THEN
    EXECUTE 'ALTER TABLE sicat.turismo_nautico_deportivo
      ALTER COLUMN cant_equipo_nautico TYPE SMALLINT
      USING (NULLIF(REGEXP_REPLACE(cant_equipo_nautico, ''[^0-9]'', '''', ''g''), '''')::smallint)';
  END IF;
END$$;


-- ====== 2) Create auxiliary tables for normalized multi-value data ======
CREATE TABLE IF NOT EXISTS sicat.establecimiento_red_social (
  id SERIAL PRIMARY KEY,
  id_estab VARCHAR(20) NOT NULL REFERENCES sicat.establecimiento(id_estab) ON DELETE CASCADE,
  tipo VARCHAR(50) NOT NULL,
  cuenta VARCHAR(250) NOT NULL
);

CREATE TABLE IF NOT EXISTS sicat.establecimiento_servicio (
  id SERIAL PRIMARY KEY,
  id_estab VARCHAR(20) NOT NULL REFERENCES sicat.establecimiento(id_estab) ON DELETE CASCADE,
  servicio_nombre VARCHAR(150) NOT NULL
);

-- Distinciones de calidad (1:N)
CREATE TABLE IF NOT EXISTS sicat.distincion_calidad (
  id_distincion SERIAL PRIMARY KEY,
  id_estab VARCHAR(20) NOT NULL REFERENCES sicat.establecimiento(id_estab) ON DELETE CASCADE,
  nombre_distincion VARCHAR(200) NOT NULL,
  organismo_otorgante VARCHAR(200),
  anio SMALLINT,
  observaciones VARCHAR(500)
);

-- Tabla de accesibilidad transversal (si no existe)
CREATE TABLE IF NOT EXISTS sicat.accesibilidad (
  id_accesibilidad SERIAL PRIMARY KEY,
  id_estab VARCHAR(20) NOT NULL UNIQUE REFERENCES sicat.establecimiento(id_estab) ON DELETE CASCADE,
  acceso_fisico BOOLEAN,
  rampas BOOLEAN,
  ascensores SMALLINT,
  banos_accesibles BOOLEAN,
  comunicacion_accesible BOOLEAN,
  observaciones VARCHAR(500)
);

-- Tabla de sustentabilidad/transversal
CREATE TABLE IF NOT EXISTS sicat.sustentabilidad (
  id_sustentabilidad SERIAL PRIMARY KEY,
  id_estab VARCHAR(20) NOT NULL UNIQUE REFERENCES sicat.establecimiento(id_estab) ON DELETE CASCADE,
  programas_ambientales VARCHAR(300),
  certificaciones VARCHAR(200),
  reciclaje BOOLEAN,
  eficiencia_energetica BOOLEAN,
  observaciones VARCHAR(500)
);


-- ====== 3) Add CHECK constraints for common controlled vocabularies (idempotent) ======
DO $$
BEGIN
  IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'chk_nautico_acceso_playa') THEN
    ALTER TABLE sicat.turismo_nautico_deportivo
      ADD CONSTRAINT chk_nautico_acceso_playa CHECK (acceso_playa IS NULL OR acceso_playa IN (
        'Libre y gratuito','Con cargo','Solo vehicular','Solo peatonal','Con turno','Mixto'
      ));
  END IF;
  IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'chk_nautico_funcionamiento') THEN
    ALTER TABLE sicat.turismo_nautico_deportivo
      ADD CONSTRAINT chk_nautico_funcionamiento CHECK (funcionamiento IS NULL OR funcionamiento IN (
        'Abierto','Cerrado','Temporario (alta temporada)','En reformas','Sin datos'
      ));
  END IF;
  IF NOT EXISTS (SELECT 1 FROM pg_constraint WHERE conname = 'chk_servicio_habilitacion') THEN
    ALTER TABLE sicat.servicio_turistico
      ADD CONSTRAINT chk_servicio_habilitacion CHECK (habilitacion IS NULL OR habilitacion IN (
        'Habilitado municipal','Habilitado provincial','Ambos','Sin datos'
      ));
  END IF;
END$$;


-- ====== 4) Helpful indexes for common filters ======
CREATE INDEX IF NOT EXISTS idx_nautico_subcategoria ON sicat.turismo_nautico_deportivo(subcategoria_nau);
CREATE INDEX IF NOT EXISTS idx_nautico_funcionamiento ON sicat.turismo_nautico_deportivo(funcionamiento);
CREATE INDEX IF NOT EXISTS idx_distincion_estab ON sicat.distincion_calidad(id_estab);


-- ====== 5) Housekeeping comments ======
COMMENT ON TABLE sicat.establecimiento_red_social IS 'Redes sociales por establecimiento (normalizado)';
COMMENT ON TABLE sicat.establecimiento_servicio IS 'Servicios listados por establecimiento (normalizado)';

COMMIT;
