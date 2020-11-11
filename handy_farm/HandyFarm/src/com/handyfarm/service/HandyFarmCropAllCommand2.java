package com.handyfarm.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.handyfarm.dao.HandyFarmDAO;
import com.handyfarm.entity.HandyFarmDTO;

public class HandyFarmCropAllCommand2 implements HandyFarmCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// id 가져오기
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		// DB에 접근하기 위한 객체 생성
		HandyFarmDAO dao = new HandyFarmDAO();
				
		// 전체 농작물 가져오기
		ArrayList<HandyFarmDTO> list = dao.cropAll_Select(id);
		
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