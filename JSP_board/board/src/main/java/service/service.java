package service;

import domain.MemberVO;

public interface service {

	int Mregister(MemberVO mvo);

	MemberVO login(MemberVO mvo);

	int lastlogin(String email);

	int remove(MemberVO mvo);

	int modify(MemberVO mvo);

	

}
