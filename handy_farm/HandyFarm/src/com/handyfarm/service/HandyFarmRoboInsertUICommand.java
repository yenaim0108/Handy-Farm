package com.handyfarm.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.handyfarm.dao.HandyFarmDAO;
import com.handyfarm.entity.HandyFarmDTO;

public class HandyFarmRoboInsertUICommand implements HandyFarmCommand {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		/* request.setAttribute("gh_id", "gh-12345678-001"); */
		//String gh_nickname = request.getParameter("gh_nickname");
		String gh_id = request.getParameter("gh_id");
		String robo_serial = request.getParameter("robo_serial");
		System.out.println("엥로보시리얼"+robo_serial);
		
		HandyFarmDAO dao = new HandyFarmDAO();
		//DB에 접근해서 list 메서드 호출
		//String gh_id = dao.getGHId(gh_nickname);
		ArrayList<HandyFarmDTO> cultivar_list_insert = dao.cultivar_list_insert();
		
		request.setAttribute("cultivar_list_insert", cultivar_list_insert);
		request.setAttribute("gh_id", gh_id);
		request.setAttribute("robo_serial", robo_serial);
	}
}