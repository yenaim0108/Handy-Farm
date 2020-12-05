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

public class HandyFarmCropCommand implements HandyFarmCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// gh_id 가져오기
		String gh_id = request.getParameter("gh_id");
		
		// DB 연결
		HandyFarmDAO dao = new HandyFarmDAO();
		
		// 작물 목록 가져오기
		ArrayList<HandyFarmDTO> list = dao.cropList(gh_id);
		
		// json 파싱
		String json = new Gson().toJson(list);
		
		// 값 전송
		try {
			response.getWriter().write(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}