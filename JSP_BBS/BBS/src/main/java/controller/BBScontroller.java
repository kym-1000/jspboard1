package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.memberVO;
import service.service;
import service.serviceim;


@WebServlet("/bmc/*")
public class BBScontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	int isOk;
	private service svc; // 서비스 인터페이스 생성
	private RequestDispatcher rdp;   // 경로를 싣고 보내는 기능을 하는 변수 선언 
	private String destpage = "";   // 경로를 저장할 변수 
       
    
	
    public BBScontroller() {
        svc = new serviceim();s
    }

	
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=UTF-8");
		
		String uri = req.getRequestURI();
		System.out.println(uri);
		String path = uri.substring(uri.lastIndexOf("/")+1);
		System.out.println(path);
		
		switch (path) {
		case "join":
			destpage ="/BM/join.jsp";
			break;
		case "Mregister": 
			try {
				System.out.println("회원가입 진입1");
				memberVO mvo = new memberVO();
				mvo.setEmail(req.getParameter("email"));
				mvo.setPwd(req.getParameter("pwd"));
				mvo.setNick(req.getParameter("Nick"));
				isOk = svc.Mregister(mvo);
				if(isOk>0) {
					System.out.println("회원가입 성공!");
				}else {
					System.out.println("회원가입 실패!");
				}
				destpage ="/";
			} catch (Exception e) {
				System.out.println("회원가입 예외발생!");
				e.printStackTrace();
			}
			break;
			
		case "login" :
			System.out.println("로그인 진입 1");
//			LocalTime now = LocalTime.now();
			// 아이디와 패스워드가 일치하는 객체가 있다면 그 객체를 가져옴
			memberVO mvo = new memberVO();
			mvo.setEmail(req.getParameter("email"));
			mvo.setPwd(req.getParameter("pwd"));
			memberVO mvo1= svc.login(mvo);
			System.out.println("로그인 OK");
			if(mvo1 != null) { // 객체를 가져왔다면
				// 로그인 정보는 세션에 담아서 보냄
				// 현재 연결되어 있는 세션이 있다면 해당 세션을 유지 없다면 새로 생성
				HttpSession ses = req.getSession();
				ses.setAttribute("ses", mvo1);
				// 로그인 유지시간 
				ses.setMaxInactiveInterval(10*60); // 초단위 10분간 유지
			}else {
				req.setAttribute("msg_login", 0);
			}
			destpage="/";
			break;
		case "logout":
			HttpSession ses = req.getSession(); 
			// 현재 연결된 세션(로그인된 정보) 가져오기
			ses.invalidate(); // 세션 무효화 (세션 종료)
			isOk = svc.lastlogin(req.getParameter("email"));
			destpage = "/";
			break;
			
		case "list":
			try {
				System.out.println("회원 리스트 진입 1");
				List<memberVO> list = svc.getlist();
				req.setAttribute("list", list);
				destpage = "/BM/Mlist.jsp";
			} catch (Exception e) {
				System.out.println("회원리스트 예외 발생!");
			}
			break;
			
		case "modify":
			try {
				System.out.println("수정 진입 1");
				String email = req.getParameter("email");
				memberVO mvo2 = svc.getDetail(email);
				req.setAttribute("mvo", mvo2);
				destpage = "/BM/modify.jsp";
			} catch (Exception e) {
				System.out.println("수정 예외 발생");
				e.printStackTrace();
			}
			break;
			
		case "update":
			try {
				System.out.println("업데이트 진입 1");
				memberVO mvo2 = new memberVO();
				mvo2.setEmail(req.getParameter("email"));
				mvo2.setPwd(req.getParameter("password"));
				mvo2.setNick(req.getParameter("nick"));
				isOk = svc.update(mvo2);
				if(isOk>0) {
					System.out.println("업데이트 성공");
				}else {
					System.out.println("업데이트 실패");
				}
				destpage = "list";
			} catch (Exception e) {
				System.out.println("업데이트 예외 발생!");
				e.printStackTrace();
			}
			break;
			
		case "remove" : 
			try {
				System.out.println("삭제 진입 1");
				String email = req.getParameter("email");
				isOk = svc.remove(email);
				destpage = "list";
			} catch (Exception e) {
				System.out.println("삭제 예외 발생!");
				e.printStackTrace();
			}
			break;
		}
		
		rdp = req.getRequestDispatcher(destpage);
		// 주소를 싣고 목적지로 가라!
		rdp.forward(req, res);
		
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		service(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
	}

}