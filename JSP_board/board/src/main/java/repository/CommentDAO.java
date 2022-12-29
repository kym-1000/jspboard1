package repository;

import java.util.List;

import domain.CommentVO;

public interface CommentDAO {

	int insert(CommentVO commentVO);

	List<CommentVO> selectList(int bno);

	int delete(int cno);

}
