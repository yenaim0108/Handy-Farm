package com.handyfarm.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashSet;

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
      
   // DB 연결하는 기본 생성자 임예나
   public HandyFarmDAO() {
      try { // jsp 단위 DB 연결
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
   } // end HandyFarmDAO 임예나

   /**
	 * @author 김연주
	 * email : sym61503@naver.com
	 */
	
	// cropAll_Select 김연주 -----이미지도 DB에 넣고 추가해줘야 됌. ------- 민정이 추가함
	public ArrayList<HandyFarmDTO> cropAll_Select(String id) {
		// list 선언
		ArrayList<HandyFarmDTO> list = new ArrayList<HandyFarmDTO>();
		try {
			// DB 연결
			con = ds.getConnection();
			
			// 병충해 목록을 가져오는 sql문
			String query = "SELECT * FROM crops c, wish w WHERE c.crops_id =w.crops_id AND id=?;";
			
			pstmt = con.prepareStatement(query);

			// 매개변수 값 대입 -> set 메서드에 값 설정
			pstmt.setString(1, id);

			// sql문 실행
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				
				// 레코드의 정보를 각 변수에 저장
				String crops_name = rs.getString("crops_name");
				String crops_img = rs.getString("crops_img");
				String crops_id = rs.getString("crops_id");
				String id2=id;
				Boolean wish = rs.getBoolean("wish");
					
				// data 객체 선언
				HandyFarmDTO data = new HandyFarmDTO();
				
				// data  객체의 set 메서드에 해당하는 값을 설정
				data.setCrops_name(crops_name);
				data.setCrops_img(crops_img);
				data.setCrops_id(crops_id);
				data.setId(id2);
				data.setWish(wish);
				
				
				if (wish == true) { // 찜하기 선택이 되었다면
					data.setImg("../icon/like_heart.png");
				} else { // 찜하기 선택이 안되어있다면
					data.setImg("../icon/unlike_heart.png");
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
		// 조회 결과 List 반환
		
		return list;
	} // end cropAll_Select 김연주 -----이미지도 DB에 넣고 추가해줘야 됌. ------- 민정이 추가함
	
	
	// realInfo_pest_Select 김연주
	public ArrayList<HandyFarmDTO> realInfo_pest_Select(String _category) {
		// list 선언
		ArrayList<HandyFarmDTO> list = new ArrayList<HandyFarmDTO>();
		
		try {
			// DB 연결
			con = ds.getConnection();
			
			// 병충해 목록을 가져오는 sql문
			String query = "SELECT * " + 
						   "FROM crawling " + 
						   "WHERE category=? ";
						   //"ORDER BY c_DATE DESC";
			pstmt = con.prepareStatement(query);
			
			// 매개변수 값 대입 -> set 메서드에 값 설정
			pstmt.setString(1, _category);
			
			// sql문 실행
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// 레코드의 정보를 각 변수에 저장
				String link = rs.getString("link");
				int views = rs.getInt("views");
				String title = rs.getString("title");
				String content = rs.getString("content");
				String c_date = rs.getString("c_date");
				String img = rs.getString("img");
				
				
				// data 객체 선언
				HandyFarmDTO data = new HandyFarmDTO();
				
				// data  객체의 set 메서드에 해당하는 값을 설정
				data.setLink(link);
				data.setViews(views);
				data.setTitle(title);
				data.setContent(content);
				data.setC_date(c_date);
				
				if (!img.equals("")) { // DB에 저장된 사진이 존재하면 해당 사진 가져오기
					data.setImg(img);
				} else { // DB에 저장된 사진이 없으면 HandyFarm Logo 사진 가져오기
					data.setImg("../icon/handyfarm_logo.png");
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
		// 조회 결과 List 반환
		
		return list;
	} // end realInfo_pest_Select 김연주

   /**
    * @author 정민정
    * email : as514188@gmail.com
    */
   
   // robo_insert   정민정
   public void roboinsert(String _robo_serial, String _robo_img, String _robo_nickname, String _gh_id, String _id, String _crops_id) {
      try {
         con = ds.getConnection();
         String query="INSERT INTO robo(robo_serial, robo_img, robo_nickname, gh_id, id, crops_id)" +
         "VALUES (?,?,?,?,?,?)";
         
         pstmt=con.prepareStatement(query);
         pstmt.setString(1, _robo_serial);
         pstmt.setString(2, _robo_img);
         pstmt.setString(3, _robo_nickname);
         pstmt.setString(4, _gh_id);
         pstmt.setString(5, _id);
         pstmt.setString(6, _crops_id);
         
         pstmt.executeUpdate();
         
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
   }//robo_insert 정민정 end
   
   //crops_list 정민정
   public ArrayList<HandyFarmDTO> crops_list(String robo_serial){
      ArrayList<HandyFarmDTO> crops_list = new ArrayList<HandyFarmDTO> ();
      
      try {
         con = ds.getConnection();
         String query = "SELECT crops_name, crops_id FROM crops where crops_id <> (SELECT crops_id FROM robo where robo_serial = ?)";
         pstmt = con.prepareStatement(query);
         pstmt.setString(1, robo_serial);
         rs = pstmt.executeQuery();
         
         while(rs.next()) {
            String crops_name = rs.getString("crops_name");
            String crops_id = rs.getString("crops_id");
            //데이터 객체 생성
            HandyFarmDTO data = new HandyFarmDTO();
            data.setCrops_name(crops_name);
            data.setCrops_id(crops_id);
            //리스트 값 추가
            crops_list.add(data);
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
      return crops_list;
   }
   //cultivar_list 정민정 end
   
   //crops_list_robo_insert 정민정 / 위의 쿼리 함수에서 경우에 따라 조금만 바꿔서 해당 함수 쓰려했는데 시간 없어서 걍 새로 만듦
   public ArrayList<HandyFarmDTO> crops_list_insert(){
      ArrayList<HandyFarmDTO> crops_list = new ArrayList<HandyFarmDTO> ();
      
      try {
         con = ds.getConnection();
         String query = "SELECT crops_name, crops_id FROM crops";
         pstmt = con.prepareStatement(query);
         rs = pstmt.executeQuery();
         
         while(rs.next()) {
            String crops_name = rs.getString("crops_name");
            String crops_id = rs.getString("crops_id");
            //데이터 객체 생성
            HandyFarmDTO data = new HandyFarmDTO();
            data.setCrops_name(crops_name);
            data.setCrops_id(crops_id);
            //리스트 값 추가
            crops_list.add(data);
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
      return crops_list;
   }
   //cultivar_list_insert 정민정 end
   
   //robo_select 정민정
   public ArrayList<HandyFarmDTO> robo_list(String gh_id){
      ArrayList<HandyFarmDTO> robo_list = new ArrayList<HandyFarmDTO> ();
      
      try {
         con = ds.getConnection();

         String query = "SELECT robo_nickname, robo_serial, crops_id FROM robo where gh_id = ?";
         pstmt = con.prepareStatement(query);
         pstmt.setString(1, gh_id);
         rs = pstmt.executeQuery();
               
         while(rs.next()) {
            String robo_nickname = rs.getString("robo_nickname");
            String robo_serial = rs.getString("robo_serial");
            String crops_id = rs.getString("crops_id"); 
            //데이터 객체 생성
            HandyFarmDTO data = new HandyFarmDTO();
            data.setRobo_nickname(robo_nickname);
            data.setRobo_serial(robo_serial);
            data.setCrops_id(crops_id);
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
      return robo_list;
   }
   //robo_select 정민정 end   
         
   //robo_search 정민정
   public ArrayList<HandyFarmDTO> robo_search_list(String robo_serial) {
      
      ArrayList<HandyFarmDTO> robo_search_list = new ArrayList<HandyFarmDTO> ();
      
      try {
         con=ds.getConnection();
         String query="SELECT * FROM robo AS r JOIN crops AS c ON r.crops_id = c.crops_id WHERE robo_serial = ? ";
         pstmt = con.prepareStatement(query);
         pstmt.setString(1, robo_serial);
         rs = pstmt.executeQuery();
         
         while (rs.next()) {
            String robo_nickname = rs.getString("robo_nickname");
            String robo_img = rs.getString("robo_img");
            String crops_name = rs.getString("crops_name");
            String crops_id = rs.getString("crops_id");
            
            HandyFarmDTO data = new HandyFarmDTO();
            data.setRobo_nickname(robo_nickname);
            data.setRobo_img(robo_img);
            data.setCrops_name(crops_name);
            data.setCrops_id(crops_id);
            
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
   //robo_search 정민정 end
      
   //robo_Update 정민정
   public void robo_update(String robo_serial, String robo_nickname, String robo_img, String crops_id) {
      
      try {
         con = ds.getConnection();
         String query = "UPDATE robo SET robo_nickname = ? , robo_img = ?, crops_id = ? where robo_serial = ?";
         pstmt = con.prepareStatement(query);
         pstmt.setString(1, robo_nickname);
         pstmt.setString(2, robo_img);
         pstmt.setString(3, crops_id);
         pstmt.setString(4, robo_serial);
         pstmt.executeUpdate();
         
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
   }//robo_Update 정민정 end
      
   //robo_delete 정민정
   
   public void robo_delete(String robo_serial) {
      try {
         con = ds.getConnection();
         String query = "DELETE FROM robo WHERE robo_serial = ?";
         pstmt = con.prepareStatement(query);
         pstmt.setString(1, robo_serial);
         pstmt.executeUpdate();
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
      
   //robo_delete 정민정 end
      
   //push_log 정민정
   public ArrayList<HandyFarmDTO> push_list(String id){
      ArrayList<HandyFarmDTO> crops_list = new ArrayList<HandyFarmDTO> ();
      ResultSet rs2 = null;
      
      try {
         con = ds.getConnection();
               
         String query = "SELECT * FROM push_log where id = ? ORDER BY push_date DESC";
         pstmt = con.prepareStatement(query);
         pstmt.setString(1, id);
         rs = pstmt.executeQuery();
         
         while(rs.next()) {
            String push_category = rs.getString("push_category");
            String push_msg = rs.getString("push_msg");
            Timestamp push_date = rs.getTimestamp("push_date");
            boolean read_status = rs.getBoolean("read_status");
            String crops_id = rs.getString("crops_id");
            String gh_id = rs.getString("gh_id");
            //데이터 객체 생성0
            HandyFarmDTO data = new HandyFarmDTO();
                  
            data.setPush_category(push_category);
            data.setPush_msg(push_msg);
            data.setPush_date(push_date);
            data.setRead_status(read_status);
            data.setCrops_id(crops_id);
            data.setGh_id(gh_id);

            try{
               String query1 = "SELECT gh_nickname FROM greenhouse where gh_id = ?";
               pstmt = con.prepareStatement(query1);
               pstmt.setString(1, gh_id);
               rs2 = pstmt.executeQuery();
                     
               if(rs2.next()) {
                  String gh_nickname = rs2.getString("gh_nickname");
                  data.setGh_nickname(gh_nickname);
               }
                     
            }catch(Exception e) {
               e.printStackTrace();
            }
                  
            try{
               String query1 = "SELECT crops_name FROM crops where crops_id = ?";
               pstmt = con.prepareStatement(query1);
               pstmt.setString(1, crops_id);
               rs2 = pstmt.executeQuery();
                     
               if(rs2.next()) {
                  String crops_name = rs2.getString("crops_name");
                  data.setCrops_name(crops_name);
               }
                     
            }catch(Exception e) {
               e.printStackTrace();
            }
                  
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
            crops_list.add(data);
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
      return crops_list;
   }
   //push_log 정민정 end
   
   //gh_insert 정민정
   public String gh_insert(String gh_img, String gh_nickname, String id) {
	   // 온실 id 생성
       Timestamp time = new Timestamp(System.currentTimeMillis());
       SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HHmmss");
       String d = sdf.format(time);
       String[] date = d.split(" ");
       String gh_id = "gh-" + id + "-" + date[0] + "-" + date[1];
       
       try {         
    	   con = ds.getConnection();
    	   
    	   String query="INSERT INTO greenhouse (gh_id, gh_img, gh_nickname, id)" +
    		            "VALUES (?,?,?,?)";
    		
    	   pstmt=con.prepareStatement(query);
    	   pstmt.setString(1, gh_id);
    	   pstmt.setString(2, gh_img);
    	   pstmt.setString(3, gh_nickname);
    	   pstmt.setString(4, id);
    	   pstmt.executeUpdate();
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
      // gh_id 리턴
      return gh_id;
   }//gh_insert 정민정 end
   
   //gh_id 가져오기 정민정
   
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
   }//gh_id 가져오기 정민정 end
   
   
   //gh_in_list 정민정
   public ArrayList<HandyFarmDTO> gh_in_list(String gh_id) {
      
      ArrayList<HandyFarmDTO> robo_search_list = new ArrayList<HandyFarmDTO> ();
      
      try {
         con=ds.getConnection();
         String query="SELECT * FROM greenhouse WHERE gh_id = ? ";
         pstmt = con.prepareStatement(query);
         pstmt.setString(1, gh_id);
         rs = pstmt.executeQuery();
         
         while (rs.next()) {
            String gh_nickname = rs.getString("gh_nickname");
            String gh_img = rs.getString("gh_img");
            String id = rs.getString("id");
            
            HandyFarmDTO data = new HandyFarmDTO();
            data.setGh_id(gh_id);
            if (gh_img == null) {
            	data.setGh_img("../icon/camera.png");
            } else {
            	data.setGh_img(gh_img);
            }
            data.setGh_nickname(gh_nickname);
            data.setId(id);
            
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
   }//gh_in_list 정민정 end


   //gh_update 정민정

   public void gh_update(String gh_id, String gh_nickname, String gh_img) {
   
      try {
         con = ds.getConnection();
         String query = "UPDATE greenhouse SET gh_img = ?, gh_nickname = ? where gh_id = ?";
         pstmt = con.prepareStatement(query);
         pstmt.setString(1, gh_img);
         pstmt.setString(2, gh_nickname);
         pstmt.setString(3, gh_id);
         pstmt.executeUpdate();
         
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
   }

   //gh_update 정민정 end
   
   
   //gh_delete 정민정
   
   public void gh_delete(String gh_id) {
      try {
         con = ds.getConnection();
         String query = "DELETE FROM greenhouse WHERE gh_id = ?";
         pstmt = con.prepareStatement(query);
         pstmt.setString(1, gh_id);
         pstmt.executeUpdate();
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
      
   //gh_delete 정민정end
   
   // tip_list 정민정
   public ArrayList<HandyFarmDTO> tip_list(String crops_id) {
	      
	      ArrayList<HandyFarmDTO> tip_list = new ArrayList<HandyFarmDTO> ();
	      
	      try {
	         con=ds.getConnection();
	         String query="SELECT *"
	         		+ " FROM crops"
	         		+ " WHERE crops_id = ? ";
	         pstmt = con.prepareStatement(query);
	         pstmt.setString(1, crops_id);
	         rs = pstmt.executeQuery();
	         
	         while (rs.next()) {
	            float sum_mrn_min_temperature = rs.getFloat("sum_mrn_min_temperature");
	            float sum_mrn_max_temperature = rs.getFloat("sum_mrn_max_temperature");
	            float sum_aft_min_temperature = rs.getFloat("sum_aft_min_temperature");
	            float sum_aft_max_temperature = rs.getFloat("sum_aft_max_temperature");
	            float sum_ngh_min_temperature = rs.getFloat("sum_ngh_min_temperature");
	            float sum_ngh_max_temperature = rs.getFloat("sum_ngh_max_temperature");
	            float win_day_min_temperature = rs.getFloat("win_day_min_temperature");
	            float win_day_max_temperature = rs.getFloat("win_day_max_temperature");
	            float win_ngh_min_temperature = rs.getFloat("win_ngh_min_temperature");
	            float win_ngh_max_temperature = rs.getFloat("win_ngh_max_temperature");
	            float min_humidity = rs.getFloat("min_humidity");
	            float max_humidity = rs.getFloat("max_humidity");
	            float min_co2 = rs.getFloat("min_co2");
	            float max_co2 = rs.getFloat("max_co2");
	            float min_soil_moisture = rs.getFloat("min_soil_moisture");
	            float max_soil_moisture = rs.getFloat("max_soil_moisture");
	            float min_sunshine = rs.getFloat("min_sunshine");
	            float max_sunshine = rs.getFloat("max_sunshine");
	            float min_soil_temperature = rs.getFloat("min_soil_temperature");
	            float max_soil_temperature = rs.getFloat("max_soil_temperature");
	            String crops_name = rs.getString("crops_name");
	            String crops_img = rs.getString("crops_img");
	            
	            HandyFarmDTO data = new HandyFarmDTO();
	            data.setSum_mrn_min_temperature(sum_mrn_min_temperature);
	            data.setSum_mrn_max_temperature(sum_mrn_max_temperature);
	            data.setSum_aft_min_temperature(sum_aft_min_temperature);
	            data.setSum_aft_max_temperature(sum_aft_max_temperature);
	            data.setSum_ngh_min_temperature(sum_ngh_min_temperature);
	            data.setSum_ngh_max_temperature(sum_ngh_max_temperature);
	            data.setWin_day_min_temperature(win_day_min_temperature);
	            data.setWin_day_max_temperature(win_day_max_temperature);
	            data.setWin_ngh_min_temperature(win_ngh_min_temperature);
	            data.setWin_ngh_max_temperature(win_ngh_max_temperature);
	            data.setMin_humidity(min_humidity);
	            data.setMax_humidity(max_humidity);
	            data.setMin_co2(min_co2);
	            data.setMax_co2(max_co2);
	            data.setMin_soil_moisture(min_soil_moisture);
	            data.setMax_soil_moisture(max_soil_moisture);
	            data.setMin_sunshine(min_sunshine);
	            data.setMax_sunshine(max_sunshine);
	            data.setMin_soil_temperature(min_soil_temperature);
	            data.setMax_soil_temperature(max_soil_temperature);
	            data.setCrops_name(crops_name);
	            data.setCrops_img(crops_img);
	            
	            //리스트 값 추가
	            tip_list.add(data);
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
	      return tip_list;
	   }
       // tip_list 정민정 end
   
   
   	  // tip_search 정민정
      public ArrayList<HandyFarmDTO> tip_search(String id, String searchword) {
	   
		ArrayList<HandyFarmDTO> tip_search_list = new ArrayList<HandyFarmDTO>();
		try {
			String a = "%"+searchword+"%";
			con = ds.getConnection();
			//검색어 비교
			String query = "SELECT * "
					+ "FROM crops AS c JOIN wish AS w ON c.crops_id =w.crops_id"
					+ " WHERE crops_name LiKE ? AND id=?";
			
			pstmt = con.prepareStatement(query);

			pstmt.setString(1, a);
			pstmt.setString(2, id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String crops_name = rs.getString("crops_name");
				String crops_img = rs.getString("crops_img");
				String crops_id = rs.getString("crops_id");
				String id2=id;
				Boolean wish = rs.getBoolean("wish");
					
				//data 선언
				HandyFarmDTO data = new HandyFarmDTO();
				
				// data 값 설정
				data.setCrops_name(crops_name);
				data.setCrops_img(crops_img);
				data.setCrops_id(crops_id);
				data.setId(id2);
				data.setWish(wish);
				
				
				if (wish == true) { // 찜하기 선택이 되었다면
					data.setImg("../icon/like_heart.png");
				} else { // 찜하기 선택이 안되어있다면
					data.setImg("../icon/unlike_heart.png");
				}
				
				
				tip_search_list.add(data);
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
		} 
		
		return tip_search_list;
	}
      // tip_search 정민정 end
   
   // wish_update 정민정
   public ArrayList<HandyFarmDTO> wish_update(String crops_id, String wish) {
	   ArrayList<HandyFarmDTO> list = new ArrayList<HandyFarmDTO>();
	   
	   String wish2 = null;
	   
	   HandyFarmDTO data = new HandyFarmDTO();
	   if(wish.equals("0")) {
		   data.setWish(true);
		   wish2 = "1";
	   }else {
		   data.setWish(false);
		   wish2 = "0";
	   }
	   list.add(data);
	   
	   try {
		   con = ds.getConnection();
	       String query = "UPDATE wish SET wish = ? where crops_id = ?";
	       pstmt = con.prepareStatement(query);
	       pstmt.setString(1, wish2);
	       pstmt.setString(2, crops_id);
	       pstmt.executeUpdate();
	       
	   }
	   catch (Exception e) {
		   e.printStackTrace();
	   }
	   finally {
		   try {
			   if(pstmt != null)
	               pstmt.close();
			   if(con != null)
				   con.close();
		   } catch (SQLException e) {
			   e.printStackTrace();
			}
	   }
	   return list;
   }
   //wish_update 정민정 end
   
   //wish_list 정민정
   public ArrayList<HandyFarmDTO> wish_list(String id) {
	   
		ArrayList<HandyFarmDTO> wish_list = new ArrayList<HandyFarmDTO>();
		try {
			con = ds.getConnection();
			//검색어 비교
			String query = "SELECT * "
					+ "FROM crops AS c JOIN wish AS w ON c.crops_id =w.crops_id"
					+ " WHERE wish = ? AND id=?";
			
			pstmt = con.prepareStatement(query);

			pstmt.setBoolean(1, true);
			pstmt.setString(2, id);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String crops_name = rs.getString("crops_name");
				String crops_img = rs.getString("crops_img");
				String crops_id = rs.getString("crops_id");
				Boolean wish = rs.getBoolean("wish");
					
				//data 선언
				HandyFarmDTO data = new HandyFarmDTO();
				
				// data 값 설정
				data.setCrops_name(crops_name);
				data.setCrops_img(crops_img);
				data.setCrops_id(crops_id);
				data.setImg("../icon/like_heart.png");
				
				wish_list.add(data);
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
		} 
		
		return wish_list;
	}
   //wish_list 정민정 end
   
   /**
    * @author 임예나
    * email : yenaim0108@gmail.com
    */

	// 수확 가능 비율 DB에 저장하기 임예나
	public void insertHarvestable(Timestamp _time, String _serial, float _harvestable) {
		// datas, robo 선언
		String[] datas = new String[3];
		
		// time 변수에서 년월일, 시분초 나눠서 가져오기
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HHmmss");
		String d = sdf.format(_time);
		String[] date = d.split(" ");
		
		try {
			con = ds.getConnection();
			
			// 로보가 속한 품종번호, 온실 ID, 휴대폰 번호 가져오는 sql문
			String query = "SELECT crops_id, gh_id, id " + 
						   "FROM robo " + 
						   "WHERE robo_serial = ?";
			
			pstmt = con.prepareStatement(query);
			// 매개변수 값 대입 -> set 메서드에 값 설정
			pstmt.setString(1, _serial);
			// sql문 실행
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				// datas 배열의 값 설정
				datas[0] = rs.getString("crops_id");
				datas[1] = rs.getString("gh_id");
				datas[2] = rs.getString("id");
			}
			
			// harvestable 테이블에 데이터를 넣는 sql문
			query = "INSERT INTO harvestable (hrv_id, harvestable, upload_time, robo_serial, crops_id, gh_id, id) " + 
					"VALUES (?, ?, ?, ?, ?, ?, ?)";
			
			pstmt = con.prepareStatement(query);
			// 매개변수 값 대입 -> set 메서드에 값 설정
			pstmt.setString(1, "hrv-" + _serial + "-" + date[0] + "-" + date[1]);
			pstmt.setFloat(2, _harvestable);
			pstmt.setTimestamp(3, _time);
			pstmt.setString(4, _serial);
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
	} // end insertHarvestable 임예나
	
	// 센서값 DB에 저장하기 임예나
	public void insertSensorValue(Timestamp _time, String _serial, String _sensor_type, float _sensor_value) {
		// datas, sensor, count 선언
		String[] datas = new String[4];
		String sensor = null;
		int count = 0;

		try {
			con = ds.getConnection();
			
			// sensor_id를 가져오는 sql문
			String query = "SELECT sensor_id " + 
						   "FROM sensor " + 
						   "WHERE robo_serial = ? AND sensor_type = ?";
		
			pstmt = con.prepareStatement(query);
			// 매개변수 값 대입 -> set 메서드에 값 설정
			pstmt.setString(1, _serial);
			pstmt.setString(2, _sensor_type);
			// sql문 실행
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				// datas 배열의 값 설정
				datas[0] = rs.getString("sensor_id");
				
				// 센서 ID 뒷 3자리 가져오기
				sensor = datas[0].substring(datas[0].length() - 3, datas[0].length());
			}
						
			// 로보가 속한 품종번호, 온실 ID, 휴대폰 번호 가져오는 sql문
			query = "SELECT crops_id, gh_id, id " + 
					"FROM robo " + 
					"WHERE robo_serial = ?";
			
			pstmt = con.prepareStatement(query);
			// 매개변수 값 대입 -> set 메서드에 값 설정
			pstmt.setString(1, _serial);
			// sql문 실행
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				// datas 배열의 값 설정
				datas[1] = rs.getString("crops_id");
				datas[2] = rs.getString("gh_id");
				datas[3] = rs.getString("id");				
			}
			
			// sensor_value 갯수를 가져오는 sql문
			query = "SELECT COUNT(*) " + 
					"FROM sensor_value " + 
					"WHERE sensor_id = ?";
		
			pstmt = con.prepareStatement(query);
			// 매개변수 값 대입 -> set 메서드에 값 설정
			pstmt.setString(1, datas[0]);
			// sql문 실행
			rs = pstmt.executeQuery();
			
			if (rs.next()) { // 행이 있으면 갯수 + 1 가져오기
				count = rs.getInt(1) + 1;
			} else { // 행이 없으면 1 지정하기
				count = 1;
			}
			
			// sensor_value를 테이블에 데이터를 넣는 sql문
			query = "INSERT INTO sensor_value (value_id, sensor_value, measure_time, sensor_id, robo_serial, crops_id, gh_id, id) " + 
					"VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
			
			pstmt = con.prepareStatement(query);
			// 매개변수 값 대입 -> set 메서드에 값 설정
			pstmt.setString(1, "sv-" + sensor + "-" + count);
			pstmt.setFloat(2, _sensor_value);
			pstmt.setTimestamp(3, _time);
			pstmt.setString(4, datas[0]);
			pstmt.setString(5, _serial);
			pstmt.setString(6, datas[1]);
			pstmt.setString(7, datas[2]);
			pstmt.setString(8, datas[3]);
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
	} // end insertSensorValue 임예나
	
	// 설비를 제어할 센서값 가져오기 임예나
	public String equipmentSensorValueSelect(String _serial) {
		// sensor_type, content, crop 선언
		String[] sensor_type = {"temperature", "humidity", "co2", "soil-moisture", "sunshine"};
		String content = "";
		String crops_id = null;
		
		try {
			con = ds.getConnection();
			
			for (String st : sensor_type) {
				// 최근 센서값을 가져오는 sql문
				String query = "SELECT sv.sensor_value " + 
							   "FROM sensor_value AS sv " + 
							   "JOIN sensor AS s " + 
							   "ON sv.sensor_id = s.sensor_id " + 
							   "WHERE sv.robo_serial = ? AND s.sensor_type = ? " + 
							   "ORDER BY sv.measure_time DESC";
				pstmt = con.prepareStatement(query);
				// 매개변수 값 대입 -> set 메서드에 값 설정
				pstmt.setString(1, _serial);
				pstmt.setString(2, st);
				// sql문 실행
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
					// content에 추가
					content += (int)rs.getFloat("sensor_value") + ", ";
				} else { // 센서 값이 없으면 0으로 채우기
					// content에 추가
					content += "0, ";
				}
			}
			content += " / ";
			
			// 품종 번호를 가져오는 query문
			String query = "SELECT crops_id " + 
						   "FROM robo " + 
						   "WHERE robo_serial = ? ";
			pstmt = con.prepareStatement(query);
			// 매개변수 값 대입 -> set 메서드에 값 설정
			pstmt.setString(1, _serial);
			// sql문 실행
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				// crop에 대입하기
				crops_id = rs.getString("crops_id");
			}
			
			// temperature, time 선언
			String[] temperature = new String[2];
			Timestamp time = new Timestamp(System.currentTimeMillis()); // 현재 시간 가져오기
			
			// time 변수에서 월, 시만 가져오기
			SimpleDateFormat sdf = new SimpleDateFormat("MM HH");
			String tmp = sdf.format(time);
			
			String[] mh = tmp.split(" "); // 월, 시 나누기
			String weather = null;
			
			// 월에 따라 계절 넣기
			switch (mh[0]) {
				case "05" :
				case "06" :
				case "07" :
				case "08" :
				case "09" :
					temperature[0] = "sum_";
					temperature[1] = "sum_";
					weather = "sum";
					break;
				case "01" :
				case "02" :
				case "03" :
				case "04" :
				case "10" :
				case "11" :
				case "12" :
					temperature[0] = "win_";
					temperature[1] = "win_";
					weather = "win";
			} // end switch
			
			// 시간에 따라 오전, 오후, 야간, 주간, 야간 설정하기
			if (weather.equals("sum")) { // 여름일 경우
				switch (mh[1]) {
					case "05" :
					case "06" :
					case "07" :
					case "08" :
					case "09" :
					case "10" :
					case "11" :
						temperature[0] += "mrn_";
						temperature[1] += "mrn_";
						break;
					case "12" :
					case "13" :
					case "14" :
					case "15" :
					case "16" :
					case "17" :
						temperature[0] += "aft_";
						temperature[1] += "aft_";
						break;
					case "18" :
					case "19" :
					case "20" :
					case "21" :
					case "22" :
					case "23" :
					case "24" :
					case "01" :
					case "02" :
					case "03" :
					case "04" :
						temperature[0] += "ngh_";
						temperature[1] += "ngh_";
				} // end switch
			} else { // 겨울일 경우
				switch (mh[1]) {
					case "06" :
					case "07" :
					case "08" :
					case "09" :
					case "10" :
					case "11" :
					case "12" :
					case "13" :
					case "14" :
					case "15" :
					case "16" :
					case "17" :
					case "18" :
						temperature[0] += "day_";
						temperature[1] += "day_";
						break;
					case "19" :
					case "20" :
					case "21" :
					case "22" :
					case "23" :
					case "00" :
					case "01" :
					case "02" :
					case "03" :
					case "04" :
					case "05" :
						temperature[0] += "ngh_";
						temperature[1] += "ngh_";
				} // end switch
			}
			
			temperature[0] += "min_temperature";
			temperature[1] += "max_temperature";
			
			// 비교 조건을 가져오는 sql문
			query = "SELECT " + temperature[0] + ", " + temperature[1] + ", " + "min_humidity, max_humidity, max_co2, min_soil_moisture, max_soil_moisture, min_sunshine, max_sunshine " + 
					"FROM crops " + 
					"WHERE crops_id = ?";
			pstmt = con.prepareStatement(query);
			// 매개변수 값 대입 -> set 메서드에 값 설정
			pstmt.setString(1, crops_id);
			// sql문 실행
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				// content에 추가
				content = content + 
						  (int)rs.getFloat(temperature[0]) + ", " +
						  (int)rs.getFloat(temperature[1]) + ", " +
						  (int)rs.getFloat("min_humidity") + ", " +
						  (int)rs.getFloat("max_humidity") + ", " +
						  (int)rs.getFloat("max_co2") + ", " +
						  (int)rs.getFloat("min_soil_moisture") + ", " +
						  (int)rs.getFloat("max_soil_moisture") + ", " +
						  (int)rs.getFloat("min_sunshine") + ", " +
						  (int)rs.getFloat("max_sunshine");
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
		// 로보한테 보낼 메시지 return
		return content;
	} // end equipmentSensorValueSelect 임예나
	
	// 설비 상태를 DB에 저장하기 임예나
	public void equipmentStatusInsert (Timestamp _time, String _serial, String _equipment_name, boolean _control_status) {
		// gh_id, gh, _id, equipment_id, ei, count 선언
		String _gh_id = null;
		String[] gh = null;
		String _id = null;
		String _equipment_id = null;
		String[] ei = null;
		int count = 0;
		System.out.println(_control_status);
		
		try {
			con = ds.getConnection();
			// 온실 id, 핸드폰 번호를 가져오는 sql문
			String query = "SELECT gh_id, id " + 
						   "FROM robo " + 
						   "WHERE robo_serial = ?";
			pstmt = con.prepareStatement(query);
			// 매개변수 값 대입 -> set 메서드에 값 설정
			pstmt.setString(1, _serial);
			// sql문 실행
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				// gh_id, phone_number 대입
				_gh_id = rs.getString("gh_id");
				_id = rs.getString("id");
			}
			
			// 설비 ID를 가져오는 sql문 
			query = "SELECT equipment_id " + 
					"FROM equipment " + 
					"WHERE gh_id = ? AND equipment_name = ?";
			pstmt = con.prepareStatement(query);
			// 매개변수 값 대입 -> set 메서드에 값 설정
			pstmt.setString(1, _gh_id);
			pstmt.setString(2, _equipment_name);
			// sql문 실행
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				// equipment_id 대입
				_equipment_id = rs.getString("equipment_id");
			}
			
			gh = _gh_id.split("-");
			ei = _equipment_id.split("-");
			
			// sensor_value 갯수를 가져오는 sql문
			query = "SELECT COUNT(*) " + 
					"FROM equipment_control_log " + 
					"WHERE equipment_id = ?";
		
			pstmt = con.prepareStatement(query);
			// 매개변수 값 대입 -> set 메서드에 값 설정
			pstmt.setString(1, _equipment_id);
			// sql문 실행
			rs = pstmt.executeQuery();
			
			if (rs.next()) { // 행이 있으면 갯수 + 1 가져오기
				count = rs.getInt(1) + 1;
			} else { // 행이 없으면 1 지정하기
				count = 1;
			}
			
			query = "INSERT INTO equipment_control_log (control_log_id, control_status, control_time, equipment_id, gh_id, id) " + 
					"VALUES (?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(query);
			// 매개변수 값 대입 -> set 메서드에 값 설정
			pstmt.setString(1, "ecl-" + _id + "-" + gh[2] + "-" + gh[3] + "-" + ei[4] + "-" + count);
			pstmt.setBoolean(2, _control_status);
			pstmt.setTimestamp(3, _time);
			pstmt.setString(4, _equipment_id);
			pstmt.setString(5, _gh_id);
			pstmt.setString(6, _id);
			// sql문 적용
			pstmt.executeUpdate();
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
	} // end equipmentStatusInsert 임예나
	
	// id 중복 확인하기 임예나
	public ArrayList<HandyFarmDTO> idCheck(String _id) {
		// isOverlap 선언
		ArrayList<HandyFarmDTO> list = new ArrayList<HandyFarmDTO>();
		
		try {
			con = ds.getConnection();
			
			// 회원 테이블에 해당 id가 있는지 찾는 sql문
			String query = "SELECT * " + 
					"FROM member " + 
					"WHERE id = ?";
			pstmt = con.prepareStatement(query);
			// 매개변수 값 대입 -> set 메서드에 값 설정
			pstmt.setString(1, _id);
			// sql문 실행
			rs = pstmt.executeQuery();
			
			HandyFarmDTO data = new HandyFarmDTO();
			
			if (rs.next()) { // 결과가 있으면 true
				data.setIdCheck(true);
			} else { // 결과가 없으면 false
				data.setIdCheck(false);
			}
			// list에  추가
			list.add(data);
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
		// 결과값 isOverlap 리턴
		return list;
	} // end idCheck 임예나
	
	// 비밀번호가 일치하는지 확인하기 임예나
	public ArrayList<HandyFarmDTO> passwordCheck(String _id, String _password) {
		// isOverlap 선언
		ArrayList<HandyFarmDTO> list = new ArrayList<HandyFarmDTO>();
		
		try {
			con = ds.getConnection();
			
			// 회원 테이블에서 해당 id에 대한 비밀번호를 가져오는 sql문
			String query = "SELECT password " + 
					"FROM member " + 
					"WHERE id = ?";
			pstmt = con.prepareStatement(query);
			// 매개변수 값 대입 -> set 메서드에 값 설정
			pstmt.setString(1, _id);
			// sql문 실행
			rs = pstmt.executeQuery();
			
			HandyFarmDTO data = new HandyFarmDTO();
			
			if (rs.next()) { // 비밀번호가 맞는지 체크
				String tmp = rs.getString("password");
				
				if (tmp.equals(_password)) { // 비밀번호가 일치하면 true
					data.setPasswordCheck(true);
				} else { // 비밀번호가 일치하지 않으면 false
					data.setPasswordCheck(false);
				}
			}
			// list에 추가
			list.add(data);
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
		// 결과값 isOverlap 리턴
		return list;
	} // end passwordCheck 임예나
	
	// 회원 테이블에 id, password 저장하기 임예나
	public boolean signup(String _id, String _password) {
		// result 선언
		boolean result = false;
		
		try {
			con = ds.getConnection();
			
			// 회원 가입 테이블에 id, password 저장하는 sql문
			String query = "INSERT INTO MEMBER (id, password, token, latitude, longitude) " + 
						   "VALUES (?, ?, ?, ?, ?);";
			pstmt = con.prepareStatement(query);
			// 매개변수 값 대입 -> set 메서드에 값 설정
			pstmt.setString(1, _id);
			pstmt.setString(2, _password);
			pstmt.setString(3, "-");
			pstmt.setString(4, "");
			pstmt.setString(5, "");
			// sql문 적용
			int n = pstmt.executeUpdate();
			
			if (n > 0) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (con != null) { con.close(); }
				if (pstmt != null) { pstmt.close(); }
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} // end finally
		// DB 저장 성공 여부 결과값 result 리턴
		return result;
	} // end signup 임예나
	
	// 회원가입 할 때 모든 농작물에 대한 찜 상태 0으로 insert 해주기 임예나
	public boolean signupWishDataInsert(String _id) {
		// datas, result 선언
		ArrayList<String> datas = new ArrayList<String>();
		boolean result = false;
		
		try {
			con = ds.getConnection();
			
			// crops 테이블에 모든 crops_id를 가져오는 sql문
			String query = "SELECT crops_id " + 
						   "FROM crops";
			pstmt = con.prepareStatement(query);
			// sql문 실행
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				datas.add(rs.getString("crops_id"));
			}
			
			for (String d : datas) {
				try {
					// wish 테이블에 농작물 찜 상태를 0으로 저장하는 sql
					query = "INSERT INTO wish (wish, id, crops_id) " + 
							"VALUE ('0', ?, ?)";
					pstmt = con.prepareStatement(query);
					// 매개변수 값 대입 -> set 메서드에 값 설정
					pstmt.setString(1, _id);
					pstmt.setString(2, d);
					// sql문 적용
					int n = pstmt.executeUpdate();
					
					if (n > 0) {
						result = true;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
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
		// DB 저장 성공 여부 결과값 result 리턴
		return result;
	} // end signupWishDataInsert 임예나
	
	// 온실 목록 가져오기 임예나
	public ArrayList<HandyFarmDTO> GHSelect(String _id) {
		// list 선언
		ArrayList<HandyFarmDTO> list = new ArrayList<HandyFarmDTO>();
		
		try {
			// DB 연결
			con = ds.getConnection();
			
			// 온실 목록을 가져오는 sql문
			String query = "SELECT * " + 
						   "FROM greenhouse " + 
						   "WHERE id = ?";
			pstmt = con.prepareStatement(query);
			// 매개변수 값 대입 -> set 메서드에 값 설정
			pstmt.setString(1, _id);
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
	} // end GHSelect 임예나

	// 로보 목록 가져오기 임예나
	public ArrayList<HandyFarmDTO> roboSelect(String _gh_id) {
		// list 선언
		ArrayList<HandyFarmDTO> list = new ArrayList<HandyFarmDTO>();
		
		try {
			// DB 연결
			con = ds.getConnection();
			
			// 로보 목록을 가져오는 sql문
			String query = "SELECT * " + 
						   "FROM robo AS r " + 
						   "JOIN crops AS c " + 
						   "ON r.crops_id = c.crops_id " + 
						   "WHERE gh_id = ?";
			pstmt = con.prepareStatement(query);
			// 매개변수 값 대입 -> set 메서드에 값 설정
			pstmt.setString(1, _gh_id);
			// sql문 실행
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// 레코드의 정보를 각 변수에 저장
				String robo_serial = rs.getString("robo_serial");
				String robo_img = rs.getString("robo_img");
				String robo_nickname = rs.getString("robo_nickname");
				String crops_name = rs.getString("crops_name");
				
				// data 객체 선언
				HandyFarmDTO data = new HandyFarmDTO();
				
				// data 객체의 set 메서드에 해당하는 값을 설정
				data.setRobo_serial(robo_serial);
				data.setRobo_nickname(robo_nickname);
				data.setCrops_name(crops_name);
				if (robo_img != null) { // DB에 저장된 로보 사진 가져오기
					data.setRobo_img(robo_img);
				} else { // DB에 저장된 사진이 없으면 HandyFarm Logo 사진 가져오기
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
		// 조회 결과 list 리턴
		return list;
	} // end RoboSelect 임예나
	
	// 온실 이름 가져오기 임예나
	public String getGHNickname(String _gh_id) {
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
	} // end getGHName 임예나
	
	// 생장 정보 가져오기 임예나
	public  ArrayList<HandyFarmDTO> growth(String _gh_id, String _crops_id) {
		// sensor, list 선언
		ArrayList<String> sensor = new ArrayList<String>();
		ArrayList<HandyFarmDTO> list = new ArrayList<HandyFarmDTO>();
		
		try {
			// DB 연결
			con = ds.getConnection();
			
			// 해당 작물 in 온실에 있는 센서 종류 조회
			String query = "SELECT sensor_type " + 
						   "FROM sensor " + 
						   "WHERE gh_id = ? AND crops_id = ?";
			
			pstmt = con.prepareStatement(query);
			// 매개변수 값 대입 -> set 메서드에 값 설정
			pstmt.setString(1, _gh_id);
			pstmt.setString(2, _crops_id);
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
						"WHERE sv.gh_id = ? AND sv.crops_id = ? AND s.sensor_type = ? " +
						"ORDER BY sv.measure_time DESC";
				
				pstmt = con.prepareStatement(query);
				// 매개변수 값 대입 -> set 메서드에 값 설정
				pstmt.setString(1, _gh_id);
				pstmt.setString(2, _crops_id);
				pstmt.setString(3, _sensor_type);
				// sql문 실행
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
					// 레코드의 정보를 각 변수에 저장
					float sensor_value = rs.getFloat("sensor_value");
					int tmp = (int) sensor_value;
					
					// data 객체 선언
					HandyFarmDTO data = new HandyFarmDTO();
					
					// data 객체의 set 메서드에 해당하는 값을 설정
					data.setSensor_type(_sensor_type);
					
					if (_sensor_type.equals("soil-moisture")) {
						if (tmp > 2600) {
							data.setStr_sensor_value("Dry");
						} else if (tmp > 2200) {
							data.setStr_sensor_value("Wet");
						} else {
							data.setStr_sensor_value("Very\nWet");
						}
					} else {
						data.setStr_sensor_value(String.valueOf(tmp));
					}

					switch (_sensor_type) {
						case "temperature" :
							data.setSensor_name("온도");
							data.setSensor_unit("℃");
							break;
						case "humidity" :
							data.setSensor_name("습도");
							data.setSensor_unit("%");
							break;
						case "co2" :
							data.setSensor_name("이산화탄소");
							data.setSensor_unit("단계");
							break;
						case "soil-moisture" :
							data.setSensor_name("토양 수분도");
							break;
						case "soil-temperature" :
							data.setSensor_name("토양 온도");
							data.setSensor_unit("℃");
							break;
						case "sunshine" :
							data.setSensor_name("일조량");
							data.setSensor_unit("lx");
					}

					list.add(data);
				} // end switch
			} // end for
			
			// 해당 온실 in 작물에 대한 수확 가능 비율 정보 조회 sql문
			query = "SELECT harvestable " + 
					"FROM harvestable " + 
					"WHERE gh_id = ? AND crops_id = ? " + 
					"ORDER BY upload_time DESC";
			
			pstmt = con.prepareStatement(query);
			// 매개변수 값 대입 -> set 메서드에 값 설정
			pstmt.setString(1, _gh_id);
			pstmt.setString(2, _crops_id);
			// sql문 실행
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				// 레코드의 정보를 각 변수에 저장
				float harvestable = rs.getFloat("harvestable");
				int tmp = (int) harvestable;
				
				// data 객체 선언
				HandyFarmDTO data = new HandyFarmDTO();
				
				// data 객체의 set 메서드에 해당하는 값을 설정
				data.setSensor_type("harvestable");
				data.setStr_sensor_value(String.valueOf(tmp));
				data.setSensor_name("수확 가능 비율");
				data.setSensor_unit("%");
				
				list.add(data);
			} else {
				// data 객체 선언
				HandyFarmDTO data = new HandyFarmDTO();
				
				// data 객체의 set 메서드에 해당하는 값을 설정
				data.setSensor_type("harvestable");
				data.setStr_sensor_value("0");
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
	} // end growth 임예나
	
	// 일정 조회 임예나
	public ArrayList<HandyFarmDTO> calendarAllSelect(String _id, Date _date) {
		// list, _cal_start_date 선언
		ArrayList<HandyFarmDTO> list = new ArrayList<HandyFarmDTO>();
		String[] colorList = { "c-0", "c-1", "c-2", "c-3" };
		int i = 0;
		
		try {
			con = ds.getConnection();
			
			String query = "SELECT cal_title, cal_start_time, cal_end_time " + 
						   "FROM calendar " + 
						   "WHERE id = ? AND cal_start_date = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, _id);
			pstmt.setDate(2, _date);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String start = rs.getTime("cal_start_time").toString();
				String end = rs.getTime("cal_end_time").toString();
				
				HandyFarmDTO data = new HandyFarmDTO(); 
				
				if (i == 4) {
					i = 0;
				}
				
				data.setCal_title(rs.getString("cal_title"));
				data.setCal_color(colorList[i]);
				if ((start.equals("00:00:00")) && (end.equals("23:59:00"))) {
					data.setCal_time("하루종일");
				} else {
					data.setCal_time(rs.getTime("cal_start_time").toString() + " ~");
				}
				
				list.add(data);
				i++;
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
		// 조회 결과값 리턴
		return list;
	} // end calendarAllSelect 임예나
	
	// 일정 보기 임예나
	public ArrayList<HandyFarmDTO> calendarSelect(String _cal_number) {
		// list, _cal_start_date 선언
		ArrayList<HandyFarmDTO> list = new ArrayList<HandyFarmDTO>();
		
		try {
			con = ds.getConnection();
			
			String query = "SELECT * " + 
						   "FROM calendar " + 
						   "WHERE cal_number = ?";
			pstmt = con.prepareStatement(query);
			// 매개변수 값 대입 -> set 메서드에 값 설정
			pstmt.setString(1, _cal_number);
			// sql 실행
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				HandyFarmDTO data = new HandyFarmDTO(); 
				
				data.setCal_number(rs.getString("cal_number"));
				data.setCal_title(rs.getString("cal_title"));
				data.setCal_start_date(rs.getDate("cal_start_date"));
				data.setCal_end_date(rs.getDate("cal_end_date"));
				data.setCal_start_time(rs.getTime("cal_start_time"));
				data.setCal_end_time(rs.getTime("cal_end_time"));
				data.setCal_memo(rs.getString("cal_memo"));
				data.setCal_yield_kg(rs.getFloat("cal_yield_kg"));
				data.setCal_yield_time(rs.getTimestamp("cal_yield_time"));
				data.setGh_id(rs.getString("gh_id"));
				data.setId(rs.getString("id"));
				data.setCrops_id(rs.getString("crops_id"));
				
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
		// 조회 결과값 return
		return list;
	} // end calendarSelect 임예나
	
	// 일정 추가 - 개인 임예나
	public void personalCalendarInsert(String _cal_number, String _cal_title, Date _cal_start_date, Date _cal_end_date, Time _cal_start_time, Time _cal_end_time, String _cal_memo, float _cal_yield_kg, Timestamp _cal_yield_time, String _id) {
		// gh_id, crops_id 선언
		String _gh_id = null;
		String _crops_id = null;
		
		try {
			con = ds.getConnection();
			
			// gh_id, crops_id 가져오기
			String query = "SELECT gh_id, crops_id " + 
						   "FROM robo " + 
						   "WHERE id = ?";
			pstmt = con.prepareStatement(query);
			// 매개변수 값 대입 -> set 메서드에 값 설정
			pstmt.setString(1, _id);
			// sql 실행
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				_gh_id = rs.getString("gh_id");
				_crops_id = rs.getString("crops_id");
			}
			
			// 개인 온실을 추가하는 sql문
			query = "INSERT INTO calendar (cal_number, cal_title, cal_start_date, cal_end_date, cal_start_time, cal_end_time, cal_memo, cal_yield_kg, cal_yield_time, gh_id, id, crops_id) " + 
					"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(query);
			// 매개변수 값 대입 -> set 메서드에 값 설정
			pstmt.setString(1, _cal_number);
			pstmt.setString(2, _cal_title);
			pstmt.setDate(3, _cal_start_date);
			pstmt.setDate(4, _cal_end_date);
			pstmt.setTime(5, _cal_start_time);
			pstmt.setTime(6, _cal_end_time);
			pstmt.setString(7, _cal_memo);
			pstmt.setFloat(8, _cal_yield_kg);
			pstmt.setTimestamp(9, _cal_yield_time);
			pstmt.setString(10, _gh_id);
			pstmt.setString(11, _id);
			pstmt.setString(12, _crops_id);
			// sql 적용
			pstmt.executeUpdate();
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
	} // end personalCalendarInsert 임예나
	
	// 일정 추가 - 온실 임예나
	public void ghCalendarInsert(String _cal_number, String _cal_title, Date date, float _cal_yield_kg, String _gh_id,String _id, String _crops_id) {
		try {
			con = ds.getConnection();
			
			// 개인 온실을 추가하는 sql문
			String query = "INSERT INTO calendar (cal_number, cal_title, cal_start_date, cal_end_date, cal_start_time, cal_end_time, cal_yield_kg, gh_id, id, crops_id) " + 
						   "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(query);
			// 매개변수 값 대입 -> set 메서드에 값 설정
			pstmt.setString(1, _cal_number);
			pstmt.setString(2, _cal_title);
			pstmt.setDate(3, date);
			pstmt.setDate(4, date);
			pstmt.setTime(5, Time.valueOf("00:00:00"));
			pstmt.setTime(6, Time.valueOf("23:59:00"));
			pstmt.setFloat(7, _cal_yield_kg);
			pstmt.setString(8, _gh_id);
			pstmt.setString(9, _id);
			pstmt.setString(10, _crops_id);
			// sql 적용
			pstmt.executeUpdate();
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
	} // end roboCalendarInsert 임예나
	
	// 일정 수정 임예나
	public void calendarUpdate(String _cal_number, String _cal_title, Date _cal_start_date, Date _cal_end_date, Time _cal_start_time, Time _cal_end_time, String _cal_memo, float _cal_yield_kg, Timestamp _cal_yield_time) {
		try {
			con = ds.getConnection();
			
			String query = "UPDATE calendar " + 
						   "SET cal_title = ?, cal_start_date = ?, cal_end_date = ?, cal_start_time = ?, cal_end_time = ?, cal_memo = ?, cal_yield_kg = ?, cal_yield_time = ?" + 
						   "WHERE cal_number = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, _cal_title);
			pstmt.setDate(2, _cal_start_date);
			pstmt.setDate(3, _cal_end_date);
			pstmt.setTime(4, _cal_start_time);
			pstmt.setTime(5, _cal_end_time);
			pstmt.setString(6, _cal_memo);
			pstmt.setFloat(7, _cal_yield_kg);
			pstmt.setTimestamp(8, _cal_yield_time);
			pstmt.setString(9, _cal_number);
			pstmt.executeUpdate();
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
	} // end calendarUpdate 임예나
	
	// 일정 삭제 임예나
	public void claendarDelete(String _cal_number) {
		try {
			con = ds.getConnection();
			
			// 일정을 삭제하는 sql문
			String query = "DELETE " + 
						   "FROM calendar " + 
						   "WHERE cal_number = ?";
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, _cal_number);
			pstmt.executeUpdate();
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
	} // end calendarDelete 임예나
	
	// 작물이 1개인지 확인하기 임예나
	public ArrayList<HandyFarmDTO> isOneCrop(String _gh_id) {
		// list, lhs, crops_id 선언
		ArrayList<HandyFarmDTO> list = new ArrayList<HandyFarmDTO>();
		LinkedHashSet<String> lhs = new LinkedHashSet<String>();
		String crops_id = "";
		
		try {
			con = ds.getConnection();
			
			// 온실에 해당하는 로보의 작물 모두 가져오기
			String query = "SELECT r.crops_id " + 
						   "FROM robo AS r " + 
						   "JOIN crops AS c " + 
						   "ON r.crops_id = c.crops_id " + 
						   "WHERE r.gh_id = ?";
			pstmt = con.prepareStatement(query);
			// 매개변수 값 대입 -> set 메서드에 값 설정
			pstmt.setString(1, _gh_id);
			// sql문 실행
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				crops_id = rs.getString("crops_id");
				lhs.add(crops_id);
			}
			
			// data 객체 선언
			HandyFarmDTO data = new HandyFarmDTO();
			
			// 작물이 1개면 crops_id랑 result true로 저장하기
			if (lhs.size() == 1) {
				// data 객체의 set 메서드에 해당하는 값을 설정
				data.setCrops_id(crops_id);
				data.setResult(true);
			} else { // 작물이 2개이상이면 result false로 저장하기
				// data 객체의 set 메서드에 해당하는 값을 설정
				data.setResult(false);
			}
			
			list.add(data);
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
		// 결과값 list 리턴
		return list;
	} // end isOneCrop 임예나
	
	// 온실에 등록된 작물 정보 가져오기 임예나
	public ArrayList<HandyFarmDTO> cropList(String _gh_id) {
		// list 선언
		ArrayList<HandyFarmDTO> list = new ArrayList<HandyFarmDTO>();
		
		try {
			// DB 연결
			con = ds.getConnection();
			
			// 온실 목록을 가져오는 sql문
			String query = "SELECT r.crops_id, c.crops_img, c.crops_name " + 
						   "FROM robo AS r " + 
						   "JOIN crops AS c " + 
						   "ON r.crops_id = c.crops_id " + 
						   "WHERE r.gh_id = ?";
			pstmt = con.prepareStatement(query);
			// 매개변수 값 대입 -> set 메서드에 값 설정
			pstmt.setString(1, _gh_id);
			// sql문 실행
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// 레코드의 정보를 각 변수에 저장
				String crops_id = rs.getString("crops_id");
				String crops_img = rs.getString("crops_img");
				String crops_name = rs.getString("crops_name");
				
				// data 객체 선언
				HandyFarmDTO data = new HandyFarmDTO();
				
				// data 객체의 set 메서드에 해당하는 값을 설정
				data.setCrops_id(crops_id);
				data.setCrops_img(crops_img);
				data.setCrops_name(crops_name);
				
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
		// 결과값 list 리턴
		return list;
	} // end cropList 임예나
}