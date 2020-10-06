<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
		<title>생장 정보</title>
		<link rel="stylesheet" type="text/css" href="../css/common_ui.css?after">
		<link rel="stylesheet" type="text/css" href="../css/main_tab.css?after">
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	</head>
	<body>
		<div class="wrap">
			<!-- title -->
			<div class="title">
			 	토마온실
			</div>
			<!-- // title -->
			
			<!-- pageBtn -->
			<button class="sel-pageBtn shadow d-ib">
				생장
			</button>
			<button class="unsel-pageBtn shadow d-ib">
				분석
			</button>
			<!-- // pageBtn -->
			
			<!-- contents -->
			<div>
				<div class="growthBox d-ib shadow">
					온도
					<div class="d-t m-t">
						<img class="d-tc" src="../icon/temperature.png" alt="temperature">
						<div class="HF-DarkGray d-tc va-m value">30℃</div>
					</div>
				</div>
				
				<div class="growthBox d-ib shadow">
					습도
					<div class="d-t m-t">
						<img class="d-tc" src="../icon/humidity.png" alt="humidity">
						<div class="HF-DarkGray d-tc va-m value">60%</div>
					</div>
				</div>
				
				<div class="growthBox d-ib shadow">
					이산화탄소
					<div class="d-t m-t">
						<img class="d-tc" src="../icon/co2.png" alt="co2">
						<div class="HF-DarkGray d-tc va-m value">2단계</div>
					</div>
				</div>
				
				<div class="growthBox d-ib shadow">
					토양수분도
					<div class="d-t m-t">
						<img class="d-tc" src="../icon/soil-moisture.png" alt="soil-moisture">
						<div class="HF-DarkGray d-tc va-m value">40%</div>
					</div>
				</div>
				
				<div class="growthBox d-ib shadow">
					일조량
					<div class="d-t m-t">
						<img class="d-tc" src="../icon/sunshine.png" alt="sunshine">
						<div class="HF-DarkGray d-tc va-m value">67lx</div>
					</div>
				</div>
				
				<div class="growthBox d-ib shadow">
					수확 가능 비율
					<div class="d-t m-t">
						<img class="d-tc" src="../icon/harvestable.png" alt="harvestable">
						<div class="HF-DarkGray d-tc va-m value">33%</div>
					</div>
				</div>
			</div>
			<!-- // contents -->
		</div>
		<!-- footer -->
		<%@ include file="../include/bottonTabBar.inc" %>
		<!-- //footer -->
	</body>
</html>