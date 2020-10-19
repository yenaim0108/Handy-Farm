package com.handyfarm.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.handyfarm.dao.HandyFarmDAO;
import com.handyfarm.entity.HandyFarmDTO;

public class HandyFarmInfoSelectCommand implements HandyFarmCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		//카테고리
		String category = request.getParameter("category");
		
		// DB에 접근하기 위한 객체 생성
		HandyFarmDAO dao = new HandyFarmDAO();
				
		// DB에 접근 메서드를 GHSelect 호출 -> 결과물
//		ArrayList<HandyFarmDTO> list = dao.realInfo_pest_Select(phone_number, category); // phone_number
		
		ArrayList<HandyFarmDTO> list = dao.realInfo_pest_Select("병충해");
		// request 영역 속성값을 설정 -> 키, 값
		request.setAttribute("pestList", list);
	}
}