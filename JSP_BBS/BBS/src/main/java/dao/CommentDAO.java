package dao;

import java.util.List;

import domain.CommentVO;

public interface CommentDAO {

	int insert(CommentVO commentVO);

	List<CommentVO> selectList(int bindex);

	int delete(int cno);

	int update(CommentVO commentVO);

	int removeALL(int bindex);

}
