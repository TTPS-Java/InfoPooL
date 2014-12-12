<%@page import="com.sun.xml.internal.bind.CycleRecoverable.Context"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ver Eventos</title>
</head>
<body>
	<h4>Eventos</h4>
	<table>
		<tr>
			<th>Evento</th>
			<th>Fecha</th>
			<th>Duracion</th>
			<th>Hora</th>
			<th>Descripcion</th>
			<th>Lugar</th>
			<th></th>
			<th></th>
		</tr>
	<s:iterator value="eventos" status="eventoStatus">
		<tr>
			<td><s:property value="nombre" /></td>
			<td><s:property value="fecha" /></td>
			<td><s:property value="duracionDias" /></td>
			<td><s:property value="hora" /></td>
			<td><s:property value="descripcion" /></td>
			<td><s:property value="Lugar.descripcion" /></td>
			<td>
				<s:url id="deleteUrl" action="borrarEvento">
					<s:param name="id" value="%{id}"></s:param>
				</s:url>
				<s:a href="%{deleteUrl}">Borrar</s:a>
			</td>
			<td>
				<s:url id="updateUrl" action="eventoNuevo">
					<s:param name="id" value="%{id}"></s:param>
				</s:url>
				<s:a href="%{updateUrl}">Modificar</s:a>
			</td>
		</tr>
    </s:iterator>
    </table>
    <br>
    <s:a href="index">Volver</s:a>
</body>
</html>