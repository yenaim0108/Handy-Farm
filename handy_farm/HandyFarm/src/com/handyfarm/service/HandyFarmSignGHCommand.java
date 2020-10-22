package com.handyfarm.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HandyFarmSignGHCommand implements HandyFarmCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// id, password, next 가져오기
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String next = request.getParameter("next");
		
		// request 영역 속성값을 설정 -> 키, 값
		request.setAttribute("id", id);
		request.setAttribute("password", password);
		request.setAttribute("next", next);
		request.setAttribute("GH_next", "signGHRoboFoward.do");
	}
}