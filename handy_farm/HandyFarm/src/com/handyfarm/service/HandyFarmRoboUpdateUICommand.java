package com.handyfarm.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.handyfarm.dao.HandyFarmDAO;
import com.handyfarm.entity.HandyFarmDTO;

public class HandyFarmRoboUpdateUICommand implements HandyFarmCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String robo_serial = request.getParameter("robo_serial");
		String gh_id = request.getParameter("gh_id");
		
		HandyFarmDAO dao = new HandyFarmDAO();
		
		ArrayList<HandyFarmDTO> robo_search_list = dao.robo_search_list(robo_serial);
		
		
		//DB에 접근해서 list 메서드 호출
		ArrayList<HandyFarmDTO> crops_list = dao.crops_list(robo_serial);
		
		request.setAttribute("robo_search_list", robo_search_list);
		request.setAttribute("crops_list", crops_list);
		request.setAttribute("robo_serial", robo_serial);
		request.setAttribute("gh_id", gh_id);
	}
}