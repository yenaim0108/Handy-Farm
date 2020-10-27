package com.handyfarm.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HandyFarmSerialQrFowardCommand implements HandyFarmCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// id, password, gh_nickname, next 가져오기
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String gh_nickname = request.getParameter("gh_nickname");
		String next = request.getParameter("next");
		
		// request 영역 속성값을 설정 -> 키, 값
		request.setAttribute("id", id);
		request.setAttribute("password", password);
		request.setAttribute("gh_nickname", gh_nickname);
		request.setAttribute("next", next);
	}
}