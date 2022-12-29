package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.memberController;
import domain.CommentVO;
import repository.CommentDAO;
import repository.CommentDAOim;

public class CommentServiceim implements CommentService {
	private static Logger log = LoggerFactory.getLogger(memberController.class);
	
	private CommentDAO cdao;
	
	public CommentServiceim() {
		cdao = new CommentDAOim();
	}

	@Override
	public int post(CommentVO commentVO) {
		log.info("댓글 등록 서비스 진행!");
		return cdao.insert(commentVO);
	}

	@Override
	public List<CommentVO> getList(int bno) {
		log.info("댓글 리스트 서비스 진행!");
		return cdao.selectList(bno);
	}

	@Override
	public int remove(int cno) {
		log.info("삭제 서비스 진행!");
		return cdao.delete(cno);
	}
	
	
	
}
