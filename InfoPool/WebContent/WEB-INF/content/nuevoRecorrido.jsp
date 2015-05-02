<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<s:head/>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css" />
<title></title>
</head>
<body>
<s:a href="Index"><s:text name="aplicacion.volver"/></s:a>
<s:form action="guardarRecorridoAction" method="post">
<s:radio name="boton"  list="tiposDeViajes" cssClass="bb" value="defaultTipoDeViaje"/>
<s:checkboxlist list="dias" cssClass="misDias" name="misDias" value="defaultDia"/>
<s:textfield id="fecha" name="viaje.fecha"  value="%{viaje.fecha}" key="nuevorecorrido.fecha" />
<s:textfield id="horaPartida" name="viaje.horaPartida"  value="%{viaje.horaPartida}" key="nuevorecorrido.horaida" />
<s:textfield id="horaVuelta" name="viaje.horaVuelta" value="%{viaje.horaVuelta}" key="nuevorrecorrido.horavuelta" />
<s:textfield id="asientosLibres" name="viaje.asientosLibres" value="%{viaje.asientosLibres}" key="nuevorecorrido.asientoslibres"  />
<s:textfield name="viaje.desde.descripcion" value="%{viaje.desde.descripcion}" key="nuevorecorrido.nombrelugarorigen" />
<s:textfield name="viaje.hasta.descripcion" value="%{viaje.hasta.descripcion}" key="nuevorecorrido.nombrelugardestino" />

	
<s:if test="%{viaje.id==null}"> 
</s:if>
<s:else>
<s:hidden name="viaje.id" value="%{viaje.id}" />
<s:hidden name="viaje.desde.id" value="%{viaje.desde.id}" />
<s:hidden name="viaje.hasta.id" value="%{viaje.hasta.id}" />
</s:else>
<s:hidden id="latitudOrigen" name="viaje.desde.latitud" value="%{viaje.desde.latitud}"  />
<s:hidden id="longitudOrigen" name="viaje.desde.longitud" value="%{viaje.desde.longitud}" /> 
<s:hidden id="latitudDestino" name="viaje.hasta.latitud" value="%{viaje.hasta.latitud}"/>
<s:hidden id="longitudDestino" name="viaje.hasta.longitud" value="%{viaje.hasta.longitud}" />		
<s:hidden  id="datoJSON" name="coordenadasEventos"  />
<s:select  headerKey="-1" headerValue="%{getText('verrecorridos.sin_evento')}" id="selectEvento" 
   key="nuevorecorrido.eventoasociado" value="%{idElegido}"   list="eventos"  listKey="id" listValue="nombre" name="idElegido"/>
<s:submit key="nuevorecorrido.guardarrecorrido" name="nada"/>	
</s:form>
<br />
<input id="reset" type="button" value="Reset" />
<div id="googleMap" style="width:500px;height:380px;"></div>



