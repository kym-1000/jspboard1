package service;

import java.util.List;

import dao.DAO;
import dao.DAOim;
import domain.memberVO;

public class serviceim implements service {
	
	private DAO mado; // 다오 인터페이스 생성
	
	public serviceim() {
		mado = new DAOim();
	}

	@Override
	public int Mregister(memberVO mvo) {
		System.out.println("회원가입 진입2");
		return mado.insert(mvo);
	}

	@Override
	public memberVO login(memberVO mvo) {
		System.out.println("로그인 진입2");
		return mado.selectOne(mvo);
	}

	@Override
	public List<memberVO> getlist() {
		System.out.println("회원리스트 진입2");
		return mado.selectlist();
	}

	@Override
	public memberVO getDetail(String email) {
		System.out.println("상세 진입2");
		return mado.getdatail(email);
	}

	@Override
	public int update(memberVO mvo2) {
		System.out.println("상세 진입2");
		return mado.update(mvo2);
	}

	@Override
	public int lastlogin(String email) {
		// TODO Auto-generated method stub
		return mado.updatelast(email);
	}

	@Override
	public int remove(String email) {
		System.out.println("삭제 진입2");
		return mado.delete(email);
	}

	
	


}
