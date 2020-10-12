package com.handyfarm.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.handyfarm.dao.HandyFarmDAO;

public class HandyFarmGHInsertCommand implements HandyFarmCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String gh_img = request.getParameter("gh_img");
		String gh_nickname = request.getParameter("gh_nickname");
		String phone_number = "01062892166";
		
		HandyFarmDAO dao = new HandyFarmDAO();
		dao.gh_insert(gh_img, gh_nickname, phone_number);
		
		String gh_id = dao.getGHId(gh_nickname);
		request.setAttribute("gh_id", gh_id);
	}
}