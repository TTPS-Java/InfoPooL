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
<title>Ver mensajes</title>
</head>
<body>
	<h4>Mensajes</h4>
	<table>
		<tr>
			<th>Mensaje</th>
			<th></th>
		</tr>
	<s:iterator value="mensajes" status="msjStatus">
		<tr>
			<td><s:property value="texto" /></td>
			<td>
				<s:url id="deleteUrl" action="borrarMensaje">
					<s:param name="id" value="%{id}"></s:param>
				</s:url>
				<s:a href="%{deleteUrl}">Borrar</s:a>
			</td>
		</tr>
    </s:iterator>
    </table>
    <br>
    <s:a href="index">Volver</s:a>
</body>
</html>