<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBwnvVdu2RMDYrUBGGvoqA2NvCjoRC46lg&sensor=TRUE">
</script>
<script>
function borrar(){
	var events=document.getElementById("datoJSON").value;
	
	var idEventoElegido= document.getElementById("selectEvento").value;
	var latitudEvento;var longitudEvento;
	
	var objetosJSON = JSON.parse(events);
	alert("paso");
	for (i in objetosJSON.eventos){
		if(objetosJSON.eventos[i].id== idEventoElegido){
			
			longitudEvento=objetosJSON.eventos[i].longitud;
			latitudEvento=objetosJSON.eventos[i].latitud;
			alert(latitudEvento)
			break;
		}
	}
	
}
var cuentaClick;
var evento;
myCenter = new google.maps.LatLng(-34.903808, -57.938117);
var markerOrigen;
var markerDestino;
var markerEvento;
var map;
$(document).ready(function(){
	function initialize() {
		cuentaClick = 0;
		evento = false;
		myCenter = new google.maps.LatLng(-34.903808, -57.938117);
		var mapProp = {
			center : myCenter,
			zoom : 16,
			mapTypeId : google.maps.MapTypeId.HYBRID
		};
		map = new google.maps.Map(document.getElementById("googleMap"),	mapProp);
		google.maps.event.addListener(map, 'click', function(e) {
			pinchar(e.latLng, map);
		});
		map.panTo(myCenter);
		directionsDisplay = new google.maps.DirectionsRenderer({map: map});
	}
	function pincharOrigen(position, map) {
		markerOrigen = new google.maps.Marker({
		    position: position,
		    map: map,
		    title:"Origen"
		});
		$("#latitudOrigen").val(position.lat());
		$("#longitudOrigen").val(position.lng());
		markerOrigen.setPosition(position);
	}
	function pincharDestino(position, map) {
		markerDestino = new google.maps.Marker({
		    position: position,
		    map: map,
		    title:"Destino"
		});
		$("#latitudDestino").val(position.lat());
		$("#longitudDestino").val(position.lng());
		markerDestino.setPosition(position);
		calcularRuta();
	}
	function pincharEvento(map) {
		var events=document.getElementById("datoJSON").value;
		var idEventoElegido= document.getElementById("selectEvento").value;
	  if(idEventoElegido!=-1){
		  var latitudEvento;var longitudEvento;
		  var objetosJSON = JSON.parse(events);
		  for (i in objetosJSON.eventos){
			  if(objetosJSON.eventos[i].id== idEventoElegido){
			    	longitudEvento=objetosJSON.eventos[i].longitud;
				    latitudEvento=objetosJSON.eventos[i].latitud;
				    break;
			  }
		  }
		var position = new google.maps.LatLng(latitudEvento,longitudEvento)
		markerEvento = new google.maps.Marker({
		    position: position,
		    map: map,
		    title:"Evento"
		});
		markerEvento.setPosition(position);
	  }else{
		  markerEvento.setMap(null);
		  initialize()
	  }
	
	  if(cuentaClick == 2){
			calcularRuta();
		}
	}
	function pinchar(pos, map) {
		switch(cuentaClick) {
	    case 0:
	    	pincharOrigen(pos, map);
	        break;
	    case 1:
	    	pincharDestino(pos, map);
	        break;
		} 
		cuentaClick++
	}
	initialize();
	var directionsService = new google.maps.DirectionsService();
	var directionsDisplay;
	function calcularRuta(waypts) {
		puntos = [];
		if(evento) {
			puntos.push({
				location: markerEvento.getPosition(),
				stopover:true})
		}	
		var start = markerOrigen.getPosition();
		var end = markerDestino.getPosition();
		var request = {
			origin: start,
			destination: end,
			waypoints: puntos,
			optimizeWaypoints: true,
			travelMode: google.maps.TravelMode.DRIVING
		};
		
		directionsService.route(request, function(response, status) {
			if (status == google.maps.DirectionsStatus.OK) {
			  directionsDisplay.setDirections(response);
			  directionsDisplay.setOptions( { suppressMarkers: true } );
			}
		});
	}
	$("#reset").click(function(){
		$("#latitudOrigen").val("0.0"); 
		$("#longitudOrigen").val("0.0" );
		$("#latitudDestino").val("0.0");
		$("#longitudDestino").val("0.0");
		$("#selectEvento").val("-1");
		evento=false;
		initialize()});
	$("#btnEvento").click(function(){
		});
	$("#selectEvento").change(function(){
		evento=true;
		var idEventoElegido= document.getElementById("selectEvento").value;
		pincharEvento(map);
	});
	
	if($('#guardarRecorridoAction_botonviaje_puntual').is(':checked')){
		$(".misDias").hide();
    	$(".checkboxLabel").hide();
    	$(".misDias").attr('checked', false);
	}
	if($('#guardarRecorridoAction_botonOne_time_trip').is(':checked')){
		$(".misDias").hide();
    	$(".checkboxLabel").hide();
    	$(".misDias").attr('checked', false);
	}
	
	$("#guardarRecorridoAction_botonviaje_periodico").click(function(){
			$(".misDias").show();
			$(".checkboxLabel").show(); 	
	});
	$("#guardarRecorridoAction_botonviaje_puntual").click(function(){
		$(".misDias").hide();
    	$(".checkboxLabel").hide();
    	$(".misDias").attr('checked', false);
    });
    $("#guardarRecorridoAction_botonPeriodic_trip").click(function(){
			$(".misDias").show();
			$(".checkboxLabel").show(); 	
	});
	$("#guardarRecorridoAction_botonOne_time_trip").click(function(){
		$(".misDias").hide();
    	$(".checkboxLabel").hide();
    	$(".misDias").attr('checked', false);
    });
    $("#fecha").datepicker({
		dateFormat : "<s:text name = "formato_fecha"/>",
	});
  function dibujar(){	
	 if(!($("#latitudOrigen").val()==0) && !($("#longitudOrigen").val()==0) ){
		 var position = new google.maps.LatLng($("#latitudOrigen").val(),$("#longitudOrigen").val());
		 pinchar(position, map);
	 }
     if(!($("#latitudDestino").val()==0)  && !($("#longitudDestino").val()==0)){
    	var position = new google.maps.LatLng($("#latitudDestino").val(),$("#longitudDestino").val());
    	pinchar(position, map);
	 }   
     if($("#selectEvento").val()!="-1"){  	
    	evento=true;
    	pincharEvento(map);
     }
   }
  
dibujar();
	
	
});


</script>








</body>
</html>