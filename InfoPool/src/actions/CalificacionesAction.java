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
	private  SessionMap<String,Object> sesion;
	@Autowired
	private ViajeroDAO viajeroDao ;
	@Autowired 
	private CalificacionDAO calificacionDao;
	@Autowired
	private ViajeDAO viajeDao;
	
	

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
	@Result(name="succes",location = "calificacionesPendientes.jsp")})
	public String verCalificacionesPendientes(){	
	   Viajero viajero=this.viajeroDao.recuperarConViajesEstoyYCalificaciones(
		    	(Long)this.getSesionUsuario().get("usuario"));
       this.calificacionesPendientes=viajero.recuperarCalificionesPendientes();
       return "succes";
    }
	
	@Action(value="calificarUsuario",
		 results={
		 @Result(name="succes",location="calificar.jsp")})
	public String calificarUsuario(){
	   HttpServletRequest req = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
	   this.setIdCalificado(Long.parseLong(req.getParameter("idCalificado")));
	   this.setIdViaje(Long.parseLong(req.getParameter("idViaje")));
	   this.getOpciones().add("positivo");
	   this.getOpciones().add("negativo");
	   return "succes";
	}
	
	@Action(value="guardarCalificacionAction",
			results={
			@Result(name="succes" ,location="verCalificacionesPendientes",type="redirect")
	        })
	public String guardarCalificacion(){
		Long idCalificador =(Long)this.getSesionUsuario().get("usuario");
		Viaje v= this.viajeDao.recuperar(this.getIdViaje());
		Viajero viajeroCalificado = this.viajeroDao.recuperar(this.getIdCalificado());
		Viajero viajerCalificador = this.viajeroDao.recuperarConCalificaciones(idCalificador);
		Calificacion c =new Calificacion(this.tipoDeCalificacion(),this.getComentario(), viajeroCalificado, viajerCalificador,v);
		this.calificacionDao.persistir(c);
		viajerCalificador.addCalificacion(c);
		this.viajeroDao.actualizar(viajerCalificador);
		return "succes";
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

}
