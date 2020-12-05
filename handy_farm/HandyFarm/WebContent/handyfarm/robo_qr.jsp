<%--
	* @author 정민정
	* email : as514188@gmail.com
 --%>
 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
      <title>로보 연동_QR</title>
      <link rel="stylesheet" href="../css/common_ui.css">
      <link rel="stylesheet" href="../css/loginSignUp.css">
      <link rel="stylesheet" href="../css/main_tab.css">
      <script src="../js/jquery-3.5.1.min.js"></script>
      <script type="text/javascript" src="../js/jsQR.js"></script>
      <script src="../js/jsQR.js"></script>
      <script type="text/javascript">   
	      document.addEventListener("DOMContentLoaded", function() {
	      var video = document.createElement("video");      
	      var canvasElement = document.getElementById("canvas");
	      var canvas = canvasElement.getContext("2d");
	      var loadingMessage = document.getElementById("loadingMessage");
	      var outputContainer = document.getElementById("output");
	      var outputMessage = document.getElementById("outputMessage");
	      var outputData = document.getElementById("outputData");
	      
	      function drawLine(begin, end, color) {
	         canvas.beginPath();
	         canvas.moveTo(begin.x, begin.y);
	         canvas.lineTo(end.x, end.y);
	         canvas.lineWidth = 4;
	         canvas.strokeStyle = color;
	         canvas.stroke();
	                }
	           // 카메라 사용시
	         navigator.mediaDevices.getUserMedia({ video: { facingMode: "environment" } }).then(function(stream) {
	                    video.srcObject = stream;
	                    video.setAttribute("playsinline", true);      // iOS 사용시 전체 화면을 사용하지 않음을 전달
	                    video.play();
	                    requestAnimationFrame(tick);
	      });
	      function tick() {
	         var codename;
	         loadingMessage.innerText = "활성화 중입니다."
	         if(video.readyState === video.HAVE_ENOUGH_DATA) {
	            loadingMessage.hidden = true;
	              canvasElement.hidden = false;
	              outputContainer.hidden = false;
	              // 읽어들이는 비디오 화면의 크기
	              canvasElement.height = video.videoHeight;
	              canvasElement.width = video.videoWidth;
	              canvas.drawImage(video, 0, 0, canvasElement.width, canvasElement.height);
	              var imageData = canvas.getImageData(0, 0, canvasElement.width, canvasElement.height);
	              var code = jsQR(imageData.data, imageData.width, imageData.height, {
	                 inversionAttempts : "dontInvert",
	                 });
	                // QR코드 인식에 성공한 경우
	                if(code) {
	                   // 인식한 QR코드의 영역을 감싸는 사용자에게 보여지는 테두리 생성
	                   drawLine(code.location.topLeftCorner, code.location.topRightCorner, "#22AA4D");
	                   drawLine(code.location.topRightCorner, code.location.bottomRightCorner, "#22AA4D");
	                   drawLine(code.location.bottomRightCorner, code.location.bottomLeftCorner, "#22AA4D");
	                   drawLine(code.location.bottomLeftCorner, code.location.topLeftCorner, "#22AA4D");
	                   outputMessage.hidden = true;
	                   outputData.parentElement.hidden = false;
	                   // QR코드 메시지 출력
	                   gh_id = $('input[name=gh_id]').val()
	                   next = $('input[name=next]').val()
	                   //outputData.innerHTML = code.data;
	                   location.href= next + "?robo_serial=" + code.data + "&gh_id=" + gh_id;
	                   // return을 써서 함수를 빠져나가면 QR코드 프로그램이 종료된다.
	                    // return;
	                   sleep();
	                   }
	                // QR코드 인식에 실패한 경우 
	                else {
	                   outputMessage.hidden = false;
	                   outputData.parentElement.hidden = true;
	                   }
	                }
	         requestAnimationFrame(tick);
	      }
	   });
	 </script>
   </head>
   <body>
   		<div class="wrap">
			<!-- upButton -->
			<button class="b-n HF-back d-b m-t-ml m-l" value="back" onclick="history.back(-1);" >
				<img class="ud-img f-l" src="../icon/upButton.png" alt="back"/>
			</button>			
			<!-- // upButton -->
			
		   <!-- title -->
           <div class="title">
            	로보 연동
           </div>
           <!-- // title -->
	           
		   <form method="post">
			    <input type="hidden" name="id" value="${ id }">
				<input type="hidden" name="password" value="${ password }">
				<input type="hidden" name="gh_nickname" value="${gh_nickname}">
				<input type="hidden" name="next" value="${ next }">
           
		         <div class="waybox">
		         *인증 방법을 선택해주세요
		         </div>
		         
		         <!-- 시리얼 -->
	        	 <button class="unsel_btn shadow" formaction=roboSerial.do>
	        	 	시리얼 번호
	        	 </button>
	        	 <!-- 시리얼 -->
	         
		         <!-- QR -->
		         <button class="sel_btn shadow">
		         	QR 코드 인증
		         </button>
		         <!-- QR --> 
		         
		      <div id="test">
		      	<div id="output" class="prmsg">
		        	<div id="outputMessage" hidden=""></div>
		       		<div id="outputLayer" hidden="">
		          		<span id="outputData"></span>
		       		</div>
		      	</div>
		   	</div>
		   <div>&nbsp;</div>
		   <div class = "qr_img_auto">
		      <div class="qrcode">
		         <div id="loadingMessage">
		            	카메라 권한을 허용해주세요.
		         </div>
		         <canvas id="canvas"></canvas>
		      </div>
		   </div>
		   </form>
   		</div>
   </body>
</html>