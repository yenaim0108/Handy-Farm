<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
		<title>온실 수정</title>
		<link rel="stylesheet" href="../css/common_ui.css">
		<link rel="stylesheet" href="../css/main_tab.css">
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		
	</head>
	<body>
	<form method="post" name="form">
	<!-- 온실 아이디만 넘길 겸 쓴다 -->
	<input type="hidden" name="gh_id" value="gh-12345678-001"/>
	<!-- 온실 아이디만 넘길 겸 쓴다 -->
	
		<div class="wrap">
			<!-- title -->
			<div class="title_gh_rb">
				온실 수정
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
<<<<<<< HEAD
	         <div class="labelsetting labelNick t-a-l">
	         	별명
	         </div>
	         <!-- Nickname -->
	         <!-- textBox Nickname -->
	         <div class="m-0-a">
	            <input class ="textBox b-n shadow p-x-ml" type="text" maxlength="8" id="Nickname" placeholder="별명을 입력해주세요."/>
	         </div>
	         <!-- textBox Nickname -->
			<div class="labelsetting labelrobo t-a-l d-ib">
			로보
			</div>
			<div class="labelsetting d-ib">
			<input type="submit" class="HF-Green b-n f-s HF-back underline" value="로보등록" formaction=roboInsertUI.do >
			</div>
	   	      
	         <!-- Crops -->
	         <!-- DropBox Crops-->
	         <select name="robo_serial" class="p-x-ml d-ib b-n shadow" style="width: 200px;">
	         	<c:forEach  items="${ robo_list }" var="dto">
					<option value="${ dto.robo_serial}">${ dto.robo_nickname }</option>
				</c:forEach>
			</select>
			
			<!-- update -->
 			<input type="submit" class="ud-div b-n HF-back HF-DarkGray underline" value="수정" formaction=roboUpdateUI.do >
			<!-- update -->
			<!-- delete -->
			<input type="submit" class="ud-div b-n HF-back HF-Red underline" value="삭제" formaction=roboDelete.do >
=======
			
			<!-- Nickname -->
			<div class="labelsetting labelNick t-a-l">
			별명
			</div>
			<!-- Nickname -->
			<!-- textBox Nickname -->
			<div>
				<input class ="textBox p-a-sl f-s b-n shadow" type="text" placeholder="수정할 로보 이름" maxlength="5" id="Nickname" alt="Nickname"/>
			</div>
			<!-- textBox Nickname -->
			
			<!-- roboBox : label을 한 이유: 로보 라는 글자가 width가 줄여져도 TextBox 위 왼쪽에 위치 -->
			<div class="labelsetting labelrobo t-a-l d-ib p-l-sl">
			로보
			</div>
			<div class="labelsetting d-ib">
			<input type="submit" class="HF-Green b-n f-s HF-back" value="로보 등록" formaction=roboInsertUI.do >
			</div>
			
			<!-- 한칸 띄우기 -->
			<div class="d-b"> </div>
			<!-- 한칸 띄우기 -->
			
			<!-- roboBox -->
			<!-- DropBox robo : labelBox을 한 이유: 로보 라는 글자가 width가 줄여져도 TextBox 위 왼쪽에 위치 -->
			<div class="labelBox d-ib">
			<select class="roboBox p-a-sl f-s d-ib b-n shadow">
				<option selected>토마로보</option>
				<!-- for문 돌릴 부분 -->
				<option>토마온실</option>
				<!-- for문 돌릴 부분 -->
			</select>
			</div>
			<!-- DropBox robo -->
			
			<!-- update -->
			<button class="ud-div b-n HF-back" value="robo_insert" onclick="location.href='robo_update.do'" >
				<img class="ud-img d-ib" src="../icon/update.png" alt="update"/>
			</button>
			<!-- update -->
			<!-- delete -->
			<button class="ud-div b-n HF-back" value="robo_delete" >
				<img class="ud-img d-ib" src="../icon/delete.png" alt="delete"/>
			</button>
>>>>>>> 152c51b61c2626c0735f4a1c662c56b957a03a7e
			<!-- delete -->
			
			<!-- 한칸 띄우기 -->
			<div class="d-b"> </div>
			<!-- 한칸 띄우기 -->
			
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
	</form>
		<!-- footer -->
		<%@ include file="../include/bottonTabBar.inc" %>
		<!-- //footer -->
	</body>
</html>