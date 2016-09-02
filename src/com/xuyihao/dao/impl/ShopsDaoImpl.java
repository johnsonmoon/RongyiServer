package com.xuyihao.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.xuyihao.common.DatabaseConnector;
import com.xuyihao.dao.ShopsDao;
import com.xuyihao.entity.Shops;

import java.util.*;

import org.springframework.stereotype.Component;

/**
 * created by xuyihao on 2016/4/28
 * 
 * @attention 平台暂时不开放删除店铺功能
 */
@Component("ShopsDao")
public class ShopsDaoImpl implements ShopsDao {
	private DatabaseConnector conn = new DatabaseConnector();

	public ShopsDaoImpl() {
	}

	public void setConn(DatabaseConnector conn) {
		this.conn = conn;
	}

	public boolean saveShops(Shops shop) {
		String sql = "insert into " + Shops.BASE_TABLE_NAME + " values(null, '" + shop.getShop_ID() + "', '"
				+ shop.getShop_name() + "', '" + shop.getShop_info() + "', '" + shop.getShop_licen() + "', "
				+ shop.getShop_lvl() + ", " + shop.getShop_ryb() + ", " + shop.getShop_favo() + ", '" + shop.getAcc_ID()
				+ "', DATE_FORMAT('" + shop.getShop_addTime() + "', '%Y-%m-%d %H:%i:%s'))";
		boolean flag = false;
		if (this.conn.executeUpdate(sql) != 0) {
			flag = true;
		}
		this.conn.close();
		return flag;
	}

	public boolean deleteShops(String Shop_ID) {
		boolean flag = false;
		String sql = "delete from " + Shops.BASE_TABLE_NAME + " where " + Shops.BASE_SHOP_ID + " = '" + Shop_ID + "'";
		if (this.conn.executeUpdate(sql) == 0) {
			flag = false;
		} else {
			flag = true;
		}
		this.conn.close();
		return flag;
	}

	public boolean updateShops(Shops shop) {
		boolean flag = false;
		String sql = "update " + Shops.BASE_TABLE_NAME + " set " + Shops.BASE_SHOP_NAME + " = '" + shop.getShop_name()
				+ "', " + Shops.BASE_SHOP_INFORMATION + " = '" + shop.getShop_info() + "', " + Shops.BASE_SHOP_LICENSE + " = '"
				+ shop.getShop_licen() + "', " + Shops.BASE_SHOP_LEVEL + " = " + shop.getShop_lvl() + ", " + Shops.BASE_SHOP_RYB
				+ " = " + shop.getShop_ryb() + ", " + Shops.BASE_SHOP_FAVOURITED_COUNT + " = " + shop.getShop_favo() + ", "
				+ Shops.BASE_SHOP_OWNER_ACCOUNT_ID + " = '" + shop.getAcc_ID() + "', " + Shops.BASE_SHOP_ADD_TIME + " = '"
				+ shop.getShop_addTime() + "' where " + Shops.BASE_SHOP_ID + " = '" + shop.getShop_ID() + "'";
		if (this.conn.executeUpdate(sql) != 0) {
			flag = true;
		}
		this.conn.close();
		return flag;
	}

	public boolean updateShops(String update) {
		boolean flag = false;
		if (this.conn.executeUpdate(update) != 0) {
			flag = true;
		}
		this.conn.close();
		return flag;
	}

	public Shops queryById(String Shop_ID) {
		String sql = "select * from " + Shops.BASE_TABLE_NAME + " where " + Shops.BASE_SHOP_ID + " = '" + Shop_ID + "'";
		ResultSet resultSet = this.conn.executeQuery(sql);
		Shops shops = getShopsFromResultSet(resultSet);
		this.conn.close();
		return shops;
	}

	public Shops queryByName(String Shop_name) {
		String sql = "select * from " + Shops.BASE_TABLE_NAME + " where " + Shops.BASE_SHOP_NAME + " = '" + Shop_name + "'";
		ResultSet resultSet = this.conn.executeQuery(sql);
		Shops shops = getShopsFromResultSet(resultSet);
		this.conn.close();
		return shops;
	}

	public List<Shops> queryByAccountId(String Acc_ID) {
		String sql = "select * from " + Shops.BASE_TABLE_NAME + " where " + Shops.BASE_SHOP_OWNER_ACCOUNT_ID + " = '"
				+ Acc_ID + "'";
		ResultSet resultSet = this.conn.executeQuery(sql);
		List<Shops> shopsList = getShopsListFromResultSet(resultSet);
		this.conn.close();
		return shopsList;
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
	private Shops getShopsFromResultSet(ResultSet resultSet) {
		Shops shops = new Shops();
		try {
			if (resultSet.next()) {
				shops.set_id(resultSet.getLong(Shops.BASE_SHOP_PHYSICAL_ID));
				shops.setShop_ID(resultSet.getString(Shops.BASE_SHOP_ID));
				shops.setShop_name(resultSet.getString(Shops.BASE_SHOP_NAME));
				shops.setShop_info(resultSet.getString(Shops.BASE_SHOP_INFORMATION));
				shops.setShop_licen(resultSet.getString(Shops.BASE_SHOP_LICENSE));
				shops.setShop_lvl(resultSet.getInt(Shops.BASE_SHOP_LEVEL));
				shops.setShop_ryb(resultSet.getInt(Shops.BASE_SHOP_RYB));
				shops.setShop_favo(resultSet.getInt(Shops.BASE_SHOP_FAVOURITED_COUNT));
				shops.setAcc_ID(resultSet.getString(Shops.BASE_SHOP_OWNER_ACCOUNT_ID));
				shops.setShop_addTime(resultSet.getString(Shops.BASE_SHOP_ADD_TIME));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return shops;
	}

	/**
	 * 通过ResultSet获取List对象
	 *
	 *
	 */
	private List<Shops> getShopsListFromResultSet(ResultSet resultSet) {
		List<Shops> shopsList = new ArrayList<>();
		try {
			while (resultSet.next()) {
				Shops shops = new Shops();
				shops.set_id(resultSet.getLong("_id"));
				shops.set_id(resultSet.getLong(Shops.BASE_SHOP_PHYSICAL_ID));
				shops.setShop_ID(resultSet.getString(Shops.BASE_SHOP_ID));
				shops.setShop_name(resultSet.getString(Shops.BASE_SHOP_NAME));
				shops.setShop_info(resultSet.getString(Shops.BASE_SHOP_INFORMATION));
				shops.setShop_licen(resultSet.getString(Shops.BASE_SHOP_LICENSE));
				shops.setShop_lvl(resultSet.getInt(Shops.BASE_SHOP_LEVEL));
				shops.setShop_ryb(resultSet.getInt(Shops.BASE_SHOP_RYB));
				shops.setShop_favo(resultSet.getInt(Shops.BASE_SHOP_FAVOURITED_COUNT));
				shops.setAcc_ID(resultSet.getString(Shops.BASE_SHOP_OWNER_ACCOUNT_ID));
				shops.setShop_addTime(resultSet.getString(Shops.BASE_SHOP_ADD_TIME));
				shopsList.add(shops);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return shopsList;
	}

	public void closeDBConnection() {
		this.conn.close();
	}
}