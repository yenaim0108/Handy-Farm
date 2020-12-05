<%--
	* @author 김연주(ui), 정민정(ui 수정, 작물 검색), 임예나(ui 수정, wishUpdate(), wishList())
	* email : sym61503@naver.com, as514188@gmail.com, yenaim0108@gmail.com
 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="viewport"
		content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
	<title>Tip_작물별 경작 조건(전체)</title>
	<link rel="stylesheet" href="../css/common_ui.css">
	<link rel="stylesheet" href="../css/tip_tab.css">
	<script src="../js/jquery-3.5.1.min.js"></script>
	 <script type="text/javascript">
	
		 function wishUpdate(img){
			 var nowsrc = img.getAttribute('src');	//src를 가져옴
			 var unsel = "../icon/unlike_heart.png";
			 var sel = "../icon/like_heart.png";
			 var wish = 0;
			 var crops_id = img.getAttribute('title');	 
			 
			 if(nowsrc == unsel){
				 wish = 0;
			 }else if(nowsrc == sel){
				 wish = 1;
			 }
			 
			 $.ajax({
				 type:"POST",
				 url:"wishUpdate.do",
				 data: "wish=" + wish + "&crops_id=" + crops_id,
				 dataType:"json",
				 success: function(data){
					 if(data[0].wish){ //1이면
						 // 사진 바꿔주기
						 img.src = sel;
					 }else{//0이면
						 img.src = unsel;
					 }
					 crops_id=null;
				 },
				 error: function(request, status, error) {
		             alert("code : " + request.status + "\nmessage : " + request.reponseText +"\nerror : " + error + "\n에러가 발생하였습니다.\n관리자에게 문의해보세요.");
				 }
			 });
		 }
	 
		 function wishList(img) {
			 var unsel = "../icon/unlike_heart.png";
			 var sel = "../icon/like_heart.png";
			 var html = " ";
			 
			 if (img.getAttribute('src') == sel) {
				 img.src = unsel;
				 
				 $.ajax({
					type: "POST",
					url: "cropAllList.do",
					dataType: "json",
					success: function (data) {
						$('#cropList').empty();
						
						$.each(data, function(key, value) {
							html += "<div id=" + value.crops_id + " class='cropBox p-a-sl shadow t-a-l'>";
							html += "<div class='crop d-t'>";
							html += "<div class='crop-img d-ib t-l' onclick='location.href='cropTipSel.do?crops_id=${dto.crops_id}''>";
							html += "<img src=" + value.crops_img + " alt='crops_img'>";
							html += "</div>";
							html += "<div class='cropintitle d-tc va-m t-a-r' onclick='location.href='cropTipSel.do?crops_id=${dto.crops_id}''>";
							html += value.crops_name;
							html += "</div>";
							html += "<div class='d-tc va-m t-a-r p-r'>";
							html += "<img class='crop_heart-img' src=" + value.img + " id='wish' title=" + value.crops_id + " onclick='wishUpdate(this)'  alt='" + value.crops_id + "'>";
							html += "</div>";
							html += "</div>";
							html += "</div>";
						});
						
						document.getElementById('cropList').innerHTML = html;
					},
					error: function(request, status, error) {
			            alert("code : " + request.status + "\nmessage : " + request.reponseText +"\nerror : " + error + "\n에러가 발생하였습니다.\n관리자에게 문의해보세요.");
					}
				 });
			 } else {
				 img.src = sel;
				 
				 $.ajax({
					type: "POST",
					url: "wishList.do",
					dataType: "json",
					success: function (data) {
						$('#cropList').empty();
						
						$.each(data, function(key, value) {
							html += "<div id=" + value.crops_id + " class='cropBox p-a-sl shadow t-a-l'>";
							html += "<div class='crop d-t'>";
							html += "<div class='crop-img d-ib t-l' onclick='location.href='cropTipSel.do?crops_id=${dto.crops_id}''>";
							html += "<img src=" + value.crops_img + " alt='crops_img'>";
							html += "</div>";
							html += "<div class='cropintitle d-tc va-m t-a-r' onclick='location.href='cropTipSel.do?crops_id=${dto.crops_id}''>";
							html += value.crops_name;
							html += "</div>";
							html += "<div class='d-tc va-m t-a-r p-r'>";
							html += "<img class='crop_heart-img' src=" + value.img + " id='wish' title=" + value.crops_id + " onclick='wishUpdate(this)'  alt='" + value.crops_id + "'>";
							html += "</div>";
							html += "</div>";
							html += "</div>";
						});
						
						document.getElementById('cropList').innerHTML = html;
					},
					error: function(request, status, error) {
			            alert("code : " + request.status + "\nmessage : " + request.reponseText +"\nerror : " + error + "\n에러가 발생하였습니다.\n관리자에게 문의해보세요.");
					}
				 });
			 }
		 }
	 </script>
</head>
<body>
	<div class="wrap">
	<form name="crop" method="post">
		<!-- title -->
		<div class="title">정보 Talk</div>
		<!-- // title -->

		<!-- pageBtn -->
		<button type="button" class="unsel-pageBtn shadow d-ib"
			onclick="location.href='crawling.do'">농업 관련 정보</button>
		<button type="button" class="sel-pageBtn shadow d-ib"
			onclick="location.href='cropAll.do'">작물별 경작조건</button>
		<!-- // pageBtn -->

		<!-- textBox search-->
		<div class="m-0-a">
			<input class="textBox b-n shadow f-s m-l-sl" type="text"
				name="searchword" placeholder="검색어를 입력해주세요." formaction=cropAll.do
				style="background-image: url('../icon/search.png'); background-position: 20px 9px; background-repeat: no-repeat; background-size: 25px;border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;

출처: https://bearpro.tistory.com/49 [not set] ">
			<img class="heart-img f-r" src="../icon/unlike_heart.png" id="wish_list" onclick="wishList(this)" alt="wish">
			<%
			String searchword = request.getParameter("searchword");
			%>
		</div>
		<!-- textBox search-->

		<!-- 작물 리스트 : crops에 있는 거 가져와서 띄워야 함. 추후에 이미지수정해야한다는 거 잊지마셈 -->
		<div id="cropList" class="m-t-lg p-t-sl">
			<c:forEach items="${cropAllList}" var="dto" varStatus="status">
				<div id="${dto.crops_id }" class="cropBox p-a-sl shadow t-a-l">
					<!--사진하고 수박 -->
					<div class="crop d-t">
						<div class="crop-img d-ib t-l" onclick="location.href='cropTipSel.do?crops_id=${dto.crops_id}'">
							<img src="${dto.crops_img }" alt="crops_img">
						</div>
						<div class="cropintitle d-tc va-m t-a-r" onclick="location.href='cropTipSel.do?crops_id=${dto.crops_id}'">
							${dto.crops_name}
						</div>
						<div class="d-tc va-m t-a-r p-r">
							<img class="crop_heart-img" src="${dto.img }" id="wish" title="${dto.crops_id }" onclick="wishUpdate(this)"  alt="${dto.crops_id }">
						</div>
					</div>
				</div>
			</c:forEach>
		</div>

		<!-- //작물 리스트 -->


	</form>
	</div>

	<!-- footer -->
	<%@ include file="../include/bottomTabBar.inc"%>
	<!-- //footer -->
</body>
</html>