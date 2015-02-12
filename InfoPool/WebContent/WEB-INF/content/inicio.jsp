<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<title>InfoPool</title>
</head>
<body>
<h2>Bienvenido, <s:property value="#request.usuario"/>!</h2>
    <img src="imagenPerfil" width="100px" height="100px" />
    <br>
	<s:a href="tablaDeRecorridosAction">Ver recorridos</s:a>
	<br>
	<s:a href="recorridoNuevoAction">Crear recorrido</s:a>
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