package actions;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

public class InformeAction {
	private String mensaje;
	private String urlVolver;
	
	@Action(value = "mensajeBorrarViaje", results={@Result(name="success", location="informe.jsp")
			})
	public  String borrarViaje(){
		this.setMensaje("Su viaje a sido borrado correctamente");
		this.setUrlVolver("verMisViajes");
		return "success";
	}
	
  @Action(value = "mensajeModificarViaje", results={@Result(name="success", location="informe.jsp")})
   public  String modificarViaje(){
   this.setMensaje("Su viaje a sido modificado correctamente");
   this.setUrlVolver("verMisViajes");
   return "success";
  }
	
  
  @Action(value = "MensajeBorrarViajes", results={@Result(name="success", location="informe.jsp")})
  public  String borrarViajes(){
  this.setMensaje("Los viajes se han borrado correctamente");
  this.setUrlVolver("verViajeros");
  return "success";
 }
  
  @Action(value = "Mensajebloquear", results={@Result(name="success", location="informe.jsp")})
  public  String mensajebloquear(){
  this.setMensaje("El usuario se ha bloqueado correctamente");
  this.setUrlVolver("verViajeros");
  return "success";
 }
  @Action(value = "MensajeDesbloquear", results={@Result(name="success", location="informe.jsp")})
  public  String mensajeDesbloquear(){
  this.setMensaje("El usuario se ha desbloqueado correctamente");
  this.setUrlVolver("verViajeros");
  return "success";
 }
  
  @Action(value = "MensajeCalificar", results={@Result(name="success", location="informe.jsp")})
  public  String mensajeCalificar(){
  this.setMensaje("Se ha guardado la calificacion correctamente");
  this.setUrlVolver("verCalificacionesPendientes");
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
