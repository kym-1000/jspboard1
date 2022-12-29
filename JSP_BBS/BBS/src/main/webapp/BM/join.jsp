<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- CSS only -->

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
</head>
<body>
	<h1>회원가입 페이지입니다~</h1>
	
	<!-- <form action="/bmc/Mregister">
 		아이디(이메일) <input type="text" name="email" placeholder="이메일 입력해주셈"><br>
 		비밀번호 <input type="password" name="pwd" placeholder="비밀번호 입력해주셈"><br>
 		닉네임 <input type="text" name="Nick" placeholder="닉네임 입력해주셈"><br>
 		<button type="submit">회원가입</button>
 	</form> -->
 	
 	<form action="/bmc/Mregister">
  <div class="mb-3">
    <label for="exampleInputEmail1" class="form-label">Email address</label>
    <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="email">
    <div id="emailHelp" class="form-text">이메일(아이디) 입력해주셈</div>
  </div>
  <div class="mb-3">
    <label for="exampleInputPassword1" class="form-label">Password</label>
    <input type="password" class="form-control" id="exampleInputPassword1" name="pwd">
    <div id="emailHelp" class="form-text">비밀번호 입력해주셈</div>
  </div>
  <div class="mb-3">
    <label for="exampleInputPassword1" class="form-label">nickName</label>
    <input type="text" class="form-control" id="exampleInputPassword1" name="Nick">
    <div id="emailHelp" class="form-text">닉네임 입력해주셈</div>
  </div>
  <button type="submit" class="btn btn-primary">회원가입</button>
</form>

</body>
</html>