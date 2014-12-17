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
		<s:textfield name="nombre" label="Nombre" value="%{nombre}" />
		<!--<sj:datepicker value="%{fecha}" id="date1" name="fecha" label="Fecha" displayFormat="yy-mm-dd"/>-->
		<s:textfield name="fecha" label="Fecha(dd/MM/yyyy)" value="%{fecha}" /> 
		<s:textfield name="duracionDias" label="Duracion en dias" value="%{duracionDias}" />
		<s:textfield name="hora" label="Hora" value="%{hora}" />
		<s:textfield name="descripcion" label="Descripcion"  value="%{descripcion}"/>
		<h5>Lugar</h5>
		<s:textfield name="lugar.descripcion" label="descripcion" value="%{lugar.descripcion}" />
		<s:textfield name="lugar.latitud" label="Latitud" value="%{lugar.latitud}"/>
		<s:textfield name="lugar.longitud" label="longitud"  value="%{lugar.longitud}" />
		<s:hidden name="id" value="%{id}"/>
		<s:submit value="Publicar"></s:submit>
	</s:form>
	<s:a href="index">Volver</s:a>
	<%=request.getAttribute("evento") %>
</body>
</html>
	