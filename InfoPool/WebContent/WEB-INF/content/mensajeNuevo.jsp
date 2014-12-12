<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Publicar Mensaje</title>
</head>
<body>
	<h4>Publicar Mensaje</h4>
	<s:form action="guardarMensaje" method="post">
		<s:textarea name="texto"></s:textarea>
		<s:submit value="Publicar"></s:submit>
	</s:form>
	<s:a href="index">Volver</s:a>
</body>
</html>