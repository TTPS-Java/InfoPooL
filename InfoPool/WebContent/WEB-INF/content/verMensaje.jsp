<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ver mensaje</title> <%-- <s:text name="mensaje.ver_mensaje"/> --%>
</head>
<body>
<h4>Mensaje de <s:property value="#request.mensaje.de.nombre"/> <s:property value="#request.mensaje.de.apellido"/></h4> <%-- <s:text name="mensaje.mensaje_de"/> --%>
<s:url id="replyUrl" action="mensajeNuevo">
	<s:param name="idDestinatario" value="%{#request.mensaje.de.id}"></s:param>
</s:url>
<s:a href="%{replyUrl}">Responder</s:a> <%-- <s:text name="mensaje.responder"/> --%>
<br/>
<b>Asunto:</b> <s:property value="#request.mensaje.asunto"/><br/><%-- <s:text name="mensaje.asunto"/> --%>
<s:property value="#request.mensaje.contenido"/>
<br>
<s:a href="verMensajes">Volver</s:a>
</body>
</html>