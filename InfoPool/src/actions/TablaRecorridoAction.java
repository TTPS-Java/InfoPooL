package actions;
import interfacesDAO.EventoDAO;
import interfacesDAO.ViajeDAO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import objetos.Evento;
import objetos.ViajeJSON;



@ParentPackage("json-default")
@Controller
public class TablaRecorridoAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ViajeDAO viajeDAO;
	@Autowired
	private EventoDAO eventoDAO; 
	
	private List<ViajeJSON> gridModel;
	private Integer rows = 0;
	private Integer page = 0;
	private Integer total = 0;
	private Integer record = 0;
	private String sord;
	private String sidx;
	private List<ViajeJSON> viajes = new ArrayList<ViajeJSON>();
	private SessionMap<String, Object> session;
	
	
	
	
	private List<Evento> eventos;
	private  int idEvento;
	private  Date fechaMinima;
	private  Date fechaMaxima;
	private  String horaMaxima;
	private  String horaMinima;
	private  String viajeSeleccionado;
	private List<String> tiposDeViajes;
	
	public void inicializar(){
		eventos = new ArrayList<Evento>();
		eventos=eventoDAO.recuperarTodos("id");
		tiposDeViajes = new ArrayList<String>();
		tiposDeViajes.add(this.getText("verrecorridos.viaje_periodico"));
		tiposDeViajes.add(this.getText("verrecorridos.viaje_puntual"));
		tiposDeViajes.add(this.getText("verrecorridos.ambos"));
	}
	
	public String getDefaultTipoDeViaje(){
		return (String)session.get("viajeSeleccionado");
	}
	public  void setViajeSeleccionado(String viajeSeleccionado) {
		this.viajeSeleccionado = viajeSeleccionado;
	}


	public List<String> getTiposDeViajes() {
		return tiposDeViajes;
	}


	public void setTiposDeViajes(List<String> tiposDeViajes) {
		this.tiposDeViajes = tiposDeViajes;
	}


	public List<Evento> getEventos() {
		return eventos;
	}


	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}
/* DATOS QUE PUEDEN MODIFICAR */
	public int getIdEvento() {
		return (int) session.get("idEvento");
	}
	public  Date getFechaMinima() {
		return (Date) session.get("fechaMinima");
	}
	public  Date getFechaMaxima() {
		return (Date) session.get("fechaMaxima");
		
	}
	public String getHoraMaxima() {
		return (String) session.get("horaMaxima");
	}
    public  String getHoraMinima() {
		return (String) session.get("horaMinima");
	}
    public String getViajeSeleccionado() {
		return this.getText("db."+(String) session.get("viajeSeleccionado"));
	}
   
    public void setHoraMaxima(String horaMaxima) {
    	this.horaMaxima = horaMaxima;}
	public  void setFechaMinima(Date fechaMinima) {
		this.fechaMinima = fechaMinima;}
	public  void setHoraMinima(String horaMinima) {
		this.horaMinima = horaMinima;
	}
	public void setIdEvento(int idEvento) {
		this.idEvento = idEvento;}
	public  void setFechaMaxima(Date fechaMaxima) {
		this.fechaMaxima = fechaMaxima;}
	
	
/*FIN DATOS QUE PUEDEN CAMBIAR*/	
	
	
	
	@JSON(serialize=false,deserialize = false)
	public EventoDAO getEventoDAO() {
		return eventoDAO;
	}

	public void setEventoDAO(EventoDAO eventoDAO) {
		this.eventoDAO = eventoDAO;
	}
