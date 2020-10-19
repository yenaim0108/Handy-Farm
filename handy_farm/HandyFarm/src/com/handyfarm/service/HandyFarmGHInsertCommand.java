package com.handyfarm.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.handyfarm.dao.HandyFarmDAO;

public class HandyFarmGHInsertCommand implements HandyFarmCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		//온실 개설 -> 로보 시리얼 커맨드
		System.out.println("GHINSERT들와따");
		String gh_id2 = request.getParameter("gh_id");
		String gh_img = request.getParameter("gh_img");
		String gh_nickname = request.getParameter("gh_nickname");
		String phone_number = "01062892166";
		
		System.out.println("여긴GHINSET커맨드: gh_nickname은?"+gh_nickname);
		HandyFarmDAO dao = new HandyFarmDAO();
		
		if(gh_id2 == null) {
			dao.gh_insert(gh_img, gh_nickname, phone_number);
			String gh_id = dao.getGHId(gh_nickname);
			System.out.println(gh_id);
			request.setAttribute("gh_id", gh_id);
		}else if(gh_id2 != null) {
			request.setAttribute("gh_id", gh_id2);
		}
	}
}