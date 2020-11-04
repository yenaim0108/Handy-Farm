<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
         <button class="sel-pageBtn shadow d-ib" onclick="location.href='realInfo.do'">경작 연관 정보</button>
         <button class="unsel-pageBtn shadow d-ib" onclick="location.href='cropAll.do'">작물별 경작조건</button>
         <!-- // pageBtn -->
         
         <!-- contents -->
         
         <div class="labelsetting labelNick t-a-l p-a-0">실시간 이슈</div>
                  
         <!-- DropBox Realtime issue-->
         <select name="issue_number" class="issueBox m-a-s p-a-sl f-s d-ib b-n shadow">
            <!-- <option value="">농작물을 선택해주세요.</option> -->

         </select>
         <!-- DropBox Realtime issue-->
         
         <!-- 한칸띄우기 -->
         <div class="d-b"></div>

		<div class="labelsetting labelNick t-a-l p-a-0">전체 카테고리</div>

		<div class="categoryBox p-a-sl shadow t-a-l m-x-sl"
			onclick="location.href='realInfoSel.do'">
			<div class="categoryintitle p-a-sl m-x-s">
				<img class="ud-img d-ib" src="../icon/pest.png" alt="pests" /><br>
				병충해 정보
			</div>
		</div>

		<div class="categoryBox p-a-sl shadow t-a-l m-x-sl">
			<div class="categoryintitle p-a-sl m-x-s">
				<img class="ud-img" src="../icon/fertilizer.png" alt="fertilizer" /><br>
				비료 정보
			</div>
		</div>

		<!-- 한줄띄우기 -->
		<div class="m-y-ml"></div>


		<div class="categoryBox p-a-sl shadow t-a-l m-x-sl">
			<div class="categoryintitle p-a-sl m-x-s">
				<img class="ud-img" src="../icon/pesticide.png" alt="pesticide" /><br>
				농약 정보
			</div>

		</div>
		<div class="categoryBox p-a-sl shadow t-a-l m-x-sl">
			<div class="categoryintitle p-a-sl m-x-s">
				<img class="ud-img" src="../icon/price-tag.png" alt="priceTag" /><br>
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