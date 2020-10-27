<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
		<title>캘린더</title>
		<link rel="stylesheet" href="../css/common_ui.css?after">
		<link rel="stylesheet" href="../css/calendar_tab.css?after">
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script type="text/javascript">
			// date, today, pageYear, firstDate, lastDate 선언
			var date = new Date(); // 달력을 표시하는 기준 날짜 저장
			var today = new Date(); // 오늘 날짜 저장
			var firstDate; // 이번 달의 첫째 날
			var lastDate; // 이번 달의 마지막 날
			
			// 이전 달
			function prvCalendar() {
				date = new Date(date.getFullYear(), date.getMonth() -1, date.getDate()); // 이전 달을 date에 저장
				createCalendar(); // 달력 생성
			}
			
			// 다음 달
			function nextCalendar() {
				date = new Date(date.getFullYear(), date.getMonth() +1, date.getDate()); // 다음 달을 date에 저장
				createCalendar(); // 달력 생성	
			}
			
			// 달력 생성
			function createCalendar() {
				firstDate = new Date(date.getFullYear(), date.getMonth(), 1) // 이번 달의 첫째 날
				lastDate = new Date(date.getFullYear(), date.getMonth() + 1, 0) // 이번 달의 마지막 날
				
				var calendar = document.getElementById("calendar"); // 캘린더 지정
				var month = document.getElementById("month"); // 월 지정
				var year = document.getElementById("year"); // 연도 지정
				
				// html에 월과 년 출력
				// new Date()로 생성할 경우 정확히 현재의 월을 가져오지만,
				// 그렇지 않은 경우 getMonth()는 0 ~ 11을 반환하기 때문에 현재 월을 구하기 위해서라면 + 1을 해야한다.
				month.innerHTML = (date.getMonth() + 1) + "월";
				year.innerHTML = date.getFullYear() + "년"
				
				while (calendar.rows.length > 1) {
					// 열 지우기
					// 기본 열 크기는 body 부분에서 1로 고정되어 있으므로(일~월 출력) 테이블의 tr -1을 해줘야 30일 이후로  다음달에 순서대로 열이 표시된다.
					calendar.deleteRow(calendar.rows.length - 1);
				}
				
				var row = null; // 행의 갯수
				var cnt = 0; // 셀의 갯수 세기
				row = calendar.insertRow(); // 캘린더 초기화, 첫 번째 행 생성
				
				// 1일이 시작되는 칸 맞추기
				for (i = 0; i < firstDate.getDay(); i++) { // 이번 달의 day만큼 반복하기
					cell = row.insertCell(); // 열 한칸 생성
					cnt += 1; // 열의 갯수를 계속 다음으로 위치하게 함
				}
				
				// 달력 출력
				for (i = 1; i <= lastDate.getDate(); i++) { // 1 ~ 마지막 일까지 반복하기
					cell = row.insertCell(); // 열 한칸 생성
					cell.innerHTML = i; // html에 day 출력
					cell.id = i; // id 추가
					cnt += 1; // 열의 갯수를 계속 다음으로 위치하게 함
					
					// 토요일이면 다음 행으로 넘기기
					if ((cnt % 7) == 0) {
						row = calendar.insertRow();
					}
					
					// 오늘 날짜에 초록색으로 표시하기
					if (date.getFullYear() == today.getFullYear() &&
						date.getMonth() == today.getMonth() &&
						i == today.getDate()) {
						cell.classList.add('active');
					}
				}
				
				click();
			}
			
			var tdArray = [];
			// 모든 날에 클릭 이벤트 처리기를 담아놓기
			function click() {
				for (i = 1; i <= lastDate.getDate(); i++) {
					tdArray[i] = document.getElementById(i);
					tdArray[i].addEventListener('click', changeActive);
				}
			}
			
			// 사용자가 클릭한 날짜의 스타일 변경 및 일정 출력
			function changeActive(e) {
				for (i = 1; i <= lastDate.getDate(); i++) { // 기존에 선택된 날짜의 active 클래스 삭제
					if (tdArray[i].classList.contains('active')) {
						tdArray[i].classList.remove('active');
					}
				}
				
				clickDate = e.currentTarget;
				clickDate.classList.add('active');
				date = new Date(date.getFullYear(), date.getMonth(), clickDate.id);
				
				reloadList(date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + clickDate.id);
			}
			
			// 사용자가 클릭한 날짜의 일정 가져오기
			function reloadList(date) {
				var html = " "
				
				$.ajax({
					type: "POST",
					url: "calendarSelect.do",
					data: "date=" + date,
					dataType: "json",
					success: function(data) {
						$('#to-doList').empty();
						
						$.each(data, function(key, value) {
							html += "<div class='do t-a-l m-b-s " + value.cal_color + "'>";
							html += value.cal_title;
							html += "<div class='m-t-s'></div>";
							html += value.cal_time + " ~";
							html += "</div>"

						});
						
						document.getElementById('to-doList').innerHTML = html;
					},
					error: function(request, status, error) {
						alert("code : " + request.status + "\nmessage : " + request.reponseText +"\nerror : " + error + "\n에러가 발생하였습니다.\n관리자에게 문의해보세요.");
					}
				});
			}
		</script>

	</head>
	<body>
		<div class="wrap">
			<!-- calendar header -->
			<div class="m-t-lg p-t">
				<img class="calendarBtn va-m" src="../icon/left_arrow.png" alt="previous" onclick="prvCalendar()">
				<div id="month" class="HF-Green d-ib"></div>
				<img class="calendarBtn va-m" src="../icon/right_arrow.png" alt="next" onclick="nextCalendar()">
			</div>
			<div id="year" class="m-b-ml"></div>
			<!-- // calendar header -->
			
			<!-- calendar -->
			<table id="calendar" class="HF-backWhite shadow p-a">
				<tr>
					<th>일</th>
					<th>월</th>
					<th>화</th>
					<th>수</th>
					<th>목</th>
					<th>금</th>
					<th>토</th>
				</tr>
			</table>
			<script type="text/javascript">
    			createCalendar();
			</script>
			<!-- // calendar -->
			
			<!-- to-do list -->
			<div id="to-doTitle" class="t-a-l">
				오늘의 일정
			</div>
			<div id="to-doList" class="HF-backWhite shadow p-a-ml">
				<c:forEach items="${ calendar }" var="dto">
					<div class="do t-a-l m-b-s ${ dto.cal_color }">
						${ dto.cal_title }
						<div class="m-t-s"></div>
						${ dto.cal_time } ~
					</div>
				</c:forEach>
			</div>
			<!-- to-do list -->
			
			<img class="floating HF-backGreen p-a-m shadow" src="../icon/add_white.png" alt="floating" onclick="location.href='calendarWho.do'">
		</div>
		<!-- footer -->
		<%@ include file="../include/bottonTabBar.inc" %>
		<!-- //footer -->
	</body>
</html>