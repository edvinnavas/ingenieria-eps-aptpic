-- TABLA: REST_CREDENTIAL.
DROP TABLE IF EXISTS rest_credential;
CREATE TABLE rest_credential (
    id_rest_credential BIGINT NOT NULL,
    usuario VARCHAR(100) NOT NULL DEFAULT '',
    contrasena VARCHAR(50) NOT NULL DEFAULT '',
    CONSTRAINT pk_rest_credential PRIMARY KEY (id_rest_credential)
);
INSERT INTO rest_credential (id_rest_credential, usuario, contrasena) VALUES (1, 'admin', MD5('@dm1n'));

-- TABLA: TIPO_SOLICITUD.
DROP TABLE IF EXISTS tipo_solicitud_comeval;
CREATE TABLE tipo_solicitud_comeval (
    id_tipo_solicitud BIGINT NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    CONSTRAINT pk_tipo_solicitud_comeval PRIMARY KEY (id_tipo_solicitud)
); 
INSERT INTO tipo_solicitud_comeval (id_tipo_solicitud, nombre) VALUES (1, 'PROMOCIÓN-DOCENTE');
INSERT INTO tipo_solicitud_comeval (id_tipo_solicitud, nombre) VALUES (2, 'LICENCIA-DOCENTE');
INSERT INTO tipo_solicitud_comeval (id_tipo_solicitud, nombre) VALUES (3, 'AMONESTACIÓN-DOCENTE');
INSERT INTO tipo_solicitud_comeval (id_tipo_solicitud, nombre) VALUES (4, 'CAMBIO-HORARIO');
INSERT INTO tipo_solicitud_comeval (id_tipo_solicitud, nombre) VALUES (5, 'AMPLIACIÓN-HORARIO');
INSERT INTO tipo_solicitud_comeval (id_tipo_solicitud, nombre) VALUES (6, 'CARGA-EVALUACIÓN-DOCENTE');
INSERT INTO tipo_solicitud_comeval (id_tipo_solicitud, nombre) VALUES (7, 'GENERAR-HOJA-RELACIÓN-LABORAL');

-- TABLA: ESTADO_SOLICITUD.
DROP TABLE IF EXISTS estado_solicitud_comeval;
CREATE TABLE estado_solicitud_comeval (
    id_estado_solicitud BIGINT NOT NULL,
    nombre VARCHAR(100) NOT NULL DEFAULT '',
    id_tipo_solicitud BIGINT NOT NULL,
    estado_inicial BIGINT NOT NULL,
    CONSTRAINT pk_estado_solicitud PRIMARY KEY (id_estado_solicitud, id_tipo_solicitud),
    CONSTRAINT fk_estado_solicitud_1 FOREIGN KEY (id_tipo_solicitud) REFERENCES tipo_solicitud_comeval (id_tipo_solicitud)
);
INSERT INTO estado_solicitud_comeval (id_estado_solicitud, nombre, id_tipo_solicitud, estado_inicial) VALUES (1, 'Ingreso solicitud (Docente)', 1, 1);
INSERT INTO estado_solicitud_comeval (id_estado_solicitud, nombre, id_tipo_solicitud, estado_inicial) VALUES (2, 'Ingreso solicitud (COMEVAL)', 1, 1);
INSERT INTO estado_solicitud_comeval (id_estado_solicitud, nombre, id_tipo_solicitud, estado_inicial) VALUES (3, 'Ingreso solicitud (Secretario académico)', 1, 1);
INSERT INTO estado_solicitud_comeval (id_estado_solicitud, nombre, id_tipo_solicitud, estado_inicial) VALUES (4, 'Revisión (COMEVAL)', 1, 0);
INSERT INTO estado_solicitud_comeval (id_estado_solicitud, nombre, id_tipo_solicitud, estado_inicial) VALUES (5, 'Revisión (Secretario académico)', 1, 0);
INSERT INTO estado_solicitud_comeval (id_estado_solicitud, nombre, id_tipo_solicitud, estado_inicial) VALUES (6, 'Acta (Junta directiva)', 1, 0);
INSERT INTO estado_solicitud_comeval (id_estado_solicitud, nombre, id_tipo_solicitud, estado_inicial) VALUES (7, 'DB Centro de Cálculo', 1, 0);

