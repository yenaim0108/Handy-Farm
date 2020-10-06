<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
		<title>회원가입-로보 등록</title>
		<link rel="stylesheet" href="../css/common_ui.css">
		<link rel="stylesheet" href="../css/main_tab.css">
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	</head>
	<body>
		<div class="wrap">

			<!-- 값 받아오기 -->
			
			<!-- 값 받아오기 -->
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
			<!-- SignUp Compelete -->
			<button class="signup-complete b-n shadow m-t-40 m-b-sl f-s">
				회원가입 완료
			</button>
			<!-- // SignUp Compelete -->
		</div>
		<!-- footer -->
		<%@ include file="../include/bottonTabBar.inc" %>
		<!-- //footer -->
	</body>
</html>