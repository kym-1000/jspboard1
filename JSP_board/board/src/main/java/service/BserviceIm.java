package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.memberController;
import domain.BoardVO;
import domain.pagingVO;
import repository.BDAO;
import repository.BDAOim;


public class BserviceIm implements Bservice {
	
	private static Logger log = LoggerFactory.getLogger(memberController.class);
	
	private BDAO bdao; // 다오 인터페이스 생성 
	
	public BserviceIm() {
		bdao = new BDAOim();
	}

	@Override
	public int register(BoardVO bvo) {
		log.info("게시글 등록 서비스 구현!");
		return bdao.insert(bvo);
	}

	@Override
	public List<BoardVO> getlist() {
		log.info("게시판 목록 서비스 구현!");
		return bdao.selectList();
	}

	@Override
	public BoardVO getDetail(int bno) {
		log.info("상세페이지 서비스 구현!");
		int isOk = bdao.updateCount(bno);
		if(isOk > 0) {
			log.info("조회수 상승완료");
		}else {
			log.info("조회수 상승실패");
		}
	
		return (isOk > 0) ?   bdao.selectOne(bno) : null;
	}

	@Override
	public int update(BoardVO bvo) {
		log.info("수정 서비스 구현!");
		return bdao.update(bvo);
	}

	@Override
	public int remove(int bno) {
		log.info("삭제 서비스 구현!");
		return bdao.delete(bno);
	}

	@Override
	public int getPageCnt() {
		log.info("페이징 카운트 서비스 구현!");
		return bdao.selectCount();
	}

	@Override
	public List<BoardVO> getPageList(pagingVO pgVo) {
		log.info("페이징 서비스 구현!");
		return bdao.selectPageList(pgVo);
	}

	

	@Override
	public int getSerPageCnt(String searchTitle) {
		log.info("검색 서비스 구현!");
		return bdao.getSerPageCnt(searchTitle);
	}

	@Override
	public List<BoardVO> getSerachList(pagingVO pgVo) {
		log.info("검색 리스트 서비스 구현!");
		return bdao.getSerachList(pgVo);
	}

	@Override
	public String getFileName(int bno) {
		log.info("검색 리스트 서비스 구현!");
		return bdao.selectimg(bno);
	}

	@Override
	public int recommendUp(int bno) {
		log.info("추천 서비스 구현!");
		return bdao.recommendUpdate(bno);
	}

	

	

}
