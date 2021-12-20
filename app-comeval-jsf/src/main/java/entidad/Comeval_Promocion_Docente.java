package entidad;

import java.io.Serializable;
import java.util.List;

public class Comeval_Promocion_Docente implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Long id_comeval_promocion_docente;
    private String personal;
    private Long tipoascenso;
    private String puesto;
    private String fecha_promueve;
    private String nota_ref_comeval;
    private String fecha_nota_ref_comeval;
    private String usuario;
    private String fecha_ingreso;
    private Long id_estado_solicitud;
    private Long id_tipo_solicitud;
    private Long rechazado;
    private Long id_estado_solicitud_rechazado;
    private Long id_tipo_solicitud_rechazado;
    private Long revision_comeval;
    private Long revision_secretario_academico;
    private List<Comeval_Promocion_Docente_Archivos> lst_archivos;
    private Comeval_Acta_Solicitud comeval_acta_solicitud;
    private List<Comeval_Solicitud_Observacion> lst_observaciones;

    public Comeval_Promocion_Docente(Long id_comeval_promocion_docente, String personal, Long tipoascenso, String puesto, String fecha_promueve, String nota_ref_comeval, String fecha_nota_ref_comeval, String usuario, String fecha_ingreso, Long id_estado_solicitud, Long id_tipo_solicitud, Long rechazado, Long id_estado_solicitud_rechazado, Long id_tipo_solicitud_rechazado, Long revision_comeval, Long revision_secretario_academico, List<Comeval_Promocion_Docente_Archivos> lst_archivos, Comeval_Acta_Solicitud comeval_acta_solicitud, List<Comeval_Solicitud_Observacion> lst_observaciones) {
        this.id_comeval_promocion_docente = id_comeval_promocion_docente;
        this.personal = personal;
        this.tipoascenso = tipoascenso;
        this.puesto = puesto;
        this.fecha_promueve = fecha_promueve;
        this.nota_ref_comeval = nota_ref_comeval;
        this.fecha_nota_ref_comeval = fecha_nota_ref_comeval;
        this.usuario = usuario;
        this.fecha_ingreso = fecha_ingreso;
        this.id_estado_solicitud = id_estado_solicitud;
        this.id_tipo_solicitud = id_tipo_solicitud;
        this.rechazado = rechazado;
        this.id_estado_solicitud_rechazado = id_estado_solicitud_rechazado;
        this.id_tipo_solicitud_rechazado = id_tipo_solicitud_rechazado;
        this.revision_comeval = revision_comeval;
        this.revision_secretario_academico = revision_secretario_academico;
        this.lst_archivos = lst_archivos;
        this.comeval_acta_solicitud = comeval_acta_solicitud;
        this.lst_observaciones = lst_observaciones;
    }

    public Comeval_Promocion_Docente() {
    }

    public Long getId_comeval_promocion_docente() {
        return id_comeval_promocion_docente;
    }

    public void setId_comeval_promocion_docente(Long id_comeval_promocion_docente) {
        this.id_comeval_promocion_docente = id_comeval_promocion_docente;
    }

    public String getPersonal() {
        return personal;
    }

    public void setPersonal(String personal) {
        this.personal = personal;
    }

    public Long getTipoascenso() {
        return tipoascenso;
    }

    public void setTipoascenso(Long tipoascenso) {
        this.tipoascenso = tipoascenso;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getFecha_promueve() {
        return fecha_promueve;
    }

    public void setFecha_promueve(String fecha_promueve) {
        this.fecha_promueve = fecha_promueve;
    }

    public String getNota_ref_comeval() {
        return nota_ref_comeval;
    }

    public void setNota_ref_comeval(String nota_ref_comeval) {
        this.nota_ref_comeval = nota_ref_comeval;
    }

    public String getFecha_nota_ref_comeval() {
        return fecha_nota_ref_comeval;
    }

    public void setFecha_nota_ref_comeval(String fecha_nota_ref_comeval) {
        this.fecha_nota_ref_comeval = fecha_nota_ref_comeval;
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

    public Long getRevision_comeval() {
        return revision_comeval;
    }

    public void setRevision_comeval(Long revision_comeval) {
        this.revision_comeval = revision_comeval;
    }

    public Long getRevision_secretario_academico() {
        return revision_secretario_academico;
    }

    public void setRevision_secretario_academico(Long revision_secretario_academico) {
        this.revision_secretario_academico = revision_secretario_academico;
    }

    public List<Comeval_Promocion_Docente_Archivos> getLst_archivos() {
        return lst_archivos;
    }

    public void setLst_archivos(List<Comeval_Promocion_Docente_Archivos> lst_archivos) {
        this.lst_archivos = lst_archivos;
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
        return "Comeval_Promocion_Docente{" + "id_comeval_promocion_docente=" + id_comeval_promocion_docente + ", personal=" + personal + ", tipoascenso=" + tipoascenso + ", puesto=" + puesto + ", fecha_promueve=" + fecha_promueve + ", nota_ref_comeval=" + nota_ref_comeval + ", fecha_nota_ref_comeval=" + fecha_nota_ref_comeval + ", usuario=" + usuario + ", fecha_ingreso=" + fecha_ingreso + ", id_estado_solicitud=" + id_estado_solicitud + ", id_tipo_solicitud=" + id_tipo_solicitud + ", rechazado=" + rechazado + ", id_estado_solicitud_rechazado=" + id_estado_solicitud_rechazado + ", id_tipo_solicitud_rechazado=" + id_tipo_solicitud_rechazado + ", revision_comeval=" + revision_comeval + ", revision_secretario_academico=" + revision_secretario_academico + ", lst_archivos=" + lst_archivos + ", comeval_acta_solicitud=" + comeval_acta_solicitud + ", lst_observaciones=" + lst_observaciones + '}';
    }
    
}
