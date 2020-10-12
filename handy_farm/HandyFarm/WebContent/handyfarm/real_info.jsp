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
		<title>Tip_실시간 정보(전체)</title>
		<link rel="stylesheet" href="../css/common_ui.css">
		<link rel="stylesheet" href="../css/tip_tab.css">
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	</head>
	<body>
		<div class="wrap">
		<!-- title -->
			<div class="title">정보 Talk</div>
			<!-- // title -->
			
			<!-- pageBtn -->
			<button class="sel-pageBtn shadow d-ib">실시간정보</button>
			<button class="unsel-pageBtn shadow d-ib">작물별 경작조건</button>
			<!-- // pageBtn -->
			
			<!-- contents -->
			
			<div class="labelsetting labelNick t-a-l p-a-0">실시간 이슈</div>
			
			
			
			<!-- DropBox Realtime issue-->
			<select name="issue_number" class="issueBox m-a-s p-a-sl f-s d-ib b-n shadow">
				<!-- <option value="">농작물을 선택해주세요.</option> -->

				<c:forEach var="cultivar" items="${cultivar_list}" varStatus="status">
					<option value="${cultivar.cultivar_number}"> ${cultivar.crops_name} </option>
				</c:forEach>
			</select>
			<!-- DropBox Realtime issue-->
			
			<!-- 한칸띄우기 -->
			<div class="d-b"></div>
			
			<div class="labelsetting labelNick t-a-l p-a-0">전체 카테고리</div>
			
			<div class="categoryBox p-a-sl shadow t-a-l">
				<div class="categoryintitle p-a-sl m-x-s">
				<img class="ud-img d-ib" src="../icon/pest.png" alt="pests"/><br>
					병충해 정보
					<!-- p-t-sl은 추후 이미지 삽입하고 제거 여부 결정!! -->
				</div>
				
			</div>	
			<div class="categoryBox p-a-sl shadow t-a-l">
				<div class="categoryintitle p-a-sl m-x-s">
				<img class="ud-img" src="../icon/fertilizer.png" alt="pests"/><br>
					비료 정보
				</div>
			</div>
			
			<!-- 한줄띄우기 -->
			<div class="m-y-m"></div>	
			
			
			<div class="categoryBox p-a-sl shadow t-a-l">
				<div class="categoryintitle p-a-sl p-l-sl">
				<img class="ud-img" src="../icon/pesticide.png" alt="pests"/><br>
					농약 정보
				</div>
				
			</div>		
			<div class="categoryBox p-a-sl shadow t-a-l">
				<div class="categoryintitle p-a-sl p-l-sl">
				<img class="ud-img" src="../icon/price-tag.png" alt="pests"/><br>
					작물시세 정보
				</div>
			</div>				
			
			
			
			<!-- //contents -->
			
		</div>
		<!-- footer -->
		<%@ include file="../include/bottonTabBar.inc" %>
		<!-- //footer -->
	</body>
</html>