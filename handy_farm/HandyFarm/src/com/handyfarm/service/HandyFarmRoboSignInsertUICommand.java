/**
    * @author 임예나
    * email : yenaim0108@gmail.com
*/

package com.handyfarm.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.handyfarm.dao.HandyFarmDAO;
import com.handyfarm.entity.HandyFarmDTO;

public class HandyFarmRoboSignInsertUICommand implements HandyFarmCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// id, password, gh_img, gh_nickname, robo_serial, next 가져오기
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String gh_img = request.getParameter("gh_img");
		String gh_nickname = request.getParameter("gh_nickname");
		String robo_serial = request.getParameter("robo_serial");
		
		// DB 연결
		HandyFarmDAO dao = new HandyFarmDAO();
		
		//DB에 접근해서 list 메서드 호출
		ArrayList<HandyFarmDTO> crops_list = dao.crops_list_insert();
		
		// request 영역 속성값을 설정 -> 키, 값
		request.setAttribute("id", id);
		request.setAttribute("password", password);
		request.setAttribute("gh_img", gh_img);
		request.setAttribute("gh_nickname", gh_nickname);
		request.setAttribute("robo_serial", robo_serial);
		request.setAttribute("crops_list", crops_list);
		request.setAttribute("next", "signup.do");
	}
}