<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
<title>실시간 정보 선택</title>
<link rel="stylesheet" href="../css/common_ui.css">
<link rel="stylesheet" href="../css/tip_tab.css">
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
		<div class="title2">병충해 정보</div>
		<!-- //title -->

		<!-- content -->
		<c:forEach items="${pestList}" var="dto">
			<form name="pest" method="post">
				<div class="pest_info_box shadow d-t" onclick="location.href='${dto.link}'">
					<div class="pest-img d-tc va-m">
						<img alt="pest-img" src="${dto.img}">
					</div>
					<div class="d-tc">
						<div class="pest_infointitle f-s HF-DarkGray t-a-l m-l">
							${dto.title }
						</div>
						<div class="pest_infoinContent HF-Gray t-a-l m-l m-t-s">
							${dto.content} <br> <br>
						</div>
						<div class="pest_infoFooter HF-DarkGray t-a-r m-l">
							${dto.c_date }
						</div>
					</div>
				</div>
			</form>
		</c:forEach>
		<!-- //content -->
	</div>
	<!-- footer -->
	<%@ include file="../include/bottonTabBar.inc"%>
	<!-- //footer -->
</body>
</html>