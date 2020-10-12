package com.handyfarm.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.handyfarm.dao.HandyFarmDAO;
import com.handyfarm.entity.HandyFarmDTO;

public class HandyFarmPushSelectCommand implements HandyFarmCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		String phone_number = "01062892166";
		
		HandyFarmDAO dao = new HandyFarmDAO();
		//DB에 접근해서 list 메서드 호출
		ArrayList<HandyFarmDTO> push_list = dao.push_list(phone_number);
		
		request.setAttribute("push_list", push_list);
		System.out.println(push_list);
		
	}
}