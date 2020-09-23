<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
		<title>알림센터</title>
		<link rel="stylesheet" href="../css/common_ui.css">
		<link rel="stylesheet" href="../css/gh.css">
		<link rel="stylesheet" href="../css/push.css">
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>

		
	</head>
	<body>
		<div class="wrap">
			<!-- icon_back -->
			<button class="ud-div b-n HF-back d-b m-t-ml m-l-sl" value="push_back" onclick="location.href='main.do'" >
				<img class="ud-img f-l" src="../icon/upButton.png" alt="back"/>
			</button>
			<!-- icon_back -->
		</div>
		
		<!-- title -->
		<div class="title_gh_rb">
			알림센터
		</div>
		<!-- title -->
		<%
			int count = 4;
			String[] ctgword = {"growthinfo", "infonotifi", "weathernotifi", "pests"};
			String ctgname = "";
			
		
			for(int i=0; i<count; i++ ) {
		
		%>
		<!-- push_list -->
		<div class="pushBox p-a-sl shadow t-a-l">
			<div class="d-ib pushintitle p-t-sl p-l-sl">
			토마토 내부 온도가 올라갔어요!!<br>2020-03-01
			</div>
			
			<!-- 카테고리 별로 css 다르게 적용 -->
			<%
				
				
				switch(ctgword[i]){
				case "growthinfo":
					ctgname = "생장정보";
					break;
				case "infonotifi":
					ctgname = "정보알림";
					break;
				case "weathernotifi":
					ctgname = "날씨알림";
					break;
				case "pests":
					ctgname = "병충해알림";
					break;
				}
			%>
			<div class="d-ib">
				<div class="pushinctg <%=ctgword[i] %>">
				<%=ctgname %>
				</div>
			</div>
			<!-- 카테고리 별로 css 다르게 적용 -->
		
		</div>
		<!-- push_list -->
		
		<!-- 알림 간 간격 -->
		<div class="m-b-sl"> </div>
		<!-- 알림 간 간격 -->
		
		<%
		}
		%>
		
		<!-- footer -->
		<%@ include file="../include/bottonTabBar.inc" %>
		<!-- //footer -->
	</body>
</html>