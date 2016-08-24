package com.xuyihao.logic;

import com.xuyihao.entity.Shops;

/**
 * 店铺逻辑
 *
 * Created by Administrator on 2016/7/20.
 */
public interface ShopsLogic {
	/**
	 * insert information into databases
	 *
	 * @param shops instance of Shops
	 * @return
	 */
	public String saveShop(Shops shops);

	/**
	 * 
	 * @param Shop_name
	 * @return
	 */
	public boolean shopNameExist(String Shop_name);

	/**
	 * get Shop_ID by Shop_name
	 *
	 * @param name
	 * @return
	 */
	public String getShopID(String name);

	/**
	 * 
	 * @param Shop_ID
	 * @return
	 */
	public Shops getShopInfo(String Shop_ID);

	/**
	 * change shop information 修改店铺信息方法 店铺名注册后不可更改
	 *
	 * @param shop
	 * @return
	 */
	public boolean changeShopInfo(Shops shop);

	/**
	 * 
	 * @param Shop_ID
	 * @return
	 */
	public boolean deleteShop(String Shop_ID);
}
