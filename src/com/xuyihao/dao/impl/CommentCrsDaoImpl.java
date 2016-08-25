package com.xuyihao.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xuyihao.common.ConnDB;
import com.xuyihao.dao.CommentCrsDao;
import com.xuyihao.entity.CommentCrs;

/*
 * created by xuyihao on 2016/4/26
 * @describe 视频评论相关数据库操作封装类
 * */
public class CommentCrsDaoImpl implements CommentCrsDao {
	private ConnDB conn = new ConnDB();

	public CommentCrsDaoImpl() {
	}

	public void setConn(ConnDB conn) {
		this.conn = conn;
	}

	@Override
	public boolean saveCommentCrs(CommentCrs commentCrs) {
		boolean flag = false;
		String sql = "insert into " + CommentCrs.BASE_TABLE_NAME + " values(null, '" + commentCrs.getComm_ID() + "', '"
				+ commentCrs.getComm_desc() + "', '" + commentCrs.getAcc_ID() + "', '" + commentCrs.getRep_ID() + "', '"
				+ commentCrs.getCrs_ID() + "', '" + commentCrs.getComm_addTime() + "')";
		if (this.conn.executeUpdate(sql) != 0) {
			flag = true;
		}
		this.conn.close();
		return flag;
	}

	@Override
	public boolean deleteCommentCrs(String Comm_ID) {
		boolean flag = false;
		String sql = "delete from " + CommentCrs.BASE_TABLE_NAME + " where " + CommentCrs.BASE_COMMENTCOURSE_ID + " = '"
				+ Comm_ID + "'";
		if (this.conn.executeUpdate(sql) == 0) {
			flag = false;
		} else {
			flag = true;
		}
		this.conn.close();
		return flag;
	}

	@Override
	public boolean updateCommentCrs(CommentCrs commentCrs) {
		boolean flag = false;
		String sql = "update " + CommentCrs.BASE_TABLE_NAME + " set " + CommentCrs.BASE_COMMENTCOURSE_DESCRIPTION + " = '"
				+ commentCrs.getComm_desc() + "', " + CommentCrs.BASE_COMMENTCOURSE_ACCOUNT_ID + " = '" + commentCrs.getAcc_ID()
				+ "', " + CommentCrs.BASE_COMMENTCOURSE_PUBLISHER_REP_ID + " = '" + commentCrs.getRep_ID() + "', "
				+ CommentCrs.BASE_COMMENTCOURSE_COURSE_ID + " = '" + commentCrs.getCrs_ID() + "', "
				+ CommentCrs.BASE_COMMENTCOURSE_ADD_TIME + " = '" + commentCrs.getComm_addTime() + "' where "
				+ CommentCrs.BASE_COMMENTCOURSE_ID + " = '" + commentCrs.getComm_ID() + "'";
		if (this.conn.executeUpdate(sql) != 0) {
			flag = true;
		}
		this.conn.close();
		return flag;
	}

	@Override
	public boolean updateCommentCrs(String update) {
		boolean flag = false;
		if (this.conn.executeUpdate(update) != 0) {
			flag = true;
		}
		this.conn.close();
		return flag;
	}

	@Override
	public CommentCrs queryById(String Comm_ID) {
		String sql = "select * from " + CommentCrs.BASE_TABLE_NAME + " where " + CommentCrs.BASE_COMMENTCOURSE_ID + " = '"
				+ Comm_ID + "'";
		ResultSet resultSet = this.conn.executeQuery(sql);
		CommentCrs commentCrs = getCommentCrsFromResultSet(resultSet);
		this.conn.close();
		return commentCrs;
	}

	@Override
	public List<CommentCrs> queryByAccountId(String Acc_ID) {
		String sql = "select * from " + CommentCrs.BASE_TABLE_NAME + " where " + CommentCrs.BASE_COMMENTCOURSE_ACCOUNT_ID
				+ " = '" + Acc_ID + "'";
		ResultSet resultSet = this.conn.executeQuery(sql);
		List<CommentCrs> commentCrsList = getCommentCrsListFromResultSet(resultSet);
		this.conn.close();
		return commentCrsList;
	}

