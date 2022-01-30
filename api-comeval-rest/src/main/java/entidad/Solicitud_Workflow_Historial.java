package entidad;

import java.io.Serializable;

public class Solicitud_Workflow_Historial implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id_solicitud;
    private Long id_estado_solicitud;
    private Long id_tipo_solicitud;
    private Long id_workflow;
    private String usuario;
    private String fecha;
    private Long rechazado;
    private String fecha_rechazado;

    public Solicitud_Workflow_Historial(Long id_solicitud, Long id_estado_solicitud, Long id_tipo_solicitud, Long id_workflow, String usuario, String fecha, Long rechazado, String fecha_rechazado) {
        this.id_solicitud = id_solicitud;
        this.id_estado_solicitud = id_estado_solicitud;
        this.id_tipo_solicitud = id_tipo_solicitud;
        this.id_workflow = id_workflow;
        this.usuario = usuario;
        this.fecha = fecha;
        this.rechazado = rechazado;
        this.fecha_rechazado = fecha_rechazado;
    }

    public Solicitud_Workflow_Historial() {
    }

    public Long getId_solicitud() {
        return id_solicitud;
    }

    public void setId_solicitud(Long id_solicitud) {
        this.id_solicitud = id_solicitud;
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

    public Long getId_workflow() {
        return id_workflow;
    }

    public void setId_workflow(Long id_workflow) {
        this.id_workflow = id_workflow;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Long getRechazado() {
        return rechazado;
    }

    public void setRechazado(Long rechazado) {
        this.rechazado = rechazado;
    }

    public String getFecha_rechazado() {
        return fecha_rechazado;
    }

    public void setFecha_rechazado(String fecha_rechazado) {
        this.fecha_rechazado = fecha_rechazado;
    }

    @Override
    public String toString() {
        return "Solicitud_Workflow_Historial{" + "id_solicitud=" + id_solicitud + ", id_estado_solicitud=" + id_estado_solicitud + ", id_tipo_solicitud=" + id_tipo_solicitud + ", id_workflow=" + id_workflow + ", usuario=" + usuario + ", fecha=" + fecha + ", rechazado=" + rechazado + ", fecha_rechazado=" + fecha_rechazado + '}';
    }

}
