package com.handyfarm.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.handyfarm.service.HandyFarmCalendarDeleteCommand;
import com.handyfarm.service.HandyFarmCalendarInsertCommand;
import com.handyfarm.service.HandyFarmCalendarSelectCommand;
import com.handyfarm.service.HandyFarmCalendarUpdateCommand;
import com.handyfarm.service.HandyFarmCommand;
import com.handyfarm.service.HandyFarmLoginCommand;
import com.handyfarm.service.HandyFarmRoboDeleteCommand;
import com.handyfarm.service.HandyFarmRoboInsertCommand;
import com.handyfarm.service.HandyFarmRoboUpdateCommand;
import com.handyfarm.service.HandyFarmSignupCommand;

/**
 * Servlet implementation class HandyFarmController
 */
@WebServlet(description = "HandyFarm 서블릿", urlPatterns = { "/handyfarm" })
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
		
		// 로그인
		if (com.equals("/handyfarm/login.do")) {
			command = new HandyFarmLoginCommand(); // 객체 인스턴스화 ==> 서버 영역에 쓰기 역할
			command.execute(request, response); // DB 연결
			nextPage = "login_ui.dart"; // login.dart 페이지로 포워딩
		}
		
		// 회원가입
		if (com.equals("/handyfarm/signup.do")) {
			command = new HandyFarmSignupCommand();
			command.execute(request, response);
			nextPage = "signup_ui.dart";
		}

		// 로보 등록
		if (com.equals("/handyfarm/roboInsert.do")) {
			command = new HandyFarmRoboInsertCommand();
			command.execute(request, response);
			nextPage = "main_ui.dart";
		}
		
		// 로보 수정
		if (com.equals("/handyfarm/roboUpdate.do")) {
			command = new HandyFarmRoboUpdateCommand();
			command.execute(request, response);
			nextPage = "main_ui.dart";
		}
		
		// 로보 삭제
		if (com.equals("/handyfarm/roboDelete.do")) {
			command = new HandyFarmRoboDeleteCommand();
			command.execute(request, response);
			nextPage = "main_ui.dart";
		}
		
		// 일정 등록
		if (com.equals("/handyfarm/calendarInsert.do")) {
			command = new HandyFarmCalendarInsertCommand();
			command.execute(request, response);
			nextPage = "calendar_ui.dart";
		}
		
		// 일정 조회
		if (com.equals("/handyfarm/calendarSelect.do")) {
			command = new HandyFarmCalendarSelectCommand();
			command.execute(request, response);
			nextPage = "calendar_ui.dart";
		}
		
		// 일정 수정
		if (com.equals("/handyfarm/calendarUpdate.do")) {
			command = new HandyFarmCalendarUpdateCommand();
			command.execute(request, response);
			nextPage = "calendar_ui.dart";
		}
		
		// 일정 삭제
		if (com.equals("/handyfarm/calendarDelete.do")) {
			command = new HandyFarmCalendarDeleteCommand();
			command.execute(request, response);
			nextPage = "calendar_ui.dart";
		}
		
		RequestDispatcher dis = request.getRequestDispatcher(nextPage);
		dis.forward(request, response);
	}

}