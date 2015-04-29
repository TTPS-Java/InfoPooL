<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><s:text name="calificaciones.titulo"/></title>
</head>
<body>
    <s:a href="Index"><s:text name="aplicacion.volver"></s:text></s:a>
    <h3><s:text name="calificaciones.titulo"/></h3>
    <s:form action="guardarCalificacionAction" method="post">
        <s:hidden name="idCalificado" value="%{idCalificado}" />
        <s:hidden name="idViaje" value="%{idViaje}" />
        <s:textarea name="comentario"></s:textarea>
        <s:radio  name="opcionSeleccionada" list="opciones"  value="DefaultRadio"/>
        <s:submit value="calificar" />
    </s:form>
</body>
</html>