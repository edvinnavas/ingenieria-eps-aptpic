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
    private String usuario;
    private String fecha_ingreso;
    private Long id_estado_solicitud;
    private Long id_tipo_solicitud;
    private Long rechazado;
    private Long id_estado_solicitud_rechazado;
    private Long id_tipo_solicitud_rechazado;
    private Long ingreso_siif_visto_bueno_escuela;
    private Long tipo_licencia_secretario_academico;
    private Long acuerdo_decanatura;
    private Long notificacion_tesoreria;
    private List<Comeval_Licencia_Docente_Plaza> lst_plazas;
    private Comeval_Acta_Solicitud comeval_acta_solicitud;
    private List<Comeval_Solicitud_Observacion> lst_observaciones;

    public Comeval_Licencia_Docente(Long id_comeval_licencia_docente, String personal, Long id_motivo_licencia, Long id_tipo_licencia, String goce_sueldo, String fecha_inicio_licencia, String fecha_final_licencia, String usuario, String fecha_ingreso, Long id_estado_solicitud, Long id_tipo_solicitud, Long rechazado, Long id_estado_solicitud_rechazado, Long id_tipo_solicitud_rechazado, Long ingreso_siif_visto_bueno_escuela, Long tipo_licencia_secretario_academico, Long acuerdo_decanatura, Long notificacion_tesoreria, List<Comeval_Licencia_Docente_Plaza> lst_plazas, Comeval_Acta_Solicitud comeval_acta_solicitud, List<Comeval_Solicitud_Observacion> lst_observaciones) {
        this.id_comeval_licencia_docente = id_comeval_licencia_docente;
        this.personal = personal;
        this.id_motivo_licencia = id_motivo_licencia;
        this.id_tipo_licencia = id_tipo_licencia;
        this.goce_sueldo = goce_sueldo;
        this.fecha_inicio_licencia = fecha_inicio_licencia;
        this.fecha_final_licencia = fecha_final_licencia;
        this.usuario = usuario;
        this.fecha_ingreso = fecha_ingreso;
        this.id_estado_solicitud = id_estado_solicitud;
        this.id_tipo_solicitud = id_tipo_solicitud;
        this.rechazado = rechazado;
        this.id_estado_solicitud_rechazado = id_estado_solicitud_rechazado;
        this.id_tipo_solicitud_rechazado = id_tipo_solicitud_rechazado;
        this.ingreso_siif_visto_bueno_escuela = ingreso_siif_visto_bueno_escuela;
        this.tipo_licencia_secretario_academico = tipo_licencia_secretario_academico;
        this.acuerdo_decanatura = acuerdo_decanatura;
        this.notificacion_tesoreria = notificacion_tesoreria;
        this.lst_plazas = lst_plazas;
        this.comeval_acta_solicitud = comeval_acta_solicitud;
        this.lst_observaciones = lst_observaciones;
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

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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

    public Long getId_estado_solicitud_rechazado() {
        return id_estado_solicitud_rechazado;
    }

    public void setId_estado_solicitud_rechazado(Long id_estado_solicitud_rechazado) {
        this.id_estado_solicitud_rechazado = id_estado_solicitud_rechazado;
    }

    public Long getId_tipo_solicitud_rechazado() {
        return id_tipo_solicitud_rechazado;
    }

    public void setId_tipo_solicitud_rechazado(Long id_tipo_solicitud_rechazado) {
        this.id_tipo_solicitud_rechazado = id_tipo_solicitud_rechazado;
    }

    public Long getIngreso_siif_visto_bueno_escuela() {
        return ingreso_siif_visto_bueno_escuela;
    }

    public void setIngreso_siif_visto_bueno_escuela(Long ingreso_siif_visto_bueno_escuela) {
        this.ingreso_siif_visto_bueno_escuela = ingreso_siif_visto_bueno_escuela;
    }

    public Long getTipo_licencia_secretario_academico() {
        return tipo_licencia_secretario_academico;
    }

    public void setTipo_licencia_secretario_academico(Long tipo_licencia_secretario_academico) {
        this.tipo_licencia_secretario_academico = tipo_licencia_secretario_academico;
    }

    public Long getAcuerdo_decanatura() {
        return acuerdo_decanatura;
    }

    public void setAcuerdo_decanatura(Long acuerdo_decanatura) {
        this.acuerdo_decanatura = acuerdo_decanatura;
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

    public Comeval_Acta_Solicitud getComeval_acta_solicitud() {
        return comeval_acta_solicitud;
    }

    public void setComeval_acta_solicitud(Comeval_Acta_Solicitud comeval_acta_solicitud) {
        this.comeval_acta_solicitud = comeval_acta_solicitud;
    }

    public List<Comeval_Solicitud_Observacion> getLst_observaciones() {
        return lst_observaciones;
    }

    public void setLst_observaciones(List<Comeval_Solicitud_Observacion> lst_observaciones) {
        this.lst_observaciones = lst_observaciones;
    }

    @Override
    public String toString() {
        return "Comeval_Licencia_Docente{" + "id_comeval_licencia_docente=" + id_comeval_licencia_docente + ", personal=" + personal + ", id_motivo_licencia=" + id_motivo_licencia + ", id_tipo_licencia=" + id_tipo_licencia + ", goce_sueldo=" + goce_sueldo + ", fecha_inicio_licencia=" + fecha_inicio_licencia + ", fecha_final_licencia=" + fecha_final_licencia + ", usuario=" + usuario + ", fecha_ingreso=" + fecha_ingreso + ", id_estado_solicitud=" + id_estado_solicitud + ", id_tipo_solicitud=" + id_tipo_solicitud + ", rechazado=" + rechazado + ", id_estado_solicitud_rechazado=" + id_estado_solicitud_rechazado + ", id_tipo_solicitud_rechazado=" + id_tipo_solicitud_rechazado + ", ingreso_siif_visto_bueno_escuela=" + ingreso_siif_visto_bueno_escuela + ", tipo_licencia_secretario_academico=" + tipo_licencia_secretario_academico + ", acuerdo_decanatura=" + acuerdo_decanatura + ", notificacion_tesoreria=" + notificacion_tesoreria + ", lst_plazas=" + lst_plazas + ", comeval_acta_solicitud=" + comeval_acta_solicitud + ", lst_observaciones=" + lst_observaciones + '}';
    }

}
