package actions;

import interfacesDAO.UsuarioDAO;
import interfacesDAO.ViajeDAO;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

import objetos.Usuario;
import objetos.Viaje;


@Controller
@ParentPackage("json-default")
public class TablaRecorridoAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private UsuarioDAO usuario;
	private List<Usuario> gridModel;
	private Integer rows = 0;
	private Integer page = 0;
	private Integer total = 0;
	private Integer record = 0;
	private String sord;
	private String sidx;
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	
	@Action(value = "datosAction",results={ @Result (name="success",type="json")})
	public String execute(){
		int to = (rows * page);
		int from = to - rows;
		this.usuarios = (List<Usuario>) usuario.recuperarTodos(1, 5, "id");
				//listar(from, to, sidx, sord);
		// Contar filas
		record = usuario.recuperarTodos("id").size();
		// Seteo el gridModel con la lista obtenida
		gridModel = usuarios;
		// Calcular el número de páginas para la consulta
		total = (int) Math.ceil((double) record / (double) rows);
		return "success";
	}
	
	public String getJSON() {
		return execute();
	}
	
	public List<Usuario> getUsuarios() {
		return this.usuarios;
	}


	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}


	public List<Usuario> getGridModel() {
		return gridModel;
	}
	public void setGridModel(List<Usuario> gridModel) {
		this.gridModel = gridModel;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getRecord() {
		return record;
	}
	public void setRecord(Integer record) {
		this.record = record;
		if (this.record > 0 && this.rows > 0) {
			this.total = (int) Math.ceil((double) this.record
					/ (double) this.rows);
		} else {
			this.total = 0;
		}
	}
	public String getSord() {
		return sord;
	}
	public void setSord(String sord) {
		this.sord = sord;
	}
	public String getSidx() {
		return sidx;
	}
	public void setSidx(String sidx) {
		this.sidx = sidx;
	}
	
	
	
	
	

}
