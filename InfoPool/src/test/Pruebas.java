package test;

import interfacesDAO.AdministradorDAO;
import interfacesDAO.CalificacionDAO;
import interfacesDAO.DenunciaDAO;
import interfacesDAO.DiaSemanaDAO;
import interfacesDAO.EstadoSolicitudDAO;
import interfacesDAO.EventoDAO;
import interfacesDAO.LugarDAO;
import interfacesDAO.MensajeDAO;
import interfacesDAO.SolicitudDAO;
import interfacesDAO.UsuarioDAO;
import interfacesDAO.ViajeDAO;
import interfacesDAO.ViajePeriodicoDAO;
import interfacesDAO.ViajePuntualDAO;
import interfacesDAO.ViajeroDAO;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import objetos.Administrador;
import objetos.Calificacion;
import objetos.Denuncia;
import objetos.DiaSemana;
import objetos.EstadoSolicitud;
import objetos.Evento;
import objetos.Lugar;
import objetos.Mensaje;
import objetos.Solicitud;
import objetos.Usuario;
import objetos.Viaje;
import objetos.ViajePeriodico;
import objetos.ViajePuntual;
import objetos.Viajero;
import DAOhiberJPA.FactoryDAO;

@WebServlet("/Pruebas")
public class Pruebas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Pruebas() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* Creo daos */
		AdministradorDAO adminDAO = FactoryDAO.getAdministradorDAO();
		CalificacionDAO califDAO = FactoryDAO.getCalificacionDAO();
		DenunciaDAO denuDAO = FactoryDAO.getDenunciaDAO();
		DiaSemanaDAO diaDAO = FactoryDAO.getDiaSemanaDAO();
		EstadoSolicitudDAO estadoDAO = FactoryDAO.getEstadoSolicitudDAO();
		EventoDAO eventoDAO = FactoryDAO.getEventoDAO();
		LugarDAO lugarDAO = FactoryDAO.getLugarDAO();
		MensajeDAO msjDAO = FactoryDAO.getMensajeDAO();
		SolicitudDAO solicDAO = FactoryDAO.getSolicitudDAO();
		UsuarioDAO usDAO = FactoryDAO.getUsuarioDAO();
		ViajeDAO viajeDAO = FactoryDAO.getViajeDAO();
		ViajePeriodicoDAO perioDAO = FactoryDAO.getViajePeriodicoDAO();
		ViajePuntualDAO puntualDAO = FactoryDAO.getViajePuntualDAO();
		ViajeroDAO viajeroDAO = FactoryDAO.getViajeroDAO();
		
		/* ABMS */
		/* Adminstrador */
		System.out.println("ABM ADMINISTRADOR");
		Administrador admin = new Administrador("admin", "admin");
		adminDAO.persistir(admin);
		long id = admin.getId();
		System.out.println("Id: "+id);
		admin = null;
		admin = adminDAO.recuperar(id);
		System.out.println("recuperar");
		System.out.println(admin);
		admin.setContrasenia("admin2");
		admin = adminDAO.actualizar(admin);
		System.out.println("actualizar");
		System.out.println(admin);
		adminDAO.borrar(id);
		admin = adminDAO.recuperar(id);
		System.out.println("borrar");
		if(admin==null){
			System.out.println("El admin no existe");
		}
		
		
		/* Viajero */
		System.out.println("ABM VIAJERO");
		Viajero vi = new Viajero("juan", "perez", "a@b", "1234", "hola.jpg", true, "juan", "1234");
		viajeroDAO.persistir(vi);
		id = vi.getId();
		System.out.println("Id: "+id);
		vi = null;
		vi = viajeroDAO.recuperar(id);
		System.out.println("recuperar");
		System.out.println(vi);
		vi.setContrasenia("12345");
		vi = viajeroDAO.actualizar(vi);
		System.out.println("actualizar");
		System.out.println(vi);
		viajeroDAO.borrar(id);
		vi = viajeroDAO.recuperar(id);
		System.out.println("borrar");
		if(vi==null){
			System.out.println("El viajero no existe");
		}
		
		/* Usuario */
		System.out.println("ABM Usuario");
		Usuario user = new Usuario("us1", "us");
		usDAO.persistir(user);
		id = user.getId();
		System.out.println("Id: "+id);
		user = null;
		user = usDAO.recuperar(id);
		System.out.println("recuperar");
		System.out.println(user);
		user.setContrasenia("uss");
		user = usDAO.actualizar(user);
		System.out.println("actualizar");
		System.out.println(user);
		usDAO.borrar(id);
		user = usDAO.recuperar(id);
		System.out.println("borrar");
		if(user==null){
			System.out.println("El usuario no existe");
		}
		
		/* Denuncia */
		System.out.println("ABM Denuncia");
		/*creo viajeros*/
		Viajero vi1 = new Viajero("juan", "perez", "a@b", "1234", "hola.jpg", true, "juan", "1234");
		Viajero vi2 = new Viajero("carlos", "perez", "a@b", "1234", "hola.jpg", true, "juan", "1234");
		viajeroDAO.persistir(vi1);
		viajeroDAO.persistir(vi2);
		
		Denuncia den = new Denuncia("cont", vi1, vi2);
		denuDAO.persistir(den);
		id = den.getId();
		System.out.println("Id: "+id);
		den = null;
		den = denuDAO.recuperar(id);
		System.out.println("recuperar");
		System.out.println(den);
		den.setContenido("cont2");
		den = denuDAO.actualizar(den);
		System.out.println("actualizar");
		System.out.println(den);
		denuDAO.borrar(id);
		den = denuDAO.recuperar(id);
		System.out.println("borrar");
		if(den==null){
			System.out.println("La denuncia no existe");
		}
		
		/* Mensaje */
		System.out.println("ABM Mensaje");
		/*el evento es opcional*/
		Mensaje msj = new Mensaje("as", "cont", null, vi1, vi2);
		msjDAO.persistir(msj);
		id = msj.getId();
		System.out.println("Id: "+id);
		msj = null;
		msj = msjDAO.recuperar(id);
		System.out.println("recuperar");
		System.out.println(msj);
		msj.setContenido("cont2");
		msj = msjDAO.actualizar(msj);
		System.out.println("actualizar");
		System.out.println(msj);
		msjDAO.borrar(id);
		msj = msjDAO.recuperar(id);
		System.out.println("borrar");
		if(den==null){
			System.out.println("El mensaje no existe");
		}
		
		/* Lugar */
		System.out.println("ABM Lugar");
		Lugar lugar = new Lugar("lug", 2, 5);
		lugarDAO.persistir(lugar);
		id = lugar.getId();
		System.out.println("Id: "+id);
		lugar = null;
		lugar = lugarDAO.recuperar(id);
		System.out.println("recuperar");
		System.out.println(lugar); 
		lugar.setDescripcion("desc2");
		lugar = lugarDAO.actualizar(lugar);
		System.out.println("actualizar");
		System.out.println(lugar);
		lugarDAO.borrar(id);
		lugar = lugarDAO.recuperar(id);
		System.out.println("borrar");
		if(lugar==null){
			System.out.println("El lugar no existe");
		}
		
		/* Evento */
		System.out.println("ABM Evento");
		/* creo lugar */
		lugar = new Lugar("desc", 2, 5);
		lugarDAO.persistir(lugar);

		Evento evento = new Evento("ev1", Date.valueOf(LocalDate.now()), 2, LocalTime.now(), "desc", lugar);
		eventoDAO.persistir(evento);
		id = evento.getId();
		System.out.println("Id: "+id);
		evento = null;
		evento = eventoDAO.recuperar(id); 
		System.out.println("recuperar");
		System.out.println(evento);
		evento.setDescripcion("desc2");
		evento = eventoDAO.actualizar(evento);
		System.out.println("actualizar");
		System.out.println(evento);
		eventoDAO.borrar(id);
		evento = eventoDAO.recuperar(id);
		System.out.println("borrar");
		if(evento==null){
			System.out.println("El evento no existe");
		}
		
		/* Viaje */
		System.out.println("ABM Viaje");
		/* creo lugares */
		Lugar lugar1 = new Lugar("desc1", 2, 5);
		Lugar lugar2 = new Lugar("desc2", 2, 5);
		lugarDAO.persistir(lugar1);
		lugarDAO.persistir(lugar2);
		/* creo evento */
		Evento evento1 = new Evento("ev1", Date.valueOf(LocalDate.now()), 2, LocalTime.now(), "desc", lugar);
		eventoDAO.persistir(evento1);
		/* creo viajeros */
		vi1 = new Viajero("juan", "peres", "1", "1", "1", true, "juan", "1234");
		viajeroDAO.persistir(vi1);

		Viaje viaje = new Viaje(LocalTime.now(), LocalTime.now(), Date.valueOf(LocalDate.now()), 3, lugar1, lugar2, vi1, evento1);
		viajeDAO.persistir(viaje);
		id = viaje.getId();
		System.out.println("Id: "+id);
		viaje = null;
		viaje = viajeDAO.recuperar(id);
		System.out.println("recuperar");
		System.out.println(viaje);
		viaje.setAsientosLibres(4);
		viaje = viajeDAO.actualizar(viaje);
		System.out.println("actualizar");
		System.out.println(viaje);
		viajeDAO.borrar(id);
		viaje = viajeDAO.recuperar(id);
		System.out.println("borrar");
		if(viaje==null){
			System.out.println("El viaje no existe");
		}
		
		/* Calificacion */
		System.out.println("ABM Calificacion");
		viaje = new Viaje(LocalTime.now(), LocalTime.now(), Date.valueOf(LocalDate.now()), 3, lugar1, lugar2, vi1, evento1);
		viajeDAO.persistir(viaje);
		Calificacion cali = new Calificacion(true, "com", vi1, vi2, viaje);
		califDAO.persistir(cali);
		id = cali.getId();
		System.out.println("Id: "+id);
		cali = null;
		cali = califDAO.recuperar(id);
		System.out.println("recuperar");
		System.out.println(cali);
		cali.setComentario("com2");
		cali = califDAO.actualizar(cali);
		System.out.println("actualizar");
		System.out.println(cali);
		califDAO.borrar(id);
		cali = califDAO.recuperar(id);
		System.out.println("borrar");
		if(cali==null){
			System.out.println("La calificacion no existe");
		}
		
		/* Estado Solicitud*/
		System.out.println("ABM Estado Solicitud");
		EstadoSolicitud esta = new EstadoSolicitud("nomb");
		estadoDAO.persistir(esta);
		id = esta.getId();
		System.out.println("Id: "+id);
		esta = null;
		esta = estadoDAO.recuperar(id);
		System.out.println("recuperar");
		System.out.println(esta);
		esta.setNombre("nombre2");
		esta = estadoDAO.actualizar(esta);
		System.out.println("actualizar");
		System.out.println(esta);
		estadoDAO.borrar(id);
		esta = estadoDAO.recuperar(id);
		System.out.println("borrar");
		if(esta==null){
			System.out.println("El estado no existe");
		}
		
		/* Solicitud */
		System.out.println("ABM Solicitud");
		EstadoSolicitud estado1 = new EstadoSolicitud("nueva");
		estadoDAO.persistir(estado1);
		Solicitud sol = new Solicitud(2, viaje, vi1, estado1);
		solicDAO.persistir(sol);
		id = sol.getId();
		System.out.println("Id: "+id);
		sol = null;
		sol = solicDAO.recuperar(id);
		System.out.println("recuperar");
		System.out.println(sol);
		sol.setCantidadAsientos(1);
		sol = solicDAO.actualizar(sol);
		System.out.println("actualizar");
		System.out.println(sol);
		solicDAO.borrar(id);
		sol = solicDAO.recuperar(id);
		System.out.println("borrar");
		if(sol==null){
			System.out.println("La solicitud no existe");
		}
		
		/* DiaSemana*/
		System.out.println("ABM DiaSemana");
		DiaSemana dia = new DiaSemana("martes");
		diaDAO.persistir(dia);
		id = dia.getId();
		System.out.println("Id: "+id);
		dia = null;
		dia = diaDAO.recuperar(id);
		System.out.println("recuperar");
		System.out.println(dia);
		dia.setNombre("nombre2");
		dia = diaDAO.actualizar(dia);
		System.out.println("actualizar");
		System.out.println(dia);
		diaDAO.borrar(id);
		dia = diaDAO.recuperar(id);
		System.out.println("borrar");
		if(dia==null){
			System.out.println("El dia no existe");
		}
		
		/* Viaje periodico*/
		System.out.println("ABM Viaje");
		ViajePeriodico viajeP = new ViajePeriodico(LocalTime.now(), LocalTime.now(), Date.valueOf(LocalDate.now()), 3, lugar1, lugar2, vi1, evento1);
		perioDAO.persistir(viajeP);
		id = viajeP.getId();
		System.out.println("Id: "+id);
		viajeP = null;
		viajeP = perioDAO.recuperar(id);
		System.out.println("recuperar");
		System.out.println(viajeP);
		viajeP.setAsientosLibres(4);
		viajeP = perioDAO.actualizar(viajeP);
		System.out.println("actualizar");
		System.out.println(viajeP);
		perioDAO.borrar(id);
		viajeP = perioDAO.recuperar(id);
		System.out.println("borrar");
		if(viajeP==null){
			System.out.println("El viaje periodico no existe");
		}
		
		/* Viaje puntual*/
		System.out.println("ABM Viaje");
		ViajePuntual viajePun = new ViajePuntual(LocalTime.now(), LocalTime.now(), Date.valueOf(LocalDate.now()), 3, lugar1, lugar2, vi1, evento1);
		puntualDAO.persistir(viajePun);
		id = viajePun.getId();
		System.out.println("Id: "+id);
		viajePun = null;
		viajePun = puntualDAO.recuperar(id);
		System.out.println("recuperar");
		System.out.println(viajePun);
		viajePun.setAsientosLibres(4);
		viajePun = puntualDAO.actualizar(viajePun);
		System.out.println("actualizar");
		System.out.println(viajePun);
		puntualDAO.borrar(id);
		viajePun = puntualDAO.recuperar(id);
		System.out.println("borrar");
		if(viajePun==null){
			System.out.println("El viaje puntual no existe");
		}
		
		/* test collections */
		/* viaje periodico -> diaSemana */
		System.out.println("viaje periodico -> diaSemana");
		ViajePeriodico viajeP1 = new ViajePeriodico(LocalTime.now(), LocalTime.now(), Date.valueOf(LocalDate.now()), 3, lugar1, lugar2, vi1, evento1);
		perioDAO.persistir(viajeP1);
		id = viajeP1.getId();
		viajeP1 = perioDAO.recuperarConDias(id);
		DiaSemana dia1 = new DiaSemana("lunes");
		DiaSemana dia2 = new DiaSemana("jueves");
		diaDAO.persistir(dia1);
		diaDAO.persistir(dia2);
		viajeP1.addDia(dia1);
		viajeP1.addDia(dia2);
		perioDAO.actualizar(viajeP1);
		viajeP1 = perioDAO.recuperarConDias(id);
		System.out.println("Dias:");
		List<DiaSemana> lis = viajeP1.getDias();
		for (DiaSemana diaSemana : lis) {
			System.out.println(diaSemana);
		}
		System.out.println("saco un dia");
		viajeP1.removeDia(dia1);
		perioDAO.actualizar(viajeP1);
		viajeP1 = perioDAO.recuperarConDias(id);
		System.out.println("Dias:");
		lis = viajeP1.getDias();
		for (DiaSemana diaSemana : lis) {
			System.out.println(diaSemana);
		}
		perioDAO.borrar(id);
		
		/* viaje <-> pasajeros (Viajero)*/
		System.out.println("viaje <-> pasajeros (Viajero)");
		Viaje viaje1 = new Viaje(LocalTime.now(), LocalTime.now(), Date.valueOf(LocalDate.now()), 3, lugar1, lugar2, vi1, evento1);
		viajeDAO.persistir(viaje1);
		id = viaje1.getId();
		viaje1 = viajeDAO.recuperarConPasajeros(id);
		viaje1.addPasajero(vi1);
		viaje1.addPasajero(vi2);
		viajeDAO.actualizar(viaje1);
		vi1.addViajeEstoy(viaje1);
		viajeroDAO.actualizar(vi1);
		vi2.addViajeEstoy(viaje1);
		viajeroDAO.actualizar(vi2);
		
		viaje1 = viajeDAO.recuperarConPasajeros(id);
		System.out.println("Pasajeros:");
		List<Viajero> lis1 = viaje1.getPasajeros();
		for (Viajero viajero : lis1) {
			System.out.println(viajero);
		}
		System.out.println("saco un pasajero");
		viaje1.removePasajero(vi1);
		viajeDAO.actualizar(viaje1);
		vi1.removeViajeEstoy(viaje1);
		viajeroDAO.actualizar(vi1);
		viaje1 = viajeDAO.recuperarConPasajeros(id);
		System.out.println("Pasajeros:");
		lis1 = viaje1.getPasajeros();
		for (Viajero viajero : lis1) {
			System.out.println(viajero);
		}
		Viaje viaje2 = new Viaje(LocalTime.now(), LocalTime.now(), Date.valueOf(LocalDate.now()), 8, lugar1, lugar2, vi1, evento1);
		viaje2.addPasajero(vi2);
		viajeDAO.persistir(viaje2);
		vi2.addViajeEstoy(viaje2);
		viajeroDAO.actualizar(vi2);
		System.out.println("Viajes v2:");
		List<Viaje> lis3 = vi2.getViajesEstoy();
		for (Viaje viaje3 : lis3) {
			System.out.println(viaje3);
		}
		System.out.println("Viajes v1:");
		lis3 = vi1.getViajesEstoy();
		for (Viaje viaje3 : lis3) {
			System.out.println(viaje3);
		}
		
		/* viajero -> calificacion */
		System.out.println("viajero -> calificacion");
		Calificacion cali1 = new Calificacion(true, "com", vi1, vi2, viaje2);
		Calificacion cali2 = new Calificacion(true, "com", vi1, vi2, viaje2);
		califDAO.persistir(cali1);
		califDAO.persistir(cali2);
		id = vi1.getId();
		vi1 = viajeroDAO.recuperarConCalificaciones(id);
		vi1.addCalificacion(cali1);
		vi1.addCalificacion(cali2);
		viajeroDAO.actualizar(vi1);
		vi1 = viajeroDAO.recuperarConCalificaciones(id);
		System.out.println("Calificaciones: ");
		
		List<Calificacion> lis4 = vi1.getCalificaciones();
		for (Calificacion calificacion : lis4) {
			System.out.println(calificacion);
		}
		System.out.println("saco una calificacion");
		vi1.removeCalificacion(cali1);
		viajeroDAO.actualizar(vi1);
		cali1.setCalificado(null);
		califDAO.actualizar(cali1);
		vi1 = viajeroDAO.recuperarConCalificaciones(id);
		System.out.println("Calificaciones: ");
		
		lis4 = vi1.getCalificaciones();
		for (Calificacion calificacion : lis4) {
			System.out.println(calificacion);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
