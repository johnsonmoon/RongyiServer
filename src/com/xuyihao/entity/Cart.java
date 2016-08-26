package com.xuyihao.entity;

/**
 * created by xuyihao on 2016/4/30 购物车的java bean
 */
public class Cart {
	public static final String BASE_TABLE_NAME = "Cart";
	public static final String BASE_CART_PHYSICAL_ID = "_id";
	public static final String BASE_CART_ID = "Cart_ID";
	public static final String BASE_CART_PRODUCT_ID = "Prod_ID";
	public static final String BASE_CART_ACCOUNT_ID = "Acc_ID";
	public static final String BASE_CART_PRODUCT_PRICE = "Prod_price";
	public static final String BASE_CART_PRODUCT_NUMBER = "Pro_num";
	public static final String BASE_CART_ADD_TIME = "Cart_addTime";
	private long _id;// 物理主键(mysql表主键)
	private String Cart_ID;// 逻辑主键
	private String Prod_ID;
	private String Acc_ID;
	private float Prod_price;
	private int Pro_num;
	private String Cart_addTime;

	public Cart() {
		this._id = 0;
		this.Cart_ID = "";
		this.Prod_ID = "";
		this.Acc_ID = "";
		this.Prod_price = 0f;
		this.Pro_num = 0;
		this.Cart_addTime = "";
	}

	public Cart(Cart cart) {
		this._id = cart.get_id();
		this.Cart_ID = cart.getCart_ID();
		this.Prod_ID = cart.getProd_ID();
		this.Acc_ID = cart.getAcc_ID();
		this.Prod_price = cart.getProd_price();
		this.Pro_num = cart.getPro_num();
		this.Cart_addTime = cart.getCart_addTime();
	}

	/**
	 * 将所有字段以JSON格式返回
	 * 
	 * @return
	 */
	public String toJSONString() {
		String returnString = "{\"" + BASE_CART_PHYSICAL_ID + "\":\"" + this._id + "\", \"" + BASE_CART_ID + "\":\""
				+ this.Cart_ID + "\", \"" + BASE_CART_PRODUCT_ID + "\":\"" + this.Prod_ID + "\", \"" + BASE_CART_ACCOUNT_ID
				+ "\":\"" + this.Acc_ID + "\", \"" + BASE_CART_PRODUCT_PRICE + "\":\"" + this.Prod_price + "\", \""
				+ BASE_CART_PRODUCT_NUMBER + "\":\"" + this.Pro_num + "\", \"" + BASE_CART_ADD_TIME + "\":\""
				+ this.Cart_addTime + "\"}";
		return returnString;
	}

	public long get_id() {
		return _id;
	}

	public void set_id(long _id) {
		this._id = _id;
	}

	public String getCart_ID() {
		return Cart_ID;
	}

	public void setCart_ID(String cart_ID) {
		Cart_ID = cart_ID;
	}

	public String getProd_ID() {
		return Prod_ID;
	}

	public void setProd_ID(String prod_ID) {
		Prod_ID = prod_ID;
	}

	public String getAcc_ID() {
		return Acc_ID;
	}

	public void setAcc_ID(String acc_ID) {
		Acc_ID = acc_ID;
	}

	public float getProd_price() {
		return Prod_price;
	}

	public void setProd_price(float prod_price) {
		Prod_price = prod_price;
	}

	public int getPro_num() {
		return Pro_num;
	}

	public void setPro_num(int pro_num) {
		Pro_num = pro_num;
	}

	public String getCart_addTime() {
		return Cart_addTime;
	}

	public void setCart_addTime(String cart_addTime) {
		Cart_addTime = cart_addTime;
	}
}
