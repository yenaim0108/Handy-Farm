<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
		<title>생장 정보</title>
		<link rel="stylesheet" type="text/css" href="../css/common_ui.css">
		<link rel="stylesheet" type="text/css" href="../css/main_tab.css">
	</head>
	<body>
		<!-- title -->
		<div class="title">토마온실</div>
		<!-- // title -->
		
		<!-- pageBtn -->
		<button class="sel-pageBtn shadow d-ib">생장</button>
		<button class="unsel-pageBtn shadow d-ib">분석</button>
		<!-- // pageBtn -->
		
		<!-- contents -->
		<div>
			<div class="growthBox shadow d-ib">
				<div class="bold m-b-sl">온도</div>
				<img src="../icon/temperature.png" alt="temperature">
				<hr class="HF-DarkGray">
				<div class="HF-DarkGray">30℃</div>
			</div>
			
			<div class="growthBox shadow d-ib">
				<div class="bold m-b-sl">습도</div>
				<img src="../icon/humidity.png" alt="humidity">
				<hr>
				<div class="HF-DarkGray">60%</div>
			</div>
			
			<div class="growthBox shadow d-ib">
				<div class="bold m-b-sl">이산화탄소</div>
				<img src="../icon/co2.png" alt="co2">
				<hr>
				<div class="HF-DarkGray">10%</div>
			</div>
			
			<div class="growthBox shadow d-ib">
				<div class="bold m-b-sl">토양 수분도</div>
				<img src="../icon/soil-moisture.png" alt="soil-moisture">
				<hr>
				<div class="HF-DarkGray">67%</div>
			</div>
			
			<div class="growthBox shadow d-ib">
				<div class="bold m-b-sl">일조량</div>
				<img src="../icon/sunshine.png" alt="sunshine">
				<hr>
				<div class="HF-DarkGray">33%</div>
			</div>
			
			<div class="growthBox shadow d-ib">
				<div class="bold m-b-sl">수확 가능 비율</div>
				<img src="../icon/harvestable.png" alt="harvestable">
				<hr>
				<div class="HF-DarkGray">0%</div>
			</div>
		</div>
		<!-- // contents -->
	</body>
</html>