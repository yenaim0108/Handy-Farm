package com.handyfarm.dao;

<<<<<<< HEAD
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
=======
>>>>>>> 152c51b61c2626c0735f4a1c662c56b957a03a7e
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
<<<<<<< HEAD


	// robo_insert	정민정
	
=======
	
	// roboinsert	
>>>>>>> 152c51b61c2626c0735f4a1c662c56b957a03a7e
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
	}//robo_insert end
	
	//cultivar_list 정민정
	public ArrayList<HandyFarmDTO> cultivar_list(String robo_serial){
		ArrayList<HandyFarmDTO> cultivar_list = new ArrayList<HandyFarmDTO> ();
		
		try {
			con = ds.getConnection();
			String query = "SELECT crops_name, cultivar_number FROM crops where cultivar_number <> (SELECT cultivar_number FROM robo where robo_serial = ?)";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, robo_serial);
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
	//cultivar_list end
	
	//cultivar_list_robo_insert 위의 쿼리 함수에서 경우에 따라 조금만 바꿔서 해당 함수 쓰려했는데 시간 없어서 걍 새로 만듦
	//정민정
	public ArrayList<HandyFarmDTO> cultivar_list_insert(){
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
<<<<<<< HEAD
	//cultivar_list_insert end
	
	
	//robo_select 정민정
	public ArrayList<HandyFarmDTO> robo_list(String gh_id){
		ArrayList<HandyFarmDTO> robo_list = new ArrayList<HandyFarmDTO> ();
		
		try {
			con = ds.getConnection();

			String query = "SELECT robo_nickname, robo_serial, cultivar_number FROM robo where gh_id = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, gh_id);
			rs = pstmt.executeQuery();
					
			while(rs.next()) {
				String robo_nickname = rs.getString("robo_nickname");
				String robo_serial = rs.getString("robo_serial");
				String cultivar_number = rs.getString("cultivar_number"); 
				//데이터 객체 생성
				HandyFarmDTO data = new HandyFarmDTO();
				data.setRobo_nickname(robo_nickname);
				data.setRobo_serial(robo_serial);
				data.setCultivar_number(cultivar_number);
				//리스트 값 추가
				robo_list.add(data);
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
		System.out.println(robo_list);
		return robo_list;
	}
	//robo_select end
		
			
			
			
	//robo_search 정민정
	public ArrayList<HandyFarmDTO> robo_search_list(String robo_serial) {
		
		ArrayList<HandyFarmDTO> robo_search_list = new ArrayList<HandyFarmDTO> ();
		
		try {
			con=ds.getConnection();
			String query="SELECT * FROM robo AS r JOIN crops AS c ON r.cultivar_number = c.cultivar_number WHERE robo_serial = ? ";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, robo_serial);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String robo_nickname = rs.getString("robo_nickname");
				String robo_img = rs.getString("robo_img");
				String crops_name = rs.getString("crops_name");
				String cultivar_number = rs.getString("cultivar_number");
				
				HandyFarmDTO data = new HandyFarmDTO();
				data.setRobo_nickname(robo_nickname);
				data.setRobo_img(robo_img);
				data.setCrops_name(crops_name);
				data.setCultivar_number(cultivar_number);
				
				//리스트 값 추가
				robo_search_list.add(data);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
		try {
			if(pstmt != null) pstmt.close();
		}catch(SQLException e) {
			e.printStackTrace();
			}
		}
		return robo_search_list;
	}
	//robo_search end
		
		
	//robo_Update 정민정
	public void robo_update(String robo_serial, String robo_nickname, String robo_img, String cultivar_number) {
		
		try {
			con = ds.getConnection();
			String query = "UPDATE robo SET robo_nickname = ? , robo_img = ?, cultivar_number = ? where robo_serial = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, robo_nickname);
			pstmt.setString(2, robo_img);
			pstmt.setString(3, cultivar_number);
			pstmt.setString(4, robo_serial);
			int n = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(con != null)
					con.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}//robo_Update end
		
		
		
	//robo_delete 정민정
	
	public void robo_delete(String robo_serial) {
		try {
			con = ds.getConnection();
			String query = "DELETE FROM robo WHERE robo_serial = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, robo_serial);
			int n = pstmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
			if (pstmt != null)
				pstmt.close();
			if (con != null)
				con.close();
		} catch(SQLException e) {
			e.printStackTrace();
			}
		}
	}
		
	//robo_delete end
		
		
	//push_log 정민정
	public ArrayList<HandyFarmDTO> push_list(String phone_number){
		ArrayList<HandyFarmDTO> cultivar_list = new ArrayList<HandyFarmDTO> ();
		ResultSet rs2 = null;
		
		try {
			con = ds.getConnection();
					
			String query = "SELECT * FROM push_log where phone_number = ? ORDER BY push_date DESC";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, phone_number);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String push_category = rs.getString("push_category");
				String push_msg = rs.getString("push_msg");
				Timestamp push_date = rs.getTimestamp("push_date");
				boolean read_status = rs.getBoolean("read_status");
				String cultivar_number = rs.getString("cultivar_number");
				String gh_id = rs.getString("gh_id");
				//데이터 객체 생성0
				HandyFarmDTO data = new HandyFarmDTO();
						
				data.setPush_category(push_category);
				data.setPush_msg(push_msg);
				data.setPush_date(push_date);
				data.setRead_status(read_status);
				data.setCultivar_number(cultivar_number);
				data.setGh_id(gh_id);

				try{
					String query1 = "SELECT gh_nickname FROM greenhouse where gh_id = ?";
					pstmt = con.prepareStatement(query1);
					pstmt.setString(1, gh_id);
					rs2 = pstmt.executeQuery();
							
					if(rs2.next()) {
						String gh_nickname = rs2.getString("gh_nickname");
						data.setGh_nickname(gh_nickname);
						System.out.println(gh_nickname);
					}
							
				}catch(Exception e) {
					e.printStackTrace();
				}
						
				try{
					String query1 = "SELECT crops_name FROM crops where cultivar_number = ?";
					pstmt = con.prepareStatement(query1);
					pstmt.setString(1, cultivar_number);
					rs2 = pstmt.executeQuery();
							
					if(rs2.next()) {
						String crops_name = rs2.getString("crops_name");
						data.setCrops_name(crops_name);
					}
							
				}catch(Exception e) {
					e.printStackTrace();
				}
				System.out.println(push_category);
						
				if(push_category.equals("병충해알림")) {
					data.setPush_name("pests");
				}else if(push_category.equals("날씨알림")) {
					data.setPush_name("weathernotifi");
				}else if(push_category.equals("생장정보")) {
					data.setPush_name("growthinfo");
				}else if(push_category.equals("실시간정보")) {
					data.setPush_name("RTinfo");
				}

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
	//push_log end
		
		
=======
	//cultivar_select end
>>>>>>> 152c51b61c2626c0735f4a1c662c56b957a03a7e
	
	/**
	 * @author 임예나
	 * email : yenaim0108@gmail.com
	 */
	
<<<<<<< HEAD
	// 수확 가능 비율 DB에 저장하기 임예나
=======
	// 수확 가능 비율 DB에 저장하기
>>>>>>> 152c51b61c2626c0735f4a1c662c56b957a03a7e
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
	
<<<<<<< HEAD
	// 온실 목록 가져오기 임예나
=======
	// 온실 목록 가져오기
>>>>>>> 152c51b61c2626c0735f4a1c662c56b957a03a7e
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
	
<<<<<<< HEAD
	// 온실 이름 가져오기 임예나
=======
	// 온실 이름 가져오기
>>>>>>> 152c51b61c2626c0735f4a1c662c56b957a03a7e
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
	

	// 생장 정보 가져오기 임예나
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