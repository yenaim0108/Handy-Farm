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
		<title>알림센터</title>
		<link rel="stylesheet" href="../css/common_ui.css">
		<link rel="stylesheet" href="../css/main_tab.css">
		<script src="../js/jquery-3.5.1.min.js"></script>
	</head>
	<body>
		<div class="wrap">
			<!-- upButton -->
			<button class="b-n HF-back d-b m-t-ml m-l" value="back" onclick="history.back(-1);" >
				<img class="ud-img f-l" src="../icon/upButton.png" alt="back"/>
			</button>			
			<!-- // upButton -->
			
			<!-- title -->
			<div class="title2">
				알림센터
			</div>
			<!-- title -->
			
			<!-- push_list -->
			<c:forEach var="push" items="${push_list}" varStatus="status">
				<div class="pushBox p-a-sl shadow t-a-l d-t">
					<div class="d-ib pushintitle p-t-sl p-x-sl d-tc">
					[${push.gh_nickname} - ${push.crops_name}] ${push.push_msg} <br> ${push.push_date}
					</div>
					<!-- 카테고리 별로 css 다르게 적용 -->
					<div class="d-tc va-m">
						<div class="pushinctg ${push.push_name}">
						${push.push_category}
					</div>
					</div>
					<!-- 카테고리 별로 css 다르게 적용 -->
				</div>
				<div class="m-b"></div>
			</c:forEach>
			<!-- push_list -->
			
			<!-- 알림 간 간격 -->
			<div class="m-b-sl"> </div>
			<!-- 알림 간 간격 -->
		</div>
		<!-- footer -->
		<%@ include file="../include/bottonTabBar.inc" %>
		<!-- //footer -->
	</body>
</html>