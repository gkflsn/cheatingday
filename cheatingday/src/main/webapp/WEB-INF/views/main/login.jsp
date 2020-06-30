<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/aboard/script/valid.js"></script>
<script>
$(function() {
	var msg = "${msg}";
	if(msg!="") {
		$("#alert").text(msg);
		$("#msg").show();
	}

	$("#username").on("blur", checkUsername);
	$("#password").on("blur", checkPassword);
	
	$("#login").on("click", function() {
		if(checkUsername()==false)
			return;
		if(checkPassword()==false)
			return;
		$("#login_frm").submit();
	});
	
	$("#password").on("keypress", function(key) {
		if(key.keyCode!=13)
			return;
		if(checkUsername()==false)
			return;
		if(checkPassword()==false)
			return;
		$("#login_frm").submit();
	});
});
</script>
</head>
<body>
	<div class="alert alert-success alert-dismissible" id="msg" style="display:none;">
    	<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
    	<strong>서버 메시지 </strong><span id="alert"></span>
  	</div>
	<form id="login_frm" action="/aboard/user/login" method="post">
		<div class="form-group">
			<label for="username">아이디</label>
			<input id="username" type="text" name="username" class="form-control">
			<span class="helper-text" id="username_msg"></span>
		</div>
		<div class="form-group">
			<label for="password">비밀번호</label>
			<input id="password" type="password" name="password" class="form-control">
			<span class="helper-text" id="password_msg"></span>
		</div>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
		<button type="button" class="btn btn-success" id="login">로그인</button>
		</form>
</body>
</html>

