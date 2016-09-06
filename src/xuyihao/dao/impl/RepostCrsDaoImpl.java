package xuyihao.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import xuyihao.common.DatabaseConnector;
import xuyihao.dao.RepostCrsDao;

/**
 * Created by Xuyh at 16-8-13 下午2:15
 */
@Component("RepostCrsDao")
public class RepostCrsDaoImpl implements RepostCrsDao {
	private DatabaseConnector connDB = new DatabaseConnector();

	public void setConnDB(DatabaseConnector connDB) {
		this.connDB = connDB;
	}

	public boolean saveRepostCrs(String Acc_Id, String Rep_Id, String Crs_Id, String Rep_addTime) {
		boolean flag = false;
		String sql = "insert into RepostCrs values(null, '" + Acc_Id + "', '" + Rep_Id + "', '" + Crs_Id
				+ "', DATE_FORMAT('" + Rep_addTime + "', '%Y-%m-%d %H:%i:%s'))";
		if (this.connDB.executeUpdate(sql) != 0) {
			flag = true;
		}
		this.connDB.close();
		return flag;
	}

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

	public boolean deleteRepostCrs(String Acc_Id, String Rep_Id) {
		boolean flag = false;
		String sql = "delete from RepostCrs where Acc_ID = '" + Acc_Id + "' and Rep_ID = '" + Rep_Id + "'";
		if (this.connDB.executeUpdate(sql) != 0) {
			flag = true;
		}
		this.connDB.close();
		return flag;
	}

	public boolean deleteRepostCrsAll(String Acc_Id) {
		boolean flag = false;
		String sql = "delete from RepostCrs where Acc_ID = '" + Acc_Id + "'";
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

	public ResultSet queryBySql(String sql) {
		ResultSet resultSet = this.connDB.executeQuery(sql);
		return resultSet;
	}

	public void closeDBConnection() {
		this.connDB.close();
	}
}