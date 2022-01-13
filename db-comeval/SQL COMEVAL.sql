select rc.* from rest_credential rc;

-- TABLAS DE CONFIGURACION Y CATALOGOS.
SELECT t.* FROM tipo_solicitud_comeval t ORDER BY t.id_tipo_solicitud;
SELECT t.* FROM estado_solicitud_comeval t WHERE t.id_tipo_solicitud = 1 ORDER BY t.id_tipo_solicitud, t.id_estado_solicitud;

-- WORKFLOW ESTADOS SOLICITUDES. 
SELECT
	(select ts.nombre from tipo_solicitud_comeval ts where ts.id_tipo_solicitud = ws.id_tipo_solicitud_actual) tipo_solicitud,
	(select es.nombre from estado_solicitud_comeval es where es.id_estado_solicitud = ws.id_estado_solicitud_actual and es.id_tipo_solicitud = ws.id_tipo_solicitud_actual) estado_solicitud_actual, 
	(select es.nombre from estado_solicitud_comeval es where es.id_estado_solicitud = ws.id_estado_solicitud_siguiente and es.id_tipo_solicitud = ws.id_tipo_solicitud_siguiente) estado_solicitud_anterior
FROM workflow_solicitud ws 
WHERE ws.id_tipo_solicitud_actual = 1 AND ws.id_tipo_solicitud_siguiente = 1
ORDER BY ws.id_tipo_solicitud_actual, ws.id_estado_solicitud_actual, ws.id_tipo_solicitud_siguiente, ws.id_estado_solicitud_siguiente;

-- CONFIGURACIÓN Y ORDEN DE LISTADOS (CATÁLOGOS PROMOCIÓN DOCENTE).
SELECT ppc.* FROM puesto_plaza_comeval ppc;
SELECT pdt.* FROM promocion_docente_tipoascenso pdt;

-- TABLAS TRANSACCIONALES DE LAS SOLICITUDES.
SELECT cpd.* FROM comeval_promocion_docente cpd ORDER BY cpd.id_comeval_promocion_docente;
SELECT cpda.* FROM comeval_promocion_docente_archivos cpda ORDER BY cpda.id_comeval_promocion_docente, cpda.id_archivo;

SELECT cld.* FROM comeval_licencia_docente cld ORDER BY cld.id_comeval_licencia_docente;
SELECT cldp.* FROM comeval_licencia_docente_plazas cldp ORDER BY cldp.id_comeval_licencia_docente;

SELECT cad.* FROM comeval_amonestacion_docente cad;

SELECT cch.* FROM comeval_cambio_horario cch ORDER BY cch.id_comeval_cambio_horario;
SELECT cchp.* FROM comeval_cambio_horario_plaza cchp ORDER BY cchp.id_comeval_cambio_horario;

SELECT cah.* FROM comeval_ampliacion_horario cah ORDER BY cah.id_comeval_ampliacion_horario;
SELECT cahp.* FROM comeval_ampliacion_horario_plaza cahp ORDER BY cahp.id_comeval_ampliacion_horario;

SELECT ccec.* FROM comeval_carga_eval_comeval ccec;
SELECT t.* FROM resultado_evaluacion t;

SELECT cas.* FROM comeval_acta_solicitud cas ORDER BY cas.id_tipo_solicitud, cas.id_solicitud;

SELECT cso.* FROM comeval_solicitud_observacion cso ORDER BY cso.id_tipo_solicitud, cso.id_solicitud, cso.id_observacion;
 
-- SEGUIMIENTOS CAMBIO DE ESTADOS EN LAS SOLICITUDES.
SELECT swh.id_solicitud, swh.id_workflow ,es.nombre estado_solicitud, ts.nombre tipo_solicitud, swh.usuario, swh.fecha, swh.rechazado, swh.fecha_rechazado 
FROM solicitud_workflow_historial swh 
	 LEFT JOIN estado_solicitud_comeval es ON (swh.id_estado_solicitud = es.id_estado_solicitud AND swh.id_tipo_solicitud = es.id_tipo_solicitud) 
	 LEFT JOIN tipo_solicitud_comeval ts ON (es.id_tipo_solicitud = ts.id_tipo_solicitud)
WHERE swh.id_tipo_solicitud = 1 -- AND swh.id_solicitud = 3
ORDER BY swh.id_tipo_solicitud, swh.id_solicitud,  swh.id_workflow, swh.id_estado_solicitud;

