<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
		<title>로그인</title>
		<link rel="stylesheet" href="../css/common_ui.css">
		<link rel="stylesheet" href="../css/loginSignUp.css">
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script type="text/javascript">
			
		</script>
	</head>
	<body>
		<div class="wrap">
			<!-- title -->
			<div class="sign-title">
			 	로그인
			</div>
			<!-- // title -->
			
			<form name="login" method="post" action="login.do">
				<!-- id -->
				<div class="labelNick labelsetting t-a-l">
					아이디
				</div>
				<input class="textBox b-n shadow p-x-ml" type="text" name="id">
				<!-- // id -->
				
				<!-- password -->
				<div class="labelNick labelsetting t-a-l m-t-m">
					비밀번호
				</div>
				<input class="textBox b-n shadow p-x-ml" type="text" name="password">
				<!-- // password -->
				
				<!-- autoLogin -->
				<div id="autoLogin" class="m-b-lg p-b-sl t-a-l">
					<input id="auto" class="t-a-l va-m" type="checkbox" name="autoLogin">
					<label for="auto" class="va-m">로그인 유지</label>
				</div>
				<!-- // autoLogin -->
				
				<!-- signUp -->
				<button class="signUp d-ib shadow" onclick="siginupUI.do">
					회원가입
				</button>
				<!-- // signUp -->
				
				<!-- login -->
				<input type="submit" class="login d-ib shadow"  value="로그인">
				<!-- // login -->
			</form>
		</div>
	</body>
</html>