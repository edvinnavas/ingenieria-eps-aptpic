package beans.Comeval_Ampliacion_Horario;

import java.io.Serializable;

public class Lista_Horarios implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Long id_horario;
    private String hora_inicio;
    private String hora_final;
    private String dias;

    public Lista_Horarios(Long id_horario, String hora_inicio, String hora_final, String dias) {
        this.id_horario = id_horario;
        this.hora_inicio = hora_inicio;
        this.hora_final = hora_final;
        this.dias = dias;
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

    @Override
    public String toString() {
        return "Lista_Horarios{" + "id_horario=" + id_horario + ", hora_inicio=" + hora_inicio + ", hora_final=" + hora_final + ", dias=" + dias + '}';
    }
    
}
