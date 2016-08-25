
	
	/*
	 * @method insert information into database table
	 * @return Cart_ID(long) if successfully
	 * */
	public long insertCrat(Cart ca){
		long flag = 0;
		String sql = "insert into Cart values(null, "+ca.getProd_ID()+", "+ca.getAcc_ID()+", "+ca.getProd_price()+", "+ca.getPro_num()+")";
		this.conn.executeUpdate(sql);
		ResultSet rs = this.conn.executeQuery("select Cart_ID from Cart where Prod_ID = "+ca.getProd_ID()+" and Acc_ID = "+ca.getAcc_ID()+" and Prod_price = "+ca.getProd_price()+" and Pro_num = "+ca.getPro_num()+"");
		try{
			if(rs.next()){
				flag = rs.getLong("Cart_ID");
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
	 * @method get Cart information from the database table
	 * @return true if successfully
	 * */
	public boolean getCartInfo(Cart cart){
		boolean flag = false;
		String sql = "select * from Cart where Cart_ID = "+cart.getCart_ID()+"";
		ResultSet rs = this.conn.executeQuery(sql);
		try{
			if(rs.next()){
				flag = true;
				cart.setProd_ID(rs.getLong("Prod_ID"));
				cart.setAcc_ID(rs.getLong("Acc_ID"));
				cart.setProd_price(rs.getFloat("Prod_price"));
				cart.setPro_num(rs.getInt("Pro_num"));
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
	 * @method change Pro_num from the database table
	 * @return true  if sucessfully
	 * @attention 修改购物车中的商品购物数量
	 * */
	public boolean changePro_num(Cart ca){
		boolean flag = false;
		String sql = "update Cart set Pro_num = "+ca.getPro_num()+" where Cart_ID = "+ca.getCart_ID()+"";
		if(this.conn.executeUpdate(sql) == 0){
			flag = false;
		}else{
			flag = true;
		}
		this.conn.close();
		return flag;
	}
	
	/*
	 * @method delete information from Cart table
	 * @return true if successfully
	 * @attention 删除购物车，或者提交订单这两种情况都需要删除表单数据 
	 * */
	public boolean deleteCart(Cart ca){
		boolean flag = false;
		String sql = "delete from Cart where Cart_ID = "+ca.getCart_ID()+"";
		if(this.conn.executeUpdate(sql) == 0){
			flag = false;
		}else{
			flag = true;
		}
		this.conn.close();
		return flag;
	}
	