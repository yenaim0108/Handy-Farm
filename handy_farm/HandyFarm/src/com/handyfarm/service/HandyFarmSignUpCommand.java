package com.handyfarm.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.handyfarm.dao.HandyFarmDAO;
import com.handyfarm.entity.HandyFarmDTO;

public class HandyFarmSignUpCommand implements HandyFarmCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// id, password, robo_serial, robo_img, robo_nickname, gh_id, crops_id, next 가져오기
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String gh_img = request.getParameter("gh_img");
		String gh_nickname = request.getParameter("gh_nickname");
		String robo_serial = request.getParameter("robo_serial");
		String robo_img = request.getParameter("robo_img");
		String robo_nickname = request.getParameter("robo_nickname");
		String crops_id = request.getParameter("crops_id");
		
		// DB 연결
		HandyFarmDAO dao = new HandyFarmDAO();
		
		// 회원 테이블에 id, password 저장하기
		boolean b = dao.signup(id, password);
		
		// 온실 개설하기
		String gh_id = dao.gh_insert(gh_img, gh_nickname, id);
		
		// 로보 등록하기
		dao.roboinsert(robo_serial, robo_img, robo_nickname, gh_id, id, crops_id);
		
		// crops 테이블에 있는 모든 농작물에 대한 wish 정보를 0으로 wish 테이블에 넣기
		b = dao.signupWishDataInsert(id);
		
		// 온실 목록 가져오기
		ArrayList<HandyFarmDTO> GHList = dao.GHSelect(id);
		
		if (b) {
			// session에 id 저장하기
			HttpSession session = request.getSession();
			session.setAttribute("id", id);
		}
		
		// request 영역 속성값을 설정 -> 키, 값
		request.setAttribute("GHList", GHList);		
	}
}