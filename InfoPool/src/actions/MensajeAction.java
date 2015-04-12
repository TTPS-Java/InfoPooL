package actions;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import interfacesDAO.EventoDAO;
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

@SuppressWarnings("serial")
@Controller
public class MensajeAction extends ActionSupport {

	private SessionMap<String, Object> session;
	@Autowired
	private ViajeroDAO viajeroDAO;
	@Autowired
	private MensajeDAO mensajeDAO;
	@Autowired
	private EventoDAO eventoDAO;
	private Viajero destinatario;
	private Mensaje mensaje;
	private String texto;
	private String asunto;
	private Long idEvento;
	private Collection<Mensaje> mensajes;
	private Collection<Evento> eventos;
	private Collection<Viajero> viajeros;

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

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

	public Long getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(Long idEvento) {
		this.idEvento = idEvento;
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
	public String denunciar() {
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
					addActionError("Debe elegir un destinatario valido"); //XXX this.getText("mensaje.error_destinatario")
					verMensajes();
					return INPUT;
				}
				Long idUsuarioSesion = (Long) session.get("usuario");
				if (idUsuarioSesion == id) {
					addActionError("No podés mandarte mensajes a vos mismo!"); // XXX this.getText("mensaje.error_automensaje")
					verMensajes();
					return INPUT;
				} else {
					if (viajeroDAO.recuperar(id) == null) {
						addActionError("El usuario no existe"); // XXX this.getText("mensaje.mensaje_no_existe")
						verMensajes();
						return INPUT;
					}
					setDestinatario(viajeroDAO.recuperar(id));
					return SUCCESS;
				}
			} else {
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
			HttpServletRequest req = (HttpServletRequest) ActionContext
					.getContext().get(ServletActionContext.HTTP_REQUEST);
			if (req.getParameter("idDestinatario") != null) {
				Long id;
				try {
					id = Long.parseLong(req.getParameter("idDestinatario"));
				} catch (NumberFormatException e) {
					addActionError("Debe elegir un destinatario valido"); //XXX this.getText("mensaje.error_destinatario")
					verMensajes();
					return INPUT;
				}
				Long idUsuarioSesion = (Long) session.get("usuario");
				if (idUsuarioSesion == id) {
					addActionError("No podés mandarte mensajes a vos mismo!"); // XXX this.getText("mensaje.error_automensaje")
					return INPUT;
				} else {
					if (viajeroDAO.recuperar(id) == null) {
						addActionError("El usuario no existe"); // XXX this.getText("mensaje.mensaje_no_existe")
						return INPUT;
					} else {
						Viajero yo = viajeroDAO.recuperar(idUsuarioSesion);
						destinatario = viajeroDAO.recuperar(id);
						Evento evento = null;
						if(req.getParameter("idEvento")!=null) {
							Long idEvento;
							try {
								idEvento = Long.parseLong(req.getParameter("idEvento"));
							} catch (NumberFormatException e) {
								addActionError("Debe elegir un evento valido"); //XXX this.getText("mensaje.error_evento")
								verMensajes();
								return INPUT;
							}
							if(idEvento!=-1) {
								evento = eventoDAO.recuperar(idEvento);
							}
						}
						mensaje = new Mensaje(asunto, texto, evento, yo, destinatario);
						mensajeDAO.persistir(mensaje);
						return SUCCESS;
					}
				}
			} else
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

	@Action(value = "listarDestinatarios", results = {
			@Result(location = "verDestinatarios.jsp"),
			@Result(name = "index", location = "Index", type = "redirectAction") })
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
}
