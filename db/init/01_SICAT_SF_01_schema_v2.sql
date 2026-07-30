-- =============================================================================
-- SICAT_SF · Sistema de Inteligencia y Calidad Turística - Santa Fe
-- Script DDL · PostgreSQL 15+
-- Dirección de Turismo · Municipalidad de Santa Fe
-- =============================================================================

-- Crear schema propio para aislar el sistema
CREATE SCHEMA IF NOT EXISTS sicat;
SET search_path TO sicat, public;

-- =============================================================================
-- 1. CATÁLOGOS DE CLASIFICACIÓN
-- =============================================================================

CREATE TABLE categoria_turistica (
    id_cat          SERIAL          PRIMARY KEY,
    prefijo         VARCHAR(5)      NOT NULL UNIQUE,   -- HOT, GAS, MUS, SAL, DEL
    nombre_categoria VARCHAR(100)   NOT NULL UNIQUE
);

COMMENT ON TABLE  categoria_turistica              IS 'Clasificación de primer nivel de la oferta turística.';
COMMENT ON COLUMN categoria_turistica.prefijo       IS 'Prefijo de 3-5 chars usado en la generación de ID_ESTAB (ej: HOT, GAS).';

-- ----------------------------------------------------------------------------

CREATE TABLE secuencia_id (
    prefijo         VARCHAR(5)      PRIMARY KEY
                                    REFERENCES categoria_turistica(prefijo)
                                    ON UPDATE CASCADE,
    ultimo_numero   INTEGER         NOT NULL DEFAULT 0
);

COMMENT ON TABLE secuencia_id IS 'Controla el correlativo por categoría para la generación de ID_ESTAB.';

-- ----------------------------------------------------------------------------

CREATE TABLE subcategoria (
    id_subcat       SERIAL          PRIMARY KEY,
    id_cat          INTEGER         NOT NULL
                                    REFERENCES categoria_turistica(id_cat)
                                    ON DELETE RESTRICT,
    nombre_subcat   VARCHAR(150)    NOT NULL,
    prefijo_id      VARCHAR(5)      REFERENCES categoria_turistica(prefijo)
                                    ON UPDATE CASCADE,
    UNIQUE (id_cat, nombre_subcat)
);

COMMENT ON TABLE subcategoria IS 'Clasificación de segundo nivel (ej: Hotel 3*, Restaurante, Bodegón).';

-- =============================================================================
-- 2. ENTIDAD CENTRAL
-- =============================================================================

CREATE TABLE establecimiento (
    id_estab            VARCHAR(20)     PRIMARY KEY,   -- SICAT-HOT-00001
    id_cat              INTEGER         NOT NULL
                                        REFERENCES categoria_turistica(id_cat)
                                        ON DELETE RESTRICT,
    id_subcat           INTEGER         REFERENCES subcategoria(id_subcat)
                                        ON DELETE SET NULL,
    nombre              VARCHAR(150)    NOT NULL,
    direccion           VARCHAR(250),
    latitud             NUMERIC(10, 6),
    longitud            NUMERIC(10, 6),
    tel_fijo            VARCHAR(30),
    tel_movil           VARCHAR(30),
    correo              VARCHAR(150),
    web                 VARCHAR(250),
    instagram           VARCHAR(100),
    facebook            VARCHAR(250),
    anio_inauguracion   SMALLINT        CHECK (anio_inauguracion BETWEEN 1800 AND 2100),
    estado              VARCHAR(20)     NOT NULL DEFAULT 'Activo'
                                        CHECK (estado IN ('Activo','Inactivo','En reformas','Temporario','Sin datos')),
    -- No se almacena fecha_relevamiento aquí: el historial completo vive en la tabla relevamiento.
    -- Este campo se actualiza automáticamente por trigger cada vez que se inserta un relevamiento.
    fecha_ultimo_relevamiento DATE,
    fecha_carga         DATE            NOT NULL DEFAULT CURRENT_DATE,
    fecha_actualizacion DATE,
    personal_permanente SMALLINT        CHECK (personal_permanente >= 0),
    personal_eventual   SMALLINT        CHECK (personal_eventual >= 0),
    redes_sociales      VARCHAR(200),   -- pipe-separated: Facebook|Instagram|TikTok
    uso_redes           VARCHAR(200),
    sistema_reserva     VARCHAR(200),
    modalidad_pago      VARCHAR(200),
    herramienta_datos   VARCHAR(150),
    automatizacion      BOOLEAN,
    tiene_distincion    BOOLEAN         DEFAULT FALSE,
    observaciones       VARCHAR(500)
);

