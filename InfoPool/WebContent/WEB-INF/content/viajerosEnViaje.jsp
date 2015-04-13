<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>viajeros en el viaje</title>
</head>
<body>
<s:a href="verMisViajes">volver</s:a>
<table>
<tr><th>Usuario</th><th>nombre</th><th>apellido</th></tr>
  <s:iterator value="viajerosEnViaje">
    <tr>
      <td><s:property value="nombreUsuario"/></td><td><s:property value="nombre"/></td><td><s:property value="apellido"/></td>
    </tr>
  </s:iterator>
 </table>
</body>
</html>