package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.memberController;
import domain.CommentVO;
import orm.DatabaseBuilder;

public class CommentDAOim implements CommentDAO {
	
private static Logger log = LoggerFactory.getLogger(memberController.class);
	
	private SqlSession sql;
	
	private final String CM = "CommentMapper."; // member의 네임 스페이스
	
	public CommentDAOim() {
		new DatabaseBuilder();
		sql = DatabaseBuilder.getFactory().openSession();
	}
	

	@Override
	public int insert(CommentVO commentVO) {
		log.info("댓글 등록 sql 실행직전!");
		int isOk = sql.insert(CM+"insert",commentVO);
		if(isOk > 0) sql.commit();
		return isOk;
	}


	@Override
	public List<CommentVO> selectList(int bno) {
		log.info("댓글 리스트 sql 실행직전!");
		return sql.selectList(CM+"list",bno);
	}


	@Override
	public int delete(int cno) {
		log.info("댓글 삭제 sql 실행직전!");
		int isOk = sql.insert(CM+"delete",cno);
		if(isOk > 0) sql.commit();
		return isOk;
	}

}
