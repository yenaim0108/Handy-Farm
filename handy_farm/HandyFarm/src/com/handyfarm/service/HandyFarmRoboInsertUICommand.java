package com.handyfarm.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.handyfarm.dao.HandyFarmDAO;
import com.handyfarm.entity.HandyFarmDTO;

public class HandyFarmRoboInsertUICommand implements HandyFarmCommand {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// id 가져오기
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
		// gh_id, robo_serial, next 값 가져오기
		String gh_id = request.getParameter("gh_id");
		String robo_serial = request.getParameter("robo_serial");

		// DB 연결
		HandyFarmDAO dao = new HandyFarmDAO();
		
		//DB에 접근해서 list 메서드 호출
		ArrayList<HandyFarmDTO> crops_list = dao.crops_list_insert();

		// 값 설정
		request.setAttribute("crops_list", crops_list);
		request.setAttribute("gh_id", gh_id);
		request.setAttribute("robo_serial", robo_serial);
		request.setAttribute("next", "roboInsert.do");
		request.setAttribute("id", id);
	}
}