-- CODIGO DE PERSONAL PARA PRUEBAS.
SELECT d.* FROM personal d WHERE d.nombre ILIKE '%everest%' AND d.apellido ILIKE '%medinilla%';        -- PERSONAL: 000018150
SELECT d.* FROM personal d WHERE d.nombre ILIKE '%julio%' AND d.apellido ILIKE '%flores%';             -- PERSONAL: 020160418
SELECT d.* FROM personal d WHERE d.nombre ILIKE '%david estuardo%' AND d.apellido ILIKE '%morales%';   -- PERSONAL: 020091178
-- 000013564, 000017976, 000940264

-- PLAZA PERSONAL.
SELECT p.* FROM plazapuesto p WHERE p.puesto='210121';
SELECT p.* FROM plazapersonal p WHERE p.personal = '000018150' ORDER BY p.anio, p.periodo;

-- PLAZAPARTIDA.
SELECT p.* FROM plazapartida p WHERE (p.plaza, p.subpartida, p.renglon) IN (SELECT pp.plaza, pp.subpartida, pp.renglon 
                                                                            FROM plazapersonal pp 
                                                                            WHERE pp.personal = '000018150');
-- PLAZA POR PERSONA.
SELECT ppp.* FROM plaza ppp WHERE ppp.plaza IN (SELECT p.plaza 
                                                FROM plazapartida p 
                                                WHERE (p.plaza, p.subpartida, p.renglon) IN (SELECT pp.plaza, pp.subpartida, pp.renglon 
                                                                                             FROM plazapersonal pp 
                                                                                             WHERE pp.personal = '000018150'));
-- SUBPARTIDA POR PERSONAL.
SELECT s.* FROM subpartida s WHERE s.subpartida IN (SELECT p.subpartida 
                                                    FROM plazapartida p 
                                                    WHERE (p.plaza, p.subpartida, p.renglon) IN (SELECT pp.plaza, pp.subpartida, pp.renglon 
                                                                                                 FROM plazapersonal pp 
                                                                                                 WHERE pp.personal = '000018150'));
                                                                                                
                                                                                                
                                                                                                
-- ========= TITULARIDADHISTORIAL Y TITULARIDAD.
SELECT th.* FROM titularidadhistorial th WHERE th.personal='000018150' ORDER BY fechaascenso;
SELECT t.* FROM titularidad t WHERE t.personal='000018150';
-- INSERT INTO titularidad (personal, puesto, acta, fechasistema, titular, dictamen, dictamenemisor) 
-- VALUES ('000017976','210170',1,'2021-10-01 17:38:02.728', true, null, 1);
-- ACTUALIZAR PUESTO EN LA TABLA PLAZAPERSONAL.


-- EXTREAER LA PLAZA ACTUAL DEL DOCENTE.
SELECT p.* 
FROM plaza p 
WHERE p.plaza in (SELECT p1.plaza 
                  FROM plazapartida p1 WHERE (p1.plaza, p1.subpartida, p1.renglon) in (SELECT p2.plaza, p2.subpartida, p2.renglon 
                                                                                       FROM plazapersonal p2 
                                                                                       WHERE p2.personal = '000018150' AND p2.anio=(EXTRACT(YEAR FROM CURRENT_DATE)-1) AND p2.periodo=(CASE WHEN EXTRACT(MONTH FROM CURRENT_DATE)<=6 THEN 1 ELSE 2 END)));

                                                                                      
-- REPORTE HOJA RELACION LABORAL.
SELECT pp.fechainicio, pp.anio, pp.periodo, p.nombre
FROM plazapersonal pp LEFT JOIN puesto p ON (pp.puesto = p.puesto) 
WHERE pp.personal = '000018150' 
ORDER BY pp.anio, pp.periodo;

SELECT t.* FROM titularidadhistorial t WHERE t.personal='000018150';
SELECT l.* FROM licencia l WHERE l.personal='000018150';

SELECT EXTRACT(YEAR FROM t.fechaascenso) ANIO, EXTRACT(MONTH FROM t.fechaascenso) MES, t.personal REGISTRO_PERSONAL, p.nombre || ' ' || p.apellido NOMBRE, CASE WHEN t.titular THEN 'TITULAR' ELSE '-'  END CATEGORIA, t.* 
FROM titularidadhistorial t LEFT JOIN personal p ON (t.personal = p.personal)
WHERE t.personal = '000006182' 
ORDER BY ANIO, MES;

SELECT DISTINCT swh.id_estado_solicitud, esc.nombre
FROM solicitud_workflow_historial swh
LEFT JOIN estado_solicitud_comeval esc ON (swh.id_tipo_solicitud=esc.id_tipo_solicitud AND swh.id_estado_solicitud=esc.id_estado_solicitud)
WHERE swh.id_solicitud=1 AND swh.id_estado_solicitud < 4;

