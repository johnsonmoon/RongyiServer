package com.xuyihao.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xuyihao.common.DatabaseConnector;
import com.xuyihao.dao.ProductsDao;
import com.xuyihao.entity.Products;

/**
 * created by xuyihao on 2016/4/28
 * 
 * @description 产品数据库操作工具类
 * @attention 删除产品时候由于完整性约束，应提前删除有关表项的相关数据
 */
public class ProductsDaoImpl implements ProductsDao {
	private DatabaseConnector conn = new DatabaseConnector();

	public ProductsDaoImpl() {
	}

	public void setConn(DatabaseConnector conn) {
		this.conn = conn;
	}

	public boolean saveProducts(Products product) {
		String sql = "insert into " + Products.BASE_TABLE_NAME + " values(null, '" + product.getProd_ID() + "', '"
				+ product.getCat_ID() + "', '" + product.getShop_ID() + "', '" + product.getProd_name() + "', '"
				+ product.getProd_desc() + "', '" + product.getProd_info() + "', " + product.getProd_price() + ", "
				+ product.getProd_num() + ", " + product.getProd_sold() + ", DATE_FORMAT('" + product.getProd_addTime()
				+ "', '%Y-%m-%d %H:%i:%s'))";
		boolean flag = false;
		if (this.conn.executeUpdate(sql) != 0) {
			flag = true;
		}
		this.conn.close();
		return flag;
	}

	public boolean deleteProducts(String Prod_ID) {
		boolean flag = false;
		String sql = "delete from " + Products.BASE_TABLE_NAME + " where " + Products.BASE_PRODUCT_ID + " = '" + Prod_ID
				+ "'";
		if (this.conn.executeUpdate(sql) == 0) {
			flag = false;
		} else {
			flag = true;
		}
		this.conn.close();
		return flag;
	}

	public boolean updateProducts(Products product) {
		boolean flag = false;
		String sql = "update " + Products.BASE_TABLE_NAME + " set " + Products.BASE_PRODUCT_CATEGORY_ID + " = '"
				+ product.getCat_ID() + "', " + Products.BASE_PRODUCT_SHOP_ID + " = '" + product.getShop_ID() + "', "
				+ Products.BASE_PRODUCT_NAME + " = '" + product.getProd_name() + "', " + Products.BASE_PRODUCT_DESCRIPTION
				+ " = '" + product.getProd_desc() + "', " + Products.BASE_PRODUCT_INFORMATION + " = '" + product.getProd_info()
				+ "', " + Products.BASE_PRODUCT_PRICE + " = " + product.getProd_price() + ", "
				+ Products.BASE_PRODUCT_REMAIN_NUMBER + " = " + product.getProd_num() + ", " + Products.BASE_PRODUCT_SOLD_COUNT
				+ " = " + product.getProd_sold() + ", " + Products.BASE_PRODUCT_ADD_TIME + " = '" + product.getProd_addTime()
				+ "' where " + Products.BASE_PRODUCT_ID + " = '" + product.getProd_ID() + "'";
		if (this.conn.executeUpdate(sql) != 0) {
			flag = true;
		}
		this.conn.close();
		return flag;
	}

	public boolean updateProducts(String update) {
		boolean flag = false;
		if (this.conn.executeUpdate(update) != 0) {
			flag = true;
		}
		this.conn.close();
		return flag;
	}

	public Products queryById(String Prod_ID) {
		String sql = "select * from " + Products.BASE_TABLE_NAME + " where " + Products.BASE_PRODUCT_ID + " = '" + Prod_ID
				+ "'";
		ResultSet resultSet = this.conn.executeQuery(sql);
		Products products = getProductsFromResultSet(resultSet);
		this.conn.close();
		return products;
	}