@Action(value = "cambioDatosTablaDeRecorridosAction", results={@Result(name="success", location="verRecorridos.jsp")
,@Result(name="index", location="Index", type="redirectAction"),
@Result(name="input", location="verRecorridos.jsp")})
public String cambioDatosTablaDeRecorridosAction(){
	session=(SessionMap<String, Object>) ActionContext.getContext().getSession();
 if(session.get("esAdmin")!=null && false==(Boolean)session.get("esAdmin")){
   this.inicializar();
   session.put("horaMinima",this.horaMinima);
   session.put("horaMaxima",this.horaMaxima);
   session.put("fechaMinima",this.fechaMinima);
   session.put("fechaMaxima",this.fechaMaxima);
   session.put("idEvento",this.idEvento);
   System.out.println(this.viajeSeleccionado);
   System.out.println("tipo de viaje modificado:"+this.getText("verrecorridos."+this.getText("db."+this.viajeSeleccionado)));
   session.put("viajeSeleccionado",this.getText("verrecorridos."+this.getText("db."+this.viajeSeleccionado)));
   return "success";
 }else{
    return "index";
}
}
	
	
		
	@Action(value = "tablaDeRecorridosAction", results={@Result(name="success", location="verRecorridos.jsp")
	                                           ,@Result(name="index", location="Index", type="redirectAction"),
                                                @Result(name="input", location="verRecorridos.jsp")}
	)
	public String tablaDeRecorridosAction(){
		session=(SessionMap<String, Object>) ActionContext.getContext().getSession();
		if(session.get("esAdmin")!=null && false==(Boolean)session.get("esAdmin")){
		  this.inicializar();
		  session.put("horaMinima",null);
		  session.put("horaMaxima",null);
		  session.put("fechaMinima",null);
		  session.put("fechaMaxima",null);
		  session.put("idEvento",-1);
		  session.put("viajeSeleccionado",this.getText("verrecorridos.ambos"));
		   return "success";
		}else{
			return "index";
		}
	}
	@Action(value = "datosAction",results={ @Result (name="success",type="json")})
	@SkipValidation
	public String execute(){
	   session=(SessionMap<String, Object>) ActionContext.getContext().getSession();
	   Long idConductor = (Long) session.get("usuario");
	   int to = (rows * page);
	   int from = to - rows;
	   ArrayList<Integer> t = new ArrayList<Integer>();
	   System.out.println("viaje seleccionado:" + this.getText(this.getViajeSeleccionado()));
	   this.setViajes((List<ViajeJSON>)
       viajeDAO.recuperarViajesCompletosJSON(from,to,sidx,sord,this.getIdEvento(),this.getFechaMinima(),
       this.getFechaMaxima(),this.getHoraMaxima(),this.getHoraMinima(),this.getViajeSeleccionado(),t,idConductor));
	   this.inicializar();
	   //la lista t trae el tama�o total de la consulta sin cortar por el from ni to
	   record = t.get(0);
	   gridModel = this.getViajes();
	   total = (int) Math.ceil((double) record / (double) rows);
	   return "success";
	}
	
	
	@JSON(serialize=false,deserialize = false)
	public ViajeDAO getViajeDAO() {
		return viajeDAO;
	}

	public void setViajePeriodicoDAO(ViajeDAO usuario) {
		this.viajeDAO = usuario;
	}
	
	public String getJSON() {
		return execute();
	}
	
	public List<ViajeJSON> getViajes() {
		return this.viajes;
	}


	public void setViajes(List<ViajeJSON> usuarios) {
		this.viajes = usuarios;
	}


	public List<ViajeJSON> getGridModel() {
		return gridModel;
	}
	public void setGridModel(List<ViajeJSON> gridModel) {
		this.gridModel = gridModel;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getRecord() {
		return record;
	}
	public void setRecord(Integer record) {
		this.record = record;
		if (this.record > 0 && this.rows > 0) {
			this.total = (int) Math.ceil((double) this.record
					/ (double) this.rows);
		} else {
			this.total = 0;
		}
	}
	public String getSord() {
		return sord;
	}
	public void setSord(String sord) {
		this.sord = sord;
	}
	public String getSidx() {
		return sidx;
	}
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}
	public void borrarDatosParaFiltro(){
		this.setFechaMaxima(null);
		this.setFechaMinima(null);
		this.setHoraMaxima(null);
		this.setHoraMinima(null);
		this.setIdEvento(-1);
		this.setViajeSeleccionado(this.getText("verrecorridos.ambos"));
	}
	
	public void validate (){
	 session=(SessionMap<String, Object>) ActionContext.getContext().getSession();
	 boolean pasoValidacion=true;
	if((this.getHoraMinima()!=null) && !(this.getHoraMinima().equals("")) && !(this.getHoraMinima().matches("^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$"))){
	    this.setHoraMinima(null);pasoValidacion=false;
		this.addFieldError("horaMinima",this.getText("tablarecorridoaction.horaminimaerror"));
	 }
	if((this.getHoraMaxima()!=null) && !(this.getHoraMaxima().equals("")) && !(this.getHoraMaxima().matches("^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$"))){
		this.setHoraMaxima(null);pasoValidacion=false;
		this.addFieldError("horaMaxima",this.getText("tablarecorridoaction.horamaximaerror") );
    }	
	if(!pasoValidacion){
	  this.inicializar();
	}
	
}
	
	

}