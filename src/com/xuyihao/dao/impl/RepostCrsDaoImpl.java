package com.xuyihao.dao.impl;

import com.xuyihao.dao.RepostCrsDao;
import com.xuyihao.tools.ConnDB;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xuyh at 16-8-13 下午2:15
 */
public class RepostCrsDaoImpl implements RepostCrsDao {
	private ConnDB connDB = new ConnDB();

	public void setConnDB(ConnDB connDB) {
		this.connDB = connDB;
	}

	@Override
	public boolean saveRepostCrs(String Acc_Id, String Rep_Id, String Crs_Id, String Rep_addTime) {
		boolean flag = false;
		String sql = "insert into RepostCrs values(null, '" + Acc_Id + "', '" + Rep_Id + "', '" + Crs_Id + "', '"
				+ Rep_addTime + "')";
		if (this.connDB.executeUpdate(sql) != 0) {
			flag = true;
		}
		this.connDB.close();
		return flag;
	}

	@Override
	public boolean deleteRepostCrs(String Acc_Id, String Rep_Id, String Crs_Id) {
		boolean flag = false;
		String sql = "delete from RepostCrs where Acc_ID = '" + Acc_Id + "' and Rep_ID = '" + Rep_Id + "' and Crs_ID = '"
				+ Crs_Id + "'";
		if (this.connDB.executeUpdate(sql) != 0) {
			flag = true;
		}
		this.connDB.close();
		return flag;
	}

	@Override
	public boolean deleteRepostCrs(String Acc_Id, String Rep_Id) {
		boolean flag = false;
		String sql = "delete from RepostCrs where Acc_ID = '" + Acc_Id + "' and Rep_ID = '" + Rep_Id + "'";
		if (this.connDB.executeUpdate(sql) != 0) {
			flag = true;
		}
		this.connDB.close();
		return flag;
	}

	@Override
	public boolean deleteRepostCrsAll(String Acc_Id) {
		boolean flag = false;
		String sql = "delete from RepostCrs where Acc_ID = '" + Acc_Id + "'";
		if (this.connDB.executeUpdate(sql) != 0) {
			flag = true;
		}
		this.connDB.close();
		return flag;
	}

	@Override
	public boolean deleteBySql(String sql) {
		boolean flag = false;
		if (this.connDB.executeUpdate(sql) != 0) {
			flag = true;
		}
		this.connDB.close();
		return flag;
	}

	@Override
	public List<String> queryByAccountId(String Acc_Id) {
		String sql = "select * from RepostCrs where Acc_ID = '" + Acc_Id + "'";
		ResultSet resultSet = this.connDB.executeQuery(sql);
		List<String> courseIdList = new ArrayList<>();
		try {
			while (resultSet.next()) {
				courseIdList.add(resultSet.getString("Crs_ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.connDB.close();
		return courseIdList;
	}

	@Override
	public List<String> query(String Acc_Id, String Rep_Id) {
		String sql = "select * from RepostCrs where Acc_ID = '" + Acc_Id + "' and Rep_ID = '" + Rep_Id + "'";
		ResultSet resultSet = this.connDB.executeQuery(sql);
		List<String> courseIdList = new ArrayList<>();
		try {
			while (resultSet.next()) {
				courseIdList.add(resultSet.getString("Crs_ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.connDB.close();
		return courseIdList;
	}

	@Override
	public ResultSet queryBySql(String sql) {
		ResultSet resultSet = this.connDB.executeQuery(sql);
		return resultSet;
	}

	@Override
	public void closeDBConnection() {
		this.connDB.close();
	}
}