	public List<Products> queryByName(String Prod_name) {
		String sql = "select * from " + Products.BASE_TABLE_NAME + " where " + Products.BASE_PRODUCT_NAME + " = '"
				+ Prod_name + "'";
		ResultSet resultSet = this.conn.executeQuery(sql);
		List<Products> productsList = getProductsListFromResultSet(resultSet);
		this.conn.close();
		return productsList;
	}

	public List<Products> queryByCategoryId(String Cat_ID) {
		String sql = "select * from " + Products.BASE_TABLE_NAME + " where " + Products.BASE_PRODUCT_CATEGORY_ID + " = '"
				+ Cat_ID + "'";
		ResultSet resultSet = this.conn.executeQuery(sql);
		List<Products> productsList = getProductsListFromResultSet(resultSet);
		this.conn.close();
		return productsList;
	}

	public List<Products> queryByShopId(String Shop_ID) {
		String sql = "select * from " + Products.BASE_TABLE_NAME + " where " + Products.BASE_PRODUCT_SHOP_ID + " = '"
				+ Shop_ID + "'";
		ResultSet resultSet = this.conn.executeQuery(sql);
		List<Products> productsList = getProductsListFromResultSet(resultSet);
		this.conn.close();
		return productsList;
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
	private Products getProductsFromResultSet(ResultSet resultSet) {
		Products products = new Products();
		try {
			if (resultSet.next()) {
				products.set_id(resultSet.getLong(Products.BASE_PRODUCT_PHYSICAL_ID));
				products.setProd_ID(resultSet.getString(Products.BASE_PRODUCT_ID));
				products.setCat_ID(resultSet.getString(Products.BASE_PRODUCT_CATEGORY_ID));
				products.setShop_ID(resultSet.getString(Products.BASE_PRODUCT_SHOP_ID));
				products.setProd_name(resultSet.getString(Products.BASE_PRODUCT_NAME));
				products.setProd_desc(resultSet.getString(Products.BASE_PRODUCT_DESCRIPTION));
				products.setProd_info(resultSet.getString(Products.BASE_PRODUCT_INFORMATION));
				products.setProd_price(resultSet.getFloat(Products.BASE_PRODUCT_PRICE));
				products.setProd_num(resultSet.getInt(Products.BASE_PRODUCT_REMAIN_NUMBER));
				products.setProd_sold(resultSet.getInt(Products.BASE_PRODUCT_SOLD_COUNT));
				products.setProd_addTime(resultSet.getString(Products.BASE_PRODUCT_ADD_TIME));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}

	/**
	 * 通过ResultSet获取List对象
	 *
	 *
	 */
	private List<Products> getProductsListFromResultSet(ResultSet resultSet) {
		List<Products> productsList = new ArrayList<>();
		try {
			while (resultSet.next()) {
				Products products = new Products();
				products.set_id(resultSet.getLong(Products.BASE_PRODUCT_PHYSICAL_ID));
				products.setProd_ID(resultSet.getString(Products.BASE_PRODUCT_ID));
				products.setCat_ID(resultSet.getString(Products.BASE_PRODUCT_CATEGORY_ID));
				products.setShop_ID(resultSet.getString(Products.BASE_PRODUCT_SHOP_ID));
				products.setProd_name(resultSet.getString(Products.BASE_PRODUCT_NAME));
				products.setProd_desc(resultSet.getString(Products.BASE_PRODUCT_DESCRIPTION));
				products.setProd_info(resultSet.getString(Products.BASE_PRODUCT_INFORMATION));
				products.setProd_price(resultSet.getFloat(Products.BASE_PRODUCT_PRICE));
				products.setProd_num(resultSet.getInt(Products.BASE_PRODUCT_REMAIN_NUMBER));
				products.setProd_sold(resultSet.getInt(Products.BASE_PRODUCT_SOLD_COUNT));
				products.setProd_addTime(resultSet.getString(Products.BASE_PRODUCT_ADD_TIME));
				productsList.add(products);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productsList;
	}

	public void closeDBConnection() {
		this.conn.close();
	}
}