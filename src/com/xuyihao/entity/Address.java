package com.xuyihao.entity;

/*
 * created by xuyihao on 2016/4/29
 * */
public class Address {
	public static final String BASE_TABLE_NAME = "Address";
	public static final String BASE_ADDRESS_PHYSICAL_ID = "_id";
	public static final String BASE_ADDRESS_ID = "Add_ID";
	public static final String BASE_ADDRESS_INFORMATION = "Add_info";
	public static final String BASE_ADDRESS_ACCOUNT_ID = "Acc_ID";
	public static final String BASE_ADDRESS_CONSIGN = "Consign";
	public static final String BASE_ADDRESS_CONSIGN_TELEPHONE = "Con_tel";
	public static final String BASE_ADDRESS_ADD_TIME = "Add_addTime";
	private long _id;// 物理主键(mysql表主键)
	private String Add_ID;// 逻辑主键
	private String Add_info;
	private String Acc_ID;
	private String Consign;
	private String Con_tel;
	private String Add_addTime;

	public Address() {
		this._id = 0;
		this.Add_ID = "";
		this.Acc_ID = "";
		this.Add_info = "";
		this.Consign = "";
		this.Con_tel = "";
		this.Add_addTime = "";
	}

	public Address(Address address) {
		this._id = address.get_id();
		this.Con_tel = address.getCon_tel();
		this.Consign = address.getCon_tel();
		this.Add_info = address.getAdd_info();
		this.Acc_ID = address.getAcc_ID();
		this.Add_ID = address.getAdd_ID();
		this.Add_addTime = address.getAdd_addTime();
	}

	/**
	 * 将所有字段以JSON格式返回
	 * 
	 * @return
	 */
	public String toJSONString() {
		String returnString = "{\"" + BASE_ADDRESS_PHYSICAL_ID + "\":\"" + this._id + "\", \"" + BASE_ADDRESS_ID + "\":\""
				+ this.Add_ID + "\", \"" + BASE_ADDRESS_INFORMATION + "\":\"" + this.Add_info + "\", \""
				+ BASE_ADDRESS_ACCOUNT_ID + "\":\"" + this.Acc_ID + "\", \"" + BASE_ADDRESS_CONSIGN + "\":\"" + this.Consign
				+ "\", \"" + BASE_ADDRESS_CONSIGN_TELEPHONE + "\":\"" + this.Con_tel + "\", \"" + BASE_ADDRESS_ADD_TIME
				+ "\":\"" + this.Add_addTime + "\"}";
		return returnString;
	}

	public long get_id() {
		return _id;
	}

	public void set_id(long _id) {
		this._id = _id;
	}

	public String getAdd_ID() {
		return Add_ID;
	}

	public void setAdd_ID(String add_ID) {
		Add_ID = add_ID;
	}

	public String getAdd_info() {
		return Add_info;
	}

	public void setAdd_info(String add_info) {
		Add_info = add_info;
	}

	public String getAcc_ID() {
		return Acc_ID;
	}

	public void setAcc_ID(String acc_ID) {
		Acc_ID = acc_ID;
	}

	public String getConsign() {
		return Consign;
	}

	public void setConsign(String consign) {
		Consign = consign;
	}

	public String getCon_tel() {
		return Con_tel;
	}

	public void setCon_tel(String con_tel) {
		Con_tel = con_tel;
	}

	public String getAdd_addTime() {
		return Add_addTime;
	}

	public void setAdd_addTime(String add_addTime) {
		Add_addTime = add_addTime;
	}
}
