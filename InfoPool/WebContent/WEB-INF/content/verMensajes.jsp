<%@page import="com.sun.xml.internal.bind.CycleRecoverable.Context"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<s:head/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ver Mensajes</title> <%-- <s:text name="mensaje.ver_mensajes"/> --%>
</head>
<body>
	<h4>Mensajes</h4> <%-- <s:text name="mensaje.mensajes"/> --%>
	<a href="listarDestinatarios">Mensaje nuevo</a><%--  <s:text name="mensaje.mensaje_nuevo"/> --%>
	<s:if test="hasActionErrors()">
		<s:actionerror/>
	</s:if>
	<table>
		<tr>
			<th>De</th><%-- <s:text name="mensaje.de"/> --%>
			<th>Asunto</th><%-- <s:text name="mensaje.asunto"/> --%>
			<th>Leido</th> <%-- <s:text name="mensaje.leido"/> --%>
			<th>Fecha</th> <%-- <s:text name="mensaje.fecha"/> --%>
			<th>Evento</th> <%-- <s:text name="mensaje.evento"/> --%>
			<th></th>
			<th></th>
			<th></th>
		</tr>
	<s:iterator value="mensajes" status="mensajeStatus">
		<tr>
			<td><s:property value="de.nombre" /> <s:property value="de.apellido" /></td>
			<td><s:property value="asunto" /></td>
			<td><s:property value="leido" /></td>
			<td><s:date name="fecha" format="dd/MM/yyyy"/></td>
			<td><s:property value="evento.nombre" /></td>
			<td>
				<s:url id="detalleUrl" action="verMensaje">
					<s:param name="idMensaje" value="%{id}"></s:param>
				</s:url>
				<s:a href="%{detalleUrl}">Leer</s:a> <!-- <s:text name="mensaje.leer"/> -->
			</td>
			<td>
				<s:url id="replyUrl" action="mensajeNuevo">
					<s:param name="idDestinatario" value="%{de.id}"></s:param>
				</s:url>
				<s:a href="%{replyUrl}">Responder</s:a> <%-- <s:text name="mensaje.responder"/> --%>
			</td>
			<td>
				<s:url id="deleteUrl" action="borrarMensaje">
					<s:param name="idEvento" value="%{id}"></s:param>
				</s:url>
				<s:a href="%{deleteUrl}">Borrar</s:a> <%-- <s:text name="evento.borrar"/> --%>
			</td>
		</tr>
    </s:iterator>
    </table>
    <br>
    <s:a href="Index">Volver</s:a> <%-- <s:text name="evento.volver"/> --%>
</body>
</html>