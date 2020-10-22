package com.handyfarm.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HandyFarmLoginCommand implements HandyFarmCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// id, password, autoLogin 가져오기
		String id = request.getParameter("id");
		String autoLogin = request.getParameter("autoLogin"); // 사용자가 체크하면 on, 안했으면 null
		
		// 세션에 id, autoLogin 정보 저장하기
		HttpSession session = request.getSession();
		session.setAttribute("id", id);
		session.setAttribute("autoLogin", autoLogin);
	}
}