public class ProductsDaoImpl {

	/*
	 * fields
	 * */
	private ConnDB conn;
	
	/*
	 * constructor
	 * */
	public ProductsDaoImpl(){
	}
	
	/**
	 * constructor2
	 * */
	public ProductsDaoImpl(ServletContext context){
		this.conn = new ConnDB(context);
	}
	
	/*
	 * @method insert information into database tables
	 * @return Prod_ID(long) if successfully
	 * */
	public long insertProduct(Products pd){
		long flag = 0;
		String sql = "insert into Products values(null, "+pd.getCat_ID()+", "+pd.getShop_ID()+", '"+pd.getProd_name()+"', '"+pd.getProd_desc()+"', '"+pd.getProd_info()+"', "+pd.getProd_price()+", "+pd.getProd_num()+", "+pd.getProd_sold()+")";
		this.conn.executeUpdate(sql);
		ResultSet rs = this.conn.executeQuery("select Prod_ID from Products where Prod_name = '"+pd.getProd_name()+"' and Cat_ID = "+pd.getCat_ID()+" and Shop_ID = "+pd.getShop_ID()+"");
		try{
			if(rs.next()){
				flag = rs.getLong("Prod_ID");
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
	 * @method get information from database table
	 * @return true if query successfully
	 * @attention 查询产品信息
	 * */
	public boolean getProductInfo(Products pd){
		boolean flag = false;
		String sql = "select * from Products where Prod_ID = "+pd.getProd_ID()+"";
		ResultSet rs = this.conn.executeQuery(sql);
		try{
			if(rs.next()){
				flag = true;
				pd.setCat_ID(rs.getInt("Cat_ID"));
				pd.setShop_ID(rs.getInt("Shop_ID"));
				pd.setProd_name(rs.getString("Prod_name"));
				pd.setProd_desc(rs.getString("Prod_desc"));
				pd.setProd_info(rs.getString("Prod_info"));
				pd.setProd_price(rs.getFloat("Prod_price"));
				pd.setProd_num(rs.getInt("Prod_num"));
				pd.setProd_sold(rs.getInt("Prod_sold"));
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
	 * @method change Prod_sold
	 * @return true if successfully
	 * @attention 直接通过参数修改月销量
	 * */
	public synchronized boolean changeProductSold(Products pd, int sold){
		boolean flag = false;
		String sql = "update Products set Prod_sold = "+sold+" where Prod_ID = "+pd.getProd_ID()+"";
		if(this.conn.executeUpdate(sql) == 0){
			flag = false;
		}else{
			flag = true;
		}
		this.conn.close();
		return flag;
	}
	
	/*
	 * @method decrease Prod_num
	 * @return true if successfully
	 * @attention 减少存货量，一次减少一件，需要synchronized修饰，以保证线程安全
	 * */
	public synchronized boolean decreaseProductNumber(Products pd){
		boolean flag = false;
		if(this.whetherProductRemain(pd)){//if Product remains
			String sql = "update Products set Prod_num = Prod_num-1 where Prod_ID = "+pd.getProd_ID()+"";
			if(this.conn.executeUpdate(sql) == 0){
				flag = false;
			}else{
				flag = true;
			}
		}else{
			flag = false;
		}
		this.conn.close();
		return flag;
	}
	
	
	/*
	 * @method find out how many Products remain, whether Prod_num bigger than zero
	 * @return true if Prod_num bigger than Zero
	 * @attention 查看数据库中产品是否还有库存
	 * */
	public boolean whetherProductRemain(Products pd){
		boolean flag = false;
		String sql = "select Prod_num from Products where Prod_ID = "+pd.getProd_ID()+"";
		ResultSet rs = this.conn.executeQuery(sql);
		try{
			if(rs.next()){
				int num = rs.getInt("Prod_num");
				if(num > 0){
					flag = true;
				}else{
					flag = false;
				}
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
	 * @method delete information from database tables
	 * @return true if delete successfully
	 * @attention 删除产品方法，需要注意的是，由于完全性约束，删除产品表项之前需要删除有关表项的有关信息
	 * */
	public boolean deleteProduct(Products pd){
		boolean flag = false;
		String sql1 = "delete from Want where Prod_ID = "+pd.getProd_ID()+"";
		String sql2 = "delete from Products where Prod_ID = "+pd.getProd_ID()+"";
		String sql3 = "delete from CommentProduct where Prod_ID = "+pd.getProd_ID()+"";
		int result1 = this.conn.executeUpdate(sql1);
		int result2 = this.conn.executeUpdate(sql2);
		int result3 = this.conn.executeUpdate(sql3);
		if((result1 == 0) && (result2 == 0) && (result3 == 0)){
			flag = false;
		}else{
			flag = true;
		}
		this.conn.close();
		return flag;
	}
	