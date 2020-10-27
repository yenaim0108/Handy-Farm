package com.handyfarm.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.handyfarm.dao.HandyFarmDAO;
import com.handyfarm.entity.HandyFarmDTO;

public class HandyFarmPushSelectCommand implements HandyFarmCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		HandyFarmDAO dao = new HandyFarmDAO();
		//DB에 접근해서 list 메서드 호출
		ArrayList<HandyFarmDTO> push_list = dao.push_list(id);
		
		request.setAttribute("push_list", push_list);
	}
}