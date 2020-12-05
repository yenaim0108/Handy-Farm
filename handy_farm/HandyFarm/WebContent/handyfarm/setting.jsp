<%--
	* @author 임예나
	* email : yenaim0108@gmail.com
 --%>
 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
		<title>설정</title>
		<link rel="stylesheet" href="../css/common_ui.css">
		<link rel="stylesheet" href="../css/setting_tab.css">
		<script src="../js/jquery-3.5.1.min.js"></script>
	</head>
	<body>
		<div class="wrap">
			<div class="s-title m-b-lg">
				설 정
			</div>
			<div class="s-box HF-backWhite shadow HF-DarkGray t-a-l d-t p-x-lg" onclick="location.href='push.do'">
				<div class="d-tc va-m">
					알림
				</div>
				<div class="d-tc va-m t-a-r">
					<img class="va-m" src="../icon/right_arrow.png" alt="arrow">
				</div>
			</div>
			<div class="s-box HF-backWhite shadow HF-DarkGray t-a-l d-t p-x-lg" onclick="location.href='http://192.168.100.10:1880/ui/#!/0?socketid=Fr4uJDtYGIRp4l1fAAAX'">
				<div class="d-tc va-m">
					설비 제어
				</div>
				<div class="d-tc va-m t-a-r">
					<img class="va-m" src="../icon/right_arrow.png" alt="arrow">
				</div>
			</div>
		</div>
		<!-- footer -->
		<%@ include file="../include/bottomTabBar.inc" %>
		<!-- //footer -->
	</body>
</html>