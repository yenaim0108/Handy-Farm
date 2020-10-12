<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
		<title>메인 페이지</title>
		<link rel="stylesheet" href="../css/common_ui.css">
		<link rel="stylesheet" href="../css/main_tab.css">
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	</head>
<<<<<<< HEAD
	<body class="HF-backGreen"> 
	<form method="post" name="form">
		<div class="wrap">
		<body class="HF-backWhite"> 
=======
	<body class="HF-backWhite"> 
>>>>>>> 152c51b61c2626c0735f4a1c662c56b957a03a7e
		<div class="HF-backGreen full-b over-x-h over-y-h">
			<!-- 로고 -->
			<img class="logo-img d-ib m-t-lg" src="../icon/handyfarm_white.png" alt="handyfarm">
			<!-- 로고 -->
			<!-- 알림 이력  -->
			<img class="PL-img f-r m-b-sl m-t-lg p-t-s m-r" src="../icon/notification.png" alt="push_log" onclick="location.href='pushLog.do'">
			<!-- // 알림 이력 -->
<<<<<<< HEAD

			<!-- push_log_icon -->
			<input type="submit" class="ud-img p-b-sl m-l-lg"  value="로그" formaction=pushLog.do>
<!-- 			<div class="d-ib push_log" onclick="location.href='push_log.jsp'">
				<img class="ud-img p-b-sl m-l-lg" src="../icon/notification.png" alt="push_log"/>
			</div> -->
			<!-- push_log_icon -->
			
			<!-- mainlist -->
			<div class="mainlist">

=======
			
>>>>>>> 152c51b61c2626c0735f4a1c662c56b957a03a7e
			<!-- 날씨 -->
			<div class="weather m-0-a">
				<div class="d-ib va-m">
					<img src="../icon/weather_sun.png" alt="weather">
				</div>
				<div class="d-ib va-m t-a-l m-r-ml">
					30℃
				</div>
				<div class="d-ib va-m location m-r">
					영등포구
				</div>
				<div class="d-ib va-m next t-a-l">
					<img src="../icon/next.png" alt="next">
				</div>
			</div>
			<div class="update m-t-sl m-b-lg">
				업데이트 5/6 오후 1:10
				<img src="../icon/refresh.png" alt="refresh">
			</div>
			<!-- // 날씨 -->
			
			<!-- 온실 목록 -->
			<div class="GHlist p-x-ml">
				<!-- 제목 -->
				<div class="t-a-l m-t-lg m-b">
					나의 온실 목록
				</div>
				<!-- // 제목 -->
				<!-- 온실 -->
				<c:forEach items="${ GHList }" var="dto">
					<form name="gh" method="post">
						<input type="hidden" name="gh_id" value="${ dto.gh_id }">
						<div class="GH GH-Red b-n shadow m-b-m p-t-m">
							<div class="d-t m-b">
								<!-- 온실 사진 -->
								<img class="m-l-lg d-tc" src="${ dto.gh_img }" alt="GHImage">
								<!-- // 온실 사진 -->
								<!-- 온실 정보 -->
								<div class="d-tc va-m t-a-l GH-Info p-r-sl">
									${ dto.gh_nickname }
								</div>
								<!-- // 온실 정보 -->
							</div>
							<!-- 수정 -->
							<input class="unsel-pageBtn shadow m-b-m" type="submit" value="수정" formaction="ghUpdateUI.do">
							<!-- // 수정 -->
							<!-- 삭제 -->
							<input class="sel-pageBtn shadow m-b-m" type="submit" value="삭제" formaction="ghDelete.do">
							<!-- // 삭제 -->
						</div>
					</form>
				</c:forEach>
				<!-- // 온실 -->
				<!-- 온실 개설 -->
				<div class="add shadow m-b-ml" onclick="location.href='ghInsertUI.do'">
					<img src="../icon/add.png" alt="add">
				</div>
				<!-- // 온실 개설 -->
			</div>
			<!-- // 온실목록 -->
			
		</div>
	</form>
		<!-- footer -->
		<%@ include file="../include/bottonTabBar.inc" %>
		<!-- //footer -->
	</body>
</html>