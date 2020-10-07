package com.handyfarm.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.handyfarm.dao.HandyFarmDAO;
import com.handyfarm.entity.HandyFarmDTO;

public class HandyFarmRoboInsertUICommand implements HandyFarmCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		HandyFarmDAO dao = new HandyFarmDAO();
		//DB에 접근해서 list 메서드 호출
		ArrayList<HandyFarmDTO> cultivar_list = dao.cultivar_list();
		
		request.setAttribute("cultivar_list", cultivar_list);
		System.out.println(cultivar_list);
	}
}