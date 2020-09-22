package com.handyfarm.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.handyfarm.service.*;

/**
 * Servlet implementation class HandyFarmController
 */
@WebServlet(description = "HandyFarm 서블릿", urlPatterns = { "*.do" })
public class HandyFarmController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // 한글 지원
		response.setCharacterEncoding("UTF-8"); // 한글 지원
		
		String requestURI = request.getRequestURI(); // 프로젝트\파일명...
		String contextPath = request.getContextPath(); // 프로젝트
		String com = requestURI.substring(contextPath.length()); // 프로젝트와 파일명 출력
		
		HandyFarmCommand command = null;
		String nextPage = null;
		
		// 앱 권한 동의
		if (com.equals("/handyfarm/agree.do")) {
			nextPage = "agree.jsp";
		}
		
		// 메인 페이지
		if (com.equals("/handyfarm/main.do")) {
			command = new HandyFarmGHSelectCommand();
			command.execute(request, response);
			nextPage = "main.jsp";
		}
		
		// 캘린더 페이지
		if (com.equals("/handyfarm/calendar.do")) {
			command = new HandyFarmCalendarSelectCommand();
			command.execute(request, response);
			nextPage = "calendar.jsp";
		}
		
		// 일정 조회
		if (com.equals("/handyfarm/calendarSelect.do")) {
			command = new HandyFarmCalendarSelectCommand();
			command.execute(request, response);
			nextPage = "calendar.jsp";
		}
		
		// Tip 페이지
		if (com.equals("/handyfarm/tip.do")) {
			command = new HandyFarmInfoAllCommand();
			command.execute(request, response);
			nextPage = "real_info.jsp";
		}
		
		// 설정 페이지
		if (com.equals("/handyfarm/setting.do")) {
			nextPage = "setting.jsp";
		}
		
		RequestDispatcher dis = request.getRequestDispatcher(nextPage);
		dis.forward(request, response);
	}

}