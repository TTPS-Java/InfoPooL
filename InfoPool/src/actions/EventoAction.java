package actions;

import interfacesDAO.EventoDAO;
import interfacesDAO.LugarDAO;
import interfacesDAO.UsuarioDAO;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import objetos.Evento;

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
			@Result(name="success", location="eventoNuevo.jsp"),
			@Result(name="error", location="verEventos.jsp")})
	@SkipValidation
	public String eventoNuevo() throws Exception {
	session=(SessionMap<String, Object>) ActionContext.getContext().getSession();
	 if(session.get("esAdmin")!=null && true==(Boolean)session.get("esAdmin")){
		HttpServletRequest req = (HttpServletRequest)ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		if(req.getParameter("idEvento")!=null){
			Long id;
			try {
				id = Long.parseLong(req.getParameter("idEvento"));
			} catch (NumberFormatException e) {
				addActionError(this.getText("evento.error_modificar"));
				verEventos();
				return ERROR;
			}
			if(!eventoDAO.existe(id)) {
				addActionError(this.getText("evento.evento_no_existe"));
				verEventos();
				return ERROR;
			};
			evento = eventoDAO.recuperar(id);
		} 
		return SUCCESS;
		} else {
		 return "index";
		}
	}
	
	@Action(value = "verEvento", results={@Result(name="index", location="Index", type="redirectAction"),
			@Result(name="success", location="verEvento.jsp"),
			@Result(name="error", location="verEventos.jsp")})
	@SkipValidation
	public String verEvento() throws Exception {
	session=(SessionMap<String, Object>) ActionContext.getContext().getSession();
	 if(session.get("esAdmin")!=null && true==(Boolean)session.get("esAdmin")){
		HttpServletRequest req = (HttpServletRequest)ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		if(req.getParameter("idEvento")!=null){
			Long id;
			try {
				id = Long.parseLong(req.getParameter("idEvento"));
			} catch (NumberFormatException e) {
				addActionError(this.getText("evento.error_mostrar"));
				verEventos();
				return ERROR;
			}
			if(!eventoDAO.existe(id)) {
				addActionError(this.getText("evento.evento_no_existe"));
				verEventos();
				return ERROR;
			};
			evento = eventoDAO.recuperar(id);
		}
		return SUCCESS;
		} else {
			return "index";
		}
	}
	
	@Action(value = "borrarEvento", results = {
			@Result(name = "success", location = "verEventos.jsp"),
			@Result(name = "index", location = "Index", type = "redirectAction"),
			@Result(name = "error", location = "verEventos.jsp")
	})
	@SkipValidation
	public String borrarEvento() throws Exception {
		session=(SessionMap<String, Object>) ActionContext.getContext().getSession();
		if( session.get("esAdmin")!=null && true==(Boolean)session.get("esAdmin")){
			HttpServletRequest req = (HttpServletRequest)ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			if(req.getParameter("idEvento")==null){
				addActionError(this.getText("evento.error_borrar"));
				verEventos();
				return ERROR;
			}
			Long id;
			try {
				id = Long.parseLong(req.getParameter("idEvento"));
			} catch (NumberFormatException e) {
				addActionError(this.getText("evento.error_borrar"));
				verEventos();
				return ERROR;
			}
			if(!eventoDAO.existe(id)) {
				addActionError(this.getText("evento.evento_no_existe"));
				verEventos();
				return ERROR;
			};
			if(eventoDAO.estaEnUnViaje(id)) {
				addActionError(this.getText("evento.evento_con_viajes"));
				verEventos();
				return ERROR;
			};
			eventoDAO.borrar(id);
			verEventos();
			return SUCCESS;
		}else{
			return "index";
		}
	}
	
	

	@Action(value = "guardarEvento", results = {
		@Result(name = "success", location = "verEventos", type="redirectAction"),
		@Result(name = "index", location = "Index", type="redirectAction"),
		@Result(name = "input", location = "eventoNuevo.jsp")})
	public String guardarEvento() throws Exception {
		session=(SessionMap<String, Object>) ActionContext.getContext().getSession();
		if( session.get("esAdmin")!=null && true==(Boolean)session.get("esAdmin")){
		  if(evento.getId()==null){
			 lugarDAO.persistir(evento.getLugar());
			 eventoDAO.persistir(evento);
		  }
		  else {
			 lugarDAO.actualizar(evento.getLugar());
			 eventoDAO.actualizar(evento);
						  
		  }
			return SUCCESS;
		}else{
			return "index";
		}
		

	}
	
	@Action(value = "verEventos", results = {
			@Result(name = "success", location = "verEventos.jsp"),
			@Result(name = "index", location = "Index", type="redirectAction")})
	@SkipValidation
	public String verEventos() throws Exception {
		session=(SessionMap<String, Object>) ActionContext.getContext().getSession();
		if( session.get("esAdmin")!=null && true==(Boolean)session.get("esAdmin")){
			eventos =  eventoDAO.recuperarTodos("id");
			return SUCCESS;
		} else {
			return "index";
		}
	}

	@Override
	public Evento getModel() {
		return this.evento;
	}

	@Override
	public void validate() {
		if((evento.getNombre()==null)||(evento.getNombre().equals(""))){
			addFieldError("nombre",this.getText("evento.falta_nombre") );
		}
		if((evento.getDescripcion()==null)||(evento.getDescripcion().equals(""))){
			addFieldError("descripcion", this.getText("evento.falta_descr"));
		}
		if((evento.getLugar().getDescripcion()==null)||(evento.getLugar().getDescripcion().equals(""))){
			addFieldError("lugar.descripcion", this.getText("evento.falta_lugar"));
		}
		if((evento.getFecha()==null)||(evento.getFecha().equals(""))){
			addFieldError("evento.fecha", this.getText("evento.falta_fecha"));
		}
		if(evento.getDuracionDias()==0){
			addFieldError("duracionDias", this.getText("evento.falta_duracion"));
		} 
		if((evento.getHora()==null)||(evento.getHora().equals(""))||!evento.getHora().matches("^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$")){
			addFieldError("hora", this.getText("evento.falta_hora"));
		}
	}

	
}