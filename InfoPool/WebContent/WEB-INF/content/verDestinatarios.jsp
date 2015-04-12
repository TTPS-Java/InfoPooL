<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Elegir destinatario</title> <%-- <s:text name="mensaje.elegir_destinatario"/> --%>
<s:head />
</head>
<body>
	<h4>Elegir destinatario</h4> <%-- <s:text name="mensaje.elegir_destinatario"/> --%>
	<s:actionerror/>
	<table>
		<tr>
			<th>Nombre</th> <%-- <s:text name="mensaje.nombre"/> --%>
			<th>Usuario</th> <%-- <s:text name="mensaje.usuario"/> --%>
			<th></th>
		</tr>
		<s:iterator value="viajeros" status="viajeroStatus">
			<tr>
				<td><s:property value="nombre" /> <s:property value="apellido" /></td>
				<td><s:property value="nombreUsuario" />
				<td><s:url id="escribirUrl" action="mensajeNuevo">
						<s:param name="idDestinatario" value="%{id}"></s:param>
					</s:url> <s:a href="%{escribirUrl}">Elegir</s:a>
				</td>
			</tr>
		</s:iterator>
	</table>
	<br>
	<s:a href="Index">Volver</s:a> <%-- <s:text name="mensaje.volver"/> --%>
</body>
</html>