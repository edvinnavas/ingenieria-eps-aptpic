select rc.* from rest_credential rc;

-- TABLAS DE CONFIGURACION Y CATALOGOS.
SELECT t.* FROM tipo_solicitud_comeval t ORDER BY t.id_tipo_solicitud;
SELECT t.* FROM estado_solicitud_comeval t WHERE t.id_tipo_solicitud=5 ORDER BY t.id_tipo_solicitud, t.id_estado_solicitud;

-- WORKFLOW ESTADOS SOLICITUDES. 
SELECT ws.* FROM workflow_solicitud ws;
SELECT
	(select ts.nombre from tipo_solicitud_comeval ts where ts.id_tipo_solicitud = ws.id_tipo_solicitud_actual) tipo_solicitud,
	(select es.nombre from estado_solicitud_comeval es where es.id_estado_solicitud = ws.id_estado_solicitud_actual and es.id_tipo_solicitud = ws.id_tipo_solicitud_actual) estado_solicitud_actual, 
	(select es.nombre from estado_solicitud_comeval es where es.id_estado_solicitud = ws.id_estado_solicitud_siguiente and es.id_tipo_solicitud = ws.id_tipo_solicitud_siguiente) estado_solicitud_anterior
FROM workflow_solicitud ws 
WHERE ws.id_tipo_solicitud_actual = 5 AND ws.id_tipo_solicitud_siguiente = 5
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

SELECT ccec.* FROM comeval_notas_evaluacion_docente ccec;

SELECT t.* FROM resultado_evaluacion t;

SELECT cas.* FROM comeval_acta_solicitud cas ORDER BY cas.id_tipo_solicitud, cas.id_solicitud;

SELECT cso.* FROM comeval_solicitud_observacion cso WHERE cso.id_tipo_solicitud=4 ORDER BY cso.id_tipo_solicitud, cso.id_solicitud, cso.id_observacion;
 
-- SEGUIMIENTOS CAMBIO DE ESTADOS EN LAS SOLICITUDES.
SELECT swh.id_solicitud, swh.id_workflow ,es.nombre estado_solicitud, ts.nombre tipo_solicitud, swh.usuario, swh.fecha, swh.rechazado, swh.fecha_rechazado 
FROM solicitud_workflow_historial swh 
	 LEFT JOIN estado_solicitud_comeval es ON (swh.id_estado_solicitud = es.id_estado_solicitud AND swh.id_tipo_solicitud = es.id_tipo_solicitud) 
	 LEFT JOIN tipo_solicitud_comeval ts ON (es.id_tipo_solicitud = ts.id_tipo_solicitud)
WHERE swh.id_tipo_solicitud = 4 -- AND swh.id_solicitud = 1
ORDER BY swh.id_tipo_solicitud, swh.id_solicitud,  swh.id_workflow, swh.id_estado_solicitud;

-- CODIGO DE PERSONAL PARA PRUEBAS.
SELECT d.* FROM personal d WHERE d.nombre ILIKE '%everest%' AND d.apellido ILIKE '%medinilla%';        -- PERSONAL: 000018150, 000930066
SELECT d.* FROM personal d WHERE d.nombre ILIKE '%julio%' AND d.apellido ILIKE '%flores%';             -- PERSONAL: 020160418
SELECT d.* FROM personal d WHERE d.nombre ILIKE '%david estuardo%' AND d.apellido ILIKE '%morales%';   -- PERSONAL: 020091178
-- 000013564, 000017976, 000940264, 000014259, 019990748, 000930336

-- PLAZA PERSONAL.
SELECT p.* FROM plazapersonal p WHERE p.anio=2021 and p.periodo=1 and personal not in ('000013564','000017976','000940264','000014259', '019990748');
SELECT p.* FROM plazapersonal p WHERE p.anio=2021 and p.periodo=1 and p.personal='020000915';
SELECT p.* FROM plazapuesto p WHERE p.puesto='210141';

-- PLAZAPARTIDA.
SELECT p.* FROM plazapartida p WHERE (p.plaza, p.subpartida, p.renglon) IN (SELECT pp.plaza, pp.subpartida, pp.renglon 
                                                                            FROM plazapersonal pp 
                                                                            WHERE pp.personal = '000018150');
-- PLAZA POR PERSONA.
SELECT ppp.* FROM plaza ppp WHERE ppp.plaza IN (SELECT p.plaza 
                                                FROM plazapartida p 
                                                WHERE (p.plaza, p.subpartida, p.renglon) IN (SELECT pp.plaza, pp.subpartida, pp.renglon 
                                                                                             FROM plazapersonal pp 
                                                                                             WHERE pp.personal = '019990748'));
-- SUBPARTIDA POR PERSONAL.
SELECT s.* FROM subpartida s WHERE s.subpartida IN (SELECT p.subpartida 
                                                    FROM plazapartida p 
                                                    WHERE (p.plaza, p.subpartida, p.renglon) IN (SELECT pp.plaza, pp.subpartida, pp.renglon 
                                                                                                 FROM plazapersonal pp 
                                                                                                 WHERE pp.personal = '000018150'));

-- ========= TITULARIDADHISTORIAL Y TITULARIDAD. =========
SELECT th.* FROM titularidadhistorial th WHERE th.personal='000930131' ORDER BY fechaascenso;
SELECT t.* FROM titularidad t WHERE t.personal='000930066';

SELECT l.* FROM licencia l;
