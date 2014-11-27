package objetos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Entity
public class Denuncia {
	@Id
	@GeneratedValue
	private long id;
	private String contenido;
	private Viajero autor;
	private Viajero denunciado;
	public long getId() {
		return id;
	}
	private void setId(long id) {
		this.id = id;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public Viajero getAutor() {
		return autor;
	}
	public void setAutor(Viajero autor) {
		this.autor = autor;
	}
	public Viajero getDenunciado() {
		return denunciado;
	}
	public void setDenunciado(Viajero denunciado) {
		this.denunciado = denunciado;
	}
}
