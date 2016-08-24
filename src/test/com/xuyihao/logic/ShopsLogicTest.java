package test.com.xuyihao.logic;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xuyihao.entity.Shops;
import com.xuyihao.logic.ShopsLogic;
import com.xuyihao.tools.utils.RandomUtils;

import junit.framework.Assert;
import test.com.xuyihao.CommonTest;

public class ShopsLogicTest extends CommonTest {
	private static List<Shops> shopsList = new ArrayList<>();
	private static String tempAccId = null;

	@Autowired
	private ShopsLogic shopsLogic;

	public void setShopsLogic(ShopsLogic shopsLogic) {
		this.shopsLogic = shopsLogic;
	}

	@Before
	public void setUp() throws Exception {
		shopsList.clear();
		tempAccId = RandomUtils.getRandomString(15) + "Acc";
		for (int i = 0; i < 10; i++) {
			Shops shop = new Shops();
			shop.setAcc_ID(tempAccId);
			shop.setShop_info("啊低价" + RandomUtils.getRandomString(10));
			shop.setShop_licen(RandomUtils.getRandomNumberString(22));
			shop.setShop_name(RandomUtils.getRandomString(13));
			String Shop_ID = this.shopsLogic.saveShop(shop);
			shopsList.add(this.shopsLogic.getShopInfo(Shop_ID));
		}
	}

	@Test
	public void testSaveShop() {
		for (int i = 0; i < 10; i++) {
			Shops shop = new Shops();
			shop.setAcc_ID(tempAccId);
			shop.setShop_info("啊低价" + RandomUtils.getRandomString(10));
			shop.setShop_licen(RandomUtils.getRandomNumberString(22));
			shop.setShop_name(RandomUtils.getRandomString(13));
			String Shop_ID = this.shopsLogic.saveShop(shop);
			Assert.assertNotSame("", Shop_ID);
		}
	}

	@Test
	public void testShopNameExist() {
		for (Shops shop : shopsList) {
			System.out.println(this.shopsLogic.shopNameExist(shop.getShop_name()));
		}
	}

	@Test
	public void testGetShopID() {
		for (Shops shop : shopsList) {
			String ID = this.shopsLogic.getShopID(shop.getShop_name());
			System.out.println(ID);
			Assert.assertNotSame("", ID);
		}
	}

	@Test
	public void testGetShopInfo() {
		for (Shops shop : shopsList) {
			Shops sh = this.shopsLogic.getShopInfo(shop.getShop_ID());
			System.out.println(sh.toJSONString());
			Assert.assertNotSame(0, sh.get_id());
		}
	}

	@Test
	public void testChangeShopInfo() {
		for (Shops shop : shopsList) {
			shop.setShop_licen("哈哈哈");
			boolean flag = this.shopsLogic.changeShopInfo(shop);
			Assert.assertEquals(true, flag);
			System.out.println(this.shopsLogic.getShopInfo(shop.getShop_ID()).toJSONString());
		}
	}

	@Test
	public void testDeleteShop() {
		for (Shops shop : shopsList) {
			boolean flag = this.shopsLogic.deleteShop(shop.getShop_ID());
			Assert.assertEquals(true, flag);
		}
	}
}
