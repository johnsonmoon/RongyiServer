/*
	 * field
	 * */
	private ConnDB conn;
	
	/*
	 * constructor
	 * */
	public CoursesDaoImpl(){
	}
	
	/**
	 * constructor2
	 * */
	public CoursesDaoImpl(ServletContext context){
		this.conn = new ConnDB(context);
	}
	
	/*
	 * method insert info into database table, create a new Course
	 * */
	public long insertCourse(Courses cou){
		long flag = 0;
		String sql = "insert into Courses values(null, '"+cou.getCrs_name()+"','"+cou.getCrs_route()+"', "+cou.getAcc_ID()+", "+cou.getAuthor_ID()+", "+cou.getCrs_rep()+", "+cou.getCrs_comm()+", "+cou.getCrs_like()+")";
		this.conn.executeUpdate(sql);
		ResultSet rs = this.conn.executeQuery("select Crs_ID from Courses where Crs_name = '"+cou.getCrs_name()+"'");
		try{
			if(rs.next()){
				flag = rs.getLong("Crs_ID");
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
	 * method delete info from database table
	 * */
	public boolean deleteCourse(Courses cou){
		boolean flag = false;
		String sql = "delete from Courses where Crs_ID = "+cou.getCrs_ID()+"";
		if(this.conn.executeUpdate(sql) == 0){
			flag = false;
		}else{
			flag = true;
		}
		this.conn.close();
		return flag;
	}
	
	/*
	 * method get Course information by detail, complete info to parameter cou and returns the result which shows whether completed
	 * */
	public boolean getCourseInfo(Courses cou){
		boolean flag = false;
		String sql = "select * from Courses where Crs_ID = "+cou.getCrs_ID()+"";
		ResultSet rs = this.conn.executeQuery(sql);
		try{
			if(rs.next()){
				flag = true;
				cou.setCrs_name(rs.getString("Crs_name"));
				cou.setCrs_route(rs.getString("Crs_route"));
				cou.setAcc_ID(rs.getLong("Acc_ID"));
				cou.setAuthor_ID(rs.getLong("Author_ID"));
				cou.setCrs_rep(rs.getInt("Crs_rep"));
				cou.setCrs_comm(rs.getInt("Crs_comm"));
				cou.setCrs_like(rs.getInt("Crs_like"));
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
	 * method share Course by a Account
	 * */
	public boolean shareCourse(Courses cou, Accounts acc){
		boolean flag = false;
		Courses cou2 = new Courses(cou);
		//modify the information of the new Course shared by another Account
		cou2.setAcc_ID(acc.Get_Acc_ID());
		cou2.setCrs_comm(0);
		cou2.setCrs_like(0);
		if(!this.insertRepostCrs(acc.Get_Acc_ID(), cou.getAuthor_ID(), cou.getCrs_ID())){
			flag = false;
		}else{
			if(this.insertCourse(cou2) == 0){
				flag = false;
			}else{
				if(this.increaseCrs_rep(cou)){
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
	 * method insert info into table RepostCrs
	 * @return true if successfully
	 * */	
	public boolean insertRepostCrs(long Acc_ID, long Rep_ID, long Crs_ID){
		boolean flag = false;
		String sql = "insert into RepostCrs values("+Acc_ID+", "+Rep_ID+", "+Crs_ID+")";
		if(this.conn.executeUpdate(sql) == 0){
			flag = false;
		}else{
			flag = true;
		}
		this.conn.close();
		return flag;
	}
	
	/*
	 * method set Crs_rep increase, if successfully, returns true
	 * */
	public boolean increaseCrs_rep(Courses cou){
		boolean flag = false;
		String sql = "update Courses set Crs_rep = Crs_rep+1 where Crs_ID = "+cou.getCrs_ID()+"";
		if(this.conn.executeUpdate(sql) == 0){
			flag = false;
		}else{
			flag = true;
		}
		this.conn.close();
		return flag;
	}
	
	/*
	 * method set Crs_comm increase, if successfully, returns true
	 * */
	public boolean increaseCrs_comm(Courses cou){
		boolean flag = false;
		String sql = "update Courses set Crs_comm = Crs_comm+1 where Crs_ID = "+cou.getCrs_ID()+"";
		if(this.conn.executeUpdate(sql) == 0){
			flag = false;
		}else{
			flag = true;
		}
		this.conn.close();
		return flag;
	}
	
	/*
	 * method set Crs_like increase, if successfully, returns true
	 * */
	public boolean increaseCrs_like(Courses cou){
		boolean flag = false;
		String sql = "update Courses set Crs_like = Crs_like+1 where Crs_ID = "+cou.getCrs_ID()+"";
		if(this.conn.executeUpdate(sql) == 0){
			flag = false;
		}else{
			flag = true;
		}
		this.conn.close();
		return flag;
	}