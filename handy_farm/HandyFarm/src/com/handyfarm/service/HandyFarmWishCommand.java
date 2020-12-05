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

public class HandyFarmWishCommand implements HandyFarmCommand {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		// crops_id, wish 가져오기
		String crops_id = request.getParameter("crops_id");
		String wish = request.getParameter("wish");
		
		// DB 연결
		HandyFarmDAO dao = new HandyFarmDAO();
		
		// 찜 정보 업데이트 하기
		ArrayList<HandyFarmDTO> list = dao.wish_update(crops_id, wish);
		
		
		//json 파싱하기
		String json = new Gson().toJson(list);
		
		try {
			// 값 전송
			response.getWriter().write(json);
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}