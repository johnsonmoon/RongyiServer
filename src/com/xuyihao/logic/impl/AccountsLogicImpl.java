package com.xuyihao.logic.impl;

import com.xuyihao.dao.AccountsDao;
import com.xuyihao.dao.FavouriteDao;
import com.xuyihao.dao.ShopsDao;
import com.xuyihao.dao.WantDao;
import com.xuyihao.logic.AccountsLogic;
import com.xuyihao.entity.Accounts;
import com.xuyihao.entity.Shops;
import com.xuyihao.tools.utils.DateUtils;
import com.xuyihao.tools.utils.RandomUtils;

import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * created by XuYihao at 2016/8/10 22:24
 */
public class AccountsLogicImpl implements AccountsLogic {
	@Autowired
	private AccountsDao accountsDao;

	@Autowired
	private FavouriteDao favouriteDao;

	@Autowired
	private ShopsDao shopsDao;

	@Autowired
	private WantDao wantDao;

	public void setWantDao(WantDao wantDao) {
		this.wantDao = wantDao;
	}

	public void setFavouriteDao(FavouriteDao favouriteDao) {
		this.favouriteDao = favouriteDao;
	}

	public void setShopsDao(ShopsDao shopsDao) {
		this.shopsDao = shopsDao;
	}

	public void setAccountsDao(AccountsDao accountsDao) {
		this.accountsDao = accountsDao;
	}

	@Override
	public boolean accountNameExist(String Acc_name) {
		boolean result = false;
		Accounts accounts = accountsDao.queryByName(Acc_name);
		if ((accounts.getAcc_ID() == null) || (accounts.getAcc_ID().equals(""))) {
			result = false;
		} else {
			result = true;
		}
		return result;
	}

	@Override
	public String saveAccounts(Accounts accounts) {
		String Acc_ID = "";
		if (accountNameExist(accounts.getAcc_name())) {
			Acc_ID = "";
		} else {
			// 生成随机ID
			Acc_ID = RandomUtils.getRandomString(15) + "Acc";
			accounts.setAcc_ID(Acc_ID);
			// 获取当前时间
			accounts.setAcc_addTime(DateUtils.currentDateTime());
			this.accountsDao.saveAccounts(accounts);
		}
		return Acc_ID;
	}

	@Override
	public String login(String Acc_name, String Acc_pwd) {
		String result = "";
		Accounts DBaccount = accountsDao.queryByName(Acc_name);
		if (DBaccount == null) {
			result = "";
		} else if (DBaccount.getAcc_pwd().equals(Acc_pwd)) {
			result = DBaccount.getAcc_ID();
		} else {
			result = "";
		}
		return result;
	}

