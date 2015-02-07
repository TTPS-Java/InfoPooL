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
<h4>Registracion</h4>
<s:form action="editarUsuario" enctype="multipart/form-data" method="post">
<s:file name="imagen" label="Imagen"/>
<s:textfield label="Usuario"  name="nombreUsuario"></s:textfield>
<s:password label="Contraseña" name="contrasenia"></s:password>
<s:password label="Repita la contraseña" name="confirmPass"></s:password>
<s:textfield label="Telefono"  name="telefono"></s:textfield>
<s:textfield label="Nombre"  name="nombre"></s:textfield>
<s:textfield label="Apellido"  name="apellido"></s:textfield>
<s:textfield label="Mail"  name="mail"></s:textfield>

<s:submit value="Enviar"></s:submit>
</s:form>
<s:a href="Index">Volver</s:a>
</body>
</html>