package xuyihao.filerelate.entity;

/**
 * 
 * @author Xuyh at 2016年8月28日 下午5:04:41
 *
 */
public class AccountsPhotos {
	public static final String BASE_TABLE_NAME = "AccountsPhotos";
	public static final String BASE_PHYSICAL_ID = "_id";
	public static final String BASE_ACCOUNTSPHOTOS_ACCOUNT_ID = "Acc_ID";
	public static final String BASE_ACCOUNTSPHOTOS_HEADPHOTO_ID = "HeadPhoto_ID";
	public static final String BASE_ACCOUNTSPHOTOS_PHOTO_ID_COMBINE = "Photo_ID_Combine";
	public static final String BASE_ACCOUNTSPHOTOS_ADD_TIME = "AccPhoto_addTime";
	private long _id;
	private String Acc_ID;
	private String HeadPhoto_ID;
	private String Photo_ID_Combine;
	private String AccPhoto_addTime;

	public AccountsPhotos() {
		this._id = 0l;
		this.Acc_ID = "";
		this.HeadPhoto_ID = "";
		this.Photo_ID_Combine = "";
		this.AccPhoto_addTime = "";
	}

	public AccountsPhotos(AccountsPhotos accountsPhotos) {
		this._id = accountsPhotos.get_id();
		this.Acc_ID = accountsPhotos.getAcc_ID();
		this.HeadPhoto_ID = accountsPhotos.getHeadPhoto_ID();
		this.Photo_ID_Combine = accountsPhotos.getPhoto_ID_Combine();
		this.AccPhoto_addTime = accountsPhotos.getAccPhoto_addTime();
	}

	public String toJSONString() {
		String value = "{\"" + BASE_PHYSICAL_ID + "\":\"" + this._id + "\", \"" + BASE_ACCOUNTSPHOTOS_ACCOUNT_ID + "\":\""
				+ this.Acc_ID + "\", \"" + BASE_ACCOUNTSPHOTOS_HEADPHOTO_ID + "\":\"" + this.HeadPhoto_ID + "\", \""
				+ BASE_ACCOUNTSPHOTOS_PHOTO_ID_COMBINE + "\":\"" + this.Photo_ID_Combine + "\", \""
				+ BASE_ACCOUNTSPHOTOS_ADD_TIME + "\":\"" + this.AccPhoto_addTime + "\"}";
		return value;
	}

	public long get_id() {
		return _id;
	}

	public void set_id(long _id) {
		this._id = _id;
	}

	public String getAcc_ID() {
		return Acc_ID;
	}

	public void setAcc_ID(String acc_ID) {
		Acc_ID = acc_ID;
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

	public String getAccPhoto_addTime() {
		return AccPhoto_addTime;
	}

	public void setAccPhoto_addTime(String accPhoto_addTime) {
		AccPhoto_addTime = accPhoto_addTime;
	}
}