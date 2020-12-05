/**
    * @author 임예나
    * email : yenaim0108@gmail.com
*/
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
      
      // 스플래시
      if (com.equals("/handyfarm/splash.do")) {
         nextPage = "splash.jsp";
      }
      
      // 앱 권한 동의
      if (com.equals("/handyfarm/agree.do")) {
    	 command = new HandyFarmThreadCommand();
         command.execute(request, response);
         nextPage = "agree.jsp";
      }
      
      // 로그인 화면
      if (com.equals("/handyfarm/loginUI.do")) {
    	 command = new HandyFarmThreadCommand();
         command.execute(request, response);
         nextPage = "login.jsp";
      }
      
      // 로그인
      if (com.equals("/handyfarm/login.do")) {
         command = new HandyFarmLoginCommand();
         command.execute(request, response);
         nextPage = "main.jsp";
      }
      
      // id 중복 체크
      if (com.equals("/handyfarm/idCheck.do")) {
    	  command = new HandyFarmIDCheckCommand();
          command.execute(request, response);
          return;
      }
      
      // password가 일치하는지 체크
      if (com.equals("/handyfarm/passwordCheck.do")) {
    	  command = new HandyFarmPasswordCheckCommand();
          command.execute(request, response);
          return;
      }
      
      // 회원가입 화면
      if (com.equals("/handyfarm/signupUI.do")) {
    	  command = new HandyFarmSignupUICommand();
          command.execute(request, response);
    	  nextPage = "signup.jsp";
      }
      
      // 회원가입 > 온실 개설
      if (com.equals("/handyfarm/signGHInsert.do")) {
         command = new HandyFarmSignGHCommand();
         command.execute(request, response);
         nextPage = "gh_insert.jsp";
      }
      
      // 회원가입 > 온실 개설 > 로보 시리얼
      if (com.equals("/handyfarm/signGHRoboFoward.do")) {
    	  command = new HandyFarmSignGHRoboCommand();
          command.execute(request, response);
          nextPage = "robo_serial.jsp";
      }
      
      // 회원가입 > 온실 개설 > 로보 시리얼 > 로보 등록
      if (com.equals("/handyfarm/roboSignInsertUI.do")) {
    	 command = new HandyFarmRoboSignInsertUICommand();
         command.execute(request, response);
         nextPage = "robo_insert.jsp";
      }
      
      // 회원가입 > 온실 개설 > 로보 시리얼 > 로보 등록 > 메인
      if (com.equals("/handyfarm/signup.do")) {
    	  command = new HandyFarmSignUpCommand();
         command.execute(request, response);
         nextPage = "main.jsp";
      }
      
      // 메인 페이지
      if (com.equals("/handyfarm/main.do")) {
         command = new HandyFarmMainCommand();
         command.execute(request, response);
         nextPage = "main.jsp";
      }
      
      // 온실 개설 화면
      if (com.equals("/handyfarm/ghInsertUI.do")) {
    	  command = new HandyFarmGHInsertUICommand();
    	  command.execute(request, response);
         nextPage = "gh_insert.jsp";
      }
      
      // 온실 개설
      if (com.equals("/handyfarm/ghInsert.do")) {
         command = new HandyFarmGHInsertCommand();
         command.execute(request, response);
         nextPage = "main.jsp";
      }
      
      // 온실 수정 화면
      if (com.equals("/handyfarm/ghUpdateUI.do")) {
         command = new HandyFarmGHSelectCommand();
         command.execute(request, response);
         nextPage = "gh_update.jsp";
      }
      
      // 온실 수정하기
      if (com.equals("/handyfarm/ghUpdate.do")) {
         command = new HandyFarmGHUpdateCommand();
         command.execute(request, response);
         nextPage = "main.jsp";
      }
      
      // 온실 삭제하기
      if (com.equals("/handyfarm/ghDelete.do")) {
         command = new HandyFarmGHDeleteCommand();
         command.execute(request, response);
         nextPage = "main.jsp";
      }
      
      // 로보 목록 화면
      if (com.equals("/handyfarm/roboList.do")) {
          command = new HandyFarmRoboSelectAllCommand();
          command.execute(request, response);
          nextPage = "robo_list.jsp";
       }
      
      // 메인 > 로보 목록 > 로보 연동
      if (com.equals("/handyfarm/mainRoboInsertUI.do")) {
          command = new HandyFarmMainRoboInsertUICommand();
          command.execute(request, response);
          nextPage = "robo_serial.jsp";
       }
      
      // 로보 연동_시리얼 화면
      if (com.equals("/handyfarm/roboSerial.do")) {
    	  command = new HandyFarmSerialQrFowardCommand();
    	  command.execute(request, response);
          nextPage = "robo_serial.jsp";
      }
      
      // 로보 연동_QR 화면
      if (com.equals("/handyfarm/roboQR.do")) {
    	 command = new HandyFarmSerialQrFowardCommand();
    	 command.execute(request, response);
         nextPage = "robo_qr.jsp";
      }
      
      // 로보 등록 화면
      if (com.equals("/handyfarm/roboInsertUI.do")) {
         command = new HandyFarmRoboInsertUICommand();
         command.execute(request, response);
         nextPage = "robo_insert.jsp";
      }
            
      // 로보 등록
      if (com.equals("/handyfarm/roboInsert.do")) {
         command = new HandyFarmRoboInsertCommand();
         command.execute(request, response);
         nextPage = "robo_list.jsp";
      }
      
      // 로보 수정 화면
      if (com.equals("/handyfarm/roboUpdateUI.do")) {
         command = new HandyFarmRoboUpdateUICommand();
         command.execute(request, response);
         nextPage = "robo_update.jsp";
      }
      
      // 로보 수정
      if (com.equals("/handyfarm/roboUpdate.do")) {
         command = new HandyFarmRoboUpdateCommand();
         command.execute(request, response);
         nextPage = "robo_list.jsp";
      }
      
      // 로보 삭제
      if (com.equals("/handyfarm/roboDelete.do")) {
         command = new HandyFarmRoboDeleteCommand();
         command.execute(request, response);
         nextPage = "robo_list.jsp";
      }
      
      // 알림 센터
      if (com.equals("/handyfarm/pushLog.do")) {
         command = new HandyFarmPushSelectCommand();
         command.execute(request, response);
         nextPage = "push_log.jsp";
      }
      
