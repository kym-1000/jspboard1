<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>	
	
		<form action="/brd/update" method="post" enctype="multipart/form-data">
			번호 <input type="text" name="bindex" readonly="readonly" value="${bvo.bindex}"> <br>
			제목 <input type="text" name="title" placeholder="제목을 적으세요" value="${bvo.title}"> <br>
			글쓴이 <input type="text" name="writer" readonly="readonly" value="${bvo.writer}"><br>
			내용 <input type="text" name="content" placeholder="내용을 적으세요" value="${bvo.content}"><br>
			image_file : 
			<input type="hidden" name="image_file" value="${bvo.image_file}">
			<input type="file" name="new_file" accept="image/png, image/jpg, image/jpeg, image/gif">
			<button type="submit">수정</button>
		</form>
			<button type="submit"><a href="/">취소</a></button>

</body>
</html>