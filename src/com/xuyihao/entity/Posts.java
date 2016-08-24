package com.xuyihao.entity;

/*
 * created by xuyihao on 2016/4/26
 * */
public class Posts {
	public static final String BASE_TABLE_NAME = "Posts";
	public static final String BASE_POSTS_PHYSICAL_ID = "_id";
	public static final String BASE_POSTS_ID = "Post_ID";
	public static final String BASE_POSTS_NAME = "Post_name";
	public static final String BASE_POSTS_ROUTE = "Post_route";
	public static final String BASE_POSTS_ACCOUNT_ID = "Acc_ID";
	public static final String BASE_POSTS_AUTHOR_ACCOUNT_ID = "Author_ID";
	public static final String BASE_POSTS_REPOST_COUNT = "Post_rep";
	public static final String BASE_POSTS_COMMON_COUNT = "Post_comm";
	public static final String BASE_POSTS_LIKE_COUNT = "Post_like";
	public static final String BASE_POSTS_ADD_TIME = "Post_addTime";
	private long _id;// 物理主键(mysql表主键)
	private String Post_ID;// 逻辑主键
	private String Post_name;
	private String Post_route;
	private String Acc_ID;
	private String Author_ID;
	private int Post_rep;
	private int Post_comm;
	private int Post_like;
	private String Post_addTime;

	public Posts() {
		this._id = 0;
		this.Post_ID = "";
		this.Post_name = "";
		this.Post_route = "";
		this.Acc_ID = "";
		this.Author_ID = "";
		this.Post_rep = 0;
		this.Post_comm = 0;
		this.Post_like = 0;
		this.Post_addTime = "";
	}

	public Posts(Posts po) {
		this._id = po.get_id();
		this.Post_ID = po.getPost_ID();
		this.Post_name = po.getPost_name();
		this.Post_route = po.getPost_route();
		this.Acc_ID = po.getAcc_ID();
		this.Author_ID = po.getAuthor_ID();
		this.Post_rep = po.getPost_rep();
		this.Post_comm = po.getPost_comm();
		this.Post_like = po.getPost_like();
		this.Post_addTime = po.getPost_addTime();
	}

	/**
	 * 将所有字段以JSON格式返回
	 * 
	 * @return
	 */
	public String toJSONString() {
		String returnString = "{\"" + BASE_POSTS_PHYSICAL_ID + "\":\"" + this._id + "\", \"" + BASE_POSTS_ID + "\":\""
				+ this.Post_ID + "\", \"" + BASE_POSTS_NAME + "\":\"" + this.Post_name + "\", \"" + BASE_POSTS_ROUTE + "\":\""
				+ this.Post_route + "\", \"" + BASE_POSTS_ACCOUNT_ID + "\":\"" + this.Acc_ID + "\", \""
				+ BASE_POSTS_AUTHOR_ACCOUNT_ID + "\":\"" + this.Author_ID + "\", \"" + BASE_POSTS_REPOST_COUNT + "\":\""
				+ this.Post_rep + "\", \"" + BASE_POSTS_COMMON_COUNT + "\":\"" + this.Post_comm + "\", \""
				+ BASE_POSTS_LIKE_COUNT + "\":\"" + this.Post_like + "\", \"" + BASE_POSTS_ADD_TIME + "\":\""
				+ this.Post_addTime + "\"}";
		return returnString;
	}

	public long get_id() {
		return _id;
	}

	public void set_id(long _id) {
		this._id = _id;
	}

	public String getPost_ID() {
		return Post_ID;
	}

	public void setPost_ID(String post_ID) {
		Post_ID = post_ID;
	}

	public String getPost_name() {
		return Post_name;
	}

	public void setPost_name(String post_name) {
		Post_name = post_name;
	}

	public String getPost_route() {
		return Post_route;
	}

	public void setPost_route(String post_route) {
		Post_route = post_route;
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

	public int getPost_rep() {
		return Post_rep;
	}

	public void setPost_rep(int post_rep) {
		Post_rep = post_rep;
	}

	public int getPost_comm() {
		return Post_comm;
	}

	public void setPost_comm(int post_comm) {
		Post_comm = post_comm;
	}

	public int getPost_like() {
		return Post_like;
	}

	public void setPost_like(int post_like) {
		Post_like = post_like;
	}

	public String getPost_addTime() {
		return Post_addTime;
	}

	public void setPost_addTime(String post_addTime) {
		Post_addTime = post_addTime;
	}
}
