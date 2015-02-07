<%@ page language="java" contentType="text/html; charset=UTF8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF8">
<title>
<s:if test="#session.nuevo!=null">
Registracion
</s:if>
<s:else>
Edicion
</s:else>
 Aceptada</title>
</head>
<body>
<s:property value="#request.usuario.apellido"/>&nbsp;
<s:property value="#request.usuario.nombre"/>
 se ha guardado exitosamente
 <br>
 <s:a href="Index">Index</s:a>
</body>
</html>