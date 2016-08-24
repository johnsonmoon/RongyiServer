package com.xuyihao.entity;

/*
 * created by xuyihao on 2016/4/25
 * */
public class Courses {
	public static final String BASE_TABLE_NAME = "Courses";
	public static final String BASE_COURSES_PHYSICAL_ID = "_id";
	public static final String BASE_COURSES_ID = "Crs_ID";
	public static final String BASE_COURSES_NAME = "Crs_name";
	public static final String BASE_COURSES_ROUTE = "Crs_route";
	public static final String BASE_COURSES_ACCOUNT_ID = "Acc_ID";
	public static final String BASE_COURSES_AUTHOR_ACCOUNT_ID = "Author_ID";
	public static final String BASE_COURSES_REPOST_COUNT = "Crs_rep";
	public static final String BASE_COURSES_COMMON_COUNT = "Crs_comm";
	public static final String BASE_COURSES_LIKE_COUNT = "Crs_like";
	public static final String BASE_COURSES_ADD_TIME = "Crs_addTime";
	private long _id;// 物理主键(mysql表主键)
	private String Crs_ID;// 逻辑主键
	private String Crs_name;
	private String Crs_route;
	private String Acc_ID;
	private String Author_ID;
	private int Crs_rep;
	private int Crs_comm;
	private int Crs_like;
	private String Crs_addTime;

	public Courses() {
		this._id = 0;
		this.Crs_ID = "";
		this.Crs_name = "";
		this.Crs_route = "";
		this.Acc_ID = "";
		this.Author_ID = "";
		this.Crs_rep = 0;
		this.Crs_comm = 0;
		this.Crs_like = 0;
		this.Crs_addTime = "";
	}

	public Courses(Courses cou) {
		this._id = cou.get_id();
		this.Crs_ID = cou.getCrs_ID();
		this.Crs_name = cou.getCrs_name();
		this.Crs_route = cou.getCrs_route();
		this.Acc_ID = cou.getAcc_ID();
		this.Author_ID = cou.getAuthor_ID();
		this.Crs_rep = cou.getCrs_rep();
		this.Crs_comm = cou.getCrs_comm();
		this.Crs_like = cou.getCrs_like();
		this.Crs_addTime = cou.getCrs_addTime();
	}

	/**
	 * 将所有字段以JSON格式返回
	 * 
	 * @return
	 */
	public String toJSONString() {
		String returnString = "{\"" + BASE_COURSES_PHYSICAL_ID + "\":\"" + this._id + "\", \"" + BASE_COURSES_ID + "\":\""
				+ this.Crs_ID + "\", \"" + BASE_COURSES_NAME + "\":\"" + this.Crs_name + "\", \"" + BASE_COURSES_ROUTE
				+ "\":\"" + this.Crs_route + "\", \"" + BASE_COURSES_ACCOUNT_ID + "\":\"" + this.Acc_ID + "\", \""
				+ BASE_COURSES_AUTHOR_ACCOUNT_ID + "\":\"" + this.Author_ID + "\", \"" + BASE_COURSES_REPOST_COUNT + "\":\""
				+ this.Crs_rep + "\", \"" + BASE_COURSES_COMMON_COUNT + "\":\"" + this.Crs_comm + "\", \""
				+ BASE_COURSES_LIKE_COUNT + "\":\"" + this.Crs_like + "\", \"" + BASE_COURSES_ADD_TIME + "\":\""
				+ this.Crs_addTime + "\"}";
		return returnString;
	}

	public long get_id() {
		return _id;
	}

	public void set_id(long _id) {
		this._id = _id;
	}

	public String getCrs_ID() {
		return Crs_ID;
	}

	public void setCrs_ID(String crs_ID) {
		Crs_ID = crs_ID;
	}

	public String getCrs_name() {
		return Crs_name;
	}

	public void setCrs_name(String crs_name) {
		Crs_name = crs_name;
	}

	public String getCrs_route() {
		return Crs_route;
	}

	public void setCrs_route(String crs_route) {
		Crs_route = crs_route;
	}

	public String getAcc_ID() {
		return Acc_ID;
	}

	public void setAcc_ID(String acc_ID) {
		Acc_ID = acc_ID;
	}

	public String getAuthor_ID() {
		return Author_ID;
	}

	public void setAuthor_ID(String author_ID) {
		Author_ID = author_ID;
	}

	public int getCrs_rep() {
		return Crs_rep;
	}

	public void setCrs_rep(int crs_rep) {
		Crs_rep = crs_rep;
	}

	public int getCrs_comm() {
		return Crs_comm;
	}

	public void setCrs_comm(int crs_comm) {
		Crs_comm = crs_comm;
	}

	public int getCrs_like() {
		return Crs_like;
	}

	public void setCrs_like(int crs_like) {
		Crs_like = crs_like;
	}

	public String getCrs_addTime() {
		return Crs_addTime;
	}

	public void setCrs_addTime(String crs_addTime) {
		Crs_addTime = crs_addTime;
	}
}
