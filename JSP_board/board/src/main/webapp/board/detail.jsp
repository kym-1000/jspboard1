<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
     <!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
    <!-- <link rel="stylesheet" href="/css/board.css"> -->
    <style>
        td {
            text-align: left !important;
        }
        
        #wrap{
        	margin: 20px;
        }
        
        .btn{
        	border-color : grey;
		    margin-top: 15px;
		    width: 120px;
		    height: 35px;
		    font-size: 17px;
		}
    </style>
</head>
<body>

	<!-- 게시판 네비 -->
	<jsp:include page="/board/Nlist.jsp"/>
	
	
    <div id="wrap">
        <h2>게시글</h2>
        <form method="post" action="BoardServlet">
        	<input type="hidden" value="board_write" name="command">
            <table class="table table hover table-striped text-center" >
 
                <tr>
                    <th>제목</th>
                    <td>${bvo.title}</td>
                    <th>글쓴이</th>
                    <td>${bvo.writer}</td>
                </tr>

                <tr>
                    <th>등록일</th>
                    <td>${bvo.regdate}</td>
                    <th>조회수</th>
                    <td>${bvo.cnt}</td>
                </tr>
                
                 <tr style="width: 100%">
                    <th>추천수</th>
                    <td>${bvo.recommend}</td>
                 </tr>
                
                <tr>
                    <th id="content">내용</th>
                    <td><textarea cols="100" rows="10" name="content" readonly="readonly" >${bvo.content}</textarea></td>
                    <img alt="" src="/_fileUpLoad/${bvo.image_file}">
                </tr>
            </table>
            <button type="button" class="btn"><a href="/brd/list" style="color: black; text-decoration: none">목록으로</a></button>
           
			<label class="btn btn-primary" for="btn-check-2"><a href="/brd/recommend?bno=${bvo.bno}" style="color: white; text-decoration: none">추천</a></label>
            <c:if test="${ses.email eq bvo.writer}">
            <button type="button" class="btn"><a href="/brd/modify?bno=${bvo.bno}" style="color: black; text-decoration: none">수정</a></button>
            <button type="button" class="btn"><a href="/brd/remove?bno=${bvo.bno}" style="color: black; text-decoration: none">삭제</a></button>
            </c:if>
        </form>
    </div>
    
    
    
    <div>
    
    </div>
    
     <section class="mb-5">
         <div class="card bg-light">
             <div class="card-body">
                 <!-- Comment form-->
                 <div class="mb-4">${ses.email}</div>
                 <div class="mb-4"><textarea class="form-control" rows="3" placeholder="Join the discussion and leave a comment!" id="cmtText"></textarea>
                  <c:if test="${ses.email ne null}"> 
                 <button type="button" id="cmtAddBtn" class="btn btn-primary">댓글등록</button>
                 <input type="hidden" id="cmtWriter" value="${ses.email}">
                 <input type="hidden" id="cmtimage" value="${ses.image_file}">
                 <input type="hidden" id="cmtPwd" value="${ses.pwd}">
                  </c:if> 
                 
                 </div>
                 
                 <!-- Comment with nested comments-->
                 <div id="Comment">
                 
	                 <div class="d-flex" id="Comment">
	                     <!-- Parent comment-->
	                     <div class="flex-shrink-0"><img class="rounded-circle" src="https://dummyimage.com/50x50/ced4da/6c757d.jpg" alt="..." /></div>
	                     <div class="ms-3">
	                         <div class="fw-bold">댓글 작성자</div>
	                         내용
	                     </div>
	                 </div>
                 </div>
                 
                 <!-- <div class="d-flex">
                      <div class="flex-shrink-0"><img class="rounded-circle" src="https://dummyimage.com/50x50/ced4da/6c757d.jpg" alt="..." /></div>
                      <div class="ms-3">
                          <div class="fw-bold">Commenter Name</div>
                          When I look at the universe and all the ways the universe wants to kill us, I find it hard to reconcile that with statements of beneficence.
                      </div>
                  </div> -->
                
             </div>
         </div>
     </section>
    
    <script type="text/javascript">
    	const bnoVal = '<c:out value="${bvo.bno}"/>';
    	console.log(bnoVal);
    	
    </script>
    
 
               </div>
            </div>
        </div>
        
    <script src="/js/board_comment.js">
  
    </script>
    
    <script type="text/javascript">
    
    	printCommentList(bnoVal)  // 맨 밑에 놔!!!!!!
    	
    </script> 
    
</body>
</html>