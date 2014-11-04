package objetos;

import java.time.LocalTime;
import java.util.List;

public class Viaje {
	private int Id;
	private int horaPartida;
	private int horaVuelta;
	private int asientosLibres;
	private Lugar desde;
	private Lugar hasta;
	private Viajero conductor;
	private Evento eventoAsociado;
	private List<Viajero> pasajeros;
	public int getId() {
		return Id;
	}
	private void setId(int id) {
		Id = id;
	}
	public List<Viajero> getPasajeros(){
		return pasajeros;
	}
	public void setPasajeros(List<Viajero> pasajeros){
		
		this.pasajeros=pasajeros;
	}
	public LocalTime getHoraPartida() {
		return horaPartida;
	}
	public void setHoraPartida(LocalTime horaPartida) {
		this.horaPartida = horaPartida;
	}
	public LocalTime getHoraVuelta() {
		return horaVuelta;
	}
	public void setHoraVuelto(LocalTime horaVuelto) {
		this.horaVuelta = horaVuelto;
	}
	public int getAsientosLibres() {
		return asientosLibres;
	}
	public void setAsientosLibres(int asientosLibres) {
		this.asientosLibres = asientosLibres;
	}
	public Lugar getDesde() {
		return desde;
	}
	public void setDesde(Lugar desde) {
		this.desde = desde;
	}
	public Lugar getHasta() {
		return hasta;
	}
	public void setHasta(Lugar hasta) {
		this.hasta = hasta;
	}
	public Viajero getConductor() {
		return conductor;
	}
	public void setConductor(Viajero conductor) {
		this.conductor = conductor;
	}
	public Evento getEventoAsociado() {
		return eventoAsociado;
	}
	public void setEventoAsociado(Evento eventoAsociado) {
		this.eventoAsociado = eventoAsociado;
	}
	
	
}