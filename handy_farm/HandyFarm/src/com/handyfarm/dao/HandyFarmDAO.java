package com.handyfarm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.handyfarm.entity.HandyFarmDTO;

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

	//roboinsert
	
	public void roboinsert(String _robo_serial, String _robo_img, String _robo_nickname, String _cultivar_number, String _gh_id, String _phone_number) {
		try {
			con = ds.getConnection();
			String query="insert into board(robo_serial, robo_img, robo_nickname, cultivar_number, gh_id, phone_number)" +
			"values (?,?,?,?,?,?)";
			
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, _robo_serial);
			pstmt.setString(2, _robo_img);
			pstmt.setString(3, _robo_nickname);
			pstmt.setString(4, _cultivar_number);
			pstmt.setString(5, _gh_id);
			pstmt.setString(6, _phone_number);
			
			int n = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		finally {
			try {
				if(pstmt != null) pstmt.close();
				}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}//roboinsert end
	
	//cultivar_select
	public ArrayList<HandyFarmDTO> cultivar_list(){
		ArrayList<HandyFarmDTO> cultivar_list = new ArrayList<HandyFarmDTO> ();
		
		try {
			con = ds.getConnection();
			String query = "SELECT crops_name FROM crops";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String crops_name = rs.getString(0);
				
				//데이터 객체 생성
				HandyFarmDTO data = new HandyFarmDTO();
				data.setCrops_name(crops_name);
				//리스트 값 추가
				cultivar_list.add(data);
			}
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if(con != null)
					con.close();
			} catch (SQLException e)  {
				e.printStackTrace();
			}
		}
		System.out.println(cultivar_list);
		return cultivar_list;
	}
	//cultivar_select end

	// 생장 정보 가져오기
	public  HandyFarmDTO growth(String _gh_id, String _cultivar_number) {
		HandyFarmDTO data = new HandyFarmDTO();
		
		try {
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) { con.close(); }
				if (pstmt != null) { pstmt.close(); }
				if (rs != null) { rs.close(); }
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // end finally
		return data;
	} // end growth
}
