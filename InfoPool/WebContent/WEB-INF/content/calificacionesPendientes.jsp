<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><s:text name="calificacionespendietes.titulo" /></title>
</head>
<body>
<s:a href="Index"><s:text name = "aplicacion.volver"/></s:a>
<h3><s:text name="calificacionespendietes.titulo" /></h3>
 <table><tr><th><s:text name="calificacionespendietes.usuario" /></th>
 <th><s:text name="calificacionespendietes.fecha" /></th>
 <th><s:text name="calificacionespendietes.lugarllegada" /></th>
 <th><s:text name="calificacionespendietes.calificar" /></th>
 <th><s:text name="calificacionespendietes.denunciar" /></th></tr>
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
           <s:a href="%{calificacionUrl}"><s:text name="calificacionespendietes.calificar" /></s:a>
      </td>
      <td>
         <s:url id="denunciarUrl" action="denunciar">
						<s:param name="id" value="%{idCalificado}"></s:param>
		 </s:url> <s:a href="%{denunciarUrl}"><s:text name="calificacionespendietes.denunciar" /></s:a>
      </td>
      
    </tr>
</s:iterator>
</table>
</body>
</html>