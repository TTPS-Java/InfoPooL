package objetos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class EstadoSolicitud {
	@Id
	@GeneratedValue
	private long id;
	private String nombre;
	
	public EstadoSolicitud() {
		// TODO Auto-generated constructor stub
	}
	
	public EstadoSolicitud(String nombre) {
		super();
		this.nombre = nombre;
	}
	private long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
}
