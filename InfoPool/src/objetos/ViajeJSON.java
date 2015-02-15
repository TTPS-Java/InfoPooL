package objetos;

import java.util.Date;

public class ViajeJSON {
	private long id;
	private String horaPartida;
	private String horaVuelta;
	private Date fecha;
	private int asientosLibres;
	private Evento eventoAsociado;
	private Lugar desde;
	private Lugar hasta;
	
	
	public ViajeJSON(){
		
	}
	
	/*public ViajeJSON(viajeId,viajeHoraPartida,
		viajeHoraVuelta,viajeFecha, viajeAsientosLibres,
			eventoAsociado,desde,hasta)*/
	
	public ViajeJSON(long id, String horaPartida,
			String horaVuelta, Date fecha, int asientosLibres,
			Evento eventoAsociado, Lugar desde, Lugar hasta) {
		super();
		this.id = id;
		this.horaPartida = horaPartida;
		this.horaVuelta = horaVuelta;
		this.fecha = fecha;
		this.asientosLibres = asientosLibres;
		this.eventoAsociado = eventoAsociado;
		this.desde = desde;
		this.hasta = hasta;
	}
	public Evento getEventoAsociado() {
		return eventoAsociado;
	}
	public void setEventoAsociado(Evento eventoAsociado) {
		this.eventoAsociado = eventoAsociado;
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
	public long getId() {
		return id;
	}
	public void setId(long viajeId) {
		this.id = viajeId;
	}
	public String getHoraPartida() {
		return horaPartida;
	}
	public void setHoraPartida(String viajeHoraPartida) {
		this.horaPartida = viajeHoraPartida;
	}
	public String getHoraVuelta() {
		return horaVuelta;
	}
	public void setHoraVuelta(String viajeHoraVuelta) {
		this.horaVuelta = viajeHoraVuelta;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date viajeFecha) {
		this.fecha = viajeFecha;
	}
	public int getAsientosLibres() {
		return asientosLibres;
	}
	public void setAsientosLibres(int viajeAsientosLibres) {
		this.asientosLibres = viajeAsientosLibres;
	}
	
	
	

}
