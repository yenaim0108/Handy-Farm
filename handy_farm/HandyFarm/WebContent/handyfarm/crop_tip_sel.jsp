<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
		<title>Tip_작물별 경작 조건 선택(상세)</title>
		<link rel="stylesheet" href="../css/common_ui.css">
		<link rel="stylesheet" href="../css/tip_tab.css">
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	</head>
	<body>
	<c:forEach var="dto" items="${tip_list}" >
		<div class="wrap">
		
		<!-- icon_back -->
		<button class="b-n HF-back d-b m-t-ml m-l-sl" value="push_back"
			onclick="location.href='cropAll.do'">
			<img class="tip_back-img f-l" src="../icon/upButton.png" alt="back" />
		</button>
		<!-- icon_back -->
		
		
		
		<!-- content -->
		<img class="crop-imgBox" src="${dto.crops_img }" alt="tomato" />
		<div class="title">${dto.crops_name }</div>
		
		<!-- 한줄띄우기 -->
        <div class="m-y-lg"></div> 
        
		<div class="labelsetting labelNick t-a-l p-a-0 m-l">온도</div>

		<!-- 여름 -->
		<div class="crop_tip_Box p-a-sl shadow t-a-l m-x-sl">
			<div class="crop_tip_content p-a-sl HF-DarkGray">
				여름 <img class="tip_whether-img" src="../icon/summer.png"
					alt="summer" /><br>
				<br>
				<div class="crop_tip_content p-a-sl">*주간</div>
				<div class="crop_tip_content m-l-lg d-i">
					-오전
					<div class="temperature_label d-i m-r">
					
						최고 <span class="HF-Red"><fmt:parseNumber integerOnly="true" value="${dto.sum_mrn_max_temperature }" />
						</span> / 최저 <span class="HF-Tip_Blue"><fmt:parseNumber integerOnly="true" value="${dto.sum_mrn_min_temperature }" /></span>
					</div>
				</div>

				<!-- 한줄띄우기 -->
				<div class="m-y-sl"></div>

				<div class="crop_tip_content m-l-lg d-i">
					-오후
					<div class="temperature_label d-i m-r">
					
						최고 <span class="HF-Red"><fmt:parseNumber integerOnly="true" value="${dto.sum_aft_max_temperature }" /></span>
						 / 최저 <span class="HF-Tip_Blue"><fmt:parseNumber integerOnly="true" value="${dto.sum_aft_min_temperature }" /></span>
					</div>
				</div>
				
				<!-- 한줄띄우기 -->
				<div class="m-y-sl"></div>
				
				<div class="crop_tip_content p-a-sl">*야간</div>
				<div class="temperature_label d-i m-r">
				
						최고 <span class="HF-Red"><fmt:parseNumber integerOnly="true" value="${dto.sum_ngh_max_temperature }" /></span>
						 / 최저 <span class="HF-Tip_Blue"><fmt:parseNumber integerOnly="true" value="${dto.sum_ngh_min_temperature }" /></span>
				</div>
			</div>
			
			<!-- 한줄띄우기 -->
			<div class="m-y-m"></div>
		
		</div>
		<!-- //여름 -->
		
		<!-- 한줄띄우기 -->
        <div class="m-y-m"></div> 
		
		<!-- 겨울 -->
		<div class="crop_tip_Box p-a-sl shadow t-a-l m-x-sl">
			<div class="crop_tip_content p-a-sl HF-DarkGray">
				겨울 <img class="tip_whether-img" src="../icon/winter.png"
					alt="winter" /><br>
				<br>
				<div class="crop_tip_content p-a-sl">*주간</div>
				<div class="temperature_label d-i m-r">
						최고 <span class="HF-Red"><fmt:parseNumber integerOnly="true" value="${dto.win_day_max_temperature }" /></span>
						 / 최저 <span class="HF-Tip_Blue"><fmt:parseNumber integerOnly="true" value="${dto.win_day_min_temperature }" /></span>
				</div>
	
				
				<!-- 한줄띄우기 -->
				<div class="m-y-sl"></div>
				
				<div class="crop_tip_content p-a-sl">*야간</div>
				<div class="temperature_label d-i m-r">
				
						최고 <span class="HF-Red"><fmt:parseNumber integerOnly="true" value="${dto.win_ngh_max_temperature }" /></span>
						 / 최저 <span class="HF-Tip_Blue"><fmt:parseNumber integerOnly="true" value="${dto.win_ngh_min_temperature }" /></span>
				</div>
			</div>
			<!-- 한줄띄우기 -->
			<div class="m-y-m"></div>
		</div>
		<!-- //겨울 -->
		
		<!-- 한줄띄우기 -->
        <div class="m-y-lg"></div>

		<!-- 습도 -->
		<div class="labelsetting labelNick t-a-l p-a-0 m-l">습도</div>
		<div class="crop_tip_Box p-a-sl shadow t-a-l m-x-sl">
			<div class="crop_tip_content p-a-sl HF-DarkGray">
				<div class="temperature_label d-i m-r">
				
					최고 <span class="HF-Red"><fmt:parseNumber integerOnly="true" value="${dto.max_humidity }" /></span>
					 / 최저 <span class="HF-Tip_Blue"><fmt:parseNumber integerOnly="true" value="${dto.min_humidity }" /></span>
				</div>

				<!-- 한줄띄우기 -->
				<div class="m-y-s"></div>
			</div>
			
			<!-- 한줄띄우기 -->
			<div class="m-y-m"></div>
		</div>
		<!-- //습도 -->

		<!-- 이산화탄소 -->
		<div class="labelsetting labelNick t-a-l p-a-0 m-l">이산화탄소</div>
		<div class="crop_tip_Box p-a-sl shadow t-a-l m-x-sl">
			<div class="crop_tip_content p-a-sl HF-DarkGray">
				<div class="temperature_label d-i m-r">
				
					최대 <span class="HF-Red"><fmt:parseNumber integerOnly="true" value="${dto.max_co2 }" /></span>
					 / 최저 <span class="HF-Tip_Blue"><fmt:parseNumber integerOnly="true" value="${dto.min_co2 }" /></span>
				</div>

				<!-- 한줄띄우기 -->
				<div class="m-y-s"></div>
			</div>
			
			<!-- 한줄띄우기 -->
			<div class="m-y-m"></div>
		</div>
		<!-- //이산화탄소 -->
		
		<!-- 토양수분도 -->
		<div class="labelsetting labelNick t-a-l p-a-0 m-l">토양수분도</div>
		<div class="crop_tip_Box p-a-sl shadow t-a-l m-x-sl">
			<div class="crop_tip_content p-a-sl HF-DarkGray">
				<div class="temperature_label d-i m-r">
				
					최고 <span class="HF-Red"><fmt:parseNumber integerOnly="true" value="${dto.max_soil_moisture }" /></span>
					 / 최저 <span class="HF-Tip_Blue"><fmt:parseNumber integerOnly="true" value="${dto.min_soil_moisture }" /></span>
				</div>

				<!-- 한줄띄우기 -->
				<div class="m-y-s"></div>
			</div>
			
			<!-- 한줄띄우기 -->
			<div class="m-y-m"></div>
		</div>
		<!-- //토양수분도 -->
		
		<!-- 일조량 -->
		<div class="labelsetting labelNick t-a-l p-a-0 m-l">일조량</div>
		<div class="crop_tip_Box p-a-sl shadow t-a-l m-x-sl">
			<div class="crop_tip_content p-a-sl HF-DarkGray">
				<div class="temperature_label d-i m-r">
				
					최고 <span class="HF-Red"><fmt:parseNumber integerOnly="true" value="${dto.max_sunshine }" /></span>
					 / 최저 <span class="HF-Tip_Blue"><fmt:parseNumber integerOnly="true" value="${dto.min_sunshine }" /></span>
				</div>

				<!-- 한줄띄우기 -->
				<div class="m-y-s"></div>
			</div>
			
			<!-- 한줄띄우기 -->
			<div class="m-y-m"></div>
		</div>
		
		<!-- //일조량 -->
		
		<!-- 토양속 온도 -->
		<div class="labelsetting labelNick t-a-l p-a-0 m-l">토양 속 온도</div>
		<div class="crop_tip_Box p-a-sl shadow t-a-l m-x-sl">
			<div class="crop_tip_content p-a-sl HF-DarkGray">
				<div class="temperature_label d-i m-r">
					최고 <span class="HF-Red"><fmt:parseNumber integerOnly="true" value="${dto.max_soil_temperature }" /></span>
					 / 최저 <span class="HF-Tip_Blue"><fmt:parseNumber integerOnly="true" value="${dto.min_soil_temperature }" /></span>
				</div>

				<!-- 한줄띄우기 -->
				<div class="m-y-s"></div>
			</div>
			
			<!-- 한줄띄우기 -->
			<div class="m-y-m"></div>
		</div>		
		<!-- //토양속 온도 -->
		
		<!-- 한줄띄우기 -->
        <div class="m-y-lg"></div> 
		
		<!-- //content -->
		</div>
		</c:forEach>
		<!-- footer -->
		<%@ include file="../include/bottonTabBar.inc" %>
		<!-- //footer -->
	</body>
</html>