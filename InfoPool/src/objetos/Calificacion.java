package objetos;

public class Calificacion {
	private int id;
	private boolean esPositiva;
	private String comentario;
	private Viajero calificado;
	private Viajero autor;
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
	private void  setId(int id){
		this.id=id;
	}
	public int getId(){
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
