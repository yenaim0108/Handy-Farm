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
@WebServlet(description = "HandyFarm �꽌釉붾┸", urlPatterns = { "*.do" })
public class HandyFarmController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); // �븳湲� 吏��썝
		response.setCharacterEncoding("UTF-8"); // �븳湲� 吏��썝
		
		String requestURI = request.getRequestURI(); // �봽濡쒖젥�듃\�뙆�씪紐�...
		String contextPath = request.getContextPath(); // �봽濡쒖젥�듃
		String com = requestURI.substring(contextPath.length()); // �봽濡쒖젥�듃�� �뙆�씪紐� 異쒕젰
		
		HandyFarmCommand command = null;
		String nextPage = null;
		
		// �뒪�뵆�옒�떆
		if (com.equals("/handyfarm/splash.do")) {
			nextPage = "splash.jsp";
		}
		
		// �빋 沅뚰븳 �룞�쓽
		if (com.equals("/handyfarm/agree.do")) {
			nextPage = "agree.jsp";
		}
		
		// 濡쒓렇�씤 �솕硫�
		if (com.equals("/handyfarm/loginUI.do")) {
			nextPage = "login.jsp";
		}
		
		// 濡쒓렇�씤
		if (com.equals("/handyfarm/login.do")) {
			command = new HandyFarmLoginCommand();
			command.execute(request, response);
			nextPage = "login.jsp";
		}
		
		// �쉶�썝媛��엯 �솕硫�
		if (com.equals("/handyfarm/signupUI.do")) {
			nextPage = "signup.jsp";
		}
		
		// �쉶�썝媛��엯
		if (com.equals("/handyfarm/signup.do")) {
			command = new HandyFarmSignUpCommand();
			command.execute(request, response);
			nextPage = "signup.jsp";
		}
		
		// �삩�떎 媛쒖꽕 �솕硫�
		if (com.equals("/handyfarm/ghInsertUI.do")) {
			nextPage = "gh_insert.jsp";
		}
		
		// �삩�떎 媛쒖꽕_�쉶�썝媛��엯
		if (com.equals("/handyfarm/ghInsertSignup.do")) {
			command = new HandyFarmGHInsertCommand();
			command.execute(request, response);
			nextPage = "robo_serial.jsp";
		}
		
		// 濡쒕낫 �뿰�룞_�떆由ъ뼹 �솕硫�
		if (com.equals("/handyfarm/roboSerial.do")) {
			nextPage = "robo_serial.jsp";
		}
		
		// 濡쒕낫 �뿰�룞_QR �솕硫�
		if (com.equals("/handyfarm/roboQR.do")) {
			nextPage = "robo_qr.jsp";
		}
		
		// 濡쒕낫 �벑濡� �솕硫�
		if (com.equals("/handyfarm/roboInsertUI.do")) {
			nextPage = "robo_sign_insert.jsp";
		}
		
		// 濡쒕낫 �벑濡�_�쉶�썝媛��엯
		if (com.equals("/handyfarm/roboSignInsert.do")) {
			command = new HandyFarmRoboInsertCommand();
			command.execute(request, response);
			nextPage = "mian.jsp";
		}
		
		// 硫붿씤 �럹�씠吏�
		if (com.equals("/handyfarm/main.do")) {
			command = new HandyFarmGHSelectAllCommand();
			command.execute(request, response);
			nextPage = "main.jsp";
		}
		
		// �삩�떎 �닔�젙 �솕硫�
		if (com.equals("/handyfarm/ghUpdateUI.do")) {
			command = new HandyFarmGHSelectCommand();
			command.execute(request, response);
			nextPage = "gh_update.jsp";
		}
		
		// �삩�떎 �닔�젙�븯湲�
		if (com.equals("/handyfarm/ghUpdate.do")) {
			command = new HandyFarmGHUpdateCommand();
			command.execute(request, response);
			nextPage = "main.jsp";
		}
		
		// �삩�떎 �궘�젣�븯湲�
		if (com.equals("/handyfarm/ghDelete.do")) {
			command = new HandyFarmGHDeleteCommand();
			command.execute(request, response);
			nextPage = "main.jsp";
		}
		
		// 濡쒕낫 �벑濡� �솕硫�
		if (com.equals("/handyfarm/roboInsertUI.do")) {
			command = new HandyFarmRoboInsertUICommand();
			command.execute(request, response);
			nextPage = "robo_insert.jsp";
		}
				
		// 濡쒕낫 �벑濡�
		if (com.equals("/handyfarm/roboInsert.do")) {
			command = new HandyFarmRoboInsertCommand();
			command.execute(request, response);
			nextPage = "main.jsp";
		}
		
		// 濡쒕낫 �닔�젙 �솕硫�
		if (com.equals("/handyfarm/roboUpdateUI.do")) {
			command = new HandyFarmRoboUpdateUICommand();
			command.execute(request, response);
			nextPage = "robo_update.jsp";
		}
		
		// 濡쒕낫 �닔�젙
		if (com.equals("/handyfarm/roboUpdate.do")) {
			command = new HandyFarmRoboUpdateCommand();
			command.execute(request, response);
			nextPage = "main.jsp";
		}
		
		// 로보 삭제
		if (com.equals("/handyfarm/roboDelete.do")) {
			command = new HandyFarmRoboDeleteCommand();
			command.execute(request, response);
			nextPage = "main.jsp";
		}
		
		// �븣由� �꽱�꽣
		if (com.equals("/handyfarm/pushLog.do")) {
			command = new HandyFarmPushSelectCommand();
			command.execute(request, response);
			nextPage = "push_log.jsp";
		}
		
		// 硫붿씤_�옉臾쇱꽑�깮
		if (com.equals("/handyfarm/mainCrop.do")) {
			command = new HandyFarmMainCropCommand();
			command.execute(request, response);
			nextPage = "main_crop.jsp";
		}
		
		// �깮�옣 �젙蹂�
		if (com.equals("/handyfarm/growth.do")) {
			command = new HandyFarmGrowthCommand();
			command.execute(request, response);
			nextPage = "growth.jsp";
		}
		
		// 遺꾩꽍 �젙蹂�
		if (com.equals("/handyfarm/analysis.do")) {
			command = new HandyFarmAnalysisCommand();
			command.execute(request, response);
			nextPage = "analysis.jsp";
		}
		
		// 罹섎┛�뜑
		if (com.equals("/handyfarm/calendar.do")) {
			command = new HandyFarmCalendarSelectAllCommand();
			command.execute(request, response);
			nextPage = "calendar.jsp";
		}
		
		// �씪�젙 �솗�씤
		if (com.equals("/handyfarm/calendarInfo.do")) {
			command = new HandyFarmCalendarSelectCommand();
			command.execute(request, response);
			nextPage = "calendar_info.jsp";
		}
		
		// �늻援ъ쓽 �씪�젙?
		if (com.equals("/handyfarm/calendarWho.do")) {
			nextPage = "calendar_who.jsp";
		}
		
		// �씪�젙 �벑濡� �솕硫�
		if (com.equals("/handyfarm/calendarInsertUI.do")) {
			nextPage = "calendar_insert.jsp";
		}
		
		// �씪�젙 �벑濡�
		if (com.equals("/handyfarm/calendarInsert.do")) {
			command = new HandyFarmCalendarInsertCommand();
			command.execute(request, response);
			nextPage = "calendar.jsp";
		}
		
		// 濡쒕낫�씪�젙_�삩�떎�꽑�깮
		if (com.equals("/handyfarm/calendarGH.do")) {
			command = new HandyFarmCalendarGHCommand();
			command.execute(request, response);
			nextPage = "calendar_gh.jsp";
		}
		
		// 濡쒕낫�씪�젙_�옉臾쇱꽑�깮
		if (com.equals("/handyfarm/calendarCrop.do")) {
			command = new HandyFarmCalendarCropCommand();
			command.execute(request, response);
			nextPage = "calendar_crop.jsp";
		}
		
		// �뼱�뼡 �씪�젙? �솕硫�
		if (com.equals("/handyfarm/calendarWhatUI.do")) {
			nextPage = "calendar_what.jsp";
		}
		
		// �닔�솗�웾 �벑濡� �솕硫�
		if (com.equals("/handyfarm/calendarYieldUI.do")) {
			nextPage = "calendar_yield.jsp";
		}
		
		// �씪�젙 �궘�젣
		if (com.equals("/handyfarm/calendarDelete.do")) {
			command = new HandyFarmCalendarDeleteCommand();
			command.execute(request, response);
			nextPage = "calendar_delete.jsp";
		}
		
		// Tip �꺆 || �떎�떆媛꾩젙蹂�
		if (com.equals("/handyfarm/tip.do") || com.equals("/handyfarm/realInfo.do")) {
			command = new HandyFarmInfoAllCommand();
			command.execute(request, response);
			nextPage = "real_info.jsp";
		}
		
		// �떎�떆媛꾩젙蹂�_�꽑�깮
		if (com.equals("/handyfarm/realInfoSel.do")) {
			command = new HandyFarmInfoSelectCommand();
			command.execute(request, response);
			nextPage = "real_info_sel.jsp";
		}
		
		// �옉臾쇰퀎 寃쎌옉議곌굔
		if (com.equals("/handyfarm/cropAll.do")) {
			command = new HandyFarmCropAllCommand();
			command.execute(request, response);
			nextPage = "crop_all_tip.jsp";
		}
		
		// �옉臾쇰퀎_�긽�꽭
		if (com.equals("/handyfarm/cropTipSel.do")) {
			command = new HandyFarmCropSelectCommand();
			command.execute(request, response);
			nextPage = "crop_tip_sel.jsp";
		}
		
		// �꽕�젙 �꺆
		if (com.equals("/handyfarm/setting.do")) {
			nextPage = "setting.jsp";
		}
		
		// �븣由쇨�由�_�삩�떎�꽑�깮
		if (com.equals("/handyfarm/pushGH.do")) {
			command = new HandyFarmPushGHCommand();
			command.execute(request, response);
			nextPage = "push_gh.jsp";
		}
		
		// �븣由쇨�由�_�옉臾쇱꽑�깮
		if (com.equals("/handyfarm/pushCrop.do")) {
			command = new HandyFarmPushCropCommand();
			command.execute(request, response);
			nextPage = "push_crop.jsp";
		}
		
		// �븣由쇨�由�
		if (com.equals("/handyfarm/push.do")) {
			command = new HandyFarmPushCommand();
			command.execute(request, response);
			nextPage = "push.jsp";
		}
		
		// �븣由� 愿�由� �닔�젙
		if (com.equals("/handyfarm/pushUpdate.do")) {
			command = new HandyFarmPushUpdateCommand();
			command.execute(request, response);
			nextPage = "setting.jsp";
		}
				
		RequestDispatcher dis = request.getRequestDispatcher(nextPage);
		dis.forward(request, response);
	}
}