	@Override
	public List<CommentCrs> queryByReporterId(String Rep_ID) {
		String sql = "select * from " + CommentCrs.BASE_TABLE_NAME + " where "
				+ CommentCrs.BASE_COMMENTCOURSE_PUBLISHER_REP_ID + " = '" + Rep_ID + "'";
		ResultSet resultSet = this.conn.executeQuery(sql);
		List<CommentCrs> commentCrsList = getCommentCrsListFromResultSet(resultSet);
		this.conn.close();
		return commentCrsList;
	}

	@Override
	public List<CommentCrs> queryByCourseId(String Crs_ID) {
		String sql = "select * from " + CommentCrs.BASE_TABLE_NAME + " where " + CommentCrs.BASE_COMMENTCOURSE_COURSE_ID
				+ " = '" + Crs_ID + "'";
		ResultSet resultSet = this.conn.executeQuery(sql);
		List<CommentCrs> commentCrsList = getCommentCrsListFromResultSet(resultSet);
		this.conn.close();
		return commentCrsList;
	}

	@Override
	public ResultSet queryBySql(String query) {
		ResultSet resultSet = this.conn.executeQuery(query);
		return resultSet;
	}

	/**
	 * 通过ResultSet获取CommentCrs对象
	 *
	 *
	 */
	private CommentCrs getCommentCrsFromResultSet(ResultSet resultSet) {
		CommentCrs commentCrs = new CommentCrs();
		try {
			if (resultSet.next()) {
				commentCrs.set_id(resultSet.getLong(CommentCrs.BASE_COMMENTCOURSE_PHYSICAL_ID));
				commentCrs.setComm_ID(resultSet.getString(CommentCrs.BASE_COMMENTCOURSE_ID));
				commentCrs.setComm_desc(resultSet.getString(CommentCrs.BASE_COMMENTCOURSE_DESCRIPTION));
				commentCrs.setAcc_ID(resultSet.getString(CommentCrs.BASE_COMMENTCOURSE_ACCOUNT_ID));
				commentCrs.setRep_ID(resultSet.getString(CommentCrs.BASE_COMMENTCOURSE_PUBLISHER_REP_ID));
				commentCrs.setCrs_ID(resultSet.getString(CommentCrs.BASE_COMMENTCOURSE_COURSE_ID));
				commentCrs.setComm_addTime(resultSet.getString(CommentCrs.BASE_COMMENTCOURSE_ADD_TIME));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return commentCrs;
	}

	/**
	 * 通过ResultSet获取CommentCrs对象
	 *
	 *
	 */
	private List<CommentCrs> getCommentCrsListFromResultSet(ResultSet resultSet) {
		List<CommentCrs> commentCrsList = new ArrayList<>();
		try {
			while (resultSet.next()) {
				CommentCrs commentCrs = new CommentCrs();
				commentCrs.set_id(resultSet.getLong(CommentCrs.BASE_COMMENTCOURSE_PHYSICAL_ID));
				commentCrs.setComm_ID(resultSet.getString(CommentCrs.BASE_COMMENTCOURSE_ID));
				commentCrs.setComm_desc(resultSet.getString(CommentCrs.BASE_COMMENTCOURSE_DESCRIPTION));
				commentCrs.setAcc_ID(resultSet.getString(CommentCrs.BASE_COMMENTCOURSE_ACCOUNT_ID));
				commentCrs.setRep_ID(resultSet.getString(CommentCrs.BASE_COMMENTCOURSE_PUBLISHER_REP_ID));
				commentCrs.setCrs_ID(resultSet.getString(CommentCrs.BASE_COMMENTCOURSE_COURSE_ID));
				commentCrs.setComm_addTime(resultSet.getString(CommentCrs.BASE_COMMENTCOURSE_ADD_TIME));
				commentCrsList.add(commentCrs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return commentCrsList;
	}

	@Override
	public void closeDBConnection() {
		this.conn.close();
	}
}
