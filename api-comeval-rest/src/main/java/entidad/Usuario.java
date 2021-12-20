package entidad;

import java.io.Serializable;

public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String usuario;
    private String nombre;
    private String contrasena;
    private Long id_rol;
    private Long id_unidad;

    public Usuario(String usuario, String nombre, String contrasena, Long id_rol, Long id_unidad) {
        this.usuario = usuario;
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.id_rol = id_rol;
        this.id_unidad = id_unidad;
    }

    public Usuario() {
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Long getId_rol() {
        return id_rol;
    }

    public void setId_rol(Long id_rol) {
        this.id_rol = id_rol;
    }

    public Long getId_unidad() {
        return id_unidad;
    }

    public void setId_unidad(Long id_unidad) {
        this.id_unidad = id_unidad;
    }

    @Override
    public String toString() {
        return "Usuario{" + "usuario=" + usuario + ", nombre=" + nombre + ", contrasena=" + contrasena + ", id_rol=" + id_rol + ", id_unidad=" + id_unidad + '}';
    }
    
}
