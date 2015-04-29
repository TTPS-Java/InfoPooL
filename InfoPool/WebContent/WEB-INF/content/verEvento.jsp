<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<s:head/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title><s:text name="evento.ver_evento"/></title>
</head>
<body>
	<h4><s:text name="evento.ver_evento"/></h4>
	<table>
	<tr><td><s:text name="evento.nombre"/>:</td><td>${evento.nombre}</td></tr>
	<tr><td><s:text name="evento.descr"/>:</td><td>${evento.descripcion}</td></tr>
	<tr><td><s:text name="evento.fecha"/>:</td><td><s:date name="evento.fecha" format="dd/MM/yyyy"/></td></tr>
	<tr><td><s:text name="evento.duracion"/></td><td>${evento.duracionDias} dias</td></tr>
	<tr><td><s:text name="evento.hora"/>:</td><td>${evento.hora}</td></tr>
	<tr><td><s:text name="evento._lugar"/></td><td>${evento.lugar.descripcion}</td></tr>
	</table>
	<s:a href="verEventos"><s:text name="aplicacion.volver"/></s:a>
	<div id="googleMap" style="width:500px;height:380px;"></div>
	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
	<script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
	<script
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBwnvVdu2RMDYrUBGGvoqA2NvCjoRC46lg&sensor=TRUE"></script>
	<script>
	myCenter = new google.maps.LatLng(-34.903808, -57.938117);
	marker = new google.maps.Marker({
		position : myCenter,
	});
	$(document).ready(function(){
		function initialize() {
			myCenter = new google.maps.LatLng(-34.903808, -57.938117);
			marker = new google.maps.Marker({
				position : myCenter,
			});
			var mapProp = {
				center : myCenter,
				zoom : 16,
				mapTypeId : google.maps.MapTypeId.HYBRID
			};

			var map = new google.maps.Map(document.getElementById("googleMap"),
					mapProp);

			marker.setMap(map);
			google.maps.event.addListener(map, 'click', function(e) {
				placeMarker(e.latLng, map);
			});
			if($("#latitud").val()=="0.0") {
				placeMarker(myCenter, map);
			} else {
				var myCenter = new google.maps.LatLng(${evento.lugar.latitud}, ${evento.lugar.longitud});
				placeMarker(myCenter, map);
			}
		}
		function placeMarker(position, map) {
			$("#latitud").val(position.lat());
			$("#longitud").val(position.lng());
			marker.setPosition(position);
			map.panTo(position);
		}
		initialize();
	});
	</script>

</body>
</html>
