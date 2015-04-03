package actions;

import interfacesDAO.DiaSemanaDAO;
import interfacesDAO.EventoDAO;
import interfacesDAO.LugarDAO;
import interfacesDAO.ViajePeriodicoDAO;
import interfacesDAO.ViajePuntualDAO;
import interfacesDAO.ViajeroDAO;













import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import objetos.DiaSemana;
import objetos.Evento;
import objetos.Viaje;
import objetos.ViajePeriodico;
import objetos.ViajePuntual;
import objetos.Viajero;
@Controller
public class recorridoAction extends ActionSupport {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private EventoDAO eventoDAO;
	@Autowired
	private ViajePeriodicoDAO viajePeriodicoDAO;
	@Autowired
	private ViajePuntualDAO viajePuntualDAO;
	@Autowired 
	private ViajeroDAO viajeroDAO;
	@Autowired
	private LugarDAO lugarDAO;
	@Autowired
	private DiaSemanaDAO diaSemanaDAO;
	
	private List<String> tiposDeViajes;
	private Viaje viaje;
	private List<Evento> eventos;
	private List<String> dias;
	private String misDias;
	private int idElegido;
	private SessionMap<String,Object> session;
	private String boton;
	private String coordenadasEventos;
	
	public List<String> getTiposDeViajes() {
		return tiposDeViajes;
	}
	public void setTiposDeViajes(List<String> tiposDeViajes) {
		this.tiposDeViajes = tiposDeViajes;
	}
	public String getBoton() {
		return boton;
	}
	public void setBoton(String boton) {
		this.boton = boton;
	}

	
	
	public String getCoordenadasEventos() {
		return coordenadasEventos;
	}
	public void setcoordenadasEventos(String dato) {
		this.coordenadasEventos = dato;
	}
	public int getIdElegido() {
		return idElegido;
	}
	public void setIdElegido(int idElegido) {
		this.idElegido = idElegido;
	}
	public String getMisDias() {
		return misDias;
	}
	public void setMisDias(String misDias) {
		this.misDias = misDias;
	}
	public recorridoAction(){
		
		
	}
	public String getDefaultTipoDeViaje(){
		return this.getText("nuevorecorrido.viaje_periodico");
	}
	public String[] getDefaultDia(){
		return new String [] {"lunes", "martes"};
	}
	
	public DiaSemanaDAO getDiaSemanaDAO() {
		return diaSemanaDAO;
	}
	public void setDiaSemanaDAO(DiaSemanaDAO diaSemanaDAO) {
		this.diaSemanaDAO = diaSemanaDAO;
	}
	public Collection<String> getDias() {
		return dias;
	}
	public void setDias(List<String> dias) {
		this.dias = dias;
	}
	
	
	
	
	
	
	@Action(value = "guardarRecorridoAction", results={@Result(name="success", location="recorridoGuardado.jsp"),
			                                           @Result(name = "input", location = "nuevoRecorrido.jsp"),
			                                           @Result(name="index", location="Index", type="redirectAction")})
	public String recorridoNuevo(){
	session=(SessionMap<String, Object>) ActionContext.getContext().getSession();
	if(session.get("esAdmin")!=null && false==(Boolean)session.get("esAdmin")){
		lugarDAO.persistir(viaje.getDesde());
		lugarDAO.persistir(viaje.getHasta());
		if(this.getIdElegido()!=-1){
			Long id= (long)this.getIdElegido();
			Evento eventoElegido = eventoDAO.recuperar(id);
			viaje.setEventoAsociado(eventoElegido);
		}
		session=(SessionMap<String, Object>) ActionContext.getContext().getSession();
		Long idUs = (long)session.get("usuario");
		Viajero v = viajeroDAO.recuperar(idUs);
		if(!misDias.equals("") && misDias!=null){
			ViajePeriodico vp = new ViajePeriodico(viaje.getHoraPartida(),
                    viaje.getHoraVuelta(),viaje.getFecha(),
                    viaje.getAsientosLibres(),viaje.getDesde(), 
                    viaje.getHasta(),v,viaje.getEventoAsociado());
			        viajePeriodicoDAO.persistir(vp);
			        this.agregarDiasAViajePeridico(vp);
				    viajePeriodicoDAO.actualizar(vp);
		}else
		{
			 ViajePuntual vp = new ViajePuntual(viaje.getHoraPartida(),
                     viaje.getHoraVuelta(),viaje.getFecha(),
                     viaje.getAsientosLibres(),viaje.getDesde(), 
                     viaje.getHasta(),v,viaje.getEventoAsociado());
            viajePuntualDAO.persistir(vp);	
		}
		return "success";
	 }else
	 {
		 return "index";
	 }
	}
	
	
	
	
	@Action(value = "recorridoNuevoAction", results={@Result(name="success", location="nuevoRecorrido.jsp"),
			@Result(name="index", location="Index", type="redirectAction")})
	@SkipValidation
	public String formRecorrido(){
		session=(SessionMap<String, Object>) ActionContext.getContext().getSession();
		if(session.get("esAdmin")!=null && false==(Boolean)session.get("esAdmin")){
		  this.inicializar();
		  return "success";
		}else{
			return "index";
		}
	}
	
	
	public void inicializar(){
		dias= new ArrayList<String>();
		eventos= new ArrayList<Evento>();
		tiposDeViajes = new ArrayList<String>();
		tiposDeViajes.add(this.getText("nuevorecorrido.viaje_periodico"));
		tiposDeViajes.add(this.getText("nuevorecorrido.viaje_puntual"));
		dias.add(this.getText("dias.lunes"));dias.add(this.getText("dias.martes"));dias.add(this.getText("dias.miercoles"));dias.add(this.getText("dias.jueves"));dias.add(this.getText("dias.viernes"));dias.add(this.getText("dias.sabado"));dias.add(this.getText("dias.domingo"));
		eventos=eventoDAO.recuperarTodos("id");
		int num = eventos.size();
		this.coordenadasEventos="{\"eventos\":[";
		for(Evento e:eventos){
			this.coordenadasEventos=this.coordenadasEventos+"{\"id\":\""+e.getId()+"\",";
			this.coordenadasEventos=this.coordenadasEventos+"\"longitud\":\""+e.getLugar().getLongitud()+"\",";
			this.coordenadasEventos=this.coordenadasEventos+"\"latitud\":\""+e.getLugar().getLatitud()+"\"";
		
			if(num>1){
				this.coordenadasEventos=this.coordenadasEventos+"},";
			}else{
				this.coordenadasEventos=this.coordenadasEventos+"}";
			}
			num=num-1;
		}
		this.coordenadasEventos=this.coordenadasEventos+"]}";
	}
	
	
	public ViajeroDAO getViajeroDAO() {
		return viajeroDAO;
	}
	
