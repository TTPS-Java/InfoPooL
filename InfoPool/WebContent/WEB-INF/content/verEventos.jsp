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
<title>Ver Eventos</title> <!-- <s:text name="evento.ver_eventos"/> -->
</head>
<body>
	<h4>Eventos</h4> <!-- <s:text name="evento.eventos"/> -->
	<a href="eventoNuevo">Crear evento</a> <!-- <s:text name="evento.crear_evento"/> -->
	<s:if test="hasActionErrors()">
		<s:actionerror/>
	</s:if>
	<table>
		<tr>
			<th>Evento</th><!-- <s:text name="evento.evento"/> -->
			<th>Fecha</th><!-- <s:text name="evento.fecha"/> -->
			<th>Duracion</th> <!-- <s:text name="evento.duracion"/> -->
			<th>Hora</th> <!-- <s:text name="evento.hora"/> -->
			<th>Descripcion</th> <!-- <s:text name="evento.descr"/> -->
			<th>Lugar</th> <!-- <s:text name="evento.lugar"/> -->
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
					<s:param name="idEvento" value="%{id}"></s:param>
				</s:url>
				<s:a href="%{deleteUrl}">Borrar</s:a> <!-- <s:text name="evento.borrar"/> -->
			</td>
			<td>
				<s:url id="updateUrl" action="eventoNuevo">
					<s:param name="idEvento" value="%{id}"></s:param>
				</s:url>
				<s:a href="%{updateUrl}">Modificar</s:a> <!-- <s:text name="evento.modificar"/> -->
			</td>
			<td>
				<s:url id="detalleUrl" action="verEvento">
					<s:param name="idEvento" value="%{id}"></s:param>
				</s:url>
				<s:a href="%{detalleUrl}">Detalle</s:a> <!-- <s:text name="evento.detalle"/> -->
			</td>
		</tr>
    </s:iterator>
    </table>
    <br>
    <s:a href="Index">Volver</s:a> <!-- <s:text name="evento.volver"/> -->
</body>
</html>