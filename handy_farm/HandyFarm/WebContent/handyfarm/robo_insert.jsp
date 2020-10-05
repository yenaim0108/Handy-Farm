<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
		<title>로보 등록</title>
		<link rel="stylesheet" href="../css/common_ui.css">
		<link rel="stylesheet" href="../css/main_tab.css">
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	</head>
	<body>
		<div class="wrap">
			<!-- 값 -->
			<form action="write.do" method="post">
			시리얼번호: <input type="text" name="robo_serial">
			이미지 경로:<input type="text" name="robo_img">
			별명: <input type="text" name="robo_nickname">
			농작물:
			<select class="roboBox p-a-sl f-s d-ib b-n shadow" name="cultivar_number">
				<option selected>-</option>
				<!-- cultivar list 출력 -->
				<c:forEach var="cultivar" items="${cultivar_list}" varStatus="status">
					<option value="<c:out value = "${cultivar_list}"/>"> </option>
				</c:forEach>
				<!-- cultivar list 출력 -->
				
			</select>
			온실: <input type="text" name="gh_id">
			<!-- 일단 휴대폰 번호 입력으로 했고 나중에 세션으로 받을 예정 -->
			휴대폰번호: <input type="text" name="phone_number">
			<br>
			<input type="submit" value="확인">
			</form>
			<!-- 값 -->
		</div>
		<!-- footer -->
		<%@ include file="../include/bottonTabBar.inc" %>
		<!-- //footer -->
	</body>
</html>