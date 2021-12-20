package entidad;

import java.io.Serializable;

public class Comeval_Cambio_Horario_Plaza implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Long id_comeval_cambio_horario;
    private Long id_horario;
    private String horainicio;
    private String horafin;
    private String dias;
    private String personal;
    private Long plaza;
    private Long periodo;
    private Long anio;
    private String subpartida;
    private String renglon;

    public Comeval_Cambio_Horario_Plaza(Long id_comeval_cambio_horario, Long id_horario, String horainicio, String horafin, String dias, String personal, Long plaza, Long periodo, Long anio, String subpartida, String renglon) {
        this.id_comeval_cambio_horario = id_comeval_cambio_horario;
        this.id_horario = id_horario;
        this.horainicio = horainicio;
        this.horafin = horafin;
        this.dias = dias;
        this.personal = personal;
        this.plaza = plaza;
        this.periodo = periodo;
        this.anio = anio;
        this.subpartida = subpartida;
        this.renglon = renglon;
    }

    public Comeval_Cambio_Horario_Plaza() {
    }

    public Long getId_comeval_cambio_horario() {
        return id_comeval_cambio_horario;
    }

    public void setId_comeval_cambio_horario(Long id_comeval_cambio_horario) {
        this.id_comeval_cambio_horario = id_comeval_cambio_horario;
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

    public String getPersonal() {
        return personal;
    }

    public void setPersonal(String personal) {
        this.personal = personal;
    }

    public Long getPlaza() {
        return plaza;
    }

    public void setPlaza(Long plaza) {
        this.plaza = plaza;
    }

    public Long getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Long periodo) {
        this.periodo = periodo;
    }

    public Long getAnio() {
        return anio;
    }

    public void setAnio(Long anio) {
        this.anio = anio;
    }

    public String getSubpartida() {
        return subpartida;
    }

    public void setSubpartida(String subpartida) {
        this.subpartida = subpartida;
    }

    public String getRenglon() {
        return renglon;
    }

    public void setRenglon(String renglon) {
        this.renglon = renglon;
    }

    @Override
    public String toString() {
        return "Comeval_Cambio_Horario_Plaza{" + "id_comeval_cambio_horario=" + id_comeval_cambio_horario + ", id_horario=" + id_horario + ", horainicio=" + horainicio + ", horafin=" + horafin + ", dias=" + dias + ", personal=" + personal + ", plaza=" + plaza + ", periodo=" + periodo + ", anio=" + anio + ", subpartida=" + subpartida + ", renglon=" + renglon + '}';
    }
    
}
