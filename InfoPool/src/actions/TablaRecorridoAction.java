package actions;

import interfacesDAO.AdministradorDAO;
import interfacesDAO.UsuarioDAO;
import interfacesDAO.ViajeDAO;
import interfacesDAO.ViajePeriodicoDAO;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import DAOhiberJPA.UsuarioDAOhiberJPA;

import com.opensymphony.xwork2.ActionSupport;

import objetos.Administrador;
import objetos.Usuario;
import objetos.Viaje;
import objetos.ViajePeriodico;


@ParentPackage("json-default")
@Action(value = "datosAction",results={ @Result (name="success",type="json")})
@Controller
public class TablaRecorridoAction extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ViajePeriodicoDAO viajePeriodicoDAO;

	private List<ViajePeriodico> gridModel;
	private Integer rows = 0;
	private Integer page = 0;
	private Integer total = 0;
	private Integer record = 0;
	private String sord;
	private String sidx;
	private List<ViajePeriodico> viajesPeriodicos = new ArrayList<ViajePeriodico>();
	
	

	public String execute(){
		int to = (rows * page);
		int from = to - rows;
		this.setViajesPeriodicos((List<ViajePeriodico>) viajePeriodicoDAO.recuperarTodos(1, 5, "id"));
				//listar(from, to, sidx, sord);
		// Contar filas
		record = this.getViajeDAO().recuperarTodos("id").size();
		// Seteo el gridModel con la lista obtenida
		gridModel = this.getViajesPeriodicos();
		// Calcular el número de páginas para la consulta
		total = (int) Math.ceil((double) record / (double) rows);
		return "success";
	}
	
	
	@JSON(serialize=false,deserialize = false)
	public ViajePeriodicoDAO getViajeDAO() {
		return viajePeriodicoDAO;
	}

	public void setViajePeriodicoDAO(ViajePeriodicoDAO usuario) {
		this.viajePeriodicoDAO = usuario;
	}
	
	public String getJSON() {
		return execute();
	}
	
	@JSON(serialize=false,deserialize = false)
	public List<ViajePeriodico> getViajesPeriodicos() {
		return this.viajesPeriodicos;
	}


	public void setViajesPeriodicos(List<ViajePeriodico> usuarios) {
		this.viajesPeriodicos = usuarios;
	}


	public List<ViajePeriodico> getGridModel() {
		return gridModel;
	}
	public void setGridModel(List<ViajePeriodico> gridModel) {
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
