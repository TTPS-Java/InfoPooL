package DAOhiberJPA;
import interfacesDAO.ViajeDAO;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import objetos.Viaje;
import objetos.ViajeJSON;
import objetos.ViajePeriodico;
import objetos.Viajero;

import org.springframework.stereotype.Repository;

import dataSource.MiEntityManagerFactory;
@Repository
public class ViajeDAOhiberJPA 
extends GenericDAOhiberJPA<Viaje> implements ViajeDAO {
	public ViajeDAOhiberJPA() {
		super(Viaje.class);
	}

	@Override
	public Viaje recuperarConPasajeros(Serializable id) {
		EntityManager em = MiEntityManagerFactory.getEMF().createEntityManager();
		EntityTransaction etx = em.getTransaction();
		etx.begin();
		Viaje entity = em.find(Viaje.class, id);
		entity.getPasajeros().size();
		em.flush();
		etx.commit();
		em.close();
		return entity;
	}

	@Override
	public List<Viaje> recuperarPorConductor(String columnOrder,
			Viajero conductor) {
		return recuperarPorConductor(-1,-1,columnOrder,conductor);
	}

	@Override
	public List<Viaje> recuperarPorConductor(int page, int maxResult,
			Viajero conductor) {
		return recuperarPorConductor(page, maxResult, "", conductor);
	}

	@Override
	public List<Viaje> recuperarPorConductor(int page, int maxResult,
			String columnOrder, Viajero Conductor) {
		TypedQuery<Viaje> consulta = em.createQuery("select e from Viaje e where conductor = :cond "
				//+ "exists ( select * from Viajero v where v.id = e.id and v.id = :id)
				+ "order by id", Viaje.class);
		consulta.setParameter("cond", Conductor);
		if(page!=-1){
			consulta.setFirstResult((page-1)* maxResult)
					.setMaxResults(maxResult);
		}
		List<Viaje> list = consulta.getResultList();
		return list;
}
	public List<ViajeJSON> recuperarViajesCompletosJSON(int from, int to, String criterio, String orden,
		int idEvento,Date fechaMinima, Date fechaMaxima,String horaMaxima, String horaMinima,String tipoDeViaje
		,ArrayList<Integer> total){
	 boolean primeraClausula=false;
	 String condiciones=" ";
	 if((idEvento!=-1) && (idEvento!=0)){
		 condiciones=condiciones+" where v.eventoAsociado.id = "+idEvento;
		 primeraClausula=true;
	 }
	 if((!(horaMaxima== null)) && (!horaMaxima.equals(""))){
		 if(primeraClausula==true){condiciones=condiciones+" and";}else{condiciones=" where ";}
		 condiciones=condiciones+" horaPartida <= "+"'"+horaMaxima+"'";
		 primeraClausula=true;
	 }
	 if((!(horaMinima== null)) && (!horaMinima.equals(""))){
		 if(primeraClausula==true){condiciones=condiciones+" and";}else{condiciones=" where ";}
		 condiciones =condiciones+" horaPartida >= " +"'"+horaMinima+"'";
		 primeraClausula=true;
	 }
	 if(!(fechaMinima== null)){
		 String f = new SimpleDateFormat("yyyy-MM-dd").format(fechaMinima);
		 if(primeraClausula==true){condiciones=condiciones+" and";}else{condiciones=" where ";}
		 condiciones=condiciones+ " fecha >= "+ "'"+f +"'";
		 primeraClausula=true;
	 }
	 if(!(fechaMaxima== null)){
		 String f = new SimpleDateFormat("yyyy-MM-dd").format(fechaMaxima);
		 if(primeraClausula==true){condiciones=condiciones+" and";}else{condiciones=" where ";}
		 condiciones= condiciones+" fecha <= "+ "'"+f+"'";
	 }
	 
		EntityManager em = MiEntityManagerFactory.getEMF().createEntityManager();
		List<Viaje> viajes= new ArrayList<Viaje>(); 
		List<ViajeJSON> viajesJSON= new ArrayList<ViajeJSON>(); 
		String sord="desc";
		if(orden !=null && !orden.equals("")){sord=orden;}       
		String stringOrderBy="id "+sord;	
		if(criterio !=null && !criterio.equals("")){stringOrderBy=criterio+" "+sord+", "+stringOrderBy;}
		
		
		TypedQuery<Viaje> consulta;
		if(tipoDeViaje.equals("viaje_periodico")){
			consulta = em.createQuery("select v from ViajePeriodico v "+condiciones+" order by "+ stringOrderBy, Viaje.class);
		}else if(tipoDeViaje.equals("viaje_puntual")){
			consulta = em.createQuery("select v from ViajePuntual v "+condiciones+" order by "+ stringOrderBy, Viaje.class);
		}else{
			consulta = em.createQuery("select v from Viaje v "+condiciones+" order by "+ stringOrderBy, Viaje.class);
		}
		//me llevo el total de registros que da la consulta
		total.add(consulta.getResultList().size());
		
		consulta.setFirstResult(from);
		consulta.setMaxResults(to-from);
		viajes = (List<Viaje>)consulta.getResultList();
		    for (Viaje v:viajes){
		    	ViajeJSON vj = new ViajeJSON(v.getId(),v.getHoraPartida(),v.getHoraVuelta(),v.getFecha()
		    			,v.getAsientosLibres(),v.getEventoAsociado(),v.getDesde(),v.getHasta());
		    	viajesJSON.add(vj);
		    }
		em.close();
		return viajesJSON;
 }
}
