package dao;

import java.sql.Date;

public class Point {
	private int pointNum;
	private String id;
	private String pointGetReason;
	private Date pointGetTime;
	private int getPoint;
	
	public int getGetPoint() {
		return getPoint;
	}
	public void setGetPoint(int getPoint) {
		this.getPoint = getPoint;
	}
	public int getPointNum() {
		return pointNum;
	}
	public void setPointNum(int pointNum) {
		this.pointNum = pointNum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPointGetReason() {
		return pointGetReason;
	}
	public void setPointGetReason(String pointGetReason) {
		this.pointGetReason = pointGetReason;
	}
	public Date getPointGetTime() {
		return pointGetTime;
	}
	public void setPointGetTime(Date pointGetTime) {
		this.pointGetTime = pointGetTime;
	}
	
}