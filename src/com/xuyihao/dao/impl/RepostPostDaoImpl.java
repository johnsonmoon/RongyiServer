package com.xuyihao.dao.impl;

import com.xuyihao.common.DatabaseConnector;
import com.xuyihao.dao.RepostPostDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * Created by Xuyh at 16-8-13 下午3:23
 */
@Component("RepostPostDao")
public class RepostPostDaoImpl implements RepostPostDao {
	private DatabaseConnector connDB = new DatabaseConnector();

	public void setConnDB(DatabaseConnector connDB) {
		this.connDB = connDB;
	}

	public boolean saveRepostPost(String Acc_Id, String Rep_Id, String Post_Id, String Rep_addTime) {
		boolean flag = false;
		String sql = "insert into RepostPost values(null, '" + Acc_Id + "', '" + Rep_Id + "', '" + Post_Id
				+ "', DATE_FORMAT('" + Rep_addTime + "', '%Y-%m-%d %H:%i:%s'))";
		if (this.connDB.executeUpdate(sql) != 0) {
			flag = true;
		}
		this.connDB.close();
		return flag;
	}

	public boolean deleteRepostPost(String Acc_Id, String Rep_Id, String Post_Id) {
		boolean flag = false;
		String sql = "delete from RepostPost where Acc_ID = '" + Acc_Id + "' and Rep_ID = '" + Rep_Id + "' and Post_ID = '"
				+ Post_Id + "'";
		if (this.connDB.executeUpdate(sql) != 0) {
			flag = true;
		}
		this.connDB.close();
		return flag;
	}

	public boolean deleteRepostPost(String Acc_Id, String Rep_Id) {
		boolean flag = false;
		String sql = "delete from RepostPost where Acc_ID = '" + Acc_Id + "' and Rep_ID = '" + Rep_Id + "'";
		if (this.connDB.executeUpdate(sql) != 0) {
			flag = true;
		}
		this.connDB.close();
		return flag;
	}

	public boolean deleteRepostPostAll(String Acc_Id) {
		boolean flag = false;
		String sql = "delete from RepostPost where Acc_ID = '" + Acc_Id + "'";
		if (this.connDB.executeUpdate(sql) != 0) {
			flag = true;
		}
		this.connDB.close();
		return flag;
	}

	public boolean deleteBySql(String sql) {
		boolean flag = false;
		if (this.connDB.executeUpdate(sql) != 0) {
			flag = true;
		}
		this.connDB.close();
		return flag;
	}

	public List<String> queryByAccountId(String Acc_Id) {
		String sql = "select * from RepostPost where Acc_ID = '" + Acc_Id + "'";
		ResultSet resultSet = this.connDB.executeQuery(sql);
		List<String> postIdList = new ArrayList<>();
		try {
			while (resultSet.next()) {
				postIdList.add(resultSet.getString("Post_ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.connDB.close();
		return postIdList;
	}

	public List<String> query(String Acc_Id, String Rep_Id) {
		String sql = "select * from RepostPost where Acc_ID = '" + Acc_Id + "' and Rep_ID = '" + Rep_Id + "'";
		ResultSet resultSet = this.connDB.executeQuery(sql);
		List<String> postIdList = new ArrayList<>();
		try {
			while (resultSet.next()) {
				postIdList.add(resultSet.getString("Post_ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.connDB.close();
		return postIdList;
	}

	public ResultSet queryBySql(String sql) {
		ResultSet resultSet = this.connDB.executeQuery(sql);
		return resultSet;
	}

	public void closeDBConnection() {
		this.connDB.close();
	}
}