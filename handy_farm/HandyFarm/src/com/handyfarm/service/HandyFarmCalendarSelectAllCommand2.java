/**
    * @author 임예나
    * email : yenaim0108@gmail.com
*/

package com.handyfarm.service;

import java.sql.Date;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.handyfarm.dao.HandyFarmDAO;
import com.handyfarm.entity.HandyFarmDTO;

public class HandyFarmCalendarSelectAllCommand2 implements HandyFarmCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// id 가져오기
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		// date 가져오기
		String date = request.getParameter("date");
		
		// String을 sql.date로 변환
		Date date2 = Date.valueOf(date);
		
		// DB 연결
		HandyFarmDAO dao = new HandyFarmDAO();
		
		// 온실 목록 가져오기
		ArrayList<HandyFarmDTO> calendar = dao.calendarAllSelect(id, date2);

		// json 파싱하기
		String json = new Gson().toJson(calendar);
		
		 try {
			 // 값 전송
			 response.getWriter().write(json); 
         } catch (Exception e) {
             e.printStackTrace();
         }
	}
}