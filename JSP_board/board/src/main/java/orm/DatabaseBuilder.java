package orm;

import java.io.IOException;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DatabaseBuilder {
	
	private static SqlSessionFactory factory;
	// SQL 세션 팩토리 를 빌드 하기 위한 변수 
	private static final String config = "orm/MybatisConfig.xml";
	
	// 초기화 블럭 사용하여 객체 생성 후 초기화
	
	static {
		try {
			factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader(config));
		} catch (IOException e) {
			// 마이바티스 설정오류시 메시지
			e.printStackTrace();
		}
	}
	
	public static SqlSessionFactory getFactory() {
		return factory;
	}
	

}
