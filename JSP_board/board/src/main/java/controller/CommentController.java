package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
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
import service.CommentServiceim;


@WebServlet("/CMT/*")
public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static Logger log = LoggerFactory.getLogger(memberController.class);
	
	private CommentService csv; // 댓글 서비스 인터페이스
	private int isOk;
       
    
    public CommentController() {
    	csv = new CommentServiceim(); // 댓글 서비스 인터페이스 구현
    }

	
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		
		String uri = req.getRequestURI();
		String pathUri = uri.substring("/CMT/".length());
		String path = pathUri;
		String pathVar = null;
		if(pathUri.contains("/")) {
			path = pathUri.substring(0,pathUri.lastIndexOf("/"));   // list
			pathVar = pathUri.substring(pathUri.lastIndexOf("/")+1); // 10
		}
		
		System.out.println(path+" "+pathVar);
		
		
		switch (path) {
		case "post":
			try {
				// js에서 보낸 데이터를 스트링버퍼로 읽어들이는 과정
				log.info("post 첫번째!");
				StringBuffer sb = new StringBuffer();
				String line = null;
				BufferedReader br = req.getReader();
				while((line = br.readLine())!=null) {
					sb.append(line);
				}
				log.info(sb.toString());
				// 객체로 생성 
				JSONParser parser = new JSONParser();
				JSONObject jsonObj = (JSONObject) parser.parse(sb.toString());
				
				int bno = Integer.parseInt(jsonObj.get("bno").toString()); 
				String writer = jsonObj.get("writer").toString();
				String content = jsonObj.get("content").toString();
				String image_file = jsonObj.get("image_file").toString();
				String pwd = jsonObj.get("pwd").toString();
				System.out.println(bno+" "+writer+" "+content);
				isOk = csv.post(new CommentVO(bno,writer,content,image_file,pwd));
				
				PrintWriter out = res.getWriter();
				out.print(isOk); // 출력 js에서 받아짐 
			} catch (Exception e) {
				log.info("post 예외 발생!");
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
					jsonObjArr[i].put("bno", list.get(i).getBno());
					jsonObjArr[i].put("writer", list.get(i).getWriter());
					jsonObjArr[i].put("content", list.get(i).getContent());
					jsonObjArr[i].put("reg_at", list.get(i).getReg_at());
					jsonObjArr[i].put("image_file", list.get(i).getImage_file());
					jsonObjArr[i].put("pwd", list.get(i).getPwd());
			
					jsonObjList.add(jsonObjArr[i]); // 객체 하나 만들어서 리스트에 추가
				}
				
				String jsonData = jsonObjList.toJSONString();   
				
				PrintWriter out = res.getWriter();
				out.print(jsonData); // 화면에 출력해주는 역할!
				
			} catch (Exception e) {
				log.info(" comment list 예외 발생");
				e.printStackTrace();
			}
			
			break;
			
		case "remove" :
			try {
				isOk = csv.remove(Integer.parseInt(pathVar));
				log.info(isOk > 0 ? "ok" : "fail");
				PrintWriter out = res.getWriter();
				out.print(isOk);
			} catch (Exception e) {
				log.info(" revome list 예외 발생");
				e.printStackTrace();
			}

		default:
			break;
		}
		
		
		
	}

	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		service(req, res);
		
	}

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
	}

}
