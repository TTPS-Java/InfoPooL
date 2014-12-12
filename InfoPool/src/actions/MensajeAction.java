package actions;

import interfacesDAO.MensajeDAO;
import interfacesDAO.UsuarioDAO;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

import clasesDAO.FactoryDAO;
import clasesObjetos.Mensaje;
import clasesObjetos.Usuario;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
public class MensajeAction extends ActionSupport implements ModelDriven<Mensaje>, SessionAware{
	private static final long serialVersionUID = 1L;
	private Mensaje msj = new Mensaje();
	private Collection<Mensaje> mensajes;
	private SessionMap<String, Object> session;
	@Override
	public Mensaje getModel() {
		return msj;
	}
	@Override
	public void setSession(Map<String, Object> map) {
		session = (SessionMap<String, Object>) map;
	}
	public Collection<Mensaje> getMensajes() {
		return mensajes;
	}
	public void setMensajes(Collection<Mensaje> mensajes) {
		this.mensajes = mensajes;
	}
	
	@Action(value = "mensajeNuevo")
	@SkipValidation
	public String mensajeNuevo() throws Exception {
		return SUCCESS;
	}

	@Action(value = "guardarMensaje", results = {
		@Result(name = "success", location = "verMensajes", type="redirectAction"),
		@Result(name = "input", location = "mensajeNuevo.jsp")})
	@RequiredStringValidator(fieldName = "texto", message = "Debe ingresar un mensaje")
	public String guardarMensaje() throws Exception {
		Usuario us = (Usuario) session.get("usuario");
		UsuarioDAO dao = FactoryDAO.getUsuarioDAO();
		us = dao.recuperarConMensajes(us.getId());
		msj.setAutor(us);
		FactoryDAO.getMensajeDAO().persistir(msj);
		dao.actualizar(us);
		return SUCCESS;
	}
	
	@Action(value = "verMensajes")
	@SkipValidation
	public String verMensajes() throws Exception {
		setMensajes(FactoryDAO.getMensajeDAO().recuperarTodos("id"));
		return SUCCESS;
	}

	@Action(value = "verMisMensajes")
	@SkipValidation
	public String verMisMensajes() throws Exception {
		Usuario us = (Usuario) session.get("usuario");
		us = FactoryDAO.getUsuarioDAO().recuperarConMensajes(us.getId());
		setMensajes(us.getMensajes());
		return SUCCESS;
	}
	@Action(value="borrarMensaje", results={@Result(name="success", location="verMisMensajes", type="redirectAction"),
											@Result(name="input", location="error.jsp")},
			interceptorRefs={
				@InterceptorRef(value = "store", params= {"operationMode", "STORE"}),
				@InterceptorRef(value = "defaultStack", params= {"operationMode", "STORE"})
			})
	@SkipValidation
	public String borrarMensaje(){
		HttpServletRequest request = (HttpServletRequest) ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
		String strId = request.getParameter("id");
		if(strId == null) {
			addActionError("Indique que mensaje desea borrar");
			return INPUT;
		}
		try {
			Long id = Long.parseLong(strId);
			MensajeDAO mDao = FactoryDAO.getMensajeDAO();
			UsuarioDAO uDao = FactoryDAO.getUsuarioDAO();
			msj = mDao.recuperar(id);
			Usuario us = (Usuario) session.get("usuario");
			us = uDao.recuperarConMensajes(us.getId());
			us.removeMensaje(msj);
			uDao.actualizar(us);
			mDao.borrar(msj.getId());
			return SUCCESS;
		} catch (NumberFormatException e) {
			addActionError("Debe indicar un numero");
			return INPUT;
		}
	}
}