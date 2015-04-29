<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<s:head></s:head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><s:text name="denunciar.titulo"/></title>
</head>
<body>
	<h4><s:text name="denunciar.titulo" /></h4>
	<s:text name="denunciar.usuario"/>: ${denunciado.getNombre()} ${denunciado.getApellido()}
	<s:form action="guardarDenuncia">
		<s:textarea key="denunciar.contenido" name="texto"></s:textarea>
		<s:hidden name="id" value="%{denunciado.id}"></s:hidden>
		<s:submit key="denunciar.enviar"/>
	</s:form>
	<br>
	<s:a href="Index"><s:text name = "aplicacion.volver"/></s:a>
</body>
</html>