package xuyihao.entity;

/**
 * created by xuyihao on 2016/4/26
 */
public class CommentPost {
	public static final String BASE_TABLE_NAME = "CommentPost";
	public static final String BASE_COMMENTPOST_PHYSICAL_ID = "_id";
	public static final String BASE_COMMENTPOST_ID = "Comm_ID";
	public static final String BASE_COMMENTPOST_DESCRIPTION = "Comm_desc";
	public static final String BASE_COMMENTPOST_ACCOUNT_ID = "Acc_ID";
	public static final String BASE_COMMENTPOST_PUBLICSHER_REP_ID = "Rep_ID";
	public static final String BASE_COMMENTPOST_POST_ID = "Post_ID";
	public static final String BASE_COMMENTPOST_ADD_TIME = "Comm_addTime";
	private long _id;// 物理主键(mysql表主键)
	private String Comm_ID;// 逻辑主键
	private String Comm_desc;
	private String Acc_ID;
	private String Rep_ID;
	private String Post_ID;
	private String Comm_addTime;

	public CommentPost() {
		this._id = 0;
		this.Comm_ID = "";
		this.Comm_desc = "";
		this.Acc_ID = "";
		this.Rep_ID = "";
		this.Post_ID = "";
		this.Comm_addTime = "";
	}

	public CommentPost(CommentPost cp) {
		this._id = cp.get_id();
		this.Comm_ID = cp.getComm_ID();
		this.Comm_desc = cp.getComm_desc();
		this.Acc_ID = cp.getAcc_ID();
		this.Rep_ID = cp.getRep_ID();
		this.Post_ID = cp.getPost_ID();
		this.Comm_addTime = cp.getComm_addTime();
	}

	/**
	 * 将所有字段以JSON格式返回
	 * 
	 * @return
	 */
	public String toJSONString() {
		String returnString = "{\"" + BASE_COMMENTPOST_PHYSICAL_ID + "\":\"" + this._id + "\", \"" + BASE_COMMENTPOST_ID
				+ "\":\"" + this.Comm_ID + "\", \"" + BASE_COMMENTPOST_DESCRIPTION + "\":\"" + this.Comm_desc + "\", \""
				+ BASE_COMMENTPOST_ACCOUNT_ID + "\":\"" + this.Acc_ID + "\", \"" + BASE_COMMENTPOST_PUBLICSHER_REP_ID + "\":\""
				+ this.Rep_ID + "\", \"" + BASE_COMMENTPOST_POST_ID + "\":\"" + this.Post_ID + "\", \""
				+ BASE_COMMENTPOST_ADD_TIME + "\":\"" + this.Comm_addTime + "\"}";
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

	public String getComm_addTime() {
		return Comm_addTime;
	}

	public void setComm_addTime(String comm_addTime) {
		Comm_addTime = comm_addTime;
	}
}
