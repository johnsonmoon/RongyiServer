package test.xuyihao.dao;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import test.xuyihao.CommonTest;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import xuyihao.dao.AccountsDao;
import xuyihao.dao.FavouriteDao;
import xuyihao.dao.ShopsDao;
import xuyihao.entity.Accounts;
import xuyihao.entity.Shops;
import xuyihao.tools.utils.DateUtils;
import xuyihao.tools.utils.RandomUtils;

/**
 * 
 * @Author Xuyh created at Aug 14, 2016 3:32:20 PM.
 *
 */
public class FavouriteDaoTest extends CommonTest {
	private static List<String> Shop_IDList = new ArrayList<>();
	private static String Acc_ID = null;

	@Autowired
	private AccountsDao accountsDao;

	@Autowired
	private ShopsDao shopsDao;

	@Autowired
	private FavouriteDao favouriteDao;

	public void setFavouriteDao(FavouriteDao favouriteDao) {
		this.favouriteDao = favouriteDao;
	}

	public void setAccountsDao(AccountsDao accountsDao) {
		this.accountsDao = accountsDao;
	}

	public void setShopsDao(ShopsDao shopsDao) {
		this.shopsDao = shopsDao;
	}

	@Before
	public void setUp() {
		Shop_IDList.clear();
		Acc_ID = RandomUtils.getRandomString(15) + "Acc";
		Accounts accounts = new Accounts();
		accounts.setAcc_ID(Acc_ID);
		accounts.setAcc_name("Johnson" + RandomUtils.getRandomString(5));
		accounts.setAcc_pwd("moon" + RandomUtils.getRandomString(5));
		accounts.setAcc_sex("男");
		accounts.setAcc_loc("浙江工业大学屛峰校区");
		accounts.setAcc_addTime(DateUtils.currentDateTime());
		this.accountsDao.saveAccounts(accounts);
		for (int i = 0; i < 50; i++) {
			String Shop_ID = RandomUtils.getRandomString(15) + "Shop";
			Shop_IDList.add(Shop_ID);
			Shops shop = new Shops();
			shop.setShop_ID(Shop_ID);
			shop.setAcc_ID(Acc_ID);
			shop.setShop_addTime(DateUtils.currentDateTime());
			shop.setShop_info(RandomUtils.getRandomString(50));
			shop.setShop_licen(RandomUtils.getRandomString(50));
			shop.setShop_name(RandomUtils.getRandomString(6));
			this.shopsDao.saveShops(shop);
			// ---add favourites
			this.favouriteDao.saveFavourite(Acc_ID, Shop_ID, DateUtils.currentDateTime());
		}
	}

	@Test
	public void testSaveFavourite() {
		boolean flag = true;
		for (int i = 0; i < 10; i++) {
			String Shop_ID = RandomUtils.getRandomString(15) + "Shop";
			Shops shop = new Shops();
			shop.setShop_ID(Shop_ID);
			shop.setAcc_ID(Acc_ID);
			shop.setShop_addTime(DateUtils.currentDateTime());
			shop.setShop_info(RandomUtils.getRandomString(50));
			shop.setShop_licen(RandomUtils.getRandomString(50));
			shop.setShop_name(RandomUtils.getRandomString(6));
			this.shopsDao.saveShops(shop);
			// ---add favourites
			flag = flag && this.favouriteDao.saveFavourite(Acc_ID, Shop_ID, DateUtils.currentDateTime());
		}
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testDeleteFavourite() {
		boolean flag = true;
		for (int i = 45; i < 50; i++) {
			flag = flag && this.favouriteDao.deleteFavourite(Acc_ID, Shop_IDList.get(i));
		}
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testDeleteFavouriteAll() {
		boolean flag = this.favouriteDao.deleteFavouriteAll(Acc_ID);
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testQueryByAccountId() {
		List<String> Shop_IDList = this.favouriteDao.queryByAccountId(Acc_ID);
		for (String Shop_ID : Shop_IDList) {
			System.out.println(Shop_ID);
		}
	}

	@Test
	public void testQueryByShopId() {
		for (String Shop_ID : Shop_IDList) {
			List<String> Acc_IDList = this.favouriteDao.queryByShopId(Shop_ID);
			for (String Acc_ID : Acc_IDList) {
				System.out.println(Acc_ID);
			}
		}
	}

}
