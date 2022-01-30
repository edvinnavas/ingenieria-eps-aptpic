package entidad;

import java.io.Serializable;

public class Comeval_Licencia_Docente_Plaza implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id_comeval_licencia_docente;
    private String personal;
    private Long plaza;
    private Long periodo;
    private Long anio;
    private String subpartida;
    private String renglon;
    private Long numero_plaza;

    public Comeval_Licencia_Docente_Plaza(Long id_comeval_licencia_docente, String personal, Long plaza, Long periodo, Long anio, String subpartida, String renglon, Long numero_plaza) {
        this.id_comeval_licencia_docente = id_comeval_licencia_docente;
        this.personal = personal;
        this.plaza = plaza;
        this.periodo = periodo;
        this.anio = anio;
        this.subpartida = subpartida;
        this.renglon = renglon;
        this.numero_plaza = numero_plaza;
    }

    public Comeval_Licencia_Docente_Plaza() {
    }

    public Long getId_comeval_licencia_docente() {
        return id_comeval_licencia_docente;
    }

    public void setId_comeval_licencia_docente(Long id_comeval_licencia_docente) {
        this.id_comeval_licencia_docente = id_comeval_licencia_docente;
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

    public Long getNumero_plaza() {
        return numero_plaza;
    }

    public void setNumero_plaza(Long numero_plaza) {
        this.numero_plaza = numero_plaza;
    }

    @Override
    public String toString() {
        return "Comeval_Licencia_Docente_Plaza{" + "id_comeval_licencia_docente=" + id_comeval_licencia_docente + ", personal=" + personal + ", plaza=" + plaza + ", periodo=" + periodo + ", anio=" + anio + ", subpartida=" + subpartida + ", renglon=" + renglon + ", numero_plaza=" + numero_plaza + '}';
    }

}
