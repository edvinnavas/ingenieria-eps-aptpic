package beans.Comeval_Licencia_Docente;

import java.io.Serializable;

public class lista_observaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id_observacion;
    private String dependencia;
    private String fecha_hora;
    private String observacion;

    public lista_observaciones(Long id_observacion, String dependencia, String fecha_hora, String observacion) {
        this.id_observacion = id_observacion;
        this.dependencia = dependencia;
        this.fecha_hora = fecha_hora;
        this.observacion = observacion;
    }

    public lista_observaciones() {
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
        return "lista_observaciones{" + "id_observacion=" + id_observacion + ", dependencia=" + dependencia + ", fecha_hora=" + fecha_hora + ", observacion=" + observacion + '}';
    }
    
}