INSERT INTO estado_solicitud_comeval (id_estado_solicitud, nombre, id_tipo_solicitud, estado_inicial) VALUES (1, 'Ingreso solicitud (Docente)', 2, 1);
INSERT INTO estado_solicitud_comeval (id_estado_solicitud, nombre, id_tipo_solicitud, estado_inicial) VALUES (2, 'Ingreso SIIF/Visto bueno (Escuela)', 2, 0);
INSERT INTO estado_solicitud_comeval (id_estado_solicitud, nombre, id_tipo_solicitud, estado_inicial) VALUES (3, 'Tipo licencia (Secretario académico)', 2, 0);
INSERT INTO estado_solicitud_comeval (id_estado_solicitud, nombre, id_tipo_solicitud, estado_inicial) VALUES (4, 'Acuerdo (Decanatura)', 2, 0);
INSERT INTO estado_solicitud_comeval (id_estado_solicitud, nombre, id_tipo_solicitud, estado_inicial) VALUES (5, 'Acta (Junta directiva)', 2, 0);
INSERT INTO estado_solicitud_comeval (id_estado_solicitud, nombre, id_tipo_solicitud, estado_inicial) VALUES (6, 'Notificación (Tesorería)', 2, 0);
INSERT INTO estado_solicitud_comeval (id_estado_solicitud, nombre, id_tipo_solicitud, estado_inicial) VALUES (7, 'DB Centro de Cálculo', 2, 0);

INSERT INTO estado_solicitud_comeval (id_estado_solicitud, nombre, id_tipo_solicitud, estado_inicial) VALUES (1, 'Ingreso solicitud (Secretario académico)', 3, 1);
INSERT INTO estado_solicitud_comeval (id_estado_solicitud, nombre, id_tipo_solicitud, estado_inicial) VALUES (2, 'Acta (Junta directiva)', 3, 0);
INSERT INTO estado_solicitud_comeval (id_estado_solicitud, nombre, id_tipo_solicitud, estado_inicial) VALUES (3, 'DB Centro de Cálculo', 3, 0);

INSERT INTO estado_solicitud_comeval (id_estado_solicitud, nombre, id_tipo_solicitud, estado_inicial) VALUES (1, 'Ingreso solicitud (Docente)', 4, 1);
INSERT INTO estado_solicitud_comeval (id_estado_solicitud, nombre, id_tipo_solicitud, estado_inicial) VALUES (2, 'Visto bueno (Director escuela)', 4, 0);
INSERT INTO estado_solicitud_comeval (id_estado_solicitud, nombre, id_tipo_solicitud, estado_inicial) VALUES (3, 'Visto bueno (Secretario académico)', 4, 0);
INSERT INTO estado_solicitud_comeval (id_estado_solicitud, nombre, id_tipo_solicitud, estado_inicial) VALUES (4, 'Acta (Junta directiva)', 4, 0);
INSERT INTO estado_solicitud_comeval (id_estado_solicitud, nombre, id_tipo_solicitud, estado_inicial) VALUES (5, 'DB Centro de Cálculo', 4, 0);

INSERT INTO estado_solicitud_comeval (id_estado_solicitud, nombre, id_tipo_solicitud, estado_inicial) VALUES (1, 'Ingreso solicitud (Docente)', 5, 1);
INSERT INTO estado_solicitud_comeval (id_estado_solicitud, nombre, id_tipo_solicitud, estado_inicial) VALUES (2, 'Visto bueno (Director escuela)', 5, 0);
INSERT INTO estado_solicitud_comeval (id_estado_solicitud, nombre, id_tipo_solicitud, estado_inicial) VALUES (3, 'Visto bueno (Secretario académico)', 5, 0);
INSERT INTO estado_solicitud_comeval (id_estado_solicitud, nombre, id_tipo_solicitud, estado_inicial) VALUES (4, 'Acta (Junta directiva)', 5, 0);
INSERT INTO estado_solicitud_comeval (id_estado_solicitud, nombre, id_tipo_solicitud, estado_inicial) VALUES (5, 'Notificación (Tesorería)', 5, 0);
INSERT INTO estado_solicitud_comeval (id_estado_solicitud, nombre, id_tipo_solicitud, estado_inicial) VALUES (6, 'DB Centro de Cálculo', 5, 0);

INSERT INTO estado_solicitud_comeval (id_estado_solicitud, nombre, id_tipo_solicitud, estado_inicial) VALUES (1, 'Carga eval. docente (Secretario académico)', 6, 1);
INSERT INTO estado_solicitud_comeval (id_estado_solicitud, nombre, id_tipo_solicitud, estado_inicial) VALUES (2, 'Acta (Junta directiva)', 6, 0);
INSERT INTO estado_solicitud_comeval (id_estado_solicitud, nombre, id_tipo_solicitud, estado_inicial) VALUES (3, 'DB Centro de Cálculo', 6, 0);

