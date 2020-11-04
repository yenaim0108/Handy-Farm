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
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

 <script type="text/javascript">

 function wishUdate(){
	 var nowsrc = $('#wish').attr("src");	//src를 가져옴
	 var unsel = "../icon/unlike_heart.png";
	 var sel = "../icon/like_heart.png";
	 var wish = 0;
	 var crops_id = $('#wish').attr("title");	 
	 
	 if(nowsrc == unsel){
		 wish = 0;
	 }else if(nowsrc == sel){
		 wish = 1;
	 }
	 
	 $.ajax({
		 type:"POST",
		 url:"wishUdate.do",
		 data: "wish=" + wish + "&crops_id=" + crops_id,
		 dataType:"json",
		 success: function(data){
			 if(data[0].wish){ //1이면
				 // 사진 바꿔주기
				 $("#wish").attr("src", sel);
			 }else{//0이면
				 $("#wish").attr("src", unsel);
			 }
			 crops_id=null;
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
	<form name="crop" method="post">
		<!-- title -->
		<div class="title">정보 Talk</div>
		<!-- // title -->

		<!-- pageBtn -->
		<button type="button" class="unsel-pageBtn shadow d-ib"
			onclick="location.href='realInfo.do'">경작 연관 정보</button>
		<button type="button" class="sel-pageBtn shadow d-ib"
			onclick="location.href='cropAll.do'">작물별 경작조건</button>
		<!-- // pageBtn -->



		<!-- textBox search-->
		<div class="m-0-a">
			<input class="textBox b-n shadow f-s m-l-sl" type="text" maxlength="8"
				name="searchword" placeholder="검색어를 입력해주세요." formaction=cropAll.do
				style="background-image: url('../icon/search.png'); background-position: 20px 9px; background-repeat: no-repeat; background-size: 25px;">
			<img class="heart-img f-r" src="../icon/unlike_heart.png" id="wish_list" onclick="location.href='wishlist.do'" alt="wish">
			<%
			String searchword = request.getParameter("searchword");
			%>
		</div>
		<!-- textBox search-->

		<!-- 한줄띄우기 -->
		<div class="m-y-ml"></div>

		<!-- 작물 리스트 : crops에 있는 거 가져와서 띄워야 함. 추후에 이미지수정해야한다는 거 잊지마셈 -->
		<c:forEach items="${cropAllList}" var="dto" varStatus="status">
				<div id="${dto.crops_id }" class="cropBox p-a-sl shadow t-a-l m-x-sl">
					<!--사진하고 수박 -->
					<div class="d-ib"  onclick="location.href='cropTipSel.do?crops_id=${dto.crops_id}'">
					<img class="crop-img f-r " src="${dto.crops_img }"
						alt="crops_img">
					<div class="cropintitle">${dto.crops_name}</div>
					</div>
					<img class="crop_heart-img va-m" src="${dto.img }" id="wish" title="${dto.crops_id }" onclick="wishUdate('${dto.crops_id }')"  alt="${dto.crops_id }">
					
				</div>
				<!-- 한줄띄우기 -->
				<div class="m-y-sl"></div>
			


		</c:forEach>

		<!-- //작물 리스트 -->


	</form>
	</div>

	<!-- footer -->
	<%@ include file="../include/bottonTabBar.inc"%>
	<!-- //footer -->
</body>
</html>