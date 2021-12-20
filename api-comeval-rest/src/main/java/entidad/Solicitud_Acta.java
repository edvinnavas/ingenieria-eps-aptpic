package entidad;

import java.io.Serializable;

public class Solicitud_Acta implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Long id_solicitud;
    private Long estado;
    private Long asunto;
    private Long carreraorigen;
    private Long carreradestino;
    private String solicitanteexterno;
    private String descripcion;
    private String fechaaprobacion;
    private String fechaingreso;
    private String facultadorigen;
    private String facultaddestino;
    private String universidadorigen;
    private String universidaddestino;
    private String scarreraorigen;
    private String id_archivo;
    private Long estado_validacion;
    private Long anio;
    private Long periodo;
    private Long transaccion;
    private String impresa;
    private Long tipoaprobacion;
    private String con_asignacion;

    public Solicitud_Acta(Long id_solicitud, Long estado, Long asunto, Long carreraorigen, Long carreradestino, String solicitanteexterno, String descripcion, String fechaaprobacion, String fechaingreso, String facultadorigen, String facultaddestino, String universidadorigen, String universidaddestino, String scarreraorigen, String id_archivo, Long estado_validacion, Long anio, Long periodo, Long transaccion, String impresa, Long tipoaprobacion, String con_asignacion) {
        this.id_solicitud = id_solicitud;
        this.estado = estado;
        this.asunto = asunto;
        this.carreraorigen = carreraorigen;
        this.carreradestino = carreradestino;
        this.solicitanteexterno = solicitanteexterno;
        this.descripcion = descripcion;
        this.fechaaprobacion = fechaaprobacion;
        this.fechaingreso = fechaingreso;
        this.facultadorigen = facultadorigen;
        this.facultaddestino = facultaddestino;
        this.universidadorigen = universidadorigen;
        this.universidaddestino = universidaddestino;
        this.scarreraorigen = scarreraorigen;
        this.id_archivo = id_archivo;
        this.estado_validacion = estado_validacion;
        this.anio = anio;
        this.periodo = periodo;
        this.transaccion = transaccion;
        this.impresa = impresa;
        this.tipoaprobacion = tipoaprobacion;
        this.con_asignacion = con_asignacion;
    }

    public Solicitud_Acta() {
    }

    public Long getId_solicitud() {
        return id_solicitud;
    }

    public void setId_solicitud(Long id_solicitud) {
        this.id_solicitud = id_solicitud;
    }

    public Long getEstado() {
        return estado;
    }

    public void setEstado(Long estado) {
        this.estado = estado;
    }

    public Long getAsunto() {
        return asunto;
    }

    public void setAsunto(Long asunto) {
        this.asunto = asunto;
    }

    public Long getCarreraorigen() {
        return carreraorigen;
    }

    public void setCarreraorigen(Long carreraorigen) {
        this.carreraorigen = carreraorigen;
    }

    public Long getCarreradestino() {
        return carreradestino;
    }

    public void setCarreradestino(Long carreradestino) {
        this.carreradestino = carreradestino;
    }

    public String getSolicitanteexterno() {
        return solicitanteexterno;
    }

    public void setSolicitanteexterno(String solicitanteexterno) {
        this.solicitanteexterno = solicitanteexterno;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaaprobacion() {
        return fechaaprobacion;
    }

    public void setFechaaprobacion(String fechaaprobacion) {
        this.fechaaprobacion = fechaaprobacion;
    }

    public String getFechaingreso() {
        return fechaingreso;
    }

    public void setFechaingreso(String fechaingreso) {
        this.fechaingreso = fechaingreso;
    }

    public String getFacultadorigen() {
        return facultadorigen;
    }

    public void setFacultadorigen(String facultadorigen) {
        this.facultadorigen = facultadorigen;
    }

    public String getFacultaddestino() {
        return facultaddestino;
    }

    public void setFacultaddestino(String facultaddestino) {
        this.facultaddestino = facultaddestino;
    }

    public String getUniversidadorigen() {
        return universidadorigen;
    }

    public void setUniversidadorigen(String universidadorigen) {
        this.universidadorigen = universidadorigen;
    }

    public String getUniversidaddestino() {
        return universidaddestino;
    }

    public void setUniversidaddestino(String universidaddestino) {
        this.universidaddestino = universidaddestino;
    }

    public String getScarreraorigen() {
        return scarreraorigen;
    }

    public void setScarreraorigen(String scarreraorigen) {
        this.scarreraorigen = scarreraorigen;
    }

    public String getId_archivo() {
        return id_archivo;
    }

    public void setId_archivo(String id_archivo) {
        this.id_archivo = id_archivo;
    }

    public Long getEstado_validacion() {
        return estado_validacion;
    }

    public void setEstado_validacion(Long estado_validacion) {
        this.estado_validacion = estado_validacion;
    }

    public Long getAnio() {
        return anio;
    }

    public void setAnio(Long anio) {
        this.anio = anio;
    }

    public Long getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Long periodo) {
        this.periodo = periodo;
    }

    public Long getTransaccion() {
        return transaccion;
    }

    public void setTransaccion(Long transaccion) {
        this.transaccion = transaccion;
    }

    public String getImpresa() {
        return impresa;
    }

    public void setImpresa(String impresa) {
        this.impresa = impresa;
    }

    public Long getTipoaprobacion() {
        return tipoaprobacion;
    }

    public void setTipoaprobacion(Long tipoaprobacion) {
        this.tipoaprobacion = tipoaprobacion;
    }

    public String getCon_asignacion() {
        return con_asignacion;
    }

    public void setCon_asignacion(String con_asignacion) {
        this.con_asignacion = con_asignacion;
    }

    @Override
    public String toString() {
        return "Solicitud_Acta{" + "id_solicitud=" + id_solicitud + ", estado=" + estado + ", asunto=" + asunto + ", carreraorigen=" + carreraorigen + ", carreradestino=" + carreradestino + ", solicitanteexterno=" + solicitanteexterno + ", descripcion=" + descripcion + ", fechaaprobacion=" + fechaaprobacion + ", fechaingreso=" + fechaingreso + ", facultadorigen=" + facultadorigen + ", facultaddestino=" + facultaddestino + ", universidadorigen=" + universidadorigen + ", universidaddestino=" + universidaddestino + ", scarreraorigen=" + scarreraorigen + ", id_archivo=" + id_archivo + ", estado_validacion=" + estado_validacion + ", anio=" + anio + ", periodo=" + periodo + ", transaccion=" + transaccion + ", impresa=" + impresa + ", tipoaprobacion=" + tipoaprobacion + ", con_asignacion=" + con_asignacion + '}';
    }
    
}