//      // 메인_작물선택
//      if (com.equals("/handyfarm/mainCrop.do")) {
//         command = new HandyFarmMainCropCommand();
//         command.execute(request, response);
//         nextPage = "main_crop.jsp";
//      }
      
      // 생장 정보
      if (com.equals("/handyfarm/growth.do")) {
         command = new HandyFarmGrowthCommand();
         command.execute(request, response);
         nextPage = "growth.jsp";
      }
      
//      // 분석 정보
//      if (com.equals("/handyfarm/analysis.do")) {
//         command = new HandyFarmAnalysisCommand();
//         command.execute(request, response);
//         nextPage = "analysis.jsp";
//      }
//      
      // 캘린더 탭
      if (com.equals("/handyfarm/calendar.do")) {
         command = new HandyFarmCalendarSelectAllCommand();
         command.execute(request, response);
         nextPage = "calendar.jsp";
      }
      
      // 일정 조회
      if (com.equals("/handyfarm/calendarSelect.do")) {
          command = new HandyFarmCalendarSelectAllCommand2();
          command.execute(request, response);
          return;
      }
      
//      // 일정 확인
//      if (com.equals("/handyfarm/calendarInfo.do")) {
//         command = new HandyFarmCalendarSelectCommand();
//         command.execute(request, response);
//         nextPage = "calendar_info.jsp";
//      }
      
      // 누구의 일정?
      if (com.equals("/handyfarm/calendarWho.do")) {
         nextPage = "calendar_who.jsp";
      }
      
      // 일정 등록 화면
      if (com.equals("/handyfarm/calendarInsertUI.do")) {
         nextPage = "calendar_insert.jsp";
      }
      
