<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><s:text  name="misviajes.titulo" /></title>
</head>
<body>
   <s:a href="Index"><s:text name="aplicacion.volver"/></s:a>
   <h3><s:text  name="misviajes.titulo" /></h3>
   <table>
     <tr>
      <th><s:text  name="misviajes.fecha" /></th>
      <th><s:text  name="misviajes.horapartida" /></th>
      <th><s:text  name="misviajes.horavuelta" /></th>
      <th><s:text  name="misviajes.asientoslibres" /></th>
      <th><s:text  name="misviajes.desde" /></th>
      <th><s:text  name="misviajes.hasta" /></th>
      <th><s:text  name="misviajes.borrado" /></th>
      <th><s:text  name="misviajes.viajeros" /></th><th></th><th></th>
     </tr>
       <s:iterator value="misViajes" var="v" >
          <tr>
           <td><s:property value="fecha" /></td>
           <td><s:property value="horaPartida" /></td>
           <td><s:property value="horaVuelta" /></td>
           <td><s:property value="asientosLibres" /></td>
           <td><s:property value="desde.descripcion" /></td>
           <td><s:property value="hasta.descripcion" /></td>
           <td>
              <s:if test="#v.visible == 1">
                <s:text  name="misviajes.no" />
              </s:if>
              <s:else>
                  <s:text  name="misviajes.si" />
              </s:else> 
           </td>
           <td>
             <s:url id="viajerosEn" action="usuariosEnViaje">
               <s:param name="idViaje" value="%{id}"></s:param>
           </s:url>
          
           <s:a href="%{viajerosEn}"><s:text  name="misviajes.verviajeros" /></s:a>
           </td>
           <td>
           <s:if test="#v.visible == 1">
                 <s:url id="borrarViaje" action="borrarViaje">
                 <s:param name="idViaje" value="%{id}"></s:param>
                 </s:url>
                 <s:a href="%{borrarViaje}">|X|<s:text  name="misviajes.borrar" /></s:a>
                 </s:if>
           </td>
           
           
           <td><s:url id="modificarViaje" action="modificarViaje">
               <s:param name="idViaje" value="%{id}"></s:param>
           </s:url>
           <s:a href="%{modificarViaje}"><s:text  name="misviajes.modificarver" /></s:a></td>
          </tr>
       </s:iterator>
   </table>
</body>
</html>