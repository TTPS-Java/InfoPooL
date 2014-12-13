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
	<s:iterator value="mensajes" status="msjStatus">
	    <s:property value="autor.nombre" /> dijo: 
	    <s:property value="texto" />
	    <s:if test="!#msjStatus.last"><br></s:if>
    </s:iterator>
    <br>
    <s:a href="index">Volver</s:a>
</body>
</html>