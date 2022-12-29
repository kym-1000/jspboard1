<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi" crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-OERcA2EqjJCMA+/3y+gxIOqMEjwtxJY7qPCqsdltbNJuaOe923+mo//f6V8Qbsw3" crossorigin="anonymous"></script>
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
        <h2>작성</h2>
        <form method="post" action="/brd/update" enctype="multipart/form-data">
        	<input type="hidden" value="board_write" name="command">
            <table class="table table hover table-striped text-center" >
            
            	 <tr>
            		<th>번호</th>
            		<td><input type="text" name="bno" value="${bvo.bno}" readonly="readonly"></td>
            	</tr> 
 
                <tr>
                    <th>제목 </th>
                    <td><input type="text" name="title" value="${bvo.title}"></td>
                    <th>아이디 </th>
                    <td><input type="text" name="writer" value="${bvo.writer}" readonly="readonly"></td>
                </tr>

                <tr>
                    <th id="content">내용</th>
                    <td><textarea cols="100" rows="10" name="content">${bvo.content}</textarea></td>
                </tr>
            </table>
            이미지 파일 :
            <input type="hidden" name="image_file" value="${bvo.image_file}">
            <input type="file" name="new_file" accept="image/png, image/jpg, imgae/jpeg, image/gif"><br>
           <button type="submit" class="btn">수정</button>
           <button type="button" class="btn"><a href="/brd/list" style="color: black; text-decoration: none">취소</a></button>
        </form>
    </div>
    
               </div>
            </div>
        </div>




</body>
</html>