package actions;
import interfacesDAO.EventoDAO;
import interfacesDAO.ViajeDAO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	
	private List<Evento> eventos;
	private static int idEvento;
	private static Date fechaMinima;
	private static Date fechaMaxima;
	private static String horaMaxima;
	private static String horaMinima;
	private static String viajeSeleccionado;
	private List<String> tiposDeViajes;
	
	private List<ViajeJSON> gridModel;
	private Integer rows = 0;
	private Integer page = 0;
	private Integer total = 0;
	private Integer record = 0;
	private String sord;
	private String sidx;
	private List<ViajeJSON> viajes = new ArrayList<ViajeJSON>();
	private SessionMap<String, Object> session;
	
	public void inicializar(){
		eventos = new ArrayList<Evento>();
		eventos=eventoDAO.recuperarTodos("id");
		tiposDeViajes = new ArrayList<String>();
		tiposDeViajes.add("viaje_periodico");
		tiposDeViajes.add("viaje_puntual");
		tiposDeViajes.add("ambos");
	}
	
	public String getDefaultTipoDeViaje(){
		return this.getViajeSeleccionado();
	}
	
	public String getViajeSeleccionado() {
		if((viajeSeleccionado==null) ||(viajeSeleccionado.equals(""))){
			return "ambos";
		}else{
		   return viajeSeleccionado;
		}
	}


	public  void setViajeSeleccionado(String viajeSeleccionado) {
		TablaRecorridoAction.viajeSeleccionado = viajeSeleccionado;
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


	public int getIdEvento() {
		return idEvento;
	}


	public void setIdEvento(int idEvento) {
		TablaRecorridoAction.idEvento = idEvento;
	}


	public  Date getFechaMinima() {
		return fechaMinima;
	}


	public  void setFechaMinima(Date fechaMinima) {
		TablaRecorridoAction.fechaMinima = fechaMinima;
	}


	public  Date getFechaMaxima() {
		return fechaMaxima;
	}


	public  void setFechaMaxima(Date fechaMaxima) {
		TablaRecorridoAction.fechaMaxima = fechaMaxima;
	}


	public String getHoraMaxima() {
		return horaMaxima;
	}


	public void setHoraMaxima(String horaMaxima) {
		TablaRecorridoAction.horaMaxima = horaMaxima;
	}


	public  String getHoraMinima() {
		return horaMinima;
	}


	public  void setHoraMinima(String horaMinima) {
		TablaRecorridoAction.horaMinima = horaMinima;
	}
	
	@JSON(serialize=false,deserialize = false)
	public EventoDAO getEventoDAO() {
		return eventoDAO;
	}

	public void setEventoDAO(EventoDAO eventoDAO) {
		this.eventoDAO = eventoDAO;
	}
	
	
	
	@Action(value = "tablaDeRecorridosAction", results={@Result(name="success", location="verRecorridos.jsp")
	                                           ,@Result(name="index", location="Index", type="redirectAction"),
                                                @Result(name="input", location="verRecorridos.jsp")}
	)
	public String tablaDeRecorridosAction(){
		session=(SessionMap<String, Object>) ActionContext.getContext().getSession();
		if(session.get("esAdmin")!=null && false==(Boolean)session.get("esAdmin")){
		   this.inicializar();
		   return "success";
		}else{
			return "index";
		}
	}
	@Action(value = "datosAction",results={ @Result (name="success",type="json")})
	@SkipValidation
	public String execute(){
	   int to = (rows * page);
	   int from = to - rows;
	   ArrayList<Integer> t = new ArrayList<Integer>();
	   this.setViajes((List<ViajeJSON>)
         viajeDAO.recuperarViajesCompletosJSON(from,to,sidx,sord,idEvento,fechaMinima,fechaMaxima
		,horaMaxima,horaMinima,this.getViajeSeleccionado(),t));
	   this.inicializar();
	   //la lista t trae el tamaño total de la consulta sin cortar por el from ni to
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
		this.setViajeSeleccionado("ambos");
	}
	
	public void validate (){
	boolean pasoValidacion=true;
	if((this.getHoraMinima()!=null) && !(this.getHoraMinima().equals("")) && !(this.getHoraMinima().matches("^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$"))){
	    this.setHoraMinima(null);pasoValidacion=false;
		this.addFieldError("horaMinima", "no se filtro por hora minima ingrese hora valida hh:mm");
	 }
	if((this.getHoraMaxima()!=null) && !(this.getHoraMaxima().equals("")) && !(this.getHoraMaxima().matches("^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$"))){
		this.setHoraMaxima(null);pasoValidacion=false;
		this.addFieldError("horaMaxima", "no se filtro por hora maxima ingrese hora valida hh:mm");
    }	
	if(this.getFechaMinima()!=null){
	   try{	
	     String f = new SimpleDateFormat("yyyy-MM-dd").format(this.getFechaMinima());
	   }catch(Exception e){
		     System.out.println("mal fecha");
	   }
	}
	if(!pasoValidacion){
	  this.inicializar();
	}
	
}
	
	

}
