package DAOhiberJPA;

import org.springframework.stereotype.Repository;

import interfacesDAO.DiaSemanaDAO;
import objetos.DiaSemana;
@Repository
public class DiaSemanaDAOhiberJPA 
extends GenericDAOhiberJPA<DiaSemana> implements DiaSemanaDAO {
	public DiaSemanaDAOhiberJPA() {
		super(DiaSemana.class);
	}
}
