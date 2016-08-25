/*
         * @method for testing the encoding of Chinese character insert into the mysql database
         * */
	public void TestDatabaseEncoding(){
		this.conn.executeUpdate("insert into Accounts values(null, '逃跑计划', 'audhugaciy', '男', '浙江大学紫金港校区', 0, 0, false, 0, 0, 0, null, '花花', null)");
		this.conn.close();
	}
	
	/*
	 * @method check whether there's a same account name
	 * @return true if the name exists
	 * @attention it is a static method, 静态方法通过类来调用
	 * */
	public boolean AccountNameExist(Accounts acc){
		boolean result = false;
		String name = acc.Get_Acc_name();
		try{
			ResultSet rs = this.conn.executeQuery("select Acc_ID from Accounts where Acc_name = '"+name+"'");
			if(rs.next()){
				result = true;
			}else{
				result = false;
			}
		}catch(SQLException e){
			result = false;
			e.printStackTrace();
		}
		this.conn.close();
		return result;
	}
	
	/*
	 * @method get account id by accout name
	 * @return Acc_ID(long) if successfully
	 * */
	public long getAccountID(String name){
		long flag;
		String sql = "select Acc_ID from Accounts where Acc_name = '"+name+"'";
		ResultSet rs = this.conn.executeQuery(sql);
		try{
			if(rs.next()){
				flag = rs.getLong("Acc_ID");
			}else{
				flag = 0;
			}
		}catch(SQLException e){
			e.printStackTrace();
			flag = 0;
		}
		this.conn.close();
		return flag;
	}
	
	/*
	 * @method for regist, if returns zero means error exists
	 * @return Acc_ID(long) if regist successfully, also means login successfully
	 * */
	public long Regist(Accounts acc){//returns the account ID
		long flag = 0;//default return value, if 0 means error appears, if succeed, flag means the userID
		if(this.AccountNameExist(acc)){//if the account name is already exist
			flag = 0;
		}else{//the account name do not exist, then insert new information of a new person
			String sql = "insert into Accounts values(null, '"+acc.Get_Acc_name()+"', '"+acc.Get_Acc_pwd()+"', '"+acc.Get_Acc_sex()+"', '"+acc.Get_Acc_loc()+"', "+acc.Get_Acc_lvl()+", "+acc.Get_Acc_ryb()+", "+acc.Get_Acc_shop()+", "+acc.Get_Acc_atn()+", "+acc.Get_Acc_atnd()+", "+acc.Get_Acc_pub()+", '"+acc.Get_Acc_no()+"', '"+acc.Get_Acc_name2()+"', '"+acc.Get_Acc_tel()+"')";
			this.conn.executeUpdate(sql);
			ResultSet rs = this.conn.executeQuery("select Acc_ID from Accounts where Acc_name = '"+acc.Get_Acc_name()+"'");
			try{
				if(rs.next()){
					flag = rs.getLong("Acc_ID");
				}else{
					flag = 0;
				}
			}catch(SQLException e){
				flag = 0;
				e.printStackTrace();
			}
		}
		this.conn.close();
		return flag;
	}
	
	/*
	 * @method for account user login, if failed, return ZERO
	 * @return Acc_ID(long) if login successfully
	 * */
	public long Login(Accounts acc){//if login successfully, returns the Acc_ID
		long flag = 0;
		String queryPwd = null;
		String sql = "select Acc_ID, Acc_pwd from Accounts where Acc_name = '"+acc.Get_Acc_name()+"'";
		try{
			ResultSet rs = this.conn.executeQuery(sql);
			if(rs.next()){
				queryPwd = rs.getString("Acc_pwd");
				if(queryPwd.equals(acc.Get_Acc_pwd())){//login password equals what is queried by account name
					flag = rs.getLong("Acc_ID");
				}else{//login failed
					flag = 0;
				}
			}else{//account name not exists
				flag = 0;
			}
		}catch(SQLException e){
			flag = 0;
			e.printStackTrace();
		}
		this.conn.close();
		return flag;
	}
	
	/*
	 * @Method for complete information of the accounts.
	 * @return Acc_ID(long) if complete Account information successfully
	 * */
	public long completeInfo(Accounts acc){//if complete successfully, returns the account's ID.
		long flag = 0;
		if(!this.AccountNameExist(acc)){//if the account do not exist.
			flag = 0;
		}else{
			String sql = "update Accounts set Acc_no = '"+acc.Get_Acc_no()+"', Acc_name2 = '"+acc.Get_Acc_name2()+"', Acc_tel = '"+acc.Get_Acc_tel()+"' where Acc_name = '"+acc.Get_Acc_name()+"'";
			this.conn.executeUpdate(sql);
			ResultSet rs = this.conn.executeQuery("select Acc_ID from Accounts where Acc_name = '"+acc.Get_Acc_name()+"'");
			try{
				if(rs.next()){
					flag = rs.getLong("Acc_ID");
				}else{
					flag = 0;
				}
			}catch(SQLException e){
				flag = 0;
				e.printStackTrace();
			}
		}
		this.conn.close();
		return flag;
	}
	
	/*
	 * @method getting Account information
	 * @return Accounts instance if 
	 * */
	public Accounts getAccountsInformation(String Acc_name){
		String sql = "select * from Accounts where Acc_name = '"+Acc_name+"'";
		Accounts acc = new Accounts();
		ResultSet rs = this.conn.executeQuery(sql);
		try{
			if(rs.next()){//if query successfully
				acc.Set_Acc_ID(rs.getLong("Acc_ID"));
				acc.Set_Acc_name(rs.getString("Acc_name"));
				acc.Set_Acc_pwd(rs.getString("Acc_pwd"));
				acc.Set_Acc_sex(rs.getString("Acc_sex"));
				acc.Set_Acc_loc(rs.getString("Acc_loc"));
				acc.Set_Acc_lvl(rs.getInt("Acc_lvl"));
				acc.Set_Acc_ryb(rs.getInt("Acc_ryb"));
				acc.Set_Acc_shop(rs.getBoolean("Acc_shop"));
				acc.Set_Acc_atn(rs.getInt("Acc_atn"));
				acc.Set_Acc_atnd(rs.getInt("Acc_atnd"));
				acc.Set_Acc_pub(rs.getInt("Acc_pub"));
				acc.Set_Acc_no(rs.getString("Acc_no"));
				acc.Set_Acc_name2(rs.getString("Acc_name2"));
				acc.Set_Acc_tel(rs.getString("Acc_tel"));
			}else{
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		this.conn.close();
		return acc;
	}
	
	/*
	 * @method attention(for account attend anther account)
	 * @return true if successfully, 返回true如果关注成功
	 * */
	public boolean Attention(long ID_1, long ID_2){
		String sql = "insert into Attention values( "+ID_1+", "+ID_2+")";
		String sql2 = "update Accounts set Acc_atn = Acc_atn+1 where Acc_ID = "+ID_1+"";
		String sql3 = "update Accounts set Acc_atnd = Acc_atnd+1 where Acc_ID = "+ID_2+"";
		int r1 = this.conn.executeUpdate(sql);
		int r2 = this.conn.executeUpdate(sql2);
		int r3 = this.conn.executeUpdate(sql3);
		this.conn.close();
		if((r1 == 0) || (r2 == 0) || (r3 == 0)){
			return false;
		}else{
			return true;
		}
	}
	
	/*
	 * @method cancel attention
	 * @return true if cancel successfully, 返回true如果取消关注成功
	 * */
	public boolean CancelAtention(long ID_1, long ID_2){
		String sql = "delete from Attention where Acc_ID = "+ID_1+" and Att_ID = "+ID_2+"";
		String sql2 = "update Accounts set Acc_atn = Acc_atn-1 where Acc_ID = "+ID_1+"";
		String sql3 = "update Accounts set Acc_atnd = Acc_atnd-1 where Acc_ID = "+ID_2+"";
		int r1 = this.conn.executeUpdate(sql);
		int r2 = this.conn.executeUpdate(sql2);
		int r3 = this.conn.executeUpdate(sql3);
		this.conn.close();
		if((r1 == 0)|| (r2 == 0) ||(r3 == 0)){
			return false;
		}else{
			return true;
		}
	}
	
	/* 
	 * @method account favourite shop
	 * @return true if successfully, 返回true如果用户收藏店铺成功
	 * */
	public boolean favourite(long ID, Shops shop){
		boolean flag = false;
		String sql = "insert into Favourite values("+ID+", "+shop.getShop_ID()+")";
		if(this.conn.executeUpdate(sql) == 0){
			flag = false;
		}else{
			if(new ShopsDaoImpl().increaseShop_favo(shop)){
				flag = true;
			}else{
				flag = false;
			}
		}
		this.conn.close();
		return flag;
	}
	
	/*
	 * @method cancel account favourite shop
	 * @return true if successfully, 返回true如果用户取消关注店铺成功
	 * */
	public boolean cancelFavourite(long ID, Shops shop){
		boolean flag = false;
		String sql = "delete from Favourite where Acc_ID = "+ID+" and Shop_ID = "+shop.getShop_ID()+"";
		if(this.conn.executeUpdate(sql) == 0){
			flag = false;
		}else{
			if(new ShopsDaoImpl().decreaseShop_favo(shop)){
				flag = true;
			}else{
				flag = false;
			}
		}
		this.conn.close();
		return flag;
	}
	
	/*
	 * @method account want product
	 * @return true if successfully
	 * @attention 用户收藏产品
	 * */
	public boolean want(long ID, Products prod){
		boolean flag = false;
		String sql = "insert into Want values("+ID+", "+prod.getProd_ID()+")";
		if(this.conn.executeUpdate(sql) == 0){
			flag = false;
		}else{
			flag = true;
		}
		this.conn.close();
		return flag;
	}
	
	/*
	 * @method account cancel want product
	 * @return true if successfully
	 * @attention 用户取消收藏商品
	 * */
	public boolean cancelWant(long ID, Products prod){
		boolean flag = false;
		String sql = "delete from Want where Acc_ID = "+ID+" and Prod_ID = "+prod.getProd_ID()+"";
		if(this.conn.executeUpdate(sql) == 0){
			flag = false;
		}else{
			flag = true;
		}
		this.conn.close();
		return flag;
	}