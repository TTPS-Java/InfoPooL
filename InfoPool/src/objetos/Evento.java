package objetos;

import java.sql.Date;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
@Entity
public class Evento {
	@Id
	@GeneratedValue
	private long id;
	private String nombre;
	private Date fecha;
	private int duracionDias;
	private LocalTime hora;
	private String descripcion;
	@OneToOne
	private Lugar lugar;
	public long getId() {
		return id;
	}
	private void setId(long id) {
		id = id;
	}
	
	public void setHora(LocalTime hora){
		this.hora=hora;
	}
	public LocalTime getHora(){
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
}
