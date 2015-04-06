<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<title>InfoPool</title>
</head>
<body>
<h2>Bienvenido, <s:property value="#request.usuario"/>!</h2>

	<br>
	<s:a href="#">ABM sistema</s:a>
	<br>
	<s:a href="verEventos">ABM eventos</s:a>
	<br>
	<s:a href="verViajeros">Administracion viajeros</s:a>
	<br>
	<s:a href="logout">Salir</s:a>
</body>
</html>