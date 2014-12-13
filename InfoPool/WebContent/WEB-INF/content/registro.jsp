<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<s:head/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<s:if test="#session.usuario==null">
<title>Registracion</title>
</s:if>
<s:else>
<title>Editar datos</title>
</s:else>
</head>
<body>
<s:if test="#session.usuario==null">
<h4>Registracion</h4>
</s:if>
<s:else>
<h4>Editar datos</h4>
</s:else>
<s:form action="editarUsuario">
<s:textfield label="Usuario" value="%{ #session.usuario.nombreUsuario }" name="nombreUsuario"></s:textfield>
<s:password label="Contraseña" name="contrasenia"></s:password>
<s:password label="Repita la contraseña" name="confirmPass"></s:password>
<s:textfield label="Telefono" value="%{ #session.usuario.telefono }" name="telefono"></s:textfield>
<s:textfield label="Nombre" value="%{ #session.usuario.nombre }" name="nombre"></s:textfield>
<s:textfield label="Apellido" value="%{ #session.usuario.apellido }" name="apellido"></s:textfield>
<s:textfield label="Mail" value="%{ #session.usuario.mail }" name="mail"></s:textfield>
<s:hidden value="%{ #session.usuario.id }" name="id"></s:hidden>
<s:submit value="Enviar"></s:submit>
</s:form>
<s:a href="index">Volver</s:a>
</body>
</html>