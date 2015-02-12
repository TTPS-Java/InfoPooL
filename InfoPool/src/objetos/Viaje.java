package objetos;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
@Entity @Inheritance
public class Viaje {
	@Id
	@GeneratedValue
	private long id;
	private String horaPartida;
	private String horaVuelta;
	private Date fecha;
	private int asientosLibres;
	@OneToOne
	private Lugar desde;
	@OneToOne
	private Lugar hasta;
	@ManyToOne
	private Viajero conductor;
	@OneToOne
	private Evento eventoAsociado;
	@ManyToMany
	private List<Viajero> pasajeros;
	@OneToMany
	private List<Solicitud> solicitudes;

  public Viaje() {
	// TODO Auto-generated constructor stub
}
	
	public Viaje(String horaPartida, String horaVuelta, Date fecha,
			int asientosLibres, Lugar desde, Lugar hasta, Viajero conductor,
			Evento eventoAsociado) {
		super();
		this.horaPartida = horaPartida;
		this.horaVuelta = horaVuelta;
		this.fecha = fecha;
		this.asientosLibres = asientosLibres;
		this.desde = desde;
		this.hasta = hasta;
		this.conductor = conductor;
		this.eventoAsociado = eventoAsociado;
		this.pasajeros = new ArrayList<Viajero>();
		this.solicitudes = new ArrayList<Solicitud>();

	}

	public long getId() {
		return id;
	}

	private void setId(long id) {
		this.id = id;
	}

	public List<Viajero> getPasajeros() {
		return pasajeros;
	}

	public void setPasajeros(List<Viajero> pasajeros) {

		this.pasajeros = pasajeros;
	}

	public void addPasajero (Viajero pasajero) {
		this.pasajeros.add(pasajero);
	}
	
	public void removePasajero (Viajero pasajero) {
		this.pasajeros.remove(pasajero);
	}
	public String getHoraPartida() {
		return horaPartida;
	}

	public void setHoraPartida(String horaPartida) {
		this.horaPartida = horaPartida;
	}

	public String getHoraVuelta() {
		return horaVuelta;
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

	public List<Solicitud> getSolicitudes() {
		return solicitudes;
	}

	public void setSolicitudes(List<Solicitud> solicitudes) {
		this.solicitudes = solicitudes;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public void setHoraVuelta(String horaVuelta) {
		this.horaVuelta = horaVuelta;
	}

	@Override
	public String toString() {
		return "Viaje [id=" + id + ", horaPartida=" + horaPartida
				+ ", horaVuelta=" + horaVuelta + ", fecha=" + fecha
				+ ", asientosLibres=" + asientosLibres + ", desde=" + desde
				+ ", hasta=" + hasta + ", conductor=" + conductor
				+ ", eventoAsociado=" + eventoAsociado + "]";
	}
	
	/*@Override
	public boolean equals(Object object) {
		if (object == this)
			return true;
		if (object == null)
			return false;

		final Viaje b = (Viaje) object;

		if (this.getId() != 0 && b.getId() != 0) {
			return this.getId() == b.getId();
		}
		return false;
	}*/
}