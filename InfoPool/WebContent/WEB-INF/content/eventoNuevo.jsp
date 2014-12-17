<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Publicar Evento</title>
</head>
<body>
	<h4>Publicar Evento</h4>
	<s:form action="guardarEvento" method="post">
		<s:textfield name="nombre" label="Nombre" value="%{evento.nombre}" />
		<!--<sj:datepicker value="%{fecha}" id="date1" name="fecha" label="Fecha" displayFormat="yy-mm-dd"/>-->
		<s:textfield name="fecha" label="Fecha(dd/MM/yyyy)" value="%{evento.fecha}" /> 
		<s:textfield name="duracionDias" label="Duracion en dias" value="%{evento.duracionDias}" />
		<s:textfield name="hora" label="Hora" value="%{evento.hora}" />
		<s:textfield name="descripcion" label="Descripcion"  value="%{evento.descripcion}"/>
		<h5>Lugar</h5>
		<s:textfield name="lugar.descripcion" label="descripcion" value="%{evento.lugar.descripcion}" />
		<s:textfield name="lugar.latitud" label="Latitud" value="%{evento.lugar.latitud}"/>
		<s:textfield name="lugar.longitud" label="longitud"  value="%{evento.lugar.longitud}" />
		<s:hidden name="evento.id" value="%{evento.id}"/>
		<s:hidden name="evento.lugar.id" value="%{evento.lugar.id}"/>
		<s:submit value="Publicar"></s:submit>
	</s:form>
	<s:a href="index">Volver</s:a>
	<%=request.getAttribute("evento") %>
</body>
</html>
	