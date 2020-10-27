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
		<div class="wrap">
			<!-- upButton -->
			<button class="b-n HF-back d-b m-t-ml m-l-sl" value="back" onclick="history.back(-1);" >
				<img class="ud-img f-l" src="../icon/upButton.png" alt="back"/>
			</button>			
			<!-- // upButton -->
			
			<!-- title -->
        	 <div class="title">
            	로보 연동
         	</div>
         	<!-- // title -->
         	
			<form name="robo_serial" method="post" action="${ next }">
				<input type="hidden" name="id" value="${ id }">
				<input type="hidden" name="password" value="${ password }">
				<input type="hidden" name="gh_id" value="${ gh_id }">
				<input type="hidden" name="gh_nickname" value="${gh_nickname}">
				<input type="hidden" name="next" value="${ next }">
	         	
	         	<div class="waybox">
	         		*인증 방법을 선택해주세요
	         	</div>
	         	
	         	<!-- 시리얼 -->
	        	 <button class="sel_btn shadow">
	        	 	시리얼 번호
	        	 </button>
	        	 <!-- 시리얼 -->
	         
		         <!-- QR -->
		         <button class="unsel_btn shadow" formaction=roboQR.do>
		         	QR 코드 인증
		         </button>
		         <!-- QR --> 
	         
	        	 <!-- textBox robo_serial -->
		         <div class="seriald-ib">
					<div class="serialbox m-t-lg d-ib">
						<input class ="serialtb b-n shadow p-x-ml" type="text" maxlength="16" id="serial" placeholder="시리얼번호를 입력" name="robo_serial"/>
					</div>
							        
			        <!-- ok button -->
					<button class="d-ib serial_ok shadow">
						확인
					</button>
					<!-- ok button -->
				
		        </div>
		        <!-- textBox robo_serial -->
	
			</form>
		</div>
	</body>
</html>