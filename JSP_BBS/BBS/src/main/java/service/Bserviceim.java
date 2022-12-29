package service;

import java.util.List;

import dao.BDAO;
import dao.BDAOim;
import domain.BoardVO;
import domain.memberVO;
import domain.pagingVO;


public class Bserviceim implements Bservice {
	private BDAO bdao; // 다오 인터페이스 생성
	
	public Bserviceim() {
		bdao = new BDAOim();
	}

	@Override
	public List<memberVO> getlist() {
		System.out.println("게시글 리스트 진입2");
		return bdao.selectList();
	}

	@Override
	public int register(BoardVO bvo) {
		System.out.println("게시글 등록 진입2");
		return bdao.insert(bvo);
	}

	@Override
	public BoardVO getDetail(int bindex) {
		System.out.println("게시글 상세 진입2");
		int isOk = bdao.updateCount(bindex);
		if(isOk > 0) {
			System.out.println("조회수 상승완료");
		}else {
			System.out.println("조회수 상승실패");
		}
		return (isOk > 0)? bdao.selectOne(bindex) : null;
	}

	@Override
	public int update(BoardVO bvo2) {
		System.out.println("업데이트 상세 진입2");
		return bdao.update(bvo2);
	}

	@Override
	public int remove(int bindex) {
		System.out.println("삭제 상세 진입2");
		CommentServiceImpl csv = new CommentServiceImpl();
		
		int isOk = csv.removeALL(bindex);
		System.out.println("댓글 삭제가?  "+isOk);
		
		return bdao.delete(bindex);
	}

	@Override
	public int updateCount(BoardVO bvo) {
		System.out.println("조회수 상승 진입2");
		return bdao.updateCount(bvo);
	}

	@Override
	public int getPageCnt() {
		// TODO Auto-generated method stub
		return bdao.selectCount();
	}

	@Override
	public List<BoardVO> getListPage(pagingVO pgVo) {
		// TODO Auto-generated method stub
		return bdao.selectList(pgVo);
	}

	@Override
	public List<BoardVO> getlist1(String email) {
		// TODO Auto-generated method stub
		return bdao.selectList1(email);
	}

	@Override
	public int getPageCnt1(String email) {
		// TODO Auto-generated method stub
		return bdao.getPageCnt1(email);
	}

	@Override
	public List<BoardVO> getListPage1(pagingVO pgVo) {
		// TODO Auto-generated method stub
		return bdao.getListPage1(pgVo);
	}

	@Override
	public String getFileName(int bindex) {
		// TODO Auto-generated method stub
		return bdao.getFileName1(bindex);
	}



	


	
}
