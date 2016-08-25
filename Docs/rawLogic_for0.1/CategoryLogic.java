
	/*
	 * @method insert information into database table
	 * @return Cat_ID(int) if successfully
	 * */
	public int insertCategory(Category cg){
		int flag = 0;
		String sql = "insert into Category values(null, '"+cg.getCat_name()+"', '"+cg.getCat_desc()+"')";
		this.conn.executeUpdate(sql);
		ResultSet rs = this.conn.executeQuery("select Cat_ID from Category where Cat_name = '"+cg.getCat_name()+"'");
		try{
			if(rs.next()){
				flag = rs.getInt("Cat_ID");
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
	 * @method get information of an Category
	 * @return true if the query successfully
	 * @attention 商品分类一旦添加便不可删除
	 * */
	public boolean getCategoryInfo(Category cg){
		boolean flag = false;
		String sql = "select * from Category where Cat_ID = "+cg.getCat_ID()+"";
		ResultSet rs = this.conn.executeQuery(sql);
		try{
			if(rs.next()){
				flag = true;
				cg.setCat_name(rs.getString("Cat_name"));
				cg.setCat_desc(rs.getString("Cat_desc"));
			}else{
				flag = false;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		this.conn.close();
		return flag;
	}