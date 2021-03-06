package xuyihao.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import xuyihao.common.DatabaseConnector;
import xuyihao.dao.FavouriteDao;

/**
 * Created by Xuyh at 16-8-13 上午11:20
 */
@Component("FavouriteDao")
public class FavouriteDaoImpl implements FavouriteDao {
	private DatabaseConnector connDB = new DatabaseConnector();

	public void setConnDB(DatabaseConnector connDB) {
		this.connDB = connDB;
	}

	public boolean saveFavourite(String Acc_Id, String Shop_Id, String Favo_addTime) {
		boolean flag = false;
		String sql = "insert into Favourite values(null, '" + Acc_Id + "', '" + Shop_Id + "', DATE_FORMAT('" + Favo_addTime
				+ "', '%Y-%m-%d %H:%i:%s'))";
		if (this.connDB.executeUpdate(sql) != 0) {
			flag = true;
		}
		this.connDB.close();
		return flag;
	}

	public boolean deleteFavourite(String Acc_Id, String Shop_Id) {
		boolean flag = false;
		String sql = "delete from Favourite where Acc_ID = '" + Acc_Id + "' and Shop_ID = '" + Shop_Id + "'";
		if (this.connDB.executeUpdate(sql) != 0) {
			flag = true;
		}
		this.connDB.close();
		return flag;
	}

	public boolean deleteFavouriteAll(String Acc_Id) {
		boolean flag = false;
		String sql = "delete from Favourite where Acc_Id = '" + Acc_Id + "'";
		if (this.connDB.executeUpdate(sql) != 0) {
			flag = true;
		}
		this.connDB.close();
		return flag;
	}

	public boolean deleteFavouriteBySql(String sql) {
		boolean flag = false;
		if (this.connDB.executeUpdate(sql) != 0) {
			flag = true;
		}
		this.connDB.close();
		return flag;
	}

	public List<String> queryByAccountId(String Acc_Id) {
		String sql = "select * from Favourite where Acc_ID = '" + Acc_Id + "'";
		ResultSet resultSet = this.connDB.executeQuery(sql);
		List<String> shopIdList = new ArrayList<>();
		try {
			while (resultSet.next()) {
				shopIdList.add(resultSet.getString("Shop_ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.connDB.close();
		return shopIdList;
	}

	public List<String> queryByShopId(String Shop_Id) {
		String sql = "select * from Favourite where Shop_ID = '" + Shop_Id + "'";
		ResultSet resultSet = this.connDB.executeQuery(sql);
		List<String> accountIdList = new ArrayList<>();
		try {
			while (resultSet.next()) {
				accountIdList.add(resultSet.getString("Shop_ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.connDB.close();
		return accountIdList;
	}

	public void closeDBConnection() {
		this.connDB.close();
	}
}