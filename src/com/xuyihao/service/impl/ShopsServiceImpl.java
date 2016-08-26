package com.xuyihao.service.impl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.xuyihao.common.ThreadLocalContext;
import com.xuyihao.entity.Category;
import com.xuyihao.entity.Products;
import com.xuyihao.entity.Shops;
import com.xuyihao.logic.CategoryLogic;
import com.xuyihao.logic.ProductsLogic;
import com.xuyihao.logic.ShopsLogic;

import net.sf.json.JSONObject;

/**
 * 
 * @Author Xuyh created at 2016年8月26日 下午1:18:57
 */
public class ShopsServiceImpl implements com.xuyihao.service.ShopsService {

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

	public void init() {
		if (shopsLogic == null) {
			this.shopsLogic = (ShopsLogic) ThreadLocalContext.getBean("ShopsLogic");
		}
		if (categoryLogic == null) {
			this.categoryLogic = (CategoryLogic) ThreadLocalContext.getBean("CategoryLogic");
		}
		if (productsLogic == null) {
			this.productsLogic = (ProductsLogic) ThreadLocalContext.getBean("ProductsLogic");
		}
	}

	public void setSessionInfo(HttpSession session) {
		this.session = session;
	}

	public String addShop(Shops shop) {
		this.init();
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
		this.init();
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
		this.init();
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
		this.init();
		Shops shop = this.shopsLogic.getShopInfo(shopId);
		return shop.toJSONString();
	}

	public String changeShopInformation(Shops shop) {
		this.init();
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
		this.init();
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
		this.init();
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
		this.init();
		Category category = this.categoryLogic.getCategoryInfoById(categoryId);
		return category.toJSONString();
	}

	public String addProduct(Products product) {
		this.init();
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
		this.init();
		Products product = this.productsLogic.getProductInfo(productId);
		return product.toJSONString();
	}

	public String deleteProduct(String productId) {
		this.init();
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
