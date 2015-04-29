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
<title><s:text name="evento.ver_eventos"/></title> <!--  -->
</head>
<body>
	<h4><s:text name="evento.ver_eventos"/></h4> <!--  -->
	<a href="eventoNuevo"><s:text name="evento.crear_evento"/></a> <!--  -->
	<s:if test="hasActionErrors()">
		<s:actionerror/>
	</s:if>
	<table>
		<tr>
			<th><s:text name="evento.evento"/></th><!--  -->
			<th><s:text name="evento.fecha"/></th><!--  -->
			<th><s:text name="evento.duracion"/></th> <!-- -->
			<th><s:text name="evento.hora"/></th> <!--  -->
			<th><s:text name="evento.descr"/></th> <!--  -->
			<th><s:text name="evento._lugar"/></th> <!--  -->
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
				<s:a href="%{deleteUrl}"><s:text name="evento.borrar"/></s:a> <!--  -->
			</td>
			<td>
				<s:url id="updateUrl" action="eventoNuevo">
					<s:param name="idEvento" value="%{id}"></s:param>
				</s:url>
				<s:a href="%{updateUrl}"><s:text name="evento.modificar"/></s:a> <!--  -->
			</td>
			<td>
				<s:url id="detalleUrl" action="verEvento">
					<s:param name="idEvento" value="%{id}"></s:param>
				</s:url>
				<s:a href="%{detalleUrl}"><s:text name="evento.detalle"/></s:a> <!--  -->
			</td>
		</tr>
    </s:iterator>
    </table>
    <br>
    <s:a href="Index"><s:text name="aplicacion.volver"/></s:a> <!--  -->
</body>
</html>