package test.com.xuyihao.logic;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xuyihao.dao.AccountsDao;
import com.xuyihao.dao.OrdersDao;
import com.xuyihao.dao.ProductsDao;
import com.xuyihao.entity.Accounts;
import com.xuyihao.entity.CommentProduct;
import com.xuyihao.entity.Orders;
import com.xuyihao.entity.Products;
import com.xuyihao.logic.CommentProductLogic;
import com.xuyihao.tools.utils.DateUtils;
import com.xuyihao.tools.utils.RandomUtils;

import junit.framework.Assert;
import test.com.xuyihao.CommonTest;

public class CommentProductLogicTest extends CommonTest {
	private static List<String> Comm_IDList = new ArrayList<>();
	private static List<String> Acc_IDList = new ArrayList<>();
	private static List<String> Prod_IDList = new ArrayList<>();
	private static List<String> Ord_IDList = new ArrayList<>();

	@Autowired
	private CommentProductLogic commentProductLogic;

	@Autowired
	private AccountsDao accountsDao;

	@Autowired
	private ProductsDao productsDao;

	@Autowired
	private OrdersDao ordersDao;

	public void setCommentProductLogic(CommentProductLogic commentProductLogic) {
		this.commentProductLogic = commentProductLogic;
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
	public void setUp() throws Exception {
		Acc_IDList.clear();
		Prod_IDList.clear();
		Ord_IDList.clear();
		String Acc_ID = RandomUtils.getRandomString(15) + "Acc";
		String Prod_ID = RandomUtils.getRandomString(15) + "Prod";
		String Ord_ID = RandomUtils.getRandomString(15) + "Ord";
		Acc_IDList.add(Acc_ID);
		Prod_IDList.add(Prod_ID);
		Ord_IDList.add(Ord_ID);
		String currentTime = DateUtils.currentDateTime();
		for (int i = 0; i < 5; i++) {
			Accounts account = new Accounts();
			account.setAcc_ID(Acc_ID);
			account.setAcc_addTime(currentTime);
			this.accountsDao.saveAccounts(account);
			Products product = new Products();
			product.setProd_ID(Prod_ID);
			product.setProd_addTime(currentTime);
			this.productsDao.saveProducts(product);
			Orders order = new Orders();
			order.setOrd_ID(Ord_ID);
			order.setOrd_addTime(currentTime);
			this.ordersDao.saveOrders(order);
			CommentProduct commentProduct = new CommentProduct();
			commentProduct.setAcc_ID(Acc_ID);
			commentProduct.setComm_desc("哈哈哈");
			commentProduct.setOrd_ID(Ord_ID);
			commentProduct.setProd_ID(Prod_ID);
			String Comm_ID = this.commentProductLogic.saveCommentProduct(commentProduct);
			Comm_IDList.add(Comm_ID);
		}
	}

	@Test
	public void testSaveCommentProduct() {
		Acc_IDList.clear();
		Prod_IDList.clear();
		Ord_IDList.clear();
		String Acc_ID = RandomUtils.getRandomString(15) + "Acc";
		String Prod_ID = RandomUtils.getRandomString(15) + "Prod";
		String Ord_ID = RandomUtils.getRandomString(15) + "Ord";
		Acc_IDList.add(Acc_ID);
		Prod_IDList.add(Prod_ID);
		Ord_IDList.add(Ord_ID);
		String currentTime = DateUtils.currentDateTime();
		for (int i = 0; i < 10; i++) {
			Accounts account = new Accounts();
			account.setAcc_ID(Acc_ID);
			account.setAcc_addTime(currentTime);
			this.accountsDao.saveAccounts(account);
			Products product = new Products();
			product.setProd_ID(Prod_ID);
			product.setProd_addTime(currentTime);
			this.productsDao.saveProducts(product);
			Orders order = new Orders();
			order.setOrd_ID(Ord_ID);
			order.setOrd_addTime(currentTime);
			this.ordersDao.saveOrders(order);
			CommentProduct commentProduct = new CommentProduct();
			commentProduct.setAcc_ID(Acc_ID);
			commentProduct.setComm_desc("哈哈哈");
			commentProduct.setOrd_ID(Ord_ID);
			commentProduct.setProd_ID(Prod_ID);
			String Comm_ID = this.commentProductLogic.saveCommentProduct(commentProduct);
			Comm_IDList.add(Comm_ID);
		}
	}

	@Test
	public void testChangeCommentDescription() {
		for (String id : Comm_IDList) {
			boolean flag = this.commentProductLogic.changeCommentDescription(id, "lala");
			Assert.assertEquals(true, flag);
			System.out.println(this.commentProductLogic.getCommentProductInfo(id).toJSONString());
		}
	}

	@Test
	public void testGetCommentProductInfo() {
		for (String id : Comm_IDList) {
			CommentProduct comm = this.commentProductLogic.getCommentProductInfo(id);
			Assert.assertNotSame(0, comm.get_id());
			System.out.println(comm.toJSONString());
		}
	}
}
