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
    <s:a href="%{en}"><s:text name="idioma.ingles" /></s:a>
    <s:url id="es" action="Index">
            <s:param name="request_locale">es_ES</s:param>
    </s:url>
    <s:a href="%{es}"><s:text name="idioma.espaniol" /></s:a>
<h2><s:text name="inicio.saludo" />, <s:property value="#request.usuario"/>!</h2>
	<br>
	<s:a href="#">ABM sistema</s:a> <!-- <s:text name="menuadmin.abm_sistema"/> -->
	<br>
	<s:a href="verEventos">ABM eventos</s:a> <!-- <s:text name="menuadmin.abm_eventos"/> -->
	<br>
	<s:a href="verViajeros">Administracion viajeros</s:a> <!-- <s:text name="menuadmin.admin_viajeros"/> -->
	<br>
	<s:a href="logout">Salir</s:a> <!-- <s:text name="menuadmin.salir"/> -->
</body>
</html>