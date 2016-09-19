package xuyihao.filerelate.entity;

/**
 * 
 * @author Xuyh at 2016年8月28日 下午2:11:29
 *
 */
public class VedioPath {
	public static final String BASE_TABLE_NAME = "VedioPath";
	public static final String BASE_VEDIOPATH_PHYSICAL_ID = "_id";
	public static final String BASE_VEDIOPATH_ID = "Vedio_ID";
	public static final String BASE_VEDIOPATH_PATHNAME = "Vedio_pathName";
	public static final String BASE_VEDIOPATH_FIRSTPHOTO_ID = "FirstPhoto_ID";
	public static final String BASE_VEDIOPATH_ADD_TIME = "Vedio_addTime";
	private long _id;
	private String Vedio_ID;
	private String Vedio_pathName;
	private String FirstPhoto_ID;
	private String Vedio_addTime;

	public VedioPath() {
		this._id = 0l;
		this.Vedio_ID = "";
		this.Vedio_pathName = "";
		this.FirstPhoto_ID = "";
		this.Vedio_addTime = "";
	}

	public VedioPath(VedioPath vedioPath) {
		this._id = vedioPath.get_id();
		this.Vedio_ID = vedioPath.getVedio_ID();
		this.Vedio_pathName = vedioPath.getVedio_pathName();
		this.FirstPhoto_ID = vedioPath.getFirstPhoto_ID();
		this.Vedio_addTime = vedioPath.getVedio_addTime();
	}

	/**
	 * 将所有字段以JSON格式返回
	 * 
	 * @return
	 */
	public String toJSONString() {
		String value = "{\"" + BASE_VEDIOPATH_PHYSICAL_ID + "\":\"" + this._id + "\", \"" + BASE_VEDIOPATH_ID + "\":\""
				+ this.Vedio_ID + "\", \"" + BASE_VEDIOPATH_PATHNAME + "\":\"" + this.Vedio_pathName + "\", \""
				+ BASE_VEDIOPATH_FIRSTPHOTO_ID + "\":\"" + this.FirstPhoto_ID + "\", \"" + BASE_VEDIOPATH_ADD_TIME + "\":\""
				+ this.Vedio_addTime + "\"}";
		return value;
	}

	public long get_id() {
		return _id;
	}

	public void set_id(long _id) {
		this._id = _id;
	}

	public String getVedio_ID() {
		return Vedio_ID;
	}

	public void setVedio_ID(String vedio_ID) {
		Vedio_ID = vedio_ID;
	}

	public String getVedio_pathName() {
		return Vedio_pathName;
	}

	public void setVedio_pathName(String vedio_pathName) {
		Vedio_pathName = vedio_pathName;
	}

	public String getFirstPhoto_ID() {
		return FirstPhoto_ID;
	}

	public void setFirstPhoto_ID(String firstPhoto_ID) {
		FirstPhoto_ID = firstPhoto_ID;
	}

	public String getVedio_addTime() {
		return Vedio_addTime;
	}

	public void setVedio_addTime(String vedio_addTime) {
		Vedio_addTime = vedio_addTime;
	}
}