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
		<script>
			function checkData() {
				if (!$('#robo_nickname').val()) {
					alert("로보 별명을 입력해주세요.");
					return false;
				}
				
				if (!$('#crop').val()) {
					alert("농작물을 선택해주세요.");
					return false;
				}
			}
		</script>
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
			 	로보 등록
			</div>
			<!-- // title -->
			
			<!-- 값 -->
			<form method="post" action="${ next }" onsubmit="return checkData()">
				<input type="hidden" name="id" value=${ id }>
				<input type="hidden" name="password" value="${ password }">
				<input type="hidden" name="gh_nickname" value=${ gh_nickname }>
				<input type="hidden" name="robo_serial" value="${ robo_serial }">


				<!-- picture insert -->
				<div class="cameraBox shadow" >
					<!-- camera_img -->
					<div class="">
					<!-- 여기에 이미지 경로 넣는 거 들가야 함 -->
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
					<input class ="textBox b-n shadow p-x-ml" type="text" maxlength="8" id="robo_nickname" placeholder="별명을 입력해주세요." name="robo_nickname">
				</div>
				<!-- textBox Nickname -->

				<!-- Crops -->
				<div class="labelsetting labelNick t-a-l">
					농작물
				</div>
				<!-- Crops -->
				
				<!-- DropBox Crops-->
				<select id="crop" name="crops_id" class="p-x-ml d-b b-n shadow m-b-40">
					<option value="">농작물을 선택해주세요.</option>
					<c:forEach var="crops" items="${crops_list}" varStatus="status">
						<option value="${crops.crops_id}"> ${crops.crops_name} </option>
					</c:forEach>
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
			</form>
		</div>
	</body>
</html>