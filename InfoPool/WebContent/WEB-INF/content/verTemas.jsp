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
<title>Ver temas</title>
</head>
<body>
	<h4>Temas</h4>
	<table>
		<thead>
			<tr>
				<td>Nombre</td>
				<td></td>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="temas" status="temaStatus">
				<tr>
					<td><s:property value="nombre" /></td>
					<td><s:url id="addUrl" action="addTema">
							<s:param name="id" value="%{id}"></s:param>
						</s:url> <s:a href="%{addUrl}">Agregar</s:a></td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<br>
	<s:a href="index">Volver</s:a>
</body>
</html>