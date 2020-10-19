<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.handyfarm.mqtt.HandyFarmMQTT" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
		<meta http-equiv="refresh" content="2; URL=login.do">
		<title>스플래시</title>
		<link rel="stylesheet" href="../css/common_ui.css">
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<%
		//mqtt 통신 시작
		HandyFarmMQTT mqtt = new HandyFarmMQTT();
		mqtt.harvestableMqtt.start();
		mqtt.sensorMqtt.start();
		mqtt.equipmentControlMqtt.start();
		mqtt.equipmentStatusMqtt.start();
%>	
	</head>
	<body class="d-t">
		<div class="d-tc va-m">
			<div class="m-b-lg p-b-sl">
				<img src="../icon/handyfarm_logo.png" alt="handyfarm_logo">
			</div>
			<div>
				<img src="../icon/handyfarm.png" style="width: 200px; height: auto;" alt="handyfarm">
			</div>
		</div>
	</body>
</html>