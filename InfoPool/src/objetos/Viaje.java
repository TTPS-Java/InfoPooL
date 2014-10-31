package objetos;

import java.time.LocalTime;

public class Viaje {
	public int Id;
	public LocalTime horaPartida;
	public LocalTime horaVuelto;
	public int asientosLibres;
	public Lugar desde;
	public Lugar hasta;
	public Viajero conductor;
	public Evento eventoAsociado;
}