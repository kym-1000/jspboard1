package service;

import java.util.List;

import domain.memberVO;

public interface service {

	int Mregister(memberVO mvo);

	memberVO login(memberVO mvo);

	List<memberVO> getlist();

	memberVO getDetail(String email);

	int update(memberVO mvo2);

	int lastlogin(String email);

	int remove(String email);

	


}
