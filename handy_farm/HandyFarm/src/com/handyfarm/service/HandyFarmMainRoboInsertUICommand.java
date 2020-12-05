/**
    * @author 임예나
    * email : yenaim0108@gmail.com
*/

package com.handyfarm.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HandyFarmMainRoboInsertUICommand implements HandyFarmCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// gh_id 가져오기
		String gh_id = request.getParameter("gh_id");
		
		// request 영역 속성값을 설정 -> 키, 값
		request.setAttribute("gh_id", gh_id);
		request.setAttribute("next", "roboInsertUI.do");
	}
}