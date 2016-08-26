package com.xuyihao.entity;

/**
 * created by xuyihao on 2016/4/1
 */
public class Accounts {
	public static final String BASE_TABLE_NAME = "Accounts";
	public static final String BASE_ACCOUNT_PHYSICAL_ID = "_id";
	public static final String BASE_ACCOUNT_ID = "Acc_ID";
	public static final String BASE_ACCOUNT_NAME = "Acc_name";
	public static final String BASE_ACCOUNT_PASSWORD = "Acc_pwd";
	public static final String BASE_ACCOUNT_SEX = "Acc_sex";
	public static final String BASE_ACCOUNT_LOCATION = "Acc_loc";
	public static final String BASE_ACCOUNT_LEVEL = "Acc_lvl";
	public static final String BASE_ACCOUNT_RYB = "Acc_ryb";
	public static final String BASE_ACCOUNT_IS_SHOP_OWNER = "Acc_shop";
	public static final String BASE_ACCOUNT_ATTENTION_COUNT = "Acc_atn";
	public static final String BASE_ACCOUNT_ATTENED_COUNT = "Acc_atnd";
	public static final String BASE_ACCOUNT_PUBLISH = "Acc_pub";
	public static final String BASE_ACCOUNT_NO = "Acc_no";
	public static final String BASE_ACCOUNT_NAME2 = "Acc_name2";
	public static final String BASE_ACCOUNT_TELEPHONE = "Acc_tel";
	public static final String BASE_ACCOUNT_ADD_TIME = "Acc_addTime";
	private long _id;// 物理主键(mysql表主键)
	private String Acc_ID;// 逻辑主键
	private String Acc_name;
	private String Acc_pwd;
	private String Acc_sex;
	private String Acc_loc;
	private int Acc_lvl;
	private int Acc_ryb;
	private boolean Acc_shop;
	private int Acc_atn;
	private int Acc_atnd;
	private int Acc_pub;
	private String Acc_no;
	private String Acc_name2;
	private String Acc_tel;
	private String Acc_addTime;

	/*
	 * constructor
	 */
	public Accounts() {
		this._id = 0;
		this.Acc_ID = "";
		this.Acc_name = "";
		this.Acc_pwd = "";
		this.Acc_sex = "";
		this.Acc_loc = "";
		this.Acc_lvl = 0;
		this.Acc_ryb = 0;
		this.Acc_shop = false;
		this.Acc_atn = 0;
		this.Acc_atnd = 0;
		this.Acc_pub = 0;
		this.Acc_no = "";
		this.Acc_name2 = "";
		this.Acc_tel = "";
		this.Acc_addTime = "";
	}

	public Accounts(Accounts accounts) {
		this._id = accounts.get_id();
		this.Acc_name = accounts.getAcc_name();
		this.Acc_pwd = accounts.getAcc_pwd();
		this.Acc_sex = accounts.getAcc_sex();
		this.Acc_loc = accounts.getAcc_loc();
		this.Acc_lvl = accounts.getAcc_lvl();
		this.Acc_ryb = accounts.getAcc_ryb();
		this.Acc_shop = accounts.isAcc_shop();
		this.Acc_atn = accounts.getAcc_atn();
		this.Acc_atnd = accounts.getAcc_atnd();
		this.Acc_pub = accounts.getAcc_pub();
		this.Acc_no = accounts.getAcc_no();
		this.Acc_name2 = accounts.getAcc_name2();
		this.Acc_tel = accounts.getAcc_tel();
		this.Acc_addTime = accounts.getAcc_addTime();
	}

