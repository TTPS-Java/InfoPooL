<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<s:head></s:head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Nueva denuncia</title>
</head>
<body>
	<h4>Denuncia nueva</h4>
	Usuario: ${denunciado.getNombre()} ${denunciado.getApellido()}
	<s:form action="guardarDenuncia">
		<s:textarea label="Contenido" name="texto"></s:textarea>
		<s:hidden name="id" value="%{denunciado.id}"></s:hidden>
		<s:submit value="Enviar"></s:submit>
	</s:form>
	<br>
	<s:a href="Index">Volver</s:a>
</body>
</html>