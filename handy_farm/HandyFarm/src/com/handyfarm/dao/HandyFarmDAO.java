package com.handyfarm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class HandyFarmDAO {
	// 데이터베이스 연결 정보
	DataSource ds;
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public HandyFarmDAO() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mariadb");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// 로그인
	public int login(String _phoneNumber, String _authNumber) {
		try {
			con = ds.getConnection(); // DB 연결
			
			// 회원 정보에 존재하는지 확인
			String query = "select phoneNumber "
						 + "from member "
						 + "where phoneNumber = ?"; // sql문 작성
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, _phoneNumber);
			rs = pstmt.executeQuery(); // sql문 실행
			
			if (rs.next()) { return 1; } // 존재하는 회원일 때
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) con.close();
				if (pstmt != null) pstmt.close();
				if (rs != null) rs.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return 0; // 존재하지 않는 회원일 때
	}
	
	// 인증하기
}
