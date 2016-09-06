package xuyihao.entity;

/**
 * created by xuyihao on 2016/4/30 订单的java bean
 */
public class Orders {
	public static final String BASE_TABLE_NAME = "Orders";
	public static final String BASE_ORDERS_PHYSICAL_ID = "_id";
	public static final String BASE_ORDERS_ID = "Ord_ID";
	public static final String BASE_ORDERS_DATE = "Ord_date";
	public static final String BASE_ORDERS_ACCOUNT_ID = "Acc_ID";
	public static final String BASE_ORDERS_PRODUCT_ID = "Prod_ID";
	public static final String BASE_ORDERS_PRODUCT_PRICE = "Prod_price";
	public static final String BASE_ORDERS_PRODUCT_NUMBER = "Pro_num";
	public static final String BASE_ORDERS_IS_PAID = "Ord_paid";
	public static final String BASE_ORDERS_IS_SENT = "Ord_sent";
	public static final String BASE_ORDERS_IS_RECEIVED = "Ord_received";
	public static final String BASE_ORDERS_IS_COMMENT = "Ord_comment";
	public static final String BASE_ORDERS_ADDRESS_ID = "Add_ID";
	public static final String BASE_ORDERS_ADD_TIME = "Ord_addTime";
	private long _id;// 物理主键(mysql表主键)
	private String Ord_ID;// 逻辑主键
	private String Ord_date;
	private String Acc_ID;
	private String Prod_ID;
	private float Prod_price;
	private int Pro_num;
	private boolean Ord_paid;
	private boolean Ord_sent;
	private boolean Ord_received;
	private boolean Ord_comment;
	private String Add_ID;
	private String Ord_addTime;

	public Orders() {
		this._id = 0;
		this.Ord_ID = "";
		this.Ord_date = "";
		this.Acc_ID = "";
		this.Prod_ID = "";
		this.Prod_price = 0f;
		this.Pro_num = 0;
		this.Ord_paid = false;
		this.Ord_sent = false;
		this.Ord_received = false;
		this.Ord_comment = false;
		this.Add_ID = "";
		this.Ord_addTime = "";
	}

	public Orders(Orders ord) {
		this._id = ord.get_id();
		this.Add_ID = ord.getAdd_ID();
		this.Ord_comment = ord.isOrd_comment();
		this.Ord_received = ord.isOrd_received();
		this.Ord_sent = ord.isOrd_sent();
		this.Ord_paid = ord.isOrd_paid();
		this.Pro_num = ord.getPro_num();
		this.Prod_price = ord.getProd_price();
		this.Prod_ID = ord.getProd_ID();
		this.Acc_ID = ord.getAcc_ID();
		this.Ord_date = ord.getOrd_date();
		this.Ord_ID = ord.getOrd_ID();
		this.Ord_addTime = ord.getOrd_addTime();
	}

	/**
	 * 将所有字段以JSON格式返回
	 * 
	 * @return
	 */
	public String toJSONString() {
		String returnString = "{\"" + BASE_ORDERS_PHYSICAL_ID + "\":\"" + this._id + "\", \"" + BASE_ORDERS_ID + "\":\""
				+ this.Ord_ID + "\", \"" + BASE_ORDERS_DATE + "\":\"" + this.Ord_date + "\", \"" + BASE_ORDERS_ACCOUNT_ID
				+ "\":\"" + this.Acc_ID + "\", \"" + BASE_ORDERS_PRODUCT_ID + "\":\"" + this.Prod_ID + "\", \""
				+ BASE_ORDERS_PRODUCT_PRICE + "\":\"" + this.Prod_price + "\", \"" + BASE_ORDERS_PRODUCT_NUMBER + "\":\""
				+ this.Pro_num + "\", \"" + BASE_ORDERS_IS_PAID + "\":\"" + this.Ord_paid + "\", \"" + BASE_ORDERS_IS_SENT
				+ "\":\"" + this.Ord_sent + "\", \"" + BASE_ORDERS_IS_RECEIVED + "\":\"" + this.Ord_received + "\", \""
				+ BASE_ORDERS_IS_COMMENT + "\":\"" + this.Ord_comment + "\", \"" + BASE_ORDERS_ADDRESS_ID + "\":\""
				+ this.Add_ID + "\", \"" + BASE_ORDERS_ADD_TIME + "\":\"" + this.Ord_addTime + "\"}";
		return returnString;
	}

	public long get_id() {
		return _id;
	}

	public void set_id(long _id) {
		this._id = _id;
	}

	public String getOrd_ID() {
		return Ord_ID;
	}

	public void setOrd_ID(String ord_ID) {
		Ord_ID = ord_ID;
	}

	public String getOrd_date() {
		return Ord_date;
	}

	public void setOrd_date(String ord_date) {
		Ord_date = ord_date;
	}

	public String getAcc_ID() {
		return Acc_ID;
	}

	public void setAcc_ID(String acc_ID) {
		Acc_ID = acc_ID;
	}

	public String getProd_ID() {
		return Prod_ID;
	}

	public void setProd_ID(String prod_ID) {
		Prod_ID = prod_ID;
	}

	public float getProd_price() {
		return Prod_price;
	}

	public void setProd_price(float prod_price) {
		Prod_price = prod_price;
	}

	public int getPro_num() {
		return Pro_num;
	}

	public void setPro_num(int pro_num) {
		Pro_num = pro_num;
	}

	public boolean isOrd_paid() {
		return Ord_paid;
	}

	public void setOrd_paid(boolean ord_paid) {
		Ord_paid = ord_paid;
	}

	public boolean isOrd_sent() {
		return Ord_sent;
	}

	public void setOrd_sent(boolean ord_sent) {
		Ord_sent = ord_sent;
	}

	public boolean isOrd_received() {
		return Ord_received;
	}

	public void setOrd_received(boolean ord_received) {
		Ord_received = ord_received;
	}

	public boolean isOrd_comment() {
		return Ord_comment;
	}

	public void setOrd_comment(boolean ord_comment) {
		Ord_comment = ord_comment;
	}

	public String getAdd_ID() {
		return Add_ID;
	}

	public void setAdd_ID(String add_ID) {
		Add_ID = add_ID;
	}

	public String getOrd_addTime() {
		return Ord_addTime;
	}

	public void setOrd_addTime(String ord_addTime) {
		Ord_addTime = ord_addTime;
	}
}
