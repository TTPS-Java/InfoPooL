package objetos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Lugar {
	@Id
	@GeneratedValue
	private long id;
	private String descripcion;
	private long latitud;
	private long longitud;
	
	public Lugar() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Lugar(String descripcion, long latitud, long longitud) {
		super();
		this.descripcion = descripcion;
		this.latitud = latitud;
		this.longitud = longitud;
	}
	private long getId() {
		return id;
	}
	private void setId(long id) {
		this.id = id;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public long getLatitud() {
		return latitud;
	}
	public void setLatitud(long latitud) {
		this.latitud = latitud;
	}
	public long getLongitud() {
		return longitud;
	}
	public void setLongitud(long longitud) {
		this.longitud = longitud;
	}
	
}
