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

public class HandyFarmMainCommand implements HandyFarmCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		// DB에 접근하기 위한 객체 생성
		HandyFarmDAO dao = new HandyFarmDAO();
				
		// DB에 접근 메서드를 GHSelect 호출 -> 결과물
		ArrayList<HandyFarmDTO> list = dao.GHSelect(id);
		
		// request 영역 속성값을 설정 -> 키, 값
		request.setAttribute("GHList", list);
	}
}