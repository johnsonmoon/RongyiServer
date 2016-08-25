private ConnDB conn;
	private CoursesDaoImpl coursesDao = new CoursesDaoImpl();
	
	public CommentCrsDaoImpl(){
	}
	
	public CommentCrsDaoImpl(ServletContext context){
		this.conn = new ConnDB(context);
	}

	/**
	 * 后期整合Spring用到
	 *
	 */
	public void setConn(ConnDB conn){
		this.conn = conn;
	}
	
	/*
	 * method insert information into database table
	 * @return Comm_ID if successfully
	 * */
	public long insertCommentCrs(CommentCrs cc){
		long flag = 0;
		String sql = "insert into CommentCrs values(null, '"+cc.getComm_desc()+"', "+cc.getAcc_ID()+", "+cc.getRep_ID()+", "+cc.getCrs_ID()+")";
		this.conn.executeUpdate(sql);
		
		Courses cou = new Courses();
		cou.setCrs_ID(cc.getCrs_ID());
		if(this.coursesDao.increaseCrs_comm(cou)){
			ResultSet rs = this.conn.executeQuery("select Comm_ID from CommentCrs where Acc_ID = "+cc.getAcc_ID()+"");
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
			flag =0;
		}
		this.conn.close();
		return flag;
	}
	
	/*
	 * method delete info from database table
	 * @return true if successfully
	 * */
	public boolean deleteCommentCrs(CommentCrs cc){
		boolean flag = false;
		String sql = "delete from CommentCrs where Comm_ID = "+cc.getComm_ID()+"";
		if(this.conn.executeUpdate(sql) == 0){
			flag = false;
		}else{
			flag = true;
		}
		this.conn.close();
		return flag;
	}