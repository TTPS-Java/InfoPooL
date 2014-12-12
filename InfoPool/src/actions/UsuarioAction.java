package actions;

import interfacesDAO.UsuarioDAO;

import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

import DAOhiberJPA.FactoryDAO;
//import objetos.Tema;
import objetos.Usuario;
import objetos.Viajero;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UsuarioAction extends ActionSupport implements SessionAware, ModelDriven<Viajero>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Viajero user = new Viajero();
	private String confirmPass;
	private SessionMap<String, Object> session;

	@Override
	public Viajero getModel() {
		return user;
	}
	@Override
	public void setSession(Map<String, Object> map) {
		session = (SessionMap<String, Object>) map;
	}
	
	public String getConfirmPass() {
		return confirmPass;
	}
	public void setConfirmPass(String confirmPass) {
		this.confirmPass = confirmPass;
	}
	
	@SkipValidation
	@Action(value = "registro")
	public String registro() throws Exception {
		
		return SUCCESS;
	}
	
	@Action(value="editarUsuario", results={
			@Result(name="input", location="registro.jsp"),
			@Result(name="success", location="editar-usuario.jsp")
			})
	public String insertOrUpdate() throws Exception {
		session.put("nuevo", null);
		UsuarioDAO dao = FactoryDAO.getUsuarioDAO();
		if(session.get("usuario")!=null){
			dao.actualizar(user);
			session.put("usuario", user);
			return SUCCESS;
		} else {
			if(dao.existe(user.getNombreUsuario())) {
				addFieldError("nombre","El usuario ya existe");
				return INPUT;
			} else {
				dao.persistir(user);
				session.put("usuario", user);
				session.put("nuevo", true);
				return SUCCESS;
			}
		}
	}
	
	@SkipValidation
	@Action(value="borrarUsuario")
	public String borrar() {
		FactoryDAO.getUsuarioDAO().borrar(user.getId());
		return SUCCESS;
	}
	
	@SkipValidation
	@Action(value ="verUsuario")
	public String verDatos() {
		user = (Viajero) session.get("usuario");
		return SUCCESS;
	}
	
	@Override
	public void validate() {
		if((user.getNombreUsuario()==null)||(user.getNombreUsuario().equals(""))){
			addFieldError("nombreUsuario", "Debe ingresar un nombre de usuario");
		}
		if((user.getContrasenia()==null)||(user.getContrasenia().equals(""))){
			addFieldError("pass", "Debe ingresar una contraseña");
		} else if(!user.getContrasenia().equals(confirmPass)) {
			addFieldError("confirmPass", "Las contrasenia no coinciden");
		}
		if((user.getNombre() ==null)||(user.getNombre().equals(""))){
			addFieldError("nombre", "Debe ingresar un nombre");
		}
		if((user.getApellido()==null)||(user.getApellido().equals(""))){
			addFieldError("apellido", "Debe ingresar un apellido");
		}
		if((user.getMail()==null)||(user.getMail().equals(""))){
			addFieldError("email", "Debe ingresar un email");
		}
		if((user.getTelefono()==null)||(user.getTelefono().equals(""))){
			addFieldError("telefono", "Debe ingresar un telefono");
		}
	}
	
}
