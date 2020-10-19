<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
		<title>실시간 정보 선택</title>
		<link rel="stylesheet" href="../css/common_ui.css">
		<link rel="stylesheet" href="../css/tip_tab.css">
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	</head>
	<body>
		<div class="wrap">
		
		<!-- icon_back -->
			<button class="b-n HF-back d-b m-t-ml m-l-sl" value="push_back" onclick="location.href='tip.do'" >
				<img class="tip_back-img f-l" src="../icon/upButton.png" alt="back"/>
			</button>
		<!-- icon_back -->
		
		<!-- title -->
		<div class="title">병충해 정보</div>
		<!-- //title -->
		
		<!-- content -->
		<c:forEach items="${ pestList }" var="dto">
			<form name="pest" method="post">

				<div class="pest_info_box p-a-sl shadow t-a-l m-0-a m-y-sl"
					onclick="location.href='realInfoSel.do'"
					style="	background-image: url('${dto.img}'); 
					background-position: center; background-repeat: no-repeat; background-size: 150px; back
					background-color : ">
					<div class="pest_infointitle p-b-s">${dto.title}</div>
					<div class="pest_infoinContent p-x-m">${dto.content} <br><br> ${dto.link}</div>
					<div class="pest_infoFooter p-x-lg">${dto.date}</div>
				</div>

			</form>			
		</c:forEach>



		<!-- 기본상자
		<div class="pest_info_box p-a-sl shadow t-a-l m-a"
			onclick="location.href='realInfoSel.do'">
			<div class="pest_infointitle p-b-s">
			안녕하세요			
			</div>
			<div class="pest_infoinContent p-x-m"> 내용입니다.</div>
			
		</div> 
		
		<div class="pest_info_box p-a-sl shadow t-a-l m-0-a m-y-sl"
					onclick="location.href='realInfoSel.do'"
					style="	background-image: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)), url('${dto.img}');
					background-position: center; background-repeat: no-repeat; background-size: 150px;">
					<div class="pest_infointitle p-b-s">${dto.title}</div>
					<div class="pest_infoinContent p-x-m">${dto.content} <br><br> ${dto.link}</div>
					<div class="pest_infoFooter p-x-lg">${dto.date}</div>

				</div>
		
		-->




		<!-- //content -->
		
		</div>
		<!-- footer -->
		<%@ include file="../include/bottonTabBar.inc" %>
		<!-- //footer -->
	</body>
</html>