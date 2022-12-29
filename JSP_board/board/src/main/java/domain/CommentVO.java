package domain;

public class CommentVO {
	
	private int cno;
	private int bno;
	private String writer;
	private String content;
	private String reg_at;
	private String image_file;
	private String pwd;
	
	
	
	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getImage_file() {
		return image_file;
	}

	public void setImage_file(String image_file) {
		this.image_file = image_file;
	}

	public CommentVO() {}  // 기본생성자 
	
	public CommentVO(int cno, int bno, String writer, String content, String reg_at) {
		this.cno = cno;
		this.bno = bno;
		this.writer = writer;
		this.content = content;
		this.reg_at = reg_at;
		
	}

	public CommentVO(int bno, String writer, String content, String image_file) {
		this.bno = bno;
		this.writer = writer;
		this.content = content;
		this.image_file = image_file;
	}
	

	public CommentVO(int bno, String writer,String content,String image_file, String pwd) {
		this.bno = bno;
		this.writer = writer;
		this.content = content;
		this.image_file = image_file;
		this.pwd = pwd;
	}

	public CommentVO(int bno, String writer, String content) {
		this.bno = bno;
		this.writer = writer;
		this.content = content;
	}

	public CommentVO(int cno, String content) {
		this.cno = cno;
		this.content = content;
	}


	public int getCno() {
		return cno;
	}

	public void setCno(int cno) {
		this.cno = cno;
	}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
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
