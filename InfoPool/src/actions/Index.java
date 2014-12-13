package actions;

import objetos.Administrador;
import objetos.Usuario;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import DAOhiberJPA.FactoryDAO;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;


public class Index extends ActionSupport {
	private static final long serialVersionUID = 1L;

	@Override
	@Action(value="Index",
  results={@Result(name="success",location="index.jsp"),
			@Result(name="admin",location="indexAdmin.jsp"),
			@Result(name="login",location="index-login.jsp")
  })
	public String execute() throws Exception {
		Usuario u=(Usuario)ActionContext.getContext().getSession().get("usuario");
		//REVISARRRR!!!
		   u = FactoryDAO.getUsuarioDAO().recuperar(u.getId());
		if(u!=null){
			if (true==(Boolean)ActionContext.getContext().getSession().get("admin")){
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