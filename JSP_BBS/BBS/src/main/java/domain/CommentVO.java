package domain;

public class CommentVO {
	
	private int cno;
	private int bindex;
	private String writer;
	private String content;
	private String reg_at; 
	
	public CommentVO() { }
	
	//post(bno, writer, content)
	public CommentVO(int bindex, String writer, String content) {
		this.bindex = bindex;
		this.writer = writer;
		this.content = content;
	}

	//modify(cno, content)
	public CommentVO(int cno, String content) {
		this.cno = cno;
		this.content = content;
	}

	//list(bno, cno, writer, content, reg_at)
	public CommentVO(int cno, int bindex, String writer, String content, String reg_at) {
		this.cno = cno;
		this.bindex = bindex;
		this.writer = writer;
		this.content = content;
		this.reg_at = reg_at;
	}

	public int getCno() {
		return cno;
	}

	public void setCno(int cno) {
		this.cno = cno;
	}


	public int getBindex() {
		return bindex;
	}

	public void setBindex(int bindex) {
		this.bindex = bindex;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReg_at() {
		return reg_at;
	}

	public void setReg_at(String reg_at) {
		this.reg_at = reg_at;
	}

	
	
}
