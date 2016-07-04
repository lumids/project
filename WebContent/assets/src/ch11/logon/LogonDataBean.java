package ch11.logon;

import java.sql.Timestamp;

public class LogonDataBean {
	private int num;
	private String id;
	private String passwd;
	private String name;
	private Timestamp reg_date;
	private String address;
	private String tel;
	private String gender;
	private String accnum;
	private Boolean out_chk;
	private int sum_point;
	private String rol;
	private String cafe_name;
	private Timestamp cafe_con_start;
	private String con_join;
	private String join_cafe;
	
	private String a;
	private String b;
	private String c;
	
	public String getA() {
		return a;
	}
	public void setA(String num) {
		this.a = num;
	}
	public String getB() {
		return b;
	}
	public void setB(String num) {
		this.b = num;
	}
	public String getC() {
		return c;
	}
	public void setC(String num) {
		this.c = num;
	}
	
	
	
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAccnum() {
		return accnum;
	}
	public void setAccnum(String accnum) {
		this.accnum = accnum;
	}
	public Boolean getOut_chk() {
		return out_chk;
	}
	public void setOut_chk(Boolean out_chk) {
		this.out_chk = out_chk;
	}
	public int getSum_point() {
		return sum_point;
	}
	public void setSum_point(int sum_point) {
		this.sum_point = sum_point;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
	}
	public String getCafe_name() {
		return cafe_name;
	}
	public void setCafe_name(String rol) {
		this.cafe_name = rol;
	}
	public Timestamp getCafe_con_start() {
		return cafe_con_start;
	}
	public void setCafe_con_start(Timestamp reg_date) {
		this.cafe_con_start = reg_date;
	}
	public String getCon_join() {
		return con_join;
	}
	public void setCon_join(String rol) {
		this.con_join = rol;
	}
	public String getJoin_cafe() {
		return join_cafe;
	}
	public void setJoin_cafe(String rol) {
		this.join_cafe = rol;
	}
}
