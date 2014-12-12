package actions;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

import DAOhiberJPA.FactoryDAO;
import objetos.Administrador;
import objetos.Usuario;

import com.opensymphony.xwork2.ActionSupport;

public class Login extends ActionSupport implements SessionAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String pass;
	private SessionMap<String, Object> session;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	@SkipValidation
	@Action(value = "login")
	public String login() throws Exception {
		return SUCCESS;
	}

	@Action(value = "procesarLogin", results = {
			@Result(name = "success", location = "index", type = "redirect"),
			@Result(name = "input", location = "login.jsp") })
	
	public String procesarLogin() throws Exception {
		Usuario us = FactoryDAO.getUsuarioDAO().recuperar(getNombre());
		if (us == null) {
			 System.out.println("es nulo");
			addFieldError("nombre", "El usuario no existe");
		
			return INPUT;
		} else {
			if (us.getContrasenia().equals(pass)) {
				if (us instanceof Administrador) {
					Administrador ad = FactoryDAO.getAdministradorDAO()
							.recuperar(us.getId());
					session.put("admin", true);
					session.put("usuario", ad);
				} else {
					session.put("usuario", us);
				}
				 System.out.println("OK");
				return SUCCESS;
			} else {
				 System.out.println(pass+":"+us.getContrasenia());
				 System.out.println("contrase�a mal");
				addFieldError("pass", "Contraseña incorrecta");
				return INPUT;
			}
		}
	}

	@Override
	public void validate() {
		if ((getNombre() == null) || (getNombre().equals(""))) {
			addFieldError("nombre", "Debe ingresar un nombre de usuario");
		}
		if ((getPass() == null) || (getPass().equals(""))) {
			addFieldError("pass", "Debe ingresar una contraseña");
		}
	}

	@Override
	public void setSession(Map<String, Object> map) {
		session = (SessionMap<String, Object>) map;
	}
}