-- TABLA: WORKFLOW_SOLICITUD.
DROP TABLE IF EXISTS workflow_solicitud;
CREATE TABLE workflow_solicitud (
    id_estado_solicitud_actual BIGINT NOT NULL,
    id_tipo_solicitud_actual BIGINT NOT NULL,
    id_estado_solicitud_siguiente BIGINT NOT NULL,
    id_tipo_solicitud_siguiente BIGINT NOT NULL,
    CONSTRAINT pk_workflow_solicitud PRIMARY KEY (id_estado_solicitud_actual, id_tipo_solicitud_actual, id_estado_solicitud_siguiente, id_tipo_solicitud_siguiente),
    CONSTRAINT fk_workflow_solicitud_1 FOREIGN KEY (id_estado_solicitud_actual, id_tipo_solicitud_actual) REFERENCES estado_solicitud_comeval (id_estado_solicitud, id_tipo_solicitud),
    CONSTRAINT fk_workflow_solicitud_2 FOREIGN KEY (id_estado_solicitud_siguiente, id_tipo_solicitud_siguiente) REFERENCES estado_solicitud_comeval (id_estado_solicitud, id_tipo_solicitud)
);
INSERT INTO workflow_solicitud (id_estado_solicitud_actual, id_tipo_solicitud_actual, id_estado_solicitud_siguiente, id_tipo_solicitud_siguiente) VALUES (1, 1, 4, 1);
INSERT INTO workflow_solicitud (id_estado_solicitud_actual, id_tipo_solicitud_actual, id_estado_solicitud_siguiente, id_tipo_solicitud_siguiente) VALUES (2, 1, 5, 1);
INSERT INTO workflow_solicitud (id_estado_solicitud_actual, id_tipo_solicitud_actual, id_estado_solicitud_siguiente, id_tipo_solicitud_siguiente) VALUES (3, 1, 6, 1);
INSERT INTO workflow_solicitud (id_estado_solicitud_actual, id_tipo_solicitud_actual, id_estado_solicitud_siguiente, id_tipo_solicitud_siguiente) VALUES (4, 1, 5, 1);
INSERT INTO workflow_solicitud (id_estado_solicitud_actual, id_tipo_solicitud_actual, id_estado_solicitud_siguiente, id_tipo_solicitud_siguiente) VALUES (5, 1, 6, 1);
INSERT INTO workflow_solicitud (id_estado_solicitud_actual, id_tipo_solicitud_actual, id_estado_solicitud_siguiente, id_tipo_solicitud_siguiente) VALUES (6, 1, 7, 1);

INSERT INTO workflow_solicitud (id_estado_solicitud_actual, id_tipo_solicitud_actual, id_estado_solicitud_siguiente, id_tipo_solicitud_siguiente) VALUES (1, 2, 2, 2);
INSERT INTO workflow_solicitud (id_estado_solicitud_actual, id_tipo_solicitud_actual, id_estado_solicitud_siguiente, id_tipo_solicitud_siguiente) VALUES (2, 2, 3, 2);
INSERT INTO workflow_solicitud (id_estado_solicitud_actual, id_tipo_solicitud_actual, id_estado_solicitud_siguiente, id_tipo_solicitud_siguiente) VALUES (3, 2, 4, 2);
INSERT INTO workflow_solicitud (id_estado_solicitud_actual, id_tipo_solicitud_actual, id_estado_solicitud_siguiente, id_tipo_solicitud_siguiente) VALUES (3, 2, 5, 2);
INSERT INTO workflow_solicitud (id_estado_solicitud_actual, id_tipo_solicitud_actual, id_estado_solicitud_siguiente, id_tipo_solicitud_siguiente) VALUES (4, 2, 6, 2);
INSERT INTO workflow_solicitud (id_estado_solicitud_actual, id_tipo_solicitud_actual, id_estado_solicitud_siguiente, id_tipo_solicitud_siguiente) VALUES (5, 2, 6, 2);
INSERT INTO workflow_solicitud (id_estado_solicitud_actual, id_tipo_solicitud_actual, id_estado_solicitud_siguiente, id_tipo_solicitud_siguiente) VALUES (6, 2, 7, 2);

INSERT INTO workflow_solicitud (id_estado_solicitud_actual, id_tipo_solicitud_actual, id_estado_solicitud_siguiente, id_tipo_solicitud_siguiente) VALUES (7, 2, 8, 2);
INSERT INTO workflow_solicitud (id_estado_solicitud_actual, id_tipo_solicitud_actual, id_estado_solicitud_siguiente, id_tipo_solicitud_siguiente) VALUES (8, 2, 9, 2);
INSERT INTO workflow_solicitud (id_estado_solicitud_actual, id_tipo_solicitud_actual, id_estado_solicitud_siguiente, id_tipo_solicitud_siguiente) VALUES (1, 3, 2, 3);
INSERT INTO workflow_solicitud (id_estado_solicitud_actual, id_tipo_solicitud_actual, id_estado_solicitud_siguiente, id_tipo_solicitud_siguiente) VALUES (2, 3, 3, 3);

