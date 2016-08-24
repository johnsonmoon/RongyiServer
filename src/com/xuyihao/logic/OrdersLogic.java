package com.xuyihao.logic;

import com.xuyihao.entity.Orders;

/**
 * Created by Administrator on 2016/7/20.
 */
public interface OrdersLogic {
	/**
	 * insert information into database table
	 *
	 * @param orders instance of Orders
	 * @return
	 */
	public String saveOrder(Orders orders);

	/**
	 * get order information from database table
	 * 
	 * @param Ord_ID
	 * @return
	 */
	public Orders getOrderInfo(String Ord_ID);

	/**
	 * delete order from database table
	 * 
	 * @param Ord_ID
	 * @return
	 */
	public boolean deleteOrder(String Ord_ID);

	/**
	 * change information into database table
	 *
	 * @param orders instance of Orders
	 * @return
	 */
	public boolean changeOrderInfo(Orders orders);
}
