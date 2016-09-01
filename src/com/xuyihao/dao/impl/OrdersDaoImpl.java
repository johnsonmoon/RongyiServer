package com.xuyihao.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xuyihao.common.DatabaseConnector;
import com.xuyihao.dao.OrdersDao;
import com.xuyihao.entity.Orders;

/**
 * created by xuyihao on 2016/4/30
 * 
 * @description 订单的数据库操作工具类
 */
public class OrdersDaoImpl implements OrdersDao {
	private DatabaseConnector conn = new DatabaseConnector();

	public OrdersDaoImpl() {
	}

	public void setConn(DatabaseConnector conn) {
		this.conn = conn;
	}

	public boolean saveOrders(Orders orders) {
		String sql = "insert into " + Orders.BASE_TABLE_NAME + " values(null, '" + orders.getOrd_ID() + "', '"
				+ orders.getOrd_date() + "', '" + orders.getAcc_ID() + "', '" + orders.getProd_ID() + "', "
				+ orders.getProd_price() + ", " + orders.getPro_num() + ", " + orders.isOrd_paid() + ", " + orders.isOrd_sent()
				+ ", " + orders.isOrd_received() + ", " + orders.isOrd_comment() + ", '" + orders.getAdd_ID()
				+ "', DATE_FORMAT('" + orders.getOrd_addTime() + "', '%Y-%m-%d %H:%i:%s'))";
		boolean flag = false;
		if (this.conn.executeUpdate(sql) != 0) {
			flag = true;
		}
		this.conn.close();
		return flag;
	}

	public boolean deleteOrders(String Ord_ID) {
		boolean flag = false;
		String sql = "delete from " + Orders.BASE_TABLE_NAME + " where " + Orders.BASE_ORDERS_ID + " = '" + Ord_ID + "'";
		if (this.conn.executeUpdate(sql) == 0) {
			flag = false;
		} else {
			flag = true;
		}
		this.conn.close();
		return flag;
	}

	public boolean updateOrders(Orders orders) {
		boolean flag = false;
		String sql = "update " + Orders.BASE_TABLE_NAME + " set " + Orders.BASE_ORDERS_DATE + " = '" + orders.getOrd_date()
				+ "', " + Orders.BASE_ORDERS_ACCOUNT_ID + " = '" + orders.getAcc_ID() + "', " + Orders.BASE_ORDERS_PRODUCT_ID
				+ " = '" + orders.getProd_ID() + "', " + Orders.BASE_ORDERS_PRODUCT_PRICE + " = " + orders.getProd_price()
				+ ", " + Orders.BASE_ORDERS_PRODUCT_NUMBER + " = " + orders.getPro_num() + ", " + Orders.BASE_ORDERS_IS_PAID
				+ " = " + orders.isOrd_paid() + ", " + Orders.BASE_ORDERS_IS_SENT + " = " + orders.isOrd_sent() + ", "
				+ Orders.BASE_ORDERS_IS_RECEIVED + " = " + orders.isOrd_received() + ", " + Orders.BASE_ORDERS_IS_COMMENT
				+ " = " + orders.isOrd_comment() + ", " + Orders.BASE_ORDERS_ADDRESS_ID + " = '" + orders.getAdd_ID() + "', "
				+ Orders.BASE_ORDERS_ADD_TIME + " = '" + orders.getOrd_addTime() + "' where " + Orders.BASE_ORDERS_ID + " = '"
				+ orders.getOrd_ID() + "'";
		if (this.conn.executeUpdate(sql) == 0) {
			flag = false;
		} else {
			flag = true;
		}
		this.conn.close();
		return flag;
	}

	public boolean updateOrders(String update) {
		boolean flag = false;
		if (this.conn.executeUpdate(update) == 0) {
			flag = false;
		} else {
			flag = true;
		}
		this.conn.close();
		return flag;
	}

	public Orders queryById(String Ord_ID) {
		String sql = "select * from " + Orders.BASE_TABLE_NAME + " where " + Orders.BASE_ORDERS_ID + " = '" + Ord_ID + "'";
		ResultSet resultSet = this.conn.executeQuery(sql);
		Orders orders = getOrdersFromResultSet(resultSet);
		this.conn.close();
		return orders;
	}

