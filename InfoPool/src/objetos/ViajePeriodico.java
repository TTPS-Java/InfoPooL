package objetos;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
@Entity
public class ViajePeriodico extends Viaje {
	@OneToMany(cascade={CascadeType.PERSIST, CascadeType.MERGE})
	private List<DiaSemana> dias;

	
	public ViajePeriodico() {
		// TODO Auto-generated constructor stub
		
	}
	public ViajePeriodico(String horaPartida, String horaVuelta, Date fecha,
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
	
	public void addDia(DiaSemana dia){
		dias.add(dia);
	}
	
	public void removeDia(DiaSemana dia){
		dias.remove(dia);
	}
	
	@Override
	public String toString() {
		return "ViajePeriodico [toString()=" + super.toString() + "]";
	}
	
	
}