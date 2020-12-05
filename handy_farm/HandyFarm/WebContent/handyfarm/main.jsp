<%--
	* @author 임예나
	* email : yenaim0108@gmail.com
 --%>
 
 <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
      <title>메인 페이지</title>
      <link rel="stylesheet" href="../css/common_ui.css">
      <link rel="stylesheet" href="../css/main_tab.css">
      <script src="../js/jquery-3.5.1.min.js"></script>
      <script type="text/javascript">
	   		// 작물선택 화면
			function cropUI(gh_id) {
				var html = "";
				var result;
				var crops_id;
				var msg = gh_id + ",";
				
				$.ajax ({
					type: "POST",
					url: "isOneCrop.do",
					data: "gh_id=" + gh_id,
					dataType: "json",
					async: false,
					success: function (data) {
						if (data[0].result) {
							result = true;
							crops_id = data[0].crops_id;
						} else {
							result = false;
						}
					},
					error: function(request, status, error) {
						alert("code : " + request.status + "\nmessage : " + request.reponseText +"\nerror : " + error + "\n에러가 발생하였습니다.\n관리자에게 문의해보세요.");
					}
				});
				
				if (result) { // 작물이 1개면 바로 생장환경으로 넘어가기
					location.href="growth.do?gh_id=" + gh_id + "&crops_id=" + crops_id;
				} else { // 작물이 2개 이상이면 작물 선택 화면으로 넘어가기
					document.getElementById('l-title').innerHTML = "작물을 선택해주세요.";
				
					$.ajax ({
						type: "POST",
						url: "CropUI.do",
						data: "gh_id=" + gh_id,
						dataType: "json",
						success: function(data) {
							$('#l-data').empty();
							
							$.each(data, function(key, value) {
								msg += value.crops_id
								html += "<div class='l-gh p-a-sl d-ib HF-backWhite t-a-c shadow' onclick=location.href='growth.do?gh_id=" + gh_id +"&crops_id=" + value.crops_id + "'>";
								html += value.crops_name;
								html += "<div>";
								html += "<img src='" + value.crops_img + "' alt='CropImage'>";
								html += "</div>";
								html += "</div>";
							});
							
							document.getElementById('l-data').innerHTML = html;
	
							$('#mask').fadeTo("fast", 0.5);
							$('#list').show().animate({
					    		bottom: 0
					    	}, 500);
						},
						error: function(request, status, error) {
							alert("code : " + request.status + "\nmessage : " + request.reponseText +"\nerror : " + error + "\n에러가 발생하였습니다.\n관리자에게 문의해보세요.");
						}
					});
				}
			}
	   		
	   		// 팝업창 없애기
	   		function remove() {
	   			$('#mask').fadeOut("fast");
	   			
		    	$('#list').show().animate({
		    		bottom: -495
		    	});
	   		}
      </script>
   </head>
   <body class="HF-backWhite">
   	  <div id="mask" class="full d-n" onclick="remove();"></div>
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
            <div class="t-a-l m-t-lg m-b m-l">
               	나의 온실 목록
            </div>
            <!-- // 제목 -->
            
            <!-- 온실 목록 -->
            <c:forEach items="${ GHList }" var="dto">
               <form name="gh" method="post" action="growth.do">
               	  <!-- 온실 ID  -->
                  <input type="hidden" name="gh_id" value="${ dto.gh_id }">
                  <!-- // 온실 ID -->
                  
                  <div class="GH GH-Red b-n shadow p-t-sl" onclick="cropUI('${ dto.gh_id }')">
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
                     <button class="unsel-pageBtn shadow m-b-m" formaction="ghUpdateUI.do">
                     	수정
                     </button>
                     <!-- // 수정 -->
                     
                     <!-- 삭제 -->
                     <button class="sel-pageBtn shadow m-b-m" formaction="ghDelete.do">
                     	삭제
                     </button>
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
      <%@ include file="../include/bottomTabBar.inc" %>
      <!-- //footer -->
      
      <!-- list -->
      <%@ include file="../include/list.inc" %>
      <!-- // list -->
   </body>
</html>