package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import domain.MemberVO;
import net.coobird.thumbnailator.Thumbnails;
import service.service;
import service.serviceIm;


@WebServlet("/bm/*")
public class memberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static Logger log = LoggerFactory.getLogger(memberController.class);
	
	int isOk;
	private service svc; // 서비스 인터페이스 생성 
	private RequestDispatcher rdp; // 경로를 싣고 보내는 기능을 하는 rdp 변수 선언
	private String destPage = ""; // 경로를 저장할 변수 
	private final String UTF8 = "utf-8";
	private String savePath; // 파일 경로를 저장할 변수
	
	public memberController() {
		svc = new serviceIm();     // 서비스 인터페이스를 구현하는 서비스 
	}
	


	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/html; charset=UTF-8");
		
		String uri = req.getRequestURI();
		log.info(uri);
		String path = uri.substring(uri.lastIndexOf("/")+1);
		log.info(path);
		
		switch (path) {
		case "register":
			try {
				// 파일을 업로드할 물리적인 경로 설정 (업로드 할때 설정)
				savePath = getServletContext().getRealPath("/_fileUpLoad");
				File fileDir = new File(savePath);
				System.out.println("저장위치"+savePath);
				
				DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
				fileItemFactory.setRepository(fileDir); // 저장할 위치를 file 객체로 지정
				fileItemFactory.setSizeThreshold(2*1024*1024); // 저장을 위한 임시 메모리 저장 용량 설정 : byte 단위
				
				MemberVO mvo = new MemberVO();
				// multipart/form-data 형식으로 넘어온 request 객체를 다루기 쉽게 변환 해주는 역할
				ServletFileUpload FileUpload = new ServletFileUpload(fileItemFactory);
				
				List<FileItem> itemList = FileUpload.parseRequest(req);
				for(FileItem item : itemList) {
					switch(item.getFieldName()) {
					case "email" :
						mvo.setEmail(item.getString(UTF8)); // 인코딩 형식을 담아서 변환
						break;
					case "nick" :
						mvo.setNick(item.getString(UTF8));
						break;
					case "pwd" :
						mvo.setPwd(item.getString(UTF8));
						break;
					case "image_file" :
						// 이미지 있는지 없는지 체크
						if(item.getSize() > 0) { // 데이터의 크기를 바이트 단위로 리턴
							String fileName = item.getName()  // 경로를 포함한 파일이름 ~~~~~/dog.jsp
									.substring(item.getName().lastIndexOf("/")+1); // 파일이름만 분리
							// 시스템의 현재시간 _ dog.jsp
							fileName = System.currentTimeMillis()+"_"+fileName;
							File UploadFilePath = new File(fileDir+File.separator+fileName);
							// 파일의 세퍼레이터는 파일을 구분짓는 경로
							System.out.println("파일경로+이름 : "+UploadFilePath);
							// 저장
							try {
								item.write(UploadFilePath); // 자바 객체를 디스크에 쓰기
								mvo.setImage_file(fileName);
								
								// 회원 썸네일 화면 
								Thumbnails.of(UploadFilePath)
								.size(50,50)
								.toFile(new File(fileDir+File.separator+"th_"+fileName));
							} catch (Exception e) {
								System.out.println(">>> file writer on disk fail");
								e.printStackTrace();
							}
							
						}
						
						break;
						
					}
				}
				isOk = svc.Mregister(mvo);
				
				if(isOk > 0 ) {
					log.info("회원가입 성공!");
					destPage = "/";
				}else {
					log.info("회원가입 실패");
					destPage = "/member/join&login.jsp";
				}
				
				destPage = "/";
			} catch (Exception e) {
				log.info("회원가입 예외 발생");
				e.printStackTrace();
			}
			break;
			
		case "login" : 
			
			try {
				log.info("로그인 진입1");
				MemberVO mvo = new MemberVO();
				mvo.setEmail(req.getParameter("email"));
				mvo.setPwd(req.getParameter("pwd"));
				log.info(req.getParameter("pwd"));
				MemberVO mvo1 = svc.login(mvo);
				if(mvo1 != null) {
					log.info("로그인 성공!");
					HttpSession ses = req.getSession(); 
					ses.setAttribute("ses", mvo1);
					ses.setMaxInactiveInterval(10*60);
					req.setAttribute("msg_login", 1);
					destPage = "/";
				}else {
					log.info("로그인 실패!");
					req.setAttribute("msg_login", 0);
					destPage = "/member/join&login.jsp";
				} 
			} catch (Exception e) {
				log.info("로그인 예외 발생");
				e.printStackTrace();
			}
			
			break;
			
		case "logout":
			try {
				HttpSession ses = req.getSession();
				ses.invalidate();
				isOk = svc.lastlogin(req.getParameter("email"));
				if(isOk > 0) {
					log.info("로그아웃완료");
				}else {
					log.info("로그아웃실패");
				}
				destPage = "/";
			} catch (Exception e) {
				log.info("로그아웃 예외 발생");
				e.printStackTrace();
			}
			break;
			
			
		case "update" :
			try {
				log.info("회원수정 진입1");
				String email = req.getParameter("email");
				String pwd = req.getParameter("pwd");
				String nick = req.getParameter("nick");
				MemberVO mvo = new MemberVO(email,pwd,nick);
				isOk = svc.modify(mvo);
				if(isOk > 0) {
					log.info("수정완료");
					destPage = "/";
				}else {
					log.info("수정실패!");
					destPage = "/member/Mmodify&del.jsp";
				}
				
			} catch (Exception e) {
				log.info("회원수정 예외 발생");
				e.printStackTrace();
			}
			
			break;
		case "remove" : 
			try {
				log.info("회원 삭제 진입1");
				String email = req.getParameter("email");
				String pwd = req.getParameter("pwd");
				MemberVO mvo = new MemberVO(email,pwd);
				isOk = svc.remove(mvo);
				if(isOk > 0) {
					log.info("삭제완료");
					HttpSession ses = req.getSession();
					ses.invalidate();
					req.setAttribute("delete", 1);
					destPage = "/";
				}else {
					log.info("삭제실패");
					req.setAttribute("delete", 0);
					destPage = "/member/Mmodify&del.jsp";
				}
			} catch (Exception e) {
				log.info("회원탈퇴 예외 발생");
				e.printStackTrace();
				
			}
			
			break;
			
		default:
			break;
		}
		
		rdp = req.getRequestDispatcher(destPage);
		rdp.forward(req, res);
		
	}


	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		service(req,res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
	}

}
