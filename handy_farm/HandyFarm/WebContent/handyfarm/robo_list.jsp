<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
		<title>로보 목록</title>
		<link rel="stylesheet" href="../css/common_ui.css">
		<link rel="stylesheet" href="../css/main_tab.css">
		<script src="../js/jquery-3.5.1.min.js"></script>
	</head>
	<body>
		<div class="wrap">
			<!-- upButton -->
			<button class="b-n HF-back d-b m-t-ml m-l" value="back" onclick="history.back(-1);" >
				<img class="ud-img f-l" src="../icon/upButton.png" alt="back"/>
			</button>			
			<!-- // upButton -->
			
			<!-- title -->
			<div class="title2">
			 	${ gh_nickname } - 로보 목록
			</div>
			<!-- // title -->
			
			<!-- 로보 목록 -->
			<c:forEach items="${ roboList }" var="dto">
               <form name="robo" method="post">
                  <!-- 온실 ID -->
                  <input type="hidden" name="gh_id" value="${ gh_id }">
                  <!-- // 온실 ID -->
               
               	  <!-- 로보 시리얼 번호 -->
                  <input type="hidden" name="robo_serial" value="${ dto.robo_serial }">
                  <!-- // 로보 시리얼번호 -->
                  
                  <div class="GH GH-Red b-n shadow m-0-a m-b-m p-t-sl">
                     <div class="d-t m-b">
                        <!-- 로보 사진 -->
                        <img class="m-l-lg d-tc" src="${ dto.robo_img }" alt="RoboImage">
                        <!-- // 로보 사진 -->
                        
                        <!-- 로보 정보 -->
                        <div class="d-tc va-m t-a-l GH-Info p-r-sl">
                        	<div class="f-s">
                        		${ dto.crops_name }
                        	</div>
                           ${ dto.robo_nickname }
                        </div>
                        <!-- // 로보 정보 -->
                     </div>
                     
                     <!-- 수정 -->
                     <input class="unsel-pageBtn shadow m-b-m" type="submit" value="수정" formaction="roboUpdateUI.do">
                     <!-- // 수정 -->
                     
                     <!-- 삭제 -->
                     <input class="sel-pageBtn shadow m-b-m" type="submit" value="삭제" formaction="roboDelete.do">
                     <!-- // 삭제 -->
                  </div>
               </form>
            </c:forEach>
            <!-- // 로보 목록 -->
            
            <!-- 로보 개설 -->
            <div class="add shadow" onclick="location.href='mainRoboInsertUI.do?gh_id=${ gh_id }'">
               <img src="../icon/add.png" alt="add">
            </div>
            <!-- // 로보 개설 -->
		</div>
	</body>
</html>