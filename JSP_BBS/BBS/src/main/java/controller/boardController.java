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

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import domain.BoardVO;
import domain.memberVO;
import domain.pagingVO;
import handler.FileHandler;
import handler.PagingHandler;
import net.coobird.thumbnailator.Thumbnails;
import service.Bservice;
import service.Bserviceim;
import service.service;
import service.serviceim;


@WebServlet("/brd/*")
public class boardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Bservice bvc; // 서비스 인터페이스 생성
	private RequestDispatcher rdp;   // 경로를 싣고 보내는 기능을 하는 변수 선언 
	private String destpage = "";   // 경로를 저장할 변수 
	int isOk;
	private String savePath;  // 파일 경로를 저장할 변수 
	private final String UTF8 = "utf-8"; // 인코딩 설정시 필요함 
	
    public boardController() {
    	bvc = new Bserviceim();
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
		case "rego" :
			destpage = "/BM/register.jsp";
			break;
		case "register" : 
			
			try {
				// 파일을 업로드할 물리적인 경로 설정 (업로드 할때 설정)
				savePath = getServletContext().getRealPath("/_fileUpLoad");
				File fileDir = new File(savePath);
				System.out.println("저장위치"+savePath);
				
				DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
				fileItemFactory.setRepository(fileDir); // 저장할 위치를 file 객체로 지정 
				fileItemFactory.setSizeThreshold(2*1024*1024); // 저장을 위한 임시 메모리 저장 용량 설정 : byte 단위
				
				BoardVO bvo = new BoardVO();
				// multipart/form-data 형식으로 넘어온 request 객체를 다루기 쉽게 변환 해주는 역할
				ServletFileUpload FileUpload = new ServletFileUpload(fileItemFactory);
				
				List<FileItem> itemList = FileUpload.parseRequest(req);
				for(FileItem item : itemList) {
					switch(item.getFieldName()) {
					case "title" :
						bvo.setTitle(item.getString(UTF8)); // 인코딩 형식을 담아서 변환 
						break;
					case "writer" :
						bvo.setWriter(item.getString(UTF8));
						break;
					case "content" :
						bvo.setContent(item.getString(UTF8));
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
								bvo.setImage_file(fileName);
								
								// 썸네일 작업 :  리스트 페이지에서  트래픽 과다사용 방지
								Thumbnails.of(UploadFilePath)
								.size(75,75)
								.toFile(new File(fileDir+File.separator+"th_"+fileName));
							} catch (Exception e) {
								System.out.println(">>> file writer on disk fail");
								e.printStackTrace();
							}
							
						}
						
						break;
						
					}
				}
				isOk = bvc.register(bvo);
				System.out.println(">>>> insert > "+(isOk > 0 ? "ok" : "fail"));
 				
				
