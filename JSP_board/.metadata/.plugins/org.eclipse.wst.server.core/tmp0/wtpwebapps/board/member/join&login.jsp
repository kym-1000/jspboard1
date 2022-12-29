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
      <form action="/bm/register" enctype="multipart/form-data" method="post">
        <h1>Create Account</h1>
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
        <span>or use your email for registration</span>
        <input type="text" placeholder="Nick"  name="nick">
        <input type="email" placeholder="Email" name="email">
        <input type="password" placeholder="Password" name="pwd">
        <input type="file" name="image_file" id="file"
			 accept="image/png, image/jpg, image/jpeg, image/gif" class="form-control"><br>
        <button class="form_btn">Sign Up</button>
      </form>
    </div>
    <div class="sign-in-container">
      <form action="/bm/login">
        <h1>Sign In</h1>
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
        <span>or use your account</span>
        <input type="email" placeholder="Email" name="email">
        <input type="password" placeholder="Password" name="pwd">
        <button class="submit">Sign In</button>
      </form>
    </div>
    <div class="overlay-container">
      <div class="overlay-left">
        <h1>Welcome Back</h1>
        <p>To keep connected with us please login with your personal info</p>
        <button id="signIn" class="overlay_btn">Sign In</button>
      </div>
      <div class="overlay-right">
        <h1>Hello, Friend</h1>
        <p>Enter your personal details and start journey with us</p>
        <button id="signUp" class="overlay_btn">Sign Up</button>
      </div>
    </div>
  </div>
</div>



 <script type="text/javascript" src="/js/join&login.js"></script>
 
 <script type="text/javascript">
		const msg_login = '<c:out value="${msg_login}" />';
			if(msg_login === '0'){
				alert('로그인 실패 아이디와 비밀번호를 확인하세요!');
			}
		</script>
 
 
 

</body>
</html>