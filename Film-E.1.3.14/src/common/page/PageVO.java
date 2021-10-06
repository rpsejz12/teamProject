package common.page;

public class PageVO {
	
	int curPage;	//현재 페이지
	int startPage;	//페이지 목차 시작	< '1' 2 3 4 5 >
	int endPage;	//페이지 목차 끝	< 1 2 3 4 '5' >
	int lastPage;	//마지막 페이지	< 11 12 '13'> 전체 페이지의 마지막
	int total;		//게시물 총 개수
	int perPage;	//페이지당 출력할 게시물 수
	int perPageSet; //페이지 목차 출력할 개수 < 1 2 3 4 5 > => 5개 
	int start;		//현재페이지에서 시작 게시물
	int end;		//현재페이지에서 끝 게시물

	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getPerPage() {
		return perPage;
	}
	public void setPerPage(int perPage) {
		this.perPage = perPage;
	}	
	public int getLastPage() {
		return lastPage;
	}
	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}	
	public int getPerPageSet() {
		return perPageSet;
	}
	public void setPerPageSet(int perPageSet) {
		this.perPageSet = perPageSet;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	
	
	
	
	
	@Override
	public String toString() {
		return "PageVO [curPage=" + curPage + ", startPage=" + startPage + ", endPage=" + endPage + ", lastPage="
				+ lastPage + ", total=" + total + ", perPage=" + perPage + ", perPageSet=" + perPageSet + ", start="
				+ start + ", end=" + end + "]";
	}
	

	
	
}
