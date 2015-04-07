package actions;

import interfacesDAO.ViajeDAO;
import interfacesDAO.ViajeroDAO;

import java.util.ArrayList;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.dispatcher.SessionMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import objetos.Viaje;
import objetos.Viajero;

@Controller
public class MisRecorridosAction {
	
	private ArrayList<Viaje> misViajes;
	@Autowired
	private ViajeDAO viajeDao;
	@Autowired
	private ViajeroDAO viajeroDao;
	
	private SessionMap<String, Object> sesion;
	
	
	@Action(value="verMisViajes",results={
		@Result(name="succes",location="verMisRecorridos.jsp")	})
	public String verMisViajes(){
		Long idViajero = (Long) this.getSesionUsuario().get("usuario");
		System.out.println("paso a ver");
		Viajero v=this.viajeroDao.recuperar(idViajero);
		this.misViajes=(ArrayList<Viaje>)viajeDao.recuperarPorConductor("id",v);
		System.out.println(misViajes.size());
		return "succes";
	}
	
	
	
	public ArrayList<Viaje> getMisViajes() {
		return misViajes;
	}



	public void setMisViajes(ArrayList<Viaje> misViajes) {
		this.misViajes = misViajes;
	}



	public SessionMap<String,Object> getSesionUsuario(){
	     if(this.sesion == null) {
		    this.sesion=
		    (SessionMap<String, Object>) ActionContext.getContext().getSession();
	     }
	     return this.sesion;
	 }
	
	
	
	
	
	
	
	

}
