package actions;

import org.apache.struts2.convention.annotation.Action;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.convention.annotation.Result;

public class InformeAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mensaje;
	private String urlVolver;
	
	@Action(value = "mensajeBorrarViaje", results={@Result(name="success", location="informe.jsp")
			})
	public  String borrarViaje(){
		this.setMensaje(this.getText("informe.borrarviaje"));
		this.setUrlVolver("verMisViajes");
		return "success";
	}
	
  @Action(value = "mensajeModificarViaje", results={@Result(name="success", location="informe.jsp")})
   public  String modificarViaje(){
   this.setMensaje(this.getText("informe.modificarviaje"));
   this.setUrlVolver("verMisViajes");
   return "success";
  }
	
  
  @Action(value = "MensajeBorrarViajes", results={@Result(name="success", location="informe.jsp")})
  public  String borrarViajes(){
  this.setMensaje(this.getText("informe.borrarviajesviajero"));
  this.setUrlVolver("verViajeros");
  return "success";
 }
  
  @Action(value = "Mensajebloquear", results={@Result(name="success", location="informe.jsp")})
  public  String mensajebloquear(){
  this.setMensaje(this.getText("informe.bloquearusuario"));
  this.setUrlVolver("verViajeros");
  return "success";
 }
  @Action(value = "MensajeDesbloquear", results={@Result(name="success", location="informe.jsp")})
  public  String mensajeDesbloquear(){
  this.setMensaje(this.getText("informe.desbloquearusuario"));
  this.setUrlVolver("verViajeros");
  return "success";
 }
  
  @Action(value = "MensajeCalificar", results={@Result(name="success", location="informe.jsp")})
  public  String mensajeCalificar(){
  this.setMensaje(this.getText("informe.calificacionguardada"));
  this.setUrlVolver("verCalificacionesPendientes");
  return "success";
 }
  @Action(value = "mensajeSalirViajeEstoy", results={@Result(name="success", location="informe.jsp")})
  public  String mensajeSalirViajeEstoy(){
  this.setMensaje(this.getText("informe.salirviajeestoy"));
  this.setUrlVolver("verViajesEstoy");
  return "success";
 }
	
  
	
	
	
	
	
	
	
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public String getUrlVolver() {
		return urlVolver;
	}
	public void setUrlVolver(String urlVolver) {
		this.urlVolver = urlVolver;
	}
	
	

}
