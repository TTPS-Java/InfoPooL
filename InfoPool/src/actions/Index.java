package actions;

import objetos.Usuario;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
@Action(value="Index")
@Results(value ={@Result(name="success",location="inicio.jsp"),
			@Result(name="admin",location="indexAdmin.jsp"),
			@Result(name="login",location="index-login.jsp")})
@Controller
public class Index extends ActionSupport {
	private static final long serialVersionUID = 1L;

	@Override	
	public String execute() throws Exception {
		Usuario u=(Usuario)ActionContext.getContext().getSession().get("usuario");
		//REVISARRRR!!!
		   //u = FactoryDAO.getUsuarioDAO().recuperar(u.getId());
		if(u!=null){
			if (true==(Boolean)ActionContext.getContext().getSession().get("esAdmin")){
				System.out.println("paso aadmin");
				return "admin";
			}
			else{
				System.out.println("paso usuario comun");
				return "success";
			}
		}
		else
			System.out.println("loguon");
			return LOGIN;
	}
}