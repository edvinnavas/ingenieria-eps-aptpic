package entidad;

import java.io.Serializable;

public class Comeval_Carga_Eval_Comeval implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private Long id_comeval_carga_eval_comeval;
    private String descripcion_solicitud;
    private String path_archivo;
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

    public Comeval_Carga_Eval_Comeval(Long id_comeval_carga_eval_comeval, String descripcion_solicitud, String path_archivo, Long id_solicitud_acta, String no_acta, String anio_acta, String punto_acta, String inciso_acta, String fecha_acta, String resolucion_acta, String fecha_ingreso, Long id_estado_solicitud, Long id_tipo_solicitud, Long rechazado) {
        this.id_comeval_carga_eval_comeval = id_comeval_carga_eval_comeval;
        this.descripcion_solicitud = descripcion_solicitud;
        this.path_archivo = path_archivo;
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
    }

    public Comeval_Carga_Eval_Comeval() {
    }

    public Long getId_comeval_carga_eval_comeval() {
        return id_comeval_carga_eval_comeval;
    }

    public void setId_comeval_carga_eval_comeval(Long id_comeval_carga_eval_comeval) {
        this.id_comeval_carga_eval_comeval = id_comeval_carga_eval_comeval;
    }

    public String getDescripcion_solicitud() {
        return descripcion_solicitud;
    }

    public void setDescripcion_solicitud(String descripcion_solicitud) {
        this.descripcion_solicitud = descripcion_solicitud;
    }

    public String getPath_archivo() {
        return path_archivo;
    }

    public void setPath_archivo(String path_archivo) {
        this.path_archivo = path_archivo;
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

    @Override
    public String toString() {
        return "Comeval_Carga_Eval_Comeval{" + "id_comeval_carga_eval_comeval=" + id_comeval_carga_eval_comeval + ", descripcion_solicitud=" + descripcion_solicitud + ", path_archivo=" + path_archivo + ", id_solicitud_acta=" + id_solicitud_acta + ", no_acta=" + no_acta + ", anio_acta=" + anio_acta + ", punto_acta=" + punto_acta + ", inciso_acta=" + inciso_acta + ", fecha_acta=" + fecha_acta + ", resolucion_acta=" + resolucion_acta + ", fecha_ingreso=" + fecha_ingreso + ", id_estado_solicitud=" + id_estado_solicitud + ", id_tipo_solicitud=" + id_tipo_solicitud + ", rechazado=" + rechazado + '}';
    }
    
}
