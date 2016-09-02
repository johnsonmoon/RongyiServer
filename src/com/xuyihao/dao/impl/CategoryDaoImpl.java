package com.xuyihao.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import com.xuyihao.common.DatabaseConnector;
import com.xuyihao.dao.CategoryDao;
import com.xuyihao.entity.Category;

/**
 * created by xuyihao on 2016/4/28
 * 
 * @description 商品分类的数据库操作工具类
 * @attention 商品分类不提供删除方法
 */
@Component("CategoryDao")
public class CategoryDaoImpl implements CategoryDao {
	private DatabaseConnector conn = new DatabaseConnector();

	public CategoryDaoImpl() {
	}

	public void setConn(DatabaseConnector conn) {
		this.conn = conn;
	}

	public boolean saveCategory(Category category) {
		boolean flag = false;
		String sql = "insert into " + Category.BASE_TABLE_NAME + " values(null, '" + category.getCat_ID() + "', '"
				+ category.getCat_name() + "', '" + category.getCat_desc() + "', DATE_FORMAT('" + category.getCat_addTime()
				+ "', '%Y-%m-%d %H:%i:%s'))";
		if (this.conn.executeUpdate(sql) != 0) {
			flag = true;
		}
		this.conn.close();
		return flag;
	}

	public boolean deleteCategory(String Cat_ID) {
		boolean flag = false;
		String sql = "delete from " + Category.BASE_TABLE_NAME + " where " + Category.BASE_CATEGORY_ID + " = '" + Cat_ID
				+ "'";
		if (this.conn.executeUpdate(sql) != 0) {
			flag = true;
		}
		this.conn.close();
		return flag;
	}

	public boolean updateCategory(Category category) {
		boolean flag = false;
		String sql = "update " + Category.BASE_TABLE_NAME + " set " + Category.BASE_CATEGORY_NAME + " = '"
				+ category.getCat_name() + "', " + Category.BASE_CATEGORY_DESCRIPTION + " = '" + category.getCat_desc() + "', "
				+ Category.BASE_CATEGORY_ADD_TIME + " = '" + category.getCat_addTime() + "' where " + Category.BASE_CATEGORY_ID
				+ " = '" + category.getCat_ID() + "'";
		if (this.conn.executeUpdate(sql) != 0) {
			flag = true;
		}
		this.conn.close();
		return flag;
	}

	public boolean updateCategory(String update) {
		boolean flag = false;
		if (this.conn.executeUpdate(update) != 0) {
			flag = true;
		}
		this.conn.close();
		return flag;
	}

	public Category queryById(String Cat_ID) {
		String sql = "select * from " + Category.BASE_TABLE_NAME + " where " + Category.BASE_CATEGORY_ID + " = '" + Cat_ID
				+ "'";
		ResultSet rs = this.conn.executeQuery(sql);
		Category category = getCategoryByResultSet(rs);
		this.conn.close();
		return category;
	}

	public Category queryByName(String Cat_name) {
		String sql = "select * from " + Category.BASE_TABLE_NAME + " where " + Category.BASE_CATEGORY_NAME + " = '"
				+ Cat_name + "'";
		ResultSet rs = this.conn.executeQuery(sql);
		Category category = getCategoryByResultSet(rs);
		this.conn.close();
		return category;
	}

	public ResultSet queryBySql(String query) {
		ResultSet resultSet = this.conn.executeQuery(query);
		return resultSet;
	}

	/**
	 * 从ResultSet获取Category对象
	 *
	 */
	private Category getCategoryByResultSet(ResultSet resultSet) {
		Category category = new Category();
		try {
			if (resultSet.next()) {
				category.set_id(resultSet.getLong(Category.BASE_CATEGORY_PHYSICAL_ID));
				category.setCat_ID(resultSet.getString(Category.BASE_CATEGORY_ID));
				category.setCat_name(resultSet.getString(Category.BASE_CATEGORY_NAME));
				category.setCat_desc(resultSet.getString(Category.BASE_CATEGORY_DESCRIPTION));
				category.setCat_addTime(resultSet.getString(Category.BASE_CATEGORY_ADD_TIME));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return category;
	}

	public void closeDBConnection() {
		this.conn.close();
	}
}
