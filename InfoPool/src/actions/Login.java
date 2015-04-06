package actions;

import interfacesDAO.AdministradorDAO;
import interfacesDAO.UsuarioDAO;

import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import DAOhiberJPA.AdministradorDAOhiberJPA;
import DAOhiberJPA.FactoryDAO;
import objetos.Administrador;
import objetos.Usuario;









import objetos.Viajero;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
@Controller
public class Login extends ActionSupport  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String pass;
	private SessionMap<String, Object> session;
	@Autowired
	private AdministradorDAO administradorDAO;
	@Autowired
	private UsuarioDAO usuarioDAO;
	

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
			@Result(name = "success", location = "Index", type = "redirect"),
			@Result(name = "input", location = "login.jsp") })

	
	public String procesarLogin() throws Exception {
		Usuario us = this.usuarioDAO.recuperar(getNombre());
		session = (SessionMap<String, Object>) ActionContext.getContext().getSession();
		boolean viajeroBloqueado = false;
		if (us == null) {
			addFieldError("nombre", "El usuario no existe");
			return INPUT;
		} else {
			
			if(us instanceof Viajero){
				Viajero v = (Viajero)us;
				if(v.isEstaActivo()==false){
					viajeroBloqueado=true;
				}
			}
		 if(viajeroBloqueado==true){
			 addFieldError("nombre", "Usted se encuentra bloqueado");
			 return INPUT;
		 }else{	
			   if (us.getContrasenia().equals(pass)) {
				  if (us instanceof Administrador) {
					  session.put("esAdmin", true);
				   } else {
					  session.put("esAdmin", false);
			   	     }
				     session.put("usuario", us.getId());
				    return SUCCESS;
			   } else {
				addFieldError("pass", "Clave incorrecta");
				return INPUT;
			   }
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

	/*@Override
	public void setSession(Map<String, Object> map) {
		session = (SessionMap<String, Object>) map;
	}*/
}
