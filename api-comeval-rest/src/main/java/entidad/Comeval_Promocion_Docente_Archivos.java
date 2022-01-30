package entidad;

import java.io.Serializable;

public class Comeval_Promocion_Docente_Archivos implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id_comeval_promocion_docente;
    private Long id_archivo;
    private String nombre_archivo;
    private String nombre_archivo_real;

    public Comeval_Promocion_Docente_Archivos(Long id_comeval_promocion_docente, Long id_archivo, String nombre_archivo, String nombre_archivo_real) {
        this.id_comeval_promocion_docente = id_comeval_promocion_docente;
        this.id_archivo = id_archivo;
        this.nombre_archivo = nombre_archivo;
        this.nombre_archivo_real = nombre_archivo_real;
    }

    public Comeval_Promocion_Docente_Archivos() {
    }

    public Long getId_comeval_promocion_docente() {
        return id_comeval_promocion_docente;
    }

    public void setId_comeval_promocion_docente(Long id_comeval_promocion_docente) {
        this.id_comeval_promocion_docente = id_comeval_promocion_docente;
    }

    public Long getId_archivo() {
        return id_archivo;
    }

    public void setId_archivo(Long id_archivo) {
        this.id_archivo = id_archivo;
    }

    public String getNombre_archivo() {
        return nombre_archivo;
    }

    public void setNombre_archivo(String nombre_archivo) {
        this.nombre_archivo = nombre_archivo;
    }

    public String getNombre_archivo_real() {
        return nombre_archivo_real;
    }

    public void setNombre_archivo_real(String nombre_archivo_real) {
        this.nombre_archivo_real = nombre_archivo_real;
    }

    @Override
    public String toString() {
        return "Comeval_Promocion_Docente_Archivos{" + "id_comeval_promocion_docente=" + id_comeval_promocion_docente + ", id_archivo=" + id_archivo + ", nombre_archivo=" + nombre_archivo + ", nombre_archivo_real=" + nombre_archivo_real + '}';
    }

}
