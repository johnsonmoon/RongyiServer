package xuyihao.service;

import javax.servlet.http.HttpSession;

import xuyihao.entity.Category;
import xuyihao.entity.Products;
import xuyihao.entity.Shops;

/**
 * 店铺相关服务接口
 * 
 * @Author Xuyh created at 2016年8月23日 上午10:49:13
 */
public interface ShopsService {
	/**
	 * XXX ShopsLogic
	 */
	/**
	 * 初始化会话信息
	 * 
	 * @param session
	 */
	public void setSessionInfo(HttpSession session);

	/**
	 * 新建店铺
	 * 
	 * @param shop
	 * @return
	 */
	public String addShop(Shops shop);

	/**
	 * 店铺名是否存在
	 * 
	 * @param shopName
	 * @return
	 */
	public String isShopNameExists(String shopName);

	/**
	 * 通过店铺名获取店铺ID
	 * 
	 * @param shopName
	 * @return
	 */
	public String getShopIdByName(String shopName);

	/**
	 * 通过店铺ID获取店铺信息
	 * 
	 * @param shopId
	 * @return
	 */
	public String getShopInformationById(String shopId);

	/**
	 * 修改店铺信息
	 * 
	 * @param shop
	 * @return
	 */
	public String changeShopInformation(Shops shop);

	/**
	 * 删除店铺
	 * 
	 * @param shopId
	 * @return
	 */
	public String deleteShop(String shopId);

	/**
	 * XXX CategoryLogic
	 */
	/**
	 * 添加产品分类
	 * 
	 * @param category
	 * @return
	 */
	public String addCategory(Category category);

	/**
	 * 获取产品分类信息
	 * 
	 * @param categoryId
	 * @return
	 */
	public String getCategoryInformationById(String categoryId);

	/**
	 * XXX ProductsLogic
	 */
	/**
	 * 添加产品
	 * 
	 * @param product
	 * @return
	 */
	public String addProduct(Products product);

	/**
	 * 获取产品信息
	 * 
	 * @param productId
	 * @return
	 */
	public String getProductInformation(String productId);

	// XXX 不添加这几个接口，产品售卖流程逻辑较复杂，需要后期详细设计(等待2.0版本)
	// public String changeProductSold(String productId, int sold);
	// public String decreaseProductNumber(String productId);
	// public String isProductRemain(String productId);

	/**
	 * 删除产品
	 * 
	 * @param productId
	 * @return
	 */
	public String deleteProduct(String productId);
}