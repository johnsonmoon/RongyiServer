package test.xuyihao.dao;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import test.xuyihao.CommonTest;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import xuyihao.dao.AccountsDao;
import xuyihao.dao.ShopsDao;
import xuyihao.entity.Accounts;
import xuyihao.entity.Shops;
import xuyihao.tools.utils.DateUtils;
import xuyihao.tools.utils.RandomUtils;

/**
 * 
 * @Author Xuyh created at Aug 14, 2016 3:40:20 PM.
 *
 */
public class ShopsDaoTest extends CommonTest {
	private static List<String> Shop_IDList = new ArrayList<>();
	private static List<String> Shop_nameList = new ArrayList<>();
	private static List<String> Acc_IDList = new ArrayList<>();

	@Autowired
	private AccountsDao accountsDao;

	@Autowired
	private ShopsDao shopsDao;

	public void setAccountsDao(AccountsDao accountsDao) {
		this.accountsDao = accountsDao;
	}

	public void setShopsDao(ShopsDao shopsDao) {
		this.shopsDao = shopsDao;
	}

	@Before
	public void setUp() {
		Shop_IDList.clear();
		Shop_nameList.clear();
		Acc_IDList.clear();
		for (int i = 0; i < 50; i++) {
			String Acc_ID = RandomUtils.getRandomString(15) + "Acc";
			String Shop_ID = RandomUtils.getRandomString(15) + "Shop";
			String Shop_name = RandomUtils.getRandomString(20) + "Name";
			Shop_IDList.add(Shop_ID);
			Shop_nameList.add(Shop_name);
			Acc_IDList.add(Acc_ID);
			Accounts accounts = new Accounts();
			accounts.setAcc_ID(Acc_ID);
			accounts.setAcc_name("Johnson" + RandomUtils.getRandomNumberString(4));
			accounts.setAcc_pwd("moon" + RandomUtils.getRandomNumberString(4));
			accounts.setAcc_sex("男");
			accounts.setAcc_loc("浙江工业大学屛峰校区");
			accounts.setAcc_addTime(DateUtils.currentDateTime());
			this.accountsDao.saveAccounts(accounts);
			Shops shop = new Shops();
			shop.setAcc_ID(Acc_ID);
			shop.setShop_addTime(DateUtils.currentDateTime());
			shop.setShop_ID(Shop_ID);
			shop.setShop_info(RandomUtils.getRandomString(50));
			shop.setShop_licen(RandomUtils.getRandomString(50));
			shop.setShop_name(Shop_name);
			this.shopsDao.saveShops(shop);
		}
	}

	@Test
	public void testSaveShops() {
		boolean flag = true;
		for (int i = 0; i < 10; i++) {
			Shops shop = new Shops();
			shop.setAcc_ID(Acc_IDList.get(i));
			shop.setShop_addTime(DateUtils.currentDateTime());
			shop.setShop_ID(RandomUtils.getRandomString(15) + "Shop");
			shop.setShop_info(RandomUtils.getRandomString(50));
			shop.setShop_licen(RandomUtils.getRandomString(50));
			shop.setShop_name(RandomUtils.getRandomString(20));
			flag = flag && this.shopsDao.saveShops(shop);
		}
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testDeleteShops() {
		boolean flag = true;
		for (int i = 45; i < Shop_IDList.size(); i++) {
			flag = flag && this.shopsDao.deleteShops(Shop_IDList.get(i));
		}
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testUpdateShopsShops() {
		boolean flag = true;
		for (int i = 0; i < 20; i++) {
			Shops shop = this.shopsDao.queryById(Shop_IDList.get(i));
			shop.setShop_licen("lalalalalala");
			shop.setShop_info("qpwoieurueiwrtyhu");
			flag = flag && this.shopsDao.updateShops(shop);
		}
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testQueryById() {
		for (int i = 0; i < 45; i++) {
			Shops shop = this.shopsDao.queryById(Shop_IDList.get(i));
			Assert.assertNotNull(shop);
			System.out.println(shop.toJSONString());
		}
	}

	@Test
	public void testQueryByName() {
		for (int i = 0; i < 45; i++) {
			Shops shop = this.shopsDao.queryById(Shop_IDList.get(i));
			Assert.assertNotNull(shop);
			System.out.println(shop.toJSONString());
		}
	}

	@Test
	public void testQueryByAccountId() {
		for (String Acc_ID : Acc_IDList) {
			List<Shops> shopsList = this.shopsDao.queryByAccountId(Acc_ID);
			Assert.assertNotNull(shopsList);
			for (Shops shop : shopsList) {
				System.out.println(shop.toJSONString());
			}
		}
	}
}
