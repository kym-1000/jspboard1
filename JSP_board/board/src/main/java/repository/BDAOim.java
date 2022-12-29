package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.memberController;
import domain.BoardVO;
import domain.pagingVO;
import orm.DatabaseBuilder;

public class BDAOim implements BDAO {
	
private static Logger log = LoggerFactory.getLogger(memberController.class);
	
	private SqlSession sql;
	
	private final String BM = "BoardMapper."; // board 멤버 스페이스
	
	public BDAOim() {
		new DatabaseBuilder();
		sql = DatabaseBuilder.getFactory().openSession();
	}

	@Override
	public int insert(BoardVO bvo) {
		log.info("게시글 등록 sql 실행직전!");
		int isOk = sql.insert(BM+"add",bvo);
		if(isOk > 0) sql.commit();
		return isOk;
	}

	@Override
	public List<BoardVO> selectList() {
		log.info("게시글 목록 sql 실행직전!");
		return sql.selectList(BM+"list");
	}

	@Override
	public BoardVO selectOne(int bno) {
		log.info("상세페이지 sql 실행직전!");
		return sql.selectOne(BM+"selectOne",bno);
	}

	@Override
	public int updateCount(int bno) {
		log.info("조회수 상승 sql 실행직전!");
		int isOk = sql.update(BM+"updateCount",bno);
		System.out.println(bno);
		if(isOk > 0) sql.commit();
		return isOk;
	}

	@Override
	public int update(BoardVO bvo) {
		log.info("수정 sql 실행직전!");
		int isOk = sql.update(BM+"update",bvo);
		if(isOk > 0) sql.commit();
		return isOk;
	}

	@Override
	public int delete(int bno) {
		log.info("삭제 sql 실행직전!");
		int isOk = sql.delete(BM+"delete",bno);
		if(isOk > 0) sql.commit();
		return isOk;
	}

	@Override
	public int selectCount() {
		log.info("페이징 번호 sql 실행직전!");
		return sql.selectOne(BM+"pagecnt");
	}

	@Override
	public List<BoardVO> selectPageList(pagingVO pgVo) {
		log.info("페이징 번호 sql 실행직전!");
		return sql.selectList(BM+"selectPageList",pgVo);
	}

	/*
	 * @Override public List<BoardVO> getSerachList(String searchTitle) {
	 * log.info("검색 sql 실행직전!"); return
	 * sql.selectList(BM+"getSerachList",searchTitle); }
	 */

	@Override
	public int getSerPageCnt(String searchTitle) {
		log.info("검색 카운트 갯수  sql 실행직전!");
		return sql.selectOne(BM+"getSerPageCnt",searchTitle);
	}

	@Override
	public List<BoardVO> getSerachList(pagingVO pgVo) {
		 log.info("검색 sql 실행직전!"); 
		 return	 sql.selectList(BM+"getSerachList",pgVo);
	}

	@Override
	public String selectimg(int bno) {
		log.info("이미지 가져오기! sql 실행직전!");
		return sql.selectOne(BM+"selectimg",bno);
	}

	@Override
	public int recommendUpdate(int bno) {
		log.info("추천 sql 실행직전!");
		return sql.update(BM+"recommendUpdate",bno);
	}

	

	

}