INSERT INTO workflow_solicitud (id_estado_solicitud_actual, id_tipo_solicitud_actual, id_estado_solicitud_siguiente, id_tipo_solicitud_siguiente) VALUES (1, 4, 2, 4);
INSERT INTO workflow_solicitud (id_estado_solicitud_actual, id_tipo_solicitud_actual, id_estado_solicitud_siguiente, id_tipo_solicitud_siguiente) VALUES (2, 4, 3, 4);
INSERT INTO workflow_solicitud (id_estado_solicitud_actual, id_tipo_solicitud_actual, id_estado_solicitud_siguiente, id_tipo_solicitud_siguiente) VALUES (3, 4, 4, 4);
INSERT INTO workflow_solicitud (id_estado_solicitud_actual, id_tipo_solicitud_actual, id_estado_solicitud_siguiente, id_tipo_solicitud_siguiente) VALUES (4, 4, 5, 4);

INSERT INTO workflow_solicitud (id_estado_solicitud_actual, id_tipo_solicitud_actual, id_estado_solicitud_siguiente, id_tipo_solicitud_siguiente) VALUES (1, 5, 2, 5);
INSERT INTO workflow_solicitud (id_estado_solicitud_actual, id_tipo_solicitud_actual, id_estado_solicitud_siguiente, id_tipo_solicitud_siguiente) VALUES (2, 5, 3, 5);
INSERT INTO workflow_solicitud (id_estado_solicitud_actual, id_tipo_solicitud_actual, id_estado_solicitud_siguiente, id_tipo_solicitud_siguiente) VALUES (3, 5, 4, 5);
INSERT INTO workflow_solicitud (id_estado_solicitud_actual, id_tipo_solicitud_actual, id_estado_solicitud_siguiente, id_tipo_solicitud_siguiente) VALUES (4, 5, 5, 5);
INSERT INTO workflow_solicitud (id_estado_solicitud_actual, id_tipo_solicitud_actual, id_estado_solicitud_siguiente, id_tipo_solicitud_siguiente) VALUES (5, 5, 6, 5);

INSERT INTO workflow_solicitud (id_estado_solicitud_actual, id_tipo_solicitud_actual, id_estado_solicitud_siguiente, id_tipo_solicitud_siguiente) VALUES (1, 6, 2, 6);
INSERT INTO workflow_solicitud (id_estado_solicitud_actual, id_tipo_solicitud_actual, id_estado_solicitud_siguiente, id_tipo_solicitud_siguiente) VALUES (2, 6, 3, 6);

-- TABLA: PUESTO_PLAZA_COMEVAL.
DROP TABLE IF EXISTS puesto_plaza_comeval;
CREATE TABLE puesto_plaza_comeval (
    id_puesto_orden SMALLINT NOT NULL, 
    puesto CHARACTER(6) NOT NULL,
    CONSTRAINT pk_puesto_plaza_comeval PRIMARY KEY (id_puesto_orden),
    CONSTRAINT fk_puesto_plaza_comeval_1 FOREIGN KEY (puesto) REFERENCES puesto (puesto)
);
INSERT INTO puesto_plaza_comeval (id_puesto_orden, puesto) VALUES (1,'210111');
INSERT INTO puesto_plaza_comeval (id_puesto_orden, puesto) VALUES (2,'210121');
INSERT INTO puesto_plaza_comeval (id_puesto_orden, puesto) VALUES (3,'210131');
INSERT INTO puesto_plaza_comeval (id_puesto_orden, puesto) VALUES (4,'210141');
INSERT INTO puesto_plaza_comeval (id_puesto_orden, puesto) VALUES (5,'210151');
INSERT INTO puesto_plaza_comeval (id_puesto_orden, puesto) VALUES (6,'210161');
INSERT INTO puesto_plaza_comeval (id_puesto_orden, puesto) VALUES (7,'210165');
INSERT INTO puesto_plaza_comeval (id_puesto_orden, puesto) VALUES (8,'210170');
INSERT INTO puesto_plaza_comeval (id_puesto_orden, puesto) VALUES (9,'210175');
INSERT INTO puesto_plaza_comeval (id_puesto_orden, puesto) VALUES (10,'210180');
INSERT INTO puesto_plaza_comeval (id_puesto_orden, puesto) VALUES (11,'210185');
INSERT INTO puesto_plaza_comeval (id_puesto_orden, puesto) VALUES (12,'210190');

