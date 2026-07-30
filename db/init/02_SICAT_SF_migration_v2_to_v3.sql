-- =============================================================================
-- SICAT_SF · Migración v2 → v3
-- Aplica solo el delta: nueva columna, 4 tablas, índices y semilla.
-- NO elimina ni modifica datos existentes.
-- PostgreSQL 15+ · Ejecutar conectado al schema sicat
-- =============================================================================

BEGIN;

SET search_path TO sicat, public;

-- =============================================================================
-- 1. NUEVA COLUMNA EN establecimiento
-- =============================================================================

ALTER TABLE establecimiento
    ADD COLUMN IF NOT EXISTS whatsapp VARCHAR(20);

COMMENT ON COLUMN establecimiento.whatsapp IS 'Número de WhatsApp sin 0 ni +54. Ej: 342XXXXXXXX';

-- =============================================================================
-- 2. NUEVAS TABLAS ESPECIALIZADAS
-- =============================================================================

-- 2.1 RECREACIÓN, DIVERSIÓN Y COMPRA ------------------------------------------

CREATE TABLE IF NOT EXISTS recreacion_diversion (
    id_estab            VARCHAR(20)     PRIMARY KEY
                                        REFERENCES establecimiento(id_estab)
                                        ON DELETE CASCADE,
    subcategoria_rec    VARCHAR(100)    CHECK (subcategoria_rec IN (
                                            'Atractivo turístico destacado',
                                            'Shopping/Paseo comercial',
                                            'Feria',
                                            'Productos regionales y artesanías',
                                            'Casino', 'Cine', 'Bowling',
                                            'Otra actividad')),
    funcionamiento      VARCHAR(20)     CHECK (funcionamiento IN (
                                            'Abierto','Cerrado','Temporario',
                                            'En reformas','Sin datos')),
    visitas_guiadas     VARCHAR(100),
    tipo_entrada        VARCHAR(50)     CHECK (tipo_entrada IN (
                                            'Gratuita','Con cargo',
                                            'Entrada voluntaria','Mixta','No aplica')),
    descripcion         VARCHAR(500),
    observaciones       VARCHAR(300),
    frecuencia_feria    VARCHAR(50)     CHECK (frecuencia_feria IN (
                                            'Semanal','Quincenal','Mensual',
                                            'Bimestral','Estacional','Eventual','Permanente')),
    dia_feria           VARCHAR(100),
    productos           VARCHAR(300),
    cant_locales        SMALLINT        CHECK (cant_locales >= 0),
    rubros_principales  VARCHAR(200)
);

COMMENT ON TABLE recreacion_diversion IS 'Atributos específicos de recreación, diversión y compra (atractivos, ferias, shoppings, cines, etc.).';

-- 2.2 PATRIMONIO --------------------------------------------------------------

CREATE TABLE IF NOT EXISTS patrimonio (
    id_estab                VARCHAR(20)     PRIMARY KEY
                                            REFERENCES establecimiento(id_estab)
                                            ON DELETE CASCADE,
    subcategoria_pat        VARCHAR(100)    CHECK (subcategoria_pat IN (
                                                'Iglesia/Templo','Catedral','Capilla','Oratorio',
                                                'Edificio histórico','Sitio histórico',
                                                'Casa histórica','Monumento',
                                                'Atractivo natural','Reserva natural',
                                                'Parque','Costa/Playa fluvial',
                                                'Espacio cultural','Centro cultural',
                                                'Sala teatral','Sala de exposiciones',
                                                'Biblioteca','Otro')),
    tipo_patrimonio         VARCHAR(200),
    declaratoria            VARCHAR(100),
    funcionamiento          VARCHAR(30)     CHECK (funcionamiento IN (
                                                'Abierto','Cerrado','Temporario',
                                                'En restauración','Sin datos')),
    tipo_entrada            VARCHAR(100)    CHECK (tipo_entrada IN (
                                                'Gratuita','Con cargo','Entrada voluntaria',
                                                'Mixta','Libre acceso exterior','No aplica')),
    visitas_guiadas         VARCHAR(100),
    tipo_acceso_nat         VARCHAR(100),
    descripcion             VARCHAR(500),
    anio_construccion       SMALLINT        CHECK (anio_construccion BETWEEN 1500 AND 2100),
    estilo_arquitectonico   VARCHAR(100),
    tipo_actividad_cult     VARCHAR(200),
    programacion_regular    VARCHAR(20)     CHECK (programacion_regular IN ('Sí','No','Esporádica')),
    aforo                   INTEGER         CHECK (aforo >= 0),
    observaciones           VARCHAR(300)
);

