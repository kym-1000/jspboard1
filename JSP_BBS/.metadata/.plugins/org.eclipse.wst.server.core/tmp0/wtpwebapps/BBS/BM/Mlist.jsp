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

<h1> Member list page </h1>
	
	<table border="1" class="table table-hover table-striped text-center">
		<tr>
			<th>아이디(이메일)</th>	
			<th>닉네임</th>
			<th>가입날짜</th>
		</tr>
		<c:forEach items="${list}" var="mvo" >
		<tr>
			<th>${mvo.email}</a></th>
			<th>${mvo.nick}</th>
			<th>${mvo.reg_at}</th>
		</tr>
		</c:forEach>
	</table>
	
<a href="/"><button type="button" class="btn btn-success">메인화면으로</button></a>

</body>
</html>