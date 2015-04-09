<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Denuncias a viajero</title>
</head>
<body>
   <s:a href="Index">Volver</s:a>
  <h3>Denuncias de viajero</h3>
  <br />
  <s:url id="bloquear" action="bloquearViajero">
		<s:param name="id" value="%{viajero.id}"></s:param>
  </s:url> 
  <s:a href="%{bloquear}">|Bloquear usuario |</s:a>
  
  
   <s:url id="borrar" action="borrarViajesDeViajero">
		<s:param name="id" value="%{viajero.id}"></s:param>
  </s:url> 
  <s:a href="%{borrar}">Borrar viajes de <s:property value="viajero.nombreUsuario" /> |</s:a>
  
  
   <h3>Usuario : <s:property value="viajero.nombreUsuario" /></h3>
   <h3>Nombre y apellido: <s:property value="viajero.Nombre"/> <s:property value="viajero.apellido"/></h3>
   <h3>Denuncias recibidas:</h3>
   <table>
      <tr><th>Usuario</th><th>Contenido</th></tr>
      <s:iterator value="denunciasDeUsuario">
      <tr>
        <td><s:property value="autor.nombreUsuario"/></td>
        <td><s:property value="contenido"/></td>
      </tr>
      </s:iterator>
   </table>
</body>
</html>