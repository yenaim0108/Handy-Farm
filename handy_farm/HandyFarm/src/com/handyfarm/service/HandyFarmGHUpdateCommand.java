package com.handyfarm.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.handyfarm.dao.HandyFarmDAO;
import com.handyfarm.entity.HandyFarmDTO;

public class HandyFarmGHUpdateCommand implements HandyFarmCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		String gh_id = request.getParameter("gh_id");
		String gh_nickname = request.getParameter("gh_nickname");
		String gh_img = request.getParameter("gh_img");
		
		HandyFarmDAO dao = new HandyFarmDAO();
		
		dao.gh_update(gh_id, gh_nickname, gh_img);
		
		// DB에 접근 메서드를 GHSelect 호출 -> 결과물
		ArrayList<HandyFarmDTO> list = dao.GHSelect(id);
		
		// request 영역 속성값을 설정 -> 키, 값
		request.setAttribute("GHList", list);
	}

}