/**
    * @author 임예나
    * email : yenaim0108@gmail.com
*/

package com.handyfarm.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HandyFarmGHInsertUICommand implements HandyFarmCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// request 영역 속성값을 설정 -> 키, 값
		request.setAttribute("next", "ghInsert.do");
	}
}