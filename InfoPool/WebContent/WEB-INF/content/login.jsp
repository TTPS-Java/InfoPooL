<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
<h4>Login</h4>
<s:form action="procesarLogin">
<s:textfield key="login.usuario" name="nombre"></s:textfield>
<s:password key="login.contrasenia" name="pass"></s:password>
<s:submit key="login.entrar"></s:submit>
</s:form>
<s:a href="Index"><s:text name="aplicacion.volver"/></s:a>

</body>
</html>