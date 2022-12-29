package dao;

import java.util.List;

import domain.BoardVO;
import domain.memberVO;
import domain.pagingVO;

public interface BDAO {

	List<memberVO> selectList();

	int insert(BoardVO bvo);

	BoardVO selectOne(int bindex);

	int update(BoardVO bvo2);

	int delete(int bindex);

	int updateCount(BoardVO bvo);

	int updateCount(int bindex);

	int selectCount();

	List<BoardVO> selectList(pagingVO pgVo);

	List<BoardVO> selectList1(String email);

	int getPageCnt1(String email);

	List<BoardVO> getListPage1(pagingVO pgVo);

	String getFileName1(int bindex);



	

}
