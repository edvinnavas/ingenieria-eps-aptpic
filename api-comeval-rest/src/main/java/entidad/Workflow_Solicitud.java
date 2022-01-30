package entidad;

import java.io.Serializable;

public class Workflow_Solicitud implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id_workflow_solicitud;
    private Long tipo_solicitud;
    private String nombre_tipo_solicitud;
    private Long estado_solicitud;
    private String nombre_estado_solicitud;
    private String estado_workflow_solicitud;
    private Long fecha_estado_workflow_solicitud;
    private Long rechazado;

    public Workflow_Solicitud(Long id_workflow_solicitud, Long tipo_solicitud, String nombre_tipo_solicitud, Long estado_solicitud, String nombre_estado_solicitud, String estado_workflow_solicitud, Long fecha_estado_workflow_solicitud, Long rechazado) {
        this.id_workflow_solicitud = id_workflow_solicitud;
        this.tipo_solicitud = tipo_solicitud;
        this.nombre_tipo_solicitud = nombre_tipo_solicitud;
        this.estado_solicitud = estado_solicitud;
        this.nombre_estado_solicitud = nombre_estado_solicitud;
        this.estado_workflow_solicitud = estado_workflow_solicitud;
        this.fecha_estado_workflow_solicitud = fecha_estado_workflow_solicitud;
        this.rechazado = rechazado;
    }

    public Workflow_Solicitud() {
    }

    public Long getId_workflow_solicitud() {
        return id_workflow_solicitud;
    }

    public void setId_workflow_solicitud(Long id_workflow_solicitud) {
        this.id_workflow_solicitud = id_workflow_solicitud;
    }

    public Long getTipo_solicitud() {
        return tipo_solicitud;
    }

    public void setTipo_solicitud(Long tipo_solicitud) {
        this.tipo_solicitud = tipo_solicitud;
    }

    public String getNombre_tipo_solicitud() {
        return nombre_tipo_solicitud;
    }

    public void setNombre_tipo_solicitud(String nombre_tipo_solicitud) {
        this.nombre_tipo_solicitud = nombre_tipo_solicitud;
    }

    public Long getEstado_solicitud() {
        return estado_solicitud;
    }

    public void setEstado_solicitud(Long estado_solicitud) {
        this.estado_solicitud = estado_solicitud;
    }

    public String getNombre_estado_solicitud() {
        return nombre_estado_solicitud;
    }

    public void setNombre_estado_solicitud(String nombre_estado_solicitud) {
        this.nombre_estado_solicitud = nombre_estado_solicitud;
    }

    public String getEstado_workflow_solicitud() {
        return estado_workflow_solicitud;
    }

    public void setEstado_workflow_solicitud(String estado_workflow_solicitud) {
        this.estado_workflow_solicitud = estado_workflow_solicitud;
    }

    public Long getFecha_estado_workflow_solicitud() {
        return fecha_estado_workflow_solicitud;
    }

    public void setFecha_estado_workflow_solicitud(Long fecha_estado_workflow_solicitud) {
        this.fecha_estado_workflow_solicitud = fecha_estado_workflow_solicitud;
    }

    public Long getRechazado() {
        return rechazado;
    }

    public void setRechazado(Long rechazado) {
        this.rechazado = rechazado;
    }

    @Override
    public String toString() {
        return "Workflow_Solicitud{" + "id_workflow_solicitud=" + id_workflow_solicitud + ", tipo_solicitud=" + tipo_solicitud + ", nombre_tipo_solicitud=" + nombre_tipo_solicitud + ", estado_solicitud=" + estado_solicitud + ", nombre_estado_solicitud=" + nombre_estado_solicitud + ", estado_workflow_solicitud=" + estado_workflow_solicitud + ", fecha_estado_workflow_solicitud=" + fecha_estado_workflow_solicitud + ", rechazado=" + rechazado + '}';
    }

}