	public void agregarDiasAViajePeridico(ViajePeriodico vp){
		String[] diasElegidos= misDias.split(",");
		ArrayList<DiaSemana> diasSemana= (ArrayList<DiaSemana>) diaSemanaDAO.recuperarTodos("id");
		for(int i=0;i<diasElegidos.length;i++){
			String dia=diasElegidos[i].toLowerCase();
			dia=dia.trim();
			dia="db."+dia;
			String diaPropertie=this.getText(dia);
			diaPropertie=diaPropertie.toUpperCase();
			for (DiaSemana ds:diasSemana){
				String diaSemana =ds.getNombre().toUpperCase();
				if(diaPropertie.equals(diaSemana)){
					vp.addDia(ds);
				}
			}
		}
		
	}
	
	public void setViajeroDAO(ViajeroDAO viajeroDAO) {
		this.viajeroDAO = viajeroDAO;
	}
	public LugarDAO getLugarDAO() {
		return lugarDAO;
	}
	public void setLugarDAO(LugarDAO lugarDAO) {
		this.lugarDAO = lugarDAO;
	}
	
	public EventoDAO getEventoDAO() {
		return eventoDAO;
	}
	public void setEventoDAO(EventoDAO eventoDAO) {
		this.eventoDAO = eventoDAO;
	}
	public ViajePeriodicoDAO getViajePeriodicoDAO() {
		return viajePeriodicoDAO;
	}
	public void setViajePeriodicoDAO(ViajePeriodicoDAO viajePeridicoDao) {
		this.viajePeriodicoDAO = viajePeridicoDao;
	}
	public ViajePuntualDAO getViajePuntualDAO() {
		return viajePuntualDAO;
	}
	public void setViajePuntualDAO(ViajePuntualDAO viajePuntualDao) {
		this.viajePuntualDAO = viajePuntualDao;
	}
	
	
	public Viaje getViaje() {
		return viaje;
	}
	public void setViaje(Viaje viaje) {
		this.viaje = viaje;
	}
	public Collection<Evento> getEventos() {
		return eventos;
	}
	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}
	
	@Override
	public void validate() {
	boolean error=false;
	   if( (viaje.getFecha()==null) || (viaje.getFecha().equals(""))){
		   addFieldError("viaje.fecha", "Debe ingresar una fecha");
		   error=true;
	   }
	   if((viaje.getHoraPartida()==null) || viaje.getHoraPartida().equals("") || !viaje.getHoraPartida().matches("^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$")){
		   addFieldError("viaje.horaPartida", "Debe ingresar una hora de partida");
		   error=true;
	   }
	   if((viaje.getAsientosLibres()==0)){
		   addFieldError("viaje.asientosLibres", "Debe ingresar la cantidad de asientos libres");
		   error=true;
	   }
	   if((viaje.getDesde().getDescripcion()==null) || viaje.getDesde().getDescripcion().equals("")){
		   addFieldError("viaje.desde.descripcion", "Debe ingresar el nombre del lugar de origen");
		   error=true;
	   }
	   if((viaje.getHasta().getDescripcion()==null) || viaje.getHasta().getDescripcion().equals("")){
		   addFieldError("viaje.hasta.descripcion", "Debe ingresar el nombre del lugar de destino");
		   error=true;
	   }
	   
	   if((boton.equals("viaje_periodico")) && ((misDias==null) || misDias.equals(""))){
		   addFieldError("misDias", "en un viaje periodico debe seleccionar al menos un dia");
		   error=true;
	   }
	   if((viaje.getHasta().getLatitud()==0)){
		   addFieldError("idElegido", "debe ingresar el lugar de destino");
		   error=true;
	   }
	   if((viaje.getDesde().getLatitud()==0)){
		   addFieldError("idElegido", "debe ingresar el lugar de origen");
		   error=true;
	   }
	   if(error){this.inicializar();}
	}
	
	
	
	

}
