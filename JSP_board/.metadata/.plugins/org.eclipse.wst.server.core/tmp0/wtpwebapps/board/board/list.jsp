<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>

	<style type="text/css">
	
		.pagination{
			text-align: center;
			justify-content: center;
			}
	
	</style>


</head>
<body>

	<!-- 게시판 네비 -->	
	<jsp:include page="/board/Nlist.jsp"/>

	
        <h2 style="text-align: center">게시판</h2>

    <table  class="table table hover table-striped text-center"   border="1" style="width: 100%; height: 1cm;">
        <tr>
            <td style="text-align: center; width: 10%">이미지</td>
            <td style="text-align: center; width: 8%">번호</td>
            <td style="text-align: center; width: 40%">제목</td>
            <td style="text-align: center; width: 8%">글쓴이</td>
            <td style="text-align: center; width: 10%">등록일</td>
            <td style="text-align: center; width: 5%">조회수</td>
            <td style="text-align: center; width: 5%">추천수</td>
        </tr>
       <c:forEach items="${list}" var="bvo">
        <tr>
	        <th>
	        <c:if test="${bvo.image_file ne '' && bvo.image_file ne null}">
	        	<img alt="이미지없음" src="/_fileUpLoad/th_${bvo.image_file}">
	        </c:if>
	        </th>
            <th>${bvo.bno}</th>
            <th style="text-align: left;"><a href="/brd/detail?bno=${bvo.bno}">${bvo.title}</a></th>
            <th>${bvo.writer}</th>
            <th>${bvo.regdate}</th>
            <th>${bvo.cnt}</th>
            <th>${bvo.recommend}</th>
        </tr>
       </c:forEach>
       
       </table>
       
       <div style="text-align: center;" aria-label="Page navigation example" id="page">
			  <ul class="pagination" >
			  
			   
			<c:if test="${pgh.pgVo.search eq null}">
			  	<c:if test="${pgh.prev}" >
			       <li class="page-item"><a class="page-link" href="/brd/page?pageNo=${pgh.startPage-1}&qty=${pgh.pgVo.qty}">Previous</a></li>
			   	</c:if>
			   	
			   	<c:forEach begin="${pgh.startPage}" end="${pgh.endPage}" var="i">
			       <li class="page-item"><a class="page-link" href="/brd/page?pageNo=${i}&qty=${pgh.pgVo.qty}">${i}</a></li>
   			 	</c:forEach> 
   			 	
				<c:if test="${pgh.next}">
			       <li class="page-item"><a class="page-link" href="/brd/page?pageNo=${pgh.endPage+1}&qty=${pgh.pgVo.qty}">Next</a></li>
			    </c:if>
			</c:if> 
			   
			   
			 <c:if test="${pgh.pgVo.search ne null}"> 
			   	 <c:if test="${pgh.prev}" >
			   		 <li class="page-item"><a class="page-link" href="/brd/search1?pageNo=${pgh.startPage-1}&qty=${pgh.pgVo.qty}&search=${pgh.pgVo.search}">Previous</a></li>
			   	 </c:if>

			     <c:forEach begin="${pgh.startPage}" end="${pgh.endPage}" var="i">
			    	 <li class="page-item"><a class="page-link" href="/brd/search1?pageNo=${i}&qty=${pgh.pgVo.qty}&search=${pgh.pgVo.search}">${i}</a></li>
   			 	 </c:forEach> 
   			 	
   			 	<c:if test="${pgh.next}">
			         <li class="page-item"><a class="page-link" href="/brd/search1?pageNo=${pgh.endPage+1}&qty=${pgh.pgVo.qty}&search=${pgh.pgVo.search}">Next</a></li>
			    </c:if> 
			</c:if> 
			   			   
			  </ul>
       </div>
       
       <div style="text-align: right;">
       <form action="/brd/search" method="get">
       	검색어 <input type="text" name="search">
       	<button type="submit">검색</button>
       </form>
       
       </div>
       
       
           </div>
            </div>
        </div>
       
      

</body>
</html>