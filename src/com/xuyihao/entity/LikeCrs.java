package com.xuyihao.entity;

/**
 * created by xuyihao on 2016/4/28
 */
public class LikeCrs {
	public static final String BASE_TABLE_NAME = "LikeCrs";
	public static final String BASE_LIKE_COURSE_PHYSICAL_ID = "_id";
	public static final String BASE_LIKE_COURSE_ID = "Like_ID";
	public static final String BASE_LIKE_COURSE_ACCOUNT_ID = "Acc_ID";
	public static final String BASE_LIKE_COURSE_PUBLISHER_REP_ID = "Rep_ID";
	public static final String BASE_LIKE_COURSE_COURSE_ID = "Crs_ID";
	public static final String BASE_LIKE_COURSE_RYB_COUNT = "Like_ryb";
	public static final String BASE_LIKE_COURSE_ADD_TIME = "Like_addTime";
	private long _id;// 物理主键(mysql表主键)
	private String Like_ID;// 逻辑主键
	private String Acc_ID;
	private String Rep_ID;
	private String Crs_ID;
	private int Like_ryb;
	private String Like_addTime;

	public LikeCrs() {
		this._id = 0;
		this.Like_ID = "";
		this.Acc_ID = "";
		this.Rep_ID = "";
		this.Crs_ID = "";
		this.Like_ryb = 0;
		this.Like_addTime = "";
	}

	public LikeCrs(LikeCrs lc) {
		this._id = lc.get_id();
		this.Like_ryb = lc.getLike_ryb();
		this.Crs_ID = lc.getCrs_ID();
		this.Rep_ID = lc.getRep_ID();
		this.Acc_ID = lc.getAcc_ID();
		this.Like_ID = lc.getLike_ID();
		this.Like_addTime = lc.getLike_addTime();
	}

	/**
	 * 将所有字段以JSON格式返回
	 * 
	 * @return
	 */
	public String toJSONString() {
		String returnString = "{\"" + BASE_LIKE_COURSE_PHYSICAL_ID + "\":\"" + this._id + "\", \"" + BASE_LIKE_COURSE_ID
				+ "\":\"" + this.Like_ID + "\", \"" + BASE_LIKE_COURSE_ACCOUNT_ID + "\":\"" + this.Acc_ID + "\", \""
				+ BASE_LIKE_COURSE_PUBLISHER_REP_ID + "\":\"" + this.Rep_ID + "\", \"" + BASE_LIKE_COURSE_COURSE_ID + "\":\""
				+ this.Crs_ID + "\", \"" + BASE_LIKE_COURSE_RYB_COUNT + "\":\"" + this.Like_ryb + "\", \""
				+ BASE_LIKE_COURSE_ADD_TIME + "\":\"" + this.Like_addTime + "\"}";
		return returnString;
	}

	public long get_id() {
		return _id;
	}

	public void set_id(long _id) {
		this._id = _id;
	}

	public String getLike_ID() {
		return Like_ID;
	}

	public void setLike_ID(String like_ID) {
		Like_ID = like_ID;
	}

	public String getAcc_ID() {
		return Acc_ID;
	}

	public void setAcc_ID(String acc_ID) {
		Acc_ID = acc_ID;
	}

	public String getRep_ID() {
		return Rep_ID;
	}

	public void setRep_ID(String rep_ID) {
		Rep_ID = rep_ID;
	}

	public String getCrs_ID() {
		return Crs_ID;
	}

	public void setCrs_ID(String crs_ID) {
		Crs_ID = crs_ID;
	}

	public int getLike_ryb() {
		return Like_ryb;
	}

	public void setLike_ryb(int like_ryb) {
		Like_ryb = like_ryb;
	}

	public String getLike_addTime() {
		return Like_addTime;
	}

	public void setLike_addTime(String like_addTime) {
		Like_addTime = like_addTime;
	}
}
