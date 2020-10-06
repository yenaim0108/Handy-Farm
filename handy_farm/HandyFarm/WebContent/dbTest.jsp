<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="javax.sql.*" %>
<%@ page import="javax.naming.*" %>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	</head>
	<body>
<%
          // 데이터베이스 연결 정보
			DataSource ds;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mariadb");
							
			try {
				con = ds.getConnection();
				out.println("db 연결 : " + con);
			} catch(Exception e) {
				e.printStackTrace();
			}

%>
	</body>
</html>