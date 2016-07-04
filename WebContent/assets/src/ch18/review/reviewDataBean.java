package ch18.review;
import java.sql.Timestamp;

public class reviewDataBean {
	private int seq; 
    private String writer;
    private String content;
    private String passwd;
    private Timestamp date;  
    private String img_src;
    private String img_src2;
    private String img_src3;
    private String cafe_name;
    private String where_;  
    private String title;  
    private int like_point; 
    private int grademark; 
    
    
	public int getSeq() {
		return seq;
	}
	public void setSeq(int seq) {
		this.seq = seq;
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
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}	
	public String getImg_src() {
		return img_src;
	}
	public void setImg_src(String img_src) {
		this.img_src = img_src;
	}	
	public String getImg_src2() {
		return img_src2;
	}
	public void setImg_src2(String img_src2) {
		this.img_src2 = img_src2;
	}
	public String getImg_src3() {
		return img_src3;
	}
	public void setImg_src3(String img_src3) {
		this.img_src3 = img_src3;
	}	
	public String getCafe_name() {
		return cafe_name;
	}
	public void setCafe_name(String cafe_name) {
		this.cafe_name = cafe_name;
	}	
	public String getWhere_() {
		return where_;
	}
	public void setWhere_(String where_ ) {
		this.where_ = where_;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title ) {
		this.title = title;
	}
	public int getLike_point() {
		return like_point;
	}
	public void setLike_point(int like_point) {
		this.like_point = like_point;
	}
	public int getGrademark() {
		return grademark;
	}
	public void setGrademark(int grademark) {
		this.grademark = grademark;
	}
	public void setSubject(String string) {
		// TODO Auto-generated method stub
		
	}
}