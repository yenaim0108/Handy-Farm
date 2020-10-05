package com.handyfarm.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.handyfarm.dao.HandyFarmDAO;
import com.handyfarm.entity.HandyFarmDTO;

public class HandyFarmGrowthCommand implements HandyFarmCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String gh_id = request.getParameter("gh_id");
		String cultivar_number = request.getParameter("cultivar_number");
		
		HandyFarmDAO dao = new HandyFarmDAO();
		HandyFarmDTO data = dao.growth(gh_id, cultivar_number);
		
		request.setAttribute("growth", data);
	}
}