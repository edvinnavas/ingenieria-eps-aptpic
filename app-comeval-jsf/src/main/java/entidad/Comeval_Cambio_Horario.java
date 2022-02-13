package entidad;

import java.io.Serializable;
import java.util.List;

public class Comeval_Cambio_Horario implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id_comeval_cambio_horario;
    private String personal;
    private Long id_tipo_cambio_horario;
    private String nota_ref;
    private String fecha_nota_ref;
    private String usuario;
    private String fecha_ingreso;
    private Long id_estado_solicitud;
    private Long id_tipo_solicitud;
    private Long rechazado;
    private Long id_estado_solicitud_rechazado;
    private Long id_tipo_solicitud_rechazado;
    private Long visto_bueno_director_escuela;
    private Long visto_bueno_secretario_academico;
    private List<Comeval_Cambio_Horario_Plaza> lst_horario_plaza;
    private Comeval_Acta_Solicitud comeval_acta_solicitud;
    private List<Comeval_Solicitud_Observacion> lst_observaciones;

    public Comeval_Cambio_Horario(Long id_comeval_cambio_horario, String personal, Long id_tipo_cambio_horario, String nota_ref, String fecha_nota_ref, String usuario, String fecha_ingreso, Long id_estado_solicitud, Long id_tipo_solicitud, Long rechazado, Long id_estado_solicitud_rechazado, Long id_tipo_solicitud_rechazado, Long visto_bueno_director_escuela, Long visto_bueno_secretario_academico, List<Comeval_Cambio_Horario_Plaza> lst_horario_plaza, Comeval_Acta_Solicitud comeval_acta_solicitud, List<Comeval_Solicitud_Observacion> lst_observaciones) {
        this.id_comeval_cambio_horario = id_comeval_cambio_horario;
        this.personal = personal;
        this.id_tipo_cambio_horario = id_tipo_cambio_horario;
        this.nota_ref = nota_ref;
        this.fecha_nota_ref = fecha_nota_ref;
        this.usuario = usuario;
        this.fecha_ingreso = fecha_ingreso;
        this.id_estado_solicitud = id_estado_solicitud;
        this.id_tipo_solicitud = id_tipo_solicitud;
        this.rechazado = rechazado;
        this.id_estado_solicitud_rechazado = id_estado_solicitud_rechazado;
        this.id_tipo_solicitud_rechazado = id_tipo_solicitud_rechazado;
        this.visto_bueno_director_escuela = visto_bueno_director_escuela;
        this.visto_bueno_secretario_academico = visto_bueno_secretario_academico;
        this.lst_horario_plaza = lst_horario_plaza;
        this.comeval_acta_solicitud = comeval_acta_solicitud;
        this.lst_observaciones = lst_observaciones;
    }

    public Comeval_Cambio_Horario() {
    }

    public Long getId_comeval_cambio_horario() {
        return id_comeval_cambio_horario;
    }

    public void setId_comeval_cambio_horario(Long id_comeval_cambio_horario) {
        this.id_comeval_cambio_horario = id_comeval_cambio_horario;
    }

    public String getPersonal() {
        return personal;
    }

    public void setPersonal(String personal) {
        this.personal = personal;
    }

    public Long getId_tipo_cambio_horario() {
        return id_tipo_cambio_horario;
    }

    public void setId_tipo_cambio_horario(Long id_tipo_cambio_horario) {
        this.id_tipo_cambio_horario = id_tipo_cambio_horario;
    }

    public String getNota_ref() {
        return nota_ref;
    }

    public void setNota_ref(String nota_ref) {
        this.nota_ref = nota_ref;
    }

    public String getFecha_nota_ref() {
        return fecha_nota_ref;
    }

    public void setFecha_nota_ref(String fecha_nota_ref) {
        this.fecha_nota_ref = fecha_nota_ref;
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

    public Long getVisto_bueno_director_escuela() {
        return visto_bueno_director_escuela;
    }

    public void setVisto_bueno_director_escuela(Long visto_bueno_director_escuela) {
        this.visto_bueno_director_escuela = visto_bueno_director_escuela;
    }

    public Long getVisto_bueno_secretario_academico() {
        return visto_bueno_secretario_academico;
    }

    public void setVisto_bueno_secretario_academico(Long visto_bueno_secretario_academico) {
        this.visto_bueno_secretario_academico = visto_bueno_secretario_academico;
    }

    public List<Comeval_Cambio_Horario_Plaza> getLst_horario_plaza() {
        return lst_horario_plaza;
    }

    public void setLst_horario_plaza(List<Comeval_Cambio_Horario_Plaza> lst_horario_plaza) {
        this.lst_horario_plaza = lst_horario_plaza;
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
        return "Comeval_Cambio_Horario{" + "id_comeval_cambio_horario=" + id_comeval_cambio_horario + ", personal=" + personal + ", id_tipo_cambio_horario=" + id_tipo_cambio_horario + ", nota_ref=" + nota_ref + ", fecha_nota_ref=" + fecha_nota_ref + ", usuario=" + usuario + ", fecha_ingreso=" + fecha_ingreso + ", id_estado_solicitud=" + id_estado_solicitud + ", id_tipo_solicitud=" + id_tipo_solicitud + ", rechazado=" + rechazado + ", id_estado_solicitud_rechazado=" + id_estado_solicitud_rechazado + ", id_tipo_solicitud_rechazado=" + id_tipo_solicitud_rechazado + ", visto_bueno_director_escuela=" + visto_bueno_director_escuela + ", visto_bueno_secretario_academico=" + visto_bueno_secretario_academico + ", lst_horario_plaza=" + lst_horario_plaza + ", comeval_acta_solicitud=" + comeval_acta_solicitud + ", lst_observaciones=" + lst_observaciones + '}';
    }

}
