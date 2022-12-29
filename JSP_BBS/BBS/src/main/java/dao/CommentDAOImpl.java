package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.CommentVO;
import orm.DatabaseBuilder;

public class CommentDAOImpl implements CommentDAO {
	
	private static final Logger log = LoggerFactory.getLogger(CommentDAOImpl.class);
	private SqlSession sql;
	private final String NS = "CommentMapper.";
	private int isOk;
	
	public CommentDAOImpl() {
		new DatabaseBuilder();
		sql = DatabaseBuilder.getFactory().openSession();
	}

	@Override
	public int insert(CommentVO commentVO) {
		log.info("insert sql 실행직전! check3");
		isOk = sql.insert(NS+"add",commentVO);
		if(isOk >0) sql.commit();
		return isOk;
	}

	@Override
	public List<CommentVO> selectList(int bindex) {
		log.info("selectList sql 실행직전! check3");
		return sql.selectList(NS+"list",bindex);
	}

	@Override
	public int delete(int cno) {
		log.info("delete sql 실행직전! check3");
		isOk = sql.delete(NS+"delete",cno);
		if(isOk >0) sql.commit();
		return isOk;
	}

	@Override
	public int update(CommentVO commentVO) {
		log.info("update sql 실행직전! check3");
		int isOk = sql.update(NS+"update",commentVO);
		if(isOk > 0) {
			sql.commit();
		}
		return isOk;
	}

	@Override
	public int removeALL(int bindex) {
		log.info("removeALL sql 실행직전! check3");
		int isOk = sql.delete(NS+"removeALL",bindex);
		if(isOk > 0) {
			sql.commit();
		}
		return isOk;
	}
	
	
}
