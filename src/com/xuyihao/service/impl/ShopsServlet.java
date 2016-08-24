package com.xuyihao.service.impl;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.xuyihao.entity.Category;
import com.xuyihao.entity.Products;
import com.xuyihao.entity.Shops;
import com.xuyihao.logic.CategoryLogic;
import com.xuyihao.logic.ProductsLogic;
import com.xuyihao.logic.ShopsLogic;

import net.sf.json.JSONObject;

public class ShopsServlet extends HttpServlet implements com.xuyihao.service.ShopsService {

	/**
	 * <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 2129550310773048138L;

	@Autowired
	private ShopsLogic shopsLogic;

	@Autowired
	private CategoryLogic categoryLogic;

	@Autowired
	private ProductsLogic productsLogic;

	private HttpSession session = null;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置会话信息
		this.session = request.getSession();
		String action = request.getParameter("action").trim();
		if (action.equals("addShop")) {
			Shops shop = new Shops();
			shop.setAcc_ID(session.getAttribute("Acc_ID").toString());
			shop.setShop_name(request.getParameter("Shop_name"));
			shop.setShop_info(request.getParameter("Shop_info"));
			// TODO 需要进一步设计，店铺运营执照信息是存图片还是文字，后期需要修正
			shop.setShop_licen(request.getParameter("Shop_licen"));
			String message = this.addShop(shop);
			response.getWriter().println(message);
		} else if (action.equals("shopNameExists")) {
			String shopName = request.getParameter("Shop_name");
			String message = this.isShopNameExists(shopName);
			response.getWriter().println(message);
		} else if (action.equals("getShopIdByname")) {
			String shopName = request.getParameter("Shop_name");
			String message = this.getShopIdByName(shopName);
			response.getWriter().println(message);
		} else if (action.equals("getShopInfo")) {
			String shopId = request.getParameter("Shop_ID");
			String message = this.getShopInformationById(shopId);
			response.getWriter().println(message);
		} else if (action.equals("changeShopInfo")) {
			Shops shop = new Shops();
			// XXX Shop_ID 为必需项
			shop.setShop_ID(request.getParameter("Shop_ID"));
			shop.setShop_name(request.getParameter("Shop_name"));
			shop.setShop_info(request.getParameter("Shop_info"));
			// TODO 需要进一步设计，店铺运营执照信息是存图片还是文字，后期需要修正
			shop.setShop_licen(request.getParameter("Shop_licen"));
			String message = this.changeShopInformation(shop);
			response.getWriter().println(message);
		} else if (action.equals("deleteShop")) {
			String shopId = request.getParameter("Shop_Id");
			String message = this.deleteShop(shopId);
			response.getWriter().println(message);
		} else if (action.equals("addCat")) {
			// TODO category 在设计上需要完善
			Category category = new Category();
			category.setCat_name(request.getParameter("Cat_ID"));
			category.setCat_desc(request.getParameter("Cat_desc"));
			String message = this.addCategory(category);
			response.getWriter().println(message);
		} else if (action.equals("getCatInfo")) {
			String categoryId = request.getParameter("Cat_ID");
			String message = this.getCategoryInformationById(categoryId);
			response.getWriter().println(message);
		} else if (action.equals("addProd")) {
			Products product = new Products();
			product.setCat_ID(request.getParameter("Cat_ID"));
			product.setShop_ID(request.getParameter("Shop_ID"));
			product.setProd_name(request.getParameter("Prod_name"));
			product.setProd_desc(request.getParameter("Prod_desc"));
			// TODO 产品详情需要进一步详细设计
			product.setProd_info(request.getParameter("Prod_info"));
			product.setProd_price(Float.parseFloat(request.getParameter("Prod_price")));
			product.setProd_num(Integer.parseInt(request.getParameter("Prod_num")));
			String message = this.addProduct(product);
			response.getWriter().println(message);
		} else if (action.equals("getProdInfo")) {
			String productId = request.getParameter("Prod_ID");
			String message = this.getProductInformation(productId);
			response.getWriter().println(message);
		} else if (action.equals("deleteProd")) {
			String productId = request.getParameter("Prod_ID");
			String message = this.deleteProduct(productId);
			response.getWriter().println(message);
		}
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	@Override
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

	@Override
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

	@Override
	public String getShopIdByName(String shopName) {
		JSONObject json = new JSONObject();
		String id = this.shopsLogic.getShopID(shopName);
		if (id == null || id.equals("")) {
			json.put("result", true);
			json.put("Shop_ID", id);
		} else {
			json.put("result", false);
			json.put("Shop_ID", "");
		}
		return json.toString();
	}

	@Override
	public String getShopInformationById(String shopId) {
		Shops shop = this.shopsLogic.getShopInfo(shopId);
		return shop.toJSONString();
	}

	@Override
	public String changeShopInformation(Shops shop) {
		JSONObject json = new JSONObject();
		String Acc_ID = this.session.getAttribute("Acc_ID").toString();
		if (Acc_ID.equals(shop.getAcc_ID())) {
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

	@Override
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

	@Override
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

	@Override
	public String getCategoryInformationById(String categoryId) {
		Category category = this.categoryLogic.getCategoryInfoById(categoryId);
		return category.toJSONString();
	}

	@Override
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

	@Override
	public String getProductInformation(String productId) {
		Products product = this.productsLogic.getProductInfo(productId);
		return product.toJSONString();
	}

	@Override
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

	public void setShopsLogic(ShopsLogic shopsLogic) {
		this.shopsLogic = shopsLogic;
	}

	public void setCategoryLogic(CategoryLogic categoryLogic) {
		this.categoryLogic = categoryLogic;
	}

	public void setProductsLogic(ProductsLogic productsLogic) {
		this.productsLogic = productsLogic;
	}
}