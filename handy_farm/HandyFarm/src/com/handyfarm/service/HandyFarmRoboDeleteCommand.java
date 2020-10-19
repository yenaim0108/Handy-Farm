package com.handyfarm.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.handyfarm.dao.HandyFarmDAO;
import com.handyfarm.entity.HandyFarmDTO;

public class HandyFarmRoboDeleteCommand implements HandyFarmCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String phone_number = request.getParameter("phone_number");
		String robo_serial = request.getParameter("robo_serial");
		String gh_id = request.getParameter("gh_id");
		System.out.println("gh_id = " + gh_id);
		
		HandyFarmDAO dao = new HandyFarmDAO();
		//DB에 접근해서 robo삭제
		
		dao.robo_delete(robo_serial);
		ArrayList<HandyFarmDTO> list = dao.GHSelect(phone_number); // phone_number
		// request 영역 속성값을 설정 -> 키, 값
		request.setAttribute("GHList", list);
		
		String gh_nickname = dao.getGHNickname(gh_id);
		ArrayList<HandyFarmDTO> list2 = dao.RoboSelect(gh_id);
		// request 영역 속성값을 설정 -> 키, 값
		request.setAttribute("RoboList", list2);
		request.setAttribute("gh_nickname", gh_nickname);
	}
}