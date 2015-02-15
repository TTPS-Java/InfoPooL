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
<title>ver recorridos</title>
<!-- Struts2 JQuery -->
<sj:head locale="es" jqueryui="true" jquerytheme="redmond"/>
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
</script>
</head>
<body>

<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
    <h4>Seleccion  de viajes por filtro</h4>
    <s:form action="tablaDeRecorridosAction" method="post" onsubmit="return validarForm()">
    <s:radio name="viajeSeleccionado"  list="tiposDeViajes" value="defaultTipoDeViaje" label="Tipo de viaje"/>
    <s:select  headerKey="-1" headerValue="Sin evento asociado" id="selectEvento" 
    label="con evento asociado" list="eventos"  listKey="id" listValue="nombre" name="idEvento" />
       <s:textfield name="fechaMinima" id="fechaMinima" label="Fecha de inicio dd/mm/aaaa " />
       <s:textfield name="fechaMaxima" id="fechaMaxima" label="Fecha maxima dd/mm/aaaa " /> 
       <s:textfield name="horaMinima"  label="hora de inicio (hh:mm)"/>
       <s:textfield name="horaMaxima" label="hora maxima (hh:mm)" />          
    <s:submit value="mandar cambio" />
  </s:form>
            <s:url id="remoteurl" action="datosAction"/>
			<sjg:grid	
				id="gridtable"
				caption="Usuarios"
				dataType="json"
				href="%{remoteurl}"
				pager="true"
				gridModel="gridModel"
				rowList="10,15,20"
				rowNum="10"
				rownumbers="true"
				autowidth="true">
				<sjg:gridColumn name="hasta.descripcion" index="hasta.descripcion" title="Nombre lugar de inicio" sortable="true"/>
				<sjg:gridColumn name="desde.descripcion" index="desde.descripcion" title="Nombre lugar de llegada" sortable="true"/>
				<sjg:gridColumn name="asientosLibres" index="asientosLibres" title="asientosLibres" sortable="true"/>
				<sjg:gridColumn name="fecha" index="fecha" title="fecha" sortable="true"/>
				<sjg:gridColumn name="horaPartida" index="horaPartida" title="horaPartida" sortable="true"/>
				<sjg:gridColumn name="horaVuelta" index="horaVuelta" title="horaVuelta" sortable="true"/>
				<sjg:gridColumn name="id" index="id" title="id" sortable="true"/>
			</sjg:grid>
<script>
$(document).ready(function(){
	  $("#fechaMinima").datepicker({
			dateFormat : "dd/mm/yy",
		});
	  $("#fechaMaxima").datepicker({
			dateFormat : "dd/mm/yy",
		}); 
});
</script>
</body>
</html>