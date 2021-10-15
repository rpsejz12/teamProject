package model.review;

public class ReviewVO {
	
	private int rpk;
	private String cmt;
	private String id;
	private String mpk;
	private String rdate;
	private double rating;
	
	
	public int getRpk() {
		return rpk;
	}
	public void setRpk(int rpk) {
		this.rpk = rpk;
	}
	public String getCmt() {
		return cmt;
	}
	public void setCmt(String cmt) {
		this.cmt = cmt;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMpk() {
		return mpk;
	}
	public void setMpk(String mpk) {
		this.mpk = mpk;
	}
	public String getRdate() {
		return rdate;
	}
	public void setRdate(String rdate) {
		this.rdate = rdate;
	}	
	
	
	
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	@Override
	public String toString() {
		return "ReviewVO [rpk=" + rpk + ", cmt=" + cmt + ", id=" + id + ", mpk=" + mpk + ", rdate=" + rdate
				+ ", rating=" + rating + "]";
	}
	

	
}
