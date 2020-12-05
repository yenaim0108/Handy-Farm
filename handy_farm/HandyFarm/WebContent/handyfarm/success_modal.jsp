<%--
	* @author 임예나
	* email : yenaim0108@gmail.com
 --%>
 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
		<title>success modal</title>
		<link rel="stylesheet" href="../css/common_ui.css">
		<script type="text/javascript">
			var title;
			var msg;
			
			successModal("df", "알<br>df");
		</script>
	</head>
	<body>
		<div id="success-modal" class="modal">
			<div class="modal-content">
				<img class="modal-icon" src="../icon/alert_success.png" alt="success">
				<div class="modal-title m-b-lg p-b-lg">
					<script>
						document.write(title);
					</script>
				</div>
				<div class="modal-msg">
					<script>
						document.write(msg);
					</script>
				</div>
				<div class="modal-btn">
					닫기
				</div>
			</div>
		</div>
		<script type="text/javascript">
			var modal = document.getElementById("success-modal");
			
			function successModal(title, msg) {
				this.title = title;
				this.msg = msg;
				
				modal.style.display = "block";
			}
			
			var btn = document.getElementsByClassName("modal-btn");
			btn.onclick = function() {
				modal.style.display = "none";
			}
		</script>
	</body>
</html>