-- TABLA: PROMOCION_DOCENTE_TIPOASCENSO.
DROP TABLE IF EXISTS promocion_docente_tipoascenso;
CREATE TABLE promocion_docente_tipoascenso (
    id_estado_solicitud BIGINT NOT NULL,
    id_tipo_solicitud BIGINT NOT NULL,
    tipoascenso SMALLINT NOT NULL,
    CONSTRAINT pk_promocion_docente_tipoascenso PRIMARY KEY (id_estado_solicitud, id_tipo_solicitud, tipoascenso),
    CONSTRAINT fk_promocion_docente_tipoascenso_1 FOREIGN KEY (id_estado_solicitud, id_tipo_solicitud) REFERENCES estado_solicitud_comeval (id_estado_solicitud, id_tipo_solicitud),
    CONSTRAINT fk_promocion_docente_tipoascenso_2 FOREIGN KEY (tipoascenso) REFERENCES tipoascenso (tipoascenso)
);
INSERT INTO promocion_docente_tipoascenso (id_estado_solicitud, id_tipo_solicitud, tipoascenso) VALUES (1,1,2);
INSERT INTO promocion_docente_tipoascenso (id_estado_solicitud, id_tipo_solicitud, tipoascenso) VALUES (1,1,3);
INSERT INTO promocion_docente_tipoascenso (id_estado_solicitud, id_tipo_solicitud, tipoascenso) VALUES (1,1,4);
INSERT INTO promocion_docente_tipoascenso (id_estado_solicitud, id_tipo_solicitud, tipoascenso) VALUES (2,1,1);
INSERT INTO promocion_docente_tipoascenso (id_estado_solicitud, id_tipo_solicitud, tipoascenso) VALUES (2,1,6);
INSERT INTO promocion_docente_tipoascenso (id_estado_solicitud, id_tipo_solicitud, tipoascenso) VALUES (3,1,5);

-- TABLA: COMEVAL_PROMOCION_DOCENTE.
DROP TABLE IF EXISTS comeval_promocion_docente;
CREATE TABLE comeval_promocion_docente (
    id_comeval_promocion_docente BIGINT NOT NULL,
    personal CHARACTER(9) NOT NULL,
    tipoascenso SMALLINT NOT NULL,
    puesto CHARACTER(6) NOT NULL,
    fecha_promueve TIMESTAMP,
    nota_ref_comeval CHARACTER(20),
    fecha_nota_ref_comeval TIMESTAMP,
    usuario VARCHAR(50) NOT NULL,
    fecha_ingreso TIMESTAMP NOT NULL,
    id_estado_solicitud BIGINT NOT NULL,
    id_tipo_solicitud BIGINT NOT NULL,
    rechazado SMALLINT NOT NULL DEFAULT 0,
    id_estado_solicitud_rechazado BIGINT,
    id_tipo_solicitud_rechazado BIGINT,
    revision_comeval SMALLINT NOT NULL DEFAULT 0,
    revision_secretario_academico SMALLINT NOT NULL DEFAULT 0,
    CONSTRAINT pk_comeval_promocion_docente PRIMARY KEY (id_comeval_promocion_docente),
    CONSTRAINT fk_comeval_promocion_docente_1 FOREIGN KEY (personal) REFERENCES personal (personal),
    CONSTRAINT fk_comeval_promocion_docente_2 FOREIGN KEY (tipoascenso) REFERENCES tipoascenso (tipoascenso),
    CONSTRAINT fk_comeval_promocion_docente_3 FOREIGN KEY (puesto) REFERENCES puesto (puesto),
    CONSTRAINT fk_comeval_promocion_docente_4 FOREIGN KEY (id_estado_solicitud, id_tipo_solicitud) REFERENCES estado_solicitud_comeval (id_estado_solicitud, id_tipo_solicitud),
    CONSTRAINT fk_comeval_promocion_docente_5 FOREIGN KEY (id_estado_solicitud_rechazado, id_tipo_solicitud_rechazado) REFERENCES estado_solicitud_comeval (id_estado_solicitud, id_tipo_solicitud)
);

-- TABLA: COMEVAL_PROMOCION_DOCENTE_ARCHIVOS.
DROP TABLE IF EXISTS comeval_promocion_docente_archivos;
CREATE TABLE comeval_promocion_docente_archivos (
    id_comeval_promocion_docente BIGINT NOT NULL,
    id_archivo BIGINT NOT NULL,
    nombre_archivo VARCHAR(200) NOT NULL DEFAULT '-',
    nombre_archivo_real VARCHAR(200) NOT NULL DEFAULT '-',
    CONSTRAINT pk_comeval_promocion_docente_archivos PRIMARY KEY (id_comeval_promocion_docente, id_archivo),
    CONSTRAINT fk_comeval_promocion_docente_archivos_1 FOREIGN KEY (id_comeval_promocion_docente) REFERENCES comeval_promocion_docente (id_comeval_promocion_docente)
);

