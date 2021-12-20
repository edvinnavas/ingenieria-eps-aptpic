package entidad;

import java.io.Serializable;

public class Comeval_Acta_Solicitud implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Long id_solicitud;
    private Long id_tipo_solicitud;
    private Long id_solicitud_acta;
    private String no_acta;
    private String anio_acta;
    private String punto_acta;
    private String inciso_acta;
    private String fecha_acta;
    private String resolucion_acta;

    public Comeval_Acta_Solicitud(Long id_solicitud, Long id_tipo_solicitud, Long id_solicitud_acta, String no_acta, String anio_acta, String punto_acta, String inciso_acta, String fecha_acta, String resolucion_acta) {
        this.id_solicitud = id_solicitud;
        this.id_tipo_solicitud = id_tipo_solicitud;
        this.id_solicitud_acta = id_solicitud_acta;
        this.no_acta = no_acta;
        this.anio_acta = anio_acta;
        this.punto_acta = punto_acta;
        this.inciso_acta = inciso_acta;
        this.fecha_acta = fecha_acta;
        this.resolucion_acta = resolucion_acta;
    }

    public Comeval_Acta_Solicitud() {
    }

    public Long getId_solicitud() {
        return id_solicitud;
    }

    public void setId_solicitud(Long id_solicitud) {
        this.id_solicitud = id_solicitud;
    }

    public Long getId_tipo_solicitud() {
        return id_tipo_solicitud;
    }

    public void setId_tipo_solicitud(Long id_tipo_solicitud) {
        this.id_tipo_solicitud = id_tipo_solicitud;
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

    @Override
    public String toString() {
        return "Comeval_Acta_Solicitud{" + "id_solicitud=" + id_solicitud + ", id_tipo_solicitud=" + id_tipo_solicitud + ", id_solicitud_acta=" + id_solicitud_acta + ", no_acta=" + no_acta + ", anio_acta=" + anio_acta + ", punto_acta=" + punto_acta + ", inciso_acta=" + inciso_acta + ", fecha_acta=" + fecha_acta + ", resolucion_acta=" + resolucion_acta + '}';
    }
    
}
