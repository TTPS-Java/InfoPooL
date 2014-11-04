package objetos;

public class Lugar {
	private int id;
	private String descripcion;
	private long latitud;
	private long longitud;
	private int getId() {
		return id;
	}
	private void setId(int id) {
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
