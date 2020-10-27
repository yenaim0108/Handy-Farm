package com.handyfarm.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.handyfarm.dao.HandyFarmDAO;
import com.handyfarm.entity.HandyFarmDTO;

public class HandyFarmWishCommand implements HandyFarmCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String crops_id = request.getParameter("crops_id");
		String wish = request.getParameter("wish");
		
		HandyFarmDAO dao = new HandyFarmDAO();
		
		ArrayList<HandyFarmDTO> list = dao.wish_update(crops_id, wish);
		
		
		//json 파싱
		String json = new Gson().toJson(list);
		
		try {
			response.getWriter().write(json);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}