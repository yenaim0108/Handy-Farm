package com.handyfarm.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.handyfarm.dao.HandyFarmDAO;
import com.handyfarm.entity.HandyFarmDTO;

public class HandyFarmCropAllCommand implements HandyFarmCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		String searchword = null;
		searchword = request.getParameter("searchword");
		
		// DB에 접근하기 위한 객체 생성
		HandyFarmDAO dao = new HandyFarmDAO();
				
		if(searchword == null) {
			ArrayList<HandyFarmDTO> list = dao.cropAll_Select(id);
			request.setAttribute("cropAllList", list);
		}else {
			ArrayList<HandyFarmDTO> list = dao.tip_search(id, searchword);
			request.setAttribute("cropAllList", list);
		}
	}
}