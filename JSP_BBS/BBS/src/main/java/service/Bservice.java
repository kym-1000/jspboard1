package service;

import java.util.List;

import domain.BoardVO;
import domain.memberVO;
import domain.pagingVO;

public interface Bservice {

	List<memberVO> getlist();

	int register(BoardVO bvo);

	BoardVO getDetail(int bindex);

	int update(BoardVO bvo2);

	int remove(int bindex);

	int updateCount(BoardVO bvo);

	int getPageCnt();

	List<BoardVO> getListPage(pagingVO pgVo);

	List<BoardVO> getlist1(String email);

	int getPageCnt1(String email);

	List<BoardVO> getListPage1(pagingVO pgVo);

	String getFileName(int bindex);

	

	
	


}
