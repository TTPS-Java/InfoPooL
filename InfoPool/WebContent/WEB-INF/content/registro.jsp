<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<s:head/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registracion</title>
</head>
<body>
<s:url id="en" action="registro">
	<s:param name="request_locale">en</s:param>
</s:url>
<s:a href="%{en}">English</s:a>
<s:url id="es" action="registro">
	<s:param name="request_locale">es</s:param>
</s:url>
<s:a href="%{es}">Español</s:a>
<h4><s:text name="registro.registracion"></s:text></h4>
<s:form action="editarUsuario" enctype="multipart/form-data" method="post">
<s:file name="imagen" key="registro.imagen"/>
<s:textfield key="registro.usuario"  name="nombreUsuario"></s:textfield>
<s:password key="registro.contrasenia" name="contrasenia"></s:password>
<s:password key="registro.confirme" name="confirmPass"></s:password>
<s:textfield key="registro.telefono"  name="telefono"></s:textfield>
<s:textfield key="registro.nombre" name="nombre"></s:textfield>
<s:textfield key="registro.apellido"  name="apellido"></s:textfield>
<s:textfield key="registro.mail" name="mail"></s:textfield>

<s:submit key="registro.enviar"></s:submit>
</s:form>
<s:a href="Index"><s:text name="aplicacion.volver"></s:text></s:a>
</body>
</html>