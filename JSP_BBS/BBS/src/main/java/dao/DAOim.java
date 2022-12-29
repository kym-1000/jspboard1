package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import domain.memberVO;
import orm.DatabaseBuilder;

public class DAOim implements DAO {
	
	private SqlSession sql;
	
	private final String MM = "memberMapper."; //멤버의 네임 스페이스

	
	public DAOim() {
		new DatabaseBuilder();
		sql = DatabaseBuilder.getFactory().openSession();
	}
	
	@Override
	public int insert(memberVO mvo) {
		System.out.println("sql 실행직전!");
		int isOk = sql.insert(MM+"add",mvo);
		if(isOk > 0) {
			sql.commit(); // 컴밋 실행!
		}
		return isOk;
	}

	@Override
	public memberVO selectOne(memberVO mvo) {
		System.out.println("sql 실행직전!");
		return sql.selectOne(MM+"login",mvo);
	}

	@Override
	public List<memberVO> selectlist() {
		System.out.println("sql 실행직전!");
		return sql.selectList(MM+"list");
	}

	@Override
	public memberVO getdatail(String email) {
		System.out.println("sql 실행직전!");
		return sql.selectOne(MM+"one",email);
	}

	@Override
	public int update(memberVO mvo2) {
		System.out.println("sql 실행직전!");
		int isOk = sql.update(MM+"update",mvo2);
		if(isOk > 0) {
			sql.commit();
		}
		return isOk;
	}

	@Override
	public int updatelast(String email) {
		System.out.println("sql 실행직전!");
		int isOk = sql.update(MM+"last",email);
		if(isOk > 0) {
			sql.commit();
		}
		return isOk;
	}

	@Override
	public int delete(String email) {
		System.out.println("sql 실행직전!");
		int isOk = sql.delete(MM+"delete",email);
		if(isOk > 0) {
			sql.commit();
		}
		return isOk;
	}
	

	


}
