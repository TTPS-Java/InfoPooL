package objetos;

public class Solicitud {
	private int id;
	private int cantidadAsientos;
	private Viaje viaje;
	private Viajero solicitante;
	private EstadoSolicitud estado;
	public int getId() {
		return id;
	}
	private void setId(int id) {
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
