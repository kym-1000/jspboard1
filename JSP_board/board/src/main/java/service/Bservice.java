package service;

import java.util.List;

import domain.BoardVO;
import domain.pagingVO;

public interface Bservice {

	int register(BoardVO bvo);

	List<BoardVO> getlist();

	BoardVO getDetail(int bno);

	int update(BoardVO bvo);

	int remove(int bno);

	int getPageCnt();

	List<BoardVO> getPageList(pagingVO pgVo);

	/* List<BoardVO> getSerachList(String searchTitle); */

	int getSerPageCnt(String searchTitle);

	List<BoardVO> getSerachList(pagingVO pgVo);

	String getFileName(int bno);

	int recommendUp(int bno);

	

	

}
