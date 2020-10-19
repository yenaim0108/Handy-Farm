package com.handyfarm.service;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.handyfarm.dao.HandyFarmDAO;
import com.handyfarm.entity.HandyFarmDTO;

public class HandyFarmRoboInsertCommand implements HandyFarmCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String robo_serial = request.getParameter("robo_serial");
		String robo_img = request.getParameter("robo_img");
		String robo_nickname = request.getParameter("robo_nickname");
		String cultivar_number = request.getParameter("cultivar_number");
		String gh_id = request.getParameter("gh_id");
		String phone_number = request.getParameter("phone_number");
		
		System.out.println("phonenum: "+ phone_number);
		System.out.println("robo_serial: "+ robo_serial);
		System.out.println("robo_nickname: "+ robo_nickname);
		System.out.println("cultivar_number: "+ cultivar_number);
		System.out.println("gh_id: "+ gh_id);
		
		HandyFarmDAO dao = new HandyFarmDAO();
		dao.roboinsert(robo_serial, robo_img, robo_nickname, cultivar_number, gh_id, phone_number);
		ArrayList<HandyFarmDTO> list = dao.GHSelect(phone_number); // phone_number
		// request 영역 속성값을 설정 -> 키, 값
		request.setAttribute("GHList", list);
	}
}