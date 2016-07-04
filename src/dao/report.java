package dao;

import java.sql.Date;

public class report {
	private int reportReaNum;
	private int repReaNum;
	private Date repDate;
	private String repHandChk;
	private String reporterId;
	private String reportedId;
	private String content;
	private int reBoardNum;
	
	public int getReBoardNum() {
		return reBoardNum;
	}
	public void setReBoardNum(int reBoardNum) {
		this.reBoardNum = reBoardNum;
	}
	public int getReportReaNum() {
		return reportReaNum;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setReportReaNum(int reportReaNum) {
		this.reportReaNum = reportReaNum;
	}
	public int getRepReaNum() {
		return repReaNum;
	}
	public void setRepReaNum(int repReaNum) {
		this.repReaNum = repReaNum;
	}
	public Date getRepDate() {
		return repDate;
	}
	public void setRepDate(Date repDate) {
		this.repDate = repDate;
	}
	public String getRepHandChk() {
		return repHandChk;
	}
	public void setRepHandChk(String repHandChk) {
		this.repHandChk = repHandChk;
	}
	public String getReporterId() {
		return reporterId;
	}
	public void setReporterId(String reporterId) {
		this.reporterId = reporterId;
	}
	public String getReportedId() {
		return reportedId;
	}
	public void setReportedId(String reportedId) {
		this.reportedId = reportedId;
	}	
}