package re.review;

import java.sql.Timestamp;

public class LogonDataBean {
	private String img_src;
	private String img_src2;
	private String img_src3;
	private String cafe_name;
	private Timestamp date;
	private String content;
	private String writer;
	private String like_point;
	private String where_;
	
	public String getImg_src() {
		return img_src;
	}
	public void setImg_src(String img) {
		this.img_src = img;
	}
	public String getImg_src2() {
		return img_src2;
	}
	public void setImg_src2(String img) {
		this.img_src2 = img;
	}
	public String getImg_src3() {
		return img_src3;
	}
	public void setImg_src3(String img) {
		this.img_src3 = img;
	}
	public String getCafe_name() {
		return cafe_name;
	}
	public void setCafe_name(String cafename) {
		this.cafe_name = cafename;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getLike_point() {
		return like_point;
	}
	public void setLike_point(String point) {
		this.like_point = point;
	}
	public String getWhere_() {
		return where_;
	}
	public void setWhere_(String point) {
		this.where_ = point;
	}
}