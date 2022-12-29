<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link href="/css/join&login.css" rel="stylesheet" type="text/css" />
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 
<div class="wrapper">
  <div class="container">
    <div class="sign-up-container">
      <form action="/bm/update">
        <h1>회원정보 수정</h1>
        <div class="social-links">
          <div>
            <a href="#"><i class="fa fa-facebook" aria-hidden="true"></i></a>
          </div>
          <div>
            <a href="#"><i class="fa fa-twitter" aria-hidden="true"></i></a>
          </div>
          <div>
            <a href="#"><i class="fa fa-linkedin" aria-hidden="true"></i></a>
          </div>
        </div>
        <span>당신의 정보를 수정하실껀가요?</span>
        <input type="text" placeholder="Nick"  name="nick" value="${ses.nick}">
        <input type="email" placeholder="Email" name="email" value="${ses.email}" readonly="readonly">
        <input type="password" placeholder="Password" name="pwd" value="${ses.pwd}">
        <button class="form_btn">수정!</button>
      </form>
    </div>
    <div class="sign-in-container">
      <form action="/bm/remove">
        <h1>회원탈퇴</h1>
        <div class="social-links">
          <div>
            <a href="#"><i class="fa fa-facebook" aria-hidden="true"></i></a>
          </div>
          <div>
            <a href="#"><i class="fa fa-twitter" aria-hidden="true"></i></a>
          </div>
          <div>
            <a href="#"><i class="fa fa-linkedin" aria-hidden="true"></i></a>
          </div>
        </div>
        <span>회원탈퇴 하실껀가요?</span>
        <input type="email" placeholder="Email" name="email" value="${ses.email}">
        <input type="password" placeholder="Password" name="pwd">
        <button class="submit">회원탈퇴!</button>
      </form>
    </div>
    <div class="overlay-container">
      <div class="overlay-left">
        <h1>아이쿠! </h1>
        <p>무엇이 바뀔까?</p>
        <button id="signIn" class="overlay_btn">삭제할껀데?</button>
      </div>
      <div class="overlay-right">
        <h1>잘가시게!!</h1>
        <p>언젠가는 또 만나기를</p>
        <button id="signUp" class="overlay_btn">수정할껀데?</button>
      </div>
    </div>
  </div>
</div>



 <script type="text/javascript" src="/js/join&login.js"></script>
 
 <script type="text/javascript">
			const del = '<c:out value="${delete}" />';
				if(del === '0'){
				alert('회원탈퇴 실패 아이디와 비밀번호를 확인하세요!')
				}
		</script>
 
 
 

</body>
</html>