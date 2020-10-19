<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri ="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
      <title>온실 수정</title>
      <link rel="stylesheet" href="../css/common_ui.css">
      <link rel="stylesheet" href="../css/main_tab.css">
      <script src="http://code.jquery.com/jquery-latest.min.js"></script>
      
   </head>
   <body>
   <form method="post" name="form">
   <!-- 온실 아이디만 넘길 겸 쓴다 -->
   <%
   String gh_id = request.getParameter("gh_id");
   %>
   <input type="hidden" name="gh_id" value="<%= gh_id%>"/>
   <!-- 온실 아이디만 넘길 겸 쓴다 -->
   
      <div class="wrap">
         <!-- title -->
         <div class="title_gh_rb">
            온실 수정
         </div>
         <!-- title -->
         <!-- picture insert -->
         <c:forEach items="${ gh_in_list }" var="in_list">
         <div class="cameraBox shadow" >
            <!-- camera_img -->
            <div class="">
            <!-- 이 아래에 src부분에 ${in_list.gh_img} 써주어야함 -->
            <img class="camera-img" src="../icon/camera.png" alt="camera" onclick=document.all.file.click();>
            <input type="file" name="file" id="file" style="display: none;"/>
            </div>
            <!-- camera_img -->
         </div>
         <!-- picture insert -->   
            <div class="labelsetting labelNick t-a-l">
               별명
            </div>
            <!-- Nickname -->
            <!-- textBox Nickname -->

            <input type="hidden" name="gh_id" value="${in_list.gh_id}">
            <input type="hidden" name="gh_img" value="${in_list.gh_img}">
            <div class="m-0-a">
               <input class ="textBox b-n shadow p-x-ml" type="text" maxlength="8" name="gh_nickname" placeholder="${in_list.gh_nickname}"/>
            </div>
            </c:forEach>
            <!-- textBox Nickname -->
         <div class="labelsetting labelrobo t-a-l d-ib">
         로보
         </div>
         <div class="labelsetting d-ib">
         <input type="submit" class="HF-Green b-n f-s HF-back underline" value="로보등록" formaction=roboInsertUI.do >
         </div>      
            <!-- Crops -->
            <div class="d-b"> </div>
            <!-- DropBox Crops-->
            <select name="robo_serial" class="p-x-ml d-ib b-n shadow" style="width: 200px;">
               <c:forEach  items="${ robo_list }" var="dto">
               <option value="${ dto.robo_serial}">${ dto.robo_nickname }</option>
            </c:forEach>
         </select>
         
         <!-- update -->
          <input type="submit" class="ud-div b-n HF-back HF-DarkGray underline" value="수정" formaction=roboUpdateUI.do >
         <!-- update -->
         <!-- delete -->
         <input type="submit" class="ud-div b-n HF-back HF-Red underline" value="삭제" formaction=roboDelete.do >
         <!-- delete -->
         
         <!-- 한칸 띄우기 -->
         <div class="d-b"> </div>
         <!-- 한칸 띄우기 -->
         
         <!-- cancel button -->
         <button class="d-ib cancel-b-Btn m-t-40" onclick="history.back(-1);">
         취소
         </button>
         <!-- cancel button -->
         <!-- ok button -->
         <input type="submit" class="d-ib sel-pageBtn okBox m-t-40"  value="확인" formaction=ghUpdate.do>
         <!-- ok button -->
      </div>
   </form>
      <!-- footer -->
      <%@ include file="../include/bottonTabBar.inc" %>
      <!-- //footer -->
   </body>
</html>