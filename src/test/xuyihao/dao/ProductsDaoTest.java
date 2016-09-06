package test.xuyihao.dao;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import test.xuyihao.CommonTest;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import xuyihao.dao.AccountsDao;
import xuyihao.dao.CategoryDao;
import xuyihao.dao.ProductsDao;
import xuyihao.dao.ShopsDao;
import xuyihao.entity.Accounts;
import xuyihao.entity.Category;
import xuyihao.entity.Products;
import xuyihao.entity.Shops;
import xuyihao.tools.utils.DateUtils;
import xuyihao.tools.utils.RandomUtils;

/**
 * 
 * @Author Xuyh created at Aug 14, 2016 3:37:46 PM.
 *
 */
public class ProductsDaoTest extends CommonTest {
	private static List<String> Prod_IDList = new ArrayList<>();
	private static List<String> Prod_nameList = new ArrayList<>();
	private static String Cat_ID = null;
	private static String Shop_ID = null;
	private static String Acc_ID = null;

	@Autowired
	private ProductsDao productsDao;

	@Autowired
	private AccountsDao accountsDao;

	@Autowired
	private ShopsDao shopsDao;

	@Autowired
	private CategoryDao categoryDao;

	public void setProductsDao(ProductsDao productsDao) {
		this.productsDao = productsDao;
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
		Prod_nameList.clear();
		Cat_ID = RandomUtils.getRandomNumberString(15) + "Cat";
		Shop_ID = RandomUtils.getRandomNumberString(15) + "Shop";
		Acc_ID = RandomUtils.getRandomNumberString(15) + "Acc";
		Accounts accounts = new Accounts();
		accounts.setAcc_ID(Acc_ID);
		accounts.setAcc_name("Johnson" + RandomUtils.getRandomString(5));
		accounts.setAcc_pwd("moon" + RandomUtils.getRandomString(5));
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
			String Prod_name = RandomUtils.getRandomNumberString(30);
			Prod_IDList.add(Prod_ID);
			Prod_nameList.add(Prod_name);
			Products product = new Products();
			product.setCat_ID(Cat_ID);
			product.setProd_addTime(DateUtils.currentDateTime());
			product.setProd_desc(RandomUtils.getRandomString(60));
			product.setProd_ID(Prod_ID);
			product.setProd_info(RandomUtils.getRandomString(80));
			product.setProd_name(Prod_name);
			product.setProd_num(i);
			product.setProd_price(50 - i);
			product.setProd_sold(i + 5);
			product.setShop_ID(Shop_ID);
			this.productsDao.saveProducts(product);
		}
	}

	@Test
	public void testSaveProducts() {
		boolean flag = true;
		for (int i = 0; i < 10; i++) {
			Products product = new Products();
			product.setCat_ID(Cat_ID);
			product.setProd_addTime(DateUtils.currentDateTime());
			product.setProd_desc(RandomUtils.getRandomString(60));
			product.setProd_ID(RandomUtils.getRandomNumberString(15) + "Prod");
			product.setProd_info(RandomUtils.getRandomString(80));
			product.setProd_name(RandomUtils.getRandomNumberString(30));
			product.setProd_num(i);
			product.setProd_price(50 - i);
			product.setProd_sold(i + 5);
			product.setShop_ID(Shop_ID);
			flag = flag && this.productsDao.saveProducts(product);
		}
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testDeleteProducts() {
		boolean flag = true;
		for (int i = 45; i < Prod_IDList.size(); i++) {
			flag = flag && this.productsDao.deleteProducts(Prod_IDList.get(i));
		}
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testUpdateProductsProducts() {
		boolean flag = true;
		for (int i = 0; i < 20; i++) {
			Products product = this.productsDao.queryById(Prod_IDList.get(i));
			product.setProd_desc("哈哈哈");
			product.setProd_info("详情请进店查询!");
			flag = flag && this.productsDao.updateProducts(product);
		}
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testQueryById() {
		for (String Prod_ID : Prod_IDList) {
			Products product = this.productsDao.queryById(Prod_ID);
			Assert.assertNotNull(product);
			System.out.println(product.toJSONString());
		}
	}

	@Test
	public void testQueryByName() {
		for (String Prod_name : Prod_nameList) {
			List<Products> productList = this.productsDao.queryByName(Prod_name);
			Assert.assertNotNull(productList);
			for (Products product : productList) {
				System.out.println(product.toJSONString());
			}
		}
	}

	@Test
	public void testQueryByCategoryId() {
		List<Products> productList = this.productsDao.queryByCategoryId(Cat_ID);
		Assert.assertNotNull(productList);
		for (Products product : productList) {
			System.out.println(product.toJSONString());
		}
	}

	@Test
	public void testQueryByShopId() {
		List<Products> productList = this.productsDao.queryByShopId(Shop_ID);
		Assert.assertNotNull(productList);
		for (Products product : productList) {
			System.out.println(product.toJSONString());
		}
	}
}
