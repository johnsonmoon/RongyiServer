package com.xuyihao.logic;

import com.xuyihao.entity.Cart;

/**
 * 购物车逻辑类
 *
 * Created by Xuyh on 2016/7/20.
 */
public interface CartLogic {
	/**
	 * insert information into database table
	 *
	 * @param cart instance of Cart
	 * @return Cart_ID(String) if successfully
	 */
	public String saveCart(Cart cart);

	/**
	 * get Cart information from the database table
	 *
	 * @param Cart_ID
	 * @return
	 */
	public Cart getCartInfo(String Cart_ID);

	/**
	 * change Pro_num from the database table
	 *
	 * @param Cart_ID ID of the Cart
	 * @param Pro_num number of products in the cart to be changed
	 * @return
	 */
	public boolean changeProductNumber(String Cart_ID, int Pro_num);

	/**
	 * delete information from Cart table
	 *
	 * @param cart instance of Cart
	 * @return
	 */
	public boolean deleteCart(String Cart_ID);
}