//      // 개인 일정 등록
//      if (com.equals("/handyfarm/calendarInsert.do")) {
//         command = new HandyFarmCalendarInsertCommand();
//         command.execute(request, response);
//         nextPage = "calendar.jsp";
//      }
      
      // 온실일정_온실선택 화면
      if (com.equals("/handyfarm/calendarGHUI.do")) {
         command = new HandyFarmCalendarGHCommand();
         command.execute(request, response);
         return;
      }
      
      // 작물이 1개인지 체크
      if (com.equals("/handyfarm/isOneCrop.do")) {
          command = new HandyFarmIsOneCropCommand();
          command.execute(request, response);
          return;
       }
      
      // 온실일정_작물선택 화면
      if (com.equals("/handyfarm/CropUI.do")) {
         command = new HandyFarmCropCommand();
         command.execute(request, response);
         return;
      }
      
      // 수확량 등록 화면
      if (com.equals("/handyfarm/calendarYieldUI.do")) {
         nextPage = "calendar_yield.jsp";
      }
      
      // 온실 일정 등록
      if (com.equals("/handyfarm/calendarGHInsert.do")) {
         command = new HandyFarmCalendarGHInsertCommand();
         command.execute(request, response);
         nextPage = "calendar.jsp";
      }
      
//      // 일정 삭제
//      if (com.equals("/handyfarm/calendarDelete.do")) {
//         command = new HandyFarmCalendarDeleteCommand();
//         command.execute(request, response);
//         nextPage = "calendar_delete.jsp";
//      }
      
      // Tip 탭 || 실시간정보
      if (com.equals("/handyfarm/tip.do") || com.equals("/handyfarm/crawling.do")) {
         nextPage = "crawling.jsp";
      }
      
      // 실시간정보_선택
      if (com.equals("/handyfarm/crawlingSel.do")) {
         command = new HandyFarmInfoSelectCommand();
         command.execute(request, response);
         nextPage = "crawling_sel.jsp";
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
      
      // 찜 업데이트
      if (com.equals("/handyfarm/wishUpdate.do")) {
         command = new HandyFarmWishCommand();
         command.execute(request, response);
         return;
      }
      
      // 찜 목록 -> sel
      if (com.equals("/handyfarm/wishList.do")) {
         command = new HandyFarmWishSelectCommand();
         command.execute(request, response);
         return;
      }
      
      // 찜 목록 -> unsel
      if (com.equals("/handyfarm/cropAllList.do")) {
         command = new HandyFarmCropAllCommand2();
         command.execute(request, response);
         return;
      }
      
      // 설정 탭
      if (com.equals("/handyfarm/setting.do")) {
         nextPage = "setting.jsp";
      }
      
//      // 알림관리_온실선택
//      if (com.equals("/handyfarm/pushGH.do")) {
//         command = new HandyFarmPushGHCommand();
//         command.execute(request, response);
//         nextPage = "push_gh.jsp";
//      }
//      
//      // 알림관리_작물선택
//      if (com.equals("/handyfarm/pushCrop.do")) {
//         command = new HandyFarmPushCropCommand();
//         command.execute(request, response);
//         nextPage = "push_crop.jsp";
//      }
//      
//      // 알림관리
//      if (com.equals("/handyfarm/push.do")) {
//         command = new HandyFarmPushCommand();
//         command.execute(request, response);
//         nextPage = "push.jsp";
//      }
//      
//      // 알림 관리 수정
//      if (com.equals("/handyfarm/pushUpdate.do")) {
//         command = new HandyFarmPushUpdateCommand();
//         command.execute(request, response);
//         nextPage = "setting.jsp";
//      }
            
      RequestDispatcher dis = request.getRequestDispatcher(nextPage);
      dis.forward(request, response);
   }
}