package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.CommentDAO;
import dao.CommentDAOImpl;
import domain.CommentVO;

public class CommentServiceImpl implements CommentService {
	private static final Logger log = LoggerFactory.getLogger(CommentServiceImpl.class);
	private CommentDAO cdao;
	
	public CommentServiceImpl() {
		cdao = new CommentDAOImpl();
	}

	@Override
	public int post(CommentVO commentVO) {
		log.info(">>> comment > post > check 2");
		return cdao.insert(commentVO);
	}

	@Override
	public List<CommentVO> getList(int bindex) {
		log.info(">>> comment > list > check 2");
		return cdao.selectList(bindex);
	}

	@Override
	public int remove(int cno) {
		log.info(">>> comment > remove > check 2");
		return cdao.delete(cno);
	}

	@Override
	public int update(CommentVO commentVO) {
		log.info(">>> comment > update > check 2");
		return cdao.update(commentVO);
	}

	public int removeALL(int bindex) {
		log.info(">>> comment > removeALL > check 2");
		return cdao.removeALL(bindex);
	}
	
}