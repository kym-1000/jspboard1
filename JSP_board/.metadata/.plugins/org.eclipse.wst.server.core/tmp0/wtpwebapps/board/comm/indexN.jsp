<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

 <!-- Navigation-->
 		
        <nav class="navbar navbar-expand-lg navbar-light fixed-top py-3" id="mainNav">
            <div class="container px-4 px-lg-5">
                <a class="navbar-brand" href="#page-top">나의 게시판</a>
                <button class="navbar-toggler navbar-toggler-right" type="button" data-bs-toggle="collapse" data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarResponsive">
                    <ul class="navbar-nav ms-auto my-2 my-lg-0">
                  
                  		<c:if test="${ses.email eq null}">
                        <li class="nav-item"><a class="nav-link" href="/member/join&login.jsp">로그인</a></li>
                  		</c:if>
                  		
                  		<c:if test="${ses.email ne null}">
                  		<li class="nav-item" style="color: white;"><img alt="이미지없음" src="/_fileUpLoad/th_${ses.image_file}">${ses.nick}</li>
                  		<li class="nav-item"><a class="nav-link" href="/bm/logout?email=${ses.email}">로그아웃</a></li>
                  		<li class="nav-item"><a class="nav-link" href="/member/Mmodify&del.jsp?email=${ses.email}">회원수정&탈퇴</a></li>
                  		</c:if>
                        
                        <li class="nav-item"><a class="nav-link" href="/brd/list">게시글보기</a></li>
                        <li class="nav-item"><a class="nav-link" href="#services">나의 기술스택</a></li>
                    </ul>
                </div>
            </div>
        </nav>

