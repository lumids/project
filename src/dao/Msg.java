package dao;

import java.sql.Timestamp;

public class Msg {
	private int msgNum;
	private String sendId;
	private String receiveId;
	private Timestamp sendTime;
	private String sendDelChk;
	private String receiveDelChk;
	private String receiveChk;
	private Timestamp receiveTime;
	private String msgSubject;
	private String msgContent;
	
	public String getMsgSubject() {
		return msgSubject;
	}
	public void setMsgSubject(String msgSubect) {
		this.msgSubject = msgSubect;
	}
	public String getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	public int getMsgNum() {
		return msgNum;
	}
	public void setMsgNum(int msgNum) {
		this.msgNum = msgNum;
	}
	public String getSendId() {
		return sendId;
	}
	public void setSendId(String sendId) {
		this.sendId = sendId;
	}
	public String getReceiveId() {
		return receiveId;
	}
	public void setReceiveId(String receiveId) {
		this.receiveId = receiveId;
	}
	public Timestamp getSendTime() {
		return sendTime;
	}
	public void setSendTime(Timestamp sendTime) {
		this.sendTime = sendTime;
	}
	public String getSendDelChk() {
		return sendDelChk;
	}
	public void setSendDelChk(String sendDelChk) {
		this.sendDelChk = sendDelChk;
	}
	public String getReceiveDelChk() {
		return receiveDelChk;
	}
	public void setReceiveDelChk(String receiveDelChk) {
		this.receiveDelChk = receiveDelChk;
	}
	public String getReceiveChk() {
		return receiveChk;
	}
	public void setReceiveChk(String receiveChk) {
		this.receiveChk = receiveChk;
	}
	public Timestamp getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(Timestamp receiveTime) {
		this.receiveTime = receiveTime;
	}
	
}