package test.com.xuyihao.logic;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xuyihao.entity.Products;
import com.xuyihao.logic.ProductsLogic;
import com.xuyihao.tools.utils.RandomUtils;

import test.com.xuyihao.CommonTest;

public class ProductsLogicTest extends CommonTest {
	private static List<Products> productList = new ArrayList<>();
	private static String tempCatId = RandomUtils.getRandomString(15) + "Cat";
	private static String tempShopId = RandomUtils.getRandomString(15) + "Shop";

	@Autowired
	private ProductsLogic productsLogic;

	public void setProductsLogic(ProductsLogic productsLogic) {
		this.productsLogic = productsLogic;
	}

	@Before
	public void setUp() throws Exception {
		for (int i = 0; i < 10; i++) {
			Products product = new Products();
			product.setCat_ID(tempCatId);
			product.setProd_desc(RandomUtils.getRandomString(66));
			product.setProd_info("啦啦啦");
			product.setProd_name(RandomUtils.getRandomString(12));
			product.setProd_num(23);
			product.setProd_price(12.5f);
			product.setShop_ID(tempShopId);
			String Prod_ID = this.productsLogic.saveProduct(product);
			productList.add(this.productsLogic.getProductInfo(Prod_ID));
		}
	}

	@Test
	public void testSaveProduct() {
		for (int i = 0; i < 10; i++) {
			Products product = new Products();
			product.setCat_ID(tempCatId);
			product.setProd_desc(RandomUtils.getRandomString(66));
			product.setProd_info("啦啦啦");
			product.setProd_name(RandomUtils.getRandomString(12));
			product.setProd_num(23);
			product.setProd_price(12.5f);
			product.setShop_ID(tempShopId);
			String Prod_ID = this.productsLogic.saveProduct(product);
			Assert.assertNotSame("", Prod_ID);
		}
	}

	@Test
	public void testGetProductInfo() {
		for (Products product : productList) {
			Products prod = this.productsLogic.getProductInfo(product.getProd_ID());
			Assert.assertNotSame("", prod.getProd_ID());
			System.out.println(prod.toJSONString());
		}
	}

	@Test
	public void testChangeProductSold() {
		for (Products product : productList) {
			boolean flag = this.productsLogic.changeProductSold(product.getProd_ID(), 41);
			Assert.assertEquals(true, flag);
			System.out.println(this.productsLogic.getProductInfo(product.getProd_ID()).getProd_sold());
		}
	}

	@Test
	public void testDecreaseProductNumber() {
		for (Products product : productList) {
			boolean flag = this.productsLogic.decreaseProductNumber(product.getProd_ID());
			Assert.assertEquals(true, flag);
			System.out.println(this.productsLogic.getProductInfo(product.getProd_ID()).getProd_num());
		}
	}

	@Test
	public void testWhetherProductRemain() {
		for (Products product : productList) {
			boolean flag = this.productsLogic.whetherProductRemain(product.getProd_ID());
			System.out.println(flag);
		}
	}

	@Test
	public void testDeleteProduct() {
		for (Products product : productList) {
			boolean flag = this.productsLogic.deleteProduct(product.getProd_ID());
			Assert.assertEquals(true, flag);
		}
	}
}
