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

		<h1>게시판임</h1>

	<c:if test="${ses.email eq null}">	
			<form action="/bmc/login" method="post">
		  <div class="mb-3">
		    <label for="exampleInputEmail1" class="form-label">Email address</label>
		    <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="email">
		    <div id="emailHelp" class="form-text">당신의 아이디(이메일)을 입력하세요</div>
		  </div>
		  <div class="mb-3">
		    <label for="exampleInputPassword1" class="form-label">Password</label>
		    <input type="password" class="form-control" id="exampleInputPassword1" name="pwd">
		  </div>
		  <div class="mb-3 form-check">
		    <input type="checkbox" class="form-check-input" id="exampleCheck1">
		    <label class="form-check-label" for="exampleCheck1">아이디저장(모양만 있음)</label>
		  </div>
		  <button type="submit" class="btn btn-success">로그인</button>
		</form>
	</c:if>
	
 		<%-- <c:if test="${ses.email eq null}">
		<form action="/bmc/login" method="post">
			id : <input type="text" name="email"><br>
			password : <input type="text" name="pwd"><br>
			<button type="submit">login</button>
		</form>
		</c:if> --%>
		
		<!-- 로그인이 되면 나타날 정보 ne(!= 같지않다.), eq(== 같다)  
		ses.id가 널이 아니라면  -->
		<c:if test="${ses.email ne null}"> 
			로그인 하였습니다! <br>
			<hr class="border border-primary border-1 opacity-75">
			당신의 아이디 ${ses.email}<br>
			<hr class="border border-primary border-1 opacity-75">
			당신의 닉네임 ${ses.nick}<br>
			<hr class="border border-primary border-1 opacity-75">
			당신의 마지막 접속일 ${ses.last_login} <br>
			<hr class="border border-primary border-1 opacity-75">
			<a href="/brd/list1?email=${ses.email}"><button type="button" class="btn btn-success">나의게시글</button></a>
			<a href="/bmc/logout?email=${ses.email}"><button type="button" class="btn btn-warning">로그아웃</button></a>
			<a href="/bmc/modify?email=${ses.email}"><button type="button" class="btn btn-secondary">회원수정</button></a>
			<a href="/bmc/remove?email=${ses.email}"><button type="button" class="btn btn-danger">회원탈퇴</button></a>
			<a href="/brd/rego"><button type="button" class="btn btn-success">게시글등록</button></a> <br>
		</c:if>
		
		<br><br><br>
		
		<a href="/brd/pageList" ><button type="button" class="btn btn-primary">게시판</button></a>
		<a href="/BM/join.jsp" ><button type="button" class="btn btn-secondary">회원가입</button></a>
		<a href="/bmc/list"><button type="button" class="btn btn-primary">회원리스트</button></a>
		
		<script type="text/javascript">
		const msg_login = '<c:out value="${msg_login}" />';
			if(msg_login === '0'){
				alert('로그인 실패');
			}
		</script>
		
		<hr>

</body>
</html>