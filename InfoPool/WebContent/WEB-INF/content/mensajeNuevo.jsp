<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<s:head></s:head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><s:text name="mensaje.mensaje_nuevo"/></title> <%--  --%>
</head>
<body>
	<h4><s:text name="mensaje.mensaje_nuevo"/></h4> 
	<s:text name="mensaje.para"/>:<s:property value="#session.destinatario.apellido"/>&nbsp;
<s:property value="#session.destinatario.nombre"/> <%-- <s:text name="mensaje.para"/> --%>
	<s:form action="guardarMensaje">
		<s:textfield key="mensaje.asunto" name="mensaje.asunto"/>
		<s:textfield key="mensaje.contenido" name="mensaje.contenido"/>
		<s:submit key="mensaje.enviar"/>
	</s:form>
	<br>
	<s:a href="verMensajes"><s:text name = "aplicacion.volver"/></s:a>
</body>
</html>