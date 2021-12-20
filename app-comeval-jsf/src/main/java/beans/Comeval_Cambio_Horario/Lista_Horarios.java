package beans.Comeval_Cambio_Horario;

import java.io.Serializable;

public class Lista_Horarios implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Long id_horario;
    private String hora_inicio;
    private String hora_final;
    private String dias;
    private String plaza;

    public Lista_Horarios(Long id_horario, String hora_inicio, String hora_final, String dias, String plaza) {
        this.id_horario = id_horario;
        this.hora_inicio = hora_inicio;
        this.hora_final = hora_final;
        this.dias = dias;
        this.plaza = plaza;
    }

    public Lista_Horarios() {
    }

    public Long getId_horario() {
        return id_horario;
    }

    public void setId_horario(Long id_horario) {
        this.id_horario = id_horario;
    }

    public String getHora_inicio() {
        return hora_inicio;
    }

    public void setHora_inicio(String hora_inicio) {
        this.hora_inicio = hora_inicio;
    }

    public String getHora_final() {
        return hora_final;
    }

    public void setHora_final(String hora_final) {
        this.hora_final = hora_final;
    }

    public String getDias() {
        return dias;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }

    public String getPlaza() {
        return plaza;
    }

    public void setPlaza(String plaza) {
        this.plaza = plaza;
    }

    @Override
    public String toString() {
        return "Lista_Horarios{" + "id_horario=" + id_horario + ", hora_inicio=" + hora_inicio + ", hora_final=" + hora_final + ", dias=" + dias + ", plaza=" + plaza + '}';
    }
    
}
