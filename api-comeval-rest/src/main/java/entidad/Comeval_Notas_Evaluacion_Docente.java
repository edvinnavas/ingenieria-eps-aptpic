package entidad;

import java.io.Serializable;
import java.util.List;

public class Comeval_Notas_Evaluacion_Docente implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Long id_comeval_notas_evaluacion_docente;
    private String nota_ref;
    private String fecha_nota_ref;
    private Long anio_notas;
    private String path_archivo;
    private String usuario;
    private String fecha_ingreso;
    private Long id_estado_solicitud;
    private Long id_tipo_solicitud;
    private Long rechazado;
    private Long id_estado_solicitud_rechazado;
    private Long id_tipo_solicitud_rechazado;
    private Comeval_Acta_Solicitud comeval_acta_solicitud;
    private List<Comeval_Solicitud_Observacion> lst_observaciones;

    public Comeval_Notas_Evaluacion_Docente(Long id_comeval_notas_evaluacion_docente, String nota_ref, String fecha_nota_ref, Long anio_notas, String path_archivo, String usuario, String fecha_ingreso, Long id_estado_solicitud, Long id_tipo_solicitud, Long rechazado, Long id_estado_solicitud_rechazado, Long id_tipo_solicitud_rechazado, Comeval_Acta_Solicitud comeval_acta_solicitud, List<Comeval_Solicitud_Observacion> lst_observaciones) {
        this.id_comeval_notas_evaluacion_docente = id_comeval_notas_evaluacion_docente;
        this.nota_ref = nota_ref;
        this.fecha_nota_ref = fecha_nota_ref;
        this.anio_notas = anio_notas;
        this.path_archivo = path_archivo;
        this.usuario = usuario;
        this.fecha_ingreso = fecha_ingreso;
        this.id_estado_solicitud = id_estado_solicitud;
        this.id_tipo_solicitud = id_tipo_solicitud;
        this.rechazado = rechazado;
        this.id_estado_solicitud_rechazado = id_estado_solicitud_rechazado;
        this.id_tipo_solicitud_rechazado = id_tipo_solicitud_rechazado;
        this.comeval_acta_solicitud = comeval_acta_solicitud;
        this.lst_observaciones = lst_observaciones;
    }

    public Comeval_Notas_Evaluacion_Docente() {
    }

    public Long getId_comeval_notas_evaluacion_docente() {
        return id_comeval_notas_evaluacion_docente;
    }

    public void setId_comeval_notas_evaluacion_docente(Long id_comeval_notas_evaluacion_docente) {
        this.id_comeval_notas_evaluacion_docente = id_comeval_notas_evaluacion_docente;
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

    public Long getAnio_notas() {
        return anio_notas;
    }

    public void setAnio_notas(Long anio_notas) {
        this.anio_notas = anio_notas;
    }

    public String getPath_archivo() {
        return path_archivo;
    }

    public void setPath_archivo(String path_archivo) {
        this.path_archivo = path_archivo;
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
        return "Comeval_Notas_Evaluacion_Docente{" + "id_comeval_notas_evaluacion_docente=" + id_comeval_notas_evaluacion_docente + ", nota_ref=" + nota_ref + ", fecha_nota_ref=" + fecha_nota_ref + ", anio_notas=" + anio_notas + ", path_archivo=" + path_archivo + ", usuario=" + usuario + ", fecha_ingreso=" + fecha_ingreso + ", id_estado_solicitud=" + id_estado_solicitud + ", id_tipo_solicitud=" + id_tipo_solicitud + ", rechazado=" + rechazado + ", id_estado_solicitud_rechazado=" + id_estado_solicitud_rechazado + ", id_tipo_solicitud_rechazado=" + id_tipo_solicitud_rechazado + ", comeval_acta_solicitud=" + comeval_acta_solicitud + ", lst_observaciones=" + lst_observaciones + '}';
    }
    
}
