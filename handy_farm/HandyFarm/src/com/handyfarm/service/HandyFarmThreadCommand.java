/**
    * @author 임예나
    * email : yenaim0108@gmail.com
*/

package com.handyfarm.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.handyfarm.crawling.HandyFarmWebCrawling;
import com.handyfarm.mqtt.HandyFarmMQTT;

public class HandyFarmThreadCommand implements HandyFarmCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		//mqtt 통신 시작
		HandyFarmMQTT mqtt = new HandyFarmMQTT();
		mqtt.subMqtt.start();
		mqtt.pubMqtt.start();
		
		// 크롤링 시작
		HandyFarmWebCrawling wc = new HandyFarmWebCrawling();
		wc.webCrawling.start();
	}
}