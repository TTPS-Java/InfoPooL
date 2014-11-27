package objetos;

import java.util.List;

import javax.persistence.Entity;
@Entity
public class Viajero extends Usuario {
	private String Nombre;
	private String Apellido;
	private String mail;
	private String telefono;
	private String foto;
	private boolean estaActivo;
	private List<Viaje> viajes;
	private List<Viaje> viajesEstoy;
	private List<Calificacion> calificaciones;
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getApellido() {
		return Apellido;
	}
	public void setApellido(String apellido) {
		Apellido = apellido;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public boolean isEstaActivo() {
		return estaActivo;
	}
	public void setEstaActivo(boolean estaActivo) {
		this.estaActivo = estaActivo;
	}
	public List<Viaje> getViajes() {
		return viajes;
	}
	public void setViajes(List<Viaje> viajes) {
		this.viajes = viajes;
	}
	public List<Viaje> getViajesEstoy() {
		return viajesEstoy;
	}
	public void setViajesEstoy(List<Viaje> viajesEstoy) {
		this.viajesEstoy = viajesEstoy;
	}
	public List<Calificacion> getCalificaciones() {
		return calificaciones;
	}
	public void setCalificaciones(List<Calificacion> calificaciones) {
		this.calificaciones = calificaciones;
	}
	
}
