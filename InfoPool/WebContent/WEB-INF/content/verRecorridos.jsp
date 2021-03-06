<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css" />
<title><s:text name="verrecorridos.ver_recorridos"></s:text></title>
<!-- Struts2 JQuery -->
<sj:head locale="%{#session.locale}" jqueryui="true" jquerytheme="redmond"/>
<script>
function validarForm(){
	  /*var pasoValidacion=true;
	  var RegExPattern = /^\d{1,2}\/\d{1,2}\/\d{2,4}$/;  
	  var fechaMaxima=document.getElementById("fechaMaxima").value;
	  if(!(fechaMaxima=="")){
		  if(!(fechaMaxima.match(RegExPattern))){
			  pasoValidacion=false;}  
	  }
	  var fechaMinima=document.getElementById("fechaMinima").value;
	  if(!(fechaMinima=="")){
		  if(!(fechaMinima.match(RegExPattern))){
			  pasoValidacion=false;
		  }  
	  }
	 if(pasoValidacion==false){alert("ingrese bien la fecha en formato dd/mm/aaaa");
		 return false;}else{return true;}*/
		 return true;
}

function formatLink(cellvalue, options, rowObject) {
	return ("<div style=\"text-align:center\">"
			+ "<a class='button' title='${verrecorridos.ver_detalles}' href='solicitudNueva?id="
			+ cellvalue
			+ "'><s:text name = 'verrecorridos.pedir_asientos' /> </a>"+ "</div>");
}
function formatLinkViajero(cellvalue, options, rowObject) {
	return ("<div style=\"text-align:center\">"
			+ "<a class='button' title='${verrecorridos.ver_usuario}' href='calificacionesViajero?id="
			+ cellvalue
			+ "'><s:text name = 'verrecorridos.ver_usuario'/></a>"+ "</div>");
}

</script>
</head>
<body>
<s:a href="Index"><s:text name="aplicacion.volver"/></s:a>
<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
    <h4><s:text name="verrecorridos.seleccion" /></h4>
    <s:form action="cambioDatosTablaDeRecorridosAction" method="post" onsubmit="return validarForm()">
    <s:radio name="viajeSeleccionado"  list="tiposDeViajes" value="defaultTipoDeViaje" key="verrecorridos.tipodeviaje"/>
    <s:select  headerKey="-1" headerValue="%{getText('verrecorridos.sin_evento')}" id="selectEvento" 
    key="verrecorridos.coneventoasociado" list="eventos"  listKey="id" listValue="nombre" name="idEvento" />
       <s:textfield name="fechaMinima" id="fechaMinima" key="verrecorridos.fechainicio" />
       <s:textfield name="fechaMaxima" id="fechaMaxima" key="verrecorridos.fechamaxima" /> 
       <s:textfield name="horaMinima"  key="verrecorridos.horainicio"/>
       <s:textfield name="horaMaxima" key="verrecorridos.horamaxima" />          
    <s:submit key="verrecorridos.aplicarfiltro" />
  </s:form>
            <s:url id="remoteurl" action="datosAction"/>
			<sjg:grid	
				id="gridtable"
				caption="%{getText('verrecorridos.viajes')}"
				dataType="json"
				href="%{remoteurl}"
				pager="true"
				gridModel="gridModel"
				rowList="10,15,20"
				rowNum="10"
				rownumbers="true"
				autowidth="true">
				<sjg:gridColumn name="hasta.descripcion" index="hasta.descripcion" title="%{getText('verrecorridos.nombre_inicio')}"  sortable="true"/>
				<sjg:gridColumn name="desde.descripcion" index="desde.descripcion" title="%{getText('verrecorridos.nombre_inicio')}" sortable="true"/>
				<sjg:gridColumn name="asientosLibres" index="asientosLibres" title="%{getText('verrecorridos.asientos')}" sortable="true"/>
				<sjg:gridColumn name="fecha" index="fecha" title="%{getText('verrecorridos.fecha')}" sortable="true"/>
				<sjg:gridColumn name="horaPartida" index="horaPartida" title="%{getText('verrecorridos.horaPartida')}" sortable="true"/>
				<sjg:gridColumn name="horaVuelta" index="horaVuelta" title="%{getText('verrecorridos.horaVuelta')}" sortable="true"/>
				<sjg:gridColumn name="id" index="acciones" title="%{getText('verrecorridos.acciones')}" sortable="false" formatter="formatLink"/>
				<sjg:gridColumn name="idConductor" index="detalle" title="%{getText('verrecorridos.detalle')}" sortable="false" formatter="formatLinkViajero"/>
			</sjg:grid>
<script>
$(document).ready(function(){
	  $("#fechaMinima").datepicker({
			dateFormat : "<s:text name = "formato_fecha"/>",
		});
	  $("#fechaMaxima").datepicker({
			dateFormat : "<s:text name = "formato_fecha"/>",
		}); 
});
</script>
</body>
</html>