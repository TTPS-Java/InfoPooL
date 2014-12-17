package objetos;

import java.sql.Date;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.stereotype.Component;

@Entity
public class Evento {
	@Id
	@GeneratedValue
	private Long id;
	private String nombre;
	private Date fecha;
	private int duracionDias;
	private String hora;
	private String descripcion;
	@OneToOne
	private Lugar lugar;
	
	
	public Evento() {
		this.lugar = new Lugar();
	}
	public Evento(String nombre, Date fecha, int duracionDias, String hora,
			String descripcion, Lugar lugar) {
		super();
		this.nombre = nombre;
		this.fecha = fecha;
		this.duracionDias = duracionDias;
		this.hora = hora;
		this.descripcion = descripcion;
		this.lugar = lugar;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setHora(String hora){
		this.hora=hora;
	}
	public String getHora(){
		return this.hora;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getDuracionDias() {
		return duracionDias;
	}
	public void setDuracionDias(int duracionDias) {
		this.duracionDias = duracionDias;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Lugar getLugar() {
		return lugar;
	}
	public void setLugar(Lugar lugar) {
		this.lugar = lugar;
	}
	@Override
	public String toString() {
		return "Evento [id=" + id + ", nombre=" + nombre + ", fecha=" + fecha
				+ ", duracionDias=" + duracionDias + ", hora=" + hora
				+ ", descripcion=" + descripcion + ", lugar=" + lugar + "]";
	}
	
	
}
