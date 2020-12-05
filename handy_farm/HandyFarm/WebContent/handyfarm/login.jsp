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
		<title>로그인</title>
		<link rel="stylesheet" href="../css/common_ui.css">
		<link rel="stylesheet" href="../css/loginSignUp.css">
		<script src="../js/jquery-3.5.1.min.js"></script>
		<script>
			// 입력된 데이터 유효성 검사
			function checkData() {
				var idCheck;
				var passwordCheck;
				
				if (!$('#id').val()) {
					alert("아이디를 입력해주세요.");
					$('#id').focus();
					return false;
				}
			
				if (!$("#password").val()) {
					alert("비밀번호를 입력해주세요.");
					$('#password').focus();
					return false;
				}
				
				// member 테이블에 id가 있는지 확인하기.
				$.ajax({
					type: "POST",
					url: "idCheck.do",
					async: false,
					data: "id=" + $('#id').val(),
					dataType: "json",
					success: function(data) {
						if (data[0].idCheck) { // id가 member 테이블이 있으면 true
							idCheck = true;
						} else { // id가 member 테이블에 없으면 false
							idCheck = false;
						}
					},
					error: function(request, status, error) {
						alert("code : " + request.status + "\nmessage : " + request.reponseText +"\nerror : " + error + "\n에러가 발생하였습니다.\n관리자에게 문의해보세요.");
					}
				});
				
				if(!idCheck) {
					alert("존재하지 않는 아이디입니다.\n회원가입을 진행해 주세요.");
					return false;
				}
				
				// password가 동일한지 확인하기.
				$.ajax({
					type: "POST",
					url: "passwordCheck.do",
					async: false,
					data: "id=" + $('#id').val() + "&password=" + $('#password').val(),
					dataType: "json",
					success: function(data) {
						console.log(data[0].passwordCheck)
						if (data[0].passwordCheck) { // 비밀번호가 일치하면 true
							passwordCheck = true;
						} else { // 비밀번호가 일치하지 않으면 false
							passwordCheck = false;
						}
					},
					error: function(request, status, error) {
						alert("code : " + request.status + "\nmessage : " + request.reponseText +"\nerror : " + error + "\n에러가 발생하였습니다.\n관리자에게 문의해보세요.");
					}
				});
				
				if (!passwordCheck) {
					alert("비밀번호가 일치하지 않습니다.\n다시 입력해주세요.");
					return false;
				}
			}
		</script>
	</head>
	<body>
		<div class="wrap">
			<!-- title -->
			<div class="login-title">
			 	로그인
			</div>
			<!-- // title -->
			
			<form name="login" method="post" action="login.do" onsubmit="return checkData()">
				<!-- id -->
				<div class="labelNick labelsetting t-a-l">
					아이디
				</div>
				<input class="textBox b-n shadow p-x-ml" type="text" id="id" name="id">
				<!-- // id -->
				
				<!-- password -->
				<div class="labelNick labelsetting t-a-l m-t-m">
					비밀번호
				</div>
				<input class="textBox b-n shadow p-x-ml" type="password" id="password" name="password">
				<!-- // password -->
				
				<!-- autoLogin -->
				<div id="autoLogin" class="m-b-lg p-b-sl t-a-l">
					<input id="auto" class="t-a-l va-m" type="checkbox" name="autoLogin">
					<label for="auto" class="va-m">로그인 유지</label>
				</div>
				<!-- // autoLogin -->
				
				<!-- signUp -->
				<button type="button" class="signUp d-ib shadow" onclick="location.href='signupUI.do'">
					회원가입
				</button>
				<!-- // signUp -->
				
				<!-- login -->
				<button class="login d-ib shadow">
					로그인
				</button>
				<!-- // login -->
			</form>
		</div>
	</body>
</html>