COMMENT ON TABLE patrimonio IS 'Atributos específicos de atractivos patrimoniales: iglesias, edificios históricos, atractivos naturales y espacios culturales.';

-- 2.3 SERVICIOS TURÍSTICOS ----------------------------------------------------

CREATE TABLE IF NOT EXISTS servicio_turistico (
    id_estab                VARCHAR(20)     PRIMARY KEY
                                            REFERENCES establecimiento(id_estab)
                                            ON DELETE CASCADE,
    subcategoria_ser        VARCHAR(100)    CHECK (subcategoria_ser IN (
                                                'Agencia de turismo receptivo',
                                                'Agencia de turismo emisivo',
                                                'Alquiler de autos',
                                                'Transfer/Remis turístico',
                                                'Casa de cambio','Western Union',
                                                'Bus turístico','Ruta temática',
                                                'Catamarán/Embarcación turística',
                                                'Otro servicio turístico')),
    habilitacion            VARCHAR(60)     CHECK (habilitacion IN (
                                                'Habilitado municipal','Habilitado provincial',
                                                'Ambos','Sin datos')),
    tipo_turismo            VARCHAR(200),
    legajo_ministerio       VARCHAR(50),
    idiomas                 VARCHAR(200),
    cant_vehiculos          SMALLINT        CHECK (cant_vehiculos >= 0),
    tipo_vehiculo           VARCHAR(200),
    cobertura_servicio      VARCHAR(100),
    punto_partida           VARCHAR(200),
    recorridos              VARCHAR(400),
    tickets                 VARCHAR(200),
    descripcion_servicio    VARCHAR(500),
    sistema_reserva         VARCHAR(200)
);

COMMENT ON TABLE servicio_turistico IS 'Atributos específicos de prestadores de servicios turísticos.';

-- 2.4 TURISMO NÁUTICO Y DEPORTIVO ---------------------------------------------

CREATE TABLE IF NOT EXISTS turismo_nautico_deportivo (
    id_estab                VARCHAR(20)     PRIMARY KEY
                                            REFERENCES establecimiento(id_estab)
                                            ON DELETE CASCADE,
    subcategoria_nau        VARCHAR(100)    CHECK (subcategoria_nau IN (
                                                'Club deportivo','Club náutico',
                                                'Turismo náutico (prestador)',
                                                'Guardería náutica','Bajada de embarcaciones',
                                                'Parador fluvial','Playa fluvial','Solarium',
                                                'Armería','Casa de pesca','Otro')),
    funcionamiento          VARCHAR(30)     CHECK (funcionamiento IN (
                                                'Abierto','Cerrado',
                                                'Temporario (alta temporada)',
                                                'En reformas','Sin datos')),
    deportes                VARCHAR(300),
    instalaciones           VARCHAR(500),
    servicios_club          VARCHAR(300),
    requisitos_admision     VARCHAR(300),
    deportes_nauticos       VARCHAR(300),
    cant_embarcaciones      VARCHAR(200),
    servicios_nauticos      VARCHAR(300),
    servicios_activ_nau     VARCHAR(300),
    cant_equipo_nautico     VARCHAR(200),
    servicios_guarderia     VARCHAR(300),
    actividades_recreacion  VARCHAR(200),
    cant_amarres            SMALLINT        CHECK (cant_amarres >= 0),
    servicios_parador       VARCHAR(400),
    acceso_playa            VARCHAR(100)    CHECK (acceso_playa IN (
                                                'Libre y gratuito','Con cargo',
                                                'Solo vehicular','Solo peatonal',
                                                'Con turno','Mixto')),
    temporada               VARCHAR(100),
    productos_pesca         VARCHAR(300),
    tipo_pesca              VARCHAR(200)
);

COMMENT ON TABLE turismo_nautico_deportivo IS 'Atributos específicos de turismo náutico y deportivo.';

-- =============================================================================
-- 3. ÍNDICES NUEVOS
-- =============================================================================

