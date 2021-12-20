package entidad;

import java.io.Serializable;
import java.util.List;

public class Comeval_Ampliacion_Horario implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Long id_comeval_ampliacion_horario;
    private String personal;
    private Long id_plaza_temporal;
    private Long id_plaza_indefinido;
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
    private Long visto_bueno_secretario_academico;
    private Long notificacion_tesoreria;
    private List<Comeval_Ampliacion_Horario_Plaza> lst_horario_plaza;

    public Comeval_Ampliacion_Horario(Long id_comeval_ampliacion_horario, String personal, Long id_plaza_temporal, Long id_plaza_indefinido, String descripcion_solicitud, Long id_solicitud_acta, String no_acta, String anio_acta, String punto_acta, String inciso_acta, String fecha_acta, String resolucion_acta, String fecha_ingreso, Long id_estado_solicitud, Long id_tipo_solicitud, Long rechazado, Long visto_bueno_director, Long visto_bueno_secretario_academico, Long notificacion_tesoreria, List<Comeval_Ampliacion_Horario_Plaza> lst_horario_plaza) {
        this.id_comeval_ampliacion_horario = id_comeval_ampliacion_horario;
        this.personal = personal;
        this.id_plaza_temporal = id_plaza_temporal;
        this.id_plaza_indefinido = id_plaza_indefinido;
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
        this.visto_bueno_secretario_academico = visto_bueno_secretario_academico;
        this.notificacion_tesoreria = notificacion_tesoreria;
        this.lst_horario_plaza = lst_horario_plaza;
    }

    public Comeval_Ampliacion_Horario() {
    }

    public Long getId_comeval_ampliacion_horario() {
        return id_comeval_ampliacion_horario;
    }

    public void setId_comeval_ampliacion_horario(Long id_comeval_ampliacion_horario) {
        this.id_comeval_ampliacion_horario = id_comeval_ampliacion_horario;
    }

    public String getPersonal() {
        return personal;
    }

    public void setPersonal(String personal) {
        this.personal = personal;
    }

    public Long getId_plaza_temporal() {
        return id_plaza_temporal;
    }

    public void setId_plaza_temporal(Long id_plaza_temporal) {
        this.id_plaza_temporal = id_plaza_temporal;
    }

    public Long getId_plaza_indefinido() {
        return id_plaza_indefinido;
    }

    public void setId_plaza_indefinido(Long id_plaza_indefinido) {
        this.id_plaza_indefinido = id_plaza_indefinido;
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

    public Long getVisto_bueno_secretario_academico() {
        return visto_bueno_secretario_academico;
    }

    public void setVisto_bueno_secretario_academico(Long visto_bueno_secretario_academico) {
        this.visto_bueno_secretario_academico = visto_bueno_secretario_academico;
    }

    public Long getNotificacion_tesoreria() {
        return notificacion_tesoreria;
    }

    public void setNotificacion_tesoreria(Long notificacion_tesoreria) {
        this.notificacion_tesoreria = notificacion_tesoreria;
    }

    public List<Comeval_Ampliacion_Horario_Plaza> getLst_horario_plaza() {
        return lst_horario_plaza;
    }

    public void setLst_horario_plaza(List<Comeval_Ampliacion_Horario_Plaza> lst_horario_plaza) {
        this.lst_horario_plaza = lst_horario_plaza;
    }

    @Override
    public String toString() {
        return "Comeval_Ampliacion_Horario{" + "id_comeval_ampliacion_horario=" + id_comeval_ampliacion_horario + ", personal=" + personal + ", id_plaza_temporal=" + id_plaza_temporal + ", id_plaza_indefinido=" + id_plaza_indefinido + ", descripcion_solicitud=" + descripcion_solicitud + ", id_solicitud_acta=" + id_solicitud_acta + ", no_acta=" + no_acta + ", anio_acta=" + anio_acta + ", punto_acta=" + punto_acta + ", inciso_acta=" + inciso_acta + ", fecha_acta=" + fecha_acta + ", resolucion_acta=" + resolucion_acta + ", fecha_ingreso=" + fecha_ingreso + ", id_estado_solicitud=" + id_estado_solicitud + ", id_tipo_solicitud=" + id_tipo_solicitud + ", rechazado=" + rechazado + ", visto_bueno_director=" + visto_bueno_director + ", visto_bueno_secretario_academico=" + visto_bueno_secretario_academico + ", notificacion_tesoreria=" + notificacion_tesoreria + ", lst_horario_plaza=" + lst_horario_plaza + '}';
    }
    
}
