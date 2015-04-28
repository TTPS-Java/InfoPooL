<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><s:text name="mensaje.elegir_destinatario"/></title>
<s:head />
</head>
<body>
	<h4><s:text name="mensaje.elegir_destinatario"/></h4>
	<s:actionerror/>
	<table>
		<tr>
			<th><s:text name = "registro.nombre"/></th>
			<th><s:text name = "registro.usuario"/></th>
			<th></th>
		</tr>
		<s:iterator value="viajeros" status="viajeroStatus">
			<tr>
				<td><s:property value="nombre" /> <s:property value="apellido" /></td>
				<td><s:property value="nombreUsuario" />
				<td><s:url id="escribirUrl" action="mensajeNuevo">
						<s:param name="idDestinatario" value="%{id}"></s:param>
					</s:url> <s:a href="%{escribirUrl}"><s:text name = "mensaje.elegir"/></s:a>
				</td>
			</tr>
		</s:iterator>
	</table>
	<br>
	<s:a href="Index"><s:text name = "aplicacion.volver"/></s:a>
</body>
</html>