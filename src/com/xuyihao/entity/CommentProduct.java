package com.xuyihao.entity;

/*
 * created by xuyihao on 2016/4/30
 * 评论产品（交易完成）的java bean
 * */
public class CommentProduct {
	public static final String BASE_TABLE_NAME = "CommentProduct";
	public static final String BASE_COMMENTPRODUCT_PHYSICAL_ID = "_id";
	public static final String BASE_COMMENTPRODUCT_ID = "Comm_ID";
	public static final String BASE_COMMENTPRODUCT_DESCRIPTION = "Comm_desc";
	public static final String BASE_COMMENTPRODUCT_ACCOUNT_ID = "Acc_ID";
	public static final String BASE_COMMENTPRODUCT_PRODUCT_ID = "Prod_ID";
	public static final String BASE_COMMENTPRODUCT_ORDER_ID = "Ord_ID";
	public static final String BASE_COMMENTPRODUCT_ADD_TIME = "Comm_addTime";
	private long _id;// 物理主键(mysql表主键)
	private String Comm_ID;// 逻辑主键
	private String Comm_desc;
	private String Acc_ID;
	private String Prod_ID;
	private String Ord_ID;
	private String Comm_addTime;

	public CommentProduct() {
		this._id = 0;
		this.Comm_ID = "";
		this.Comm_desc = "";
		this.Acc_ID = "";
		this.Prod_ID = "";
		this.Ord_ID = "";
		this.Comm_addTime = "";
	}

	public CommentProduct(CommentProduct cp) {
		this._id = cp.get_id();
		this.Ord_ID = cp.getOrd_ID();
		this.Prod_ID = cp.getProd_ID();
		this.Acc_ID = cp.getAcc_ID();
		this.Comm_desc = cp.getComm_desc();
		this.Comm_ID = cp.getComm_ID();
		this.Comm_addTime = cp.getComm_addTime();
	}

	/**
	 * 将所有字段以JSON格式返回
	 * 
	 * @return
	 */
	public String toJSONString() {
		String returnString = "{\"" + BASE_COMMENTPRODUCT_PHYSICAL_ID + "\":\"" + this._id + "\", \""
				+ BASE_COMMENTPRODUCT_ID + "\":\"" + this.Comm_ID + "\", \"" + BASE_COMMENTPRODUCT_DESCRIPTION + "\":\""
				+ this.Comm_desc + "\", \"" + BASE_COMMENTPRODUCT_ACCOUNT_ID + "\":\"" + this.Acc_ID + "\", \""
				+ BASE_COMMENTPRODUCT_PRODUCT_ID + "\":\"" + this.Prod_ID + "\", \"" + BASE_COMMENTPRODUCT_ORDER_ID + "\":\""
				+ this.Ord_ID + "\", \"" + BASE_COMMENTPRODUCT_ADD_TIME + "\":\"" + this.Comm_addTime + "\"}";
		return returnString;
	}

	public long get_id() {
		return _id;
	}

	public void set_id(long _id) {
		this._id = _id;
	}

	public String getComm_ID() {
		return Comm_ID;
	}

	public void setComm_ID(String comm_ID) {
		Comm_ID = comm_ID;
	}

	public String getComm_desc() {
		return Comm_desc;
	}

	public void setComm_desc(String comm_desc) {
		Comm_desc = comm_desc;
	}

	public String getAcc_ID() {
		return Acc_ID;
	}

	public void setAcc_ID(String acc_ID) {
		Acc_ID = acc_ID;
	}

	public String getProd_ID() {
		return Prod_ID;
	}

	public void setProd_ID(String prod_ID) {
		Prod_ID = prod_ID;
	}

	public String getOrd_ID() {
		return Ord_ID;
	}

	public void setOrd_ID(String ord_ID) {
		Ord_ID = ord_ID;
	}

	public String getComm_addTime() {
		return Comm_addTime;
	}

	public void setComm_addTime(String comm_addTime) {
		Comm_addTime = comm_addTime;
	}
}
