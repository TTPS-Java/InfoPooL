package actions;

import interfacesDAO.UsuarioDAO;
import interfacesDAO.ViajeroDAO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import objetos.Usuario;
import objetos.Viajero;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.validation.SkipValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
public class UsuarioAction extends ActionSupport implements
		ModelDriven<Viajero> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Viajero user = new Viajero();
	private String confirmPass;
	@Autowired
	private UsuarioDAO usuarioDao;
	@Autowired
	private ViajeroDAO viajeroDAO;
	private File imagen;
	private String imagenContentType;

	public File getImagen() {
		return imagen;
	}

	public void setImagen(File imagen) {
		this.imagen = imagen;
	}

	public String getImagenContentType() {
		return imagenContentType;
	}

	public void setImagenContentType(String imagenContentType) {
		this.imagenContentType = imagenContentType;
	}

	public String getImagenFileName() {
		return imagenFileName;
	}

	public void setImagenFileName(String imagenFileName) {
		this.imagenFileName = imagenFileName;
	}

	private String imagenFileName;
	private SessionMap<String, Object> session;

	private List<Viajero> viajeros;

	@Override
	public Viajero getModel() {
		return user;
	}

	/*
	 * @Override public void setSession(Map<String, Object> map) { session =
	 * (SessionMap<String, Object>) map; }
	 */

	public String getConfirmPass() {
		return confirmPass;
	}

	public void setConfirmPass(String confirmPass) {
		this.confirmPass = confirmPass;
	}

	public List<Viajero> getViajeros() {
		return viajeros;
	}

	@SkipValidation
	@Action(value = "registro")
	public String registro() throws Exception {
		return SUCCESS;
	}

	@Action(value = "editarUsuario", results = {
			@Result(name = "input", location = "registro.jsp"),
			@Result(name = "success", location = "editar-usuario.jsp") })
	public String insertOrUpdate() throws Exception {
		session = (SessionMap<String, Object>) ActionContext.getContext()
				.getSession();
		UsuarioDAO dao = this.usuarioDao;
		if (dao.existe(user.getNombreUsuario())) {
			addFieldError("nombre", "El usuario ya existe");
			return INPUT;
		} else {
			BufferedImage bi = ImageIO.read(this.getImagen());
			HttpServletRequest req = (HttpServletRequest) ActionContext
					.getContext().get(ServletActionContext.HTTP_REQUEST);
			String rutaAlmacenamiento = ServletActionContext.getRequest()
					.getSession().getServletContext().getRealPath("/imagenes/");
			// String h=req.getServletContext().getRealPath("/imagenes/");
			String nomImagen = imagen.getName();
			String ni = nomImagen.replace(".tmp", ".jpg");
			user.setFoto(ni);
			rutaAlmacenamiento = "/tmp/";
			ImageIO.write(bi, "jpg", new File(rutaAlmacenamiento + ni));
			Usuario u = dao.persistir(user);
			session.put("foto", user.getFoto());
			session.put("esAdmin", false);
			session.put("usuario", u.getId());
			req.setAttribute("usuario", user);
			return SUCCESS;
		}
	}

	@SkipValidation
	@Action(value = "borrarUsuario")
	public String borrar() {
		this.usuarioDao.borrar(user.getId());
		return SUCCESS;
	}

	@SkipValidation
	@Action(value = "verUsuario")
	public String verDatos() {
		user = (Viajero) session.get("usuario");
		return SUCCESS;
	}

	public boolean esUnMail(String string) {
		if (!string.contains("@"))
			return false;
		if (!string.contains("."))
			return false;
		if (string.indexOf('@') > string.indexOf('.'))
			return false;
		if (!Character.isLetter(string.charAt(0)))
			return false;
		String[] partes = string.split("@|\\.");
		if (partes.length != 3)
			return false;
		for (String parte : partes) {
			if (parte.length() == 0)
				return false;
			for (int i = 0; i < parte.length(); i++) {
				if (((Character) parte.charAt(i)).equals('_'))
					continue;
				if (Character.isLetterOrDigit(parte.charAt(i)))
					continue;
				return false;
			}
		}
		return true;
	}

	@SkipValidation
	@Action(value = "verViajeros", results = {
			@Result(location = "verViajeros.jsp"),
			@Result(name = "index", location = "Index", type = "redirectAction") })
	public String verSolicitudes() {
		session = (SessionMap<String, Object>) ActionContext.getContext()
				.getSession();
		if (session.get("esAdmin") != null
				&& false == (Boolean) session.get("esAdmin")) {
			Long id = (Long) session.get("usuario");
			List<Viajero> list = viajeroDAO.recuperarTodos("nombre");
			viajeros = new LinkedList<Viajero>();
			for (Viajero viajero : list) {
				if (viajero.getId() != id) {
					viajeros.add(viajero);
				}
			}
			return SUCCESS;
		} else {
			return "index";
		}
	}

	@Override
	public void validate() {
		if ((user.getNombreUsuario() == null)
				|| (user.getNombreUsuario().equals(""))) {
			addFieldError("nombreUsuario", "Debe ingresar un nombre de usuario");
		}
		if ((user.getContrasenia() == null)
				|| (user.getContrasenia().equals(""))) {
			addFieldError("pass", "Debe ingresar una contrase�a");
		} else if (!user.getContrasenia().equals(confirmPass)) {
			addFieldError("confirmPass", "Las contrasenia no coinciden");
		}
		if ((user.getNombre() == null) || (user.getNombre().equals(""))) {
			addFieldError("nombre", "Debe ingresar un nombre");
		}
		if ((user.getApellido() == null) || (user.getApellido().equals(""))) {
			addFieldError("apellido", "Debe ingresar un apellido");
		}
		if ((user.getMail() == null)
				|| (user.getMail().equals("") || !this.esUnMail(user.getMail()))) {
			addFieldError("mail",
					"Debe ingresar un email ejemplo: alejandro@hotmail.com");
		}
		if ((user.getTelefono() == null) || (user.getTelefono().equals(""))) {
			addFieldError("telefono", "Debe ingresar un telefono");
		} else {
			try {
				Integer.parseInt(user.getTelefono());
			} catch (Exception e) {
				addFieldError("telefono", "Debe ingresar un telefono");
			}
		}
		if (this.imagen == null
				|| (this.imagenContentType != null && !this.imagenContentType
						.equals("image/jpeg"))) {
			addFieldError("imagen", "Debe ingresar una imagen en formato jpg");

		}
	}
}
