package com.handyfarm.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.handyfarm.mqtt.HandyFarmMQTT;

public class HandyFarmMqttCommand implements HandyFarmCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		//mqtt 통신 시작
		HandyFarmMQTT mqtt = new HandyFarmMQTT();
		mqtt.harvestableMqtt.start();
		mqtt.sensorMqtt.start();
		mqtt.equipmentControlMqtt.start();
		mqtt.equipmentStatusMqtt.start();
	}
}