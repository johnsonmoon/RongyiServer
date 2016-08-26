package com.xuyihao.dao.impl;

import com.xuyihao.common.DatabaseConnector;
import com.xuyihao.dao.WantDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Xuyh at 16-8-13 上午10:29
 */
public class WantDaoImpl implements WantDao {
	private DatabaseConnector connDB = new DatabaseConnector();

	@Override
	public boolean saveWant(String Acc_ID, String Prod_ID, String Want_addTime) {
		boolean flag = false;
		String sql = "insert into Want values(null, '" + Acc_ID + "', '" + Prod_ID + "', '" + Want_addTime + "')";
		if (this.connDB.executeUpdate(sql) != 0) {
			flag = true;
		}
		this.connDB.close();
		return flag;
	}

	@Override
	public boolean deleteWant(String Acc_Id, String Prod_ID) {
		boolean flag = false;
		String sql = "delete from Want where Acc_ID = '" + Acc_Id + "' and Prod_ID = '" + Prod_ID + "'";
		if (this.connDB.executeUpdate(sql) != 0) {
			flag = true;
		}
		this.connDB.close();
		return flag;
	}

	@Override
	public boolean deleteWantAll(String Acc_Id) {
		boolean flag = false;
		String sql = "delete from Want where Acc_ID = '" + Acc_Id + "'";
		if (this.connDB.executeUpdate(sql) != 0) {
			flag = true;
		}
		this.connDB.close();
		return flag;
	}

	@Override
	public boolean deleteWantBySql(String sql) {
		boolean flag = false;
		if (this.connDB.executeUpdate(sql) != 0) {
			flag = true;
		}
		this.connDB.close();
		return flag;
	}

	@Override
	public List<String> queryByAccountId(String Acc_Id) {
		String sql = "select * from Want where Acc_ID = '" + Acc_Id + "'";
		ResultSet resultSet = this.connDB.executeQuery(sql);
		List<String> productIdList = new ArrayList<>();
		try {
			while (resultSet.next()) {
				productIdList.add(resultSet.getString("Prod_ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.connDB.close();
		return productIdList;
	}

	@Override
	public List<String> queryByProductId(String Prod_Id) {
		String sql = "select * from Want where Prod_ID = '" + Prod_Id + "'";
		ResultSet resultSet = this.connDB.executeQuery(sql);
		List<String> accountIdList = new ArrayList<>();
		try {
			while (resultSet.next()) {
				accountIdList.add(resultSet.getString("Acc_ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		this.connDB.close();
		return accountIdList;
	}

	public void setConnDB(DatabaseConnector connDB) {
		this.connDB = connDB;
	}

	@Override
	public void closeDBConnection() {
		this.connDB.close();
	}
}
