<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mis Solicitudes</title>
<s:head/>
</head>
<body>
<h4>Solicitudes pendientes: ${solicitudes.size() }</h4>
<table>
		<tr>
			<th>Solicitante</th>
			<th>Asientos</th>
			<th>Fecha</th>
			<th>Desde</th>
			<th>Hasta</th>
			<th>Evento</th>
			<th></th>
			<th></th>
		</tr>
	<s:iterator value="solicitudes" status="soliciStatus">
		<tr>
			<td><s:property value="solicitante.nombre" /> <s:property value="solicitante.apellido" /></td>
			<td><s:property value="cantidadAsientos"/>
			<td><s:date name="viaje.fecha" format="dd/MM/yyyy" />
			<td><s:property value="viaje.desde.descripcion" /></td>
			<td><s:property value="viaje.hasta.descripcion" /></td>
			<td><s:property value="evento" /></td>
			<td>
				<s:url id="aceptarUrl" action="aceptarSolicitud">
					<s:param name="id" value="%{id}"></s:param>
				</s:url>
				<s:a href="%{aceptarUrl}">Aceptar</s:a>
			</td>
			<td>
				<s:url id="rechazarUrl" action="rechazarSolicitud">
					<s:param name="id" value="%{id}"></s:param>
				</s:url>
				<s:a href="%{rechazarUrl}">Rechazar</s:a>
			</td>
		</tr>
    </s:iterator>
    </table>
    <br>
    <s:a href="Index">Volver</s:a>
</body>
</html>