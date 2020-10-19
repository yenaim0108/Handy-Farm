package com.handyfarm.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.handyfarm.dao.HandyFarmDAO;
import com.handyfarm.entity.HandyFarmDTO;

public class HandyFarmGHDeleteCommand implements HandyFarmCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String gh_id = request.getParameter("gh_id");
		String phone_number = "01062892166";
		
		HandyFarmDAO dao = new HandyFarmDAO();
		dao.gh_delete(gh_id);
		
		// DB에 접근 메서드를 GHSelect 호출 -> 결과물
		ArrayList<HandyFarmDTO> list = dao.GHSelect(phone_number); // phone_number
		// request 영역 속성값을 설정 -> 키, 값
		request.setAttribute("GHList", list);
	}
}