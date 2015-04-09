package actions;
import interfacesDAO.CalificacionDAO;
import interfacesDAO.ViajeDAO;
import interfacesDAO.ViajeroDAO;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;

import com.opensymphony.xwork2.ActionContext;

import objetos.Calificacion;
import objetos.CalificacionPendiente;
import objetos.Viaje;
import objetos.Viajero;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CalificacionesAction {
	private String comentario;
	private Long idCalificado;
	private List<String> opciones= new ArrayList<String>();
	private Long idViaje;
	private String opcionSeleccionada;
	private List<CalificacionPendiente> calificacionesPendientes;
	private Long votosPositivos;
	private Long votosNegativos;
	private  SessionMap<String,Object> sesion;
	private List<Calificacion> calificaciones;
	private Viajero viajero=null;
	@Autowired
	private ViajeroDAO viajeroDao ;
	@Autowired 
	private CalificacionDAO calificacionDao;
	@Autowired
	private ViajeDAO viajeDao;
	
	
	
	@Action(value="calificacionesViajero",results={
	@Result(name="succes",location = "calificacionesViajero.jsp"),
	@Result(name="index", location="Index", type="redirectAction")})
	public String verUsuario(){		
		HttpServletRequest req = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
		if(req.getParameter("id")!=null ){
			 this.viajero=this.viajeroDao.recuperar(Long.parseLong(req.getParameter("id")));
		}
		if(this.viajero!=null && this.getSesionUsuario().get("esAdmin")!=null && false==(Boolean)this.getSesionUsuario().get("esAdmin")){
			this.calificaciones=(ArrayList<Calificacion>)this.calificacionDao.recuperarPorCalificado(viajero.getId());
			System.out.println(this.calificaciones.size());
			this.votosNegativos=(long) 0;
			this.votosPositivos=(long) 0;
			for (Calificacion c:this.calificaciones){
				System.out.println("calificaion"+c.getEsPositiva());
				if(c.getEsPositiva()==true){
					this.votosPositivos++;
				}else{
					this.votosNegativos++;
				}
			}
			return "succes";
		}else{
			return "index";
		}
	}
	public boolean tipoDeCalificacion() {
		//se va obtener con getText
		if(this.getOpcionSeleccionada().equals("positivo")){
			return true;
		}
		else
			 return false;
		
	}
	
	public String getDefaultRadio(){
		//se va obtener con getText
		return "positivo";
	}
 
	

	@Action(value="verCalificacionesPendientes",results={
	@Result(name="succes",location = "calificacionesPendientes.jsp"),
	@Result(name="index", location="Index", type="redirectAction")})
	public String verCalificacionesPendientes(){	
    if( this.getSesionUsuario().get("esAdmin")!=null && false==(Boolean)this.getSesionUsuario().get("esAdmin")){
	   this.viajero=this.viajeroDao.recuperarConViajesEstoyYCalificaciones(
		    	(Long)this.getSesionUsuario().get("usuario"));
       this.calificacionesPendientes=this.viajero.recuperarCalificionesPendientes();
       System.out.println("calificaciones pendientes"+this.calificacionesPendientes.size());
       return "succes";
    }else{
    	return "index";
    }
    }
	
	@Action(value="calificarUsuario",
		 results={
		 @Result(name="succes",location="calificar.jsp"),
		 @Result(name="index", location="Index", type="redirectAction")})
	public String calificarUsuario(){
	   HttpServletRequest req = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
	   if(req.getParameter("idViaje")!=null && req.getParameter("idCalificado")!=null && this.getSesionUsuario().get("esAdmin")!=null && false==(Boolean)this.getSesionUsuario().get("esAdmin"))
	   {	   
	     this.setIdCalificado(Long.parseLong(req.getParameter("idCalificado")));
	     this.setIdViaje(Long.parseLong(req.getParameter("idViaje")));
	     this.getOpciones().add("positivo");
	     this.getOpciones().add("negativo");
	     return "succes";
	   }else{
		   return "index";
	   }
	}
	
	@Action(value="guardarCalificacionAction",
			results={
			@Result(name="succes" ,location="verCalificacionesPendientes",type="redirect"),
			@Result(name="index", location="Index", type="redirectAction")
	        })
	public String guardarCalificacion(){
		
	 if( this.getSesionUsuario().get("esAdmin")!=null && false==(Boolean)this.getSesionUsuario().get("esAdmin")){
		Long idCalificador =(Long)this.getSesionUsuario().get("usuario");
		Viaje v= this.viajeDao.recuperar(this.getIdViaje());
		Viajero viajeroCalificado = this.viajeroDao.recuperar(this.getIdCalificado());
		Viajero viajerCalificador = this.viajeroDao.recuperarConCalificaciones(idCalificador);
		Calificacion c =new Calificacion(this.tipoDeCalificacion(),this.getComentario(), viajeroCalificado, viajerCalificador,v);
		this.calificacionDao.persistir(c);
		viajerCalificador.addCalificacion(c);
		this.viajeroDao.actualizar(viajerCalificador);
		return "succes";
	 }else{
		 return "index";
	 }
	}
	public SessionMap<String,Object> getSesionUsuario(){
	     if(this.sesion == null) {
		    this.sesion=
		    (SessionMap<String, Object>) ActionContext.getContext().getSession();
	     }
	     return this.sesion;
	 }
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public Long getIdCalificado() {
		return idCalificado;
	}
	public void setIdCalificado(Long idCalificado) {
		this.idCalificado = idCalificado;
	}
	public List<String> getOpciones() {
		return opciones;
	}
	public void setOpciones(List<String> opciones) {
		this.opciones = opciones;
	}
	public List<CalificacionPendiente> getCalificacionesPendientes() {
		return calificacionesPendientes;
	}
	public void setCalificacionesPendientes(
			List<CalificacionPendiente> calificacionesPendientes) {
		this.calificacionesPendientes = calificacionesPendientes;
	}
	public Long getIdViaje() {
		return idViaje;
	}
	public void setIdViaje(Long idViaje) {
		this.idViaje = idViaje;
	}
	public String getOpcionSeleccionada() {
		return this.opcionSeleccionada;
	}
	public void setOpcionSeleccionada(String opcionSeleccionada) {
		this.opcionSeleccionada = opcionSeleccionada;
	}
	public List<Calificacion> getCalificaciones() {
		return calificaciones;
	}
	public void setCalificaciones(List<Calificacion> calificaciones) {
		this.calificaciones = calificaciones;
	}
	public Long getVotosPositivos() {
		return votosPositivos;
	}
	public void setVotosPositivos(Long votosPositivos) {
		this.votosPositivos = votosPositivos;
	}
	public Long getVotosNegativos() {
		return votosNegativos;
	}
	public void setVotosNegativos(Long votosNegativos) {
		this.votosNegativos = votosNegativos;
	}
	public Viajero getViajero() {
		return viajero;
	}
	public void setViajero(Viajero viajero) {
		this.viajero = viajero;
	}
}
