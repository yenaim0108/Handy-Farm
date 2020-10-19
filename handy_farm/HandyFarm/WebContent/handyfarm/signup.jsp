<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
		<title>회원가입</title>
		<link rel="stylesheet" href="../css/common_ui.css">
		<link rel="stylesheet" href="../css/loginSignUp.css">
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	</head>
	<body>
		<div class="wrap">
			<!-- title -->
			<div class="sign-title">
			 	회원가입
			</div>
			<!-- // title -->
			
			<form name="login" method="post" action="signup.do">
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
				
				<!-- next -->
				<input type="submit" class="next d-ib shadow"  value="다음">
				<!-- // next -->
			</form>
		</div>
	</body>
</html>