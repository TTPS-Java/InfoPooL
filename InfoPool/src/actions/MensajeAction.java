package actions;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import interfacesDAO.MensajeDAO;
import interfacesDAO.ViajeroDAO;
import objetos.Evento;
import objetos.Mensaje;
import objetos.Viajero;

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

@SuppressWarnings("serial")
@Controller
public class MensajeAction extends ActionSupport implements
		ModelDriven<Mensaje> {

	private SessionMap<String, Object> session;
	@Autowired
	private ViajeroDAO viajeroDAO;
	@Autowired
	private MensajeDAO mensajeDAO;
	private Viajero destinatario;
	private Mensaje mensaje = new Mensaje();
	private Collection<Mensaje> mensajes;
	private Collection<Evento> eventos;
	private Collection<Viajero> viajeros;

	public Viajero getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Viajero destinatario) {
		this.destinatario = destinatario;
	}

	public Collection<Mensaje> getMensajes() {
		return mensajes;
	}

	public void setMensajes(Collection<Mensaje> mensajes) {
		this.mensajes = mensajes;
	}

	public Collection<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(Collection<Evento> eventos) {
		this.eventos = eventos;
	}

	public Collection<Viajero> getViajeros() {
		return viajeros;
	}

	public void setViajeros(Collection<Viajero> viajeros) {
		this.viajeros = viajeros;
	}

	@Action(value = "mensajeNuevo", results = {
			@Result(location = "mensajeNuevo.jsp"),
			@Result(name = "index", location = "Index", type = "redirectAction"),
			@Result(name = "input", location = "verMensajes.jsp") })
	@SkipValidation
	public String mensajeNuevo() {
		session = (SessionMap<String, Object>) ActionContext.getContext()
				.getSession();
		if (session.get("esAdmin") != null
				&& false == (Boolean) session.get("esAdmin")) {
			HttpServletRequest req = (HttpServletRequest) ActionContext
					.getContext().get(ServletActionContext.HTTP_REQUEST);
			if (req.getParameter("idDestinatario") != null) {
				Long id;
				try {
					id = Long.parseLong(req.getParameter("idDestinatario"));
				} catch (NumberFormatException e) {
					addActionError(this.getText("mensaje.error_destinatario")); 
					verMensajes();
					return INPUT;
				}
				Long idUsuarioSesion = (Long) session.get("usuario");
				if (idUsuarioSesion == id) {
					addActionError(this.getText("mensaje.error_automensaje"));
					verMensajes();
					return INPUT;
				} else {
					if (viajeroDAO.recuperar(id) == null) {
						addActionError(this.getText("mensaje.mensaje_no_existe"));
						verMensajes();
						return INPUT;
					}
					setDestinatario(viajeroDAO.recuperar(id));
					session.put("destinatario", destinatario);
					return SUCCESS;
				}
			} else {
				return INPUT;
			}
		} else {
			return "index";
		}
	}

	@Action(value = "borrarMensaje", results = {
			@Result(location = "mensajeBorrado.jsp"),
			@Result(name = "index", location = "Index", type = "redirectAction"),
			@Result(name = "input", location = "verMensajes.jsp") })
	@SkipValidation
	public String borrarMensaje() {
		session = (SessionMap<String, Object>) ActionContext.getContext()
				.getSession();
		if (session.get("esAdmin") != null && false == (Boolean) session.get("esAdmin")) {
			HttpServletRequest req = (HttpServletRequest) ActionContext
					.getContext().get(ServletActionContext.HTTP_REQUEST);
			if (req.getParameter("idMensaje") != null) {
				Long id;
				try {
					id = Long.parseLong(req.getParameter("idMensaje"));
				} catch (NumberFormatException e) {
					addActionError(this.getText("mensaje.error_mensaje"));
					verMensajes();
					return INPUT;
				}
				mensajeDAO.borrar(id);
				return SUCCESS;
			} else {
				addActionError(this.getText("mensaje.error_mensaje")); 
				verMensajes();
				return INPUT;
			}
		} else {
			return "index";
		}
	}

	@Action(value = "guardarMensaje", results = {
			@Result(location = "mensajeEnviado.jsp"),
			@Result(name = "index", location = "Index", type = "redirectAction"),
			@Result(name = "input", location = "mensajeNuevo.jsp") })
	public String guardarMensaje() {
		session = (SessionMap<String, Object>) ActionContext.getContext()
				.getSession();
		if (session.get("esAdmin") != null
				&& false == (Boolean) session.get("esAdmin")) {
			destinatario = (Viajero) session.get("destinatario");
			if (destinatario != null) {
				Long idUsuarioSesion = (Long) session.get("usuario");
				if (idUsuarioSesion == destinatario.getId()) {
					addActionError(this.getText("mensaje.error_automensaje"));
					return INPUT;
				} else {
					if (!viajeroDAO.existe(destinatario.getId())) {
						addActionError(getText("mensaje.mensaje_no_existe"));
						return INPUT;
					} else {
						Viajero yo = viajeroDAO.recuperar(idUsuarioSesion);
						destinatario = viajeroDAO.recuperar(destinatario
								.getId());
						mensaje.setDe(yo);
						mensaje.setPara(destinatario);
						mensajeDAO.persistir(mensaje);
						return SUCCESS;
					}
				}
			} else
				addActionError(this.getText("mensaje.error_destinatario"));
			return "input";
		} else {
			return "index";
		}
	}

	@Action(value = "verMensajes", results = {
			@Result(name = "success", location = "verMensajes.jsp"),
			@Result(name = "index", location = "Index", type = "redirectAction") })
	@SkipValidation
	public String verMensajes() {
		session = (SessionMap<String, Object>) ActionContext.getContext()
				.getSession();
		if (session.get("esAdmin") != null
				&& false == (Boolean) session.get("esAdmin")) {
			Long idUsuarioSesion = (Long) session.get("usuario");
			Viajero yo = viajeroDAO.recuperar(idUsuarioSesion);
			mensajes = mensajeDAO.recuperarPorDestinatario("id", yo);
			return SUCCESS;
		} else {
			return "index";
		}
	}

	@Action(value = "verMensaje", results = {
			@Result(name = "index", location = "Index", type = "redirectAction"),
			@Result(name = "success", location = "verMensaje.jsp"),
			@Result(name = "error", location = "verMensajes.jsp") })
	@SkipValidation
	public String verMensaje() throws Exception {
		session = (SessionMap<String, Object>) ActionContext.getContext()
				.getSession();
		if (session.get("esAdmin") != null
				&& false == (Boolean) session.get("esAdmin")) {
			HttpServletRequest req = (HttpServletRequest) ActionContext
					.getContext().get(ServletActionContext.HTTP_REQUEST);
			if (req.getParameter("idMensaje") != null) {
				Long id;
				try {
					id = Long.parseLong(req.getParameter("idMensaje"));
				} catch (NumberFormatException e) {
					addActionError(this.getText("mensaje.error_mostrar")); 
					verMensajes();
					return ERROR;
				}
				if (!mensajeDAO.existe(id)) {
					addActionError(this.getText("mensaje.mensaje_no_existe"));
					verMensajes();
					return ERROR;
				}
				mensaje = mensajeDAO.recuperar(id);
				req.setAttribute("mensaje", mensaje);
				return SUCCESS;
			} else {
				addActionError(this.getText("mensaje.error_mostrar"));
				verMensajes();
				return ERROR;
			}
		} else {
			return "index";
		}
	}

	@Action(value = "listarDestinatarios", results = {
			@Result(location = "verDestinatarios.jsp"),
			@Result(name = "index", location = "Index", type = "redirectAction") })
	@SkipValidation
	public String listarDestinatarios() {
		session = (SessionMap<String, Object>) ActionContext.getContext()
				.getSession();
		if (session.get("esAdmin") != null
				&& false == (Boolean) session.get("esAdmin")) {
			Long idUsuarioSesion = (Long) session.get("usuario");
			Viajero yo = viajeroDAO.recuperar(idUsuarioSesion);
			viajeros = viajeroDAO.recuperarTodos("id");
			viajeros.remove(yo);
			return SUCCESS;
		} else {
			return "index";
		}
	}

	@Override
	public void validate() {
		if (this.mensaje == null) {
			addFieldError("mensaje.asunto", this.getText("mensaje.null"));
		}
		if ((this.mensaje.getAsunto() == null)
				|| (this.mensaje.getAsunto().equals(""))) {
			addFieldError("mensaje.asunto", this.getText("mensaje.falta_asunto"));
		}
		if ((this.mensaje.getContenido() == null)
				|| (this.mensaje.getContenido().equals(""))) {
			addFieldError("mensaje.contenido", this.getText("mensaje.falta_mensaje"));
		}
	}

	@Override
	public Mensaje getModel() {
		return this.mensaje;
	}
}
