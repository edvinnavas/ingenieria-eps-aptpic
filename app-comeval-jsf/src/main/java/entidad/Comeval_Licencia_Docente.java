package entidad;

import java.io.Serializable;
import java.util.List;

public class Comeval_Licencia_Docente implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Long id_comeval_licencia_docente;
    private String personal;
    private Long id_motivo_licencia;
    private Long id_tipo_licencia;
    private String goce_sueldo;
    private String fecha_inicio_licencia;
    private String fecha_final_licencia;
    private String descripcion_solicitud;
    private Long id_solicitud_acta;
    private String no_acta;
    private String anio_acta;
    private String punto_acta;
    private String inciso_acta;
    private String fecha_acta;
    private String resolucion_acta;
    private String fecha_ingreso;
    private Long id_estado_solicitud;
    private Long id_tipo_solicitud;
    private Long rechazado;
    private Long visto_bueno_director;
    private Long ingreso_siif_traslado;
    private Long confirmar_traslado;
    private Long asignar_tipo_licencia;
    private Long aprobacion_decanatura;
    private Long notificacion_tesoreria;
    private List<Comeval_Licencia_Docente_Plaza> lst_plazas;

    public Comeval_Licencia_Docente(Long id_comeval_licencia_docente, String personal, Long id_motivo_licencia, Long id_tipo_licencia, String goce_sueldo, String fecha_inicio_licencia, String fecha_final_licencia, String descripcion_solicitud, Long id_solicitud_acta, String no_acta, String anio_acta, String punto_acta, String inciso_acta, String fecha_acta, String resolucion_acta, String fecha_ingreso, Long id_estado_solicitud, Long id_tipo_solicitud, Long rechazado, Long visto_bueno_director, Long ingreso_siif_traslado, Long confirmar_traslado, Long asignar_tipo_licencia, Long aprobacion_decanatura, Long notificacion_tesoreria, List<Comeval_Licencia_Docente_Plaza> lst_plazas) {
        this.id_comeval_licencia_docente = id_comeval_licencia_docente;
        this.personal = personal;
        this.id_motivo_licencia = id_motivo_licencia;
        this.id_tipo_licencia = id_tipo_licencia;
        this.goce_sueldo = goce_sueldo;
        this.fecha_inicio_licencia = fecha_inicio_licencia;
        this.fecha_final_licencia = fecha_final_licencia;
        this.descripcion_solicitud = descripcion_solicitud;
        this.id_solicitud_acta = id_solicitud_acta;
        this.no_acta = no_acta;
        this.anio_acta = anio_acta;
        this.punto_acta = punto_acta;
        this.inciso_acta = inciso_acta;
        this.fecha_acta = fecha_acta;
        this.resolucion_acta = resolucion_acta;
        this.fecha_ingreso = fecha_ingreso;
        this.id_estado_solicitud = id_estado_solicitud;
        this.id_tipo_solicitud = id_tipo_solicitud;
        this.rechazado = rechazado;
        this.visto_bueno_director = visto_bueno_director;
        this.ingreso_siif_traslado = ingreso_siif_traslado;
        this.confirmar_traslado = confirmar_traslado;
        this.asignar_tipo_licencia = asignar_tipo_licencia;
        this.aprobacion_decanatura = aprobacion_decanatura;
        this.notificacion_tesoreria = notificacion_tesoreria;
        this.lst_plazas = lst_plazas;
    }

    public Comeval_Licencia_Docente() {
    }

    public Long getId_comeval_licencia_docente() {
        return id_comeval_licencia_docente;
    }

    public void setId_comeval_licencia_docente(Long id_comeval_licencia_docente) {
        this.id_comeval_licencia_docente = id_comeval_licencia_docente;
    }

    public String getPersonal() {
        return personal;
    }

    public void setPersonal(String personal) {
        this.personal = personal;
    }

    public Long getId_motivo_licencia() {
        return id_motivo_licencia;
    }

    public void setId_motivo_licencia(Long id_motivo_licencia) {
        this.id_motivo_licencia = id_motivo_licencia;
    }

    public Long getId_tipo_licencia() {
        return id_tipo_licencia;
    }

    public void setId_tipo_licencia(Long id_tipo_licencia) {
        this.id_tipo_licencia = id_tipo_licencia;
    }

    public String getGoce_sueldo() {
        return goce_sueldo;
    }

    public void setGoce_sueldo(String goce_sueldo) {
        this.goce_sueldo = goce_sueldo;
    }

    public String getFecha_inicio_licencia() {
        return fecha_inicio_licencia;
    }

    public void setFecha_inicio_licencia(String fecha_inicio_licencia) {
        this.fecha_inicio_licencia = fecha_inicio_licencia;
    }

    public String getFecha_final_licencia() {
        return fecha_final_licencia;
    }

    public void setFecha_final_licencia(String fecha_final_licencia) {
        this.fecha_final_licencia = fecha_final_licencia;
    }

    public String getDescripcion_solicitud() {
        return descripcion_solicitud;
    }

    public void setDescripcion_solicitud(String descripcion_solicitud) {
        this.descripcion_solicitud = descripcion_solicitud;
    }

    public Long getId_solicitud_acta() {
        return id_solicitud_acta;
    }

    public void setId_solicitud_acta(Long id_solicitud_acta) {
        this.id_solicitud_acta = id_solicitud_acta;
    }

    public String getNo_acta() {
        return no_acta;
    }

    public void setNo_acta(String no_acta) {
        this.no_acta = no_acta;
    }

    public String getAnio_acta() {
        return anio_acta;
    }

    public void setAnio_acta(String anio_acta) {
        this.anio_acta = anio_acta;
    }

    public String getPunto_acta() {
        return punto_acta;
    }

    public void setPunto_acta(String punto_acta) {
        this.punto_acta = punto_acta;
    }

    public String getInciso_acta() {
        return inciso_acta;
    }

    public void setInciso_acta(String inciso_acta) {
        this.inciso_acta = inciso_acta;
    }

    public String getFecha_acta() {
        return fecha_acta;
    }

    public void setFecha_acta(String fecha_acta) {
        this.fecha_acta = fecha_acta;
    }

    public String getResolucion_acta() {
        return resolucion_acta;
    }

    public void setResolucion_acta(String resolucion_acta) {
        this.resolucion_acta = resolucion_acta;
    }

    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public Long getId_estado_solicitud() {
        return id_estado_solicitud;
    }

    public void setId_estado_solicitud(Long id_estado_solicitud) {
        this.id_estado_solicitud = id_estado_solicitud;
    }

    public Long getId_tipo_solicitud() {
        return id_tipo_solicitud;
    }

    public void setId_tipo_solicitud(Long id_tipo_solicitud) {
        this.id_tipo_solicitud = id_tipo_solicitud;
    }

    public Long getRechazado() {
        return rechazado;
    }

    public void setRechazado(Long rechazado) {
        this.rechazado = rechazado;
    }

    public Long getVisto_bueno_director() {
        return visto_bueno_director;
    }

    public void setVisto_bueno_director(Long visto_bueno_director) {
        this.visto_bueno_director = visto_bueno_director;
    }

    public Long getIngreso_siif_traslado() {
        return ingreso_siif_traslado;
    }

    public void setIngreso_siif_traslado(Long ingreso_siif_traslado) {
        this.ingreso_siif_traslado = ingreso_siif_traslado;
    }

    public Long getConfirmar_traslado() {
        return confirmar_traslado;
    }

    public void setConfirmar_traslado(Long confirmar_traslado) {
        this.confirmar_traslado = confirmar_traslado;
    }

    public Long getAsignar_tipo_licencia() {
        return asignar_tipo_licencia;
    }

    public void setAsignar_tipo_licencia(Long asignar_tipo_licencia) {
        this.asignar_tipo_licencia = asignar_tipo_licencia;
    }

    public Long getAprobacion_decanatura() {
        return aprobacion_decanatura;
    }

    public void setAprobacion_decanatura(Long aprobacion_decanatura) {
        this.aprobacion_decanatura = aprobacion_decanatura;
    }

    public Long getNotificacion_tesoreria() {
        return notificacion_tesoreria;
    }

    public void setNotificacion_tesoreria(Long notificacion_tesoreria) {
        this.notificacion_tesoreria = notificacion_tesoreria;
    }

    public List<Comeval_Licencia_Docente_Plaza> getLst_plazas() {
        return lst_plazas;
    }

    public void setLst_plazas(List<Comeval_Licencia_Docente_Plaza> lst_plazas) {
        this.lst_plazas = lst_plazas;
    }

    @Override
    public String toString() {
        return "Comeval_Licencia_Docente{" + "id_comeval_licencia_docente=" + id_comeval_licencia_docente + ", personal=" + personal + ", id_motivo_licencia=" + id_motivo_licencia + ", id_tipo_licencia=" + id_tipo_licencia + ", goce_sueldo=" + goce_sueldo + ", fecha_inicio_licencia=" + fecha_inicio_licencia + ", fecha_final_licencia=" + fecha_final_licencia + ", descripcion_solicitud=" + descripcion_solicitud + ", id_solicitud_acta=" + id_solicitud_acta + ", no_acta=" + no_acta + ", anio_acta=" + anio_acta + ", punto_acta=" + punto_acta + ", inciso_acta=" + inciso_acta + ", fecha_acta=" + fecha_acta + ", resolucion_acta=" + resolucion_acta + ", fecha_ingreso=" + fecha_ingreso + ", id_estado_solicitud=" + id_estado_solicitud + ", id_tipo_solicitud=" + id_tipo_solicitud + ", rechazado=" + rechazado + ", visto_bueno_director=" + visto_bueno_director + ", ingreso_siif_traslado=" + ingreso_siif_traslado + ", confirmar_traslado=" + confirmar_traslado + ", asignar_tipo_licencia=" + asignar_tipo_licencia + ", aprobacion_decanatura=" + aprobacion_decanatura + ", notificacion_tesoreria=" + notificacion_tesoreria + ", lst_plazas=" + lst_plazas + '}';
    }
    
}
