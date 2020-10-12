<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
      <title>메인 페이지</title>
      <link rel="stylesheet" href="../css/common_ui.css">
      <link rel="stylesheet" href="../css/main_tab.css">
      <script src="http://code.jquery.com/jquery-latest.min.js"></script>
   </head>
   <body class="HF-backWhite"> 
      <div class="HF-backGreen full-b over-x-h over-y-h">
         <!-- 로고 -->
         <img class="logo-img d-ib m-t-lg" src="../icon/handyfarm_white.png" alt="handyfarm">
         <!-- 로고 -->
         
         <!-- 알림 이력  -->
         <img class="PL-img f-r m-b-sl m-t-lg p-t-s m-r" src="../icon/notification.png" alt="push_log" onclick="location.href='pushLog.do'">
         <!-- // 알림 이력 -->
         
         <!-- 날씨 -->
         <div class="weather m-0-a">
            <div class="d-ib va-m">
               <img src="../icon/weather_sun.png" alt="weather">
            </div>
            <div class="d-ib va-m t-a-l m-r-ml">
               30℃
            </div>
            <div class="d-ib va-m location m-r">
               영등포구
            </div>
            <div class="d-ib va-m next t-a-l">
               <img src="../icon/next.png" alt="next">
            </div>
         </div>
         <div class="update m-t-sl m-b-lg">
            업데이트 5/6 오후 1:10
            <img src="../icon/refresh.png" alt="refresh">
         </div>
         <!-- // 날씨 -->
         
         <!-- 온실 목록 -->
         <div class="GHlist p-x-ml">
            <!-- 제목 -->
            <div class="t-a-l m-t-lg m-b">
               	나의 온실 목록
            </div>
            <!-- // 제목 -->
            
            <!-- 온실 목록 -->
            <c:forEach items="${ GHList }" var="dto">
               <form name="gh" method="post">
               	  <!-- 온실 ID  -->
                  <input type="hidden" name="gh_id" value="${ dto.gh_id }">
                  <!-- // 온실 ID -->
                  
                  <div class="GH GH-Red b-n shadow p-t-sl">
                  	<input type="submit" class="HF-Green b-n f-s GH-Red underline d-b f-r m-r-sl" value="로보목록" formaction="roboList.do">
                     <div class="d-t m-b">
                        <!-- 온실 사진 -->
                        <img class="m-l-lg d-tc" src="${ dto.gh_img }" alt="GHImage">
                        <!-- // 온실 사진 -->
                        
                        <!-- 온실 정보 -->
                        <div class="d-tc va-m t-a-l GH-Info p-r-sl">
                           ${ dto.gh_nickname }
                        </div>
                        <!-- // 온실 정보 -->
                     </div>
                     
                     <!-- 수정 -->
                     <input class="unsel-pageBtn shadow m-b-m" type="submit" value="수정" formaction="ghUpdateUI.do">
                     <!-- // 수정 -->
                     
                     <!-- 삭제 -->
                     <input class="sel-pageBtn shadow m-b-m" type="submit" value="삭제" formaction="ghDelete.do">
                     <!-- // 삭제 -->
                  </div>
               </form>
            </c:forEach>
            <!-- // 온실 목록 -->
            
            <!-- 온실 개설 -->
            <div class="add shadow" onclick="location.href='ghInsertUI.do'">
               <img src="../icon/add.png" alt="add">
            </div>
            <!-- // 온실 개설 -->
         </div>
         <!-- // 온실목록 -->
      </div>
      <!-- footer -->
      <%@ include file="../include/bottonTabBar.inc" %>
      <!-- //footer -->
   </body>
</html>