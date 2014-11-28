package objetos;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
@Entity
public class ViajePeriodico extends Viaje {
	@OneToMany
	private List<DiaSemana> dias;

	
	public ViajePeriodico() {
		// TODO Auto-generated constructor stub
		
	}
	public ViajePeriodico(LocalTime horaPartida, LocalTime horaVuelta, Date fecha,
			int asientosLibres, Lugar desde, Lugar hasta, Viajero conductor,
			Evento eventoAsociado) {
		// TODO Auto-generated constructor stub
		super(horaPartida, horaVuelta,  fecha,
			asientosLibres,desde,hasta,conductor, eventoAsociado);
		 this.dias= new ArrayList<DiaSemana>();
	}
	public List<DiaSemana> getDias() {
		return dias;
	}
	public void setDias(List<DiaSemana> dias) {
		this.dias = dias;
	}
	
	
}