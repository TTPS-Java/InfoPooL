<%@ page language="java" contentType="text/html; charset=UTF8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<title><s:text name="registro.aceptada"></s:text></title>
</head>
<body>
<s:property value="#request.usuario.apellido"/>&nbsp;
<s:property value="#request.usuario.nombre"/>&nbsp;<s:text name="registro.correcto"></s:text>
 <br>
 <s:a href="Index"><s:text name="registro.inicio"></s:text></s:a>
</body>
</html>