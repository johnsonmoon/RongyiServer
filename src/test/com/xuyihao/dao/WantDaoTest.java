package test.com.xuyihao.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import test.com.xuyihao.CommonTest;

import com.xuyihao.dao.AccountsDao;
import com.xuyihao.dao.CategoryDao;
import com.xuyihao.dao.ProductsDao;
import com.xuyihao.dao.ShopsDao;
import com.xuyihao.dao.WantDao;
import com.xuyihao.entity.Accounts;
import com.xuyihao.entity.Category;
import com.xuyihao.entity.Products;
import com.xuyihao.entity.Shops;
import com.xuyihao.tools.utils.DateUtils;
import com.xuyihao.tools.utils.RandomUtils;

import junit.framework.Assert;

/**
 * 
 * @Author Xuyh created at Aug 14, 2016 3:41:07 PM.
 *
 */
public class WantDaoTest extends CommonTest {
	private static List<String> Prod_IDList = new ArrayList<>();
	private static String Acc_ID = null;
	private static String Cat_ID = null;
	private static String Shop_ID = null;

	@Autowired
	private ProductsDao productsDao;

	@Autowired
	private AccountsDao accountsDao;

	@Autowired
	private ShopsDao shopsDao;

	@Autowired
	private CategoryDao categoryDao;

	@Autowired
	private WantDao wantDao;

	public void setWantDao(WantDao wantDao) {
		this.wantDao = wantDao;
	}

	public void setProductsDao(ProductsDao productsDao) {
		this.productsDao = productsDao;
	}

	public void setAccountsDao(AccountsDao accountsDao) {
		this.accountsDao = accountsDao;
	}

	public void setShopsDao(ShopsDao shopsDao) {
		this.shopsDao = shopsDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Before
	public void setUp() {
		Prod_IDList.clear();
		Acc_ID = RandomUtils.getRandomString(15) + "Acc";
		Cat_ID = RandomUtils.getRandomString(15) + "Cat";
		Shop_ID = RandomUtils.getRandomString(15) + "Shop";
		Accounts accounts = new Accounts();
		accounts.setAcc_ID(Acc_ID);
		accounts.setAcc_name("Johnson" + RandomUtils.getRandomNumberString(5));
		accounts.setAcc_pwd("moon" + RandomUtils.getRandomNumberString(5));
		accounts.setAcc_sex("男");
		accounts.setAcc_loc("浙江工业大学屛峰校区");
		accounts.setAcc_addTime(DateUtils.currentDateTime());
		this.accountsDao.saveAccounts(accounts);
		Category category = new Category();
		category.setCat_ID(Cat_ID);
		category.setCat_name(RandomUtils.getRandomString(5));
		category.setCat_desc("产品描述测试" + RandomUtils.getRandomString(5));
		category.setCat_addTime(DateUtils.currentDateTime());
		this.categoryDao.saveCategory(category);
		Shops shop = new Shops();
		shop.setAcc_ID(Acc_ID);
		shop.setShop_addTime(DateUtils.currentDateTime());
		shop.setShop_ID(Shop_ID);
		shop.setShop_info(RandomUtils.getRandomString(50));
		shop.setShop_licen(RandomUtils.getRandomString(50));
		shop.setShop_name(RandomUtils.getRandomString(20));
		this.shopsDao.saveShops(shop);
		for (int i = 0; i < 50; i++) {
			String Prod_ID = RandomUtils.getRandomNumberString(15) + "Prod";
			Prod_IDList.add(Prod_ID);
			Products product = new Products();
			product.setCat_ID(Cat_ID);
			product.setProd_addTime(DateUtils.currentDateTime());
			product.setProd_desc(RandomUtils.getRandomString(60));
			product.setProd_ID(Prod_ID);
			product.setProd_info(RandomUtils.getRandomString(80));
			product.setProd_name(RandomUtils.getRandomString(12));
			product.setProd_num(i);
			product.setProd_price(50 - i);
			product.setProd_sold(i + 5);
			product.setShop_ID(Shop_ID);
			this.productsDao.saveProducts(product);
			// --
			this.wantDao.saveWant(Acc_ID, Prod_ID, DateUtils.currentDateTime());
		}
	}

	@Test
	public void testSaveWant() {
		boolean flag = true;
		for (int i = 0; i < 10; i++) {
			String Prod_ID = RandomUtils.getRandomNumberString(15) + "Prod";
			Products product = new Products();
			product.setCat_ID(Cat_ID);
			product.setProd_addTime(DateUtils.currentDateTime());
			product.setProd_desc(RandomUtils.getRandomString(60));
			product.setProd_ID(Prod_ID);
			product.setProd_info(RandomUtils.getRandomString(80));
			product.setProd_name(RandomUtils.getRandomString(12));
			product.setProd_num(i);
			product.setProd_price(50 - i);
			product.setProd_sold(i + 5);
			product.setShop_ID(Shop_ID);
			this.productsDao.saveProducts(product);
			// --
			flag = flag && this.wantDao.saveWant(Acc_ID, Prod_ID, DateUtils.currentDateTime());
		}
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testDeleteWant() {
		boolean flag = true;
		for (int i = 0; i < 30; i++) {
			flag = flag && this.wantDao.deleteWant(Acc_ID, Prod_IDList.get(i));
		}
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testDeleteWantAll() {
		boolean flag = this.wantDao.deleteWantAll(Acc_ID);
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testQueryByAccountId() {
		List<String> ProdIDList = this.wantDao.queryByAccountId(Acc_ID);
		Assert.assertNotNull(ProdIDList);
		for (String s : ProdIDList) {
			System.out.println(s);
		}
	}

	@Test
	public void testQueryByProductId() {
		for (String Prod_ID : Prod_IDList) {
			List<String> ProdIDList = this.wantDao.queryByProductId(Prod_ID);
			Assert.assertNotNull(ProdIDList);
			for (String s : ProdIDList) {
				System.out.println(s);
			}
		}
	}
}
