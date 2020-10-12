package com.handyfarm.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.handyfarm.dao.HandyFarmDAO;
import com.handyfarm.entity.HandyFarmDTO;

public class HandyFarmGrowthCommand implements HandyFarmCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// 온실 ID, 품종 번호
		String gh_id = request.getParameter("gh_id");
		String cultivar_number = request.getParameter("cultivar_number");
		
		// DB에 접근하기 위한 객체 생성
		HandyFarmDAO dao = new HandyFarmDAO();
		
		// 온실 이름 가져오기
		String gh_name = dao.getGHName(gh_id);
		
		// DB에 접근 growth 메서드를 호출 -> 결과물
		ArrayList<HandyFarmDTO> list = dao.growth(gh_id, cultivar_number);
		// request 영역 속성값을 설정 -> 키, 값
		request.setAttribute("growth", list);
		request.setAttribute("gh_name", gh_name);
	}
}