package actions;

import interfacesDAO.UsuarioDAO;

import java.util.List;
import java.util.Map;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.interceptor.validation.SkipValidation;

import clasesDAO.FactoryDAO;
import clasesObjetos.Tema;
import clasesObjetos.Usuario;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UsuarioAction extends ActionSupport implements SessionAware, ModelDriven<Usuario>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuario user = new Usuario();
	private String confirmPass;
	private SessionMap<String, Object> session;
	private List<Tema> todosTemas;
	private List<Tema> temasElegidos;

	@Override
	public Usuario getModel() {
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
		todosTemas = FactoryDAO.getTemaDAO().recuperarTodos("id");
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
			if(dao.existe(user.getNombre())) {
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
		user = (Usuario) session.get("usuario");
		return SUCCESS;
	}
	
	@Override
	public void validate() {
		if((user.getNombre()==null)||(user.getNombre().equals(""))){
			addFieldError("nombre", "Debe ingresar un nombre de usuario");
		}
		if((user.getPass()==null)||(user.getPass().equals(""))){
			addFieldError("pass", "Debe ingresar una contraseña");
		} else if(!user.getPass().equals(confirmPass)) {
			addFieldError("confirmPass", "Las contraseñas no coinciden");
		}
		if((user.getNombreReal()==null)||(user.getNombreReal().equals(""))){
			addFieldError("nombreReal", "Debe ingresar un nombre");
		}
		if((user.getApellido()==null)||(user.getApellido().equals(""))){
			addFieldError("apellido", "Debe ingresar un apellido");
		}
		if((user.getEmail()==null)||(user.getEmail().equals(""))){
			addFieldError("email", "Debe ingresar un email");
		}
		if((user.getNick()==null)||(user.getNick().equals(""))){
			addFieldError("nick", "Debe ingresar un nick");
		}
	}
	public List<Tema> getTodosTemas() {
		return todosTemas;
	}
	public void setTodosTemas(List<Tema> todosTemas) {
		this.todosTemas = todosTemas;
	}
	public List<Tema> getTemasElegidos() {
		return temasElegidos;
	}
	public void setTemasElegidos(List<Tema> temasElegidos) {
		this.temasElegidos = temasElegidos;
	}
}
