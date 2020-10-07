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

	// roboinsert	
	public void roboinsert(String _robo_serial, String _robo_img, String _robo_nickname, String _cultivar_number, String _gh_id, String _phone_number) {
		try {
			con = ds.getConnection();
			String query="INSERT INTO robo(robo_serial, robo_img, robo_nickname, cultivar_number, gh_id, phone_number)" +
			"VALUES (?,?,?,?,?,?)";
			
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
			
			
			String query = "SELECT crops_name, cultivar_number FROM crops";
			pstmt = con.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String crops_name = rs.getString("crops_name");
				String cultivar_number = rs.getString("cultivar_number");
				//데이터 객체 생성
				HandyFarmDTO data = new HandyFarmDTO();
				data.setCrops_name(crops_name);
				data.setCultivar_number(cultivar_number);
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

	// 온실 이름 가져옫기
	public String getGHName(String _gh_id) {
		String gh_name = null;
		try {
			// DB 연결
			con = ds.getConnection();
			
			// 온실 이름 조회
			String query = "SELECT gh_nickname " + 
						   "FROM greenhouse " + 
						   "WHERE gh_id = ?";
			
			pstmt = con.prepareStatement(query);
			// 매개변수 값 대입 -> set 메서드에 값 설정
			pstmt.setString(1,  _gh_id);
			// sql문 실행
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				gh_name = rs.getString("gh_nickname");
			}
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
		// 조회 결과 gh_name 리턴
		return gh_name;
	} // end getGHName
	
	// 생장 정보 가져오기
	public  ArrayList<HandyFarmDTO> growth(String _gh_id, String _cultivar_number) {
		// sensor, list 선언
		ArrayList<String> sensor = new ArrayList<String>();
		ArrayList<HandyFarmDTO> list = new ArrayList<HandyFarmDTO>();
		
		try {
			// DB 연결
			con = ds.getConnection();
			
			// 해당 온실 in 작물에 있는 센서 종류 조회
			String query = "SELECT sensor_type " + 
						   "FROM sensor " + 
						   "WHERE gh_id = ? AND cultivar_number = ?";
			
			pstmt = con.prepareStatement(query);
			// 매개변수 값 대입 -> set 메서드에 값 설정
			pstmt.setString(1, _gh_id);
			pstmt.setString(2, _cultivar_number);
			// sql문 실행
			rs = pstmt.executeQuery();
			
			// 레코드 값만큼 반복
			while (rs.next()) {
				// 레코드의 정보를 변수에 저장
				String sensor_type = rs.getString("sensor_type");
				
				sensor.add(sensor_type);
			}
			
			for (String _sensor_type : sensor) {
				// 해당 온실 in 작물에 대한 생장환경 정보 조회 sql문
				query = "SELECT sv.sensor_value " + 
						"FROM sensor_value AS sv " + 
						"JOIN sensor AS s " + 
						"ON sv.sensor_id = s.sensor_id " + 
						"WHERE sv.gh_id = ? AND sv.cultivar_number = ? AND s.sensor_type = ? " +
						"ORDER BY sv.measure_time DESC";
				
				pstmt = con.prepareStatement(query);
				// 매개변수 값 대입 -> set 메서드에 값 설정
				pstmt.setString(1, _gh_id);
				pstmt.setString(2, _cultivar_number);
				pstmt.setString(3, _sensor_type);
				// sql문 실행
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
					// 레코드의 정보를 각 변수에 저장
					float sensor_value = rs.getFloat("sensor_value");
					
					
					// data 객체 선언
					HandyFarmDTO data = new HandyFarmDTO();
					
					// data 객체의 set 메서드에 해당하는 값을 설정
					data.setSensor_type(_sensor_type);
					data.setSensor_value(sensor_value);
					
					if (_sensor_type.equals("temperature")) {
						data.setSensor_name("온도");
						data.setSensor_unit("℃");
					} else if (_sensor_type.equals("humidity")) {
						data.setSensor_name("습도");
						data.setSensor_unit("%");
					} else if (_sensor_type.equals("co2")) {
						data.setSensor_name("이산화탄소");
						data.setSensor_unit("단계");
					} else if (_sensor_type.equals("soil-moisture")) {
						data.setSensor_name("토양 수분도");
						data.setSensor_unit("%");
					} else if (_sensor_type.equals("sunshine")) {
						data.setSensor_name("일조량");
						data.setSensor_unit("lx");
					}
					
					list.add(data);
				} // end if
			} // end for
			
			// 해당 온실 in 작물에 대한 수확 가능 비율 정보 조회 sql문
			query = "SELECT harvestable " + 
					"FROM harvestable " + 
					"WHERE gh_id = ? AND cultivar_number = ?" + 
					"ORDER BY upload_time DESC";
			
			pstmt = con.prepareStatement(query);
			// 매개변수 값 대입 -> set 메서드에 값 설정
			pstmt.setString(1, _gh_id);
			pstmt.setString(2, _cultivar_number);
			// sql문 실행
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				// 레코드의 정보를 각 변수에 저장
				float harvestable = rs.getFloat("harvestable");
				
				// data 객체 선언
				HandyFarmDTO data = new HandyFarmDTO();
				
				// data 객체의 set 메서드에 해당하는 값을 설정
				data.setSensor_type("harvestable");
				data.setSensor_value(harvestable);
				data.setSensor_name("수확 가능 비율");
				data.setSensor_unit("%");
				
				list.add(data);
			} else {
				// data 객체 선언
				HandyFarmDTO data = new HandyFarmDTO();
				
				// data 객체의 set 메서드에 해당하는 값을 설정
				data.setSensor_type("harvestable");
				data.setSensor_value(0);
				data.setSensor_name("수확 가능 비율");
				data.setSensor_unit("%");
				
				list.add(data);
			}
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
		// 조회 결과 list 리턴
		return list;
	} // end growth
}
