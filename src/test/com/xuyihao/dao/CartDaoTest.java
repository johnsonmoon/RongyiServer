package test.com.xuyihao.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import test.com.xuyihao.CommonTest;

import com.xuyihao.dao.AccountsDao;
import com.xuyihao.dao.CartDao;
import com.xuyihao.entity.Accounts;
import com.xuyihao.entity.Cart;
import com.xuyihao.tools.utils.DateUtils;
import com.xuyihao.tools.utils.RandomUtils;

import junit.framework.Assert;

/**
 * 
 * @Author Xuyh created at Aug 14, 2016 3:18:53 PM.
 *
 */
public class CartDaoTest extends CommonTest {
	private static List<String> Cart_IDList = new ArrayList<String>();
	private static String Acc_ID = null;

	@Autowired
	private CartDao cartDao;

	@Autowired
	private AccountsDao accountsDao;

	public void setAccountsDao(AccountsDao accountsDao) {
		this.accountsDao = accountsDao;
	}

	public void setCartDao(CartDao cartDao) {
		this.cartDao = cartDao;
	}

	@Before
	public void setUp() {
		Cart_IDList.clear();
		Accounts accounts = new Accounts();
		Acc_ID = RandomUtils.getRandomString(15) + "Acc";
		accounts.setAcc_ID(Acc_ID);
		accounts.setAcc_name("Johnson" + RandomUtils.getRandomNumberString(4));
		accounts.setAcc_pwd("moon" + RandomUtils.getRandomNumberString(4));
		accounts.setAcc_sex("男");
		accounts.setAcc_loc("浙江工业大学屛峰校区");
		accounts.setAcc_addTime(DateUtils.currentDateTime());
		this.accountsDao.saveAccounts(accounts);
		for (int i = 0; i <= 50; i++) {
			Cart cart = new Cart();
			String Cart_ID = RandomUtils.getRandomString(15) + "Cart";
			Cart_IDList.add(Cart_ID);
			cart.setAcc_ID(Acc_ID);
			cart.setCart_addTime(DateUtils.currentDateTime());
			cart.setCart_ID(Cart_ID);
			cart.setPro_num(i);
			cart.setProd_ID(RandomUtils.getRandomString(15) + "Prod");
			cart.setProd_price(i);
			this.cartDao.saveCart(cart);
		}
	}

	@Test
	public void testSaveCart() {
		boolean flag = true;
		for (int i = 0; i < 10; i++) {
			Cart cart = new Cart();
			cart.setCart_ID(RandomUtils.getRandomString(15) + "Cart");
			cart.setAcc_ID("1hi091hc17gfg06Acc");
			cart.setCart_addTime(DateUtils.currentDateTime());
			cart.setPro_num(10 + i);
			cart.setProd_ID(RandomUtils.getRandomString(15) + "Prod");
			cart.setProd_price(25 + i);
			flag = flag && this.cartDao.saveCart(cart);
		}
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testDeleteCart() {
		boolean flag = true;
		for (int i = 0; i < 5; i++) {
			flag = flag && this.cartDao.deleteCart(Cart_IDList.get(i));
		}
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testUpdateCartCart() {
		boolean flag = true;
		for (int i = 4; i < Cart_IDList.size(); i++) {
			Cart cart = this.cartDao.queryById(Cart_IDList.get(i));
			cart.setPro_num(20);
			cart.setProd_price(6.5f);
			flag = flag && this.cartDao.updateCart(cart);
		}
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testQueryByAccountId() {
		List<Cart> cartList = this.cartDao.queryByAccountId(Acc_ID);
		for (Cart cart : cartList) {
			System.out.println(cart.toJSONString());
		}
	}

	@Test
	public void testQueryById() {
		for (int i = 4; i < Cart_IDList.size(); i++) {
			Cart cart = this.cartDao.queryById(Cart_IDList.get(i));
			System.out.println(cart.toJSONString());
		}
	}

}
