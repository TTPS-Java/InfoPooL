<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<title>InfoPool</title>
<link rel="shortcut icon" type="image/x-icon" href="favicon.ico"/>
</head>
<body>
<h2>Bienvenido, <s:property value="#session.usuario.nombreUsuario"/>!</h2>
	<s:a href="#">Mis recorridos</s:a>
	<br>
	<s:a href="#">Buscar recorridos</s:a>
	<br>
	<s:a href="#">Solicitudes</s:a>
	<br>
	<s:a href="#">Calificaciones</s:a>
	<br>
	<s:a href="#">Mensajes</s:a>
	<br>
	<s:a href="logout">Salir</s:a>
</body>
</html>