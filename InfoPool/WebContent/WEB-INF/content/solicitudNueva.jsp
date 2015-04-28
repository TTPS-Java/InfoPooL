<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<s:head></s:head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>
	<h4><s:text name="pedir_asientos.nueva_solicitud"/></h4>
	<s:text name="pedir_asientos.origen"/>: ${viaje.getDesde().getDescripcion()}
	<br> <s:text name="pedir_asientos.destino"/>: ${viaje.getHasta().getDescripcion()}
	<br> <s:text name="aplicacion.fecha"/>:
	<s:date name="viaje.fecha" format="dd/MM/yyyy" />
	<br> <s:property value="%{#request.viaje.asientosLibres }" /> <s:text name = "pedir_asientos.asientos_restantes"/>
	<s:form action="guardarSolicitud">
		<s:textfield type="number" key="pedir_asientos.cantidad"
			name="cantAsientos"></s:textfield>
		<s:hidden name="id" value="%{#request.viaje.id}"></s:hidden>
		<s:submit key="pedir_asientos.pedir"></s:submit>
	</s:form>
	<br>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBwnvVdu2RMDYrUBGGvoqA2NvCjoRC46lg&sensor=TRUE">
</script>
	<script type="text/javascript">
	var evento;
	myCenter = new google.maps.LatLng(-34.903808, -57.938117);
	var markerOrigen;
	var markerDestino;
	var markerEvento;
	var map;
	$(document).ready(function(){
		function initialize() {
			myCenter = new google.maps.LatLng(-34.903808, -57.938117);
			var mapProp = {
				center : myCenter,
				zoom : 16,
				mapTypeId : google.maps.MapTypeId.HYBRID
			};
			map = new google.maps.Map(document.getElementById("googleMap"),	mapProp);
			map.panTo(myCenter);
			directionsDisplay = new google.maps.DirectionsRenderer({map: map});
			pincharEvento(map)
		}
		function pincharOrigen(map) {
			var position = new google.maps.LatLng(<s:property value='#request.viaje.desde.latitud'/>,<s:property value='#request.viaje.desde.longitud'/>)
			markerOrigen = new google.maps.Marker({
			    position: position,
			    map: map,
			    title:"<s:text name='pedir_asientos.origen'/>"
			});
			markerOrigen.setPosition(position);
		}
		function pincharDestino(map) {
			var position = new google.maps.LatLng(<s:property value='#request.viaje.hasta.latitud'/>,<s:property value='#request.viaje.hasta.longitud'/>)
			markerDestino = new google.maps.Marker({
			    position: position,
			    map: map,
			    title:"<s:text name='pedir_asientos.destino'/>"
			});
			markerDestino.setPosition(position);
		}
		function pincharEvento(map) {
			<s:if test='%{#request.viaje.eventoAsociado!=""}'>
			var position = new google.maps.LatLng(<s:property value='#request.viaje.eventoAsociado.latitud'/>,<s:property value='#request.viaje.eventoAsociado.longitud'/>)
			markerEvento = new google.maps.Marker({
			    position: position,
			    map: map,
			    title:"<s:text name='pedir_asientos.evento'/>"
			});
			markerEvento.setPosition(position);</s:if>
		    pincharOrigen(map);
		    pincharDestino( map);
		    calcularRuta()
		}
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
		
		
		initialize();
	});
	
	</script>
	<div id="googleMap" style="width:500px;height:380px;"></div>
	<s:a href="tablaDeRecorridosAction"><s:text name = "aplicacion.volver"/></s:a>
</body>
</html>