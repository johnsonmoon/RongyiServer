
	/*
	 * fields
	 * */
	private ConnDB conn;
	
	/*
	 * constructor
	 * */
	public CommentProductDaoImpl(){
	}
	
	/**
	 * constructor2
	 * */
	public CommentProductDaoImpl(ServletContext context){
		this.conn = new ConnDB(context);
	}
	
	/*
	 * @method insert information into database table
	 * @return Comm_ID(long) if successfully
	 * */
	public long insertCommentProduct(CommentProduct cp){
		long flag = 0;
		String sql = "insert into CommentProduct values(null, '"+cp.getComm_desc()+"', "+cp.getAcc_ID()+", "+cp.getProd_ID()+", "+cp.getOrd_ID()+")";
		this.conn.executeUpdate(sql);
		ResultSet rs = this.conn.executeQuery("select Comm_ID from CommentProduct where Acc_ID = "+cp.getAcc_ID()+" and Prod_ID = "+cp.getProd_ID()+" and Ord_ID = "+cp.getOrd_ID()+"");
		try{
			if(rs.next()){
				flag = rs.getLong("Comm_ID"); 
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
	 * @method change Commen_desc into database table
	 * @return true if successfully
	 * @attention 修改评价详情
	 * */
	public boolean changeComm_desc(CommentProduct cp){
		boolean flag = false;
		String sql = "update CommentProduct set Comm_desc = '"+cp.getComm_desc()+"'";
		if(this.conn.executeUpdate(sql) == 0){
			flag = false;
		}else{
			flag = true;
		}
		this.conn.close();
		return flag;
	}
	
	/*
	 * @method get information from database table
	 * @return true if successfully
	 * */
	public boolean getCommentProductInfo(CommentProduct cp){
		boolean flag = false;
		String sql = "select * from CommentProduct where Comm_ID = "+cp.getComm_ID()+"";
		ResultSet rs = this.conn.executeQuery(sql);
		try{
			if(rs.next()){
				flag = true;
				cp.setComm_desc(rs.getString("Comm_desc"));
				cp.setAcc_ID(rs.getLong("Acc_ID"));
				cp.setProd_ID(rs.getLong("Prod_ID"));
				cp.setOrd_ID(rs.getLong("Ord_ID"));
			}else{
				flag = false;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		this.conn.close();
		return flag;
	}
	