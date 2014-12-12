package actions;

import interfacesDAO.UsuarioDAO;

import java.util.Collection;
import java.util.Map;

import objetos.Administrador;
import objetos.Evento;
import objetos.Usuario;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

import DAOhiberJPA.FactoryDAO;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
public class EventoAction extends ActionSupport implements ModelDriven<Evento>, SessionAware{
	private static final long serialVersionUID = 1L;
	private Evento evento = new Evento();
	private Collection<Evento> eventos;
	private SessionMap<String, Object> session;
	@Override
	public Evento getModel() {
		return evento;
	}
	@Override
	public void setSession(Map<String, Object> map) {
		session = (SessionMap<String, Object>) map;
	}
	public Collection<Evento> getEventos() {
		return eventos;
	}
	
	
	@Action(value = "eventoNuevo")
	@SkipValidation
	public String eventoNuevo() throws Exception {
		return SUCCESS;
	}

	@Action(value = "guardarEvento", results = {
		@Result(name = "success", location = "verEventos", type="redirectAction"),
		@Result(name = "input", location = "eventoNuevo.jsp")})
	@RequiredStringValidator(fieldName = "texto", message = "Debe ingresar un Evento")
	public String guardarEvento() throws Exception {
		Usuario us = (Usuario) session.get("usuario");
		UsuarioDAO dao = FactoryDAO.getUsuarioDAO();
		us = dao.recuperar(us.getId());
		if(us instanceof Administrador){
		  if(evento.getId()==null)
			FactoryDAO.getEventoDAO().persistir(evento);
		  else
			FactoryDAO.getEventoDAO().actualizar(evento);
			return SUCCESS;
		}else{
			return INPUT;
		}
		

	}
	
	@Action(value = "verEventos")
	@SkipValidation
	public String verEventos() throws Exception {
		eventos =  FactoryDAO.getEventoDAO().recuperarTodos("id");
		return SUCCESS;
	}


	
}