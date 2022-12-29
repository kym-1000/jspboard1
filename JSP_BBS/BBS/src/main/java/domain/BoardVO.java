package domain;

public class BoardVO {
	private int bindex;
	private String title;
	private String writer;
	private String content;
	private String regdate;
	private int read_count;
	private String image_file;
	
	
	public String getImage_file() {
		return image_file;
	}

	public void setImage_file(String image_file) {
		this.image_file = image_file;
	}
	
	
	

	public BoardVO(int bindex, String title, String writer, String content, String regdate, int read_count,
			String image_file) {
		this.bindex = bindex;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.regdate = regdate;
		this.read_count = read_count;
		this.image_file = image_file;
	}

	public BoardVO() {}
	
	// 인설트 : 제목 글쓴이 내용 
	public BoardVO(String title, String writer, String content) {
		this.title = title;
		this.writer = writer;
		this.content = content;
	}
	
	// 리스트 : 넘버 제목 글쓴이 등록날짜
	public BoardVO(int bindex,String title,String writer,String content) {
		this(title,writer,content);
		this.bindex = bindex;
	}
	
	// 업데이트 : 넘버 제목 내용 
	public BoardVO(int bindex,String title,String content) {
		this.bindex = bindex;
		this.title = title;
		this.content = content;
	}
	
	// 상세 : 넘버 제목 글쓴이 등록 날짜 내용 
	
	public BoardVO(int bindex,String title,String writer,String regdate ,String content) {
		this(bindex,title,writer,content);
		this.regdate = regdate;
	}
	

	public BoardVO(int bindex, String title, String writer, String content, String regdate, int read_count) {
		this.bindex = bindex;
		this.title = title;
		this.writer = writer;
		this.content = content;
		this.regdate = regdate;
		this.read_count = read_count;
	}

	public int getRead_count() {
		return read_count;
	}

	public void setRead_count(int read_count) {
		this.read_count = read_count;
	}

	public int getBindex() {
		return bindex;
	}

	public void setBindex(int bindex) {
		this.bindex = bindex;
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

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	

}
