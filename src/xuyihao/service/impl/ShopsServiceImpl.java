package xuyihao.service.impl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.sf.json.JSONObject;
import xuyihao.entity.Category;
import xuyihao.entity.Products;
import xuyihao.entity.Shops;
import xuyihao.logic.CategoryLogic;
import xuyihao.logic.ProductsLogic;
import xuyihao.logic.ShopsLogic;

/**
 * 
 * @Author Xuyh created at 2016年8月26日 下午1:18:57
 */
@Component("ShopsService")
public class ShopsServiceImpl implements xuyihao.service.ShopsService {

	@Autowired
	private ShopsLogic shopsLogic;

	@Autowired
	private CategoryLogic categoryLogic;

	@Autowired
	private ProductsLogic productsLogic;

	private HttpSession session = null;

	public void setShopsLogic(ShopsLogic shopsLogic) {
		this.shopsLogic = shopsLogic;
	}

	public void setCategoryLogic(CategoryLogic categoryLogic) {
		this.categoryLogic = categoryLogic;
	}

	public void setProductsLogic(ProductsLogic productsLogic) {
		this.productsLogic = productsLogic;
	}

	public void setSessionInfo(HttpSession session) {
		this.session = session;
	}

	public String addShop(Shops shop) {
		JSONObject json = new JSONObject();
		String Acc_ID = this.session.getAttribute("Acc_ID").toString();
		if (Acc_ID.equals(shop.getAcc_ID())) {
			String Shop_ID = this.shopsLogic.saveShop(shop);
			if (Shop_ID == null || Shop_ID.equals("")) {
				json.put("result", false);
				json.put("Shop_ID", "");
			} else {
				json.put("result", true);
				json.put("Shop_ID", Shop_ID);
			}
		} else {
			json.put("result", false);
			json.put("Shop_ID", "");
		}
		return json.toString();
	}

	public String isShopNameExists(String shopName) {
		JSONObject json = new JSONObject();
		boolean flag = this.shopsLogic.shopNameExist(shopName);
		if (flag) {
			json.put("result", true);
		} else {
			json.put("result", false);
		}
		return json.toString();
	}

	public String getShopIdByName(String shopName) {
		JSONObject json = new JSONObject();
		String id = this.shopsLogic.getShopID(shopName);
		if (id == null || id.equals("")) {
			json.put("result", false);
			json.put("Shop_ID", "");
		} else {
			json.put("result", true);
			json.put("Shop_ID", id);
		}
		return json.toString();
	}

	public String getShopInformationById(String shopId) {
		Shops shop = this.shopsLogic.getShopInfo(shopId);
		return shop.toJSONString();
	}

	public String changeShopInformation(Shops shop) {
		JSONObject json = new JSONObject();
		String Acc_ID = this.session.getAttribute("Acc_ID").toString();
		Shops queryShop = this.shopsLogic.getShopInfo(shop.getShop_ID());
		if (Acc_ID.equals(queryShop.getAcc_ID())) {
			boolean flag = this.shopsLogic.changeShopInfo(shop);
			if (flag) {
				json.put("result", true);
			} else {
				json.put("result", false);
			}
		} else {
			json.put("result", false);
		}
		return json.toString();
	}

	public String deleteShop(String shopId) {
		JSONObject json = new JSONObject();
		String Acc_ID = this.session.getAttribute("Acc_ID").toString();
		Shops queryShop = this.shopsLogic.getShopInfo(shopId);
		if (Acc_ID.equals(queryShop.getAcc_ID())) {
			boolean flag = this.shopsLogic.deleteShop(shopId);
			if (flag) {
				json.put("result", true);
			} else {
				json.put("result", false);
			}
		} else {
			json.put("result", false);
		}
		return json.toString();
	}

	public String addCategory(Category category) {
		// XXX Category 还需要进一步设计，即添加Shop_ID 关联等
		JSONObject json = new JSONObject();
		String Cat_ID = this.categoryLogic.saveCategory(category);
		if (Cat_ID == null || Cat_ID.equals("")) {
			json.put("result", false);
			json.put("Cat_ID", "");
		} else {
			json.put("result", true);
			json.put("Cat_ID", Cat_ID);
		}
		return json.toString();
	}

	public String getCategoryInformationById(String categoryId) {
		Category category = this.categoryLogic.getCategoryInfoById(categoryId);
		return category.toJSONString();
	}

	public String addProduct(Products product) {
		// XXX 还需要进一步设计，即添加Shop_ID 关联等
		JSONObject json = new JSONObject();
		String Prod_ID = this.productsLogic.saveProduct(product);
		if (Prod_ID == null || Prod_ID.equals("")) {
			json.put("result", false);
			json.put("Prod_ID", "");
		} else {
			json.put("result", true);
			json.put("Prod_ID", Prod_ID);
		}
		return json.toString();
	}

	public String getProductInformation(String productId) {
		Products product = this.productsLogic.getProductInfo(productId);
		return product.toJSONString();
	}

	public String deleteProduct(String productId) {
		JSONObject json = new JSONObject();
		boolean flag = this.productsLogic.deleteProduct(productId);
		if (flag) {
			json.put("result", true);
		} else {
			json.put("result", false);
		}
		return json.toString();
	}
}