package com.xuyihao.entity;

/**
 * created by xuyihao on 2016/4/28 产品的java bean
 */
public class Products {
	public static final String BASE_TABLE_NAME = "Products";
	public static final String BASE_PRODUCT_PHYSICAL_ID = "_id";
	public static final String BASE_PRODUCT_ID = "Prod_ID";
	public static final String BASE_PRODUCT_CATEGORY_ID = "Cat_ID";
	public static final String BASE_PRODUCT_SHOP_ID = "Shop_ID";
	public static final String BASE_PRODUCT_NAME = "Prod_name";
	public static final String BASE_PRODUCT_DESCRIPTION = "Prod_desc";
	public static final String BASE_PRODUCT_INFORMATION = "Prod_info";
	public static final String BASE_PRODUCT_PRICE = "Prod_price";
	public static final String BASE_PRODUCT_REMAIN_NUMBER = "Prod_num";
	public static final String BASE_PRODUCT_SOLD_COUNT = "Prod_sold";
	public static final String BASE_PRODUCT_ADD_TIME = "Prod_addTime";
	private long _id;// 物理主键(mysql表主键)
	private String Prod_ID;// 逻辑主键
	private String Cat_ID;
	private String Shop_ID;
	private String Prod_name;
	private String Prod_desc;
	private String Prod_info;
	private float Prod_price;
	private int Prod_num;
	private int Prod_sold;
	private String Prod_addTime;

	public Products() {
		this._id = 0;
		this.Prod_ID = "";
		this.Cat_ID = "";
		this.Shop_ID = "";
		this.Prod_name = "";
		this.Prod_desc = "";
		this.Prod_info = "";
		this.Prod_price = 0.0f;
		this.Prod_num = 0;
		this.Prod_sold = 0;
		this.Prod_addTime = "";
	}

	public Products(Products pd) {
		this._id = pd.get_id();
		this.Prod_ID = pd.getProd_ID();
		this.Cat_ID = pd.getCat_ID();
		this.Shop_ID = pd.getShop_ID();
		this.Prod_name = pd.getProd_name();
		this.Prod_desc = pd.getProd_desc();
		this.Prod_info = pd.getProd_info();
		this.Prod_price = pd.getProd_price();
		this.Prod_num = pd.getProd_num();
		this.Prod_sold = pd.getProd_sold();
		this.Prod_addTime = pd.getProd_addTime();
	}

	/**
	 * 将所有字段以JSON格式返回
	 * 
	 * @return
	 */
	public String toJSONString() {
		String returnString = "{\"" + BASE_PRODUCT_PHYSICAL_ID + "\":\"" + this._id + "\", \"" + BASE_PRODUCT_ID + "\":\""
				+ this.Prod_ID + "\", \"" + BASE_PRODUCT_CATEGORY_ID + "\":\"" + this.Cat_ID + "\", \"" + BASE_PRODUCT_SHOP_ID
				+ "\":\"" + this.Shop_ID + "\", \"" + BASE_PRODUCT_NAME + "\":\"" + this.Prod_name + "\", \""
				+ BASE_PRODUCT_DESCRIPTION + "\":\"" + this.Prod_desc + "\", \"" + BASE_PRODUCT_INFORMATION + "\":\""
				+ this.Prod_info + "\", \"" + BASE_PRODUCT_PRICE + "\":\"" + this.Prod_price + "\", \""
				+ BASE_PRODUCT_REMAIN_NUMBER + "\":\"" + this.Prod_num + "\", \"" + BASE_PRODUCT_SOLD_COUNT + "\":\""
				+ this.Prod_sold + "\", \"" + BASE_PRODUCT_ADD_TIME + "\":\"" + this.Prod_addTime + "\"}";
		return returnString;
	}

	public long get_id() {
		return _id;
	}

	public void set_id(long _id) {
		this._id = _id;
	}

	public String getProd_ID() {
		return Prod_ID;
	}

	public void setProd_ID(String prod_ID) {
		Prod_ID = prod_ID;
	}

	public String getCat_ID() {
		return Cat_ID;
	}

	public void setCat_ID(String cat_ID) {
		Cat_ID = cat_ID;
	}

	public String getShop_ID() {
		return Shop_ID;
	}

	public void setShop_ID(String shop_ID) {
		Shop_ID = shop_ID;
	}

	public String getProd_name() {
		return Prod_name;
	}

	public void setProd_name(String prod_name) {
		Prod_name = prod_name;
	}

	public String getProd_desc() {
		return Prod_desc;
	}

	public void setProd_desc(String prod_desc) {
		Prod_desc = prod_desc;
	}

	public String getProd_info() {
		return Prod_info;
	}

	public void setProd_info(String prod_info) {
		Prod_info = prod_info;
	}

	public float getProd_price() {
		return Prod_price;
	}

	public void setProd_price(float prod_price) {
		Prod_price = prod_price;
	}

	public int getProd_num() {
		return Prod_num;
	}

	public void setProd_num(int prod_num) {
		Prod_num = prod_num;
	}

	public int getProd_sold() {
		return Prod_sold;
	}

	public void setProd_sold(int prod_sold) {
		Prod_sold = prod_sold;
	}

	public String getProd_addTime() {
		return Prod_addTime;
	}

	public void setProd_addTime(String prod_addTime) {
		Prod_addTime = prod_addTime;
	}
}
