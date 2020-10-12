package com.handyfarm.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.handyfarm.dao.HandyFarmDAO;

public class HandyFarmRoboDeleteCommand implements HandyFarmCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String robo_serial = request.getParameter("robo_serial");
		
		HandyFarmDAO dao = new HandyFarmDAO();
		//DB에 접근해서 robo삭제
		
		dao.robo_delete(robo_serial);
	}
}