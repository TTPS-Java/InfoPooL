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
	private double latitud;
	private double longitud;

	
	public Lugar() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Lugar(String descripcion, long latitud, long longitud) {
		super();
		this.descripcion = descripcion;
		this.latitud = latitud;
		this.longitud = longitud;
	}

	public long getId() {
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
	public double getLatitud() {
		return latitud;
	}
	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}
	public double getLongitud() {
		return longitud;
	}
	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}


	@Override
	public String toString() {
		return "Lugar [id=" + id + ", descripcion=" + descripcion
				+ ", latitud=" + latitud + ", longitud=" + longitud + "]";
	}
	
	
}
