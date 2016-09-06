package test.xuyihao.logic;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import test.xuyihao.CommonTest;
import xuyihao.entity.Cart;
import xuyihao.logic.CartLogic;
import xuyihao.tools.utils.RandomUtils;

public class CartLogicTest extends CommonTest {
	private static List<Cart> cartList = new ArrayList<>();
	private static String tempAccId = RandomUtils.getRandomString(15) + "Acc";
	private static String tempProdId = RandomUtils.getRandomString(15) + "Prod";

	@Autowired
	private CartLogic cartLogic;

	public void setCartLogic(CartLogic cartLogic) {
		this.cartLogic = cartLogic;
	}

	@Before
	public void setUp() throws Exception {
		cartList.clear();
		for (int i = 0; i < 10; i++) {
			Cart cart = new Cart();
			cart.setAcc_ID(tempAccId);
			cart.setPro_num(5);
			cart.setProd_ID(tempProdId);
			cart.setProd_price(6.5f);
			String s = this.cartLogic.saveCart(cart);
			cartList.add(this.cartLogic.getCartInfo(s));
		}
	}

	@Test
	public void testSaveCart() {
		for (int i = 0; i < 10; i++) {
			Cart cart = new Cart();
			cart.setAcc_ID(tempAccId);
			cart.setPro_num(5);
			cart.setProd_ID(tempProdId);
			cart.setProd_price(6.5f);
			String s = this.cartLogic.saveCart(cart);
			Assert.assertNotSame("", s);
		}
	}

	@Test
	public void testGetCartInfo() {
		for (Cart cart : cartList) {
			Cart DBcart = this.cartLogic.getCartInfo(cart.getCart_ID());
			Assert.assertNotSame(0, DBcart.get_id());
			System.out.println(DBcart.toJSONString());
		}
	}

	@Test
	public void testChangeProductNumber() {
		boolean flag = true;
		for (Cart cart : cartList) {
			System.out.println("Before: " + cart.toJSONString());
			flag = flag && this.cartLogic.changeProductNumber(cart.getCart_ID(), 66);
			System.out.println("After: " + this.cartLogic.getCartInfo(cart.getCart_ID()).toJSONString());
		}
	}

	@Test
	public void testDeleteCart() {
		for (Cart cart : cartList) {
			Assert.assertEquals(true, this.cartLogic.deleteCart(cart.getCart_ID()));
		}
	}
}
