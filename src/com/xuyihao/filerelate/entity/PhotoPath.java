package com.xuyihao.filerelate.entity;

/**
 * 
 * @author Xuyh at 2016年8月28日 下午2:40:17
 *
 */
public class PhotoPath {
	public static final String BASE_TABLE_NAME = "PhotoPath";
	public static final String BASE_PHOTOPATH_PHYSICAL_ID = "_id";
	public static final String BASE_PHOTOPATH_ID = "Photo_ID";
	public static final String BASE_PHOTOPATH_PATHNAME = "Photo_pathName";
	public static final String BASE_PHOTOPATH_THUMBNAIL_PATHNAME = "Thumbnail_pathName";
	public static final String BASE_PHOTOPATH_ADDTIME = "Photo_addTime";
	private long _id;
	private String Photo_ID;
	private String Photo_pathName;
	private String Thumbnail_pathName;
	private String Photo_addTime;

	public PhotoPath() {
		this._id = 0l;
		this.Photo_ID = "";
		this.Photo_pathName = "";
		this.Thumbnail_pathName = "";
		this.Photo_addTime = "";
	}

	public PhotoPath(PhotoPath photoPath) {
		this._id = photoPath.get_id();
		this.Photo_ID = photoPath.getPhoto_ID();
		this.Photo_pathName = photoPath.getPhoto_pathName();
		this.Thumbnail_pathName = photoPath.getThumbnail_pathName();
		this.Photo_addTime = photoPath.getPhoto_addTime();
	}

	public String toJSINString() {
		String value = "{\"" + BASE_PHOTOPATH_PHYSICAL_ID + "\":\"" + this._id + "\", \"" + BASE_PHOTOPATH_ID + "\":\""
				+ this.Photo_ID + "\", \"" + BASE_PHOTOPATH_PATHNAME + "\":\"" + this.Photo_pathName + "\", \""
				+ BASE_PHOTOPATH_THUMBNAIL_PATHNAME + "\":\"" + this.Thumbnail_pathName + "\", \"" + BASE_PHOTOPATH_ADDTIME
				+ "\":\"" + this.Photo_addTime + "\"}";
		return value;
	}

	public long get_id() {
		return _id;
	}

	public void set_id(long _id) {
		this._id = _id;
	}

	public String getPhoto_ID() {
		return Photo_ID;
	}

	public void setPhoto_ID(String photo_ID) {
		Photo_ID = photo_ID;
	}

	public String getPhoto_pathName() {
		return Photo_pathName;
	}

	public void setPhoto_pathName(String photo_pathName) {
		Photo_pathName = photo_pathName;
	}

	public String getThumbnail_pathName() {
		return Thumbnail_pathName;
	}

	public void setThumbnail_pathName(String thumbnail_pathName) {
		Thumbnail_pathName = thumbnail_pathName;
	}

	public String getPhoto_addTime() {
		return Photo_addTime;
	}

	public void setPhoto_addTime(String photo_addTime) {
		Photo_addTime = photo_addTime;
	}
}