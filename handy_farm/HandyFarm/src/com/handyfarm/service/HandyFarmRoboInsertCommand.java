/**
    * @author 정민정
    * email : as514188@gmail.com
*/

package com.handyfarm.service;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.handyfarm.dao.HandyFarmDAO;
import com.handyfarm.entity.HandyFarmDTO;

public class HandyFarmRoboInsertCommand implements HandyFarmCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String robo_serial = request.getParameter("robo_serial");
		String robo_img = request.getParameter("robo_img");
		String robo_nickname = request.getParameter("robo_nickname");
		String gh_id = request.getParameter("gh_id");
		String id = request.getParameter("id");
		String crops_id = request.getParameter("crops_id");

		if (id == null) {
			HttpSession session =  request.getSession();
			session.getAttribute("id");
		}
		
		HandyFarmDAO dao = new HandyFarmDAO();
		
		dao.roboinsert(robo_serial, robo_img, robo_nickname, gh_id, id, crops_id);
		
		// 로보 목록 가져오기
		ArrayList<HandyFarmDTO> roboList = dao.roboSelect(gh_id);

		// 온실 별명 가져오기
		String gh_nickname = dao.getGHNickname(gh_id);
				
		// request 영역 속성값을 설정 -> 키, 값
		request.setAttribute("roboList", roboList);
		request.setAttribute("gh_id", gh_id);
		request.setAttribute("gh_nickname", gh_nickname);
	}
}