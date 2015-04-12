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
	Para: ${destinatario.getNombre()} ${destinatario.getApellido()} <%-- <s:text name="mensaje.para"/> --%>
	<s:form action="guardarMensaje">
		<s:textfield label="Asunto" name="mensaje.asunto"></s:textfield> <%-- <s:textfield key="mensaje.asunto" name="mensaje.asunto"/> --%>
		<s:select  headerKey="-1" headerValue="Sin evento asociado" id="selectEvento" key="nuevorecorrido.eventoasociado" value="%{idElegido}" list="eventos"  listKey="id" listValue="nombre" name="idEvento"/>
		<s:textarea label="Contenido" name="mensaje.texto"></s:textarea> <%-- <s:textfield key="mensaje.contenido" name="mensaje.asunto"/> --%>
		<s:hidden name="id" value="%{destinatario.id}"></s:hidden> <%-- <s:submit key="mensaje.enviar"/> --%>
		<s:submit value="Enviar"></s:submit>
	</s:form>
	<br>
	<s:a href="Index">Volver</s:a>
</body>
</html>