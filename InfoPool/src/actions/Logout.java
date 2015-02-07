package actions;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.dispatcher.SessionMap;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
@Action(value = "logout")
@Result(name = "success", location = "Index", type="redirectAction")
public class Logout extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public String execute() throws Exception {
		SessionMap<String, Object> session = (SessionMap<String,Object>)ActionContext.getContext().getSession();
		session.invalidate();
		return SUCCESS;
	}

}
