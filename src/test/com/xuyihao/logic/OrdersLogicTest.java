package test.com.xuyihao.logic;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xuyihao.entity.Orders;
import com.xuyihao.logic.OrdersLogic;
import com.xuyihao.tools.utils.RandomUtils;

import junit.framework.Assert;
import test.com.xuyihao.CommonTest;

public class OrdersLogicTest extends CommonTest {
	private static List<Orders> orderList = new ArrayList<>();
	private static String Acc_ID = null;
	private static String Prod_ID = null;
	private static String Add_ID = null;

	@Autowired
	private OrdersLogic ordersLogic;

	public void setOrdersLogic(OrdersLogic ordersLogic) {
		this.ordersLogic = ordersLogic;
	}

	@Before
	public void setUp() throws Exception {
		orderList.clear();
		Acc_ID = RandomUtils.getRandomString(15) + "Acc";
		Prod_ID = RandomUtils.getRandomString(15) + "Prod";
		Add_ID = RandomUtils.getRandomString(15) + "Add";
		for (int i = 0; i < 10; i++) {
			Orders order = new Orders();
			order.setAcc_ID(Acc_ID);
			order.setAdd_ID(Add_ID);
			order.setPro_num(3);
			order.setProd_ID(Prod_ID);
			order.setProd_price(36.5f);
			String Ord_ID = this.ordersLogic.saveOrder(order);
			orderList.add(this.ordersLogic.getOrderInfo(Ord_ID));
		}
	}

	@Test
	public void testSaveOrder() {
		for (int i = 0; i < 10; i++) {
			Orders order = new Orders();
			order.setAcc_ID(Acc_ID);
			order.setAdd_ID(Add_ID);
			order.setPro_num(3);
			order.setProd_ID(Prod_ID);
			order.setProd_price(36.5f);
			String Ord_ID = this.ordersLogic.saveOrder(order);
			Assert.assertNotSame("", Ord_ID);
		}
	}

	@Test
	public void testGetOrderInfo() {
		for (Orders order : orderList) {
			Orders ord = this.ordersLogic.getOrderInfo(order.getOrd_ID());
			Assert.assertNotSame(0, ord.get_id());
			System.out.println(ord.toJSONString());
		}
	}

	@Test
	public void testDeleteOrder() {
		for (Orders order : orderList) {
			Assert.assertNotSame(0, this.ordersLogic.deleteOrder(order.getOrd_ID()));
		}
	}

	@Test
	public void testChangeOrderInfo() {
		for (Orders order : orderList) {
			order.setOrd_sent(true);
			order.setOrd_paid(true);
			order.setOrd_received(true);
			order.setOrd_comment(true);
			boolean flag = this.ordersLogic.changeOrderInfo(order);
			Assert.assertEquals(true, flag);
			System.out.println(this.ordersLogic.getOrderInfo(order.getOrd_ID()).toJSONString());
		}
	}
}
