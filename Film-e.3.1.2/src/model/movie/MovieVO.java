package model.movie;

public class MovieVO {
	
	private String mpk;				//pk 장르 + nvl 
	private String title;			//영화 이름
	private String content;			//영화 설명
	private String mtype;			//영화 장르
	private String mdate;			//영화 개봉일
	private String filename;		//영화 이미지
	private double ratingavg;
	
	
	public String getMpk() {
		return mpk;
	}
	public void setMpk(String mpk) {
		this.mpk = mpk;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMtype() {
		return mtype;
	}
	public void setMtype(String mtype) {
		this.mtype = mtype;
	}
	public String getMdate() {
		return mdate;
	}
	public void setMdate(String mdate) {
		this.mdate = mdate;
	}
	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public double getRatingavg() {
		return ratingavg;
	}
	public void setRatingavg(double ratingavg) {
		this.ratingavg = ratingavg;
	}
	
	
	
	@Override
	public String toString() {
		return "MovieVO [mpk=" + mpk + ", title=" + title + ", content=" + content + ", mtype=" + mtype + ", mdate="
				+ mdate + ", filename=" + filename + ", ratingavg=" + ratingavg + "]";
	}
	
	

}
