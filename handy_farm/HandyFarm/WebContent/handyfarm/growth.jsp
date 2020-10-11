<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
		<title>생장 정보</title>
		<link rel="stylesheet" type="text/css" href="../css/common_ui.css?after">
		<link rel="stylesheet" type="text/css" href="../css/main_tab.css?after">
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<%
		request.setAttribute("gh_id", "gh-12345678-001");
		request.setAttribute("cultivar_number", "pepper");
%>
	</head>
	<body>
		<div class="wrap">
			<!-- title -->
			<div class="title">
			 	${ gh_name }
			</div>
			<!-- // title -->
			
			<!-- pageBtn -->
			<button class="sel-pageBtn shadow d-ib" onclick="location.href='growth.do'">
				생장
			</button>
			<button class="unsel-pageBtn shadow d-ib" onclick="location.href='analysis.do'">
				분석
			</button>
			<!-- // pageBtn -->
			
			<!-- contents -->
			<div>
				<c:forEach	items="${ growth }" var="dto">
					<div class="growthBox d-ib shadow">
						${ dto.sensor_name }
						<div class="d-t m-t">
							<img class="d-tc" src="../icon/${ dto.sensor_type }.png" alt="${ dto.sensor_type }">
							<fmt:parseNumber var="sensor_value" integerOnly="true" value="${ dto.sensor_value }" />
							<div class="HF-DarkGray d-tc va-m value">${ sensor_value }${ dto.sensor_unit }</div>
						</div>
					</div>
				</c:forEach>
			</div>
			<!-- // contents -->
		</div>
		<!-- footer -->
		<%@ include file="../include/bottonTabBar.inc" %>
		<!-- //footer -->
	</body>
</html>