-- TABLA: COMEVAL_LICENCIA_DOCENTE.
DROP TABLE IF EXISTS comeval_licencia_docente;
CREATE TABLE comeval_licencia_docente (
    id_comeval_licencia_docente BIGINT NOT NULL,
    personal CHARACTER(9) NOT NULL,
    id_motivo_licencia INTEGER NOT NULL,
    id_tipo_licencia SMALLINT,
    goce_sueldo VARCHAR(2) NOT NULL DEFAULT '-', 
    fecha_inicio_licencia TIMESTAMP NOT NULL,
    fecha_final_licencia TIMESTAMP NOT NULL,
    usuario VARCHAR(50) NOT NULL,
    fecha_ingreso TIMESTAMP NOT NULL,
    id_estado_solicitud BIGINT NOT NULL,
    id_tipo_solicitud BIGINT NOT NULL,
    rechazado SMALLINT NOT NULL DEFAULT 0,
    id_estado_solicitud_rechazado BIGINT,
    id_tipo_solicitud_rechazado BIGINT,
    ingreso_siif_visto_bueno_escuela SMALLINT NOT NULL DEFAULT 0,
    tipo_licencia_secretario_academico SMALLINT NOT NULL DEFAULT 0,
    acuerdo_decanatura SMALLINT NOT NULL DEFAULT 0,
    notificacion_tesoreria SMALLINT NOT NULL DEFAULT 0,    
    CONSTRAINT pk_comeval_licencia_docente PRIMARY KEY (id_comeval_licencia_docente),
    CONSTRAINT fk_comeval_licencia_docente_1 FOREIGN KEY (personal) REFERENCES personal (personal),
    CONSTRAINT fk_comeval_licencia_docente_2 FOREIGN KEY (id_motivo_licencia) REFERENCES licencia_motivo (id),
    CONSTRAINT fk_comeval_licencia_docente_3 FOREIGN KEY (id_tipo_licencia) REFERENCES tipolicencia (tipolicencia),
    CONSTRAINT fk_comeval_licencia_docente_4 FOREIGN KEY (id_estado_solicitud, id_tipo_solicitud) REFERENCES estado_solicitud_comeval (id_estado_solicitud, id_tipo_solicitud)
);

-- TABLA: COMEVAL_LICENCIA_DOCENTE_PLAZAS.
DROP TABLE IF EXISTS comeval_licencia_docente_plazas;
CREATE TABLE comeval_licencia_docente_plazas (
    id_comeval_licencia_docente BIGINT NOT NULL,
	personal CHARACTER(9) NOT NULL,
	plaza INTEGER NOT NULL,
	periodo INTEGER NOT NULL,
	anio INTEGER NOT NULL,
	subpartida CHARACTER(7) NOT NULL,
	renglon CHARACTER(3) NOT NULL,
	numero_plaza INTEGER NOT NULL,
	CONSTRAINT pk_comeval_licencia_docente_plazas PRIMARY KEY (id_comeval_licencia_docente, personal, plaza, periodo, anio, subpartida, renglon),
    CONSTRAINT fk_comeval_licencia_docente_plazas_1 FOREIGN KEY (id_comeval_licencia_docente) REFERENCES comeval_licencia_docente (id_comeval_licencia_docente),
    CONSTRAINT fk_comeval_licencia_docente_plazas_2 FOREIGN KEY (personal, plaza, periodo, anio, subpartida, renglon) REFERENCES plazapersonal (personal, plaza, periodo, anio, subpartida, renglon)
);

-- TABLA: COMEVAL_AMONESTACION_DOCENTE.
DROP TABLE IF EXISTS comeval_amonestacion_docente;
CREATE TABLE comeval_amonestacion_docente (
    id_comeval_amonestacion_docente BIGINT NOT NULL,
    personal CHARACTER(9) NOT NULL,
    descripcion_solicitud TEXT NOT NULL DEFAULT '-',
    id_solicitud_acta BIGINT,
    no_acta VARCHAR(50),
    anio_acta VARCHAR(50),
    punto_acta VARCHAR(50),
    inciso_acta VARCHAR(50),
    fecha_acta TIMESTAMP, 
    resolucion_acta TEXT,
    fecha_ingreso TIMESTAMP NOT NULL,
    id_estado_solicitud BIGINT NOT NULL,
    id_tipo_solicitud BIGINT NOT NULL,
    rechazado SMALLINT NOT NULL DEFAULT 0,
    CONSTRAINT pk_comeval_amonestacion_docente PRIMARY KEY (id_comeval_amonestacion_docente),
    CONSTRAINT fk_comeval_amonestacion_docente_1 FOREIGN KEY (personal) REFERENCES personal (personal),
    CONSTRAINT fk_comeval_amonestacion_docente_2 FOREIGN KEY (id_estado_solicitud, id_tipo_solicitud) REFERENCES estado_solicitud_comeval (id_estado_solicitud, id_tipo_solicitud)
);

