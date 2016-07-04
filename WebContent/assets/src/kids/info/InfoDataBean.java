package kids.info;

import java.sql.Timestamp;

public class InfoDataBean {
	private int num;
	private int cafe_num;
	private String cafe_cr_num;
	private String cafe_name;
	private String cafe_position;
	private String cafe_adul_fee;
	private String cafe_chi_fee;
	private String cafe_phone;
	private int cafe_bh_start;
	private int cafe_bh_end;
	private String wifi;
	private String parking;
	private String eat;
	private String moim;
	private String party;
	private Timestamp reg_date;
	private String con_join;
	private Timestamp cafe_con_start;
	private Timestamp cafe_con_end;

	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getCafe_num() {
		return cafe_num;
	}
	public void setCafe_num(int num) {
		this.cafe_num = num;
	}
	public String getCafe_cr_num() {
		return cafe_cr_num;
	}
	public void setCafe_cr_num(String id) {
		this.cafe_cr_num = id;
	}
	public String getCafe_name() {
		return cafe_name;
	}
	public void setCafe_name(String rol) {
		this.cafe_name = rol;
	}
	public String getCafe_position() {
		return cafe_position;
	}
	public void setCafe_position(String rol) {
		this.cafe_position = rol;
	}
	public String getCafe_adul_fee() {
		return cafe_adul_fee;
	}
	public void setCafe_adul_fee(String rol) {
		this.cafe_adul_fee = rol;
	}
	public String getCafe_chi_fee() {
		return cafe_chi_fee;
	}
	public void setCafe_chi_fee(String rol) {
		this.cafe_chi_fee = rol;
	}
	public String getCafe_phone() {
		return cafe_phone;
	}
	public void setCafe_phone(String rol) {
		this.cafe_phone = rol;
	}
	public int getCafe_bh_start() {
		return cafe_bh_start;
	}
	public void setCafe_bh_start(int rol) {
		this.cafe_bh_start = rol;
	}
	public int getCafe_bh_end() {
		return cafe_bh_end;
	}
	public void setCafe_bh_end(int rol) {
		this.cafe_bh_end = rol;
	}
	public String getWifi() {
		return wifi;
	}
	public void setWifi(String rol) {
		this.wifi = rol;
	}
	public String getParking() {
		return parking;
	}
	public void setParking(String rol) {
		this.parking = rol;
	}
	public String getEat() {
		return eat;
	}
	public void setEat(String rol) {
		this.eat = rol;
	}
	public String getMoim() {
		return moim;
	}
	public void setMoim(String rol) {
		this.moim = rol;
	}
	public String getParty() {
		return party;
	}
	public void setParty(String rol) {
		this.party = rol;
	}
	public Timestamp getReg_date() {
		return reg_date;
	}
	public void setReg_date(Timestamp rol) {
		this.reg_date = rol;
	}
	public String getCon_join() {
		return con_join;
	}
	public void setCon_join(String rol) {
		this.con_join = rol;
	}
	public Timestamp getCafe_con_start() {
		return cafe_con_start;
	}
	public void setCafe_con_start(Timestamp reg_date) {
		this.cafe_con_start = reg_date;
	}
	public Timestamp getCafe_con_end() {
		return cafe_con_end;
	}
	public void setCafe_con_end(Timestamp reg_date) {
		this.cafe_con_end = reg_date;
	}
}

