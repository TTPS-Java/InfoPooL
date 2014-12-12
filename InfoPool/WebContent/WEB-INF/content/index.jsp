<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<title>Blog de mensajes</title>
<link rel="shortcut icon" type="image/x-icon" href="favicon.ico"/>
</head>
<body>
<h2>Bienvenido, <s:property value="#session.usuario.nombre"/>!</h2>
	<s:a href="verUsuario">Ver mis datos</s:a>
	<br>
	<s:a href="verMensajes">Ver todos los mensajes</s:a>
	<br>
	<s:a href="verMisMensajes">Ver mis mensajes</s:a>
	<br>
	<s:a href="verTemas">Ver todos los temas</s:a>
	<br>
	<s:a href="verMisTemas">Ver mis temas</s:a>
	<br>
	<s:a href="mensajeNuevo">Publicar Mensajes</s:a>
	<br>
	<s:if test="#session.admin">
	<s:a href="verAdministradores">Administrar Administradores</s:a>
	<br>
	</s:if>
	<s:a href="logout">Salir</s:a>
</body>
</html>