package com.handyfarm.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.handyfarm.dao.HandyFarmDAO;
import com.handyfarm.entity.HandyFarmDTO;

public class HandyFarmCropAllCommand implements HandyFarmCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		String phone_number = request.getParameter("phone_number");
		// DB에 접근하기 위한 객체 생성
		HandyFarmDAO dao = new HandyFarmDAO();
	
//		ArrayList<HandyFarmDTO> list = dao.cropAll_Select(phone_number);
		ArrayList<HandyFarmDTO> list = dao.cropAll_Select("01012345678");
		request.setAttribute("cropAllList", list);
	}
}