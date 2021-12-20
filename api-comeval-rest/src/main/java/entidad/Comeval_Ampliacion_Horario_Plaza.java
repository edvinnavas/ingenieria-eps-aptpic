package entidad;

import java.io.Serializable;

public class Comeval_Ampliacion_Horario_Plaza implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Long id_comeval_ampliacion_horario;
    private Long id_horario;
    private String horainicio;
    private String horafin;
    private String dias;

    public Comeval_Ampliacion_Horario_Plaza(Long id_comeval_ampliacion_horario, Long id_horario, String horainicio, String horafin, String dias) {
        this.id_comeval_ampliacion_horario = id_comeval_ampliacion_horario;
        this.id_horario = id_horario;
        this.horainicio = horainicio;
        this.horafin = horafin;
        this.dias = dias;
    }

    public Comeval_Ampliacion_Horario_Plaza() {
    }

    public Long getId_comeval_ampliacion_horario() {
        return id_comeval_ampliacion_horario;
    }

    public void setId_comeval_ampliacion_horario(Long id_comeval_ampliacion_horario) {
        this.id_comeval_ampliacion_horario = id_comeval_ampliacion_horario;
    }

    public Long getId_horario() {
        return id_horario;
    }

    public void setId_horario(Long id_horario) {
        this.id_horario = id_horario;
    }

    public String getHorainicio() {
        return horainicio;
    }

    public void setHorainicio(String horainicio) {
        this.horainicio = horainicio;
    }

    public String getHorafin() {
        return horafin;
    }

    public void setHorafin(String horafin) {
        this.horafin = horafin;
    }

    public String getDias() {
        return dias;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }

    @Override
    public String toString() {
        return "Comeval_Ampliacion_Horario_Plaza{" + "id_comeval_ampliacion_horario=" + id_comeval_ampliacion_horario + ", id_horario=" + id_horario + ", horainicio=" + horainicio + ", horafin=" + horafin + ", dias=" + dias + '}';
    }
    
}
