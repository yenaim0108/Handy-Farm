<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
		<title>분석 정보</title>
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
			<div class="title">
			 	${ gh_nickname }
			</div>
			<!-- // title -->
			
			<!-- pageBtn -->
			<button class="unsel-pageBtn shadow d-ib" onclick="location.href='growth.do'">
				생장
			</button>
			<button class="sel-pageBtn shadow d-ib" onclick="location.href='analysis.do'">
				분석
			</button>
			<!-- // pageBtn -->
		</div>
		<!-- footer -->
		<%@ include file="../include/bottonTabBar.inc" %>
		<!-- //footer -->
	</body>
</html>