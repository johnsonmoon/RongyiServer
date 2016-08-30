package com.xuyihao.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.xuyihao.common.DatabaseConnector;
import com.xuyihao.dao.AccountsDao;
import com.xuyihao.entity.Accounts;

/**
 * created by xuyihao on 2016/4/1
 * 
 * @describe 用户相关数据库操作封装类
 */
public class AccountsDaoImpl implements AccountsDao {
	private DatabaseConnector conn = new DatabaseConnector();

	public boolean saveAccounts(Accounts accounts) {
		boolean flag = false;
		String sql = "insert into " + Accounts.BASE_TABLE_NAME + " values(null, '" + accounts.getAcc_ID() + "', '"
				+ accounts.getAcc_name() + "', '" + accounts.getAcc_pwd() + "', '" + accounts.getAcc_sex() + "', '"
				+ accounts.getAcc_loc() + "', " + accounts.getAcc_lvl() + ", " + accounts.getAcc_ryb() + ", "
				+ accounts.isAcc_shop() + ", " + accounts.getAcc_atn() + ", " + accounts.getAcc_atnd() + ", "
				+ accounts.getAcc_pub() + ", '" + accounts.getAcc_no() + "', '" + accounts.getAcc_name2() + "', '"
				+ accounts.getAcc_tel() + "', DATE_FORMAT('" + accounts.getAcc_addTime() + "', '%Y-%m-%d %H:%i:%s'))";
		if (this.conn.executeUpdate(sql) != 0) {
			flag = true;
		}
		this.conn.close();
		return flag;
	}

	public boolean deleteAccounts(String Acc_ID) {
		boolean flag = false;
		String sql = "delete from " + Accounts.BASE_TABLE_NAME + " where " + Accounts.BASE_ACCOUNT_ID + " = '" + Acc_ID
				+ "'";
		if (this.conn.executeUpdate(sql) != 0) {
			flag = true;
		}
		this.conn.close();
		return flag;
	}

	public boolean updateAccounts(Accounts accounts) {
		boolean flag = false;
		String sql = "update " + Accounts.BASE_TABLE_NAME + " set " + Accounts.BASE_ACCOUNT_NAME + " = '"
				+ accounts.getAcc_name() + "', " + Accounts.BASE_ACCOUNT_PASSWORD + " = '" + accounts.getAcc_pwd() + "', "
				+ Accounts.BASE_ACCOUNT_SEX + " = '" + accounts.getAcc_sex() + "', " + Accounts.BASE_ACCOUNT_LOCATION + " = '"
				+ accounts.getAcc_loc() + "', " + Accounts.BASE_ACCOUNT_LEVEL + " = " + accounts.getAcc_lvl() + ", "
				+ Accounts.BASE_ACCOUNT_RYB + " = " + accounts.getAcc_ryb() + ", " + Accounts.BASE_ACCOUNT_IS_SHOP_OWNER + " = "
				+ accounts.isAcc_shop() + ", " + Accounts.BASE_ACCOUNT_ATTENTION_COUNT + " = " + accounts.getAcc_atn() + ", "
				+ Accounts.BASE_ACCOUNT_ATTENED_COUNT + " = " + accounts.getAcc_atnd() + ", " + Accounts.BASE_ACCOUNT_PUBLISH
				+ " = " + accounts.getAcc_pub() + ", " + Accounts.BASE_ACCOUNT_NO + " = '" + accounts.getAcc_no() + "', "
				+ Accounts.BASE_ACCOUNT_NAME2 + " = '" + accounts.getAcc_name2() + "', " + Accounts.BASE_ACCOUNT_TELEPHONE
				+ " = '" + accounts.getAcc_tel() + "', " + Accounts.BASE_ACCOUNT_ADD_TIME + " = '" + accounts.getAcc_addTime()
				+ "' where " + Accounts.BASE_ACCOUNT_ID + " = '" + accounts.getAcc_ID() + "'";
		if (this.conn.executeUpdate(sql) != 0) {
			flag = true;
		}
		this.conn.close();
		return flag;
	}

	public boolean updateAccounts(String update) {
		boolean flag = false;
		String sql = update;
		if (this.conn.executeUpdate(sql) != 0) {
			flag = true;
		}
		this.conn.close();
		return flag;
	}

	public Accounts queryByName(String Acc_name) {
		String sql = "select * from " + Accounts.BASE_TABLE_NAME + " where " + Accounts.BASE_ACCOUNT_NAME + " = '"
				+ Acc_name + "'";
		ResultSet rs = this.conn.executeQuery(sql);
		Accounts accounts = getAccountsValueFromResultSet(rs);
		this.conn.close();
		return accounts;
	}

	public Accounts queryById(String Acc_ID) {
		String sql = "select * from " + Accounts.BASE_TABLE_NAME + " where " + Accounts.BASE_ACCOUNT_ID + " = '" + Acc_ID
				+ "'";
		ResultSet rs = this.conn.executeQuery(sql);
		Accounts accounts = getAccountsValueFromResultSet(rs);
		this.conn.close();
		return accounts;
	}

	public ResultSet queryBySql(String query) {
		ResultSet resultSet = this.conn.executeQuery(query);
		return resultSet;
	}

	/**
	 * 从ResultSet中取出Accounts对象
	 *
	 */
	private Accounts getAccountsValueFromResultSet(ResultSet resultSet) {
		Accounts accounts = new Accounts();
		try {
			if (resultSet.next()) {// if query successfully
				accounts.set_id(resultSet.getLong(Accounts.BASE_ACCOUNT_PHYSICAL_ID));
				accounts.setAcc_ID(resultSet.getString(Accounts.BASE_ACCOUNT_ID));
				accounts.setAcc_name(resultSet.getString(Accounts.BASE_ACCOUNT_NAME));
				accounts.setAcc_pwd(resultSet.getString(Accounts.BASE_ACCOUNT_PASSWORD));
				accounts.setAcc_sex(resultSet.getString(Accounts.BASE_ACCOUNT_SEX));
				accounts.setAcc_loc(resultSet.getString(Accounts.BASE_ACCOUNT_LOCATION));
				accounts.setAcc_lvl(resultSet.getInt(Accounts.BASE_ACCOUNT_LEVEL));
				accounts.setAcc_ryb(resultSet.getInt(Accounts.BASE_ACCOUNT_RYB));
				accounts.setAcc_shop(resultSet.getBoolean(Accounts.BASE_ACCOUNT_IS_SHOP_OWNER));
				accounts.setAcc_atn(resultSet.getInt(Accounts.BASE_ACCOUNT_ATTENTION_COUNT));
				accounts.setAcc_atnd(resultSet.getInt(Accounts.BASE_ACCOUNT_ATTENED_COUNT));
				accounts.setAcc_pub(resultSet.getInt(Accounts.BASE_ACCOUNT_PUBLISH));
				accounts.setAcc_no(resultSet.getString(Accounts.BASE_ACCOUNT_NO));
				accounts.setAcc_name2(resultSet.getString(Accounts.BASE_ACCOUNT_NAME2));
				accounts.setAcc_tel(resultSet.getString(Accounts.BASE_ACCOUNT_TELEPHONE));
				accounts.setAcc_addTime(resultSet.getString(Accounts.BASE_ACCOUNT_ADD_TIME));
			} else {
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accounts;
	}

	public void closeDBConnection() {
		this.conn.close();
	}
}