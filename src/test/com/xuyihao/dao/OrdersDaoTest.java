package test.com.xuyihao.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import test.com.xuyihao.CommonTest;

import com.xuyihao.dao.AccountsDao;
import com.xuyihao.dao.AddressDao;
import com.xuyihao.dao.CategoryDao;
import com.xuyihao.dao.OrdersDao;
import com.xuyihao.dao.ProductsDao;
import com.xuyihao.dao.ShopsDao;
import com.xuyihao.entity.Accounts;
import com.xuyihao.entity.Address;
import com.xuyihao.entity.Category;
import com.xuyihao.entity.Orders;
import com.xuyihao.entity.Products;
import com.xuyihao.entity.Shops;
import com.xuyihao.tools.utils.DateUtils;
import com.xuyihao.tools.utils.RandomUtils;

/**
 * 
 * @Author Xuyh created at Aug 14, 2016 3:35:15 PM.
 *
 */
public class OrdersDaoTest extends CommonTest {
	private static List<String> Ord_IDList = new ArrayList<>();
	private static List<String> Ord_dateList = new ArrayList<>();
	private static String Acc_ID = null;
	private static String Add_ID = null;
	private static String Cat_ID = null;
	private static String Shop_ID = null;
	private static String Prod_ID = null;

	@Autowired
	private AccountsDao accountsDao;

	@Autowired
	private AddressDao addressDao;

	@Autowired
	private CategoryDao categoryDao;

	@Autowired
	private ShopsDao shopsDao;

	@Autowired
	private ProductsDao productsDao;

	@Autowired
	private OrdersDao ordersDao;

	public void setAccountsDao(AccountsDao accountsDao) {
		this.accountsDao = accountsDao;
	}

	public void setAddressDao(AddressDao addressDao) {
		this.addressDao = addressDao;
	}

	public void setProductsDao(ProductsDao productsDao) {
		this.productsDao = productsDao;
	}

	public void setOrdersDao(OrdersDao ordersDao) {
		this.ordersDao = ordersDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public void setShopsDao(ShopsDao shopsDao) {
		this.shopsDao = shopsDao;
	}

	@Before
	public void setUp() {
		Ord_IDList.clear();
		Ord_dateList.clear();
		Acc_ID = RandomUtils.getRandomString(15) + "Acc";
		Prod_ID = RandomUtils.getRandomString(15) + "Prod";
		Cat_ID = RandomUtils.getRandomString(15) + "Cat";
		Shop_ID = RandomUtils.getRandomString(15) + "Shop";
		Add_ID = RandomUtils.getRandomString(15) + "Add";
		Accounts accounts = new Accounts();
		accounts.setAcc_ID(Acc_ID);
		accounts.setAcc_name("Johnson" + RandomUtils.getRandomNumberString(5));
		accounts.setAcc_pwd("moon" + RandomUtils.getRandomNumberString(5));
		accounts.setAcc_sex("男");
		accounts.setAcc_loc("浙江工业大学屛峰校区");
		accounts.setAcc_addTime(DateUtils.currentDateTime());
		this.accountsDao.saveAccounts(accounts);
		Address address = new Address();
		address.setAdd_ID(Add_ID);
		address.setAdd_info("你好吗?");
		address.setAcc_ID(Acc_ID);
		address.setConsign("兰兰" + RandomUtils.getRandomString(5));
		address.setCon_tel("15700083" + RandomUtils.getRandomNumberString(3));
		address.setAdd_addTime(DateUtils.currentDateTime());
		this.addressDao.saveAddress(address);
		Category category = new Category();
		category.setCat_ID(Cat_ID);
		category.setCat_name(RandomUtils.getRandomString(3));
		category.setCat_desc("产品描述测试");
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
		Products product = new Products();
		product.setCat_ID(Cat_ID);
		product.setProd_addTime(DateUtils.currentDateTime());
		product.setProd_desc(RandomUtils.getRandomString(60));
		product.setProd_ID(Prod_ID);
		product.setProd_info(RandomUtils.getRandomString(80));
		product.setProd_name(RandomUtils.getRandomNumberString(30));
		product.setProd_num(6);
		product.setProd_price(50);
		product.setProd_sold(5);
		product.setShop_ID(Shop_ID);
		this.productsDao.saveProducts(product);
		for (int i = 0; i < 50; i++) {
			String Ord_ID = RandomUtils.getRandomNumberString(15) + "Ord";
			String Ord_date = DateUtils.currentDate();
			Ord_IDList.add(Ord_ID);
			Ord_dateList.add(Ord_date);
			Orders order = new Orders();
			order.setAcc_ID(Acc_ID);
			order.setAdd_ID(Add_ID);
			order.setOrd_addTime(DateUtils.currentDateTime());
			order.setOrd_date(Ord_date);
			order.setOrd_ID(Ord_ID);
			order.setPro_num(50);
			order.setProd_ID(Prod_ID);
			order.setProd_price(12.5f);
			this.ordersDao.saveOrders(order);
		}
	}

	@Test
	public void testSaveOrders() {
		boolean flag = true;
		for (int i = 0; i < 10; i++) {
			String Ord_ID = RandomUtils.getRandomNumberString(15) + "Ord";
			String Ord_date = DateUtils.currentDate();
			Orders order = new Orders();
			order.setAcc_ID(Acc_ID);
			order.setAdd_ID(Add_ID);
			order.setOrd_addTime(DateUtils.currentDateTime());
			order.setOrd_date(Ord_date);
			order.setOrd_ID(Ord_ID);
			order.setPro_num(50);
			order.setProd_ID(Prod_ID);
			order.setProd_price(12.5f);
			flag = flag && this.ordersDao.saveOrders(order);
		}
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testDeleteOrders() {
		boolean flag = true;
		for (int i = 45; i < Ord_IDList.size(); i++) {
			flag = flag && this.ordersDao.deleteOrders(Ord_IDList.get(i));
		}
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testUpdateOrdersOrders() {
		boolean flag = true;
		for (int i = 0; i < 20; i++) {
			Orders order = this.ordersDao.queryById(Ord_IDList.get(i));
			order.setOrd_paid(true);
			order.setOrd_received(true);
			order.setOrd_sent(true);
			order.setOrd_comment(true);
			flag = flag && this.ordersDao.updateOrders(order);
		}
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testQueryById() {
		for (String Ord_ID : Ord_IDList) {
			Orders order = this.ordersDao.queryById(Ord_ID);
			assertNotNull(order);
			System.out.println(order.toJSONString());
		}
	}

	@Test
	public void testQueryByAccountId() {
		List<Orders> ordersList = this.ordersDao.queryByAccountId(Acc_ID);
		assertNotNull(ordersList);
		for (Orders order : ordersList) {
			System.out.println(order.toJSONString());
		}
	}

	@Test
	public void testQueryByOrderDatetime() {
		for (String date : Ord_dateList) {
			List<Orders> ordersList = this.ordersDao.queryByOrderDate(date);
			Assert.assertNotNull(ordersList);
			for (Orders order : ordersList) {
				System.out.println(order.toJSONString());
			}
		}
	}
}
