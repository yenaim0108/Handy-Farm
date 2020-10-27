package com.handyfarm.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.handyfarm.dao.HandyFarmDAO;
import com.handyfarm.entity.HandyFarmDTO;

public class HandyFarmCalendarSelectAllCommand implements HandyFarmCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// id 가져오기
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		// 현재 날짜 가져오기
		Timestamp time = new Timestamp(System.currentTimeMillis());
		Date date = new Date(time.getTime());
		
		
		// DB 연결
		HandyFarmDAO dao = new HandyFarmDAO();
		
		// 온실 목록 가져오기
		ArrayList<HandyFarmDTO> calendar = dao.calendarAllSelect(id, date);
		
		// request 영역 속성값을 설정 -> 키, 값
		request.setAttribute("calendar", calendar);
	}
}