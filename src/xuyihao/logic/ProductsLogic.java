package xuyihao.logic;

import xuyihao.entity.Products;

/**
 * 产品逻辑
 *
 * Created by Administrator on 2016/7/20.
 */
public interface ProductsLogic {
	/**
	 * insert information into database tables
	 *
	 * @param products instance of Products
	 * @return
	 */
	public String saveProduct(Products products);

	/**
	 * get information from database table
	 * 
	 * @param Prod_ID
	 * @return
	 */
	public Products getProductInfo(String Prod_ID);

	/**
	 * 修改月销量
	 * 
	 * @param Prod_ID
	 * @param sold
	 * @return
	 */
	public boolean changeProductSold(String Prod_ID, int sold);

	/**
	 * decrease Prod_num 减少存货量，一次减少一件，需要synchronized修饰，以保证线程安全
	 * 
	 * @param Prod_ID
	 * @return
	 */
	public boolean decreaseProductNumber(String Prod_ID);

	/**
	 * find out how many Products remain, whether Prod_num bigger than zero
	 * 查看数据库中产品是否还有库存
	 * 
	 * @param Prod_ID
	 * @return
	 */
	public boolean whetherProductRemain(String Prod_ID);

	/**
	 * delete information from database tables
	 * 
	 * @param Prod_ID
	 * @return
	 */
	public boolean deleteProduct(String Prod_ID);
}
