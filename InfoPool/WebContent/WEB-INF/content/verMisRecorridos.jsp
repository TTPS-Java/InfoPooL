<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Mis recorridos</title>
</head>
<body>
   <s:a href="Index">Volver</s:a>
   <h3>Mis recorridos</h3>
   <table>
     <tr>
      <th>fecha</th><th>hora partida</th><th>hora vuelta</th><th>asientos libres</th><th>desde</th><th>hasta</th><th>borrado</th><th>viajeros</th><th></th><th></th>
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
                 NO
              </s:if>
              <s:else>
                 SI
              </s:else> 
           </td>
           <td>
             <s:url id="viajerosEn" action="usuariosEnViaje">
               <s:param name="idViaje" value="%{id}"></s:param>
           </s:url>
           <s:a href="%{viajerosEn}">ver viajeros</s:a>
           </td>
           <td>
           <s:if test="#v.visible == 1">
                 <s:url id="borrarViaje" action="borrarViaje">
                 <s:param name="idViaje" value="%{id}"></s:param>
                 </s:url>
                 <s:a href="%{borrarViaje}">|X|borrar</s:a>
                 </s:if>
           </td>
           
           
           <td><s:url id="modificarViaje" action="modificarViaje">
               <s:param name="idViaje" value="%{id}"></s:param>
           </s:url>
           <s:a href="%{modificarViaje}">modificar/ver</s:a></td>
          </tr>
       </s:iterator>
   </table>
</body>
</html>