package com.handyfarm.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.handyfarm.dao.HandyFarmDAO;
import com.handyfarm.entity.HandyFarmDTO;

public class HandyFarmGHInsertCommand implements HandyFarmCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// session에서 id 값 가져오기
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		// gh_img, gh_nickname, next 가져오기
		String gh_img = request.getParameter("gh_img");
		String gh_nickname = request.getParameter("gh_nickname");
		
		// DB 연결
		HandyFarmDAO dao = new HandyFarmDAO();
		
		// 온실 정보 db에 저장하고 생성한 gh_id 가져오기
		dao.gh_insert(gh_img, gh_nickname, id);
		
		// DB에 접근 메서드를 GHSelect 호출 -> 결과물
		ArrayList<HandyFarmDTO> list = dao.GHSelect(id);
				
		// gh_id, next, GH_next 값 설정
		request.setAttribute("GHList", list);
	}
}