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
		<title>로보 등록</title>
		<link rel="stylesheet" href="../css/common_ui.css">
		<link rel="stylesheet" href="../css/main_tab.css">
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	</head>
	<body>
		<div class="wrap">
			<!-- 값 -->
			<form action="roboinsert.do" method="post">
			시리얼번호: <input type="text" name="robo_serial">
			이미지 경로:<input type="text" name="robo_img">
			별명: <input type="text" name="robo_nickname">
			농작물:<input type="text" name="crops_name">
<!-- 			<select class="roboBox p-a-sl f-s d-ib b-n shadow" name="cultivar_number">
				<option selected>-</option> -->
				<!-- cultivar list 출력 -->
<%-- 				<%
				HandyFarmDAO dao = new HandyFarmDAO();
				ArrayList<HandyFarmDTO> cultivar_list = dao.cultivar_list();
				
				request.setAttribute("cultivar_list", cultivar_list);
				System.out.println(cultivar_list);
				%>
				<c:forEach var="cultivar" items="${cultivar_list}" varStatus="status">
					<option value="<c:out value = "${cultivar_list}"/>">${cultivar_list} </option>
				</c:forEach> --%>
				
				<!-- cultivar list 출력 -->
				
			</select>
			온실: <input type="text" name="gh_id">
			<!-- 일단 휴대폰 번호 입력으로 했고 나중에 세션으로 받을 예정 -->
			휴대폰번호: <input type="text" name="phone_number">
			<br>
			<input type="submit" value="확인">
			</form>
			<!-- 값 -->
			<!-- title -->
			<div class="title_gh_rb">
				로보 등록
			</div>
			<!-- title -->
			<!-- picture insert -->
			<div class="cameraBox shadow" >
				<!-- camera_img -->
				<div class="">
				<img class="camera-img" src="../icon/camera.png" alt="camera" onclick=document.all.file.click();>
				<input type="file" name="file" id="file" style="display: none;"/>
				</div>
				<!-- camera_img -->
			</div>
			<!-- picture insert -->	
			
			<!-- Nickname -->
			<div class="labelsetting labelNick t-a-l">
				별명
			</div>
			<!-- Nickname -->
			<!-- textBox Nickname -->
			<div class="m-0-a">
				<input class ="textBox b-n shadow p-x-ml" type="text" maxlength="8" id="Nickname" placeholder="별명을 입력해주세요."/>
			</div>
			<!-- textBox Nickname -->
			<!-- Crops -->
			<div class="labelsetting labelNick t-a-l">
				농작물
			</div>
			<!-- Crops -->
			<!-- DropBox Crops-->
			<select class="p-x-ml d-b b-n shadow">
				<option value="">농작물을 선택해주세요.</option>
				<option value="tomato">토마토</option>
			</select>
			<!-- DropBox Crops -->
			<!-- cancel button -->
			<button class="d-ib cancel-b-Btn m-t-40" onclick="history.back(-1);">
				취소
			</button>
			<!-- cancel button -->
			<!-- ok button -->
			<button class="d-ib sel-pageBtn okBox m-t-40">
				확인
			</button>
			<!-- ok button -->
		</div>
		<!-- footer -->
		<%@ include file="../include/bottonTabBar.inc" %>
		<!-- //footer -->
	</body>
</html>