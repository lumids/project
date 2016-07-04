package dao;
import java.sql.Timestamp;

public class Board {
	private int boardNum;
	private int aniNum;
	private String id;
	private String subject;
	private String content;
	private int readcount;
	private int	ref;
	private int re_step; 
	private int re_level;
	private Timestamp writeDate;
	private String delChk;
	private String noticeChk;
	public int getBoardNum() {
		return boardNum;
	}
	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}
	public int getAniNum() {
		return aniNum;
	}
	public void setAniNum(int aniNum) {
		this.aniNum = aniNum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getRe_step() {
		return re_step;
	}
	public void setRe_step(int re_step) {
		this.re_step = re_step;
	}
	public int getRe_level() {
		return re_level;
	}
	public void setRe_level(int re_level) {
		this.re_level = re_level;
	}

	public Timestamp getWriteDate() {
		return writeDate;
	}
	public void setWriteDate(Timestamp writeDate) {
		this.writeDate = writeDate;
	}
	public String getDelChk() {
		return delChk;
	}
	public void setDelChk(String delChk) {
		this.delChk = delChk;
	}
	public String getNoticeChk() {
		return noticeChk;
	}
	public void setNoticeChk(String noticeChk) {
		this.noticeChk = noticeChk;
	}	
}