<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><s:text name="mensaje.ver_mensaje"/></title> <%--  --%>
</head>
<body>
<h4><s:text name="mensaje.mensaje_de"/><s:property value="#request.mensaje.de.nombre"/> <s:property value="#request.mensaje.de.apellido"/></h4> <%--  --%>
<s:url id="replyUrl" action="mensajeNuevo">
	<s:param name="idDestinatario" value="%{#request.mensaje.de.id}"></s:param>
</s:url>
<s:a href="%{replyUrl}"><s:text name="mensaje.responder"/></s:a> <%--  --%>
<br/>
<b><s:text name="mensaje.asunto"/>:</b> <s:property value="#request.mensaje.asunto"/><br/><%--  --%>
<s:property value="#request.mensaje.contenido"/>
<br>
<s:a href="verMensajes"><s:text name = "aplicacion.volver"/></s:a>
</body>
</html>