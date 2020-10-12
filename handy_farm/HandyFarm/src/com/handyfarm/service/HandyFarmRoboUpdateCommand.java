package com.handyfarm.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.handyfarm.dao.HandyFarmDAO;

public class HandyFarmRoboUpdateCommand implements HandyFarmCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String robo_serial = request.getParameter("robo_serial");
		String robo_img = request.getParameter("robo_img");
		String robo_nickname = request.getParameter("robo_nickname");
		String cultivar_number = request.getParameter("cultivar_number");
		System.out.println(robo_nickname);
		HandyFarmDAO dao = new HandyFarmDAO();
		System.out.println(robo_nickname);
		dao.robo_update(robo_serial, robo_nickname, robo_img, cultivar_number);
	}
}