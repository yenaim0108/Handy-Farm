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
		HandyFarmCommand command2 = null;
		String nextPage = null;
		
		// 스플래시
		if (com.equals("/handyfarm/splash.do")) {
			nextPage = "splash.jsp";
		}
		
		// 앱 권한 동의
		if (com.equals("/handyfarm/agree.do")) {
			nextPage = "agree.jsp";
		}
		
		// 로그인
		if (com.equals("/handyfarm/login.do")) {
			command = new HandyFarmLoginCommand();
			command.execute(request, response);
			nextPage = "login.jsp";
		}
		
		// 회원가입
		if (com.equals("/handyfarm/signup.do")) {
			command = new HandyFarmSignUpCommand();
			command.execute(request, response);
			nextPage = "siignup.jsp";
		}
		
		// 온실 개설
		if (com.equals("/handyfarm/ghInsert.do")) {
			command = new HandyFarmGHInsertCommand();
			command.execute(request, response);
			nextPage = "gh_insert.jsp";
		}
		
		// 온실 수정
		if (com.equals("/handyfarm/ghUpdate.do")) {
			command = new HandyFarmGHUpdateCommand();
			command.execute(request, response);
			nextPage = "gh_update.jsp";
		}
		
		// 온실 삭제
		if (com.equals("/handyfarm/ghDelete.do")) {
			command = new HandyFarmGHDeleteCommand();
			command.execute(request, response);
			nextPage = "gh_delete.jsp";
		}
		
		// 로보 연동_시리얼
		if (com.equals("/handyfarm/roboSerial.do")) {
			command = new HandyFarmRoboConnectionCommand();
			command.execute(request, response);
			nextPage = "robo_serial.jsp";
		}
		
		// 로보 연동_QR
		if (com.equals("/handyfarm/roboQR.do")) {
			command = new HandyFarmRoboConnectionCommand();
			command.execute(request, response);
			nextPage = "robo_qr.jsp";
		}
		
		// 로보 등록_회원가입
		if (com.equals("/handyfarm/roboSignInsert.do")) {
			command = new HandyFarmRoboInsertCommand();
			command.execute(request, response);
			nextPage = "robo_sign_insert.jsp";
		}
		
		// 로보 등록
		if (com.equals("/handyfarm/roboInsert.do")) {
			command = new HandyFarmRoboInsertCommand();
			command.execute(request, response);
			nextPage = "robo_insert.jsp";
		}
		
		// 로보 수정
		if (com.equals("/handyfarm/roboUpdate.do")) {
			command = new HandyFarmRoboUpdateCommand();
			command.execute(request, response);
			nextPage = "robo_update.jsp";
		}
		
		// 메인 페이지
		if (com.equals("/handyfarm/main.do")) {
			command = new HandyFarmWeatherCommand();
			command.execute(request, response);
			command2 = new HandyFarmGHSelectCommand();
			command2.execute(request, response);
			nextPage = "main.jsp";
		}
		
		// 알림 센터
		if (com.equals("/handyfarm/pushLog.do")) {
			command = new HandyFarmPushSelectCommand();
			command.execute(request, response);
			nextPage = "push_log.jsp";
		}
		
		// 메인_작물선택
		if (com.equals("/handyfarm/mainCrop.do")) {
			command = new HandyFarmMainCropCommand();
			command.execute(request, response);
			nextPage = "main_crop.jsp";
		}
		
		// 생장 정보
		if (com.equals("/handyfarm/growth.do")) {
			command = new HandyFarmGrowthCommand();
			command.execute(request, response);
			nextPage = "growth.jsp";
		}
		
		// 분석 정보
		if (com.equals("/handyfarm/analysis.do")) {
			command = new HandyFarmAnalysisCommand();
			command.execute(request, response);
			nextPage = "analysis.jsp";
		}
		
		// 캘린더
		if (com.equals("/handyfarm/calendar.do")) {
			command = new HandyFarmCalendarSelectAllCommand();
			command.execute(request, response);
			nextPage = "calendar.jsp";
		}
		
		// 일정 확인
		if (com.equals("/handyfarm/calendarInfo.do")) {
			command = new HandyFarmCalendarSelectCommand();
			command.execute(request, response);
			nextPage = "calendar_info.jsp";
		}
		
		// 누구의 일정?
		if (com.equals("/handyfarm/calendarWho.do")) {
			nextPage = "calendar_who.jsp";
		}
		
		// 일정 등록
		if (com.equals("/handyfarm/calendarInsert.do")) {
			command = new HandyFarmCalendarInsertCommand();
			command.execute(request, response);
			nextPage = "calendar_insert.jsp";
		}
		
		// 로보일정_온실선택
		if (com.equals("/handyfarm/calendarGH.do")) {
			command = new HandyFarmCalendarGHCommand();
			command.execute(request, response);
			nextPage = "calendar_gh.jsp";
		}
		
		// 로보일정_작물선택
		if (com.equals("/handyfarm/calendarCrop.do")) {
			command = new HandyFarmCalendarCropCommand();
			command.execute(request, response);
			nextPage = "calendar_crop.jsp";
		}
		
		// 어떤 일정?
		if (com.equals("/handyfarm/calendarWhat.do")) {
			command = new HandyFarmCalendarInsertCommand();
			command.execute(request, response);
			nextPage = "calendar_what.jsp";
		}
		
		// 수확량 등록
		if (com.equals("/handyfarm/calendarYield.do")) {
			command = new HandyFarmCalendarInsertCommand();
			command.execute(request, response);
			nextPage = "calendar_yield.jsp";
		}
		
		// 일정 삭제
		if (com.equals("/handyfarm/calendarDelete.do")) {
			command = new HandyFarmCalendarDeleteCommand();
			command.execute(request, response);
			nextPage = "calendar_delete.jsp";
		}
		
		// 실시간정보
		if (com.equals("/handyfarm/realInfo.do")) {
			command = new HandyFarmInfoAllCommand();
			command.execute(request, response);
			nextPage = "real_info.jsp";
		}
		
		// 실시간정보_선택
		if (com.equals("/handyfarm/realInfoSel.do")) {
			command = new HandyFarmInfoSelectCommand();
			command.execute(request, response);
			nextPage = "real_info_sel.jsp";
		}
		
		// 작물별 경작조건
		if (com.equals("/handyfarm/cropAll.do")) {
			command = new HandyFarmCropAllCommand();
			command.execute(request, response);
			nextPage = "crop_all_tip.jsp";
		}
		
		// 작물별_상세
		if (com.equals("/handyfarm/cropTipSel.do")) {
			command = new HandyFarmCropSelectCommand();
			command.execute(request, response);
			nextPage = "crop_tip_sel.jsp";
		}
		
		// 설정 탭
		if (com.equals("/handyfarm/setting.do")) {
			nextPage = "setting.jsp";
		}
		
		// 알림관리_온실선택
		if (com.equals("/handyfarm/pushGH.do")) {
			command = new HandyFarmPushGHCommand();
			command.execute(request, response);
			nextPage = "push_gh.jsp";
		}
		
		// 알림관리_작물선택
		if (com.equals("/handyfarm/pushCrop.do")) {
			command = new HandyFarmPushCropCommand();
			command.execute(request, response);
			nextPage = "push_crop.jsp";
		}
		
		// 알림관리
		if (com.equals("/handyfarm/push.do")) {
			command = new HandyFarmPushCommand();
			command.execute(request, response);
			nextPage = "push.jsp";
		}
				
		RequestDispatcher dis = request.getRequestDispatcher(nextPage);
		dis.forward(request, response);
	}

}