package objetos;

public class Calificacion {
	private int id;
	private boolean esPositiva;
	private String comentario;
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
