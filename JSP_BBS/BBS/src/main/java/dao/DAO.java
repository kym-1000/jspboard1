package dao;

import java.util.List;

import domain.memberVO;

public interface DAO {

	int insert(memberVO mvo);

	memberVO selectOne(memberVO mvo);

	List<memberVO> selectlist();

	memberVO getdatail(String email);

	int updatelast(String email);

	int update(memberVO mvo2);

	int delete(String email);

	


}
