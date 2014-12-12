<%@page import="com.sun.xml.internal.bind.CycleRecoverable.Context"%>
<%@page import="java.util.*"%>
<%@page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ver administradores</title>
</head>
<body>
	<h4>Administradores</h4>
	<table>
		<thead>
			<tr>
				<th>Nombre</th>
				<th>Cargo</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="admins" status="adminStatus">
				<tr>
					<td><s:property value="nombre" /></td>
					<td><s:property value="cargo" /></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>

	<br>
	<s:a href="index">Volver</s:a>
</body>
</html>