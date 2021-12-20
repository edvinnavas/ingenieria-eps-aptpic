package beans.Comeval_Carga_Eval_Comeval;

import java.io.File;
import java.io.Serializable;

public class lista_archivos implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id_archivo;
    private String nombre_archivo;
    private File archivo;

    public lista_archivos(Long id_archivo, String nombre_archivo, File archivo) {
        this.id_archivo = id_archivo;
        this.nombre_archivo = nombre_archivo;
        this.archivo = archivo;
    }

    public lista_archivos() {
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

    public File getArchivo() {
        return archivo;
    }

    public void setArchivo(File archivo) {
        this.archivo = archivo;
    }

    @Override
    public String toString() {
        return "lista_archivos{" + "id_archivo=" + id_archivo + ", nombre_archivo=" + nombre_archivo + ", archivo=" + archivo + '}';
    }
    
}
