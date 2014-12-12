<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<title>InfoPool</title>
<link rel="shortcut icon" type="image/x-icon" href="favicon.ico"/>
</head>
<body>
<h2>Bienvenido, <s:property value="#session.usuario.nombreUsuario"/>!</h2>

	<br>
	<s:a href="#">ABM sistema</s:a>
	<br>
	<s:a href="verEventos">ABM eventos</s:a>
	<br>
	<s:a href="#">Administracion viajeros</s:a>
	<br>
	<s:a href="logout">Salir</s:a>
</body>
</html>