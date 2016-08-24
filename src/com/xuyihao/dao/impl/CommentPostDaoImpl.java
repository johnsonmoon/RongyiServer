package com.xuyihao.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xuyihao.dao.CommentPostDao;
import com.xuyihao.entity.CommentPost;
import com.xuyihao.tools.ConnDB;

/*
 * created by xuyihao on 2016/4/26
 * @describe 帖子评论相关数据库操作封装类
 * */
public class CommentPostDaoImpl implements CommentPostDao {
	private ConnDB conn = new ConnDB();

	public CommentPostDaoImpl() {
	}

	public void setConn(ConnDB conn) {
		this.conn = conn;
	}

	@Override
	public boolean saveCommentPost(CommentPost commentPost) {
		boolean flag = false;
		String sql = "insert into " + CommentPost.BASE_TABLE_NAME + " values(null, '" + commentPost.getComm_ID() + "', '"
				+ commentPost.getComm_desc() + "', '" + commentPost.getAcc_ID() + "', '" + commentPost.getRep_ID() + "', '"
				+ commentPost.getPost_ID() + "', '" + commentPost.getComm_addTime() + "')";
		if (this.conn.executeUpdate(sql) != 0) {
			flag = true;
		}
		this.conn.close();
		return flag;
	}

	@Override
	public boolean deleteCommentPost(String Comm_ID) {
		boolean flag = false;
		String sql = "delete from " + CommentPost.BASE_TABLE_NAME + " where " + CommentPost.BASE_COMMENTPOST_ID + " = '"
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
	public boolean updateCommentPost(CommentPost commentPost) {
		boolean flag = false;
		String sql = "update " + CommentPost.BASE_TABLE_NAME + " set " + CommentPost.BASE_COMMENTPOST_DESCRIPTION + " = '"
				+ commentPost.getComm_desc() + "', " + CommentPost.BASE_COMMENTPOST_ACCOUNT_ID + " = '"
				+ commentPost.getAcc_ID() + "', " + CommentPost.BASE_COMMENTPOST_PUBLICSHER_REP_ID + " = '"
				+ commentPost.getRep_ID() + "', " + CommentPost.BASE_COMMENTPOST_POST_ID + " = '" + commentPost.getPost_ID()
				+ "', " + CommentPost.BASE_COMMENTPOST_ADD_TIME + " = '" + commentPost.getComm_addTime() + "' where "
				+ CommentPost.BASE_COMMENTPOST_ID + " = '" + commentPost.getComm_ID() + "'";
		if (this.conn.executeUpdate(sql) != 0) {
			flag = true;
		}
		this.conn.close();
		return flag;
	}

	@Override
	public boolean updateCommentPost(String update) {
		boolean flag = false;
		if (this.conn.executeUpdate(update) != 0) {
			flag = true;
		}
		this.conn.close();
		return flag;
	}

	@Override
	public CommentPost queryById(String Comm_ID) {
		String sql = "select * from " + CommentPost.BASE_TABLE_NAME + " where " + CommentPost.BASE_COMMENTPOST_ID + " = '"
				+ Comm_ID + "'";
		ResultSet resultSet = this.conn.executeQuery(sql);
		CommentPost commentPost = getCommentPostFromResultSet(resultSet);
		this.conn.close();
		return commentPost;
	}

	@Override
	public List<CommentPost> queryByAccountId(String Acc_ID) {
		String sql = "select * from " + CommentPost.BASE_TABLE_NAME + " where " + CommentPost.BASE_COMMENTPOST_ACCOUNT_ID
				+ " = '" + Acc_ID + "'";
		ResultSet resultSet = this.conn.executeQuery(sql);
		List<CommentPost> commentPostList = getCommentPostListFromResultSet(resultSet);
		this.conn.close();
		return commentPostList;
	}

	@Override
	public List<CommentPost> queryByReporterId(String Rep_ID) {
		String sql = "select * from " + CommentPost.BASE_TABLE_NAME + " where "
				+ CommentPost.BASE_COMMENTPOST_PUBLICSHER_REP_ID + " = '" + Rep_ID + "'";
		ResultSet resultSet = this.conn.executeQuery(sql);
		List<CommentPost> commentPostList = getCommentPostListFromResultSet(resultSet);
		this.conn.close();
		return commentPostList;
	}

	@Override
	public List<CommentPost> queryByPostId(String Post_ID) {
		String sql = "select * from " + CommentPost.BASE_TABLE_NAME + " where " + CommentPost.BASE_COMMENTPOST_POST_ID
				+ " = '" + Post_ID + "'";
		ResultSet resultSet = this.conn.executeQuery(sql);
		List<CommentPost> commentPostList = getCommentPostListFromResultSet(resultSet);
		this.conn.close();
		return commentPostList;
	}

	@Override
	public ResultSet queryBySql(String query) {
		ResultSet resultSet = this.conn.executeQuery(query);
		return resultSet;
	}

	/**
	 * 通过ResultSet获取CommentPost对象
	 *
	 *
	 */
	private CommentPost getCommentPostFromResultSet(ResultSet resultSet) {
		CommentPost commentPost = new CommentPost();
		try {
			if (resultSet.next()) {
				commentPost.set_id(resultSet.getLong(CommentPost.BASE_COMMENTPOST_PHYSICAL_ID));
				commentPost.setComm_ID(resultSet.getString(CommentPost.BASE_COMMENTPOST_ID));
				commentPost.setComm_desc(resultSet.getString(CommentPost.BASE_COMMENTPOST_DESCRIPTION));
				commentPost.setAcc_ID(resultSet.getString(CommentPost.BASE_COMMENTPOST_ACCOUNT_ID));
				commentPost.setRep_ID(resultSet.getString(CommentPost.BASE_COMMENTPOST_PUBLICSHER_REP_ID));
				commentPost.setPost_ID(resultSet.getString(CommentPost.BASE_COMMENTPOST_POST_ID));
				commentPost.setComm_addTime(resultSet.getString(CommentPost.BASE_COMMENTPOST_ADD_TIME));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return commentPost;
	}

	/**
	 * 通过ResultSet获取CommentPost对象
	 *
	 *
	 */
	private List<CommentPost> getCommentPostListFromResultSet(ResultSet resultSet) {
		List<CommentPost> commentPostList = new ArrayList<>();
		try {
			while (resultSet.next()) {
				CommentPost commentPost = new CommentPost();
				commentPost.set_id(resultSet.getLong(CommentPost.BASE_COMMENTPOST_PHYSICAL_ID));
				commentPost.setComm_ID(resultSet.getString(CommentPost.BASE_COMMENTPOST_ID));
				commentPost.setComm_desc(resultSet.getString(CommentPost.BASE_COMMENTPOST_DESCRIPTION));
				commentPost.setAcc_ID(resultSet.getString(CommentPost.BASE_COMMENTPOST_ACCOUNT_ID));
				commentPost.setRep_ID(resultSet.getString(CommentPost.BASE_COMMENTPOST_PUBLICSHER_REP_ID));
				commentPost.setPost_ID(resultSet.getString(CommentPost.BASE_COMMENTPOST_POST_ID));
				commentPost.setComm_addTime(resultSet.getString(CommentPost.BASE_COMMENTPOST_ADD_TIME));
				commentPostList.add(commentPost);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return commentPostList;
	}

	@Override
	public void closeDBConnection() {
		this.conn.close();
	}
}
