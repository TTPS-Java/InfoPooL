<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mensaje enviado</title> <!-- <s:text name="mensaje.mensaje_enviado"/> -->
</head>
<body>
	<h4>Se ha enviado el mensaje.</h4> <%-- <s:text name="mensaje.mensaje_enviado_msj"/> --%>
	<br>
	<s:a href="verMensajes">Volver</s:a> <%-- <s:text name="mensaje.volver"/> --%>
</body>
</html>