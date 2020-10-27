package com.handyfarm.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.handyfarm.dao.HandyFarmDAO;
import com.handyfarm.entity.HandyFarmDTO;

public class HandyFarmCropSelectCommand implements HandyFarmCommand {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String crops_id = request.getParameter("crops_id");
		
		HandyFarmDAO dao = new HandyFarmDAO();
		ArrayList<HandyFarmDTO> tip_list = dao.tip_list(crops_id);
		request.setAttribute("tip_list", tip_list);
	}
}