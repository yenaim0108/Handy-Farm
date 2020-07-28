<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.sql.*" %>
<%@ page import = "javax.naming.*" %>
<%@ page import = "javax.sql.*" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="EUC-KR">
	<title>MariaDB 원격 접속 테스트</title>
</head>
<body>
	<%
		DataSource ds;
		Connection con = null;
		PreparedStatement pstmt = null;
		
		Context ctx = new InitialContext();
		ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mariadb");
		
		try {
			con = ds.getConnection();
			out.println(con);
			
			int _cell = 1012345678;
			String _auth = "342946";
			
			String query = "insert into member "
						 + "values (?, ?)";
			pstmt = con.prepareStatement(query);
			pstmt.setInt(1, _cell);
			pstmt.setString(2, _auth);
			int n = pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstmt != null) pstmt.close();
				if (con != null) pstmt.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	%>
</body>
</html>