<%--
	* @author 정민정
	* email : as514188@gmail.com
 --%>
 
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
      <script src="../js/jquery-3.5.1.min.js"></script>
   </head>
   <body>
      <div class="wrap">
			<!-- upButton -->
			<button class="b-n HF-back d-b m-t-ml m-l" value="back" onclick="history.back(-1);" >
				<img class="ud-img f-l" src="../icon/upButton.png" alt="back"/>
			</button>			
			<!-- // upButton -->
			
		   <form method="post" name="form">
			   <!-- 온실 아이디만 넘길 겸 쓴다 -->
			   <%
			   String gh_id = request.getParameter("gh_id");
			   %>
			   <input type="hidden" name="gh_id" value="<%= gh_id%>"/>
			   <!-- 온실 아이디만 넘길 겸 쓴다 -->
			   
		         <!-- title -->
		         <div class="title">
		          	  온실 수정
		         </div>
		         <!-- title -->
		         
	         <!-- picture insert -->
	         <c:forEach items="${ gh_in_list }" var="in_list">
		         <div class="cameraBox shadow" >
		            <!-- camera_img -->
		            <div class="">
		            <!-- 이 아래에 src부분에 ${in_list.gh_img} 써주어야함 -->
		            <img class="camera-img" src="${in_list.gh_img}" alt="camera" onclick=document.all.file.click();>
		            <input type="file" name="file" id="file" style="display: none;"/>
		            </div>
		            <!-- camera_img -->
		         </div>
		         <!-- picture insert -->   
		
	            <input type="hidden" name="gh_id" value="${in_list.gh_id}">
		        
		        <!-- Nickname -->
				<div class="labelsetting labelNick t-a-l">
					별명
				</div>
				<!-- Nickname -->
				
				<!-- textBox Nickname -->
				<div class="m-0-a">
					<input class ="textBox b-n shadow p-x-ml" type="text" maxlength="8" id="gh_nickname" value="${in_list.gh_nickname}" name="gh_nickname" alt="nickname">
				</div>
				<!-- textBox Nickname -->
			</c:forEach>
	         
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
	   </form>
      </div>
   </body>
</html>