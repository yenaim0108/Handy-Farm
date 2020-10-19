package com.handyfarm.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.handyfarm.dao.HandyFarmDAO;
import com.handyfarm.entity.HandyFarmDTO;

public class HandyFarmGHSelectCommand implements HandyFarmCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String gh_id = request.getParameter("gh_id");;
		
		HandyFarmDAO dao = new HandyFarmDAO();
		//DB에 접근해서 list 메서드 호출
		ArrayList<HandyFarmDTO> robo_list = dao.robo_list(gh_id);
		ArrayList<HandyFarmDTO> gh_in_list = dao.gh_in_list(gh_id); // phone_numbervv
		
		request.setAttribute("robo_list", robo_list);
		request.setAttribute("gh_in_list", gh_in_list);
	}
}