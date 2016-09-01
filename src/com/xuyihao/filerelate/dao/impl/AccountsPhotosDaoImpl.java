package com.xuyihao.filerelate.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.xuyihao.common.DatabaseConnector;
import com.xuyihao.filerelate.dao.AccountsPhotosDao;
import com.xuyihao.filerelate.entity.AccountsPhotos;

public class AccountsPhotosDaoImpl implements AccountsPhotosDao {
	private DatabaseConnector databaseConnector = new DatabaseConnector();

	public boolean saveAccountsPhotos(AccountsPhotos accountsPhotos) {
		String sql = "insert into " + AccountsPhotos.BASE_TABLE_NAME + " values(null, '" + accountsPhotos.getAcc_ID()
				+ "', '" + accountsPhotos.getHeadPhoto_ID() + "', '" + accountsPhotos.getPhoto_ID_Combine() + "', '"
				+ accountsPhotos.getAccPhoto_addTime() + "')";
		if (this.databaseConnector.executeUpdate(sql) != 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean deleteAccountsPhotos(String Acc_ID) {
		String sql = "delete from " + AccountsPhotos.BASE_TABLE_NAME + " where "
				+ AccountsPhotos.BASE_ACCOUNTSPHOTOS_ACCOUNT_ID + " = '" + Acc_ID + "'";
		if (this.databaseConnector.executeUpdate(sql) != 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean updateAccountsPhotos(AccountsPhotos accountsPhotos) {
		String sql = "update " + AccountsPhotos.BASE_TABLE_NAME + " set " + AccountsPhotos.BASE_ACCOUNTSPHOTOS_HEADPHOTO_ID
				+ " = '" + accountsPhotos.getHeadPhoto_ID() + "', " + AccountsPhotos.BASE_ACCOUNTSPHOTOS_PHOTO_ID_COMBINE
				+ " = '" + accountsPhotos.getPhoto_ID_Combine() + "' where " + AccountsPhotos.BASE_ACCOUNTSPHOTOS_ACCOUNT_ID
				+ " = '" + accountsPhotos.getAcc_ID() + "'";
		if (this.databaseConnector.executeUpdate(sql) != 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean updateAccountsPhotosBySql(String update) {
		if (this.databaseConnector.executeUpdate(update) != 0) {
			return true;
		} else {
			return false;
		}
	}

	public AccountsPhotos query(String Acc_ID) {
		String sql = "select * from " + AccountsPhotos.BASE_TABLE_NAME + " where "
				+ AccountsPhotos.BASE_ACCOUNTSPHOTOS_ACCOUNT_ID + " = '" + Acc_ID + "'";
		ResultSet rs = this.databaseConnector.executeQuery(sql);
		return this.getAccountsPhotoByResultSet(rs);
	}

	public AccountsPhotos queryBySql(String query) {
		ResultSet rs = this.databaseConnector.executeQuery(query);
		return this.getAccountsPhotoByResultSet(rs);
	}

	private AccountsPhotos getAccountsPhotoByResultSet(ResultSet resultSet) {
		AccountsPhotos photo = new AccountsPhotos();
		try {
			if (resultSet.next()) {
				photo.set_id(resultSet.getLong(AccountsPhotos.BASE_PHYSICAL_ID));
				photo.setAcc_ID(resultSet.getString(AccountsPhotos.BASE_ACCOUNTSPHOTOS_ACCOUNT_ID));
				photo.setHeadPhoto_ID(resultSet.getString(AccountsPhotos.BASE_ACCOUNTSPHOTOS_HEADPHOTO_ID));
				photo.setPhoto_ID_Combine(resultSet.getString(AccountsPhotos.BASE_ACCOUNTSPHOTOS_PHOTO_ID_COMBINE));
				photo.setAccPhoto_addTime(resultSet.getString(AccountsPhotos.BASE_ACCOUNTSPHOTOS_ADD_TIME));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return photo;
	}

	public void closeDBConnection() {
		this.databaseConnector.close();
	}
}