COMMENT ON TABLE  establecimiento                   IS 'Entidad central. Representa cualquier establecimiento turístico de Santa Fe.';
COMMENT ON COLUMN establecimiento.id_estab           IS 'Identificador único generado automáticamente. Formato: SICAT-XXX-NNNNN.';
COMMENT ON COLUMN establecimiento.redes_sociales     IS 'Valores separados por pipe: Facebook|Instagram|TikTok|LinkedIn|Web';
COMMENT ON COLUMN establecimiento.fecha_actualizacion IS 'Actualizada automáticamente por trigger ante cualquier UPDATE.';

-- =============================================================================
-- 3. ESPECIALIZACIONES POR TIPO
-- =============================================================================

-- 3.1 ALOJAMIENTO (Hoteles y Delegaciones) -----------------------------------

CREATE TABLE alojamiento (
    id_estab                VARCHAR(20)     PRIMARY KEY
                                            REFERENCES establecimiento(id_estab)
                                            ON DELETE CASCADE,
    tipo_alojamiento        VARCHAR(60),
    reformas_ultimos_4_anios BOOLEAN,
    observaciones_reforma   VARCHAR(300),
    cant_habitaciones       INTEGER         CHECK (cant_habitaciones > 0),
    tipos_habitaciones      VARCHAR(300),   -- pipe-separated
    total_plazas            INTEGER         CHECK (total_plazas > 0),
    cant_hab_accesibles     SMALLINT        DEFAULT 0 CHECK (cant_hab_accesibles >= 0),
    cant_plazas_accesibles  SMALLINT        DEFAULT 0 CHECK (cant_plazas_accesibles >= 0),
    servicios_generales     VARCHAR(500),
    sala_eventos_nombre_cap VARCHAR(300),
    equipamiento_habitacion VARCHAR(300),
    sistema_info_calidad    VARCHAR(400)
    -- nombre_distincion y organismos_distincion se gestionan en distincion_calidad (1:N)
);

COMMENT ON TABLE alojamiento IS 'Atributos específicos de establecimientos de alojamiento (hoteles y delegaciones).';

-- 3.2 GASTRONOMÍA ------------------------------------------------------------

CREATE TABLE gastronomia (
    id_estab            VARCHAR(20)     PRIMARY KEY
                                        REFERENCES establecimiento(id_estab)
                                        ON DELETE CASCADE,
    subcategoria_gastro VARCHAR(100),
    tipo_cocina         VARCHAR(150),
    whatsapp            VARCHAR(20),
    link_reserva        VARCHAR(200),
    comentario          VARCHAR(300),
    zona_ciudad         VARCHAR(50),
    dias_apertura       VARCHAR(50),
    horario_apertura    VARCHAR(50)     -- formato: HH:MM – HH:MM / HH:MM – HH:MM
);

COMMENT ON TABLE gastronomia IS 'Atributos específicos de establecimientos gastronómicos.';

-- 3.3 MUSEO / SALA CULTURAL --------------------------------------------------

CREATE TABLE museo_sala_cultural (
    id_estab                VARCHAR(20)     PRIMARY KEY
                                            REFERENCES establecimiento(id_estab)
                                            ON DELETE CASCADE,
    subcategoria_museo      VARCHAR(100),
    dominio                 VARCHAR(20)     CHECK (dominio IN ('Municipal','Provincial','Nacional','Privado','Mixto')),
    funcionamiento          VARCHAR(20)     CHECK (funcionamiento IN ('Abierto','Cerrado','Temporario','En reformas')),
    tipo_entrada            VARCHAR(50)     CHECK (tipo_entrada IN ('Gratuita','Con cargo','Entrada voluntaria','Mixta')),
    visitas_guiadas         VARCHAR(100),
    descripcion             VARCHAR(500),
    observaciones_museo     VARCHAR(300),
    coleccion_principal     VARCHAR(100),
    servicios_adicionales   VARCHAR(300)
);

COMMENT ON TABLE museo_sala_cultural IS 'Atributos específicos de museos y salas culturales.';

-- 3.4 SALA PARA EVENTOS ------------------------------------------------------

