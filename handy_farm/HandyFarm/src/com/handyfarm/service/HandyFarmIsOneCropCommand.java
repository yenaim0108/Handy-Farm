/**
    * @author 임예나
    * email : yenaim0108@gmail.com
*/

package com.handyfarm.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.handyfarm.dao.HandyFarmDAO;
import com.handyfarm.entity.HandyFarmDTO;

public class HandyFarmIsOneCropCommand implements HandyFarmCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// gh_id 가져오기
		String gh_id = request.getParameter("gh_id");
		
		// DB 연결
		HandyFarmDAO dao = new HandyFarmDAO();
		
		// 작물이 한개인가?
		ArrayList<HandyFarmDTO> list = dao.isOneCrop(gh_id);
		
		try {
			// json 파싱하기
			String json = new Gson().toJson(list);
			
			// 값 전송
			response.getWriter().write(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}