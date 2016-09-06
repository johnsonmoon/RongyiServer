package xuyihao.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import xuyihao.common.DatabaseConnector;
import xuyihao.dao.CartDao;
import xuyihao.entity.Cart;

/**
 * created by xuyihao on 2016/4/30
 * 
 * @description 购物车数据库操作工具类
 */
@Component("CartDao")
public class CartDaoImpl implements CartDao {
	private DatabaseConnector conn = new DatabaseConnector();

	public CartDaoImpl() {
	}

	public void setConn(DatabaseConnector conn) {
		this.conn = conn;
	}

	public boolean saveCart(Cart cart) {
		boolean flag = false;
		String sql = "insert into " + Cart.BASE_TABLE_NAME + " values(null, '" + cart.getCart_ID() + "', '"
				+ cart.getProd_ID() + "', '" + cart.getAcc_ID() + "', " + cart.getProd_price() + ", " + cart.getPro_num()
				+ ", DATE_FORMAT('" + cart.getCart_addTime() + "', '%Y-%m-%d %H:%i:%s'))";
		if (this.conn.executeUpdate(sql) != 0) {
			flag = true;
		}
		this.conn.close();
		return flag;
	}

	public boolean deleteCart(String Cart_ID) {
		boolean flag = false;
		String sql = "delete from " + Cart.BASE_TABLE_NAME + " where " + Cart.BASE_CART_ID + " = '" + Cart_ID + "'";
		if (this.conn.executeUpdate(sql) == 0) {
			flag = false;
		} else {
			flag = true;
		}
		this.conn.close();
		return flag;
	}

	public boolean updateCart(Cart cart) {
		boolean flag = false;
		String sql = "update " + Cart.BASE_TABLE_NAME + " set " + Cart.BASE_CART_PRODUCT_ID + " = '" + cart.getProd_ID()
				+ "', " + Cart.BASE_CART_ACCOUNT_ID + " = '" + cart.getAcc_ID() + "', " + Cart.BASE_CART_PRODUCT_PRICE + " = "
				+ cart.getProd_price() + ", " + Cart.BASE_CART_PRODUCT_NUMBER + " = " + cart.getPro_num() + ", "
				+ Cart.BASE_CART_ADD_TIME + " = '" + cart.getCart_addTime() + "' where " + Cart.BASE_CART_ID + " = '"
				+ cart.getCart_ID() + "'";
		if (this.conn.executeUpdate(sql) == 0) {
			flag = false;
		} else {
			flag = true;
		}
		this.conn.close();
		return flag;
	}

	public boolean updateCart(String update) {
		boolean flag = false;
		if (this.conn.executeUpdate(update) == 0) {
			flag = false;
		} else {
			flag = true;
		}
		this.conn.close();
		return flag;
	}

	public List<Cart> queryByAccountId(String Acc_ID) {
		String sql = "select * from " + Cart.BASE_TABLE_NAME + " where " + Cart.BASE_CART_ACCOUNT_ID + " = '" + Acc_ID
				+ "'";
		ResultSet rs = this.conn.executeQuery(sql);
		List<Cart> cartList = getCartListFromResultSet(rs);
		this.conn.close();
		return cartList;
	}

	public Cart queryById(String Cart_ID) {
		String sql = "select * from " + Cart.BASE_TABLE_NAME + " where " + Cart.BASE_CART_ID + " = '" + Cart_ID + "'";
		ResultSet rs = this.conn.executeQuery(sql);
		Cart cart = getCartFromResultSet(rs);
		this.conn.close();
		return cart;
	}

	public ResultSet queryBySql(String query) {
		ResultSet resultSet = this.conn.executeQuery(query);
		return resultSet;
	}

	/**
	 * 从ResultSet中获取Cart对象
	 *
	 */
	private Cart getCartFromResultSet(ResultSet resultSet) {
		Cart cart = new Cart();
		try {
			if (resultSet.next()) {
				cart.set_id(resultSet.getLong(Cart.BASE_CART_PHYSICAL_ID));
				cart.setCart_ID(resultSet.getString(Cart.BASE_CART_ID));
				cart.setProd_ID(resultSet.getString(Cart.BASE_CART_PRODUCT_ID));
				cart.setAcc_ID(resultSet.getString(Cart.BASE_CART_ACCOUNT_ID));
				cart.setProd_price(resultSet.getFloat(Cart.BASE_CART_PRODUCT_PRICE));
				cart.setPro_num(resultSet.getInt(Cart.BASE_CART_PRODUCT_NUMBER));
				cart.setCart_addTime(resultSet.getString(Cart.BASE_CART_ADD_TIME));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cart;
	}

	/**
	 * 从ResultSet中获取Cart对象
	 *
	 */
	private List<Cart> getCartListFromResultSet(ResultSet resultSet) {
		List<Cart> cartList = new ArrayList<>();
		try {
			while (resultSet.next()) {
				Cart cart = new Cart();
				cart.set_id(resultSet.getLong(Cart.BASE_CART_PHYSICAL_ID));
				cart.setCart_ID(resultSet.getString(Cart.BASE_CART_ID));
				cart.setProd_ID(resultSet.getString(Cart.BASE_CART_PRODUCT_ID));
				cart.setAcc_ID(resultSet.getString(Cart.BASE_CART_ACCOUNT_ID));
				cart.setProd_price(resultSet.getFloat(Cart.BASE_CART_PRODUCT_PRICE));
				cart.setPro_num(resultSet.getInt(Cart.BASE_CART_PRODUCT_NUMBER));
				cart.setCart_addTime(resultSet.getString(Cart.BASE_CART_ADD_TIME));
				cartList.add(cart);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cartList;
	}

	public void closeDBConnection() {
		this.conn.close();
	}
}