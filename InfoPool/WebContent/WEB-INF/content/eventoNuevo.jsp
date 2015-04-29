<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>  
<!DOCTYPE html>
<html>
<head>
<s:head/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css" />
<title><s:text name="evento.publicar_evento"/></title>
</head>
<body>
	<h4><s:text name="evento.publicar_evento"/></h4>
	<s:form action="guardarEvento" method="post">
		<s:textfield name="nombre" key="evento.nombre" value="%{evento.nombre}" />
		<s:textfield name="descripcion" key="evento.descr" value="%{evento.descripcion}" />
		<s:textfield value="%{evento.fecha}" id="fecha" name="evento.fecha" key="aplicacion.fecha"/>
		<s:textfield name="duracionDias" key="evento.duracion"
			value="%{evento.duracionDias}" />
		<s:textfield name="hora" key="evento.hora" value="%{evento.hora}" />
		<s:textfield name="lugar.descripcion" key="evento._lugar"
			value="%{evento.lugar.descripcion}" />
		<s:hidden id="latitud" name="lugar.latitud" 
			value="%{evento.lugar.latitud}" />
		<s:hidden id="longitud" name="lugar.longitud" 
			value="%{evento.lugar.longitud}" />
		<s:hidden name="evento.id" value="%{evento.id}" />
		<s:hidden name="evento.lugar.id" value="%{evento.lugar.id}" />
		<s:submit key="evento.publicar"></s:submit>
	</s:form>
	<h4><s:text name="evento.seleccione_lugar"/>:</h4>
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
		$("#fecha").datepicker({
			dateFormat : "<s:text name = "formato_fecha"/>",
		});
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
