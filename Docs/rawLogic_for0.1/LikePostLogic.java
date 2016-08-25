/*
 * fields
 * */
private ConnDB conn;
private PostsDaoImpl postsDao = new PostsDaoImpl();

/*
 * constructor
 * */
public LikePostDaoImpl(){
}

/**
 * constructor2
 * */
public LikePostDaoImpl(ServletContext context){
	this.conn = new ConnDB(context);
}

/*
 * method insert information into database tables
 * @return Like_ID if successfully
 * */
public long insertLikePost(LikePost lp){
	long flag = 0;
	String sql = "insert into LikePost values(null, "+lp.getAcc_ID()+", "+lp.getRep_ID()+", "+lp.getPost_ID()+", "+lp.getLike_ryb()+")";
	this.conn.executeUpdate(sql);
	
	Posts po = new Posts();
	po.setPost_ID(lp.getPost_ID());
	if(this.postsDao.increasePost_like(po)){
		ResultSet rs = this.conn.executeQuery("select Like_ID from LikePost where Acc_ID = "+lp.getAcc_ID()+" and Rep_ID = "+lp.getRep_ID()+" and Post_ID = "+lp.getPost_ID()+"");
		try{
			if(rs.next()){
				flag = rs.getLong("Like_ID");
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