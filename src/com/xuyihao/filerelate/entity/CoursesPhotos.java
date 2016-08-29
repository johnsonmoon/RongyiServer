package com.xuyihao.filerelate.entity;

/**
 * 
 * @author Xuyh at 2016年8月28日 下午8:18:46
 *
 */
public class CoursesPhotos {
	public static final String BASE_TABLE_NAME = "CoursesPhotos";
	public static final String BASE_PHYSICAL_ID = "_id";
	public static final String BASE_COURSESPHOTOS_COURSE_ID = "Crs_ID";
	public static final String BASE_COURSESPHOTOS_HEADPHOTO_ID = "HeadPhoto_ID";
	public static final String BASE_COURSESPHOTOS_PHOTO_ID_COMBINE = "Photo_ID_Combine";
	public static final String BASE_COURSESPHOTOS_ADD_TIME = "CrsPhoto_addTime";
	private long _id;
	private String Crs_ID;
	private String HeadPhoto_ID;
	private String Photo_ID_Combine;
	private String CrsPhoto_addTime;

	public CoursesPhotos() {
		this._id = 0l;
		this.Crs_ID = "";
		this.HeadPhoto_ID = "";
		this.Photo_ID_Combine = "";
		this.CrsPhoto_addTime = "";
	}

	public CoursesPhotos(CoursesPhotos coursesPhotos) {
		this._id = coursesPhotos.get_id();
		this.Crs_ID = coursesPhotos.getCrs_ID();
		this.HeadPhoto_ID = coursesPhotos.getHeadPhoto_ID();
		this.Photo_ID_Combine = coursesPhotos.getPhoto_ID_Combine();
		this.CrsPhoto_addTime = coursesPhotos.getCrsPhoto_addTime();
	}

	public String toJSONString() {
		String value = "{\"" + BASE_PHYSICAL_ID + "\":\"" + this._id + "\", \"" + BASE_COURSESPHOTOS_COURSE_ID + "\":\""
				+ this.Crs_ID + "\", \"" + BASE_COURSESPHOTOS_HEADPHOTO_ID + "\":\"" + this.HeadPhoto_ID + "\", \""
				+ BASE_COURSESPHOTOS_PHOTO_ID_COMBINE + "\":\"" + this.Photo_ID_Combine + "\", \"" + BASE_COURSESPHOTOS_ADD_TIME
				+ "\":\"" + this.CrsPhoto_addTime + "\"}";
		return value;
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

	public String getCrsPhoto_addTime() {
		return CrsPhoto_addTime;
	}

	public void setCrsPhoto_addTime(String crsPhoto_addTime) {
		CrsPhoto_addTime = crsPhoto_addTime;
	}
}