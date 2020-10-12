<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
		<title>온실 개설</title>
		<link rel="stylesheet" href="../css/common_ui.css">
		<link rel="stylesheet" href="../css/main_tab.css">
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		
	</head>
	<body>
	 <form method="post" name="form">
		<div class="wrap">
		<%
			session.setAttribute("phone_number","01062892166");
			String phone_number = (String)session.getAttribute("phone_number");
			System.out.println(phone_number);
		%>
		<!-- title -->
		<div class="title_gh_rb">
			온실 개설
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
		<div>
			<input class ="textBox b-n shadow p-x-ml" type="text" maxlength="5"  placeholder="별명을 입력해주세요." name="gh_nickname" alt="Nickname"/>
		</div>
		<!-- textBox Nickname -->
		
		
		
		<!-- roboBox : label을 한 이유: 로보 라는 글자가 width가 줄여져도 TextBox 위 왼쪽에 위치 -->
<!-- 		<div class="labelsetting labelrobo t-a-l d-ib">
		로보
		</div>
		<div class="labelsetting d-ib">
		<button class="HF-Green b-n f-s HF-back underline" value="robo_insert" onclick="location.href='robo_insert.do'" >로보등록</button>
		</div>
		
		한칸 띄우기
		<div class="d-b"> </div>
		한칸 띄우기
		
		roboBox
		DropBox robo : labelBox을 한 이유: 로보 라는 글자가 width가 줄여져도 TextBox 위 왼쪽에 위치
		<div class="labelBox d-ib">
		<select class="p-x-ml d-ib b-n shadow" style="width: 190px;">
			<option selected>-</option>
			for문 돌릴 부분
			<option>DB에서 가져온 값</option>
			for문 돌릴 부분
		</select>
		</div>
		DropBox robo
		
		update
		<button class="b-n HF-back" value="robo_insert" onclick="location.href='robo_update.do'" >
			<img class="ud-img d-ib" src="../icon/update.png" alt="update"/>
		</button>
		update
		delete
		<button class="b-n HF-back" value="robo_delete" >
			<img class="ud-img d-ib" src="../icon/delete.png" alt="delete"/>
		</button> -->
		<!-- delete -->
		
		<!-- 한칸 띄우기 -->
		<div class="m-t-gh"> </div>
		<!-- 한칸 띄우기 -->
		
		<!-- cancel button -->
		<button class="d-ib cancel-b-Btn m-t-40" onclick="history.back(-1);">
		취소
		</button>
		<!-- cancel button -->
		<!-- ok button -->
		<input type="submit" class="d-ib sel-pageBtn okBox m-t-40"  value="확인" formaction=ghInsertSignup.do>
		<!-- ok button -->
		
		</div>
		</form>
		<!-- footer -->
		<%@ include file="../include/bottonTabBar.inc" %>
		<!-- //footer -->
	</body>
</html>