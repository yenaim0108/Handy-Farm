<%--
	* @author 정민정
	* email : as514188@gmail.com
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
		<title>온실 개설</title>
		<link rel="stylesheet" href="../css/common_ui.css">
		<link rel="stylesheet" href="../css/main_tab.css">
		<script src="../js/jquery-3.5.1.min.js"></script>
		<script>
			function checkData() {
				if (!$('#gh_nickname').val()) {
					alert("온실 별명을 입력해주세요.");
					return false;
				}
			}
		</script>
	</head>
	<body>
		<div class="wrap">
			<!-- upButton -->
			<button class="b-n HF-back d-b m-t-ml m-l" value="back" onclick="history.back(-1);" >
				<img class="ud-img f-l" src="../icon/upButton.png" alt="back"/>
			</button>			
			<!-- // upButton -->
			
			<!-- title -->
			<div class="title">
			 	온실 개설
			</div>
			<!-- // title -->
			
			<form name="gh_insert" method="post" action="${ next }" onsubmit="return checkData()">
				<input type="hidden" name="id" value="${ id }">
				<input type="hidden" name="password" value="${ password }">
				
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
					<input class ="textBox b-n shadow p-x-ml" type="text" maxlength="8" id="gh_nickname" placeholder="별명을 입력해주세요." name="gh_nickname" alt="nickname">
				</div>
				<!-- textBox Nickname -->
				
				<!-- cancel button -->
				<button type="button" class="d-ib cancel-b-Btn m-t-40" onclick="history.back(-1);">
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