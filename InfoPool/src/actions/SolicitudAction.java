package actions;

import interfacesDAO.EstadoSolicitudDAO;
import interfacesDAO.LugarDAO;
import interfacesDAO.SolicitudDAO;
import interfacesDAO.ViajeDAO;
import interfacesDAO.ViajeroDAO;

import java.util.Collection;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;

import objetos.EstadoSolicitud;
import objetos.Solicitud;
import objetos.Viaje;
import objetos.Viajero;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.dispatcher.SessionMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
@Controller
public class SolicitudAction extends ActionSupport {
	private int cantAsientos;
	private Solicitud solicitud;
	private Collection<Solicitud> solicitudes;
	private SessionMap<String, Object> session;
	private Viaje viaje;
	@Autowired
	private ViajeDAO viajeDAO;
	@Autowired
	private ViajeroDAO viajeroDAO;
	@Autowired
	private LugarDAO lugarDAO;

	@Autowired
	private EstadoSolicitudDAO estadoSolicitudDAO;
	@Autowired
	private SolicitudDAO solicitudDAO;

	public int getCantAsientos() {
		return cantAsientos;
	}

	public void setCantAsientos(int cantAsientos) {
		this.cantAsientos = cantAsientos;
	}

	public Solicitud getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(Solicitud solicitud) {
		this.solicitud = solicitud;
	}

	public Collection<Solicitud> getSolicitudes() {
		return solicitudes;
	}

	public void setSolicitudes(Collection<Solicitud> solicitudes) {
		this.solicitudes = solicitudes;
	}

	@Action(value = "solicitudNueva", results = {
			@Result(location = "solicitudNueva.jsp"),
			@Result(name = "index", location = "Index", type = "redirectAction"),
			@Result(name = "input", location = "verRecorridos", type = "redirectAction") })
	public String solicitudNueva() throws Exception {
		session = (SessionMap<String, Object>) ActionContext.getContext()
				.getSession();
		if (session.get("esAdmin") != null
				&& false == (Boolean) session.get("esAdmin")) {
			HttpServletRequest req = (HttpServletRequest) ActionContext
					.getContext().get(ServletActionContext.HTTP_REQUEST);
			if (req.getParameter("id") != null) {
				Long id = Long.parseLong(req.getParameter("id"));
				viaje = viajeDAO.recuperar(id);// !
				req.setAttribute("viaje", viaje);
				return SUCCESS;
			} else {
				return INPUT;
			}
		} else {
			return "index";
		}
	}

	@Action(value = "guardarSolicitud", results = {
			@Result(location = "solicitudGuardada.jsp"),
			@Result(name = "input", location = "solicitudNueva.jsp"),
			@Result(name = "index", location = "Index", type = "redirectAction") })
	public String guardarSolicitud() {
		session = (SessionMap<String, Object>) ActionContext.getContext()
				.getSession();
		if (session.get("esAdmin") != null
				&& false == (Boolean) session.get("esAdmin")) {
			HttpServletRequest req = (HttpServletRequest) ActionContext
					.getContext().get(ServletActionContext.HTTP_REQUEST);
			if (req.getParameter("id") != null) {
				Long idV = Long.parseLong(req.getParameter("id"));
				viaje = viajeDAO.recuperar(idV);
				if (viaje.getAsientosLibres() < cantAsientos) {
					addFieldError("cantAsientos",
							"No hay suficientes asientos!");
					return INPUT;
				} else if (cantAsientos == 0) {
					addFieldError("cantAsientos", "Pida al menos un asiento!");
					return INPUT;
				} else {
					viaje.setAsientosLibres(viaje.getAsientosLibres()
							- cantAsientos);
					viajeDAO.actualizar(viaje);
					Long id = (Long) session.get("usuario");
					Viajero via = viajeroDAO.recuperar(id);
					EstadoSolicitud estado = estadoSolicitudDAO
							.recuperar("Pendiente");
					solicitud = new Solicitud(cantAsientos, viaje, via, estado);
					solicitudDAO.persistir(solicitud);
					return SUCCESS;
				}
			} else
				return "input";
		} else {
			return "index";
		}
	}

