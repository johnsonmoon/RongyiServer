
	/*
	 * fields
	 * */
	private ConnDB conn;
	private PostsDaoImpl postDao = new PostsDaoImpl();
	
	/*
	 * constructor
	 * */
	public CommentPostDaoImpl(){
	}
	
	/**
	 * constructor2
	 * */
	public CommentPostDaoImpl(ServletContext context){
		this.conn = new ConnDB(context);
	}
	
	/*
	 * method insert information into database table
	 * @return Comm_ID if successfully
	 * */
	public long insertCommentPost(CommentPost cp){
		long flag = 0;
		String sql = "insert into CommentPost values(null, '"+cp.getComm_desc()+"', "+cp.getAcc_ID()+", "+cp.getRep_ID()+", "+cp.getPost_ID()+")";
		this.conn.executeUpdate(sql);
		
		Posts po = new Posts();
		po.setPost_ID(cp.getPost_ID());
		if(this.postDao.increasePost_comm(po)){
			ResultSet rs = this.conn.executeQuery("select Comm_ID from CommentPost where Acc_ID = "+cp.getAcc_ID()+"");
			try{
				if(rs.next()){
					flag = rs.getLong("Comm_ID");
				}else{
					flag = 0;
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
		}else{
			flag = 0;
		}
		this.conn.close();
		return flag;
	}
	
	/*
	 * method delete information from database table 
	 * @return true if successfully
	 * */
	public boolean deleteCommentPost(CommentPost cp){
		boolean flag = false;
		String sql = "delete from CommentPost where Comm_ID = "+cp.getComm_ID()+"";
		if(this.conn.executeUpdate(sql) == 0){
			flag = false;
		}else{
			flag = true;
		}
		this.conn.close();
		return flag;
	}