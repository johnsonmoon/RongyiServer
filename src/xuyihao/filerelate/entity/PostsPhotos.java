package xuyihao.filerelate.entity;

/**
 * 
 * @author Xuyh at 2016年8月28日 下午8:18:51
 *
 */
public class PostsPhotos {
	public static final String BASE_TABLE_NAME = "PostsPhotos";
	public static final String BASE_PHYSICAL_ID = "_id";
	public static final String BASE_POSTSPHOTOS_POST_ID = "Post_ID";
	public static final String BASE_POSTSPHOTOS_HEADPHOTO_ID = "HeadPhoto_ID";
	public static final String BASE_POSTSPHOTOS_PHOTO_ID_COMBINE = "Photo_ID_Combine";
	public static final String BASE_POSTSPHOTOS_ADD_TIME = "PostPhoto_addTime";
	private long _id;
	private String Post_ID;
	private String HeadPhoto_ID;
	private String Photo_ID_Combine;
	private String PostPhoto_addTime;

	public PostsPhotos() {
		this._id = 0l;
		this.Post_ID = "";
		this.HeadPhoto_ID = "";
		this.Photo_ID_Combine = "";
		this.PostPhoto_addTime = "";
	}

	public PostsPhotos(PostsPhotos postsPhotos) {
		this._id = postsPhotos.get_id();
		this.Post_ID = postsPhotos.getPost_ID();
		this.HeadPhoto_ID = postsPhotos.getHeadPhoto_ID();
		this.Photo_ID_Combine = postsPhotos.getPhoto_ID_Combine();
		this.PostPhoto_addTime = postsPhotos.getPostPhoto_addTime();
	}

	public String toJSONString() {
		String value = "{\"" + BASE_PHYSICAL_ID + "\":\"" + this._id + "\", \"" + BASE_POSTSPHOTOS_POST_ID + "\":\""
				+ this.Post_ID + "\", \"" + BASE_POSTSPHOTOS_HEADPHOTO_ID + "\":\"" + this.HeadPhoto_ID + "\", \""
				+ BASE_POSTSPHOTOS_PHOTO_ID_COMBINE + "\":\"" + this.Photo_ID_Combine + "\", \"" + BASE_POSTSPHOTOS_ADD_TIME
				+ "\":\"" + this.PostPhoto_addTime + "\"}";
		return value;
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

	public String getHeadPhoto_ID() {
		return HeadPhoto_ID;
	}

	public void setHeadPhoto_ID(String headPhoto_ID) {
		HeadPhoto_ID = headPhoto_ID;
	}

	public String getPhoto_ID_Combine() {
		return Photo_ID_Combine;
	}

	public void setPhoto_ID_Combine(String photo_ID_Combine) {
		Photo_ID_Combine = photo_ID_Combine;
	}

	public String getPostPhoto_addTime() {
		return PostPhoto_addTime;
	}

	public void setPostPhoto_addTime(String postPhoto_addTime) {
		PostPhoto_addTime = postPhoto_addTime;
	}
}