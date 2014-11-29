package objetos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Calificacion {
	@Id
	@GeneratedValue
	private long id;
	private boolean esPositiva;
	private String comentario;
	@ManyToOne
	private Viajero calificado;
	@OneToOne
	private Viajero autor;
	@OneToOne
	private Viaje viaje;
	
	
	public Calificacion() {
		// TODO Auto-generated constructor stub
	}
	
	public Calificacion(boolean esPositiva, String comentario,
			Viajero calificado, Viajero autor, Viaje viaje) {
		super();
		this.esPositiva = esPositiva;
		this.comentario = comentario;
		this.calificado = calificado;
		this.autor = autor;
		this.viaje = viaje;
	}
	public Viajero getCalificado() {
		return calificado;
	}
	public void setCalificado(Viajero calificado) {
		this.calificado = calificado;
	}
	public Viajero getAutor() {
		return autor;
	}
	public void setAutor(Viajero autor) {
		this.autor = autor;
	}
	public Viaje getViaje() {
		return viaje;
	}
	public void setViaje(Viaje viaje) {
		this.viaje = viaje;
	}
	private void  setId(long id){
		this.id=id;
	}
	public long getId(){
		return this.id;
	}
	
	public void setEsPositiva(Boolean unBoolean){
		this.esPositiva=unBoolean;
	}
	public Boolean getEsPositiva(){
		return this.esPositiva;
	}
	public void setComentario(String unComentario){
		
		this.comentario=unComentario;
	}
	public String getComentario(){
		return this.comentario;
	}

	@Override
	public String toString() {
		return "Calificacion [id=" + id + ", esPositiva=" + esPositiva
				+ ", comentario=" + comentario + ", calificado=" + calificado
				+ ", autor=" + autor + ", viaje=" + viaje + "]";
	}
	
	@Override
	public boolean equals(Object object) {
		if (object == this)
			return true;
		if (object == null)
			return false;

		final Calificacion b = (Calificacion) object;

		if (this.getId() != 0 && b.getId() != 0) {
			return this.getId() == b.getId();
		}
		return false;
	}
	
}

