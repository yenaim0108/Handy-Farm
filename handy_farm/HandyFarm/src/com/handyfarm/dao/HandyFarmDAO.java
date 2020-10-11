package com.handyfarm.dao;

import java.sql.*;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

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
		
	// DB 연결하는 기본 생성자
	public HandyFarmDAO() { // jsp 단위 DB 연결
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mariadb");
		} catch(Exception e) {
			try { // java 단위 DB 연결
				Class.forName ("org.mariadb.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/handyfarm", "farmplant", "handyfarm");
			} catch(Exception e2) {
				e2.printStackTrace();
			}
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
	
	/**
	 * @author 임예나
	 * email : yenaim0108@gmail.com
	 */
	
	// 수확 가능 비율 DB에 저장하기
	public void insertHarvestable(Timestamp time, String serial, float harvestable) {
		// datas, robo 선언
		String[] datas = new String[3];
		String robo = null;
		
		// time 변수에서 시분초값만 가져오기
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
		String hms = sdf.format(time);
		
		try {
			// 로보가 속한 품종번호, 온실 ID, 휴대폰 번호 가져오는 sql문
			String query = "SELECT cultivar_number, gh_id, phone_number " + 
						   "FROM robo " + 
						   "WHERE robo_serial = ?";
			
			pstmt = con.prepareStatement(query);
			// 매개변수 값 대입 -> set 메서드에 값 설정
			pstmt.setString(1, serial);
			// sql문 실행
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				// datas 배열의 값 설정
				datas[0] = rs.getString("cultivar_number");
				datas[1] = rs.getString("gh_id");
				datas[2] = rs.getString("phone_number");
				
				// 로보 시리얼 번호 뒷 8자리 가져오기
				robo = serial.substring(serial.length() - 8, serial.length());
			}
			
			// harvestable 테이블에 데이터를 넣는 sql문
			query = "INSERT INTO harvestable (hrv_id, harvestable, upload_time, robo_serial, cultivar_number, gh_id, phone_number) " + 
					"VALUES (?, ?, ?, ?, ?, ?, ?)";
			
			pstmt = con.prepareStatement(query);
			// 매개변수 값 대입 -> set 메서드에 값 설정
			pstmt.setString(1, "hrv-" + robo + "-" + hms);
			pstmt.setFloat(2, harvestable);
			pstmt.setTimestamp(3, time);
			pstmt.setString(4, serial);
			pstmt.setString(5, datas[0]);
			pstmt.setString(6, datas[1]);
			pstmt.setString(7, datas[2]);
			// sql문 적용
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		finally {
			try {
				if (con != null) { con.close(); }
				if (pstmt != null) { pstmt.close(); }
				if (rs != null) { rs.close(); }
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // end finally
	} // end insertHarvestable
	
	// 온실 목록 가져오기
	public ArrayList<HandyFarmDTO> GHSelect(String _phone_number) {
		// list 선언
		ArrayList<HandyFarmDTO> list = new ArrayList<HandyFarmDTO>();
		
		try {
			// DB 연결
			con = ds.getConnection();
			
			// 온실 목록을 가져오는 sql문
			String query = "SELECT * " + 
						   "FROM greenhouse " + 
						   "WHERE phone_number = ?";
			pstmt = con.prepareStatement(query);
			// 매개변수 값 대입 -> set 메서드에 값 설정
			pstmt.setString(1, _phone_number);
			// sql문 실행
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// 레코드의 정보를 각 변수에 저장
				String gh_id = rs.getString("gh_id");
				String gh_img = rs.getString("gh_img");
				String gh_nickname = rs.getString("gh_nickname");
				
				// data 객체 선언
				HandyFarmDTO data = new HandyFarmDTO();
				
				// data 객체의 set 메서드에 해당하는 값을 설정
				data.setGh_id(gh_id);
				data.setGh_nickname(gh_nickname);
				if (gh_img != null) { // DB에 저장된 온실 사진 가져오기
					data.setGh_img(gh_img);
				} else { // DB에 저장된 사진이 없으면 HandyFarm Logo 사진 가져오기
					data.setGh_img("../icon/handyfarm_logo.png");
				}
				
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
	} // end GHSelect
	
	// 온실 이름 가져오기
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
			
			// 해당 작물 in 온실에 있는 센서 종류 조회
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
				// 해당 작물 in 온실에 대한 생장환경 정보 조회 sql문
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
					"WHERE gh_id = ? AND cultivar_number = ? " + 
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
