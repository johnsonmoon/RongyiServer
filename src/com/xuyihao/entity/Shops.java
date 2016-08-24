package com.xuyihao.entity;

/*
 * create by xuyihao on 2016/4/28
 * 商家的java bean
 * */
public class Shops {
	public static final String BASE_TABLE_NAME = "Shops";
	public static final String BASE_SHOP_PHYSICAL_ID = "_id";
	public static final String BASE_SHOP_ID = "Shop_ID";
	public static final String BASE_SHOP_NAME = "Shop_name";
	public static final String BASE_SHOP_INFORMATION = "Shop_info";
	public static final String BASE_SHOP_LICENSE = "Shop_licen";
	public static final String BASE_SHOP_LEVEL = "Shop_lvl";
	public static final String BASE_SHOP_RYB = "Shop_ryb";
	public static final String BASE_SHOP_FAVOURITED_COUNT = "Shop_favo";
	public static final String BASE_SHOP_OWNER_ACCOUNT_ID = "Acc_ID";
	public static final String BASE_SHOP_ADD_TIME = "Shop_addTime";
	private long _id;// 物理主键(mysql表主键)
	private String Shop_ID;// 逻辑主键
	private String Shop_name;
	private String Shop_info;
	private String Shop_licen;
	private int Shop_lvl;
	private int Shop_ryb;
	private int Shop_favo;
	private String Acc_ID;
	private String Shop_addTime;

	public Shops() {
		this._id = 0;
		this.Shop_ID = "";
		this.Shop_name = "";
		this.Shop_info = "";
		this.Shop_licen = "";
		this.Shop_lvl = 0;
		this.Shop_ryb = 0;
		this.Shop_favo = 0;
		this.Acc_ID = "";
		this.Shop_addTime = "";
	}

	public Shops(Shops shops) {
		this._id = shops.get_id();
		this.Shop_ID = shops.getShop_ID();
		this.Shop_name = shops.getShop_name();
		this.Shop_info = shops.getShop_info();
		this.Shop_licen = shops.getShop_licen();
		this.Shop_lvl = shops.getShop_lvl();
		this.Shop_ryb = shops.getShop_ryb();
		this.Shop_favo = shops.getShop_favo();
		this.Acc_ID = shops.getAcc_ID();
		this.Shop_addTime = shops.getShop_addTime();
	}

	/**
	 * 将所有字段以JSON格式返回
	 * 
	 * @return
	 */
	public String toJSONString() {
		String returnString = "{\"" + BASE_SHOP_PHYSICAL_ID + "\":\"" + this._id + "\", \"" + BASE_SHOP_ID + "\":\""
				+ this.Shop_ID + "\", \"" + BASE_SHOP_NAME + "\":\"" + this.Shop_name + "\", \"" + BASE_SHOP_INFORMATION
				+ "\":\"" + this.Shop_info + "\", \"" + BASE_SHOP_LICENSE + "\":\"" + this.Shop_licen + "\", \""
				+ BASE_SHOP_LEVEL + "\":\"" + this.Shop_lvl + "\", \"" + BASE_SHOP_RYB + "\":\"" + this.Shop_ryb + "\", \""
				+ BASE_SHOP_FAVOURITED_COUNT + "\":\"" + this.Shop_favo + "\", \"" + BASE_SHOP_OWNER_ACCOUNT_ID + "\":\""
				+ this.Acc_ID + "\", \"" + BASE_SHOP_ADD_TIME + "\":\"" + this.Shop_addTime + "\"}";
		return returnString;
	}

	public long get_id() {
		return _id;
	}

	public void set_id(long _id) {
		this._id = _id;
	}

	public String getShop_ID() {
		return Shop_ID;
	}

	public void setShop_ID(String shop_ID) {
		Shop_ID = shop_ID;
	}

	public String getShop_name() {
		return Shop_name;
	}

	public void setShop_name(String shop_name) {
		Shop_name = shop_name;
	}

	public String getShop_info() {
		return Shop_info;
	}

	public void setShop_info(String shop_info) {
		Shop_info = shop_info;
	}

	public String getShop_licen() {
		return Shop_licen;
	}

	public void setShop_licen(String shop_licen) {
		Shop_licen = shop_licen;
	}

	public int getShop_lvl() {
		return Shop_lvl;
	}

	public void setShop_lvl(int shop_lvl) {
		Shop_lvl = shop_lvl;
	}

	public int getShop_ryb() {
		return Shop_ryb;
	}

	public void setShop_ryb(int shop_ryb) {
		Shop_ryb = shop_ryb;
	}

	public int getShop_favo() {
		return Shop_favo;
	}

	public void setShop_favo(int shop_favo) {
		Shop_favo = shop_favo;
	}

	public String getAcc_ID() {
		return Acc_ID;
	}

	public void setAcc_ID(String acc_ID) {
		Acc_ID = acc_ID;
	}

	public String getShop_addTime() {
		return Shop_addTime;
	}

	public void setShop_addTime(String shop_addTime) {
		Shop_addTime = shop_addTime;
	}
}
