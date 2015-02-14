package actions;

import interfacesDAO.DenunciaDAO;
import interfacesDAO.ViajeroDAO;

import javax.servlet.http.HttpServletRequest;

import objetos.Denuncia;
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
public class DenunciaAction extends ActionSupport {

	private SessionMap<String, Object> session;
	@Autowired
	private ViajeroDAO viajeroDAO;
	@Autowired
	private DenunciaDAO denunciaDAO;
	private Viajero denunciado;
	private Denuncia denuncia;
	private String texto;

	public Viajero getDenunciado() {
		return denunciado;
	}

	public void setDenunciado(Viajero denunciado) {
		this.denunciado = denunciado;
	}

	public Denuncia getDenuncia() {
		return denuncia;
	}

	public void setDenuncia(Denuncia denuncia) {
		this.denuncia = denuncia;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	@Action(value = "denunciar", results = {
			@Result(location = "denunciar.jsp"),
			@Result(name = "index", location = "Index", type = "redirectAction"),
			@Result(name = "input", location = "verViajeros", type = "redirectAction") })
	public String denunciar() {
		session = (SessionMap<String, Object>) ActionContext.getContext()
				.getSession();
		if (session.get("esAdmin") != null
				&& false == (Boolean) session.get("esAdmin")) {
			HttpServletRequest req = (HttpServletRequest) ActionContext
					.getContext().get(ServletActionContext.HTTP_REQUEST);
			if (req.getParameter("id") != null) {
				Long idDenunciado = Long.parseLong(req.getParameter("id"));
				Long idUsuarioSesion = (Long) session.get("usuario");
				if (idUsuarioSesion == idDenunciado) {
					addActionError("No podés denunciarte a vos mismo!");
					return INPUT;
				} else {
					if (viajeroDAO.recuperar(idDenunciado) == null) {
						addActionError("El usuario no existe");
						return INPUT;
					}
					setDenunciado(viajeroDAO.recuperar(idDenunciado));
					return SUCCESS;
				}
			} else {
				return INPUT;
			}
		} else {
			return "index";
		}
	}

	@Action(value = "guardarDenuncia", results = {
			@Result(location = "denunciaGuardada.jsp"),
			@Result(name = "index", location = "Index", type = "redirectAction"),
			@Result(name = "input", location = "denunciar.jsp") })
	public String guardarDenuncia() {
		session = (SessionMap<String, Object>) ActionContext.getContext()
				.getSession();
		if (session.get("esAdmin") != null
				&& false == (Boolean) session.get("esAdmin")) {
			HttpServletRequest req = (HttpServletRequest) ActionContext
					.getContext().get(ServletActionContext.HTTP_REQUEST);
			if (req.getParameter("id") != null) {
				Long idDenunciado = Long.parseLong(req.getParameter("id"));
				Long idUsuarioSesion = (Long) session.get("usuario");
				if (idUsuarioSesion == idDenunciado) {
					addActionError("No podés denunciarte a vos mismo!");
					return INPUT;
				} else {
					if (viajeroDAO.recuperar(idDenunciado) == null) {
						addActionError("El usuario no existe");
						return INPUT;
					} else {
						Viajero yo = viajeroDAO.recuperar(idUsuarioSesion);
						denunciado = viajeroDAO.recuperar(idDenunciado);
						denuncia = new Denuncia(texto, yo, denunciado);
						denunciaDAO.persistir(denuncia);
						return SUCCESS;
					}
				}
			} else
				return "input";
		} else {
			return "index";
		}
	}
}