	@Override
	public boolean changeAccountInfo(Accounts accounts) {
		if ((accounts.getAcc_ID() == null) || (accounts.getAcc_ID().equals(""))) {
			return false;
		}
		Accounts DBaccount = this.accountsDao.queryById(accounts.getAcc_ID());
		accounts.setAcc_ID(DBaccount.getAcc_ID());
		if ((accounts.getAcc_name() == null) || (accounts.getAcc_name().equals(""))) {
			accounts.setAcc_name(DBaccount.getAcc_name());
		}
		if ((accounts.getAcc_pwd() == null) || (accounts.getAcc_pwd().equals(""))) {
			accounts.setAcc_pwd(DBaccount.getAcc_pwd());
		}
		if ((accounts.getAcc_sex() == null) || (accounts.getAcc_sex().equals(""))) {
			accounts.setAcc_sex(DBaccount.getAcc_sex());
		}
		if ((accounts.getAcc_loc() == null) || (accounts.getAcc_loc().equals(""))) {
			accounts.setAcc_loc(DBaccount.getAcc_loc());
		}
		if (accounts.getAcc_lvl() == 0) {
			accounts.setAcc_lvl(DBaccount.getAcc_lvl());
		}
		if (accounts.getAcc_ryb() == 0) {
			accounts.setAcc_ryb(DBaccount.getAcc_ryb());
		}
		if (DBaccount.isAcc_shop()) {
			accounts.setAcc_shop(true);
		}
		if (accounts.getAcc_atn() == 0) {
			accounts.setAcc_atn(DBaccount.getAcc_atn());
		}
		if (accounts.getAcc_atnd() == 0) {
			accounts.setAcc_atnd(DBaccount.getAcc_atnd());
		}
		if (accounts.getAcc_pub() == 0) {
			accounts.setAcc_pub(DBaccount.getAcc_pub());
		}
		if ((accounts.getAcc_no() == null) || (accounts.getAcc_no().equals(""))) {
			accounts.setAcc_no(DBaccount.getAcc_no());
		}
		if ((accounts.getAcc_name2() == null) || (accounts.getAcc_name2().equals(""))) {
			accounts.setAcc_name2(DBaccount.getAcc_name2());
		}
		if ((accounts.getAcc_tel() == null) || (accounts.getAcc_tel().equals(""))) {
			accounts.setAcc_tel(DBaccount.getAcc_tel());
		}
		accounts.setAcc_addTime(DBaccount.getAcc_addTime());
		if (accountsDao.updateAccounts(accounts)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Accounts getAccountsInformationByName(String Acc_name) {
		return accountsDao.queryByName(Acc_name);
	}

	@Override
	public Accounts getAccountsInformationById(String Acc_ID) {
		return accountsDao.queryById(Acc_ID);
	}

	@Override
	public boolean attention(String atn_Id, String atned_Id) {
		boolean result = false;
		Accounts accountAtn = accountsDao.queryById(atn_Id);
		Accounts accountAtnd = accountsDao.queryById(atned_Id);
		accountAtn.setAcc_atn(accountAtn.getAcc_atn() + 1);
		accountAtnd.setAcc_atnd(accountAtnd.getAcc_atnd() + 1);
		boolean flag1 = accountsDao.updateAccounts(accountAtn);
		boolean flag2 = accountsDao.updateAccounts(accountAtnd);
		if (flag1 && flag2) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean cancelAtention(String atn_Id, String atned_Id) {
		boolean result = false;
		Accounts accountAtn = accountsDao.queryById(atn_Id);
		Accounts accountAtnd = accountsDao.queryById(atned_Id);
		accountAtn.setAcc_atn(accountAtn.getAcc_atn() - 1);
		accountAtnd.setAcc_atnd(accountAtnd.getAcc_atnd() - 1);
		boolean flag1 = accountsDao.updateAccounts(accountAtn);
		boolean flag2 = accountsDao.updateAccounts(accountAtnd);
		if (flag1 && flag2) {
			result = true;
		}
		return result;
	}

	@Override
	public boolean favourite(String Acc_Id, String Shop_ID) {
		boolean flag1 = this.favouriteDao.saveFavourite(Acc_Id, Shop_ID, DateUtils.currentDateTime());
		Shops shopUpdate = this.shopsDao.queryById(Shop_ID);
		shopUpdate.setShop_favo(shopUpdate.getShop_favo() + 1);
		boolean flag2 = this.shopsDao.updateShops(shopUpdate);
		return (flag1 && flag2);
	}

	@Override
	public boolean cancelFavourite(String Acc_Id, String Shop_ID) {
		boolean flag1 = this.favouriteDao.deleteFavourite(Acc_Id, Shop_ID);
		Shops shopUpdate = this.shopsDao.queryById(Shop_ID);
		shopUpdate.setShop_favo(shopUpdate.getShop_favo() - 1);
		boolean flag2 = this.shopsDao.updateShops(shopUpdate);
		return (flag1 && flag2);
	}

	@Override
	public boolean want(String Acc_ID, String Prod_ID) {
		return this.wantDao.saveWant(Acc_ID, Prod_ID, DateUtils.currentDateTime());
	}

	@Override
	public boolean cancelWant(String Acc_ID, String Prod_ID) {
		return this.wantDao.deleteWant(Acc_ID, Prod_ID);
	}
}
