package objetos;

public class Mensaje {
	private int id;
	private String asunto;
	private String contenido;
	private Evento evento;
	private Viajero para;
	private Viajero de;
	public int getId() {
		return id;
	}
	private void setId(int id) {
		id = id;
	}
	
	public Viajero getPara() {
		return para;
	}
	public void setPara(Viajero para) {
		this.para = para;
	}
	public Viajero getDe() {
		return de;
	}
	public void setDe(Viajero de) {
		this.de = de;
	}
	public String getAsunto() {
		return asunto;
	}
	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}
	public String getContenido() {
		return contenido;
	}
	public void setContenido(String contenido) {
		this.contenido = contenido;
	}
	public Evento getEvento() {
		return evento;
	}
	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	
	
	
}
