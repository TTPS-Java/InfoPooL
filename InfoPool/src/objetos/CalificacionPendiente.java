package objetos;

import java.util.Date;

public class CalificacionPendiente {
	private long idCalificado;
	private Date fecha;
	private long idViaje;
	private String usuario;
	private String nombreLugarDestino;
	
	public CalificacionPendiente(long idCalificado, Date fecha, long idViaje,
			String usuario,String nombreLugarDestino) {
		super();
		this.idCalificado = idCalificado;
		this.fecha = fecha;
		this.idViaje = idViaje;
		this.usuario = usuario;
		this.nombreLugarDestino=nombreLugarDestino;
	}
	
	public long getIdCalificado() {
		return idCalificado;
	}
	public void setIdCalificado(long idCalificado) {
		this.idCalificado = idCalificado;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public long getIdViaje() {
		return idViaje;
	}
	public void setIdViaje(long idViaje) {
		this.idViaje = idViaje;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getNombreLugarDestino() {
		return nombreLugarDestino;
	}

	public void setNombreLugarDestino(String nombreLugarDestino) {
		this.nombreLugarDestino = nombreLugarDestino;
	}
	
	
	

}