CREATE TABLE sala_evento (
    -- PK = FK al establecimiento propio del salón (especialización 1:1)
    id_estab            VARCHAR(20)     PRIMARY KEY
                                        REFERENCES establecimiento(id_estab)
                                        ON DELETE CASCADE,
    -- Autorreferencia opcional: salón hijo de un establecimiento padre (ej: hotel con varios salones)
    id_estab_padre      VARCHAR(20)     REFERENCES establecimiento(id_estab)
                                        ON DELETE SET NULL,
    subcategoria_sala   VARCHAR(100),
    nombre_espacio      VARCHAR(100)    NOT NULL,
    reformas_3_anios    BOOLEAN,
    cap_auditorio       INTEGER         CHECK (cap_auditorio >= 0),
    cap_banquete        INTEGER         CHECK (cap_banquete >= 0),
    cap_coctel          INTEGER         CHECK (cap_coctel >= 0),
    superficie_m2       NUMERIC(8, 2)   CHECK (superficie_m2 > 0),
    mesas               VARCHAR(50),
    sillas_butacas      VARCHAR(50),
    climatizacion       BOOLEAN,
    wifi                BOOLEAN,
    pantalla_canon      BOOLEAN,
    sonido_tecnica      BOOLEAN,
    iluminacion         BOOLEAN,
    guardarropas        BOOLEAN,
    escenario           BOOLEAN,
    tarimas             BOOLEAN,
    atriles             BOOLEAN,
    locales_anexos      VARCHAR(200),
    sanitarios          VARCHAR(60),    -- pipe-separated: Mujeres|Hombres|Accesibles
    servicio_limpieza   BOOLEAN,
    gastronomia_incluida VARCHAR(30)    CHECK (gastronomia_incluida IN ('Sí','No','Externa'))
);

COMMENT ON TABLE sala_evento IS 'Salones para eventos. Autorreferencial: un hotel puede tener N salones hijos.';
COMMENT ON COLUMN sala_evento.id_estab_padre IS 'FK al establecimiento padre. Permite que un hotel tenga varios salones registrados.';

-- =============================================================================
-- 4. ENTIDADES TRANSVERSALES
-- =============================================================================

-- 4.1 CONTACTO REFERENTE (privacidad) ----------------------------------------

CREATE TABLE contacto_referente (
    id_ref              SERIAL          PRIMARY KEY,
    id_estab            VARCHAR(20)     NOT NULL
                                        REFERENCES establecimiento(id_estab)
                                        ON DELETE CASCADE,
    nombre_referente    VARCHAR(150),
    telefono_referente  VARCHAR(20),
    correo_referente    VARCHAR(150),
    cargo               VARCHAR(130)
);

COMMENT ON TABLE contacto_referente IS 'Datos del referente interno. Uso operativo exclusivo. No publicar.';

-- 4.2 ACCESIBILIDAD ----------------------------------------------------------

CREATE TABLE accesibilidad (
    id_accesibilidad        SERIAL          PRIMARY KEY,
    id_estab                VARCHAR(20)     NOT NULL UNIQUE
                                            REFERENCES establecimiento(id_estab)
                                            ON DELETE CASCADE,
    accesib_fisica_ingreso  VARCHAR(500),
    estado_rampas           VARCHAR(20)     CHECK (estado_rampas IN ('Bueno','Regular','Malo','No aplica')),
    transporte_publico_200m BOOLEAN,
    lineas_colectivo        VARCHAR(200),
    ciclovias_200m          BOOLEAN,
    rampas_esquinas         BOOLEAN,
    ascensor                BOOLEAN,
    cant_ascensores         SMALLINT        DEFAULT 0 CHECK (cant_ascensores >= 0),
    accesib_permanencia     VARCHAR(400),
    accesib_banos           VARCHAR(400),
    accesib_comunicacional  VARCHAR(400),
    accesib_seguridad       VARCHAR(400)
);

COMMENT ON TABLE accesibilidad IS 'Indicadores de accesibilidad física, de permanencia, comunicacional y de seguridad.';

-- 4.3 SUSTENTABILIDAD --------------------------------------------------------

CREATE TABLE sustentabilidad (
    id_sustentabilidad          SERIAL      PRIMARY KEY,
    id_estab                    VARCHAR(20) NOT NULL UNIQUE
                                            REFERENCES establecimiento(id_estab)
                                            ON DELETE CASCADE,
    practicas_sustentabilidad   VARCHAR(500)
);

COMMENT ON TABLE sustentabilidad IS 'Prácticas ambientales declaradas por el establecimiento.';

-- 4.4 DISTINCIÓN DE CALIDAD --------------------------------------------------

