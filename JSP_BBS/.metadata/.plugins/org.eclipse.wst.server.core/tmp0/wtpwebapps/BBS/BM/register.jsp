<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
</head>
<body>

		<form action="/brd/register" method="post" enctype="multipart/form-data">
		  <div class="mb-3">
		    <label for="exampleInputEmail1" class="form-label">제목</label>
		    <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp"  name="title">
		    <div id="emailHelp" class="form-text">글 제목은 무엇인가</div>
		  </div>
		  <div class="mb-3">
		    <label for="exampleInputPassword1" class="form-label">글쓴이</label>
		    <input type="email" class="form-control" id="exampleInputPassword1" name="writer" value="${ses.email}" readonly="readonly">
		  </div>
		  <div class="mb-3">
		    <label for="exampleInputPassword1" class="form-label">내용</label>
		    <input type="text" class="form-control" id="exampleInputPassword1" name="content">
		  </div>
		  첨부 이미지 파일 <input type="file" name="image_file" id="file"
			 accept="image/png, image/jpg, image/jpeg, image/gif" class="form-control"><br>
		  <button type="submit" class="btn btn-primary">등록</button>
		</form>
			<button type="submit" class="btn btn-warning"><a href="/">취소</a></button>


		
		<%-- <form action="/brd/register" method="post" enctype="multipart/form-data">
			제목 <input type="text" name="title" placeholder="제목을 적으세요"> <br>
			글쓴이 <input type="text" name="writer" readonly="readonly" value="${ses.email}"><br>
			내용 <input type="text" name="content" placeholder="내용을 적으세요"><br>
			image_file <input type="file" name="image_file" id="file"
			 accept="image/png, image/jpg, image/jpeg, image/gif">
			<button type="submit">등록</button>
		</form>
			<button type="submit"><a href="/">취소</a></button> --%>



</body>
</html>