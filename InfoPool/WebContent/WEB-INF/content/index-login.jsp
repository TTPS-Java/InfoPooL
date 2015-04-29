<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head><title>InfoPool</title>
<s:head/>
</head>
<body>
	<s:url id="en" action="Index">
            <s:param name="request_locale">en</s:param>
    </s:url>
    <s:a href="%{en}">English</s:a>
    <s:url id="es" action="Index">
            <s:param name="request_locale">es</s:param>
    </s:url>
    <s:a href="%{es}">Español</s:a>
    <br>
	<s:a href="login">Login</s:a>
	<br>
	<s:a href="registro"><s:text name="inicio.registro" /></s:a>
	<br>
</body>
</html>