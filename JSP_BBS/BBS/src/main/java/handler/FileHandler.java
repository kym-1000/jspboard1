package handler;


import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.CommentController;

public class FileHandler {
		
	private static Logger log = LoggerFactory.getLogger(CommentController.class);
	
	// 파일이름과 경로를 받아서 파일을 삭제하는 메서드
	// 리턴타입 int  => 잘 삭제되었는지 체크하기 위한 변수 
	public FileHandler() {}
	
	public int deleteFile(String imageFileName, String savePath) {
		boolean isDel = true;  // 삭제가 잘 이루어졌는지 체크하는 변수
		log.info("deleteFile 메서드 접근");
		log.info("imageFileName"+imageFileName);
		
		File fileDir = new File(savePath);
		File removeFile = new File(fileDir+File.separator+imageFileName);
		File removeThumbFile = new File(fileDir+File.separator+"th_"+imageFileName);
		
		// 파일이 존재해야 삭제 가능 
		if(removeFile.exists() || removeThumbFile.exists()) {
			isDel = removeFile.delete(); // 불리언 형태로 리턴
			log.info("FileHandler remove : "+ (isDel ? "ok" : "fail"));
			if(isDel) {
				isDel = removeThumbFile.delete();
				log.info("FileHandler remove 썸네일 : "+ (isDel ? "ok" : "fail"));
			}
			
		}
		log.info("FileHandler remove ok");
		return isDel ? 1 : 0 ;
	}
}
