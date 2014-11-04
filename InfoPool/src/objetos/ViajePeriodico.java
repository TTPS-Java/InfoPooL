package objetos;

import java.time.LocalTime;
import java.util.List;

public class ViajePeriodico extends Viaje {
	public int id;
	public LocalTime fecha;
	private List<DiaSemana> dias;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalTime getFecha() {
		return fecha;
	}
	public void setFecha(LocalTime fecha) {
		this.fecha = fecha;
	}
	public List<DiaSemana> getDias() {
		return dias;
	}
	public void setDias(List<DiaSemana> dias) {
		this.dias = dias;
	}
	
	
}