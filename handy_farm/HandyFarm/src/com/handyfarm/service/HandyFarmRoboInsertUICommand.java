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
		ArrayList<HandyFarmDTO> cultivar_list_insert = dao.cultivar_list_insert();
		
		request.setAttribute("cultivar_list_insert", cultivar_list_insert);
		System.out.println(cultivar_list_insert);
	}
}