	@Action(value = "verSolicitudes", results = {
			@Result(location = "verSolicitudes.jsp"),
			@Result(name = "index", location = "Index") })
	public String verSolicitudes() {
		session = (SessionMap<String, Object>) ActionContext.getContext()
				.getSession();
		if (session.get("esAdmin") != null
				&& false == (Boolean) session.get("esAdmin")) {
			Long id = (Long) session.get("usuario");
			Viajero via = viajeroDAO.recuperar(id);
			Collection<Solicitud> list = solicitudDAO.recuperarPorConductor(
					"id", via);
			solicitudes = new LinkedList<Solicitud>();
			for (Solicitud solicitud : list) {
				if (solicitud.getEstado().getNombre().equals("Pendiente")) {
					solicitudes.add(solicitud);
				}
			}
			return SUCCESS;
		} else {
			return "index";
		}
	}

	@Action(value = "aceptarSolicitud", results = {
			@Result(location = "verSolicitudes", type = "redirectAction"),
			@Result(name = "index", location = "Index", type = "redirectAction") })
	public String aceptarSolicitud() {
		session = (SessionMap<String, Object>) ActionContext.getContext()
				.getSession();
		if (session.get("esAdmin") != null
				&& false == (Boolean) session.get("esAdmin")) {
			HttpServletRequest req = (HttpServletRequest) ActionContext
					.getContext().get(ServletActionContext.HTTP_REQUEST);
			if (req.getParameter("id") != null) {
				Long idS = Long.parseLong(req.getParameter("id"));
				solicitud = solicitudDAO.recuperar(idS);
				Long idU = (Long) session.get("usuario");
				if (solicitud.getViaje().getConductor().getId() == idU) {
					viaje = viajeDAO.recuperarConPasajeros(solicitud.getViaje()
							.getId());
					viaje.addPasajero(solicitud.getSolicitante());
					viajeDAO.actualizar(solicitud.getViaje());
					solicitud.setEstado(estadoSolicitudDAO
							.recuperar("Aceptada"));
					solicitudDAO.actualizar(solicitud);
					return SUCCESS;
				} else
					return "index";
			} else
				return "index";
		} else
			return "index";
	}

	@Action(value = "rechazarSolicitud", results = {
			@Result(location = "verSolicitudes", type = "redirectAction"),
			@Result(name = "index", location = "Index", type = "redirectAction") })
	public String rechazarSolicitud() {
		session = (SessionMap<String, Object>) ActionContext.getContext()
				.getSession();
		if (session.get("esAdmin") != null
				&& false == (Boolean) session.get("esAdmin")) {
			HttpServletRequest req = (HttpServletRequest) ActionContext
					.getContext().get(ServletActionContext.HTTP_REQUEST);
			if (req.getParameter("id") != null) {
				Long idS = Long.parseLong(req.getParameter("id"));
				solicitud = solicitudDAO.recuperar(idS);
				Long idU = (Long) session.get("usuario");
				if (solicitud.getViaje().getConductor().getId() == idU) {
					solicitud.getViaje().setAsientosLibres(
							solicitud.getViaje().getAsientosLibres()
									+ solicitud.getCantidadAsientos());
					viajeDAO.actualizar(solicitud.getViaje());
					solicitud.setEstado(estadoSolicitudDAO
							.recuperar("Rechazada"));
					solicitudDAO.actualizar(solicitud);
					return SUCCESS;
				} else
					return "index";
			} else
				return "index";
		} else
			return "index";
	}

	public Viaje getViaje() {
		return viaje;
	}

	public void setViaje(Viaje viaje) {
		this.viaje = viaje;
	}
}