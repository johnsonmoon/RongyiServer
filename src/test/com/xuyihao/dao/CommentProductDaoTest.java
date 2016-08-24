package test.com.xuyihao.dao;

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
import com.xuyihao.dao.CommentProductDao;
import com.xuyihao.dao.OrdersDao;
import com.xuyihao.dao.ProductsDao;
import com.xuyihao.dao.ShopsDao;
import com.xuyihao.entity.Accounts;
import com.xuyihao.entity.Address;
import com.xuyihao.entity.Category;
import com.xuyihao.entity.CommentProduct;
import com.xuyihao.entity.Orders;
import com.xuyihao.entity.Products;
import com.xuyihao.entity.Shops;
import com.xuyihao.tools.utils.DateUtils;
import com.xuyihao.tools.utils.RandomUtils;

/**
 * 
 * @Author Xuyh created at Aug 14, 2016 3:23:53 PM.
 *
 */
public class CommentProductDaoTest extends CommonTest {
	private static List<String> Comm_IDList = new ArrayList<>();
	private static String Acc_ID = null;
	private static String Prod_ID = null;
	private static String Ord_ID = null;
	private static String Cat_ID = null;
	private static String Shop_ID = null;
	private static String Add_ID = null;

	@Autowired
	private AddressDao addressDao;

	@Autowired
	private ShopsDao shopsDao;

	@Autowired
	private CommentProductDao commentProductDao;

	@Autowired
	private AccountsDao accountsDao;

	@Autowired
	private ProductsDao productsDao;

	@Autowired
	private OrdersDao ordersDao;

	@Autowired
	private CategoryDao categoryDao;

	public void setAddressDao(AddressDao addressDao) {
		this.addressDao = addressDao;
	}

	public void setShopsDao(ShopsDao shopsDao) {
		this.shopsDao = shopsDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public void setCommentProductDao(CommentProductDao commentProductDao) {
		this.commentProductDao = commentProductDao;
	}

	public void setAccountsDao(AccountsDao accountsDao) {
		this.accountsDao = accountsDao;
	}

	public void setProductsDao(ProductsDao productsDao) {
		this.productsDao = productsDao;
	}

	public void setOrdersDao(OrdersDao ordersDao) {
		this.ordersDao = ordersDao;
	}

	@Before
	public void setUp() {
		Comm_IDList.clear();
		Acc_ID = RandomUtils.getRandomString(15) + "Acc";
		Prod_ID = RandomUtils.getRandomString(15) + "Prod";
		Ord_ID = RandomUtils.getRandomString(15) + "Ord";
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
		Address address = new Address();
		address.setAdd_ID(Add_ID);
		address.setAdd_info("你好吗?");
		address.setAcc_ID(Acc_ID);
		address.setConsign("兰兰" + RandomUtils.getRandomString(5));
		address.setCon_tel("15700083" + RandomUtils.getRandomNumberString(3));
		address.setAdd_addTime(DateUtils.currentDateTime());
		this.addressDao.saveAddress(address);
		Orders order = new Orders();
		order.setAcc_ID(Acc_ID);
		order.setAdd_ID(Add_ID);
		order.setOrd_addTime(DateUtils.currentDateTime());
		order.setOrd_date(DateUtils.currentDate());
		order.setOrd_ID(Ord_ID);
		order.setPro_num(50);
		order.setProd_ID(Prod_ID);
		order.setProd_price(12.5f);
		this.ordersDao.saveOrders(order);
		for (int i = 0; i < 50; i++) {
			String Comm_ID = RandomUtils.getRandomString(15) + "Comm";
			Comm_IDList.add(Comm_ID);
			CommentProduct commentProduct = new CommentProduct();
			commentProduct.setAcc_ID(Acc_ID);
			commentProduct.setComm_addTime(DateUtils.currentDateTime());
			commentProduct.setComm_desc("adiohafouihaoufhnoa");
			commentProduct.setComm_ID(Comm_ID);
			commentProduct.setOrd_ID(Ord_ID);
			commentProduct.setProd_ID(Prod_ID);
			this.commentProductDao.saveCommentProduct(commentProduct);
		}
	}

	@Test
	public void testSaveCommentProduct() {
		boolean flag = true;
		for (int i = 0; i < 50; i++) {
			CommentProduct commentProduct = new CommentProduct();
			commentProduct.setAcc_ID(Acc_ID);
			commentProduct.setComm_addTime(DateUtils.currentDateTime());
			commentProduct.setComm_desc("adiohafouihaoufhnoa");
			commentProduct.setComm_ID(RandomUtils.getRandomString(15) + "Comm");
			commentProduct.setOrd_ID(Ord_ID);
			commentProduct.setProd_ID(Prod_ID);
			flag = flag && this.commentProductDao.saveCommentProduct(commentProduct);
		}
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testDeleteCommentProduct() {
		boolean flag = true;
		for (int i = 45; i < Comm_IDList.size(); i++) {
			flag = flag && this.commentProductDao.deleteCommentProduct(Comm_IDList.get(i));
		}
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testUpdateCommentProductCommentProduct() {
		boolean flag = true;
		for (int i = 0; i < 20; i++) {
			CommentProduct commentProduct = this.commentProductDao.queryById(Comm_IDList.get(i));
			commentProduct.setComm_desc("lala");
			flag = flag && this.commentProductDao.updateCommentProduct(commentProduct);
		}
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testQueryById() {
		for (String Comm_ID : Comm_IDList) {
			CommentProduct commentProduct = this.commentProductDao.queryById(Comm_ID);
			Assert.assertNotNull(commentProduct);
			System.out.println(commentProduct.toJSONString());
		}
	}

	@Test
	public void testQueryByAccountId() {
		List<CommentProduct> commentProductsList = this.commentProductDao.queryByAccountId(Acc_ID);
		Assert.assertNotNull(commentProductsList);
		for (CommentProduct commentProduct : commentProductsList) {
			System.out.println(commentProduct.toJSONString());
		}
	}

	@Test
	public void testQueryByProductId() {
		List<CommentProduct> commentProductsList = this.commentProductDao.queryByProductId(Prod_ID);
		Assert.assertNotNull(commentProductsList);
		for (CommentProduct commentProduct : commentProductsList) {
			System.out.println(commentProduct.toJSONString());
		}
	}
}
