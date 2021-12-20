package beans.Comeval_Carga_Eval_Comeval;

import java.io.Serializable;
import java.util.Date;

public class Tbl_Comeval_Carga_Eval_Comeval implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id_comeval_carga_eval_comeval;
    private Date fecha_ingreso;
    private String estado_solicitud;
    private String tipo_solicitud;

    public Tbl_Comeval_Carga_Eval_Comeval(Long id_comeval_carga_eval_comeval, Date fecha_ingreso, String estado_solicitud, String tipo_solicitud) {
        this.id_comeval_carga_eval_comeval = id_comeval_carga_eval_comeval;
        this.fecha_ingreso = fecha_ingreso;
        this.estado_solicitud = estado_solicitud;
        this.tipo_solicitud = tipo_solicitud;
    }

    public Tbl_Comeval_Carga_Eval_Comeval() {
    }

    public Long getId_comeval_carga_eval_comeval() {
        return id_comeval_carga_eval_comeval;
    }

    public void setId_comeval_carga_eval_comeval(Long id_comeval_carga_eval_comeval) {
        this.id_comeval_carga_eval_comeval = id_comeval_carga_eval_comeval;
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
        return "Tbl_Comeval_Carga_Eval_Comeval{" + "id_comeval_carga_eval_comeval=" + id_comeval_carga_eval_comeval + ", fecha_ingreso=" + fecha_ingreso + ", estado_solicitud=" + estado_solicitud + ", tipo_solicitud=" + tipo_solicitud + '}';
    }
    
}