CREATE INDEX IF NOT EXISTS idx_recreacion_subcat  ON recreacion_diversion(subcategoria_rec);
CREATE INDEX IF NOT EXISTS idx_recreacion_func    ON recreacion_diversion(funcionamiento);
CREATE INDEX IF NOT EXISTS idx_patrimonio_subcat  ON patrimonio(subcategoria_pat);
CREATE INDEX IF NOT EXISTS idx_patrimonio_func    ON patrimonio(funcionamiento);
CREATE INDEX IF NOT EXISTS idx_servicio_subcat    ON servicio_turistico(subcategoria_ser);
CREATE INDEX IF NOT EXISTS idx_nautico_subcat     ON turismo_nautico_deportivo(subcategoria_nau);
CREATE INDEX IF NOT EXISTS idx_nautico_func       ON turismo_nautico_deportivo(funcionamiento);

-- =============================================================================
-- 4. NUEVAS CATEGORÍAS Y SUBCATEGORÍAS (datos semilla)
-- =============================================================================

INSERT INTO sicat.categoria_turistica (prefijo, nombre_categoria) VALUES
    ('REC', 'Recreación, Diversión y Compra'),
    ('PAT', 'Atractivo Patrimonial'),
    ('SER', 'Servicio Turístico'),
    ('NAU', 'Turismo Náutico y Deportivo')
ON CONFLICT (prefijo) DO NOTHING;

INSERT INTO sicat.secuencia_id (prefijo, ultimo_numero)
SELECT prefijo, 0 FROM sicat.categoria_turistica
WHERE prefijo IN ('REC','PAT','SER','NAU')
ON CONFLICT (prefijo) DO NOTHING;

-- Subcategorías REC
INSERT INTO sicat.subcategoria (id_cat, nombre_subcat, prefijo_id)
SELECT id_cat, nombre, 'REC' FROM sicat.categoria_turistica
CROSS JOIN (VALUES
    ('Atractivo turístico destacado'), ('Shopping/Paseo comercial'), ('Feria'),
    ('Productos regionales y artesanías'), ('Casino'), ('Cine'), ('Bowling'),
    ('Otra actividad')
) AS s(nombre)
WHERE prefijo = 'REC'
ON CONFLICT (id_cat, nombre_subcat) DO NOTHING;

-- Subcategorías PAT
INSERT INTO sicat.subcategoria (id_cat, nombre_subcat, prefijo_id)
SELECT id_cat, nombre, 'PAT' FROM sicat.categoria_turistica
CROSS JOIN (VALUES
    ('Iglesia/Templo'), ('Catedral'), ('Capilla'), ('Oratorio'),
    ('Edificio histórico'), ('Sitio histórico'), ('Casa histórica'), ('Monumento'),
    ('Atractivo natural'), ('Reserva natural'), ('Parque'), ('Costa/Playa fluvial'),
    ('Espacio cultural'), ('Centro cultural'), ('Sala teatral'),
    ('Sala de exposiciones'), ('Biblioteca'), ('Otro')
) AS s(nombre)
WHERE prefijo = 'PAT'
ON CONFLICT (id_cat, nombre_subcat) DO NOTHING;

-- Subcategorías SER
INSERT INTO sicat.subcategoria (id_cat, nombre_subcat, prefijo_id)
SELECT id_cat, nombre, 'SER' FROM sicat.categoria_turistica
CROSS JOIN (VALUES
    ('Agencia de turismo receptivo'), ('Agencia de turismo emisivo'),
    ('Alquiler de autos'), ('Transfer/Remis turístico'),
    ('Casa de cambio'), ('Western Union'),
    ('Bus turístico'), ('Ruta temática'),
    ('Catamarán/Embarcación turística'), ('Otro servicio turístico')
) AS s(nombre)
WHERE prefijo = 'SER'
ON CONFLICT (id_cat, nombre_subcat) DO NOTHING;

-- Subcategorías NAU
INSERT INTO sicat.subcategoria (id_cat, nombre_subcat, prefijo_id)
SELECT id_cat, nombre, 'NAU' FROM sicat.categoria_turistica
CROSS JOIN (VALUES
    ('Club deportivo'), ('Club náutico'), ('Turismo náutico (prestador)'),
    ('Guardería náutica'), ('Bajada de embarcaciones'),
    ('Parador fluvial'), ('Playa fluvial'), ('Solarium'),
    ('Armería'), ('Casa de pesca'), ('Otro')
) AS s(nombre)
WHERE prefijo = 'NAU'
ON CONFLICT (id_cat, nombre_subcat) DO NOTHING;

COMMIT;
