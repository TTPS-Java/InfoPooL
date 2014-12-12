<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Datos personales</title>
</head>
<body>
	<h4>Datos personales</h4>
	<table>
		<tr>
			<td>Usuario</td>
			<td><s:property value="#session.usuario.nombre" /></td>
		</tr>
		<tr>
			<td>Nick</td>
			<td><s:property value="#session.usuario.nick" /></td>
		</tr>
		<tr>
			<td>Nombre</td>
			<td><s:property value="#session.usuario.nombreReal" /></td>
		</tr>
		<tr>
			<td>Apellido</td>
			<td><s:property value="#session.usuario.apellido" /></td>
		</tr>
		<tr>
			<td>Mail</td>
			<td><s:property value="#session.usuario.email" /></td>
		</tr>
		<s:if test="#session.admin">
			<tr>
				<td>Cargo</td>
				<td><s:property value="#session.usuario.cargo" /></td>
			</tr>
		</s:if>
	</table>
	<s:a href="registro">Cambiar datos</s:a>
	<br>
	<s:a href="index">Volver</s:a>
</body>
</html>