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
		
		HandyFarmDAO dao = new HandyFarmDAO();
		
		ArrayList<HandyFarmDTO> robo_search_list = dao.robo_search_list(robo_serial);
		
		request.setAttribute("robo_search_list", robo_search_list);
		System.out.println(robo_search_list);
		
		
		//DB에 접근해서 list 메서드 호출
		ArrayList<HandyFarmDTO> cultivar_list = dao.cultivar_list(robo_serial);
		
		request.setAttribute("cultivar_list", cultivar_list);
		System.out.println(cultivar_list);
	}
}