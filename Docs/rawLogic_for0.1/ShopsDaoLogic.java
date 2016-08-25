public class ShopsDaoImpl {
	
	/*
	 * fields
	 * */
	private ConnDB conn;
	
	/*
	 * constructor
	 * */
	public ShopsDaoImpl(){
	}
	
	/**
	 * constructor
	 * */
	public ShopsDaoImpl(ServletContext context){
		this.conn = new ConnDB(context);
	}
	
	/*
	 * method search shop name by shop name 
	 * @return true if the name already exists
	 * @attention 不允许重复的店铺名，所以需要此方法
	 * */
	public boolean shopNameExist(Shops shop){
		boolean flag = false;
		String sql = "select Shop_ID from Shops where Shop_name = '"+shop.getShop_name()+"'";
		ResultSet rs = this.conn.executeQuery(sql);
		try{
			if(rs.next()){
				flag = true;
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
	 * @method get Shop_ID by Shop_name
	 * @return Shop_ID(int) if successfully
	 * */
	public int getShopID(String name){
		int flag = 0;
		String sql = "select Shop_ID from Shops where Shop_name = '"+name+"'";
		ResultSet rs = this.conn.executeQuery(sql);
		try{
			if(rs.next()){
				flag = rs.getInt("Shop_ID");
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
	 * method insert information into databases
	 * @return Shop_ID if successfully
	 * */
	public int insertShop(Shops sp){
		int flag = 0;
		String sql = "insert into Shops values(null, '"+sp.getShop_name()+"', '"+sp.getShop_info()+"', '"+sp.getShop_licen()+"', "+sp.getShop_lvl()+", "+sp.getShop_ryb()+", "+sp.getShop_favo()+", "+sp.getAcc_ID()+")";
		this.conn.executeUpdate(sql);
		ResultSet rs = this.conn.executeQuery("select Shop_ID from Shops where Shop_name = '"+sp.getShop_name()+"' and Acc_ID = "+sp.getAcc_ID()+"");
		try{
			if(rs.next()){
				flag = rs.getInt("Shop_ID");
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
	 * method get shop information by details
	 * @return true if successfully & parameter detailed
	 * @attention 通过引用传值将参数的信息完整
	 * */	
	public boolean getShopInfo(Shops shop){
		boolean flag = false;
		String sql = "select * from Shops where Shop_ID = "+shop.getShop_ID()+"";
		ResultSet rs = this.conn.executeQuery(sql);
		try{
			if(rs.next()){
				flag = true;
				shop.setShop_name(rs.getString("Shop_name"));
				shop.setShop_info(rs.getString("Shop_info"));
				shop.setShop_licen(rs.getString("Shop_licen"));
				shop.setShop_lvl(rs.getInt("Shop_lvl"));
				shop.setShop_ryb(rs.getInt("Shop_ryb"));
				shop.setShop_favo(rs.getInt("Shop_favo"));
				shop.setAcc_ID(rs.getLong("Acc_ID"));
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
	 * method change shop information
	 * @return true if successfully
	 * @about 修改店铺信息方法
	 * @attention 店铺名注册后不可更改
	 * */
	public boolean changeShopInfo(Shops shop){
		boolean flag = false;
		String sql = "update Shops set Shop_info = '"+shop.getShop_info()+"' where Shop_ID = "+shop.getShop_ID()+"";
		if(this.conn.executeUpdate(sql) == 0){
			flag = false;
		}else{
			flag = true;
		}
		this.conn.close();
		return flag;
	}
	
	/*
	 * method delete information from database tables
	 * @return true if successfully
	 * @attention 由于完全性约束的关系，删除Shops表内容需要先删除有关表段的相应信息(Products, Favourite),同时，删除Products内容也会相应改变其他表段
	 * @unfinished
	 * @about 平台暂时不开放删除店铺功能，此方法不实现
	 * */
	public boolean deleteShop(Shops sp){
		boolean flag = false;
		return flag;
	}
	
	/*
	 * @method increase Shop_favo
	 * @return true if successfully
	 * @attention 店铺粉丝量加一
	 * */
	public boolean increaseShop_favo(Shops shop){
		boolean flag = false;
		String sql = "update Shops set Shop_favo = Shop_favo+1 where Shop_ID = "+shop.getShop_ID()+"";
		if(this.conn.executeUpdate(sql) == 0){
			flag = false;
		}else{
			flag = true;
		}
		this.conn.close();
		return flag;
	}
	
	/*
	 * @method decrease Shop_favo
	 * @return true if successfully
	 * @attention 店铺粉丝量减一
	 * */
	public boolean decreaseShop_favo(Shops shop){
		boolean flag = false;
		String sql = "update Shops set Shop_favo = Shop_favo-1 where Shop_ID = "+shop.getShop_ID()+"";
		if(this.conn.executeUpdate(sql) == 0){
			flag = false;
		}else{
			flag = true;
		}
		this.conn.close();
		return flag;
	}
	
	/*
	 * @method check whether Shop_favo is bigger than zero
	 * @return true if it is bigger than zero
	 * @attention 查看店铺粉丝量是否大于零
	 * */
	public boolean whetherShop_favoBiggerThanZero(Shops shop){
		boolean flag = false;
		String sql = "select Shop_favo from Shops where Shop_ID = "+shop.getShop_ID()+"";
		ResultSet rs = this.conn.executeQuery(sql);
		try{
			if(rs.next()){
				int num = rs.getInt("Shop_favo");
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
}