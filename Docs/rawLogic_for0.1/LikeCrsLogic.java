/*
	 * fields
	 * */
	private ConnDB conn;
	private CoursesDaoImpl coursesDao = new CoursesDaoImpl();
	
	/*
	 * constructor
	 * */
	public LikeCrsDaoImpl(){
	}
	
	/**
	 * constructor2
	 * */
	public LikeCrsDaoImpl(ServletContext context){
		this.conn = new ConnDB(context);
	}
	
	/*
	 * method insert information into database tables
	 * @return Like_ID if successfully
	 * */
	public long insertLikeCrs(LikeCrs lc){
		long flag = 0;
		String sql = "insert into LikeCrs values(null, "+lc.getAcc_ID()+", "+lc.getRep_ID()+", "+lc.getCrs_ID()+", "+lc.getLike_ryb()+")";
		this.conn.executeUpdate(sql);
		
		Courses cou = new Courses();
		cou.setCrs_ID(lc.getCrs_ID());
		if(this.coursesDao.increaseCrs_like(cou)){
			ResultSet rs = this.conn.executeQuery("select Like_ID from LikeCrs where Acc_ID = "+lc.getAcc_ID()+" and Rep_ID = "+lc.getRep_ID()+" and Crs_ID = "+lc.getCrs_ID()+"");
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