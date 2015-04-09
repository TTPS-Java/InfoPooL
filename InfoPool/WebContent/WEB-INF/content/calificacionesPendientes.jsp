<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Calificaciones pendientes</title>
</head>
<body>
<s:a href="Index">Volver</s:a>
<h3>Calificaciones pendientes</h3>
 <table><tr><th>Usuario</th><th>Fecha</th><th>Lugar de llegada</th><th>Calificar</th><th>Denunciar</th></tr>
<s:iterator  value="calificacionesPendientes">
    <tr>
       <td>
          <s:property value="usuario"/>
       </td>
       <td>
          <s:property value="fecha"/>
       </td>
       <td>
          <s:property value="nombreLugarDestino"/>
       </td>
      <td>
           <s:url id="calificacionUrl" action="calificarUsuario">
             <s:param name="idCalificado" value="%{idCalificado}"></s:param>
             <s:param name="idViaje" value="%{idViaje}"></s:param>
           </s:url>
           <s:a href="%{calificacionUrl}">Calificar</s:a>
      </td>
      <td>
         <s:url id="denunciarUrl" action="denunciar">
						<s:param name="id" value="%{idCalificado}"></s:param>
		 </s:url> <s:a href="%{denunciarUrl}">Denunciar</s:a>
      </td>
      
    </tr>
</s:iterator>
</table>
</body>
</html>