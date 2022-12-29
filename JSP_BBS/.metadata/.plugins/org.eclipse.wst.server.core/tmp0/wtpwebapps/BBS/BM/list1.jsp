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
	<h1 style="text-align: center" > 게시글 입니당</h1>
	
	<table class="table table-hover table-striped text-center" border="1">
		<tr>
			<th>이미지</th>	
			<th>번호</th>	
			<th>제목</th>
			<th>이메일(아이디)</th>
			<th>조회수</th>
		</tr>
		<c:forEach items="${list}" var="bvo" >
		<tr>
			<td>
			<c:if test="${bvo.image_file ne '' && bvo.image_file ne null}">
			<img alt="이미지없음" src="/_fileUpLoad/th_${bvo.image_file}">
			</c:if>
			<th><a href="/brd/detail?bindex=${bvo.bindex}">${bvo.bindex}</a></th>
			</td>
			<th>${bvo.title}</th>
			<th>${bvo.writer}</th>
			<th>${bvo.read_count}</th>
		</tr>
		</c:forEach>
	</table>
	
	<div>
		
		<c:if test="${pgh.prev}">
		<a href="/brd/Mypage?pageNo=${pgh.startPage-1}&qty=${pgh.pgVo.qty}&email=${ses.email}">P</a>
		</c:if>

		<c:forEach begin="${pgh.startPage}" end="${pgh.endPage}" var="i">
				<a href="/brd/Mypage?pageNo=${i}&qty=${pgh.pgVo.qty}&email=${ses.email}">${i}</a>
		</c:forEach>
		<c:if test="${pgh.next}">
		<a href="/brd/Mypage?pageNo=${pgh.endPage+1}&qty=${pgh.pgVo.qty}&email=${ses.email}">N</a>
		</c:if>
		
		
	</div>
	
	
<a href="/index.jsp">메인화면으로</a>

</body>
</html>