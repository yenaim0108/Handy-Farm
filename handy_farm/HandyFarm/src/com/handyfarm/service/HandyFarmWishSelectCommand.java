package com.handyfarm.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.handyfarm.dao.HandyFarmDAO;
import com.handyfarm.entity.HandyFarmDTO;

public class HandyFarmWishSelectCommand implements HandyFarmCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		HandyFarmDAO dao = new HandyFarmDAO();
		
		ArrayList<HandyFarmDTO> list = dao.wish_list(id);
		
		// json 파싱하기
		String json = new Gson().toJson(list);
		
		 try {
			 // 값 전송
			 response.getWriter().write(json); 
         } catch (Exception e) {
             e.printStackTrace();
         }
	}
}