	public List<Orders> queryByAccountId(String Acc_ID) {
		String sql = "select * from " + Orders.BASE_TABLE_NAME + " where " + Orders.BASE_ORDERS_ACCOUNT_ID + " = '" + Acc_ID
				+ "'";
		ResultSet resultSet = this.conn.executeQuery(sql);
		List<Orders> ordersList = getOrdersListFromResultSet(resultSet);
		this.conn.close();
		return ordersList;
	}

	public List<Orders> queryByOrderDate(String Ord_date) {
		String sql = "select * from " + Orders.BASE_TABLE_NAME + " where " + Orders.BASE_ORDERS_DATE + " = '" + Ord_date
				+ "'";
		ResultSet resultSet = this.conn.executeQuery(sql);
		List<Orders> ordersList = getOrdersListFromResultSet(resultSet);
		this.conn.close();
		return ordersList;
	}

	public ResultSet queryBySql(String sql) {
		ResultSet resultSet = this.conn.executeQuery(sql);
		return resultSet;
	}

	/**
	 * 通过ResultSet获取对象
	 *
	 *
	 */
	private Orders getOrdersFromResultSet(ResultSet resultSet) {
		Orders orders = new Orders();
		try {
			if (resultSet.next()) {
				orders.set_id(resultSet.getLong(Orders.BASE_ORDERS_PHYSICAL_ID));
				orders.setOrd_ID(resultSet.getString(Orders.BASE_ORDERS_ID));
				orders.setOrd_date(resultSet.getString(Orders.BASE_ORDERS_DATE));
				orders.setAcc_ID(resultSet.getString(Orders.BASE_ORDERS_ACCOUNT_ID));
				orders.setProd_ID(resultSet.getString(Orders.BASE_ORDERS_PRODUCT_ID));
				orders.setProd_price(resultSet.getFloat(Orders.BASE_ORDERS_PRODUCT_PRICE));
				orders.setPro_num(resultSet.getInt(Orders.BASE_ORDERS_PRODUCT_NUMBER));
				orders.setOrd_paid(resultSet.getBoolean(Orders.BASE_ORDERS_IS_PAID));
				orders.setOrd_sent(resultSet.getBoolean(Orders.BASE_ORDERS_IS_SENT));
				orders.setOrd_received(resultSet.getBoolean(Orders.BASE_ORDERS_IS_RECEIVED));
				orders.setOrd_comment(resultSet.getBoolean(Orders.BASE_ORDERS_IS_COMMENT));
				orders.setAdd_ID(resultSet.getString(Orders.BASE_ORDERS_ADDRESS_ID));
				orders.setOrd_addTime(resultSet.getString(Orders.BASE_ORDERS_ADD_TIME));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orders;
	}

	/**
	 * 通过ResultSet获取对象
	 *
	 *
	 */
	private List<Orders> getOrdersListFromResultSet(ResultSet resultSet) {
		List<Orders> ordersList = new ArrayList<>();
		try {
			while (resultSet.next()) {
				Orders orders = new Orders();
				orders.set_id(resultSet.getLong(Orders.BASE_ORDERS_PHYSICAL_ID));
				orders.setOrd_ID(resultSet.getString(Orders.BASE_ORDERS_ID));
				orders.setOrd_date(resultSet.getString(Orders.BASE_ORDERS_DATE));
				orders.setAcc_ID(resultSet.getString(Orders.BASE_ORDERS_ACCOUNT_ID));
				orders.setProd_ID(resultSet.getString(Orders.BASE_ORDERS_PRODUCT_ID));
				orders.setProd_price(resultSet.getFloat(Orders.BASE_ORDERS_PRODUCT_PRICE));
				orders.setPro_num(resultSet.getInt(Orders.BASE_ORDERS_PRODUCT_NUMBER));
				orders.setOrd_paid(resultSet.getBoolean(Orders.BASE_ORDERS_IS_PAID));
				orders.setOrd_sent(resultSet.getBoolean(Orders.BASE_ORDERS_IS_SENT));
				orders.setOrd_received(resultSet.getBoolean(Orders.BASE_ORDERS_IS_RECEIVED));
				orders.setOrd_comment(resultSet.getBoolean(Orders.BASE_ORDERS_IS_COMMENT));
				orders.setAdd_ID(resultSet.getString(Orders.BASE_ORDERS_ADDRESS_ID));
				orders.setOrd_addTime(resultSet.getString(Orders.BASE_ORDERS_ADD_TIME));
				ordersList.add(orders);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ordersList;
	}

	public void closeDBConnection() {
		this.conn.close();
	}
}