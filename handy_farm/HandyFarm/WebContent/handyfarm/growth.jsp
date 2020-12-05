<%--
	* @author 임예나
	* email : yenaim0108@gmail.com
 --%>
 
 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
		<title>생장 정보</title>
		<link rel="stylesheet" type="text/css" href="../css/common_ui.css?after">
		<link rel="stylesheet" type="text/css" href="../css/main_tab.css?after">
		<script src="../js/jquery-3.5.1.min.js"></script>
	</head>
	<body>
		<div class="wrap_bottomNone">
			<!-- upButton -->
			<button class="b-n HF-back d-b m-t-ml m-l" value="back" onclick="history.back(-1);" >
				<img class="ud-img f-l" src="../icon/upButton.png" alt="back"/>
			</button>			
			<!-- // upButton -->
			
			<!-- title -->
			<div class="title2">
			 	${ gh_nickname }
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
							<div class="d-tc m-r-sl va-m">
								<img src="../icon/${ dto.sensor_type }.png" alt="${ dto.sensor_type }">
							</div>
							<div class="HF-DarkGray d-tc va-m value t-a-r">${ dto.str_sensor_value }${ dto.sensor_unit }</div>
						</div>
					</div>
				</c:forEach>
			</div>
			<!-- // contents -->
		</div>
	</body>
</html>