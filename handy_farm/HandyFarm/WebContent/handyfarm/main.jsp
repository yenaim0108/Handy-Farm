<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
		<title>메인 페이지</title>
		<link rel="stylesheet" href="../css/common_ui.css">
		<link rel="stylesheet" href="../css/main_tab.css">
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	</head>
	<body class="HF-backGreen"> 
		<div class="wrap">
			
			<!-- 로고 -->
			<div class="Logo-img-m-l">
				<img class="Logo-img LogoBox d-ib" src="../icon/handyfarm_white.png" alt="Logo" >
			</div>
			<!-- 로고 -->
			
			<!-- push_log_icon -->
			<div class="d-ib push_log" onclick="location.href='push_log.jsp'">
				<img class="ud-img p-b-sl m-l-lg" src="../icon/notification.png" alt="push_log"/>
			</div>
			<!-- push_log_icon -->
			
			<!-- mainlist -->
			<div class="mainlist">
				
			
			
			</div>
			<!-- mainlist -->
			
		</div>
		<!-- footer -->
		<%@ include file="../include/bottonTabBar.inc" %>
		<!-- //footer -->
	</body>
</html>