package repository;

import java.util.List;

import domain.BoardVO;
import domain.pagingVO;

public interface BDAO {

	int insert(BoardVO bvo);

	List<BoardVO> selectList();

	BoardVO selectOne(int bno);

	int updateCount(int bno);

	int update(BoardVO bvo);

	int delete(int bno);

	int selectCount();

	List<BoardVO> selectPageList(pagingVO pgVo);

	/* List<BoardVO> getSerachList(String searchTitle); */


	int getSerPageCnt(String searchTitle);

	List<BoardVO> getSerachList(pagingVO pgVo);

	String selectimg(int bno);

	int recommendUpdate(int bno);



	

}
