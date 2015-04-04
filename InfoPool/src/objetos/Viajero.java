package objetos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
@Entity
public class Viajero extends Usuario {
	private String Nombre;
	private String Apellido;
	private String mail;
	private String telefono;
	private String foto;
	private boolean estaActivo;
	@OneToMany(mappedBy="conductor")
	private List<Viaje> viajes;
	@ManyToMany
	private List<Viaje> viajesEstoy;
	@OneToMany
	private List<Calificacion> calificaciones;
	
	public Viajero() {
		// TODO Auto-generated constructor stub
		
	}
	
	public Viajero(String nombre, String apellido, String mail,
			String telefono, String foto, boolean estaActivo,String nombreUsuario, String contrasenia) {
		super(nombreUsuario,contrasenia);
		Nombre = nombre;
		Apellido = apellido;
		this.mail = mail;
		this.telefono = telefono;
		this.foto = foto;
		this.estaActivo = estaActivo;
		this.viajes = new ArrayList<Viaje>();
		this.viajesEstoy=new ArrayList<Viaje>();
		this.calificaciones= new ArrayList<Calificacion>();
	}

	public List<Viaje>recuperarViajesEstoyFinalizados(Date fechaRecibida){
		ArrayList<Viaje> viajesFinalizadosViajero = new ArrayList<Viaje>();
		System.out.println("recuperando viajes finalizados");
		for (Viaje ve:this.getViajesEstoy())
		{
			System.out.println("recuperando viajes finalizados");
			System.out.println(ve.getFecha());
			System.out.println(fechaRecibida);
			if(ve.getFecha().before(fechaRecibida)){
				viajesFinalizadosViajero.add(ve);
			}
		}
		return viajesFinalizadosViajero;
	}
	
	public List<CalificacionPendiente> recuperarCalificionesPendientes(){
		System.out.println("paso a ver calificaciones");
		ArrayList<Viaje> viajesFinalizados = (ArrayList<Viaje>) this.recuperarViajesEstoyFinalizados(new Date());
		ArrayList<CalificacionPendiente> calificacionesPendientes= new ArrayList<CalificacionPendiente>();
		System.out.println("tamanio lista viajes finalizados: "+viajesFinalizados.size());
			for (Viaje v:viajesFinalizados){
			  boolean Secalifico = false;
			  for (Calificacion c:this.getCalificaciones()){
				  if(v.getId()==c.getViaje().getId()){
					Secalifico=true;
					break;
			       }
			  }
			  if(!Secalifico){
				    CalificacionPendiente cp = new CalificacionPendiente(v.getConductor().getId(),v.getFecha(),v.getId(),v.getConductor().getNombreUsuario()
				    		,v.getHasta().getDescripcion()); 
					calificacionesPendientes.add(cp);
			  }
		}
		return calificacionesPendientes;
	}
	
	
	
	
	
	
	
	
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getApellido() {
		return Apellido;
	}
	public void setApellido(String apellido) {
		Apellido = apellido;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public boolean isEstaActivo() {
		return estaActivo;
	}
	public void setEstaActivo(boolean estaActivo) {
		this.estaActivo = estaActivo;
	}
	public List<Viaje> getViajes() {
		return viajes;
	}
	public void setViajes(List<Viaje> viajes) {
		this.viajes = viajes;
	}
	public List<Viaje> getViajesEstoy() {
		return viajesEstoy;
	}
	public void setViajesEstoy(List<Viaje> viajesEstoy) {
		this.viajesEstoy = viajesEstoy;
	}
	
	public void addViajeEstoy(Viaje viajeEstoy) {
		this.viajesEstoy.add(viajeEstoy);
	}
	
	public void removeViajeEstoy(Viaje viajeEstoy) {
		this.viajesEstoy.remove(viajeEstoy);
	}
	public List<Calificacion> getCalificaciones() {
		return calificaciones;
	}
	public void setCalificaciones(List<Calificacion> calificaciones) {
		this.calificaciones = calificaciones;
	}

	public void addCalificacion(Calificacion calificacion) {
		calificaciones.add(calificacion);
	}
	public void removeCalificacion(Calificacion calificacion) {
		calificaciones.remove(calificacion);
	}
	@Override
	public String toString() {
		return "Viajero [Nombre=" + Nombre + ", Apellido=" + Apellido
				+ ", mail=" + mail + ", telefono=" + telefono + ", foto="
				+ foto + ", estaActivo=" + estaActivo + ", getId()=" + getId()
				+ ", getNombreUsuario()=" + getNombreUsuario()
				+ ", getContrasenia()=" + getContrasenia() + "]";
	}
	
	/*@Override
	public boolean equals(Object object) {
		if (object == this)
			return true;
		if (object == null)
			return false;

		final Viajero b = (Viajero) object;

		if (this.getId() != 0 && b.getId() != 0) {
			return this.getId() == b.getId();
		}
		return false;
	}*/
	
	
}