//				System.out.println("등록 진입1");
//				BoardVO bvo = new BoardVO();
//				bvo.setTitle(req.getParameter("title"));
//				bvo.setWriter(req.getParameter("writer"));
//				bvo.setContent(req.getParameter("content"));
//				isOk = bvc.register(bvo);
				destpage = "/";
			} catch (Exception e) {
				System.out.println("등록 예외 발생!");
				e.printStackTrace();
			}
			break;

		case "list1":
			try {
				String email = req.getParameter("email");
				pagingVO pgVo = new pagingVO(); 
				pgVo.setEmail(email);
				int totalCount = bvc.getPageCnt1(email);
				System.out.println("게시글 리스트 진입 1");
//				List<BoardVO> list = bvc.getlist1(email);
				List<BoardVO> list = bvc.getListPage1(pgVo);
				System.out.println(list.get(0).getWriter());
				PagingHandler ph = new PagingHandler(pgVo, totalCount);
				req.setAttribute("list", list);
				req.setAttribute("pgh", ph); // 페이지네이션 정보
				destpage ="/BM/list1.jsp";
			} catch (Exception e) {
				System.out.println("게시판 예외 발생!");
				e.printStackTrace();
			}
			break;
			
		case "Mypage" :
			try {
				String email = req.getParameter("email");
				int totalCount = bvc.getPageCnt1(email);
//				List<BoardVO> list = bvc.getlist1(email); // 이메일로 .....
				int pageNo = Integer.parseInt(req.getParameter("pageNo"));
				int qty = Integer.parseInt(req.getParameter("qty"));
				pagingVO pgVo = new pagingVO(pageNo,qty);  
				pgVo.setEmail(email);
				PagingHandler ph = new PagingHandler(pgVo, totalCount);
				List<BoardVO> list = bvc.getListPage1(pgVo);
				System.out.println(pgVo.getQty());
				req.setAttribute("list", list); 
				req.setAttribute("pgh", ph); 
				destpage = "/BM/list1.jsp";
			} catch (Exception e) {
				System.out.println("서브페이지 예외 발생");
			}
			break;
			
		case "pageList":
			try {
				pagingVO pgVo = new pagingVO(); 
				int totalCount = bvc.getPageCnt();
				List<BoardVO> list = bvc.getListPage(pgVo);
				PagingHandler ph = new PagingHandler(pgVo, totalCount);
				req.setAttribute("list", list); // 한 페이지에 나와야 하는 리스트 보내기
				req.setAttribute("pgh", ph); // 페이지네이션 정보
				destpage = "/BM/list.jsp";
			} catch (Exception e) {
				System.out.println("페이지리스트 예외 발생!");
				e.printStackTrace();
			}
			break;
			
		case "page" :
			try {
				int pageNo = Integer.parseInt(req.getParameter("pageNo"));
				int qty = Integer.parseInt(req.getParameter("qty"));
				pagingVO pgVo = new pagingVO(pageNo,qty);  // 바뀐페이지로 값을 가져와라!
				int totalCount = bvc.getPageCnt();
				List<BoardVO> list = bvc.getListPage(pgVo);
				PagingHandler ph = new PagingHandler(pgVo, totalCount);
				req.setAttribute("list", list); // 한 페이지에 나와야 하는 리스트 보내기
				req.setAttribute("pgh", ph); // 페이지네이션 정보
				destpage = "/BM/list.jsp";
			} catch (Exception e) {
				System.out.println("서브페이지 예외 발생");
			}
			break;
			
		case "detail" :
			try {
				// JSP에서 보낸 bindex 받기 
				int bindex =  Integer.parseInt(req.getParameter("bindex"));
				System.out.println("상세 진입1");
				BoardVO bvo1 = bvc.getDetail(bindex);
//				isOk = bvc.updateCount(bvo1);
//				if(isOk > 0) {
//					System.out.println("조회수 상승완료");
//				}else {
//					System.out.println("조회수 상승실패");
//				}
//				BoardVO bvo2 = bvc.getDetail(bindex);
				System.out.println("상세 진입4");
				req.setAttribute("bvo", bvo1);
				destpage = "/BM/detail.jsp";
			} catch (Exception e) {
				System.out.println("상세 예외 발생!");
				e.printStackTrace();
			}
			break;
			
		case "modify":
			try {
				System.out.println("수정 진입 1");
				int bindex =  Integer.parseInt(req.getParameter("bindex"));
				BoardVO bvo2 = bvc.getDetail(bindex);
				req.setAttribute("bvo", bvo2);
				destpage = "/BM/bmodify.jsp";
			} catch (Exception e) {
				System.out.println("수정 예외 발생");
				e.printStackTrace();
			}
			break;
			
			
		case "update" : 
			
		try {
			// 파일을 업로드할 물리적인 경로 설정 (업로드 할때 설정)
			savePath = getServletContext().getRealPath("/_fileUpLoad");
			File fileDir = new File(savePath);
			System.out.println("저장위치"+savePath);
			
			DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
			fileItemFactory.setRepository(fileDir); // 저장할 위치를 file 객체로 지정 
			fileItemFactory.setSizeThreshold(2*1024*1024); // 저장을 위한 임시 메모리 저장 용량 설정 : byte 단위
			
			BoardVO bvo = new BoardVO();
			// multipart/form-data 형식으로 넘어온 request 객체를 다루기 쉽게 변환 해주는 역할
			ServletFileUpload FileUpload = new ServletFileUpload(fileItemFactory);
			System.out.println("업데이트 준비");
			
			List<FileItem> itemList = FileUpload.parseRequest(req);
			
			String old_file = null;
			
			for(FileItem item : itemList) {
				switch(item.getFieldName()) {
				case "bindex" : 
					bvo.setBindex(Integer.parseInt(item.getString(UTF8)));
					break;
				case "title" :
					bvo.setTitle(item.getString(UTF8)); // 인코딩 형식을 담아서 변환 
					break;
				case "content" :
					bvo.setContent(item.getString(UTF8));
					break;
				case "image_file" :
					// old_file 
					old_file = item.getString(UTF8);
					break;
				case "new_file" :
					if(item.getSize() > 0) { // 새로운 파일이 등록 됨
						if(old_file != null) {
							// 올드파일이 널이 아니고 새로운 파일이 등록되면 올드 파일 삭제! 
							// 파일 핸들러 객체를 불러와 삭제!
							FileHandler fileHandler = new FileHandler();
							isOk = fileHandler.deleteFile(old_file,savePath);
						}
						// 경로가 포함된 전체경로와 파일이름 생성 
						String fileName = item.getName().substring(item.getName().lastIndexOf(File.separator)+1); 
						System.out.println(fileName);
						// 실제 저장될 파일이름
						fileName = System.currentTimeMillis()+"_"+fileName;
						// 실제 업로드 되야 하는 파일 경로
						File uploadFilePath = new File(fileDir+File.separator+fileName);
						
						try {
							item.write(uploadFilePath); // 자바 객체를 디스크에 쓰기 
							bvo.setImage_file(fileName);
							System.out.println(">> upload img_file"+(bvo.getImage_file()));
							
							Thumbnails.of(uploadFilePath).size(75, 75)
							.toFile(new File(fileDir+File.separator+"th_"+fileName));
							
						} catch (Exception e) {
							System.out.println(">>> file write on disk fail");
							e.printStackTrace();
						}
						
					}else { // 새로운 파일이 없다면 기존파일을 bvo 객체에 담기 
						bvo.setImage_file(old_file);
					}
					break;
					
				}	
			}
			
			isOk = bvc.update(bvo);
			System.out.println(">>>> update > "+(isOk > 0 ? "ok" : "fail"));
			
			destpage = "/";
		} catch (Exception e) {
			System.out.println("업데이트 예외 발생!");
			e.printStackTrace();
		}
		break;	
			
