package repository;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.memberController;
import domain.MemberVO;
import orm.DatabaseBuilder;

public class MDAOim implements MDAO {
	
	private static Logger log = LoggerFactory.getLogger(memberController.class);
	
	private SqlSession sql;
	
	private final String MM = "memberMapper."; // member의 네임 스페이스
	
	public MDAOim() {
		new DatabaseBuilder();
		sql = DatabaseBuilder.getFactory().openSession();
	}
	

	@Override
	public int insert(MemberVO mvo) {
		log.info("회원가입 insert sql 실행직전!");
		int isOk = sql.insert(MM+"memberinsert",mvo);
		if(isOk > 0) sql.commit();
		return isOk;
	}


	@Override
	public MemberVO selectOne(MemberVO mvo) {
		log.info("로그인 select sql 실행직전!");
		return sql.selectOne(MM+"login",mvo);
	}


	@Override
	public int updatelogin(String email) {
		log.info("마지막 로그인 시간 update sql 실행직전!");
		int isOk = sql.update(MM+"last",email);
		if(isOk > 0) sql.commit();
		return isOk;
	}


	@Override
	public int delete(MemberVO mvo) {
		log.info("삭제 delete sql 실행직전!");
		int isOk = sql.delete(MM+"delete",mvo);
		if(isOk > 0) sql.commit();
		return isOk;
	}


	@Override
	public int update(MemberVO mvo) {
		log.info("회원수정 update sql 실행직전!");
		int isOk = sql.delete(MM+"update",mvo);
		if(isOk > 0) sql.commit();
		return isOk;
	}


	

}
