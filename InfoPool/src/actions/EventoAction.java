package actions;

import interfacesDAO.EventoDAO;
import interfacesDAO.LugarDAO;
import interfacesDAO.UsuarioDAO;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import objetos.Administrador;
import objetos.Evento;
import objetos.Usuario;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
@Controller
public class EventoAction extends ActionSupport implements ModelDriven<Evento> {
	private static final long serialVersionUID = 1L;


	private Evento evento=new Evento();
	@Autowired
	private EventoDAO eventoDAO;
	@Autowired
	private UsuarioDAO usuarioDAO;
	@Autowired
	private LugarDAO lugarDAO;
	
	private Collection<Evento> eventos;
	private SessionMap<String, Object> session;
	public Collection<Evento> getEventos() {
		return eventos;
	}
	
	public Evento getEvento() {
		return evento;
	}

    
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	@Action(value = "eventoNuevo", results={@Result(name="index", location="Index", type="redirectAction"),
			@Result(name="success", location="eventoNuevo.jsp")})
	@SkipValidation
	public String eventoNuevo() throws Exception {
	session=(SessionMap<String, Object>) ActionContext.getContext().getSession();
	 if(session.get("esAdmin")!=null && true==(Boolean)session.get("esAdmin")){
		HttpServletRequest req = (HttpServletRequest)ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		if(req.getParameter("id")!=null){
			  evento = eventoDAO.recuperar(Long.parseLong(req.getParameter("id")));
			  System.out.println(evento);
		
		} 
		return SUCCESS;
	 }else
	 {
		 return "index";
	 }
	}

	@Action(value = "guardarEvento", results = {
		@Result(name = "success", location = "verEventos", type="redirectAction"),
		@Result(name = "index", location = "Index", type="redirectAction"),
		@Result(name = "input", location = "eventoNuevo.jsp")})
	public String guardarEvento() throws Exception {
		session=(SessionMap<String, Object>) ActionContext.getContext().getSession();
		Usuario us = (Usuario) session.get("usuario");
		us = usuarioDAO.recuperar(us.getId());
		if( session.get("esAdmin")!=null && true==(Boolean)session.get("esAdmin")){
		  if(evento.getId()==null){
			 lugarDAO.persistir(evento.getLugar());
			 eventoDAO.persistir(evento);
		  }
		  else {
			 eventoDAO.actualizar(evento);
			 lugarDAO.persistir(evento.getLugar());			  
		  }
			return SUCCESS;
		}else{
			return "index";
		}
		

	}
	
	@Action(value = "verEventos")
	@SkipValidation
	public String verEventos() throws Exception {
		eventos =  eventoDAO.recuperarTodos("id");
		return SUCCESS;
	}

	@Override
	public Evento getModel() {
		// TODO Auto-generated method stub
		return this.evento;
	}


	
}