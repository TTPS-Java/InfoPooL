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
<title><s:text name="mensaje.ver_mensajes"/></title>
</head>
<body>
	<h4><s:text name="mensaje.mensajes"/></h4> <%--  --%>
	<a href="listarDestinatarios"><s:text name="mensaje.mensaje_nuevo"/></a><%--   --%>
	<s:if test="hasActionErrors()">
		<s:actionerror/>
	</s:if>
	<table>
		<tr>
			<th><s:text name="mensaje.de"/></th><%--  --%>
			<th><s:text name="mensaje.asunto"/></th><%--  --%>
			<th><s:text name="mensaje.fecha"/></th> <%--  --%>
			<th></th>
			<th></th>
			<th></th>
		</tr>
	<s:iterator value="mensajes" status="mensajeStatus">
		<tr>
			<td><s:property value="de.nombre" /> <s:property value="de.apellido" /></td>
			<td><s:property value="asunto" /></td>
			<td><s:date name="fecha" format="dd/MM/yyyy"/></td>
			<td>
				<s:url id="detalleUrl" action="verMensaje">
					<s:param name="idMensaje" value="%{id}"></s:param>
				</s:url>
				<s:a href="%{detalleUrl}"><s:text name="mensaje.leer"/></s:a> <!--  -->
			</td>
			<td>
				<s:url id="replyUrl" action="mensajeNuevo">
					<s:param name="idDestinatario" value="%{de.id}"></s:param>
				</s:url>
				<s:a href="%{replyUrl}"><s:text name="mensaje.responder"/></s:a> <%--  --%>
			</td>
			<td>
				<s:url id="deleteUrl" action="borrarMensaje">
					<s:param name="idMensaje" value="%{id}"></s:param>
				</s:url>
				<s:a href="%{deleteUrl}"><s:text name="mensaje.borrar"/></s:a> <%--  --%>
			</td>
		</tr>
    </s:iterator>
    </table>
    <br>
    <s:a href="Index"><s:text name = "aplicacion.volver"/></s:a>
</body>
</html>