CREATE TABLE distincion_calidad (
    id_distincion           SERIAL          PRIMARY KEY,
    id_estab                VARCHAR(20)     NOT NULL
                                            REFERENCES establecimiento(id_estab)
                                            ON DELETE CASCADE,
    nombre_distincion       VARCHAR(200),
    organismos_distincion   VARCHAR(150),
    anio_emision            SMALLINT        CHECK (anio_emision BETWEEN 1900 AND 2100)
);

COMMENT ON TABLE distincion_calidad IS 'Historial de certificaciones y distinciones. Relación 1-N para conservar el historial completo.';

-- 4.5 RELEVAMIENTO -----------------------------------------------------------

CREATE TABLE relevamiento (
    id_relev                SERIAL          PRIMARY KEY,
    id_estab                VARCHAR(20)     NOT NULL
                                            REFERENCES establecimiento(id_estab)
                                            ON DELETE CASCADE,
    fecha_relevamiento      DATE            NOT NULL,
    tecnico_responsable     VARCHAR(100),
    observaciones_carga     VARCHAR(500),
    estado_carga            VARCHAR(30)     DEFAULT 'Completo'
                                            CHECK (estado_carga IN ('Completo','Incompleto','En revisión','Con observaciones'))
);

COMMENT ON TABLE relevamiento IS 'Historial de relevamientos de campo. Cada visita genera un nuevo registro.';

-- =============================================================================
-- 5. ÍNDICES
-- =============================================================================

CREATE INDEX idx_estab_categoria    ON establecimiento(id_cat);
CREATE INDEX idx_estab_subcategoria ON establecimiento(id_subcat);
CREATE INDEX idx_estab_estado       ON establecimiento(estado);
CREATE INDEX idx_estab_nombre       ON establecimiento(nombre);
CREATE INDEX idx_relev_estab        ON relevamiento(id_estab);
CREATE INDEX idx_relev_fecha        ON relevamiento(fecha_relevamiento);
CREATE INDEX idx_distincion_estab   ON distincion_calidad(id_estab);
CREATE INDEX idx_contacto_estab     ON contacto_referente(id_estab);
CREATE INDEX idx_sala_padre         ON sala_evento(id_estab_padre);

-- =============================================================================
-- 6. TRIGGER · Generación automática de ID_ESTAB (formato SICAT-XXX-NNNNN)
-- =============================================================================

CREATE OR REPLACE FUNCTION sicat.generar_id_estab()
RETURNS TRIGGER
LANGUAGE plpgsql
AS $$
DECLARE
    v_prefijo   VARCHAR(5);
    v_numero    INTEGER;
BEGIN
    -- Obtener prefijo de la categoría elegida
    SELECT prefijo INTO v_prefijo
    FROM sicat.categoria_turistica
    WHERE id_cat = NEW.id_cat;

    -- Incrementar correlativo con lock para evitar condición de carrera
    UPDATE sicat.secuencia_id
    SET ultimo_numero = ultimo_numero + 1
    WHERE prefijo = v_prefijo
    RETURNING ultimo_numero INTO v_numero;

    -- Si no existía la fila del prefijo, insertarla
    IF NOT FOUND THEN
        INSERT INTO sicat.secuencia_id (prefijo, ultimo_numero)
        VALUES (v_prefijo, 1)
        RETURNING ultimo_numero INTO v_numero;
    END IF;

    -- Asignar ID en formato SICAT-XXX-NNNNN
    NEW.id_estab := 'SICAT-' || v_prefijo || '-' || LPAD(v_numero::TEXT, 5, '0');

    RETURN NEW;
END;
$$;

CREATE TRIGGER trg_generar_id_estab
BEFORE INSERT ON sicat.establecimiento
FOR EACH ROW
WHEN (NEW.id_estab IS NULL OR NEW.id_estab = '')
EXECUTE FUNCTION sicat.generar_id_estab();

-- =============================================================================
-- 7. TRIGGER · Actualización automática de fecha_actualizacion
-- =============================================================================

CREATE OR REPLACE FUNCTION sicat.actualizar_fecha()
RETURNS TRIGGER
LANGUAGE plpgsql
AS $$
BEGIN
    NEW.fecha_actualizacion := CURRENT_DATE;
    RETURN NEW;
END;
$$;

CREATE TRIGGER trg_actualizar_fecha
BEFORE UPDATE ON sicat.establecimiento
FOR EACH ROW
EXECUTE FUNCTION sicat.actualizar_fecha();

