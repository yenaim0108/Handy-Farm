/**
    * @author 임예나
    * email : yenaim0108@gmail.com
*/

package com.handyfarm.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.handyfarm.dao.HandyFarmDAO;

public class HandyFarmCalendarGHInsertCommand implements HandyFarmCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// id 가져오기
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		// selDate, cal_title, cal_yield_kg, unit, gh_id, crops_id 가져오기
		String selDate = request.getParameter("selDate");
		int i = Integer.parseInt(request.getParameter("i"));
		float cal_yield_kg = Float.parseFloat(request.getParameter("yield"));
		String unit = request.getParameter("unit");
		String gh_id = request.getParameter("gh_id");
		String crops_id = request.getParameter("crops_id");
		
		// cal_title 생성하기
		String cal_title = null;
		switch (i) {
			case 0 :
				cal_title = "밭 갈기";
				break;
			case  1:
				cal_title = "농작물 심기";
				break;
			case  2:
				cal_title = "물 주기";
				break;
			case  3:
				cal_title = "비료 주기";
				break;
			case  4:
				cal_title = "수확량";
		}
		
		// 현재 시간 가져오기
		Timestamp time = new Timestamp(System.currentTimeMillis());
		
		// time 변수에서 년월일, 시분초 나눠서 가져오기
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HHmmss");
		String d = sdf.format(time);
		String[] dateTime = d.split(" ");
		
		// cal_number 생성하기
		String cal_number = "g-" + id + "-" + dateTime[0] + "-" + dateTime[1];
		
		// String sql.date로 변환
		Date date = Date.valueOf(selDate);
				
		// 수확량 전부 kg으로 변환
		if (cal_yield_kg != -1) {
			switch (unit) {
			case "g" :
				break;
			case "t" :
			}
		}
		
		// DB 연결
		HandyFarmDAO dao = new HandyFarmDAO();
		
		// 온실 일정 등록하기
		dao.ghCalendarInsert(cal_number, cal_title, date, cal_yield_kg, gh_id, id, crops_id);
	}
}