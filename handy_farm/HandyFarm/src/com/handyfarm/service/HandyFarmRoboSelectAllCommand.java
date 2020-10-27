package com.handyfarm.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.handyfarm.dao.HandyFarmDAO;
import com.handyfarm.entity.HandyFarmDTO;

public class HandyFarmRoboSelectAllCommand implements HandyFarmCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// gh_id
		String gh_id = request.getParameter("gh_id");
		
		// DB에 접근하기 위한 객체 생성
		HandyFarmDAO dao = new HandyFarmDAO();
		
		// 온실 별명 가져오기
		String gh_nickname = dao.getGHNickname(gh_id);
				
		// DB에 접근 메서드를 roboSelect 호출 -> 결과물
		ArrayList<HandyFarmDTO> roboList = dao.roboSelect(gh_id);
		
		// request 영역 속성값을 설정 -> 키, 값
		request.setAttribute("roboList", roboList);
		request.setAttribute("gh_id", gh_id);
		request.setAttribute("gh_nickname", gh_nickname);
	}

}
