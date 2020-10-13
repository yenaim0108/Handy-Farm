package com.handyfarm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.*;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.handyfarm.entity.HandyFarmDTO;

public class HandyFarmDAO {
	// �뜲�씠�꽣踰좎씠�뒪 �뿰寃� �젙蹂�
	DataSource ds;
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
		
	// DB �뿰寃고븯�뒗 湲곕낯 �깮�꽦�옄
	public HandyFarmDAO() {
		try { // jsp �떒�쐞 DB �뿰寃�
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mariadb");
		} catch(Exception e) {
			try { // java �떒�쐞 DB �뿰寃�
				Class.forName ("org.mariadb.jdbc.Driver");
				con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/handyfarm", "farmplant", "handyfarm");
			} catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	/**
	 * @author 源��뿰二�
	 * email : sym61503@naver.com
	 */

	/**
	 * @author �젙誘쇱젙
	 * email : as514188@gmail.com
	 */
	
	// robo_insert	�젙誘쇱젙
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
	
	//cultivar_list �젙誘쇱젙
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
				//�뜲�씠�꽣 媛앹껜 �깮�꽦
				HandyFarmDTO data = new HandyFarmDTO();
				data.setCrops_name(crops_name);
				data.setCultivar_number(cultivar_number);
				//由ъ뒪�듃 媛� 異붽�
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
	
	//cultivar_list_robo_insert �쐞�쓽 荑쇰━ �븿�닔�뿉�꽌 寃쎌슦�뿉 �뵲�씪 議곌툑留� 諛붽퓭�꽌 �빐�떦 �븿�닔 �벐�젮�뻽�뒗�뜲 �떆媛� �뾾�뼱�꽌 嫄� �깉濡� 留뚮벀
	//�젙誘쇱젙
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
				//�뜲�씠�꽣 媛앹껜 �깮�꽦
				HandyFarmDTO data = new HandyFarmDTO();
				data.setCrops_name(crops_name);
				data.setCultivar_number(cultivar_number);
				//由ъ뒪�듃 媛� 異붽�
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
	//cultivar_list_insert end
	
	//robo_select �젙誘쇱젙
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
				//�뜲�씠�꽣 媛앹껜 �깮�꽦
				HandyFarmDTO data = new HandyFarmDTO();
				data.setRobo_nickname(robo_nickname);
				data.setRobo_serial(robo_serial);
				data.setCultivar_number(cultivar_number);
				//由ъ뒪�듃 媛� 異붽�
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
			
	//robo_search �젙誘쇱젙
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
				
				//由ъ뒪�듃 媛� 異붽�
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
		
	//robo_Update �젙誘쇱젙
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
		
	//robo_delete �젙誘쇱젙
	
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
		
	//push_log �젙誘쇱젙
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
				//�뜲�씠�꽣 媛앹껜 �깮�꽦0
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
						
				if(push_category.equals("蹂묒땐�빐�븣由�")) {
					data.setPush_name("pests");
				}else if(push_category.equals("�궇�뵪�븣由�")) {
					data.setPush_name("weathernotifi");
				}else if(push_category.equals("�깮�옣�젙蹂�")) {
					data.setPush_name("growthinfo");
				}else if(push_category.equals("�떎�떆媛꾩젙蹂�")) {
					data.setPush_name("RTinfo");
				}

				//由ъ뒪�듃 媛� 異붽�
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
	
	//gh_insert
	
	public void gh_insert(String gh_img, String gh_nickname, String phone_number) {
		
		try {
			int count = 0;
			con = ds.getConnection();
			try{
				String query1 = "SELECT count(*) FROM greenhouse";
				pstmt = con.prepareStatement(query1);
				rs = pstmt.executeQuery();
						
				while(rs.next()) {
					count = rs.getInt(1);
				}
						
			}catch(Exception e) {
				e.printStackTrace();
			}
			String phone_num = phone_number.substring(3);
			
			String query="INSERT INTO greenhouse (gh_id, gh_img, gh_nickname, phone_number)" +
			"VALUES (?,?,?,?)";

			pstmt=con.prepareStatement(query);
			pstmt.setString(1, "gh-"+ phone_num+ "-" + count);
			pstmt.setString(2, gh_img);
			pstmt.setString(3, gh_nickname);
			pstmt.setString(4, phone_number);
			
			int n = pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		finally {
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
	}//gh_insert_end
	
	//gh_id 媛��졇�삤湲�
	
	public String getGHId(String gh_nickname) {
		String gh_id = null;
		try {
			con = ds.getConnection();
			String query = "SELECT gh_id FROM greenhouse WHERE gh_nickname = ?";
			
			pstmt = con.prepareStatement(query);
			pstmt.setString(1,  gh_nickname);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				gh_id = rs.getString("gh_id");
			}
		} catch (Exception e) {
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
		return gh_id;
	}
	
	//gh_id 媛��졇�삤湲� end

	/**
	 * @author �엫�삁�굹
	 * email : yenaim0108@gmail.com
	 */

	// �닔�솗 媛��뒫 鍮꾩쑉 DB�뿉 ���옣�븯湲�
	public void insertHarvestable(Timestamp time, String serial, float harvestable) {
		// datas, robo �꽑�뼵
		String[] datas = new String[3];
		String robo = null;
		
		// time 蹂��닔�뿉�꽌 �떆遺꾩큹媛믩쭔 媛��졇�삤湲�
		SimpleDateFormat sdf = new SimpleDateFormat("HHmmss");
		String hms = sdf.format(time);
		
		try {
			// 濡쒕낫媛� �냽�븳 �뭹醫낅쾲�샇, �삩�떎 ID, �쑕���룿 踰덊샇 媛��졇�삤�뒗 sql臾�
			String query = "SELECT cultivar_number, gh_id, phone_number " + 
						   "FROM robo " + 
						   "WHERE robo_serial = ?";
			
			pstmt = con.prepareStatement(query);
			// 留ㅺ컻蹂��닔 媛� ���엯 -> set 硫붿꽌�뱶�뿉 媛� �꽕�젙
			pstmt.setString(1, serial);
			// sql臾� �떎�뻾
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				// datas 諛곗뿴�쓽 媛� �꽕�젙
				datas[0] = rs.getString("cultivar_number");
				datas[1] = rs.getString("gh_id");
				datas[2] = rs.getString("phone_number");
				
				// 濡쒕낫 �떆由ъ뼹 踰덊샇 �뮮 8�옄由� 媛��졇�삤湲�
				robo = serial.substring(serial.length() - 8, serial.length());
			}
			
			// harvestable �뀒�씠釉붿뿉 �뜲�씠�꽣瑜� �꽔�뒗 sql臾�
			query = "INSERT INTO harvestable (hrv_id, harvestable, upload_time, robo_serial, cultivar_number, gh_id, phone_number) " + 
					"VALUES (?, ?, ?, ?, ?, ?, ?)";
			
			pstmt = con.prepareStatement(query);
			// 留ㅺ컻蹂��닔 媛� ���엯 -> set 硫붿꽌�뱶�뿉 媛� �꽕�젙
			pstmt.setString(1, "hrv-" + robo + "-" + hms);
			pstmt.setFloat(2, harvestable);
			pstmt.setTimestamp(3, time);
			pstmt.setString(4, serial);
			pstmt.setString(5, datas[0]);
			pstmt.setString(6, datas[1]);
			pstmt.setString(7, datas[2]);
			// sql臾� �쟻�슜
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
	
	// �삩�떎 紐⑸줉 媛��졇�삤湲� �엫�삁�굹
	public ArrayList<HandyFarmDTO> GHSelect(String _phone_number) {
		// list �꽑�뼵
		ArrayList<HandyFarmDTO> list = new ArrayList<HandyFarmDTO>();
		
		try {
			// DB �뿰寃�
			con = ds.getConnection();
			
			// �삩�떎 紐⑸줉�쓣 媛��졇�삤�뒗 sql臾�
			String query = "SELECT * " + 
						   "FROM greenhouse " + 
						   "WHERE phone_number = ?";
			pstmt = con.prepareStatement(query);
			// 留ㅺ컻蹂��닔 媛� ���엯 -> set 硫붿꽌�뱶�뿉 媛� �꽕�젙
			pstmt.setString(1, _phone_number);
			// sql臾� �떎�뻾
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// �젅肄붾뱶�쓽 �젙蹂대�� 媛� 蹂��닔�뿉 ���옣
				String gh_id = rs.getString("gh_id");
				String gh_img = rs.getString("gh_img");
				String gh_nickname = rs.getString("gh_nickname");
				
				// data 媛앹껜 �꽑�뼵
				HandyFarmDTO data = new HandyFarmDTO();
				
				// data 媛앹껜�쓽 set 硫붿꽌�뱶�뿉 �빐�떦�븯�뒗 媛믪쓣 �꽕�젙
				data.setGh_id(gh_id);
				data.setGh_nickname(gh_nickname);
				if (gh_img != null) { // DB�뿉 ���옣�맂 �삩�떎 �궗吏� 媛��졇�삤湲�
					data.setGh_img(gh_img);
				} else { // DB�뿉 ���옣�맂 �궗吏꾩씠 �뾾�쑝硫� HandyFarm Logo �궗吏� 媛��졇�삤湲�
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
		// 議고쉶 寃곌낵 list 由ы꽩
		return list;
	} // end GHSelect

	// 濡쒕낫 紐⑸줉 媛��졇�삤湲� �엫�삁�굹
	public ArrayList<HandyFarmDTO> RoboSelect(String _gh_id) {
		// list �꽑�뼵
		ArrayList<HandyFarmDTO> list = new ArrayList<HandyFarmDTO>();
		
		try {
			// DB �뿰寃�
			con = ds.getConnection();
			
			// 濡쒕낫 紐⑸줉�쓣 媛��졇�삤�뒗 sql臾�
			String query = "SELECT * " + 
						   "FROM robo AS r " + 
						   "JOIN crops AS c " + 
						   "ON r.cultivar_number = c.cultivar_number " + 
						   "WHERE gh_id = ?";
			pstmt = con.prepareStatement(query);
			// 留ㅺ컻蹂��닔 媛� ���엯 -> set 硫붿꽌�뱶�뿉 媛� �꽕�젙
			pstmt.setString(1, _gh_id);
			// sql臾� �떎�뻾
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// �젅肄붾뱶�쓽 �젙蹂대�� 媛� 蹂��닔�뿉 ���옣
				String robo_serial = rs.getString("robo_serial");
				String robo_img = rs.getString("robo_img");
				String robo_nickname = rs.getString("robo_nickname");
				String crops_name = rs.getString("crops_name");
				
				// data 媛앹껜 �꽑�뼵
				HandyFarmDTO data = new HandyFarmDTO();
				
				// data 媛앹껜�쓽 set 硫붿꽌�뱶�뿉 �빐�떦�븯�뒗 媛믪쓣 �꽕�젙
				data.setRobo_serial(robo_serial);
				data.setRobo_nickname(robo_nickname);
				data.setCrops_name(crops_name);
				if (robo_img != null) { // DB�뿉 ���옣�맂 濡쒕낫 �궗吏� 媛��졇�삤湲�
					data.setRobo_img(robo_img);
				} else { // DB�뿉 ���옣�맂 �궗吏꾩씠 �뾾�쑝硫� HandyFarm Logo �궗吏� 媛��졇�삤湲�
					data.setRobo_img("../icon/handyfarm_logo.png");
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
		// 議고쉶 寃곌낵 list 由ы꽩
		return list;
	} // end RoboSelect
	
	// �삩�떎 �씠由� 媛��졇�삤湲� �엫�삁�굹
	public String getGHName(String _gh_id) {
		String gh_name = null;
		try {
			// DB �뿰寃�
			con = ds.getConnection();
			
			// �삩�떎 �씠由� 議고쉶
			String query = "SELECT gh_nickname " + 
						   "FROM greenhouse " + 
						   "WHERE gh_id = ?";
			
			pstmt = con.prepareStatement(query);
			// 留ㅺ컻蹂��닔 媛� ���엯 -> set 硫붿꽌�뱶�뿉 媛� �꽕�젙
			pstmt.setString(1,  _gh_id);
			// sql臾� �떎�뻾
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
		// 議고쉶 寃곌낵 gh_name 由ы꽩
		return gh_name;
	} // end getGHName
	
	// �깮�옣 �젙蹂� 媛��졇�삤湲� �엫�삁�굹
	public  ArrayList<HandyFarmDTO> growth(String _gh_id, String _cultivar_number) {
		// sensor, list �꽑�뼵
		ArrayList<String> sensor = new ArrayList<String>();
		ArrayList<HandyFarmDTO> list = new ArrayList<HandyFarmDTO>();
		
		try {
			// DB �뿰寃�
			con = ds.getConnection();
			
			// �빐�떦 �옉臾� in �삩�떎�뿉 �엳�뒗 �꽱�꽌 醫낅쪟 議고쉶
			String query = "SELECT sensor_type " + 
						   "FROM sensor " + 
						   "WHERE gh_id = ? AND cultivar_number = ?";
			
			pstmt = con.prepareStatement(query);
			// 留ㅺ컻蹂��닔 媛� ���엯 -> set 硫붿꽌�뱶�뿉 媛� �꽕�젙
			pstmt.setString(1, _gh_id);
			pstmt.setString(2, _cultivar_number);
			// sql臾� �떎�뻾
			rs = pstmt.executeQuery();
			
			// �젅肄붾뱶 媛믩쭔�겮 諛섎났
			while (rs.next()) {
				// �젅肄붾뱶�쓽 �젙蹂대�� 蹂��닔�뿉 ���옣
				String sensor_type = rs.getString("sensor_type");
				
				sensor.add(sensor_type);
			}
			
			for (String _sensor_type : sensor) {
				// �빐�떦 �옉臾� in �삩�떎�뿉 ���븳 �깮�옣�솚寃� �젙蹂� 議고쉶 sql臾�
				query = "SELECT sv.sensor_value " + 
						"FROM sensor_value AS sv " + 
						"JOIN sensor AS s " + 
						"ON sv.sensor_id = s.sensor_id " + 
						"WHERE sv.gh_id = ? AND sv.cultivar_number = ? AND s.sensor_type = ? " +
						"ORDER BY sv.measure_time DESC";
				
				pstmt = con.prepareStatement(query);
				// 留ㅺ컻蹂��닔 媛� ���엯 -> set 硫붿꽌�뱶�뿉 媛� �꽕�젙
				pstmt.setString(1, _gh_id);
				pstmt.setString(2, _cultivar_number);
				pstmt.setString(3, _sensor_type);
				// sql臾� �떎�뻾
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
					// �젅肄붾뱶�쓽 �젙蹂대�� 媛� 蹂��닔�뿉 ���옣
					float sensor_value = rs.getFloat("sensor_value");
					
					
					// data 媛앹껜 �꽑�뼵
					HandyFarmDTO data = new HandyFarmDTO();
					
					// data 媛앹껜�쓽 set 硫붿꽌�뱶�뿉 �빐�떦�븯�뒗 媛믪쓣 �꽕�젙
					data.setSensor_type(_sensor_type);
					data.setSensor_value(sensor_value);
					
					if (_sensor_type.equals("temperature")) {
						data.setSensor_name("�삩�룄");
						data.setSensor_unit("�꼦");
					} else if (_sensor_type.equals("humidity")) {
						data.setSensor_name("�뒿�룄");
						data.setSensor_unit("%");
					} else if (_sensor_type.equals("co2")) {
						data.setSensor_name("�씠�궛�솕�깂�냼");
						data.setSensor_unit("�떒怨�");
					} else if (_sensor_type.equals("soil-moisture")) {
						data.setSensor_name("�넗�뼇 �닔遺꾨룄");
						data.setSensor_unit("%");
					} else if (_sensor_type.equals("sunshine")) {
						data.setSensor_name("�씪議곕웾");
						data.setSensor_unit("lx");
					}
					
					list.add(data);
				} // end if
			} // end for
			
			// �빐�떦 �삩�떎 in �옉臾쇱뿉 ���븳 �닔�솗 媛��뒫 鍮꾩쑉 �젙蹂� 議고쉶 sql臾�
			query = "SELECT harvestable " + 
					"FROM harvestable " + 
					"WHERE gh_id = ? AND cultivar_number = ? " + 
					"ORDER BY upload_time DESC";
			
			pstmt = con.prepareStatement(query);
			// 留ㅺ컻蹂��닔 媛� ���엯 -> set 硫붿꽌�뱶�뿉 媛� �꽕�젙
			pstmt.setString(1, _gh_id);
			pstmt.setString(2, _cultivar_number);
			// sql臾� �떎�뻾
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				// �젅肄붾뱶�쓽 �젙蹂대�� 媛� 蹂��닔�뿉 ���옣
				float harvestable = rs.getFloat("harvestable");
				
				// data 媛앹껜 �꽑�뼵
				HandyFarmDTO data = new HandyFarmDTO();
				
				// data 媛앹껜�쓽 set 硫붿꽌�뱶�뿉 �빐�떦�븯�뒗 媛믪쓣 �꽕�젙
				data.setSensor_type("harvestable");
				data.setSensor_value(harvestable);
				data.setSensor_name("�닔�솗 媛��뒫 鍮꾩쑉");
				data.setSensor_unit("%");
				
				list.add(data);
			} else {
				// data 媛앹껜 �꽑�뼵
				HandyFarmDTO data = new HandyFarmDTO();
				
				// data 媛앹껜�쓽 set 硫붿꽌�뱶�뿉 �빐�떦�븯�뒗 媛믪쓣 �꽕�젙
				data.setSensor_type("harvestable");
				data.setSensor_value(0);
				data.setSensor_name("�닔�솗 媛��뒫 鍮꾩쑉");
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
		// 議고쉶 寃곌낵 list 由ы꽩
		return list;
	} // end growth
}