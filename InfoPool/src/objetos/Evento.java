package objetos;

import java.sql.Date;
import java.time.LocalTime;

public class Evento {
	public String nombre;
	public Date fecha;
	public int duracionDias;
	public LocalTime hora;
	public String descripcion;
	public Lugar lugar;
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
