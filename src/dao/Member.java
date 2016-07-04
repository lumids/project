package dao;

import java.sql.Date;

public class Member {
	private String id;
	private String passwd;
	private String nickname;
	private int warnCount;
	private char dropChk;
	private Date reg_date;
	private int pointSum;
	
	public int getPointSum() {
		return pointSum;
	}
	public void setPointSum(int pointSum) {
		this.pointSum = pointSum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public int getWarnCount() {
		return warnCount;
	}
	public void setWarnCount(int warnCount) {
		this.warnCount = warnCount;
	}
	public char getDropChk() {
		return dropChk;
	}
	public void setDropChk(char c) {
		this.dropChk = c;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
}