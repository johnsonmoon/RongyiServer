package com.xuyihao.filerelate.logic.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xuyihao.filerelate.dao.AccountsPhotosDao;
import com.xuyihao.filerelate.entity.AccountsPhotos;
import com.xuyihao.filerelate.logic.AccountsPhotosLogic;
import com.xuyihao.tools.utils.DateUtils;

@Component("AccountsPhotosLogic")
public class AccountsPhotosLogicImpl implements AccountsPhotosLogic {
	@Autowired
	private AccountsPhotosDao accountsPhotosDao;

	public void setAccountsPhotosDao(AccountsPhotosDao accountsPhotosDao) {
		this.accountsPhotosDao = accountsPhotosDao;
	}

	public boolean saveAccountsPhotos(String Acc_ID, String HeadPhoto_ID, List<String> photoIdList) {
		AccountsPhotos accountsPhotos = new AccountsPhotos();
		accountsPhotos.setAcc_ID(Acc_ID);
		accountsPhotos.setHeadPhoto_ID(HeadPhoto_ID);
		if (photoIdList != null && photoIdList.size() > 0) {
			String photoIdCombine = "";

			for (String id : photoIdList) {
				photoIdCombine += (id + "&&");
			}
			photoIdCombine = photoIdCombine.substring(0, photoIdCombine.length() - 2);
			accountsPhotos.setPhoto_ID_Combine(photoIdCombine);
		}
		accountsPhotos.setAccPhoto_addTime(DateUtils.currentDateTime());
		return this.accountsPhotosDao.saveAccountsPhotos(accountsPhotos);
	}

	public boolean deleteAccountsPhotos(String Acc_ID) {
		return this.accountsPhotosDao.deleteAccountsPhotos(Acc_ID);
	}

	public boolean changeAccountsPhotosInfo(String Acc_ID, String HeadPhoto_ID, List<String> photoIdList) {
		AccountsPhotos accountsPhotos = this.accountsPhotosDao.query(Acc_ID);
		if (accountsPhotos.get_id() == 0) {
			return false;
		}
		if (HeadPhoto_ID != null && !HeadPhoto_ID.equals("")) {
			accountsPhotos.setHeadPhoto_ID(HeadPhoto_ID);
		}
		if (photoIdList != null && photoIdList.size() > 0) {
			String photoIdCombine = "";
			for (String id : photoIdList) {
				photoIdCombine += (id + "&&");
			}
			photoIdCombine = photoIdCombine.substring(0, photoIdCombine.length() - 2);
			accountsPhotos.setPhoto_ID_Combine(photoIdCombine);
		}
		return this.accountsPhotosDao.updateAccountsPhotos(accountsPhotos);
	}

	public AccountsPhotos getAccountsPhotosInfo(String Acc_ID) {
		return this.accountsPhotosDao.query(Acc_ID);
	}

	public String getAccountsHeadPhoto(String Acc_ID) {
		AccountsPhotos accountsPhotos = this.accountsPhotosDao.query(Acc_ID);
		String headPhotoId = accountsPhotos.getHeadPhoto_ID();
		return headPhotoId;
	}

	public List<String> getAccountsOtherPhotos(String Acc_ID) {
		AccountsPhotos accountsPhotos = this.accountsPhotosDao.query(Acc_ID);
		String[] idArray = accountsPhotos.getPhoto_ID_Combine().split("&&");
		List<String> idList = new ArrayList<String>();
		for (int i = 0; i < idArray.length; i++) {
			idList.add(idArray[i]);
		}
		return idList;
	}
}