<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<s:head></s:head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Nueva solicitud</title>
</head>
<body>
	<h4>Solicitud nueva</h4>
	Desde: ${viaje.getDesde().getDescripcion()}
	<br> Hasta: ${viaje.getHasta().getDescripcion()}
	<br> Fecha:
	<s:date name="viaje.fecha" format="dd/MM/yyyy" />
	<br> Quedan
	<s:property value="%{#request.viaje.asientosLibres }" />
	asientos.
	<s:form action="guardarSolicitud">
		<s:textfield type="number" label="Cantidad de asientos"
			name="cantAsientos"></s:textfield>
		<s:hidden name="id" value="%{#request.viaje.id}"></s:hidden>
		<s:submit value="Pedir"></s:submit>
	</s:form>
	<br>
	<s:a href="Index">Volver</s:a>
</body>
</html>