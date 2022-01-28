package beans.Comeval_Notas_Evaluacion_Docente;

import java.io.Serializable;
import java.util.Date;

public class Tbl_Comeval_Notas_Evaluacion_Docente implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id_comeval_notas_evaluacion_docente;
    private Date fecha_ingreso;
    private String estado_solicitud;
    private String tipo_solicitud;

    public Tbl_Comeval_Notas_Evaluacion_Docente(Long id_comeval_notas_evaluacion_docente, Date fecha_ingreso, String estado_solicitud, String tipo_solicitud) {
        this.id_comeval_notas_evaluacion_docente = id_comeval_notas_evaluacion_docente;
        this.fecha_ingreso = fecha_ingreso;
        this.estado_solicitud = estado_solicitud;
        this.tipo_solicitud = tipo_solicitud;
    }

    public Tbl_Comeval_Notas_Evaluacion_Docente() {
    }

    public Long getId_comeval_notas_evaluacion_docente() {
        return id_comeval_notas_evaluacion_docente;
    }

    public void setId_comeval_notas_evaluacion_docente(Long id_comeval_notas_evaluacion_docente) {
        this.id_comeval_notas_evaluacion_docente = id_comeval_notas_evaluacion_docente;
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
        return "Tbl_Comeval_Notas_Evaluacion_Docente{" + "id_comeval_notas_evaluacion_docente=" + id_comeval_notas_evaluacion_docente + ", fecha_ingreso=" + fecha_ingreso + ", estado_solicitud=" + estado_solicitud + ", tipo_solicitud=" + tipo_solicitud + '}';
    }
    
}
