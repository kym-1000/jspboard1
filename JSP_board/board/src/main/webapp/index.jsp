<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
    <jsp:include page="/comm/indexH.jsp"/>
    <jsp:include page="/comm/indexN.jsp"/>
    
<!-- About-->
        <section class="page-section bg-primary" id="about">
            <div class="container px-4 px-lg-5">
                <div class="row gx-4 gx-lg-5 justify-content-center">
                    <div class="col-lg-8 text-center">
                        <h2 class="text-white mt-0">We've got what you need!</h2>
                        <hr class="divider divider-light" />
                        <p class="text-white-75 mb-4"> ${ses.email}Start Bootstrap has everything you need to get your new website up and running in no time! Choose one of our open source, free to download, and easy to use themes! No strings attached!</p>
                        <a class="btn btn-light btn-xl" href="#services">Get Started!</a>
                    </div>
                </div>
            </div>
        </section>
        <!-- Services-->
        <section class="page-section" id="services">
            <div class="container px-4 px-lg-5">
                <h2 class="text-center mt-0">나의 기술스택</h2>
                <hr class="divider" />
                <div class="row gx-4 gx-lg-5">
                    <div class="col-lg-3 col-md-6 text-center">
                        <div class="mt-5">
                            <!-- <div class="mb-2"><i class="bi-gem fs-1 text-primary"></i></div> -->
                            <img alt=".." src="https://upload.wikimedia.org/wikipedia/en/3/30/Java_programming_language_logo.svg" style="width: 100px; height: 100px">
                            <h3 class="h4 mb-2">JAVA</h3>
                            <p class="text-muted mb-0">Our themes are updated </p>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6 text-center">
                        <div class="mt-5">
                            <img alt=".." src="https://upload.wikimedia.org/wikipedia/en/d/dd/MySQL_logo.svg" style="width: 100px; height: 100px">
                            <h3 class="h4 mb-2">MYSQL</h3>
                            <p class="text-muted mb-0">All dependencies are kept current to keep things fresh.</p>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6 text-center">
                        <div class="mt-5">
                            <img alt=".." src="https://upload.wikimedia.org/wikipedia/commons/6/61/HTML5_logo_and_wordmark.svg" style="width: 100px; height: 100px">
                            <h3 class="h4 mb-2">HTML</h3>
                            <p class="text-muted mb-0">You can use this design as is, or you can make changes!</p>
                        </div>
                    </div>
                    <div class="col-lg-3 col-md-6 text-center">
                        <div class="mt-5">
                           <img alt=".." src="https://upload.wikimedia.org/wikipedia/commons/d/d5/CSS3_logo_and_wordmark.svg" style="width: 100px; height: 100px">
                            <h3 class="h4 mb-2">CSS</h3>
                            <p class="text-muted mb-0">Is it really open source if it's not made with love?</p>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        
        
        
        <jsp:include page="/comm/indexF.jsp" />
        
        <script type="text/javascript">
		const msg_login = '<c:out value="${msg_login}" />';
			if(msg_login === '1'){
				alert('로그인 성공!');
			}
		</script>
		
        <script type="text/javascript">
			const del = '<c:out value="${delete}" />';
				if(del === '1'){
				alert('회원탈퇴 성공!')
				}
		</script>
		
		
 