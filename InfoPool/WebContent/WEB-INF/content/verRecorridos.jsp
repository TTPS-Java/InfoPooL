<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ver recorridos</title>
<!-- Struts2 JQuery -->
<sj:head locale="es" jqueryui="true" jquerytheme="redmond"/>
</head>
<body>
			<s:url id="remoteurl" action="datosAction"/>
			<sjg:grid	
				id="gridtable"
				caption="Usuarios"
				dataType="json"
				href="%{remoteurl}"
				pager="true"
				gridModel="gridModel"
				rowList="10,15,20"
				rowNum="10"
				rownumbers="true"
				autowidth="true">
				<sjg:gridColumn name="nombreUsuario" index="nombreUsuario" title="nombreUsuario" sortable="true"/>
				<sjg:gridColumn name="contrasenia" index="contrasenia" title="contrasenia" sortable="true"/>
				<sjg:gridColumn name="id" index="id" title="id" sortable="true"/>
			</sjg:grid>
</body>
</html>