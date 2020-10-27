package com.handyfarm.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.handyfarm.dao.HandyFarmDAO;
import com.handyfarm.entity.HandyFarmDTO;

public class HandyFarmGHDeleteCommand implements HandyFarmCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		String gh_id = request.getParameter("gh_id");
		
		HandyFarmDAO dao = new HandyFarmDAO();
		dao.gh_delete(gh_id);
		
		// DB에 접근 메서드를 GHSelect 호출 -> 결과물
		ArrayList<HandyFarmDTO> list = dao.GHSelect(id);
				
		// gh_id, next, GH_next 값 설정
		request.setAttribute("GHList", list);	}
}