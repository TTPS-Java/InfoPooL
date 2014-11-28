package objetos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class Solicitud {
	@Id
	@GeneratedValue
	private long id;
	private int cantidadAsientos;
	@ManyToOne
	private Viaje viaje;
	@ManyToOne
	private Viajero solicitante;
	@ManyToOne
	private EstadoSolicitud estado;
	
	
	public Solicitud() {
		// TODO Auto-generated constructor stub
	}
	
	public Solicitud(int cantidadAsientos, Viaje viaje, Viajero solicitante,
			EstadoSolicitud estado) {
		super();
		this.cantidadAsientos = cantidadAsientos;
		this.viaje = viaje;
		this.solicitante = solicitante;
		this.estado = estado;
	}
	public long getId() {
		return id;
	}
	private void setId(long id) {
		this.id = id;
	}
	public int getCantidadAsientos() {
		return cantidadAsientos;
	}
	public void setCantidadAsientos(int cantidadAsientos) {
		this.cantidadAsientos = cantidadAsientos;
	}
	public Viaje getViaje() {
		return viaje;
	}
	public void setViaje(Viaje viaje) {
		this.viaje = viaje;
	}
	public Viajero getSolicitante() {
		return solicitante;
	}
	public void setSolicitante(Viajero solicitante) {
		this.solicitante = solicitante;
	}
	public EstadoSolicitud getEstado() {
		return estado;
	}
	public void setEstado(EstadoSolicitud estado) {
		this.estado = estado;
	}
	
}
