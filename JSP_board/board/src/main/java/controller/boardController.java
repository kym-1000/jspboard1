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

import domain.BoardVO;
import domain.pagingVO;
import handler.FileHandler;
import handler.PagingHandler;
import net.coobird.thumbnailator.Thumbnails;
import service.Bservice;
import service.BserviceIm;


@WebServlet("/brd/*")
public class boardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static Logger log = LoggerFactory.getLogger(memberController.class);
	
	private Bservice bvc; // 서비스 인터페이스 생성 
	private RequestDispatcher rdp; // 
	private String destPage = ""; // 경로를 저장할 변수
	private String savePath; // 파일 경로를 저장할 변수
	private final String UTF8 = "utf-8"; 
	private int isOk;
	
	
    public boardController() {
        bvc = new BserviceIm();  // 서비스 구현 클래스 생성
    }
    
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding(UTF8);
		res.setCharacterEncoding(UTF8);
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
				
//					log.info(" 게시글 등록 진입1");
//					BoardVO bvo = new BoardVO();
//					bvo.setTitle(req.getParameter("title"));
//					bvo.setWriter(req.getParameter("writer"));
//					bvo.setContent(req.getParameter("content"));
//					isOk = bvc.register(bvo);
//					if(isOk > 0 ) {
//						log.info("게시글 등록완료");
//						
//					}else {
//						log.info("게시글 등록실패");
//						
//					}
				
				destPage = "list";
				
			} catch (Exception e) {
				log.info("게시글 등록 예외 발생");
				e.printStackTrace();
			}
			
			break;
			
		case "list" : 
			try {
				log.info("게시글 목록 불러오기");
				pagingVO pgVo = new pagingVO();
				int totalCount = bvc.getPageCnt();
				List<BoardVO> list = bvc.getPageList(pgVo);
				PagingHandler ph = new PagingHandler(pgVo,totalCount);
				req.setAttribute("list", list); // 한페이지에 나와야 하는 리스트 보내기
				req.setAttribute("pgh", ph);	// 페이지네이션 정보
				destPage = "/board/list.jsp";
			} catch (Exception e) {
				log.info("게시판 예외 발생");
				e.printStackTrace();
			}
			
			break;
			
		case "page" : 
			try {
				log.info("페이지 불러오기");
				int pageNo = Integer.parseInt(req.getParameter("pageNo"));
				int qty = Integer.parseInt(req.getParameter("qty"));
				pagingVO pgVo = new pagingVO(pageNo,qty);
				int totalCount = bvc.getPageCnt();
				List<BoardVO> list = bvc.getPageList(pgVo);
				PagingHandler ph = new PagingHandler(pgVo,totalCount);
				req.setAttribute("list", list); // 한페이지에 나와야 하는 리스트 보내기
				req.setAttribute("pgh", ph);	// 페이지네이션 정보
				destPage = "/board/list.jsp";
			} catch (Exception e) {
				log.info("페이지 예외 발생");
				e.printStackTrace();
			}
			
			break;
			
			// 검색어 페이징 초기화면 
		case "search" : 
			try {
				String searchTitle = req.getParameter("search");
				System.out.println(searchTitle);
				pagingVO pgVo = new pagingVO();
				pgVo.setSearch(searchTitle);
				System.out.println(pgVo.getSearch());
				int sertotalCount = bvc.getSerPageCnt(searchTitle);
				System.out.println(sertotalCount);
				List<BoardVO> list = bvc.getSerachList(pgVo);
				PagingHandler ph = new PagingHandler(pgVo,sertotalCount);
				req.setAttribute("list", list);
				req.setAttribute("pgh", ph);	
				destPage = "/board/list.jsp";
			} catch (Exception e) {
				log.info("페이지 예외 발생");
				e.printStackTrace();
			}
			
			break;
			
		
		// 검색어에 맞는 페이징 작업	
		case "search1" : 
			try {
				int pageNo = Integer.parseInt(req.getParameter("pageNo"));
				int qty = Integer.parseInt(req.getParameter("qty"));
				 
				String searchTitle = req.getParameter("search");
				System.out.println(searchTitle);
				pagingVO pgVo = new pagingVO(pageNo,qty);
				pgVo.setSearch(searchTitle);
				System.out.println(pgVo.getSearch());
				int sertotalCount = bvc.getSerPageCnt(searchTitle);
				System.out.println(sertotalCount);
				List<BoardVO> list = bvc.getSerachList(pgVo);
				PagingHandler ph = new PagingHandler(pgVo,sertotalCount);
				req.setAttribute("list", list);
				req.setAttribute("pgh", ph);	
				destPage = "/board/list.jsp";
			} catch (Exception e) {
				log.info("페이지 예외 발생");
				e.printStackTrace();
			}
			
			break;
			
		case "detail" :
			try {
				log.info("상세페이지 진입1 ");
				int bno = Integer.parseInt(req.getParameter("bno"));
				BoardVO bvo = bvc.getDetail(bno);
				req.setAttribute("bvo", bvo);
				destPage = "/board/detail.jsp";
			} catch (Exception e) {
				log.info("상세페이지 예외 발생");
				e.printStackTrace();
			}
			
			break;
			
		case "modify" :
			try {
				log.info("수정페이지 진입1");
				int bno = Integer.parseInt(req.getParameter("bno"));
				System.out.println("수정"+bno);
				BoardVO bvo = bvc.getDetail(bno);
				req.setAttribute("bvo", bvo);
				destPage = "/board/modify.jsp";
				
			} catch (Exception e) {
				log.info("수정페이지 예외 발생");
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
					case "bno" :
						bvo.setBno(Integer.parseInt(item.getString(UTF8)));
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
				
				destPage = "/";
			} catch (Exception e) {
				log.info("업데이트 예외 발생");
				e.printStackTrace();
			}
			
			break;
			
			
		case "remove" : 
			try {
				log.info("게시글 삭제 진입");
				int bno = Integer.parseInt(req.getParameter("bno"));
				String imageFileName = bvc.getFileName(bno);
				FileHandler fileHandler = new FileHandler();
				savePath = getServletContext().getRealPath("/_fileUpLoad");
				isOk = fileHandler.deleteFile(imageFileName, savePath);
				isOk = bvc.remove(bno);
				destPage = "/";
			} catch (Exception e) {
				log.info("업데이트 예외 발생");
				e.printStackTrace();
			}
			
			break;
			
		case "recommend" : 
			try {
				log.info("추천 진입1");
				int bno = Integer.parseInt(req.getParameter("bno"));
				isOk = bvc.recommendUp(bno);
				destPage = "list";
			} catch (Exception e) {
				log.info("추천 예외 발생");
				e.printStackTrace();
			}
			
			break;
			
//		case "recoList" :
//			try {
//				log.info("추천 정렬 진입1");
//				pagingVO pgVo = new pagingVO();
//				pgVo.setSearch(searchTitle);
//				System.out.println(pgVo.getSearch());
//				int totalCount = bvc.getPageCnt();
//				System.out.println(totalCount);
//				List<BoardVO> list = bvc.getSerachList(pgVo);
//				PagingHandler ph = new PagingHandler(pgVo,totalCount);
//				req.setAttribute("list", list);
//				req.setAttribute("pgh", ph);	
//				destPage = "/board/list.jsp";
//			} catch (Exception e) {
//				log.info("추천 정렬 예외 발생");
//				e.printStackTrace();
//			}
//				
//			break;
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
