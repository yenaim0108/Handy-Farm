/**
    * @author 임예나
    * email : yenaim0108@gmail.com
*/

package com.handyfarm.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.handyfarm.dao.HandyFarmDAO;
import com.handyfarm.entity.HandyFarmDTO;

public class HandyFarmLoginCommand implements HandyFarmCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// id, password, autoLogin 가져오기
		String id = request.getParameter("id");
		String autoLogin = request.getParameter("autoLogin"); // 사용자가 체크하면 on, 안했으면 null
		
		// 세션에 id, autoLogin 정보 저장하기
		HttpSession session = request.getSession();
		session.setAttribute("id", id);
		session.setAttribute("autoLogin", autoLogin);
		
		// DB에 접근하기 위한 객체 생성
		HandyFarmDAO dao = new HandyFarmDAO();
				
		// DB에 접근 메서드를 GHSelect 호출 -> 결과물
		ArrayList<HandyFarmDTO> list = dao.GHSelect(id);
		
		// request 영역 속성값을 설정 -> 키, 값
		request.setAttribute("GHList", list);
		request.setAttribute("next", "roboInsertUI.do");
	}
}