<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0">
		<title>회원가입</title>
		<link rel="stylesheet" href="../css/common_ui.css">
		<link rel="stylesheet" href="../css/loginSignUp.css?after">
		<script src="https://code.jquery.com/jquery-latest.min.js"></script>
		<script>
			// idValue, checkedId 선언
			var idValue = null;
			var checkedId = false;
			
			// 입력된 데이터 유효성 검사
			function checkData() {
				if (!$('#id').val()) {
					alert("아이디를 입력해주세요.");
					$('#id').focus();
					return false;
				}
				
				if (!checkedId || ($('#id').val() != idValue)) {
					alert("아이디 중복확인을 해주세요.");
					return false;
				}
			
				if (!$("#password").val()) {
					alert("비밀번호를 입력해주세요.");
					$('#password').focus();
					return false;
				}
				
				if ($('#password').val() != $('#passwordCheck').val()) {
					alert("비밀번호를 동일하게 입력해주세요.");
					$('#passwordCheck').focus();
					return false;
				}
			}
			
			// id 중복 확인
			function idCheck() {
				// id 유효성 검사
				if (!$('#id').val()) {
					alert("아이디를 입력해주세요");
					$('#id').focus();
					return false;
				}
				
				$.ajax({
					type: "POST",
					url: "idCheck.do",
					data: "id=" + $('#id').val(),
					dataType: "json",
					success: function(data) {
						if (data[0].idCheck) { // id가 중복이면 alert 띄우고 id 입력하는 곳에 커서 두기
							alert("이미 사용중인 아이디입니다.");
							$('#id').val('');
							$('#id').foscus();
						} else { // 사용가능한 id면 alert 띄우고 비밀먼호 입력하는 곳에 커서 두기
							checkedId = true;
							alert("사용 가능한 아이디입니다.");
							idValue = $('#id').val();
							$('#password').focus();
						}
					},
					error: function(request, status, error) {
						alert("code : " + request.status + "\nmessage : " + request.reponseText +"\nerror : " + error + "\n에러가 발생하였습니다.\n관리자에게 문의해보세요.");
					}
				});
			}
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
			<div class="title m-b-lg p-b-m">
			 	회원가입
			</div>
			<!-- // title -->
			
			<form name="signup" method="post" action="signGHInsert.do" onsubmit="return checkData()">
				<input type="hidden" name="next" value="roboSignInsertUI.do">
				
				<!-- id -->
				<div class="labelNick labelsetting t-a-l">
					아이디
				</div>
				<input class="id HF-backWhite b-n shadow p-x-ml i-f-s" type="text" value="${ id }" id="id" name="id">
				<!-- // id -->
				
				<!-- double check -->
				<button type="button" class="double-check HF-Green HF-backWhite b-n shadow m-l-s" onclick="return idCheck()">
					중복확인
				</button>
				<!-- // double check -->
				
				<!-- password -->
				<div class="labelNick labelsetting t-a-l m-t-m">
					비밀번호
				</div>
				<input class="textBox b-n shadow p-x-ml i-f-s" type="password" id="password" name="password">
				<!-- // password -->
				
				<!-- password check -->
				<div class="labelNick labelsetting t-a-l m-t-m">
					비밀번호 확인
				</div>
				<input class="textBox b-n shadow p-x-ml i-f-s" type="password" id="passwordCheck" name="passwordCheck">
				<!-- // password check -->
				
				<!-- next -->
				<button class="next shadow d-b">
					다음
				</button>
				<!-- // next -->
			</form>
		</div>
	</body>
</html>