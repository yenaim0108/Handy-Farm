package com.handyfarm.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.handyfarm.dao.HandyFarmDAO;
import com.handyfarm.entity.HandyFarmDTO;

public class HandyFarmPasswordCheckCommand implements HandyFarmCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// id, password 가져오기
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		// DB 연결
		HandyFarmDAO dao = new HandyFarmDAO();
		
		// 회원 테이블에 id가 존재하는지 확인
		ArrayList<HandyFarmDTO> list = dao.passwordCheck(id, password);

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