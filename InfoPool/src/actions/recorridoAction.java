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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;

import objetos.DiaSemana;
import objetos.Evento;
import objetos.Viaje;
import objetos.ViajePeriodico;
import objetos.ViajePuntual;
import objetos.Viajero;
@Controller
public class recorridoAction {
	
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
	
	private Viaje viaje;
	private List<Evento> eventos;
	private List<String> dias;
	private String misDias;
	private int idElegido;
	private SessionMap<String,Object> session;
	
	private String coordenadasEventos;
	
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
		dias= new ArrayList<String>();
		eventos= new ArrayList<Evento>();
		dias.add("lunes");
		dias.add("martes");
		dias.add("miercoles");
		dias.add("jueves");
		dias.add("viernes");
		dias.add("sabado");
		dias.add("domingo");
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
	
	
	
	
	
	
	@Action(value = "guardarRecorridoAction", results={@Result(name="success", location="nuevoRecorrido.jsp")})
	public String recorridoNuevo(){
		System.out.println("dias:"+misDias+"id elegido"+idElegido);
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
	
	@Action(value = "tablaDeRecorridosAction", results={@Result(name="success", location="verRecorridos.jsp")})
	public String tablaDeRecorridosAction(){
		return "success";
	}
	
	
	@Action(value = "recorridoNuevoAction", results={@Result(name="success", location="nuevoRecorrido.jsp")})
	public String formRecorrido(){
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
		System.out.println(this.coordenadasEventos);
		return "success";
	}
	
	
	public ViajeroDAO getViajeroDAO() {
		return viajeroDAO;
	}
	
	public void agregarDiasAViajePeridico(ViajePeriodico vp){
		String[] diasElegidos= misDias.split(",");
		ArrayList<DiaSemana> diasSemana= (ArrayList<DiaSemana>) diaSemanaDAO.recuperarTodos("id");
		for(int i=0;i<diasElegidos.length;i++){
			String dia=diasElegidos[i].toUpperCase();
			dia=dia.trim();
			for (DiaSemana ds:diasSemana){
				String diaSemana =ds.getNombre().toUpperCase();
				if(dia.equals(diaSemana)){
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
	
	
	
	
	

}
