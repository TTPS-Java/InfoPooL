package actions;
import interfacesDAO.CalificacionDAO;
import interfacesDAO.DiaSemanaDAO;
import interfacesDAO.EventoDAO;
import interfacesDAO.LugarDAO;
import interfacesDAO.SolicitudDAO;
import interfacesDAO.ViajeDAO;
import interfacesDAO.ViajePeriodicoDAO;
import interfacesDAO.ViajePuntualDAO;
import interfacesDAO.ViajeroDAO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
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
import objetos.Lugar;
import objetos.Solicitud;
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
	@Autowired
	private ViajeDAO viajeDao;
	@Autowired
	private SolicitudDAO solicitudDao;
	@Autowired
	private ViajeroDAO viajeroDao;
	@Autowired
	private CalificacionDAO calificacionDao;
	
	private List<String> tiposDeViajes;
	private List<Solicitud> solicitudes;
	private Viaje viaje = new Viaje();
	private List<Evento> eventos;
	private List<String> dias;
	private String misDias;
	private Long idElegido;
	private SessionMap<String,Object> session;
	private String boton="";
	private String coordenadasEventos;
	private String[] defaultDias = new String[7];
	private String mensajeError;
	private ArrayList<Viaje> misViajes;
	private SessionMap<String, Object> sesion;
	private List<Viajero> viajerosEnViaje;
	
	
	
	
	public List<Solicitud> getSolicitudes() {
		return solicitudes;
	}
	public void setSolicitudes(List<Solicitud> solicitudes) {
		this.solicitudes = solicitudes;
	}
	public List<Viajero> getViajerosEnViaje() {
		return viajerosEnViaje;
	}
	public void setViajerosEnViaje(List<Viajero> viajerosEnViaje) {
		this.viajerosEnViaje = viajerosEnViaje;
	}
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
	public Long getIdElegido() {
		return idElegido;
	}
	public void setIdElegido(Long idElegido) {
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
		if(this.boton.equals("")){
			return this.getText("nuevorecorrido.viaje_periodico");
		}else{
			return this.boton;
		}
	}
	public String[] getDefaultDia(){
		return this.defaultDias ;
				/*new String [] {"lunes", "martes"};*/
	}
	public void setDefautDia(String[] dias){
		this.defaultDias=dias;
	}
	public String getMensajeError() {
		return mensajeError;
	}
	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
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
			                                           @Result(name="viajeModificado", location="mensajeModificarViaje",type="redirectAction"),
			                                           @Result(name = "input", location = "nuevoRecorrido.jsp"),
			                                           @Result(name="index", location="Index", type="redirectAction"),
			                                           @Result(name="cambioTipoViaje", location="nuevoRecorrido.jsp")
	})
	public String recorridoNuevo(){
	session=(SessionMap<String, Object>) ActionContext.getContext().getSession();
	if(session.get("esAdmin")!=null && false==(Boolean)session.get("esAdmin")){
	  if(viaje.getId()!=null){
		  Lugar ld =this.lugarDAO.recuperar(this.viaje.getDesde().getId());
		  ld.setDescripcion(this.viaje.getDesde().getDescripcion());
		  ld.setLatitud(this.viaje.getDesde().getLatitud());
		  ld.setLongitud(this.viaje.getDesde().getLongitud());
		  
		  Lugar lh =this.lugarDAO.recuperar(this.viaje.getHasta().getId());
		  lh.setDescripcion(this.viaje.getHasta().getDescripcion());
		  lh.setLatitud(this.viaje.getHasta().getLatitud());
		  lh.setLongitud(this.viaje.getHasta().getLongitud());
		  this.lugarDAO.actualizar(ld);
		  this.lugarDAO.actualizar(lh);
		  ViajePeriodico vp = this.viajePeriodicoDAO.recuperarConDias(viaje.getId());
		  if(vp!=null && !misDias.equals("") && misDias!=null){
			 ArrayList<DiaSemana> ds= new ArrayList<DiaSemana>();
			 ds.addAll(vp.getDias());
			 ArrayList<DiaSemana> ds2= new ArrayList<DiaSemana>();
		    for(DiaSemana d:ds){
		    	ds2.add(d);
		    }
		    for (DiaSemana di:ds2){
		    	vp.removeDia(di);
		    }
		    this.agregarDiasAViajePeridico(vp);
		    vp.setAsientosLibres(this.viaje.getAsientosLibres());
		    vp.setFecha(this.viaje.getFecha());
		    vp.setHoraPartida(this.viaje.getHoraPartida());
		    vp.setHoraVuelta(this.viaje.getHoraVuelta());
		    if(this.getIdElegido()!=-1){
				Long id= (long)this.getIdElegido();
				Evento eventoElegido = eventoDAO.recuperar(id);
				vp.setEventoAsociado(eventoElegido);
			}
		    this.viajePeriodicoDAO.actualizar(vp);
		  }else if (vp==null && (misDias.equals("") || misDias==null)){
			  ViajePuntual vpun = this.viajePuntualDAO.recuperar(viaje.getId());
			  vpun.setAsientosLibres(this.viaje.getAsientosLibres());
			  vpun.setFecha(this.viaje.getFecha());
			  vpun.setHoraPartida(this.viaje.getHoraPartida());
			  vpun.setHoraVuelta(this.viaje.getHoraVuelta());
			    if(this.getIdElegido()!=-1){
					Long id= (long)this.getIdElegido();
					Evento eventoElegido = eventoDAO.recuperar(id);
					vpun.setEventoAsociado(eventoElegido);
				}
			    this.viajePuntualDAO.actualizar(vpun);
		  
		  }else{
				   addFieldError("boton",this.getText("recorridoaction.cambiotipoviaje"));
				   this.modificarViaje(this.viaje.getId());
		           return "cambioTipoViaje";
		  }
		  return "viajeModificado";
	  }else{
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
	   }
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
	
	
	public String modificarViaje(Long idV){
		session=(SessionMap<String, Object>) ActionContext.getContext().getSession();
		if(session.get("esAdmin")!=null && false==(Boolean)session.get("esAdmin")){
		  this.inicializar();
		  ViajePeriodico vp = this.viajePeriodicoDAO.recuperarConDias(idV);
		  if(vp!=null){
			  int i=0;
			  for(DiaSemana ds:vp.getDias()){
				  String nombreDia = "dias."+ds.getNombre().toLowerCase();
				  this.defaultDias[i]=this.getText(nombreDia);
				  i++;	   
			  }
			  Evento e=vp.getEventoAsociado();
			  idElegido=(long)-1;
			  if(e!=null){
				 this.idElegido=e.getId();
			  } 
			  this.viaje = this.viajeDao.recuperar(vp.getId());	
			  this.boton= this.getText("nuevorecorrido.viaje_periodico");
		  }else{
			  ViajePuntual vpu = this.viajePuntualDAO.recuperar(idV);
			  Evento e=vpu.getEventoAsociado();
			  idElegido=(long)-1;
			  if(e!=null){
				 this.idElegido=e.getId();
			  } 
			  this.viaje=this.viajeDao.recuperar(vpu.getId());
			  this.boton= this.getText("nuevorecorrido.viaje_puntual");
		  }  
		  return "success";
		  
		}else{
			return "index";
		}
	}
	
	
	
	@Action(value = "modificarViaje", results={@Result(name="success", location="nuevoRecorrido.jsp"),
			@Result(name="index", location="Index", type="redirectAction")})
	@SkipValidation
	public String mViaje(){
		 HttpServletRequest req = (HttpServletRequest) ActionContext
					.getContext().get(ServletActionContext.HTTP_REQUEST);
		    Long idu =(Long)this.getSesionUsuario().get("usuario");
			Viajero vs=null;
			if(idu!=null){
			   vs= this.viajeroDao.recuperar((Long)this.getSesionUsuario().get("usuario"));
			}
			if(vs!=null && req.getParameter("idViaje")!=null){ 
		       return this.modificarViaje(Long.parseLong(req.getParameter("idViaje")));
			}else{
				return "index";
			}
	}
	
	
	
	
	@Action(value="verMisViajes",results={
			@Result(name="succes",location="verMisRecorridos.jsp"),	
			@Result(name="index", location="Index", type="redirectAction")})
	@SkipValidation
	public String verMisViajes(){
		Long idu =(Long)this.getSesionUsuario().get("usuario");
		Viajero vs=null;
		if(idu!=null){
		   vs= this.viajeroDao.recuperar((Long)this.getSesionUsuario().get("usuario"));
		}
		if(vs!=null){
			Long idViajero = (Long) this.getSesionUsuario().get("usuario");
			Viajero v=this.viajeroDao.recuperar(idViajero);
			this.misViajes=(ArrayList<Viaje>)viajeDao.recuperarPorConductor("id",v);
			return "succes";
		}else{
			return  "index";
		}
	}
	
	
	@Action(value="verViajesEstoy",results={
			@Result(name="succes",location="verViajesEstoy.jsp"),	
			@Result(name="index", location="Index", type="redirectAction")})
	@SkipValidation
	public String verViajesEstoy(){
		Long idu =(Long)this.getSesionUsuario().get("usuario");
		Viajero vs=null;
		if(idu!=null){
		   vs= this.viajeroDao.recuperar((Long)this.getSesionUsuario().get("usuario"));
		}
		if(vs!=null){
	     ArrayList<Solicitud> solicit=(ArrayList<Solicitud>) this.solicitudDao.recuperarPorSolicitante(vs);
	     System.out.println("tama�o solicitudes:"+solicit.size());
	     this.solicitudes = new ArrayList<Solicitud>();
	     for(Solicitud s:solicit){
	    	if(s.getEstado().getNombre().equals("Aceptada")) 
	    	 this.solicitudes.add(s);
	     }
			return "succes";
		}else{
			return  "index";
		}
	}
	
	
	@Action(value = "borrarViajeEstoy", results={@Result(name="success", location="mensajeSalirViajeEstoy",type="redirectAction"),
			@Result(name="index", location="Index", type="redirectAction")})
	@SkipValidation
	public String borrarViajeEstoy(){
	   HttpServletRequest req = (HttpServletRequest) ActionContext
					.getContext().get(ServletActionContext.HTTP_REQUEST);
	   Long idu =(Long)this.getSesionUsuario().get("usuario");
	   Viajero vs=null;
	   if(idu!=null){
			   vs= this.viajeroDao.recuperarConViajesEstoy((Long)this.getSesionUsuario().get("usuario"));
	   }
	   if(vs!=null && req.getParameter("idSolicitud")!=null){
		   Long ids =Long.parseLong(req.getParameter("idSolicitud"));
         Solicitud solicitudABorrar = this.solicitudDao.recuperar(ids);
         if(solicitudABorrar==null || vs==null){ 
    	   return "index";
         }else{	
        	Viaje v = this.viajeDao.recuperarConPasajeros(solicitudABorrar.getViaje().getId());
        	v.getPasajeros().remove(vs);
        	v.setAsientosLibres(v.getAsientosLibres()+solicitudABorrar.getCantidadAsientos());
        	this.viajeDao.actualizar(v);
        	vs = this.viajeroDAO.recuperarConViajesEstoy(vs.getId());
     	    vs.getViajesEstoy().remove(v);
     	    this.viajeroDao.actualizar(vs);
    	    this.solicitudDao.borrar(solicitudABorrar);
    	    return "success";
          }
	   }else{
		   return "index";
	   }
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
	@Action(value = "borrarViaje", results={@Result(name="success", location="mensajeBorrarViaje",type="redirectAction"),
			@Result(name="index", location="Index", type="redirectAction")})
	@SkipValidation
	public String borrarViaje(){
		 HttpServletRequest req = (HttpServletRequest) ActionContext
					.getContext().get(ServletActionContext.HTTP_REQUEST);
	 Long idu =(Long)this.getSesionUsuario().get("usuario");
	Viajero vs=null;
	if(idu!=null){
			   vs= this.viajeroDao.recuperar((Long)this.getSesionUsuario().get("usuario"));
	}
	if(vs!=null && req.getParameter("idViaje")!=null){	
		
		
		session=(SessionMap<String, Object>) ActionContext.getContext().getSession();
		
		Viajero via = this.viajeroDao.recuperar((Long)session.get("usuario"));
        Viaje viajeABorrar = this.viajeDao.recuperarConPasajeros(Long.parseLong(req.getParameter("idViaje")));
       if(viajeABorrar==null || via==null){ 
    	  return "Index";
       }else{	
    	   viajeABorrar.setVisible(false);
    	   this.viajeDao.actualizar(viajeABorrar);
    	   return "success";
    	/*   
    	ArrayList<Viajero> viajerosDelSistema =(ArrayList<Viajero>)viajeroDao.recuperarTodos("id");
         //Borrando todas las solicitudes al viaje
    	ArrayList<Solicitud> solicitudesAViaje = (ArrayList<Solicitud>) this.solicitudDao.recuperarPorViaje("id", viajeABorrar);
    	for(Solicitud s:solicitudesAViaje){
    		   this.solicitudDao.borrar(s);
    	}
    	//sacarle a los viajeros el viaje a borrar
    	for(Viajero vi:viajerosDelSistema){
    			   Viajero viaj = this.viajeroDao.recuperarConViajesEstoy(vi.getId());
    			   viaj.getViajesEstoy().remove(viajeABorrar);
    			   this.viajeroDao.actualizar(viaj);
    	}
    	//borrar sus calificaciones si es que tiene
    	ArrayList<Calificacion> calificaciones=calificacionDao.recuperarCalificacionesPorViaje(viajeABorrar.getId());
		   for (Calificacion c:calificaciones){
			   Viajero v=viajeroDao.recuperarConCalificaciones(c.getAutor().getId());
			   v.getCalificaciones().remove(c);;
			   viajeroDao.actualizar(v);
			   this.calificacionDao.borrar(c.getId());
		   }
    	
        this.viajeDao.borrar(viajeABorrar.getId()); */  
    	
     // } 
    }     
	}else{
		return "index";
	}
}
	
	
	
	@Action(value = "usuariosEnViaje", results={@Result(name="success", location="viajerosEnViaje.jsp"),
			@Result(name="index", location="Index", type="redirectAction")})
	@SkipValidation
	public String usuariosEnViaje(){
		 HttpServletRequest req = (HttpServletRequest) ActionContext
					.getContext().get(ServletActionContext.HTTP_REQUEST);
	 Long idu =(Long)this.getSesionUsuario().get("usuario");
	Viajero vs=null;
	if(idu!=null){
			   vs= this.viajeroDao.recuperar((Long)this.getSesionUsuario().get("usuario"));
	}
	if(vs!=null && req.getParameter("idViaje")!=null && !req.getParameter("idViaje").equals("")){
		
		Viaje v=this.viajeDao.recuperarConPasajeros(Long.parseLong(req.getParameter("idViaje")));
		this.setViajerosEnViaje(v.getPasajeros());
		return"success";
	 }else
	 {
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
		   addFieldError("viaje.fecha", this.getText("recorridoaction.fechaerror"));
		   error=true;
	   }
	   if((viaje.getHoraPartida()==null) || viaje.getHoraPartida().equals("") || !viaje.getHoraPartida().matches("^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$")){
		   addFieldError("viaje.horaPartida", this.getText("recorridoaction.horaerror"));
		   error=true;
	   }
	   
	   if((viaje.getAsientosLibres()==0)){
		   addFieldError("viaje.asientosLibres", this.getText("recorridoaction.asientoslibreserror"));
		   error=true;
	   }
	   if((viaje.getDesde().getDescripcion()==null) || viaje.getDesde().getDescripcion().equals("")){
		   addFieldError("viaje.desde.descripcion",this.getText("recorridoaction.lugarorigenerror"));
		   error=true;
	   }
	   if((viaje.getHasta().getDescripcion()==null) || viaje.getHasta().getDescripcion().equals("")){
		   addFieldError("viaje.hasta.descripcion",this.getText("recorridoaction.lugardestinoerror"));
		   error=true;
	   }
	   
	   if((boton.equals("viaje_periodico") || boton.equals("trip_newspaper") ) && ((misDias==null) || misDias.equals(""))){
		   addFieldError("misDias",this.getText("recorridoaction.diasviajeperiodicoerror"));
		   error=true;
	   }
	   if((viaje.getHasta().getLatitud()==0)){
		   addFieldError("idElegido",this.getText("recorridoaction.pinchelugardestinoerror"));
		   error=true;
	   }
	   if((viaje.getDesde().getLatitud()==0)){
		   addFieldError("idElegido",this.getText("recorridoaction.pinchelugarorigenerror"));
		   error=true;
	   }
	   if(error){this.inicializar();}
	}
	
	
	
	

}
