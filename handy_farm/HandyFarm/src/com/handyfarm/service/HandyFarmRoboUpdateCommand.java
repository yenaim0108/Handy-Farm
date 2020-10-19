package com.handyfarm.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.handyfarm.dao.HandyFarmDAO;
import com.handyfarm.entity.HandyFarmDTO;

public class HandyFarmRoboUpdateCommand implements HandyFarmCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String robo_serial = request.getParameter("robo_serial");
		String robo_img = request.getParameter("robo_img");
		String robo_nickname = request.getParameter("robo_nickname");
		String crops_id = request.getParameter("crops_id");
		String phone_number = "01062892166";
		System.out.println(robo_nickname);
		HandyFarmDAO dao = new HandyFarmDAO();
		System.out.println(robo_nickname);
		dao.robo_update(robo_serial, robo_nickname, robo_img, crops_id);
		

		ArrayList<HandyFarmDTO> list = dao.GHSelect(phone_number); // phone_number
		// request 영역 속성값을 설정 -> 키, 값
		request.setAttribute("GHList", list);
	}
}