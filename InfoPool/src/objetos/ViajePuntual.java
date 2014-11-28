package objetos;

import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Entity;

@Entity
public class ViajePuntual extends Viaje {

	public ViajePuntual() {

	}

	public ViajePuntual(LocalTime horaPartida, LocalTime horaVuelta,
			Date fecha, int asientosLibres, Lugar desde, Lugar hasta,
			Viajero conductor, Evento eventoAsociado) {
		// TODO Auto-generated constructor stub
		super(horaPartida, horaVuelta, fecha, asientosLibres, desde, hasta,
				conductor, eventoAsociado);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ViajePuntual [toString()=" + super.toString() + "]";
	}
	
	
}