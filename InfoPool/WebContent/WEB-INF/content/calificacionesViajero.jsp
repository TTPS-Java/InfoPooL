<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Calificaciones de usuario</title>
</head>
<body>
    <s:a href="tablaDeRecorridosAction">Volver</s:a>
     <h4 style="color:red">calificaciones negativas:<s:property value="votosNegativos" /></h4>
     <h4 style="color:green">calificaciones positivas:<s:property value="votosPositivos" /></h4>
     <h3>Nombre de Usuario:<s:property value="viajero.nombreUsuario"/></h3>
     <h3>Nombre :<s:property value="viajero.Nombre"/></h3>
     <h3>Apellido:<s:property value="viajero.Apellido"/></h3>
     <h3>Telefono:<s:property value="viajero.telefono"/></h3>
     <table>
        <tr><th>Usuario</th><th></th></tr>
     <s:iterator value="calificaciones">
        <tr>
        <td><s:property value="autor.nombreUsuario"/> </td>
        <td><s:property value="comentario"/> </td>
        </tr>
     </s:iterator>
    </table>
    
</body>
</html>