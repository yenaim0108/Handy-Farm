/**
    * @author 임예나
    * email : yenaim0108@gmail.com
*/

package com.handyfarm.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HandyFarmSignGHRoboCommand implements HandyFarmCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// id, password, gh_img, gh_nickname, next 가져오기
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String gh_img = request.getParameter("gh_img");
		String gh_nickname = request.getParameter("gh_nickname");
		
		// request 영역 속성값을 설정 -> 키, 값
		request.setAttribute("id", id);
		request.setAttribute("password", password);
		request.setAttribute("gh_img", gh_img);
		request.setAttribute("gh_nickname", gh_nickname);
		request.setAttribute("next", "roboSignInsertUI.do");
	}
}