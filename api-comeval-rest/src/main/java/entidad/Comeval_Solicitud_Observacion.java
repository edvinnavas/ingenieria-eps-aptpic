package entidad;

import java.io.Serializable;

public class Comeval_Solicitud_Observacion implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id_solicitud;
    private Long id_tipo_solicitud;
    private Long id_observacion;
    private String dependencia;
    private String fecha_hora;
    private String observacion;

    public Comeval_Solicitud_Observacion(Long id_solicitud, Long id_tipo_solicitud, Long id_observacion, String dependencia, String fecha_hora, String observacion) {
        this.id_solicitud = id_solicitud;
        this.id_tipo_solicitud = id_tipo_solicitud;
        this.id_observacion = id_observacion;
        this.dependencia = dependencia;
        this.fecha_hora = fecha_hora;
        this.observacion = observacion;
    }

    public Comeval_Solicitud_Observacion() {
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

    public Long getId_observacion() {
        return id_observacion;
    }

    public void setId_observacion(Long id_observacion) {
        this.id_observacion = id_observacion;
    }

    public String getDependencia() {
        return dependencia;
    }

    public void setDependencia(String dependencia) {
        this.dependencia = dependencia;
    }

    public String getFecha_hora() {
        return fecha_hora;
    }

    public void setFecha_hora(String fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    @Override
    public String toString() {
        return "Comeval_Solicitud_Observacion{" + "id_solicitud=" + id_solicitud + ", id_tipo_solicitud=" + id_tipo_solicitud + ", id_observacion=" + id_observacion + ", dependencia=" + dependencia + ", fecha_hora=" + fecha_hora + ", observacion=" + observacion + '}';
    }

}
