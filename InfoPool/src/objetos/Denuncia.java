package objetos;

public class Denuncia {
	public int id;
	public String contenido;
	public Viajero autor;
	public Viajero denunciado;
	public int getId() {
		return id;
	}
	private void setId(int id) {
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
