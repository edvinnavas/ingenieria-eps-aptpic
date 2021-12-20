package beans.Comeval_Cambio_Horario;

import java.io.Serializable;
import java.util.Date;

public class Tbl_Comeval_Cambio_Horario implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id_comeval_cambio_horario;
    private String id_docente;
    private Date fecha_ingreso;
    private String estado_solicitud;
    private String tipo_solicitud;

    public Tbl_Comeval_Cambio_Horario(Long id_comeval_cambio_horario, String id_docente, Date fecha_ingreso, String estado_solicitud, String tipo_solicitud) {
        this.id_comeval_cambio_horario = id_comeval_cambio_horario;
        this.id_docente = id_docente;
        this.fecha_ingreso = fecha_ingreso;
        this.estado_solicitud = estado_solicitud;
        this.tipo_solicitud = tipo_solicitud;
    }

    public Tbl_Comeval_Cambio_Horario() {
    }

    public Long getId_comeval_cambio_horario() {
        return id_comeval_cambio_horario;
    }

    public void setId_comeval_cambio_horario(Long id_comeval_cambio_horario) {
        this.id_comeval_cambio_horario = id_comeval_cambio_horario;
    }

    public String getId_docente() {
        return id_docente;
    }

    public void setId_docente(String id_docente) {
        this.id_docente = id_docente;
    }

    public Date getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(Date fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public String getEstado_solicitud() {
        return estado_solicitud;
    }

    public void setEstado_solicitud(String estado_solicitud) {
        this.estado_solicitud = estado_solicitud;
    }

    public String getTipo_solicitud() {
        return tipo_solicitud;
    }

    public void setTipo_solicitud(String tipo_solicitud) {
        this.tipo_solicitud = tipo_solicitud;
    }

    @Override
    public String toString() {
        return "Tbl_Comeval_Cambio_Horario{" + "id_comeval_cambio_horario=" + id_comeval_cambio_horario + ", id_docente=" + id_docente + ", fecha_ingreso=" + fecha_ingreso + ", estado_solicitud=" + estado_solicitud + ", tipo_solicitud=" + tipo_solicitud + '}';
    }
    
}