-- TABLA: COMEVAL_CAMBIO_HORARIO.
DROP TABLE IF EXISTS comeval_cambio_horario;
CREATE TABLE comeval_cambio_horario (
    id_comeval_cambio_horario BIGINT NOT NULL,
    personal CHARACTER(9) NOT NULL,
    descripcion_solicitud TEXT NOT NULL DEFAULT '-',
    id_solicitud_acta BIGINT,
    no_acta VARCHAR(50),
    anio_acta VARCHAR(50),
    punto_acta VARCHAR(50),
    inciso_acta VARCHAR(50),
    fecha_acta TIMESTAMP, 
    resolucion_acta TEXT,
    fecha_ingreso TIMESTAMP NOT NULL,
    id_estado_solicitud BIGINT NOT NULL,
    id_tipo_solicitud BIGINT NOT NULL,
    rechazado SMALLINT NOT NULL DEFAULT 0,
    visto_bueno_director SMALLINT NOT NULL DEFAULT 0,
    visto_bueno_secretario_academico SMALLINT NOT NULL DEFAULT 0,
    CONSTRAINT pk_comeval_cambio_horario PRIMARY KEY (id_comeval_cambio_horario),
    CONSTRAINT fk_comeval_cambio_horario_1 FOREIGN KEY (personal) REFERENCES personal (personal),
    CONSTRAINT fk_comeval_cambio_horario_2 FOREIGN KEY (id_estado_solicitud, id_tipo_solicitud) REFERENCES estado_solicitud_comeval (id_estado_solicitud, id_tipo_solicitud)
);

-- TABLA: COMEVAL_CAMBIO_HORARIO_PLAZA.
DROP TABLE IF EXISTS comeval_cambio_horario_plaza;
CREATE TABLE comeval_cambio_horario_plaza (
	id_comeval_cambio_horario BIGINT NOT NULL,
	id_horario INTEGER NOT NULL,
	horainicio TIME NOT NULL,
	horafin TIME NOT NULL,
	dias CHARACTER(7) NOT NULL,
	personal CHARACTER(9) NOT NULL,
	plaza INTEGER NOT NULL,
	periodo INTEGER NOT NULL,
	anio INTEGER NOT NULL,
	subpartida CHARACTER(7) NOT NULL,
	renglon CHARACTER(3) NOT NULL,
	CONSTRAINT pk_comeval_cambio_horario_plaza PRIMARY KEY (id_comeval_cambio_horario, id_horario, personal, plaza, periodo, anio, subpartida, renglon),
    CONSTRAINT fk_comeval_cambio_horario_plaza_1 FOREIGN KEY (id_comeval_cambio_horario) REFERENCES comeval_cambio_horario (id_comeval_cambio_horario),
    CONSTRAINT fk_comeval_cambio_horario_plaza_2 FOREIGN KEY (personal, plaza, periodo, anio, subpartida, renglon) REFERENCES plazapersonal (personal, plaza, periodo, anio, subpartida, renglon)
);

-- TABLA: COMEVAL_AMPLIACION_HORARIO.
DROP TABLE IF EXISTS comeval_ampliacion_horario;
CREATE TABLE comeval_ampliacion_horario (
    id_comeval_ampliacion_horario BIGINT NOT NULL,
    personal CHARACTER(9) NOT NULL,
    id_plaza_temporal INTEGER,
    id_plaza_indefinido INTEGER,
    descripcion_solicitud TEXT NOT NULL DEFAULT '-',
    id_solicitud_acta BIGINT,
    no_acta VARCHAR(50),
    anio_acta VARCHAR(50),
    punto_acta VARCHAR(50),
    inciso_acta VARCHAR(50),
    fecha_acta TIMESTAMP, 
    resolucion_acta TEXT,
    fecha_ingreso TIMESTAMP NOT NULL,
    id_estado_solicitud BIGINT NOT NULL,
    id_tipo_solicitud BIGINT NOT NULL,
    rechazado SMALLINT NOT NULL DEFAULT 0,
    visto_bueno_director SMALLINT NOT NULL DEFAULT 0,
    visto_bueno_secretario_academico SMALLINT NOT NULL DEFAULT 0,
    notificacion_tesoreria SMALLINT NOT NULL DEFAULT 0,
    CONSTRAINT pk_comeval_ampliacion_horario PRIMARY KEY (id_comeval_ampliacion_horario),
    CONSTRAINT fk_comeval_ampliacion_horario_1 FOREIGN KEY (personal) REFERENCES personal (personal),
    CONSTRAINT fk_comeval_ampliacion_horario_2 FOREIGN KEY (id_plaza_temporal) REFERENCES plaza (plaza),
    CONSTRAINT fk_comeval_ampliacion_horario_3 FOREIGN KEY (id_plaza_indefinido) REFERENCES plaza (plaza),
    CONSTRAINT fk_comeval_ampliacion_horario_4 FOREIGN KEY (id_estado_solicitud, id_tipo_solicitud) REFERENCES estado_solicitud_comeval (id_estado_solicitud, id_tipo_solicitud)
);

