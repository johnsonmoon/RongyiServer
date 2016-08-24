package com.xuyihao.dao;

import com.xuyihao.entity.Cart;
import java.sql.ResultSet;
import java.util.List;

/**
 * Created by Xuyh on 2016/7/21.
 */
public interface CartDao {
	/**
	 * 写入数据库
	 *
	 * @param cart 购物车对象
	 * @return
	 */
	public boolean saveCart(Cart cart);

	/**
	 * 从数据库删除数据
	 *
	 * @param cart 购物车对象
	 * @return true成功， false失败
	 */
	public boolean deleteCart(String Cart_ID);

	/**
	 * 修改数据库中的数据
	 *
	 * @param cart 购物车对象
	 * @return true成功， false失败
	 */
	public boolean updateCart(Cart cart);

	/**
	 * 通过传入sql语句改数据
	 *
	 * @param update 更新的sql语句
	 * @return int -1 失败 1 成功
	 */
	public boolean updateCart(String update);

	/**
	 * 查询
	 *
	 * @param Acc_ID 账号ID(查询账号的收货地址集)
	 * @return 购物车对象
	 */
	public List<Cart> queryByAccountId(String Acc_ID);

	/**
	 * 查询
	 *
	 * @param Cart_ID 购物车ID
	 * @return 购物车对象
	 */
	public Cart queryById(String Cart_ID);

	/**
	 * 查询
	 *
	 * @param query 查询sql语句
	 * @return Resultset 查询结果游标
	 */
	public ResultSet queryBySql(String query);
	
	/**
	 * 关闭数据库连接(对于返回ResultSet的查询，其余的不用)
	 * 
	 */
	public void closeDBConnection();
}
