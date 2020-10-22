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
		String gh_id = request.getParameter("gh_id");
		String id = request.getParameter("id");
		String crops_id = request.getParameter("crops_id");
		
		HandyFarmDAO dao = new HandyFarmDAO();
		
		dao.roboinsert(robo_serial, robo_img, robo_nickname, gh_id, id, crops_id);
		ArrayList<HandyFarmDTO> roboList = dao.roboSelect(gh_id);
		// request 영역 속성값을 설정 -> 키, 값
		request.setAttribute("roboList", roboList);
	}
}