package domain;

public class pagingVO {
		
	private int pageNo;  // 현재 화면에 출력되는 페이지네이션 번호
	private int qty; // 한 페이지당 보여줄 게시글 수 
	private String search; 
	
	public pagingVO() { // 페이지네이션을 클릭하기 전 기본 리스트 출력값 
		this(1,10); // 만약 아무것도 들어온게 없다면 첫번째 페이지에 기본 10개
	}
	
	public int getPageStart() {
		return (this.pageNo-1)*qty; // 시작페이지 번호 구하기
	}
	
	public pagingVO(int pageNo,int qty) {
		this.pageNo = pageNo;
		this.qty = qty;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	
	
	

}
