package entidad;

import java.io.Serializable;
import java.util.Date;

public class Acuerdo_Acta implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Long id_solicitud_acta;
    private Boolean tiene_contenido;
    private Long contenido;
    private Long no_acta;     // CONTENIDO.NUMEROACTA
    private Long anio_acta;   // CONTENIDO.ANIOACTA
    private Long punto_acta;  // CONTENIDO.CONTENIDO
    private Long inciso_acta; // CONTENIDO.NUMERO
    private Date fecha_acta;  // NEW DATE()
    private String acuerdo;   // RESOLUCION.ACUERDO
    private Boolean aprobado; // RESOLUCION.APROBADO

    public Acuerdo_Acta(Long id_solicitud_acta, Boolean tiene_contenido, Long contenido, Long no_acta, Long anio_acta, Long punto_acta, Long inciso_acta, Date fecha_acta, String acuerdo, Boolean aprobado) {
        this.id_solicitud_acta = id_solicitud_acta;
        this.tiene_contenido = tiene_contenido;
        this.contenido = contenido;
        this.no_acta = no_acta;
        this.anio_acta = anio_acta;
        this.punto_acta = punto_acta;
        this.inciso_acta = inciso_acta;
        this.fecha_acta = fecha_acta;
        this.acuerdo = acuerdo;
        this.aprobado = aprobado;
    }

    public Acuerdo_Acta() {
    }

    public Long getId_solicitud_acta() {
        return id_solicitud_acta;
    }

    public void setId_solicitud_acta(Long id_solicitud_acta) {
        this.id_solicitud_acta = id_solicitud_acta;
    }

    public Boolean getTiene_contenido() {
        return tiene_contenido;
    }

    public void setTiene_contenido(Boolean tiene_contenido) {
        this.tiene_contenido = tiene_contenido;
    }

    public Long getContenido() {
        return contenido;
    }

    public void setContenido(Long contenido) {
        this.contenido = contenido;
    }

    public Long getNo_acta() {
        return no_acta;
    }

    public void setNo_acta(Long no_acta) {
        this.no_acta = no_acta;
    }

    public Long getAnio_acta() {
        return anio_acta;
    }

    public void setAnio_acta(Long anio_acta) {
        this.anio_acta = anio_acta;
    }

    public Long getPunto_acta() {
        return punto_acta;
    }

    public void setPunto_acta(Long punto_acta) {
        this.punto_acta = punto_acta;
    }

    public Long getInciso_acta() {
        return inciso_acta;
    }

    public void setInciso_acta(Long inciso_acta) {
        this.inciso_acta = inciso_acta;
    }

    public Date getFecha_acta() {
        return fecha_acta;
    }

    public void setFecha_acta(Date fecha_acta) {
        this.fecha_acta = fecha_acta;
    }

    public String getAcuerdo() {
        return acuerdo;
    }

    public void setAcuerdo(String acuerdo) {
        this.acuerdo = acuerdo;
    }

    public Boolean getAprobado() {
        return aprobado;
    }

    public void setAprobado(Boolean aprobado) {
        this.aprobado = aprobado;
    }

    @Override
    public String toString() {
        return "Acuerdo_Acta{" + "id_solicitud_acta=" + id_solicitud_acta + ", tiene_contenido=" + tiene_contenido + ", contenido=" + contenido + ", no_acta=" + no_acta + ", anio_acta=" + anio_acta + ", punto_acta=" + punto_acta + ", inciso_acta=" + inciso_acta + ", fecha_acta=" + fecha_acta + ", acuerdo=" + acuerdo + ", aprobado=" + aprobado + '}';
    }
    
}
