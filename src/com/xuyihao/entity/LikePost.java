package com.xuyihao.entity;

/**
 * created by xuyihao on 2016/4/28
 */
public class LikePost {
	public static final String BASE_TABLE_NAME = "LikePost";
	public static final String BASE_LIKE_POST_PHYSICAL_ID = "_id";
	public static final String BASE_LIKE_POST_ID = "Like_ID";
	public static final String BASE_LIKE_POST_ACCOUNT_ID = "Acc_ID";
	public static final String BASE_LIKE_POST_PUBLISHER_REP_ID = "Rep_ID";
	public static final String BASE_LIKE_POST_POST_ID = "Post_ID";
	public static final String BASE_LIKE_POST_RYB_COUNT = "Like_ryb";
	public static final String BASE_LIKE_POST_ADD_TIME = "Like_addTime";
	private long _id;// 物理主键(mysql表主键)
	private String Like_ID;// 逻辑主键
	private String Acc_ID;
	private String Rep_ID;
	private String Post_ID;
	private int Like_ryb;
	private String Like_addTime;

	public LikePost() {
		this._id = 0;
		this.Like_ID = "";
		this.Acc_ID = "";
		this.Rep_ID = "";
		this.Post_ID = "";
		this.Like_ryb = 0;
		this.Like_addTime = "";
	}

	public LikePost(LikePost lp) {
		this._id = lp.get_id();
		this.Like_ryb = lp.getLike_ryb();
		this.Post_ID = lp.getPost_ID();
		this.Rep_ID = lp.getRep_ID();
		this.Acc_ID = lp.getAcc_ID();
		this.Like_ID = lp.getLike_ID();
		this.Like_addTime = lp.getLike_addTime();
	}

	/**
	 * 将所有字段以JSON格式返回
	 * 
	 * @return
	 */
	public String toJSONString() {
		String returnString = "{\"" + BASE_LIKE_POST_PHYSICAL_ID + "\":\"" + this._id + "\", \"" + BASE_LIKE_POST_ID
				+ "\":\"" + this.Like_ID + "\", \"" + BASE_LIKE_POST_ACCOUNT_ID + "\":\"" + this.Acc_ID + "\", \""
				+ BASE_LIKE_POST_PUBLISHER_REP_ID + "\":\"" + this.Rep_ID + "\", \"" + BASE_LIKE_POST_POST_ID + "\":\""
				+ this.Post_ID + "\", \"" + BASE_LIKE_POST_RYB_COUNT + "\":\"" + this.Like_ryb + "\", \""
				+ BASE_LIKE_POST_ADD_TIME + "\":\"" + this.Like_addTime + "\"}";
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

	public String getPost_ID() {
		return Post_ID;
	}

	public void setPost_ID(String post_ID) {
		Post_ID = post_ID;
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
