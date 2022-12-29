package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.management.ClassLoadingMXBean;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.CommentVO;
import service.CommentService;
import service.CommentServiceImpl;

@WebServlet("/cmt/*")
public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(CommentController.class);
    private CommentService csv;
    private int isOk;
	
	
    public CommentController() {
        csv = new CommentServiceImpl();
    }

	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		
		String uri = req.getRequestURI(); // /cmt/list/10 
		String pathUri = uri.substring("/cmt/".length());
		String path = pathUri;
		String pathVar = null;
		if(pathUri.contains("/")) {
			path = pathUri.substring(0, pathUri.lastIndexOf("/")); //list
			pathVar = pathUri.substring(pathUri.lastIndexOf("/")+1); //10
		}
		
		log.info(">>> uri >> "+uri);
		log.info(">>> pathUri >> "+pathUri);
		log.info(">>> path >> "+path);
		log.info(">>> pathVar >> "+pathVar);
		
		switch (path) {
		case "post":
			try {
				// js에서 보낸 데이터를 StringBuffer로 읽어들이는 작업
				log.info("post 첫번째!!");
				StringBuffer sb = new StringBuffer();
				String line = null;
				BufferedReader br = req.getReader(); // 댓글 객체  입력
				while ((line = br.readLine())!=null) { // 값이 널이 아니라면 (값이 남아있다면)
					sb.append(line);
				}
				log.info(">>> sb"+ sb.toString());
				// 객체로 생성 
				JSONParser parser = new JSONParser();
				// 파서는 구문해석을 위해서 사용되는 프로그램(객체?)
				// parser란 compiler의 일부로 컴파일러나 
				// 인터프리터에서 원시 프로그램을 읽어 들여 그 문장의 구조를 알아내는 parsing(구문 분석)을 행하는 프로그램
				JSONObject jsonObj = (JSONObject) parser.parse(sb.toString());
				// key = value 
				int bindex = Integer.parseInt(jsonObj.get("bindex").toString());// return타입 obj
				String writer = jsonObj.get("writer").toString();
				String content = jsonObj.get("content").toString();
				isOk = csv.post(new CommentVO(bindex,writer,content));
				
				PrintWriter out = res.getWriter();  
				out.print(isOk); // 출력 js에서 받아짐
			} catch (Exception e) {
				log.info(" >>> Comment > post > error");
				e.printStackTrace();
			}
			break;
		case "list":
			try {
				List<CommentVO> list = csv.getList(Integer.parseInt(pathVar));
				log.info(">>> Comment > list > db성공");
				// json 형태로 변환
				JSONObject[] jsonObjArr = new JSONObject[list.size()];
				JSONArray jsonObjList = new JSONArray();
				
				for(int i=0; i<list.size(); i++) {
					jsonObjArr[i] = new JSONObject(); // key : value
					jsonObjArr[i].put("cno", list.get(i).getCno());    // cno : 1  
					jsonObjArr[i].put("bindex", list.get(i).getBindex());
					jsonObjArr[i].put("writer", list.get(i).getWriter());
					jsonObjArr[i].put("content", list.get(i).getContent());
					jsonObjArr[i].put("reg_at", list.get(i).getReg_at());
			
					jsonObjList.add(jsonObjArr[i]); // 객체 하나 만들어서 리스트에 추가
				}
				
				String jsonData = jsonObjList.toJSONString();   // 존슨데이터로 변환
				
				PrintWriter out = res.getWriter();
				out.print(jsonData); // 화면에 출력해주는 역할!
				
			} catch (Exception e) {
				log.info(" >>> Comment > list > error");
				e.printStackTrace();
			}
			
			break;
			
		case "remove":
			try {
				isOk = csv.remove(Integer.parseInt(pathVar));
				log.info(isOk > 0 ? "ok" : "fail");
				PrintWriter out = res.getWriter();  
				out.print(isOk); // 출력 js에서 받아짐
			} catch (Exception e) {
				log.info(" >>> Comment > remove > error");
				e.printStackTrace();
			}
			break;
			
		case "update":
			try {
				// js에서 보낸 데이터를 StringBuffer로 읽어들이는 작업
				log.info("post 첫번째!!");
				StringBuffer sb = new StringBuffer();
				String line = null;
				BufferedReader br = req.getReader(); // 댓글 객체  입력
				while ((line = br.readLine())!=null) { // 값이 널이 아니라면 (값이 남아있다면)
					sb.append(line);
				}
				log.info(">>> sb"+ sb.toString());
				// 객체로 생성 
				JSONParser parser = new JSONParser();  
				//	파서는 구문해석을 위해서 사용되는 프로그램(객체?)
				// parser란 compiler의 일부로 컴파일러나 
				// 인터프리터에서 원시 프로그램을 읽어 들여 그 문장의 구조를 알아내는 parsing(구문 분석)을 행하는 프로그램
				JSONObject jsonObj = (JSONObject) parser.parse(sb.toString()); 
				// 일반 오프젝트 값을 해석하여 존슨오프젝트 값으로 형변환
				// key = value 
				int cno = Integer.parseInt(jsonObj.get("cno").toString());// return타입 obj
				String content = jsonObj.get("content").toString();
				
				isOk = csv.update(new CommentVO(cno,content));
				log.info(isOk > 0 ? "ok" : "fail");
				PrintWriter out = res.getWriter();  
				out.print(isOk); // 출력 js에서 받아짐
			} catch (Exception e) {
				log.info(" >>> Comment > update > error");
				e.printStackTrace();
			}
			break;
		}
		
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		service(request, response);
	}

}
