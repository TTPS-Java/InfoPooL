<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<title>InfoPool</title>
</head>
<body>
    <s:url id="en" action="Index">
            <s:param name="request_locale">en_CA</s:param>
    </s:url>
    <s:a href="%{en}">Ingles</s:a>
    <s:url id="es" action="Index">
            <s:param name="request_locale">es_ES</s:param>
    </s:url>
    <s:a href="%{es}">Español</s:a>
<h2>Bienvenido, <s:property value="#request.usuario"/>!</h2>
    <img src="imagenPerfil" width="100px" height="100px" />
    <br>
	<s:a href="tablaDeRecorridosAction">
	<s:text name="menuviajero.verrecorridos" /></s:a>
	<br>
	<s:a href="recorridoNuevoAction">
	<s:text name="menuviajero.crearrecorrido" />
	</s:a>
	<br>
	<s:a href="verViajeros"
	><s:text name="menuviajero.denunciarusuario" /></s:a>
	<br>
	<s:a href="verSolicitudes">
	<s:text name="menuviajero.solicitudes" />
	</s:a>
	<br>
	<s:a href="verMisViajes" ><s:text name="menuviajero.misrecorridos" /></s:a>
	<br />
	<s:a href="verCalificacionesPendientes"><s:text name="menuviajero.calificaciones" /></s:a>
	<br>
	<s:a href="verMensajes"><s:text name="menuviajero.mensajes" /></s:a>
	<br>
	<s:a href="logout"><s:text name="menuviajero.salir" /></s:a>
</body>
</html>