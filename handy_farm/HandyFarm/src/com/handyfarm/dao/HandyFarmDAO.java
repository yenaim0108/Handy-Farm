package com.handyfarm.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class HandyFarmDAO {
	// 데이터베이스 연결 정보
	DataSource ds;
	Connection con = null;
	
	public HandyFarmDAO() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mariadb");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		login(2, 3);
	}
	
	// 로그인
	public int login(int phoneNumber, int authNumber) {
		try {
			con = ds.getConnection();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) con.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return 0;
	}
}
