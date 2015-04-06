<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administrar viajeros</title>
</head>
<body>
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
				<td><s:url id="detalleUsuario" action="detalleViajero">
						<s:param name="id" value="%{id}"></s:param>
					</s:url> <s:a href="%{detalleUsuario}">DetalleUsuario</s:a>
			    </td>
			</tr>
		</s:iterator>
	</table>
</body>
</html>