package actions;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import objetos.Viajero;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.dispatcher.SessionMap;

import com.opensymphony.xwork2.ActionContext;


@Action(value="imagenPerfil")
@Result(
	    name = "success", 
	    type = "stream", 
	    params = { 
	        "contentType", "image/jpeg", 
	        "inputName", "stream", 
	        "bufferSize", "1024", 
	    }
	)
public class imagenPerfilAction {
	
	private String type = "image/jpeg";
    private String filename;
    private InputStream stream;
 
    public String execute() throws Exception {
        SessionMap<String,Object> session = (SessionMap<String, Object>) ActionContext.getContext().getSession();
        String foto =(String)session.get("foto");
        filename=foto;
        File img = new File("/tmp/"+foto);
        stream = new FileInputStream(img);
 
        return "success";
    }
     
    public String getType() {
        return this.type;
    }
     
    public String getFilename() {
        return this.filename;
    }
     
    public InputStream getStream() {
        return this.stream;
    }




}
