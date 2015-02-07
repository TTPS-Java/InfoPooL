package actions;

import javax.servlet.http.HttpServletRequest;

import interfacesDAO.UsuarioDAO;
import interfacesDAO.ViajeroDAO;
import objetos.Usuario;
import objetos.Viajero;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.dispatcher.SessionMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
@Controller
public class Index extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UsuarioDAO usuarioDAO;
	
	@Autowired
	private ViajeroDAO viajeroDAO;
	

	@Action(value="Index", results ={@Result(name="success",location="inicio.jsp"),
			@Result(name="admin",location="indexAdmin.jsp"),
			@Result(name="login",location="index-login.jsp")})
	@Override	
	public String execute() throws Exception {
		SessionMap<String, Object> session = (SessionMap<String, Object>) ActionContext.getContext().getSession();
		Long id = (Long) session.get("usuario");
	
		if(id!=null) {
			Usuario us =usuarioDAO.recuperar(id);
			HttpServletRequest req = (HttpServletRequest)ActionContext.getContext().get(ServletActionContext.HTTP_REQUEST);
			req.setAttribute("usuario",us.getNombreUsuario());
			
			if (true==(Boolean)session.get("esAdmin")){
				return "admin";
			}
			else{
				Viajero v =viajeroDAO.recuperar(id);
				session.put("foto", v.getFoto());
				return "success";
			}
		}
		else
			return LOGIN;
	}
	
	@Action(value = "", results={@Result(name = "success", location = "Index", type="redirectAction")})
	public String root() {
		return SUCCESS;
	}
}