package domain;

public class BoardVO {
	
//	create table board(
//			bno int auto_increment,
//			title varchar(100),
//			writer varchar(50),
//			content text,
//			cnt int,
//			recommend int,
//			regdate datetime default now(),
//			primary key(bno));
	
	
	private int bno;
	private String title;
	private String writer;
	private String content;
	private int cnt;
	private int recommend;
	private String regdate;
	private String image_file;
	
	
	
	public String getImage_file() {
		return image_file;
	}

	public void setImage_file(String image_file) {
		this.image_file = image_file;
	}

	public BoardVO() {}

	public int getBno() {
		return bno;
	}

	public void setBno(int bno) {
		this.bno = bno;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public int getRecommend() {
		return recommend;
	}

	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	};
	
	
	

}
