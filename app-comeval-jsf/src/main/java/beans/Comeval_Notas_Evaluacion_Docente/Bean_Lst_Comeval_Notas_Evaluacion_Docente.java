package beans.Comeval_Notas_Evaluacion_Docente;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javax.inject.Named;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;

@Named(value = "Bean_Lst_Comeval_Notas_Evaluacion_Docente")
@ViewScoped
public class Bean_Lst_Comeval_Notas_Evaluacion_Docente implements Serializable {

    private static final long serialVersionUID = 1L;
    private entidad.Usuario usuario;
    private List<Tbl_Comeval_Notas_Evaluacion_Docente> lst_comeval_notas_evaluacion_docente;
    private Tbl_Comeval_Notas_Evaluacion_Docente sel_comeval_notas_evaluacion_docente;
    private Long id_estado_solicitud;
    private Long id_tipo_solicitud;

    private Boolean btnCrear;

    public Bean_Lst_Comeval_Notas_Evaluacion_Docente() {
        try {
            this.lst_comeval_notas_evaluacion_docente = new ArrayList<>();
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: Bean_Lst_Comeval_Notas_Evaluacion_Docente ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }

    public void cargar_datos(entidad.Usuario usuario, Long id_estado_solicitud, Long id_tipo_solicitud) {
        try {
            this.usuario = usuario;
            this.id_estado_solicitud = id_estado_solicitud;
            this.id_tipo_solicitud = id_tipo_solicitud;

            String ccecenasql = "select "
                    + "cned.id_comeval_notas_evaluacion_docente id_solicitud, "
                    + "cned.fecha_ingreso fecha_ingreso, "
                    + "es.nombre estado_solicitud, "
                    + "ts.nombre tipo_solicitud "
                    + "from "
                    + "comeval_notas_evaluacion_docente cned "
                    + "left join estado_solicitud_comeval es on (cned.id_estado_solicitud = es.id_estado_solicitud and cned.id_tipo_solicitud = es.id_tipo_solicitud) "
                    + "left join tipo_solicitud_comeval ts on (es.id_tipo_solicitud = ts.id_tipo_solicitud) "
                    + "where "
                    + "cned.id_estado_solicitud=" + this.id_estado_solicitud + " and "
                    + "cned.id_tipo_solicitud=" + this.id_tipo_solicitud + " "
                    + "order by "
                    + "cned.id_comeval_notas_evaluacion_docente";

            this.lst_comeval_notas_evaluacion_docente = new ArrayList<>();
            servicio.cliente.Cliente_Api_Comeval_Rest cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
            String jsonString = cliente_api_comeval_rest.driver_comeval_personal2(ccecenasql);
            Type listType = new TypeToken<ArrayList<String>>() {
            }.getType();
            List<String> lista_drive = new Gson().fromJson(jsonString, listType);
            SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            for (Integer i = 1; i < lista_drive.size(); i++) {
                String[] col = lista_drive.get(i).split("???");
                Tbl_Comeval_Notas_Evaluacion_Docente tbl_comeval_notas_evaluacion_docente = new Tbl_Comeval_Notas_Evaluacion_Docente(Long.parseLong(col[0]), dateFormat1.parse(col[1]), col[2], col[3]);
                this.lst_comeval_notas_evaluacion_docente.add(tbl_comeval_notas_evaluacion_docente);
            }

            if (this.id_estado_solicitud == Long.parseLong("1")) {
                this.btnCrear = false;
            } else {
                this.btnCrear = true;
            }
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: cargar_datos ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }

    public void siguiente_estado_solicitud() {
        try {
            if (this.lst_comeval_notas_evaluacion_docente != null) {
                List<entidad.Comeval_Notas_Evaluacion_Docente> lst_comeval_notas_evaluacion_docente_temp = new ArrayList<>();

                String cadenasql = "select "
                        + "t.id_comeval_notas_evaluacion_docente, "
                        + "t.nota_ref, "
                        + "coalesce(t.fecha_nota_ref, now()) fecha_nota_ref, "
                        + "t.anio_notas, "
                        + "t.path_archivo, "
                        + "t.fecha_ingreso, "
                        + "t.id_estado_solicitud, "
                        + "t.id_tipo_solicitud, "
                        + "t.rechazado, "
                        + "coalesce(t.id_estado_solicitud_rechazado,1) id_estado_solicitud_rechazado, "
                        + "coalesce(t.id_tipo_solicitud_rechazado,1) id_tipo_solicitud_rechazado "
                        + "from "
                        + "comeval_notas_evaluacion_docente t "
                        + "where "
                        + "t.id_comeval_notas_evaluacion_docente=" + this.sel_comeval_notas_evaluacion_docente.getId_comeval_notas_evaluacion_docente();
                servicio.cliente.Cliente_Api_Comeval_Rest cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
                String jsonString = cliente_api_comeval_rest.driver_comeval_personal2(cadenasql);
                Type listType = new TypeToken<ArrayList<String>>() {
                }.getType();
                List<String> lista_drive = new Gson().fromJson(jsonString, listType);
                for (Integer i = 1; i < lista_drive.size(); i++) {
                    String[] col = lista_drive.get(i).split("???");
                    SimpleDateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
                    SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
                    entidad.Comeval_Notas_Evaluacion_Docente comeval_notas_evaluacion_docente = new entidad.Comeval_Notas_Evaluacion_Docente(
                            Long.parseLong(col[0]),
                            col[1],
                            dateFormat2.format(dateFormat1.parse(col[2])),
                            Long.parseLong(col[3]),
                            col[4],
                            this.usuario.getUsuario(),
                            dateFormat2.format(dateFormat1.parse(col[5])),
                            Long.parseLong(col[6]),
                            Long.parseLong(col[7]),
                            Long.parseLong(col[8]),
                            Long.parseLong(col[9]),
                            Long.parseLong(col[10]),
                            null,
                            null);
                    lst_comeval_notas_evaluacion_docente_temp.add(comeval_notas_evaluacion_docente);
                }

                cliente_api_comeval_rest = new servicio.cliente.Cliente_Api_Comeval_Rest("admin", "@dm1n");
                String resultado = "";
                if (this.id_estado_solicitud == Long.parseLong("1")) {
                    resultado = cliente_api_comeval_rest.notas_evaluacion_enviar_ingreso_secretario(lst_comeval_notas_evaluacion_docente_temp);
                }

                this.cargar_datos(this.usuario, this.id_estado_solicitud, this.id_tipo_solicitud);

                String[] mensaje = resultado.split(",");
                if (mensaje[0].equals("0")) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje del sistema...", mensaje[1]));
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Mensaje del sistema...", mensaje[1]));
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Mensaje del sistema...", "Debe seleccionar una solicitud."));
            }
        } catch (Exception ex) {
            System.out.println("CLASE: " + this.getClass().getName() + " METODO: siguiente_estado_solicitud ERROR: " + ex.toString());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Mensaje del sistema...", ex.toString()));
        }
    }

    public entidad.Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(entidad.Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Tbl_Comeval_Notas_Evaluacion_Docente> getLst_comeval_notas_evaluacion_docente() {
        return lst_comeval_notas_evaluacion_docente;
    }

    public void setLst_comeval_notas_evaluacion_docente(List<Tbl_Comeval_Notas_Evaluacion_Docente> lst_comeval_notas_evaluacion_docente) {
        this.lst_comeval_notas_evaluacion_docente = lst_comeval_notas_evaluacion_docente;
    }

    public Tbl_Comeval_Notas_Evaluacion_Docente getSel_comeval_notas_evaluacion_docente() {
        return sel_comeval_notas_evaluacion_docente;
    }

    public void setSel_comeval_notas_evaluacion_docente(Tbl_Comeval_Notas_Evaluacion_Docente sel_comeval_notas_evaluacion_docente) {
        this.sel_comeval_notas_evaluacion_docente = sel_comeval_notas_evaluacion_docente;
    }

    public Long getId_estado_solicitud() {
        return id_estado_solicitud;
    }

    public void setId_estado_solicitud(Long id_estado_solicitud) {
        this.id_estado_solicitud = id_estado_solicitud;
    }

    public Long getId_tipo_solicitud() {
        return id_tipo_solicitud;
    }

    public void setId_tipo_solicitud(Long id_tipo_solicitud) {
        this.id_tipo_solicitud = id_tipo_solicitud;
    }

    public Boolean getBtnCrear() {
        return btnCrear;
    }

    public void setBtnCrear(Boolean btnCrear) {
        this.btnCrear = btnCrear;
    }
    
}