//		case "update":
//			try {
//				System.out.println("업데이트 진입 1");
//				BoardVO bvo2 = new BoardVO();
//				int bindex =  Integer.parseInt(req.getParameter("bindex"));
//				System.out.println(bindex);
//				bvo2.setBindex(bindex);
//				bvo2.setTitle(req.getParameter("title"));
//				bvo2.setWriter(req.getParameter("writer"));
//				bvo2.setContent(req.getParameter("content"));
//				isOk = bvc.update(bvo2);
//				if(isOk>0) {
//					System.out.println("업데이트 성공");
//				}else {
//					System.out.println("업데이트 실패");
//				}
//				destpage = "pageList";
//			} catch (Exception e) {
//				System.out.println("업데이트 예외 발생!");
//				e.printStackTrace();
//			}
//			break;
			
		case "remove" : 
			try {
				System.out.println("삭제 진입 1");
				int bindex =  Integer.parseInt(req.getParameter("bindex"));
//				String image_file = req.getParameter("image_file");
//				System.out.println(image_file);
				String imageFileName = bvc.getFileName(bindex); // 삭제할 파일 이름 호출
				FileHandler fileHandler = new FileHandler();
				savePath = getServletContext().getRealPath("/_fileUpLoad");
				isOk = fileHandler.deleteFile(imageFileName, savePath);
				isOk = bvc.remove(bindex);
				// image_file Name 불러오기
				destpage = "/";
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
