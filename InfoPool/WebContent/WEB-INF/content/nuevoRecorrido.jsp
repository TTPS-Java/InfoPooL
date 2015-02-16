<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css" />
<title></title>
</head>
<body>
<s:a href="Index">Volver</s:a>
<s:form action="guardarRecorridoAction" method="post">
<s:radio name="boton"  list="tiposDeViajes" cssClass="bb" value="defaultTipoDeViaje"/>
<s:checkboxlist list="dias" cssClass="misDias" name="misDias"/>
<s:textfield id="fecha" name="viaje.fecha" label="Fecha" />
<s:textfield id="horaPartida" name="viaje.horaPartida" label="horaIda" />
<s:textfield id="horaVuelta" name="viaje.horaVuelta" label="horaVuelta" />
<s:textfield id="asientosLibres" name="viaje.asientosLibres" label="asientosLibres" value="0" />
<s:textfield name="viaje.desde.descripcion" label="nombre lugar de origen" />
<s:textfield name="viaje.hasta.descripcion" label="nombre lugar de destino" />
<s:hidden id="latitudOrigen" name="viaje.desde.latitud" label="desde latitud" />
<s:hidden id="longitudOrigen" name="viaje.desde.longitud" label="desde longitud" /> 
<s:hidden id="latitudDestino" name="viaje.hasta.latitud" label="hastalatitud"/>
<s:hidden id="longitudDestino" name="viaje.hasta.longitud" label="hastalongitud" />		
<s:hidden  id="datoJSON" name="coordenadasEventos" />
<s:select  headerKey="-1" headerValue="Sin evento asociado" id="selectEvento" 
    label="Asociar a evento"      list="eventos"  listKey="id" listValue="nombre" name="idElegido"/>
<s:submit value="guardar recorrido"/>	
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
	////
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
	
	////
	$("#reset").click(function(){initialize()});
	$("#btnEvento").click(function(){
		});
	$("#selectEvento").change(function(){
		evento=true;
		pincharEvento(map);
		if(cuentaClick == 2){
			calcularRuta();
		}
		
	})
	
	$("#guardarRecorridoAction_botonviaje_periodico").click(function(){
			$(".misDias").show();
			$(".checkboxLabel").show(); 	
	})
	$("#guardarRecorridoAction_botonviaje_puntual").click(function(){
		$(".misDias").hide();
    	$(".checkboxLabel").hide();
    	$(".misDias").attr('checked', false);
    })

    $("#fecha").datepicker({
		dateFormat : "dd/mm/yy",
	});
	
});


</script>








</body>
</html>