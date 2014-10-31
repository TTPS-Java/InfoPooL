package objetos;

import java.sql.Date;
import java.time.LocalTime;

public class Evento {
	public String nombre;
	public Date fecha;
	public int duracionDias;
	public LocalTime hora;
	public String descripcion;
	public Lugar lugar;
}
