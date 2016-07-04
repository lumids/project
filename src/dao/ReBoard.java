package dao;

import java.sql.Timestamp;

public class ReBoard {
	private int reBoardNum;
	private int boardNum;
	private String id;
	private String content;
	private int	rRef;
	private int rRe_step; 
	private int rRe_level;
	private Timestamp rWriteDate;
	private String rDelChk;

	public int getReBoardNum() {
		return reBoardNum;
	}
	public void setReBoardNum(int reBoardNum) {
		this.reBoardNum = reBoardNum;
	}
	public int getBoardNum() {
		return boardNum;
	}
	public void setBoardNum(int boardNum) {
		this.boardNum = boardNum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getRRef() {
		return rRef;
	}
	public void setRRef(int rref) {
		rRef = rref;
	}
	public int getRRe_step() {
		return rRe_step;
	}
	public void setRRe_step(int rre_step) {
		rRe_step = rre_step;
	}
	public int getRRe_level() {
		return rRe_level;
	}
	public void setRRe_level(int rre_level) {
		rRe_level = rre_level;
	}
	public Timestamp getRWriteDate() {
		return rWriteDate;
	}
	public void setRWriteDate(Timestamp rwriteDate) {
		rWriteDate = rwriteDate;
	}
	public String getRDelChk() {
		return rDelChk;
	}
	public void setRDelChk(String rdelChk) {
		rDelChk = rdelChk;
	}
	
}