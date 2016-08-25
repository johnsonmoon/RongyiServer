public class PostsDaoImpl {
	
	/*
	 * field
	 * */
	private ConnDB conn;
	
	/*
	 * constructor
	 * */
	public PostsDaoImpl(){
		
	}
	
	/**
	 * constructor2
	 * */
	public PostsDaoImpl(ServletContext context){
		this.conn = new ConnDB(context);
	}
	
	/*
	 * method insert information into database table, create a new Post
	 *@return:Post_ID if successfully, 0 if failed
	 * */
	public long insertPost(Posts po){
		long flag = 0;
		String sql = "insert into Posts values(null, '"+po.getPost_name()+"', '"+po.getPost_route()+"', "+po.getAcc_ID()+", "+po.getAuthor_ID()+", "+po.getPost_rep()+", "+po.getPost_comm()+", "+po.getPost_like()+")";
		this.conn.executeUpdate(sql);
		ResultSet rs = this.conn.executeQuery("select Post_ID from Posts where Post_name = '"+po.getPost_name()+"'");
		try{
			if(rs.next()){
				flag = rs.getLong("Post_ID");
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
	 * method delete information from database table
	 * @return true if delete successfully
	 * */
	public boolean deletePost(Posts po){
		boolean flag = false;
		String sql = "delete from Posts where Post_ID = "+po.getPost_ID()+"";
		if(this.conn.executeUpdate(sql) == 0){
			flag = false;
		}else{
			flag = true;
		}
		this.conn.close();
		return flag;
	}
	
	/*
	 * method get Post information by detail, complete information to parameter po and returns the result which shows whether completed
	 * @return true if operation is successful
	 * */
	public boolean getPostInfo(Posts po){
		boolean flag = false;
		String sql = "select * from Posts where Post_ID = "+po.getPost_ID()+"";
		ResultSet rs = this.conn.executeQuery(sql);
		try{
			if(rs.next()){
				flag = true;
				po.setPost_name(rs.getString("Post_name"));
				po.setPost_route(rs.getString("Post_route"));
				po.setAcc_ID(rs.getLong("Acc_ID"));
				po.setAuthor_ID(rs.getLong("Author_ID"));
				po.setPost_rep(rs.getInt("Post_rep"));
				po.setPost_comm(rs.getInt("Post_comm"));
				po.setPost_like(rs.getInt("Post_like"));
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
	 * method share Post by an Account
	 * @return true if share successfully
	 * */
	public boolean sharePost(Posts po, Accounts acc){
		boolean flag = false;
		Posts po2 = new Posts(po);
		//modify the information of the new Post shared by another Account
		po2.setAcc_ID(acc.Get_Acc_ID());
		po2.setPost_comm(0);
		po2.setPost_like(0);
		if(!this.insertRepostPost(acc.Get_Acc_ID(), po.getAuthor_ID(), po.getPost_ID())){
			flag = false;
		}else{
			if(this.insertPost(po2) == 0){
				flag = false;
			}else{
				if(this.increasePost_rep(po)){
					flag = true;
				}else{
					flag = false;
				}
			}
		}
		this.conn.close();
		return flag;
	}
	
	/*
	 * method insert info into table RepostPost
	 * @return true if successfully
	 * */
	public boolean insertRepostPost(long Acc_ID, long Rep_ID, long Post_ID){
		boolean flag = false;
		String sql = "insert into RepostPost values("+Acc_ID+", "+Rep_ID+", "+Post_ID+")";
		if(this.conn.executeUpdate(sql) == 0){
			flag = false;
		}else{
			flag = true;
		}
		this.conn.close();
		return flag;
	}
	
	/*
	 * method set Post_rep increase
	 * @return true if successfully
	 * */
	public boolean increasePost_rep(Posts po){
		boolean flag = false;
		String sql = "update Posts set Post_rep = Post_rep+1 where Post_ID = "+po.getPost_ID()+"";
		if(this.conn.executeUpdate(sql) == 0){
			flag = false;
		}else{
			flag = true;
		}
		this.conn.close();
		return flag;
	}
	
	/*
	 * method increase Post_comm
	 * @return true if successfully
	 * */
	public boolean increasePost_comm(Posts po){
		boolean flag = false;
		String sql = "update Posts set Post_comm = Post_comm+1 where Post_ID = "+po.getPost_ID()+"";
		if(this.conn.executeUpdate(sql) == 0){
			flag = false;
		}else{
			flag = true;
		}
		this.conn.close();
		return flag;
	}
	
	/*
	 * method increase Post_like
	 * @return true if successfully
	 * */
	public boolean increasePost_like(Posts po){
		boolean flag = false;
		String sql = "update Posts set Post_like = Post_like+1 where Post_ID = "+po.getPost_ID()+"";
		if(this.conn.executeUpdate(sql) == 0){
			flag = false;
		}else{
			flag = true;
		}
		this.conn.close();
		return flag;
	}
	
	
