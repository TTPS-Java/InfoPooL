<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><s:text  name="viajesestoy.titulo" /></title>
</head>
<body>
<s:a href="Index"><s:text name = "aplicacion.volver"/></s:a>
<h2><s:text  name="viajesestoy.titulo" /> : ${solicitudes.size()}</h2>
<table>
     <tr>
      <th><s:text  name="misviajes.fecha" /></th>
      <th><s:text  name="misviajes.horapartida" /></th>
      <th><s:text  name="misviajes.horavuelta" /></th>
      <th><s:text  name="misviajes.asientoslibres" /></th>
      <th><s:text  name="misviajes.desde" /></th>
      <th><s:text  name="misviajes.hasta" /></th>
      <th></th>
     </tr>
       <s:iterator value="solicitudes" >
          <tr>
           <td><s:property value="viaje.fecha" /></td>
           <td><s:property value="viaje.horaPartida" /></td>
           <td><s:property value="viaje.horaVuelta" /></td>
           <td><s:property value="cantidadAsientos" /></td>
           <td><s:property value="viaje.desde.descripcion" /></td>
           <td><s:property value="viaje.hasta.descripcion" /></td>
           <td>
                 <s:url id="borrarViaje" action="borrarViajeEstoy">
                 <s:param name="idSolicitud" value="%{id}"></s:param>
                 </s:url>
                 <s:a href="%{borrarViaje}">|X|<s:text  name="viajesestoy.salirdeviaje" /></s:a>
           </td>                                              
          </tr>
       </s:iterator>
   </table>
</body>
</html>