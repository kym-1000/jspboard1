<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" 
rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">

<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" 
integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>

</head>
<body >
	<h1>디테일 페이지</h1>
	
	<div>
	<img alt="이미지없음" src="/_fileUpLoad/${bvo.image_file}">
	</div>
	
      <table border="1" class="table table-hover table-striped text-center" >
         <tr>
            <th>bindex</th>
            <td>${bvo.bindex}</td>
         </tr>
         <tr>
            <th>title</th>
            <td>${bvo.title}</td>
         </tr>
         <tr>
            <th>writer</th>
            <td>${bvo.writer}</td>
         </tr>
         <tr>
            <th>regdate</th>
            <td>${bvo.regdate}</td>
         </tr>
         <tr>
            <th>content</th>
            <td>${bvo.content}</td>
         </tr>
         <tr>
            <th>count</th>
            <td>${bvo.read_count}</td>
         </tr>
      </table>
      
    <div>
	comment line<br>
	<input type="text"  id="cmtWriter" value="${ses.email }" readonly="readonly"><br>
	<input type="text"  id="cmtText" placeholder="Add Comment">
	<button type="button" id="cmtAddBtn" class="btn btn-primary">댓글등록</button>		
	</div>
	
	
	
	
      <script type="text/javascript">
		const bindexVal = '<c:out value="${bvo.bindex}"/>';
		</script>

		<script src="/reso/board_detail.js"></script>
	
      <a href="/index.jsp"><button type="button" class="btn btn-primary">메인화면</button></a>
      
      <c:if test="${bvo.writer eq ses.email}">
      	<a href="/brd/modify?bindex=${bvo.bindex}"><button type="button" class="btn btn-warning">수정</button></a> 
      	<a href="/brd/remove?bindex=${bvo.bindex}&image_file=${bvo.image_file}"><button type="button" class="btn btn-danger">삭제</button></a> 
      </c:if>
      
	<div> 
		<!-- 댓글이 나오는 영역 -->
      <div class="accordion" id="accordionExample">
      
      <!-- 댓글 아이템 표시 영역 -->
      <div class="accordion-item">
    <h2 class="accordion-header" id="headingOne">
      <button class="accordion-button" type="button" data-bs-toggle="collapse" 
      data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
        	cno,bindex,writer
      </button>
    </h2>
    <div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
      <div class="accordion-body">
        content, reg_at
      </div>
    </div>
  </div>
	</div>
	
		<script type="text/javascript">
		printCommentList(bindexVal)
		</script>	

</body>
</html>