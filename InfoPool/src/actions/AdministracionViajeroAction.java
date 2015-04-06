package actions;

import interfacesDAO.CalificacionDAO;
import interfacesDAO.DenunciaDAO;
import interfacesDAO.SolicitudDAO;
import interfacesDAO.ViajeDAO;
import interfacesDAO.ViajeroDAO;

import java.util.ArrayList;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import objetos.Calificacion;
import objetos.Denuncia;
import objetos.Solicitud;
import objetos.Viaje;
import objetos.Viajero;
@Controller
public class AdministracionViajeroAction {
	private ArrayList<Denuncia> denunciasDeUsuario;
	private Viajero viajero;
	
	@Autowired
	private ViajeroDAO viajeroDao;
	@Autowired
	private DenunciaDAO denunciaDao;
	@Autowired
	private ViajeDAO viajeDao;
	@Autowired
	private CalificacionDAO calificacionDao;
	@Autowired
	private SolicitudDAO solicitudDao;
	
   @Action(value="detalleViajero",results={
		   @Result(name="succes",location="denunciasAViajero.jsp")
   })
    public String detalleViajero(){
    	/* crear jsp detalle viajero que muestra sus denuncias y la opcion
    	 * bloquear usuario y borrar sus viajes
    	 */
    	HttpServletRequest req = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
        this.viajero=this.viajeroDao.recuperar(Long.parseLong(req.getParameter("id")));
    	this.denunciasDeUsuario=(ArrayList<Denuncia>) this.denunciaDao.denunciasAViajero(viajero.getId());
    	return"succes";
    }
	
 
   public void setearEstadoViajero(boolean estado){
	   HttpServletRequest req = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);
       this.viajero=this.viajeroDao.recuperar(Long.parseLong(req.getParameter("id")));
       this.viajero.setEstaActivo(estado);
       this.viajeroDao.actualizar(this.viajero);
	   
   }
	
   @Action(value="bloquearViajero",results={
		   @Result(name="succes",location="denunciasAViajero.jsp")})
	  public String bloquearViajero(){
	     this.setearEstadoViajero(false);
	     return "succes";
      }
   
       @Action(value="desbloquearViajero",results={
		   @Result(name="succes",location="denunciasAViajero.jsp")})
	   public String desbloquearViajero(){
	     this.setearEstadoViajero(true);
	     return "succes";
        }
	
       @Action(value="borrarViajesDeViajero",results={
    		   @Result(name="succes",location="denunciasAViajero.jsp")})
       public String borarViajesViajero(){
    	   HttpServletRequest req = (HttpServletRequest) ActionContext
   				.getContext().get(ServletActionContext.HTTP_REQUEST);
          this.viajero=this.viajeroDao.recuperar(Long.parseLong(req.getParameter("id")));
    	   ArrayList<Viaje> viajes=(ArrayList<Viaje>) this.viajeDao.recuperarPorConductor("id",this.viajero);
    	   ArrayList<Viajero> viajerosDelSistema =(ArrayList<Viajero>)viajeroDao.recuperarTodos("id");
    	   
    	   //Borrando todas las solicitudes a los viajes del denunciado
    	   LinkedList<Solicitud> solicitudesADenunciado = (LinkedList<Solicitud>) this.solicitudDao.recuperarPorConductor("id", this.viajero);
    	   for(Solicitud s:solicitudesADenunciado){
    		   this.solicitudDao.borrar(s);
    	   }
    	   
    	   for(Viaje viaje:viajes){
    		   for(Viajero vi:viajerosDelSistema){
    			   Viajero viaj = this.viajeroDao.recuperarConViajesEstoy(vi.getId());
    			   viaj.removeViajeEstoy(viaje);
    			   this.viajeroDao.actualizar(viaj);
    		   }
    		   ArrayList<Calificacion> calificaciones=calificacionDao.recuperarCalificacionesPorViaje(viaje.getId());
    		   for (Calificacion c:calificaciones){
    			   Viajero v=viajeroDao.recuperarConCalificaciones(c.getAutor().getId());
    			   v.removeCalificacion(c);
    			   viajeroDao.actualizar(v);
    			   this.calificacionDao.borrar(c.getId());
    		   }
    		   this.viajeDao.borrar(viaje.getId());
    		   
    	   }
    	   
    	   return "succes";
       }
	
	
	
	
	
	
	
	
	
	
	
	public ArrayList<Denuncia> getDenunciasDeUsuario() {
		return denunciasDeUsuario;
	}
	public void setDenunciasDeUsuario(ArrayList<Denuncia> denunciasDeUsuario) {
		this.denunciasDeUsuario = denunciasDeUsuario;
	}
	public Viajero getViajero() {
		return viajero;
	}
	public void setViajero(Viajero viajero) {
		this.viajero = viajero;
	}
	
	
	
	

}