-- TABLA: COMEVAL_AMPLIACION_HORARIO.
DROP TABLE IF EXISTS comeval_ampliacion_horario_plaza;
CREATE TABLE comeval_ampliacion_horario_plaza (
	id_comeval_ampliacion_horario BIGINT NOT NULL,
	id_horario INTEGER NOT NULL,
	horainicio TIME NOT NULL,
	horafin TIME NOT NULL,
	dias CHARACTER(7) NOT NULL,
	CONSTRAINT pk_comeval_ampliacion_horario_plaza PRIMARY KEY (id_comeval_ampliacion_horario, id_horario),
    CONSTRAINT fk_comeval_ampliacion_horario_plaza_1 FOREIGN KEY (id_comeval_ampliacion_horario) REFERENCES comeval_ampliacion_horario (id_comeval_ampliacion_horario)
);

-- TABLA: COMEVAL_CARGA_EVAL_COMEVAL.
DROP TABLE IF EXISTS comeval_carga_eval_comeval;
CREATE TABLE comeval_carga_eval_comeval (
    id_comeval_carga_eval_comeval BIGINT NOT NULL,
    descripcion_solicitud TEXT NOT NULL DEFAULT '-',
    path_archivo TEXT NOT NULL DEFAULT '-',
    id_solicitud_acta BIGINT,
    no_acta VARCHAR(50),
    anio_acta VARCHAR(50),
    punto_acta VARCHAR(50),
    inciso_acta VARCHAR(50),
    fecha_acta TIMESTAMP, 
    resolucion_acta TEXT,
    fecha_ingreso TIMESTAMP NOT NULL,
    id_estado_solicitud BIGINT NOT NULL,
    id_tipo_solicitud BIGINT NOT NULL,
    rechazado SMALLINT NOT NULL DEFAULT 0,
    CONSTRAINT pk_comeval_carga_eval_comeval PRIMARY KEY (id_comeval_carga_eval_comeval),
    CONSTRAINT fk_comeval_carga_eval_comeval_1 FOREIGN KEY (id_estado_solicitud, id_tipo_solicitud) REFERENCES estado_solicitud_comeval (id_estado_solicitud, id_tipo_solicitud)
);

-- TABLA: SOLICITUD_WORKFLOW_HISTORIAL.
DROP TABLE IF EXISTS solicitud_workflow_historial;
CREATE TABLE solicitud_workflow_historial (
    id_solicitud BIGINT NOT NULL,
    id_estado_solicitud BIGINT NOT NULL,
    id_tipo_solicitud BIGINT NOT NULL,
    id_workflow BIGINT NOT NULL,
    usuario VARCHAR(50) NOT NULL,
    fecha TIMESTAMP NOT NULL,
    rechazado SMALLINT NOT NULL DEFAULT 0,
    fecha_rechazado TIMESTAMP,
    CONSTRAINT pk_solicitud_workflow_historial PRIMARY KEY (id_solicitud, id_estado_solicitud, id_tipo_solicitud, id_workflow),
    CONSTRAINT fk_solicitud_workflow_historial_1 FOREIGN KEY (id_estado_solicitud, id_tipo_solicitud) REFERENCES estado_solicitud_comeval (id_estado_solicitud, id_tipo_solicitud)
);

-- TABLA: COMEVAL_ACTA_SOLICITUD.
DROP TABLE IF EXISTS comeval_acta_solicitud;
CREATE TABLE comeval_acta_solicitud (
	id_solicitud BIGINT NOT NULL,
	id_tipo_solicitud BIGINT NOT NULL,
	id_solicitud_acta BIGINT,
	no_acta VARCHAR(50),
	anio_acta VARCHAR(50),
	punto_acta VARCHAR(50),
	inciso_acta VARCHAR(50),
	fecha_acta TIMESTAMP, 
	resolucion_acta TEXT,
	aprobado SMALLINT,
	CONSTRAINT pk_comeval_acta_solicitud PRIMARY KEY (id_solicitud, id_tipo_solicitud),
	CONSTRAINT fk_comeval_acta_solicitud_1 FOREIGN KEY (id_tipo_solicitud) REFERENCES tipo_solicitud_comeval (id_tipo_solicitud)
);

-- TABLA: COMEVAL_PROMOCION_DOCENTE_OBSERVACION.
DROP TABLE IF EXISTS comeval_solicitud_observacion;
CREATE TABLE comeval_solicitud_observacion (
    id_solicitud BIGINT NOT NULL,
	id_tipo_solicitud BIGINT NOT NULL,
    id_observacion BIGINT NOT NULL,
    dependencia VARCHAR(50) NOT NULL,
    fecha_hora TIMESTAMP,
    observacion TEXT NOT NULL DEFAULT '-',
    CONSTRAINT pk_comeval_solicitud_observacion PRIMARY KEY (id_solicitud, id_tipo_solicitud, id_observacion),
	CONSTRAINT fk_comeval_solicitud_observacion_1 FOREIGN KEY (id_tipo_solicitud) REFERENCES tipo_solicitud_comeval (id_tipo_solicitud)
);

