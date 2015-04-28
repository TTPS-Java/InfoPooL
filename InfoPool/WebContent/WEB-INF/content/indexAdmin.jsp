<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<title>InfoPool</title>
</head>
<body>
      <s:url id="en" action="Index">
            <s:param name="request_locale">en_CA</s:param>
    </s:url>
    <s:a href="%{en}">English</s:a>
    <s:url id="es" action="Index">
            <s:param name="request_locale">es_ES</s:param>
    </s:url>
    <s:a href="%{es}">Español</s:a>
<h2><s:text name="inicio.saludo" />, <s:property value="#request.usuario"/>!</h2>
	<br>
	<s:a href="#"><s:text name="menuadmin.abm_sistema"/></s:a> 
	<br>
	<s:a href="verEventos"><s:text name="menuadmin.abm_eventos"/></s:a> 
	<br>
	<s:a href="verViajeros"><s:text name="menuadmin.admin_viajeros"/></s:a> 
	<br>
	<s:a href="logout"><s:text name="menuadmin.salir"/></s:a>  
</body>
</html>