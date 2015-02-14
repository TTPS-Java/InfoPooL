<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Todos los viajeros</title>
<s:head />
</head>
<body>
	<h4>Todos los viajeros</h4>
	<s:actionerror/>
	<table>
		<tr>
			<th>Nombre</th>
			<th>Usuario</th>
			<th></th>
		</tr>
		<s:iterator value="viajeros" status="viajeroStatus">
			<tr>
				<td><s:property value="nombre" /> <s:property value="apellido" /></td>
				<td><s:property value="nombreUsuario" />
				<td><s:url id="denunciarUrl" action="denunciar">
						<s:param name="id" value="%{id}"></s:param>
					</s:url> <s:a href="%{denunciarUrl}">Denunciar</s:a></td>
			</tr>
		</s:iterator>
	</table>
	<br>
	<s:a href="Index">Volver</s:a>
</body>
</html>