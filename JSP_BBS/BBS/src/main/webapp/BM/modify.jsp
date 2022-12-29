<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@
5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" 
integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@
5.2.2/dist/js/bootstrap.bundle.min.js" 
integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
</head>
<body>

	<<%-- form action="/bmc/update" method="post" >
	 	email : <input type="text" name="email" value="${mvo.email}" readonly="readonly"><br>
	 	password : <input type="text" name="pwd" value="${mvo.pwd}" readonly="readonly"><br>
	 	nick : <input type="text" name="nick" value="${mvo.nick}"><br>
	 	<button type="submit">수정완료</button>
 	</form>
 	
 	<button type="submit"><a href="/">취소</a></button> --%>
 	
 	
 	
 	<form action="/bmc/update" method="post">
  <div class="mb-3">
    <label for="exampleInputEmail1" class="form-label">Email address</label>
    <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="email" value="${mvo.email}" readonly="readonly">
    <div id="emailHelp" class="form-text">밑에 닉네임만 수정가능함!</div>
  </div>
  <div class="mb-3">
    <label for="exampleInputPassword1" class="form-label">Password</label>
    <input type="password" class="form-control" id="exampleInputPassword1" name="pwd" value="${mvo.pwd}" readonly="readonly">
  </div>
  <div class="mb-3">
    <label for="exampleInputPassword1" class="form-label">nick</label>
    <input type=text class="form-control" id="exampleInputPassword1" name="nick" value="${mvo.nick}">
  </div>
 
  <button type="submit" class="btn btn-primary">수정완료</button>
</form>
  <button type="submit" class="btn btn-warning"><a href="/">취소</a></button>
	


</body>
</html>