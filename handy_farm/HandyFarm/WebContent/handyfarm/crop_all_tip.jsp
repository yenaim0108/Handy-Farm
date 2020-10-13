<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
      <title>Tip_작물별 경작 조건(전체)</title>
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
         <button class="unsel-pageBtn shadow d-ib" onclick="location.href='realInfo.do'">실시간정보</button>
         <button class="sel-pageBtn shadow d-ib" onclick="location.href='cropAll.do'">작물별 경작조건</button>
         <!-- // pageBtn -->


		<!-- textBox search-->
		<div class="m-0-a">
			<input class="textBox b-n shadow p-x-ml" type="text" maxlength="8"
				id="searchWord" placeholder="      검색어를 입력해주세요." name="tip_search"
				style="background-image: url('../icon/search.png'); background-position: 20px 9px; 
					background-repeat: no-repeat; background-size : 25px;">
			<img class="heart-img f-r " src="../icon/unlike_heart.png" alt="unlike_heart">
			
		</div>
		<!-- textBox search-->
		
		<!-- 한줄띄우기 -->
         <div class="m-y-ml"></div>  
		
		<!-- 작물 리스트 : crops에 있는 거 가져와서 띄워야 함. 추후에 수정해야한다는 거 잊지마셈 -->
		
		<div class="cropBox p-a-sl shadow t-a-l m-x-sl m-y-s">
			<img class="crop-img f-r " src="../icon/circle_stawberry.png" alt="circle_stawberry">
			<div class="cropintitle">딸기</div>
			<img class="crop_heart-img" src="../icon/unlike_heart.png" alt="unlike_heart">
         </div>   
         
         <div class="cropBox p-a-sl shadow t-a-l m-x-sl m-y-sl">
			<img class="crop-img f-r " src="../icon/circle_watermelon.png" alt="circle_watermelon">
			<div class="cropintitle">수박</div>
			<img class="crop_heart-img" src="../icon/unlike_heart.png" alt="unlike_heart">
         </div>  
         
         <div class="cropBox p-a-sl shadow t-a-l m-x-sl m-y-s">
			<img class="crop-img f-r " src="../icon/circle_chamoe.png" alt="circle_chamoe">
			<div class="cropintitle">참외</div>
			<img class="crop_heart-img" src="../icon/unlike_heart.png" alt="unlike_heart">
         </div>  
         
         <div class="cropBox p-a-sl shadow t-a-l m-x-sl m-y-sl" onclick="location.href='cropTipSel.do'">
			<img class="crop-img f-r " src="../icon/circle_tomato.png" alt="circle_tomato">
			<div class="cropintitle">토마토</div>
			<img class="crop_heart-img" src="../icon/unlike_heart.png" alt="unlike_heart">
         </div>  
         
         <!-- //작물 리스트 -->
        
         

	</div>
      <!-- footer -->
      <%@ include file="../include/bottonTabBar.inc" %>
      <!-- //footer -->
   </body>
</html>