package xuyihao.dao;

import java.util.*;

import xuyihao.entity.Orders;

import java.sql.ResultSet;

/**
 * Created by Xuyh at 2016/7/21 20:23.
 */
public interface OrdersDao {
	/**
	 * 写入数据库
	 *
	 * @param orders 订单对象
	 * @return
	 */
	public boolean saveOrders(Orders orders);

	/**
	 * 从数据库删除数据
	 *
	 * @param orders 订单对象
	 * @return true成功， false失败
	 */
	public boolean deleteOrders(String Ord_ID);

	/**
	 * 修改数据库中的内容
	 *
	 * @param orders 订单对象
	 * @return true成功， false失败
	 */
	public boolean updateOrders(Orders orders);

	/**
	 * 修改数据库中的内容
	 *
	 * @param update 更新的sql语句
	 * @return true成功， false失败
	 */
	public boolean updateOrders(String update);

	/**
	 * 查询
	 *
	 * @param Ord_ID 订单ID
	 * @return
	 */
	public Orders queryById(String Ord_ID);

	/**
	 * 查询
	 *
	 * @param Acc_ID 用户ID
	 * @return
	 */
	public List<Orders> queryByAccountId(String Acc_ID);

	/**
	 * 查询
	 *
	 * @param Ord_date 下单时间(天)
	 * @return
	 */
	public List<Orders> queryByOrderDate(String Ord_date);

	/**
	 * 查询
	 *
	 * @param sql 查询sql语句
	 * @return Resultset 查询结果游标
	 */
	public ResultSet queryBySql(String sql);

	/**
	 * 关闭数据库连接(对于返回ResultSet的查询，其余的不用)
	 * 
	 */
	public void closeDBConnection();
}
