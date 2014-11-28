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
}