-- =============================================================================
-- 8. TRIGGER · Sincronización de fecha_ultimo_relevamiento
-- =============================================================================
-- Cada vez que se inserta un nuevo relevamiento, actualiza el campo
-- fecha_ultimo_relevamiento en establecimiento, evitando duplicar datos
-- pero manteniendo acceso rápido a la fecha del último relevamiento.

CREATE OR REPLACE FUNCTION sicat.sync_fecha_ultimo_relevamiento()
RETURNS TRIGGER
LANGUAGE plpgsql
AS $$
BEGIN
    UPDATE sicat.establecimiento
    SET fecha_ultimo_relevamiento = NEW.fecha_relevamiento
    WHERE id_estab = NEW.id_estab
      AND (fecha_ultimo_relevamiento IS NULL
           OR NEW.fecha_relevamiento > fecha_ultimo_relevamiento);
    RETURN NEW;
END;
$$;

CREATE TRIGGER trg_sync_fecha_relevamiento
AFTER INSERT ON sicat.relevamiento
FOR EACH ROW
EXECUTE FUNCTION sicat.sync_fecha_ultimo_relevamiento();

-- =============================================================================
-- 9. DATOS SEMILLA · Categorías y secuencias
-- =============================================================================

-- Índice adicional para autorreferencia de sala_evento
CREATE INDEX IF NOT EXISTS idx_sala_padre ON sicat.sala_evento(id_estab_padre);

INSERT INTO sicat.categoria_turistica (prefijo, nombre_categoria) VALUES
    ('HOT', 'Alojamiento - Hotel'),
    ('DEL', 'Alojamiento - Delegación'),
    ('GAS', 'Gastronomía'),
    ('MUS', 'Museo / Sala Cultural'),
    ('SAL', 'Sala para Eventos');

INSERT INTO sicat.secuencia_id (prefijo, ultimo_numero)
SELECT prefijo, 0 FROM sicat.categoria_turistica;

-- Subcategorías de Alojamiento - Hotel
INSERT INTO sicat.subcategoria (id_cat, nombre_subcat, prefijo_id)
SELECT id_cat, nombre, 'HOT' FROM sicat.categoria_turistica
CROSS JOIN (VALUES
    ('Hotel 1 estrella'), ('Hotel 2 estrellas'), ('Hotel 3 estrellas'),
    ('Hotel 4 estrellas'), ('Hotel 5 estrellas'), ('Hotel Boutique'),
    ('Apart Hotel'), ('Hostel')
) AS s(nombre)
WHERE prefijo = 'HOT';

-- Subcategorías de Alojamiento - Delegación
INSERT INTO sicat.subcategoria (id_cat, nombre_subcat, prefijo_id)
SELECT id_cat, nombre, 'DEL' FROM sicat.categoria_turistica
CROSS JOIN (VALUES
    ('Delegación Municipal'), ('Complejo Sindical'), ('Colonia de Vacaciones'), ('Camping')
) AS s(nombre)
WHERE prefijo = 'DEL';

-- Subcategorías de Gastronomía
INSERT INTO sicat.subcategoria (id_cat, nombre_subcat, prefijo_id)
SELECT id_cat, nombre, 'GAS' FROM sicat.categoria_turistica
CROSS JOIN (VALUES
    ('Restaurante'), ('Bodegón'), ('Bar'), ('Café / Confitería'),
    ('Rotisería'), ('Heladería'), ('Panadería'), ('Cervecería'), ('Otro')
) AS s(nombre)
WHERE prefijo = 'GAS';

-- Subcategorías de Museos
INSERT INTO sicat.subcategoria (id_cat, nombre_subcat, prefijo_id)
SELECT id_cat, nombre, 'MUS' FROM sicat.categoria_turistica
CROSS JOIN (VALUES
    ('Museo Municipal'), ('Museo Provincial'), ('Museo Nacional'),
    ('Museo Privado'), ('Sala de Arte'), ('Sala Fotográfica'),
    ('Centro Experimental'), ('Casa Museo'), ('Espacio Patrimonial')
) AS s(nombre)
WHERE prefijo = 'MUS';

-- Subcategorías de Salas para Eventos
INSERT INTO sicat.subcategoria (id_cat, nombre_subcat, prefijo_id)
SELECT id_cat, nombre, 'SAL' FROM sicat.categoria_turistica
CROSS JOIN (VALUES
    ('Espacio Privado'), ('Institución Educativa'), ('Club / Delegación'),
    ('Espacio Público'), ('Salón en Hotel'), ('Otro')
) AS s(nombre)
WHERE prefijo = 'SAL';
