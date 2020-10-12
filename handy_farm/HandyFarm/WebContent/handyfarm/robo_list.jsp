<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
		<title>로보 목록</title>
		<link rel="stylesheet" href="../css/common_ui.css">
		<link rel="stylesheet" href="../css/main_tab.css">
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	</head>
	<body>
		<div class="wrap">
			<!-- title -->
			<div class="title">
			 	${ gh_name } - 로보 목록
			</div>
			<!-- // title -->
			
			<!-- 로보 목록 -->
			<c:forEach items="${ RoboList }" var="dto">
               <form name="gh" method="post">
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
                     <input class="unsel-pageBtn shadow m-b-m" type="submit" value="수정" formaction="roboUpdate.do">
                     <!-- // 수정 -->
                     
                     <!-- 삭제 -->
                     <input class="sel-pageBtn shadow m-b-m" type="submit" value="삭제" formaction="roboDelete.do">
                     <!-- // 삭제 -->
                  </div>
               </form>
            </c:forEach>
            <!-- // 로보 목록 -->
		</div>
		<!-- footer -->
		<%@ include file="../include/bottonTabBar.inc" %>
		<!-- //footer -->
	</body>
</html>