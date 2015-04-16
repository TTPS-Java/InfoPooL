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
  <h3><s:text name="detalleviajero.titulo" /></h3>
  <br />
  <s:url id="bloquear" action="bloquearViajero">
		<s:param name="id" value="%{viajero.id}"></s:param>
  </s:url> 
  <s:url id="desbloquear" action="desbloquearViajero">
		<s:param name="id" value="%{viajero.id}"></s:param>
  </s:url>
  <s:if  test="viajero.estaActivo==1">
    <s:a href="%{bloquear}">|<s:text name="detalleviajero.bloquearusuario" />|</s:a>
  </s:if>
  <s:else>
     <s:a href="%{desbloquear}">|<s:text name="detalleviajero.desbloquear" />|</s:a>
  </s:else>
  
  
   <s:url id="borrar" action="borrarViajesDeViajero">
		<s:param name="id" value="%{viajero.id}"></s:param>
  </s:url> 
  <s:a href="%{borrar}"><s:text name="detalleviajero.borrarviajes" /> <s:property value="viajero.nombreUsuario" /> |</s:a>
  
  
   <h3><s:text name="detalleviajero.usuario" />:<s:property value="viajero.nombreUsuario" /></h3>
   <h3><s:text name="detalleviajero.nombreyapellido" />:<s:property value="viajero.Nombre"/> ,<s:property value="viajero.apellido"/></h3>
   <h3><s:text name="detalleviajero.cantidaddeviajes" />:<s:property value="cantidadViajesViajero"/> </h3>
    <s:if  test="viajero.estaActivo==1"> 
      <h3><s:text name="detalleviajero.estado" />:<s:text name="detalleviajero.activo" /></h3>
     </s:if>
    <s:else>
     <h3><s:text name="detalleviajero.estado" />:<s:text name="detalleviajero.bloqueado" /></h3>
    </s:else>
   
   <h3><s:text name="detalleviajero.denunciasrecibidas" />: ${denunciasDeUsuario.size() }</h3>
   <table>
      <tr><th><s:text name="detalleviajero.usuario" /></th><th><s:text name="detalleviajero.contenido" /></th></tr>
      <s:iterator value="denunciasDeUsuario">
      <tr>
        <td><s:property value="autor.nombreUsuario"/></td>
        <td><s:property value="contenido"/></td>
      </tr>
      </s:iterator>
   </table>
</body>
</html>