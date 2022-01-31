SELECT c.* FROM asunto c WHERE c.nombre ILIKE '%audi%';
-- TABLAS PARA REGISTRAR LA SOLICITUD EN LAS TABLAS DE LA APLICACION DE ACTAS.
	-- 31 AMPLIACIONES DE HORARIO.
	-- 37 EVALUACIONES DOCENTES.
	-- 42 LICENCIAS.
	-- 43 PROMOCIONES DOCENTES. 
	-- 50 CAMBIOS DE HORARIO.
    -- 29 AUDIENCIAS.
SELECT c.* FROM asunto c WHERE c.asunto IN (31, 37, 42, 43, 50, 29);
SELECT es.* FROM estado_solicitud es; -- 0: INGRESADA VIA WEB.
SELECT s.* FROM solicitud s WHERE s.asunto = 37;
-- SELECT coalesce(max(s.solicitud) + 1, 1) max_id_solicitud FROM solicitud s;
-- SELECT nextval('seq_solicitud');
SELECT s.* FROM solicitud s WHERE s.solicitud in (23893, 23894, 23889, 23891, 23890, 23892, 23895, 23896, 23897, 23898);
SELECT ss.* FROM solicitud_solicitante ss WHERE ss.solicitud IN (23893, 23894, 23889, 23891, 23890, 23892, 23895, 23896, 23897, 23898);
SELECT sc.* FROM solicitud_contenido sc WHERE sc.solicitud IN (23893, 23894, 23889, 23891, 23890, 23892, 23895, 23896, 23897, 23898);

SELECT c.* FROM contenido c WHERE c.contenido IN (31352, 31353, 31354, 31355, 31357, 31358, 31359);
SELECT c.* FROM contenido c WHERE c.contenido = 31350;
SELECT r.* FROM resolucion r WHERE r.contenido IN (31352, 31353, 31354, 31355, 31357, 31358, 31359);
UPDATE resolucion SET aprobado = true, acuerdo = 'ACUERDO TEXTO DE PRUEBA.' WHERE contenido IN (31352, 31353, 31354, 31355, 31357, 31358, 31359);

-- QUERY OREJA DE COCHE.
SELECT
	coalesce(contenido_hijo.numeroacta,0) no_acta,
	coalesce(contenido_hijo.anioacta,0) anio_acta,
	coalesce(contenido_padre.numero,0) punto,
	coalesce(contenido_hijo.numero,0) inciso,
	coalesce(contenido_hijo.tipoacta ,0) tipoacta 
FROM
	contenido contenido_hijo
	INNER JOIN contenido contenido_padre on (contenido_padre.contenido = contenido_hijo.contenidopadre)
	INNER JOIN solicitud_contenido sc on (sc.contenido = contenido_hijo.contenido)
WHERE
	sc.solicitud IN (23893, 23894, 23889, 23891, 23890, 23892, 23895);

-- RESOLUCION.
-- a) Aprobar lo informado con respecto al Ingeniero Julio Cesar Solares Peñate , informando a donde corresponda, b) 
--    Felicitar al Ingeniero Solares Peñate la promoción adquirida tomando en cuenta los resultados de su correspondiente evaluación, 
--    incentivándolo a continuar su preparación en beneficio de la academia y de su superación como docente de nuestra unidad académica.

-- a) Aprobar lo informado con respecto al Ingeniero Julio Cesar Solares Peñate , informando a donde corresponda, b) 
--    Felicitar al Ingeniero Solares Peñate la promoción adquirida tomando en cuenta los resultados de su correspondiente evaluación, 
--    incentivándolo a continuar su preparación en beneficio de la academia y de su superación como docente de nuestra unidad académica.

-- PROMOCION-DOCENTE.
-- Se recibió nota de Ref. COMEVAL 120-10.2016 de fecha 14 de octubre de 2016, presentada por el Ing. José Alfonso Velásquez Diaz, Coordinador de COMEVAL 
-- quien presenta el cuadro de promoción docente del Ingeniero Edgar Francisco Rodas Robledo  con registro de personal No. 970169, quien promueve de 
-- Titular III a Titular IV a partir del 01 de julio de 2014.

-- LICENCIA-DOCENTE.
-- Se recibió solicitud de licencia sin goce de sueldo del Ing. Freiry Javier Gramajo López, Profesor TItular II de la Escuela de CIencias y Sistemas, 
-- registro de personal No. 17271, plaza No. 58. Dicha licencia la solicita por tener asuntos personales que no le permiten atender su curso durante 
-- el presente semestre. Dicha licencia tiene vigencia del 01 de julio al 31 de diciembre de 2014.

-- CAMBIO DE HORARIO
-- Se recibió nota de fecha 9 de septiembre de 2014, enviada por el Ing. Byron Giovanni Palacios Colindres, Profesor de la escuela de Ingeniería Mecánica, 
-- con el Visto Bueno del Ing. Julio Cesar Campos Paiz, Director de escuela de Ingeniería Mecánica quien informa lo siguiente:
-- "Expongo que atendiendo a las necesidades de los estudiantes y de la escuela de Ingeniería Mecánica, en años anteriores se  me solicito de parte de la 
-- dirección de escuela, modificar mi horario de contratación en la plaza número 49 que era de 16:00 a 18:00 horas por el horario de 18:0 a 20:00 horas. 
-- A lo cual accedí en beneficio de los estudiantes y de la escuela misma.  Como en su momento no se hizo la modificación correspondiente a dicho horario.
-- Solicito a Junta Directiva autorice el cambio de horario en forma permanente y retroactiva a partir del 01 de julio de 2014 de la forma siguiente:
-- Dice:
-- De 16:00 a 18:00 horas.
-- Debe decir:
-- De 18:00 a 20:00 horas.

-- Se recibió nota de Ref. COMEVAL ###-##.#### de fecha ## de #MES# de ####, 
-- presentada por el/la Ing/Inga (TABLA TITULO o "docente"). EDVIN FRANCISCO NAVAS MEJIA, 
-- usuario de la aplicación Procesos Titulares de la Facultad de Ingenieria, 
-- quien presenta el cuadro de promoción docente del ingeniero/a EVEREST DARWIN MEDINILLA RODRIGUEZ con registro de personal No. 000018150, 
-- quien promueve de TITULAR I a TITULAR IV a partir del 15 de octubre de 2021.

-- a) Aprobar lo informado con respecto al Ingeniero Julio Cesar Solares Peñate , informando a donde corresponda, 
-- b) Felicitar al Ingeniero Solares Peñate la promoción adquirida tomando en cuenta los resultados de su correspondiente evaluación, 
-- incentivándolo a continuar su preparación en beneficio de la academia y de su superación como docente de nuestra unidad académica.

-- NOTAS EVALUACION DOCENTE.
-- 'Se recibió nota de Ref. S 694-2014 de fecha 20 de agosto de 2014, 
-- enviada por el Ing(a). Hugo Humberto Rivera Pérez, Secretario Académico de la Facultad de ingeniería, 
-- quien presenta las NOTAS DE LAS EVALUACIONES DOCENTES del año 2021 de los profesionales docente.' 
