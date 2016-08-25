public class OrdersDaoImpl {

	/*
	 * fields
	 * */
	private ConnDB conn;
	
	/*
	 *constructor 
	 * */
	public OrdersDaoImpl(){
	}
	
	/**
	 * constructor2
	 * */
	public OrdersDaoImpl(ServletContext context){
		this.conn = new ConnDB(context);
	}
	
	/*
	 * @method insert information into database table
	 * @return Ord_ID(long) if successfully
	 * */
	public long insertOrder(Orders ord){
		long flag = 0;
		String sql = "insert into Orders values(null, '"+ord.getOrd_date()+"', "+ord.getAcc_ID()+", "+ord.getProd_ID()+", "+ord.getProd_price()+", "+ord.getPro_num()+", "+ord.getOrd_paid()+", "+ord.getOrd_sent()+", "+ord.getOrd_received()+", "+ord.getOrd_comment()+", "+ord.getAdd_ID()+")";
		this.conn.executeUpdate(sql);
		ResultSet rs = this.conn.executeQuery("select Ord_ID from Orders where Ord_date = '"+ord.getOrd_date()+"' and Acc_ID = "+ord.getAcc_ID()+" and Prod_ID = "+ord.getProd_ID()+" and Add_ID = "+ord.getAdd_ID()+"");
		try{
			if(rs.next()){
				flag = rs.getLong("Ord_ID");
			}else{
				flag = 0;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		this.conn.close();
		return flag;
	}
	
	/*
	 * @method get order information from database table
	 * @return true if successfully
	 * */
	public boolean getOrderInfo(Orders ord){
		boolean flag = false;
		String sql = "select * from Orders where Ord_ID = "+ord.getOrd_ID()+"";
		ResultSet rs = this.conn.executeQuery(sql);
		try{
			if(rs.next()){
				flag = true;
				ord.setOrd_date(rs.getTimestamp("Ord_date"));
				ord.setAcc_ID(rs.getLong("Acc_ID"));
				ord.setProd_ID(rs.getLong("Prod_ID"));
				ord.setProd_price(rs.getFloat("Prod_price"));
				ord.setPro_num(rs.getInt("Pro_num"));
				ord.setOrd_paid(rs.getBoolean("Ord_paid"));
				ord.setOrd_sent(rs.getBoolean("Ord_sent"));
				ord.setOrd_received(rs.getBoolean("Ord_received"));
				ord.setOrd_comment(rs.getBoolean("Ord_comment"));
				ord.setAdd_ID(rs.getLong("Add_ID"));
			}else{
				flag = false;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		this.conn.close();
		return flag;
	}
	
	/*
	 * @method delete order from database table
	 * @return true if successfully
	 * @attention 删除订单意味着取消订单，需要后台确认信息，若已发货，则无法取消,此逻辑在控制层实现
	 * */
	public boolean deleteOrder(Orders ord){
		boolean flag = false;
		String sql = "delete from Orders where Ord_ID = "+ord.getOrd_ID()+"";
		if(ord.getOrd_sent()){
			flag = false;
		}else{
			if(this.conn.executeUpdate(sql) == 0){
				flag = false;
			}else{
				flag = true;
			}
		}
		this.conn.close();
		return flag;
	}
	
	/*
	 * @method change information into database table
	 * @return true if successfully
	 * @attention 注意订单只可修改（是否付款，是否发货，是否收货，是否评价）等状态项
	 * */
	public boolean changeOrderInfo(Orders ord){
		boolean flag = false;
		String sql = "update Orders set Ord_paid = "+ord.getOrd_paid()+", Ord_sent = "+ord.getOrd_sent()+", Ord_received = "+ord.getOrd_received()+", Ord_comment = "+ord.getOrd_comment()+" where Ord_ID = "+ord.getOrd_ID()+"";
		if(this.conn.executeUpdate(sql) == 0){
			flag = false;
		}else{
			flag = true;
		}
		this.conn.close();
		return flag;
	}
	
}