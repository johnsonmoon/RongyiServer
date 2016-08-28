package com.xuyihao.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xuyihao.common.DatabaseConnector;
import com.xuyihao.dao.CommentProductDao;
import com.xuyihao.entity.CommentProduct;

/**
 * created by xuyihao on 2016/4/30
 * 
 * @description 评论产品的数据库操作工具类
 * @attention 产品评论后只能修改评论，不能删除，所以不添加删除方法
 */
public class CommentProductDaoImpl implements CommentProductDao {
	private DatabaseConnector conn = new DatabaseConnector();

	public CommentProductDaoImpl() {
	}

	public void setConn(DatabaseConnector conn) {
		this.conn = conn;
	}

	@Override
	public boolean saveCommentProduct(CommentProduct commentProduct) {
		boolean flag = false;
		String sql = "insert into " + CommentProduct.BASE_TABLE_NAME + " values(null, '" + commentProduct.getComm_ID()
				+ "', '" + commentProduct.getComm_desc() + "', '" + commentProduct.getAcc_ID() + "', '"
				+ commentProduct.getProd_ID() + "', '" + commentProduct.getOrd_ID() + "', DATE_FORMAT('"
				+ commentProduct.getComm_addTime() + "', '%Y-%m-%d %H:%i:%s'))";
		if (this.conn.executeUpdate(sql) != 0) {
			flag = true;
		}
		this.conn.close();
		return flag;
	}

	@Override
	public boolean deleteCommentProduct(String Comm_ID) {
		boolean flag = false;
		String sql = "delete from " + CommentProduct.BASE_TABLE_NAME + " where " + CommentProduct.BASE_COMMENTPRODUCT_ID
				+ " = '" + Comm_ID + "'";
		if (this.conn.executeUpdate(sql) != 0) {
			flag = true;
		}
		this.conn.close();
		return flag;
	}

	@Override
	public boolean updateCommentProduct(CommentProduct commentProduct) {
		boolean flag = false;
		String sql = "update " + CommentProduct.BASE_TABLE_NAME + " set " + CommentProduct.BASE_COMMENTPRODUCT_DESCRIPTION
				+ " = '" + commentProduct.getComm_desc() + "', " + CommentProduct.BASE_COMMENTPRODUCT_ACCOUNT_ID + " = '"
				+ commentProduct.getAcc_ID() + "', " + CommentProduct.BASE_COMMENTPRODUCT_PRODUCT_ID + " = '"
				+ commentProduct.getProd_ID() + "', " + CommentProduct.BASE_COMMENTPRODUCT_ORDER_ID + " = '"
				+ commentProduct.getOrd_ID() + "', " + CommentProduct.BASE_COMMENTPRODUCT_ADD_TIME + " = '"
				+ commentProduct.getComm_addTime() + "' where " + CommentProduct.BASE_COMMENTPRODUCT_ID + " = '"
				+ commentProduct.getComm_ID() + "'";
		if (this.conn.executeUpdate(sql) == 0) {
			flag = false;
		} else {
			flag = true;
		}
		this.conn.close();
		return flag;
	}

	@Override
	public boolean updateCommentProduct(String update) {
		boolean flag = false;
		if (this.conn.executeUpdate(update) == 0) {
			flag = false;
		} else {
			flag = true;
		}
		this.conn.close();
		return flag;
	}

	@Override
	public CommentProduct queryById(String Comm_ID) {
		String sql = "select * from " + CommentProduct.BASE_TABLE_NAME + " where " + CommentProduct.BASE_COMMENTPRODUCT_ID
				+ " = '" + Comm_ID + "'";
		ResultSet resultSet = this.conn.executeQuery(sql);
		CommentProduct commentProduct = getCommentProductFromResultSet(resultSet);
		this.conn.close();
		return commentProduct;
	}

	@Override
	public List<CommentProduct> queryByAccountId(String Acc_ID) {
		String sql = "select * from " + CommentProduct.BASE_TABLE_NAME + " where "
				+ CommentProduct.BASE_COMMENTPRODUCT_ACCOUNT_ID + " = '" + Acc_ID + "'";
		ResultSet resultSet = this.conn.executeQuery(sql);
		List<CommentProduct> commentProductList = getCommentListProductFromResultSet(resultSet);
		this.conn.close();
		return commentProductList;
	}

	@Override
	public List<CommentProduct> queryByProductId(String Prod_ID) {
		String sql = "select * from " + CommentProduct.BASE_TABLE_NAME + " where "
				+ CommentProduct.BASE_COMMENTPRODUCT_PRODUCT_ID + " = '" + Prod_ID + "'";
		ResultSet resultSet = this.conn.executeQuery(sql);
		List<CommentProduct> commentProductList = getCommentListProductFromResultSet(resultSet);
		this.conn.close();
		return commentProductList;
	}

	@Override
	public ResultSet queryBySql(String query) {
		ResultSet resultSet = this.conn.executeQuery(query);
		return resultSet;
	}

	/**
	 * 通过ResultSet获取CommentProduct对象
	 *
	 *
	 */
	private CommentProduct getCommentProductFromResultSet(ResultSet resultSet) {
		CommentProduct commentProduct = new CommentProduct();
		try {
			if (resultSet.next()) {
				commentProduct.set_id(resultSet.getLong(CommentProduct.BASE_COMMENTPRODUCT_PHYSICAL_ID));
				commentProduct.setComm_ID(resultSet.getString(CommentProduct.BASE_COMMENTPRODUCT_ID));
				commentProduct.setComm_desc(resultSet.getString(CommentProduct.BASE_COMMENTPRODUCT_DESCRIPTION));
				commentProduct.setAcc_ID(resultSet.getString(CommentProduct.BASE_COMMENTPRODUCT_ACCOUNT_ID));
				commentProduct.setProd_ID(resultSet.getString(CommentProduct.BASE_COMMENTPRODUCT_PRODUCT_ID));
				commentProduct.setOrd_ID(resultSet.getString(CommentProduct.BASE_COMMENTPRODUCT_ORDER_ID));
				commentProduct.setComm_addTime(resultSet.getString(CommentProduct.BASE_COMMENTPRODUCT_ADD_TIME));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return commentProduct;
	}

	/**
	 * 通过ResultSet获取CommentProduct对象
	 *
	 *
	 */
	private List<CommentProduct> getCommentListProductFromResultSet(ResultSet resultSet) {
		List<CommentProduct> commentProductList = new ArrayList<>();
		try {
			while (resultSet.next()) {
				CommentProduct commentProduct = new CommentProduct();
				commentProduct.set_id(resultSet.getLong(CommentProduct.BASE_COMMENTPRODUCT_PHYSICAL_ID));
				commentProduct.setComm_ID(resultSet.getString(CommentProduct.BASE_COMMENTPRODUCT_ID));
				commentProduct.setComm_desc(resultSet.getString(CommentProduct.BASE_COMMENTPRODUCT_DESCRIPTION));
				commentProduct.setAcc_ID(resultSet.getString(CommentProduct.BASE_COMMENTPRODUCT_ACCOUNT_ID));
				commentProduct.setProd_ID(resultSet.getString(CommentProduct.BASE_COMMENTPRODUCT_PRODUCT_ID));
				commentProduct.setOrd_ID(resultSet.getString(CommentProduct.BASE_COMMENTPRODUCT_ORDER_ID));
				commentProduct.setComm_addTime(resultSet.getString(CommentProduct.BASE_COMMENTPRODUCT_ADD_TIME));
				commentProductList.add(commentProduct);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return commentProductList;
	}

	@Override
	public void closeDBConnection() {
		this.conn.close();
	}
}
