package com.handyfarm.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.handyfarm.dao.HandyFarmDAO;

public class HandyFarmGHInsertCommand implements HandyFarmCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// session에서 id 값 가져오기
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		// gh_img, gh_nickname, next 가져오기
		String gh_img = request.getParameter("gh_img");
		String gh_nickname = request.getParameter("gh_nickname");
		String next = request.getParameter("next");
		
		// DB 연결
		HandyFarmDAO dao = new HandyFarmDAO();
		
		// 온실 정보 db에 저장하고 생성한 gh_id 가져오기
		String gh_id = dao.gh_insert(gh_img, gh_nickname, id);
		
		// gh_id, next, GH_next 값 설정
		request.setAttribute("gh_id", gh_id);
	}
}