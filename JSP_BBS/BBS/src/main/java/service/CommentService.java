package service;

import java.util.List;

import domain.CommentVO;

public interface CommentService {

	int post(CommentVO commentVO);

	List<CommentVO> getList(int bindex);

	int remove(int cno);

	int update(CommentVO commentVO);

	

}
