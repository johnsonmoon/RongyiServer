/*
	 * @method insert information into database table
	 * @return Add_ID(long) if successfully
	 * */
	public long insertAddress(Address add){
		long flag = 0;
		String sql = "insert into Address values(null, '"+add.getAdd_info()+"', "+add.getAcc_ID()+", '"+add.getConsign()+"', '"+add.getCon_tel()+"')";
		this.conn.executeUpdate(sql);
		ResultSet rs = this.conn.executeQuery("select Add_ID from Address where Acc_ID = "+add.getAcc_ID()+" and Consign = '"+add.getConsign()+"' and Con_tel = '"+add.getCon_tel()+"'");
		try{
			if(rs.next()){
				flag = rs.getLong("Add_ID");
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
	 * @return true if successfully
	 * @attention 引用传值，修改形参
	 * */
	public boolean getAddressInfo(Address add){
		boolean flag = false;
		String sql = "select * from Address where Add_ID = "+add.getAdd_ID()+"";
		ResultSet rs = this.conn.executeQuery(sql);
		try{
			if(rs.next()){
				flag = true;
				add.setAdd_info(rs.getString("Add_info"));
				add.setAcc_ID(rs.getLong("Acc_ID"));
				add.setConsign(rs.getString("Consign"));
				add.setCon_tel(rs.getString("Con_tel"));
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
	 * @method change information into the database table
	 * @return true if successfully
	 * */
	public boolean changeAddress(Address add){
		boolean flag = false;
		String sql = "update Address set Add_info = '"+add.getAdd_info()+"', Consign = '"+add.getConsign()+"', Con_tel = '"+add.getCon_tel()+"' where Add_ID = "+add.getAdd_ID()+"";
		if(this.conn.executeUpdate(sql) == 0){
			flag = false;
		}else{
			flag = true;
		}
		this.conn.close();
		return flag;
	}
	
	/*
	 * @method delete information from database table
	 * @return true if successfully
	 * */
	public boolean deleteAddress(Address add){
		boolean flag = false;
		String sql = "delete from Address where Add_ID = "+add.getAdd_ID()+"";
		if(this.conn.executeUpdate(sql) == 0){
			flag = false;
		}else{
			flag = true;
		}
		this.conn.close();
		return flag;
	}
	