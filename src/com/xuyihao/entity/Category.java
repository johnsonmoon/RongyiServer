package com.xuyihao.entity;

/*
 * created by xuyihao on 2016/4/28
 * 商品分类的java bean
 * */
public class Category {
	public static final String BASE_TABLE_NAME = "Category";
	public static final String BASE_CATEGORY_PHYSICAL_ID = "_id";
	public static final String BASE_CATEGORY_ID = "Cat_ID";
	public static final String BASE_CATEGORY_NAME = "Cat_name";
	public static final String BASE_CATEGORY_DESCRIPTION = "Cat_desc";
	public static final String BASE_CATEGORY_ADD_TIME = "Cat_addTime";
	private long _id;// 物理主键(mysql表主键)
	private String Cat_ID;// 逻辑主键
	private String Cat_name;
	private String Cat_desc;
	private String Cat_addTime;

	public Category() {
		this._id = 0;
		this.Cat_ID = "";
		this.Cat_name = "";
		this.Cat_desc = "";
		this.Cat_addTime = "";
	}

	public Category(Category ca) {
		this._id = ca.get_id();
		this.Cat_desc = ca.getCat_desc();
		this.Cat_name = ca.getCat_name();
		this.Cat_ID = ca.getCat_ID();
		this.Cat_addTime = ca.getCat_addTime();
	}

	/**
	 * 将所有字段以JSON格式返回
	 * 
	 * @return
	 */
	public String toJSONString() {
		String returnString = "{\"" + BASE_CATEGORY_PHYSICAL_ID + "\":\"" + this._id + "\", \"" + BASE_CATEGORY_ID
				+ "\":\"" + this.Cat_ID + "\", \"" + BASE_CATEGORY_NAME + "\":\"" + this.Cat_name + "\", \""
				+ BASE_CATEGORY_DESCRIPTION + "\":\"" + this.Cat_desc + "\", \"" + BASE_CATEGORY_ADD_TIME + "\":\""
				+ this.Cat_addTime + "\"}";
		return returnString;
	}

	public long get_id() {
		return _id;
	}

	public void set_id(long _id) {
		this._id = _id;
	}

	public String getCat_ID() {
		return Cat_ID;
	}

	public void setCat_ID(String cat_ID) {
		Cat_ID = cat_ID;
	}

	public String getCat_name() {
		return Cat_name;
	}

	public void setCat_name(String cat_name) {
		Cat_name = cat_name;
	}

	public String getCat_desc() {
		return Cat_desc;
	}

	public void setCat_desc(String cat_desc) {
		Cat_desc = cat_desc;
	}

	public String getCat_addTime() {
		return Cat_addTime;
	}

	public void setCat_addTime(String cat_addTime) {
		Cat_addTime = cat_addTime;
	}
}
