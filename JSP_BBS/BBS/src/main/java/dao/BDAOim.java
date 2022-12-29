package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import domain.BoardVO;
import domain.memberVO;
import domain.pagingVO;
import orm.DatabaseBuilder;

public class BDAOim implements BDAO {
	
private SqlSession sql;
	
	private final String BM = "boardMapper."; //멤버의 네임 스페이스

	
	public BDAOim() {
		new DatabaseBuilder();
		sql = DatabaseBuilder.getFactory().openSession();
		// 응용 프로그램은 SqlSessionFactoryBuilder를 
		// 사용하여 빌드된 SqlSessionFactory에서 SqlSession을 가져옵니다.
		// SqlSessionFactory는 SqlSession을 생성하고 이를 애플리케이션에 반환합니다.
	}

	@Override
	public List<memberVO> selectList() {
		System.out.println("sql 실행 직전!");
		return sql.selectList(BM+"list");
	}

	@Override
	public int insert(BoardVO bvo) {
		System.out.println("sql 실행 직전!");
		int isOk = sql.insert(BM+"add",bvo);
		if(isOk > 0) {
			sql.commit(); // 컴밋 실행!
		}
		return isOk;
	}

	@Override
	public BoardVO selectOne(int bindex) {
		System.out.println("sql 실행 직전!");
		return sql.selectOne(BM+"one",bindex);
	}

	@Override
	public int update(BoardVO bvo2) {
		System.out.println("sql 실행 직전!");
		int isOk = sql.update(BM+"update",bvo2);
		if(isOk > 0) {
			sql.commit(); // 컴밋 실행!
		}
		return isOk;
	}

	@Override
	public int delete(int bindex) {
		System.out.println("sql 실행 직전!");
		int isOk = sql.delete(BM+"delete",bindex);
		if(isOk > 0) {
			sql.commit(); // 컴밋 실행!
		}
		return isOk;
	}

	@Override
	public int updateCount(BoardVO bvo) {
		System.out.println("sql 실행 직전!");
		int isOk = sql.update(BM+"updateCount",bvo);
		if(isOk > 0) {
			sql.commit(); // 컴밋 실행!
		}
		return isOk;
	}

	@Override
	public int updateCount(int bindex) {
		System.out.println("sql 실행 직전!");
		int isOk = sql.update(BM+"updateCount",bindex);
		if(isOk > 0) {
			sql.commit(); // 컴밋 실행!
		}
		return isOk;
	}

	@Override
	public int selectCount() {
		System.out.println("sql 실행 직전!");
		return sql.selectOne(BM+"cnt");
	}

	@Override
	public List<BoardVO> selectList(pagingVO pgVo) {
		// TODO Auto-generated method stub
		return sql.selectList(BM+"pagingList",pgVo);
	}

	@Override
	public List<BoardVO> selectList1(String email) {
		System.out.println("sql 실행 직전!");
		return sql.selectList(BM+"list1",email);
	}

	@Override
	public int getPageCnt1(String email) {
		System.out.println("sql 실행 직전!");
		return sql.selectOne(BM+"cnt1",email);
	}

	@Override
	public List<BoardVO> getListPage1(pagingVO pgVo) {
		System.out.println("sql 실행 직전!");
		return sql.selectList(BM+"pagingList1",pgVo);
	}

	@Override
	public String getFileName1(int bindex) {
		// TODO Auto-generated method stub
		return sql.selectOne(BM+"img",bindex);
	}



	

	
	

}
