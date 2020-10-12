<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.handyfarm.dao.HandyFarmDAO" %>
<%@ page import="com.handyfarm.entity.HandyFarmDTO" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
		<title>로보 연동_시리얼</title>
		<link rel="stylesheet" href="../css/common_ui.css">
		<link rel="stylesheet" href="../css/loginSignUp.css">
		<link rel="stylesheet" href="../css/main_tab.css">
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	</head>
	<body>
	<form method="post">
		<div class="wrap">
		<input type="hidden" name="gh_id" value="${gh_id}">
			<!-- title -->
         <div class="title_rb">
            로보 연동
         </div>
         <div class="waybox">
         *인증 방법을 선택해주세요
         </div>
         <!-- 시리얼 -->
         <input type="submit" class="sel_btn shadow" value="시리얼 번호">
         <!-- 시리얼 -->
         
         <!-- QR -->
         <input type="submit" class="unsel_btn shadow" value="QR 코드 인증" formaction=roboQR.do>
         <!-- QR --> 
         
         <!-- textBox robo_serial -->
	         <div class="seriald-ib">
				<div class="serialbox m-t-lg d-ib">
					<input class ="serialtb b-n shadow p-x-ml" type="text" maxlength="15" id="serial" placeholder="시리얼번호를 입력" name="robo_serial"/>
				</div>		        
	        <!-- ok button -->
			<input type="submit" class="d-ib serial_ok shadow"  value="확인" formaction=roboInsertUI.do>
			<!-- ok button -->
			
	        </div>
	        <!-- textBox robo_serial -->

		</div>
		</form>
		<!-- footer -->
		<%@ include file="../include/bottonTabBar.inc" %>
		<!-- //footer -->
	</body>
</html>