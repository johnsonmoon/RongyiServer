package xuyihao.filerelate.entity;

/**
 * 
 * @Author Xuyh created at 2016年9月18日 下午5:14:50
 */
public class CoursesVedio {
	public static final String BASE_TABLE_NAME = "CoursesVedio";
	public static final String BASE_COURSESVEDIO_PHYSICAL_ID = "_id";
	public static final String BASE_COURSESVEDIO_COURSES_ID = "Crs_ID";
	public static final String BASE_COURSESVEDIO_VEDIO_ID = "Vedio_ID";
	public static final String BASE_COURSESVEDIO_ADD_TIME = "CrsVedio_addTime";
	private long _id;
	private String Crs_ID;
	private String Vedio_ID;
	private String CrsVedio_addTime;

	public CoursesVedio() {
		this._id = 0l;
		this.Crs_ID = "";
		this.Vedio_ID = "";
		this.CrsVedio_addTime = "";
	}

	public CoursesVedio(CoursesVedio coursesVedio) {
		this._id = coursesVedio.get_id();
		this.Crs_ID = coursesVedio.getCrs_ID();
		this.Vedio_ID = coursesVedio.getVedio_ID();
		this.CrsVedio_addTime = coursesVedio.getCrsVedio_addTime();
	}

	/**
	 * 将所有字段以JSON格式返回
	 * 
	 * @return
	 */
	public String toJSONString() {
		String value = "{\"" + BASE_COURSESVEDIO_PHYSICAL_ID + "\":\"" + this._id + "\", \"" + BASE_COURSESVEDIO_COURSES_ID
				+ "\":\"" + this.Crs_ID + "\", \"" + BASE_COURSESVEDIO_VEDIO_ID + "\":\"" + this.Vedio_ID + "\", \""
				+ BASE_COURSESVEDIO_ADD_TIME + "\":\"" + this.CrsVedio_addTime + "\"}";
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

	public String getVedio_ID() {
		return Vedio_ID;
	}

	public void setVedio_ID(String vedio_ID) {
		Vedio_ID = vedio_ID;
	}

	public String getCrsVedio_addTime() {
		return CrsVedio_addTime;
	}

	public void setCrsVedio_addTime(String crsVedio_addTime) {
		CrsVedio_addTime = crsVedio_addTime;
	}
}