	/**
	 * 将所有字段以JSON格式返回
	 * 
	 * @return
	 */
	public String toJSONString() {
		String value = "{\"" + BASE_ACCOUNT_PHYSICAL_ID + "\":\"" + this._id + "\", \"" + BASE_ACCOUNT_ID + "\":\""
				+ this.Acc_ID + "\", \"" + BASE_ACCOUNT_NAME + "\":\"" + this.Acc_name + "\", \"" + BASE_ACCOUNT_PASSWORD
				+ "\":\"" + this.Acc_pwd + "\", \"" + BASE_ACCOUNT_SEX + "\":\"" + this.Acc_sex + "\", \""
				+ BASE_ACCOUNT_LOCATION + "\":\"" + this.Acc_loc + "\", \"" + BASE_ACCOUNT_LEVEL + "\":\"" + this.Acc_lvl
				+ "\", \"" + BASE_ACCOUNT_RYB + "\":\"" + this.Acc_ryb + "\", \"" + BASE_ACCOUNT_IS_SHOP_OWNER + "\":\""
				+ this.Acc_shop + "\", \"" + BASE_ACCOUNT_ATTENTION_COUNT + "\":\"" + this.Acc_atn + "\", \""
				+ BASE_ACCOUNT_ATTENED_COUNT + "\":\"" + this.Acc_atnd + "\", \"" + BASE_ACCOUNT_PUBLISH + "\":\""
				+ this.Acc_pub + "\", \"" + BASE_ACCOUNT_NO + "\":\"" + this.Acc_no + "\", \"" + BASE_ACCOUNT_NAME2 + "\":\""
				+ this.Acc_name2 + "\", \"" + BASE_ACCOUNT_TELEPHONE + "\":\"" + this.Acc_tel + "\", \"" + BASE_ACCOUNT_ADD_TIME
				+ "\":\"" + this.Acc_addTime + "\"}";
		return value;
	}

	public long get_id() {
		return _id;
	}

	public void set_id(long _id) {
		this._id = _id;
	}

	public String getAcc_ID() {
		return Acc_ID;
	}

	public void setAcc_ID(String acc_ID) {
		Acc_ID = acc_ID;
	}

	public String getAcc_name() {
		return Acc_name;
	}

	public void setAcc_name(String acc_name) {
		Acc_name = acc_name;
	}

	public String getAcc_pwd() {
		return Acc_pwd;
	}

	public void setAcc_pwd(String acc_pwd) {
		Acc_pwd = acc_pwd;
	}

	public String getAcc_sex() {
		return Acc_sex;
	}

	public void setAcc_sex(String acc_sex) {
		Acc_sex = acc_sex;
	}

	public String getAcc_loc() {
		return Acc_loc;
	}

	public void setAcc_loc(String acc_loc) {
		Acc_loc = acc_loc;
	}

	public int getAcc_lvl() {
		return Acc_lvl;
	}

	public void setAcc_lvl(int acc_lvl) {
		Acc_lvl = acc_lvl;
	}

	public int getAcc_ryb() {
		return Acc_ryb;
	}

	public void setAcc_ryb(int acc_ryb) {
		Acc_ryb = acc_ryb;
	}

	public boolean isAcc_shop() {
		return Acc_shop;
	}

	public void setAcc_shop(boolean acc_shop) {
		Acc_shop = acc_shop;
	}

	public int getAcc_atn() {
		return Acc_atn;
	}

	public void setAcc_atn(int acc_atn) {
		Acc_atn = acc_atn;
	}

	public int getAcc_atnd() {
		return Acc_atnd;
	}

	public void setAcc_atnd(int acc_atnd) {
		Acc_atnd = acc_atnd;
	}

	public int getAcc_pub() {
		return Acc_pub;
	}

	public void setAcc_pub(int acc_pub) {
		Acc_pub = acc_pub;
	}

	public String getAcc_no() {
		return Acc_no;
	}

	public void setAcc_no(String acc_no) {
		Acc_no = acc_no;
	}

	public String getAcc_name2() {
		return Acc_name2;
	}

	public void setAcc_name2(String acc_name2) {
		Acc_name2 = acc_name2;
	}

	public String getAcc_tel() {
		return Acc_tel;
	}

	public void setAcc_tel(String acc_tel) {
		Acc_tel = acc_tel;
	}

	public String getAcc_addTime() {
		return Acc_addTime;
	}

	public void setAcc_addTime(String acc_addTime) {
		Acc_addTime = acc_addTime;
	}
}
