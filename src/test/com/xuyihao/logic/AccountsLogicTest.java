package test.com.xuyihao.logic;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xuyihao.dao.ProductsDao;
import com.xuyihao.dao.ShopsDao;
import com.xuyihao.entity.Accounts;
import com.xuyihao.entity.Products;
import com.xuyihao.entity.Shops;
import com.xuyihao.logic.AccountsLogic;
import com.xuyihao.tools.utils.RandomUtils;

import junit.framework.Assert;
import test.com.xuyihao.CommonTest;

public class AccountsLogicTest extends CommonTest {
	private static List<Accounts> accountsList = new ArrayList<>();
	private static String tempShopID = RandomUtils.getRandomString(15) + "Shop";
	private static String tempProductID = RandomUtils.getRandomString(15) + "Prod";

	@Autowired
	private AccountsLogic accountsLogic;

	@Autowired
	private ShopsDao shopsDao;

	@Autowired
	private ProductsDao productsDao;

	public void setShopsDao(ShopsDao shopsDao) {
		this.shopsDao = shopsDao;
	}

	public void setProductsDao(ProductsDao productsDao) {
		this.productsDao = productsDao;
	}

	public void setAccountsLogic(AccountsLogic accountsLogic) {
		this.accountsLogic = accountsLogic;
	}

	@Before
	public void setUp() {
		accountsList.clear();
		Shops shop = new Shops();
		shop.setShop_ID(tempShopID);
		this.shopsDao.saveShops(shop);
		Products product = new Products();
		product.setProd_ID(tempProductID);
		this.productsDao.saveProducts(product);
		for (int i = 0; i < 10; i++) {
			Accounts account = new Accounts();
			account.setAcc_loc("浙工大屛峰校区");
			account.setAcc_name("Poping" + RandomUtils.getRandomString(10));
			account.setAcc_pwd("123456");
			account.setAcc_sex("男");
			account.setAcc_tel(RandomUtils.getRandomNumberString(11));
			String successAcc_ID = this.accountsLogic.saveAccounts(account);
			Accounts DBaccount = this.accountsLogic.getAccountsInformationById(successAcc_ID);
			accountsList.add(DBaccount);
		}
	}

	@Test
	public void testAccountNameExist() {
		boolean flag = true;
		for (Accounts account : accountsList) {
			flag = flag && this.accountsLogic.accountNameExist(account.getAcc_name());
		}
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testSaveAccounts() {
		for (int i = 0; i < 5; i++) {
			Accounts account = new Accounts();
			account.setAcc_loc("浙工大屛峰校区");
			account.setAcc_name("John" + RandomUtils.getRandomString(5));
			account.setAcc_pwd("123456");
			account.setAcc_sex("男");
			account.setAcc_tel(RandomUtils.getRandomNumberString(11));
			String result = this.accountsLogic.saveAccounts(account);
			System.out.println(result);
			Assert.assertNotSame("", result);
		}
	}

	@Test
	public void testLogin() {
		for (Accounts account : accountsList) {
			String Acc_ID = this.accountsLogic.login(account.getAcc_name(), account.getAcc_pwd());
			Assert.assertNotSame("", Acc_ID);
		}
	}

	@Test
	public void testChangeAccountInfo() {
		for (Accounts account : accountsList) {
			account.setAcc_loc("嘉兴市秀洲区");
			boolean flag = this.accountsLogic.changeAccountInfo(account);
			Assert.assertEquals(true, flag);
		}
	}

	@Test
	public void testGetAccountsInformationById() {
		for (Accounts account : accountsList) {
			Accounts a = this.accountsLogic.getAccountsInformationById(account.getAcc_ID());
			System.out.println(a.toJSONString());
			Assert.assertNotNull(a);
		}
	}

	@Test
	public void testGetAccountsInformationByName() {
		for (Accounts account : accountsList) {
			Accounts a = this.accountsLogic.getAccountsInformationByName(account.getAcc_name());
			System.out.println(a.toJSONString());
			Assert.assertNotNull(a);
		}
	}

	@Test
	public void testAttention() {
		for (int i = 0; i < 10; i++) {
			boolean flag = this.accountsLogic.attention(accountsList.get(i).getAcc_ID(), accountsList.get(9 - i).getAcc_ID());
			Assert.assertEquals(true, flag);
		}
	}

	@Test
	public void testCancelAtention() {
		for (int i = 0; i < 10; i++) {
			boolean flag = this.accountsLogic.cancelAtention(accountsList.get(i).getAcc_ID(),
					accountsList.get(9 - i).getAcc_ID());
			Assert.assertEquals(true, flag);
		}
	}

	@Test
	public void testFavouriteCancelFavourite() {
		boolean flag = true;
		for (Accounts account : accountsList) {
			flag = flag && this.accountsLogic.favourite(account.getAcc_ID(), tempShopID);
			flag = flag && this.accountsLogic.cancelFavourite(account.getAcc_ID(), tempShopID);
		}
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testWantCancelWant() {
		boolean flag = true;
		for (Accounts account : accountsList) {
			flag = flag && this.accountsLogic.want(account.getAcc_ID(), tempProductID);
			flag = flag && this.accountsLogic.cancelWant(account.getAcc_ID(), tempProductID);
		}
		Assert.assertEquals(true, flag);
	}
}
