package com.xuyihao.filerelate.dao.impl;

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
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updateAccountsPhotos(AccountsPhotos accountsPhotos) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean updateAccountsPhotosBySql(String update) {
		// TODO Auto-generated method stub
		return false;
	}

	public AccountsPhotos query(String Acc_ID) {
		// TODO Auto-generated method stub
		return null;
	}

	public AccountsPhotos queryBySql(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	public void closeDBConnection() {
		this.databaseConnector.close();
	}
}