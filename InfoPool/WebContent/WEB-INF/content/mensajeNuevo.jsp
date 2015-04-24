<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<s:head></s:head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Nuevo mensaje</title> <%-- <s:text name="mensaje.nuevo_mensaje"/> --%>
</head>
<body>
	<h4>Nuevo mensaje</h4> <%-- <s:text name="mensaje.nuevo_mensaje"/> --%>
	Para:<s:property value="#session.destinatario.apellido"/>&nbsp;
<s:property value="#session.destinatario.nombre"/> <%-- <s:text name="mensaje.para"/> --%>
	<s:form action="guardarMensaje">
		<s:textfield label="Asunto" name="asunto"></s:textfield> <%-- <s:textfield key="mensaje.asunto" name="mensaje.asunto"/> --%>
		<s:textarea label="Contenido" name="contenido"></s:textarea> <%-- <s:textfield key="mensaje.contenido" name="mensaje.asunto"/> --%>
		<s:submit value="Enviar"></s:submit> <%-- <s:submit key="mensaje.enviar"/> --%>
	</s:form>
	<br>
	<s:a href="verMensajes">Volver</s:a>
</body>
</html>