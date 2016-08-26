package com.xuyihao.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xuyihao.common.DatabaseConnector;
import com.xuyihao.dao.LikePostDao;
import com.xuyihao.entity.LikePost;

/**
 * create by xuyihao on 2016/4/28
 * 
 * @describe 打赏帖子相关数据库操作封装类
 * @attention 打赏帖子的数据库操作，只有添加信息入表，没有删除操作，因为打赏是不可以撤销的
 */
public class LikePostDaoImpl implements LikePostDao {
	private DatabaseConnector conn = new DatabaseConnector();

	public LikePostDaoImpl() {
	}

	public void setConn(DatabaseConnector conn) {
		this.conn = conn;
	}

	@Override
	public boolean saveLikePost(LikePost likePost) {
		String sql = "insert into " + LikePost.BASE_TABLE_NAME + " values(null, '" + likePost.getLike_ID() + "', '"
				+ likePost.getAcc_ID() + "', '" + likePost.getRep_ID() + "', '" + likePost.getPost_ID() + "', "
				+ likePost.getLike_ryb() + ", '" + likePost.getLike_addTime() + "')";
		boolean flag = false;
		if (this.conn.executeUpdate(sql) != 0) {
			flag = true;
		}
		this.conn.close();
		return flag;
	}

	@Override
	public boolean deleteLikePost(String Like_ID) {
		boolean flag = false;
		String sql = "delete from " + LikePost.BASE_TABLE_NAME + " where " + LikePost.BASE_LIKE_POST_ID + " = '" + Like_ID
				+ "'";
		if (this.conn.executeUpdate(sql) == 0) {
			flag = false;
		} else {
			flag = true;
		}
		this.conn.close();
		return flag;
	}

	@Override
	public boolean updateLikePost(LikePost likePost) {
		boolean flag = false;
		String sql = "update " + LikePost.BASE_TABLE_NAME + " set " + LikePost.BASE_LIKE_POST_ACCOUNT_ID + " = '"
				+ likePost.getAcc_ID() + "', " + LikePost.BASE_LIKE_POST_PUBLISHER_REP_ID + " = '" + likePost.getRep_ID()
				+ "', " + LikePost.BASE_LIKE_POST_POST_ID + " = '" + likePost.getPost_ID() + "', "
				+ LikePost.BASE_LIKE_POST_RYB_COUNT + " = " + likePost.getLike_ryb() + ", " + LikePost.BASE_LIKE_POST_ADD_TIME
				+ " = '" + likePost.getLike_addTime() + "' where " + LikePost.BASE_LIKE_POST_ID + " = '" + likePost.getLike_ID()
				+ "'";
		if (this.conn.executeUpdate(sql) != 0) {
			flag = true;
		}
		this.conn.close();
		return flag;
	}

	@Override
	public boolean updateLikePost(String update) {
		boolean flag = false;
		if (this.conn.executeUpdate(update) != 0) {
			flag = true;
		}
		this.conn.close();
		return flag;
	}

	@Override
	public LikePost queryById(String Like_ID) {
		String sql = "select * from " + LikePost.BASE_TABLE_NAME + " where " + LikePost.BASE_LIKE_POST_ID + " = '" + Like_ID
				+ "'";
		ResultSet resultSet = this.conn.executeQuery(sql);
		LikePost likePost = getLikePostFromResultSet(resultSet);
		this.conn.close();
		return likePost;
	}

	@Override
	public List<LikePost> queryByAccountId(String Acc_ID) {
		String sql = "select * from " + LikePost.BASE_TABLE_NAME + " where " + LikePost.BASE_LIKE_POST_ACCOUNT_ID + " = '"
				+ Acc_ID + "'";
		ResultSet resultSet = this.conn.executeQuery(sql);
		List<LikePost> likePostList = getLikePostListFromResultSet(resultSet);
		this.conn.close();
		return likePostList;
	}

	@Override
	public List<LikePost> queryByReporterId(String Rep_ID) {
		String sql = "select * from " + LikePost.BASE_TABLE_NAME + " where " + LikePost.BASE_LIKE_POST_PUBLISHER_REP_ID
				+ " = '" + Rep_ID + "'";
		ResultSet resultSet = this.conn.executeQuery(sql);
		List<LikePost> likePostList = getLikePostListFromResultSet(resultSet);
		this.conn.close();
		return likePostList;
	}

	@Override
	public List<LikePost> queryByPostId(String Post_ID) {
		String sql = "select * from " + LikePost.BASE_TABLE_NAME + " where " + LikePost.BASE_LIKE_POST_ID + " = '" + Post_ID
				+ "'";
		ResultSet resultSet = this.conn.executeQuery(sql);
		List<LikePost> likePostList = getLikePostListFromResultSet(resultSet);
		this.conn.close();
		return likePostList;
	}

	@Override
	public ResultSet queryBySql(String sql) {
		ResultSet resultSet = this.conn.executeQuery(sql);
		return resultSet;
	}

	/**
	 * 通过ResultSet获取对象
	 *
	 *
	 */
	private LikePost getLikePostFromResultSet(ResultSet resultSet) {
		LikePost likePost = new LikePost();
		try {
			if (resultSet.next()) {
				likePost.set_id(resultSet.getLong(LikePost.BASE_LIKE_POST_PHYSICAL_ID));
				likePost.setLike_ID(resultSet.getString(LikePost.BASE_LIKE_POST_ID));
				likePost.setAcc_ID(resultSet.getString(LikePost.BASE_LIKE_POST_ACCOUNT_ID));
				likePost.setRep_ID(resultSet.getString(LikePost.BASE_LIKE_POST_PUBLISHER_REP_ID));
				likePost.setPost_ID(resultSet.getString(LikePost.BASE_LIKE_POST_POST_ID));
				likePost.setLike_ryb(resultSet.getInt(LikePost.BASE_LIKE_POST_RYB_COUNT));
				likePost.setLike_addTime(resultSet.getString(LikePost.BASE_LIKE_POST_ADD_TIME));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return likePost;
	}

	/**
	 * 通过ResultSet获取对象
	 *
	 *
	 */
	private List<LikePost> getLikePostListFromResultSet(ResultSet resultSet) {
		List<LikePost> likePostList = new ArrayList<>();
		try {
			while (resultSet.next()) {
				LikePost likePost = new LikePost();
				likePost.set_id(resultSet.getLong(LikePost.BASE_LIKE_POST_PHYSICAL_ID));
				likePost.setLike_ID(resultSet.getString(LikePost.BASE_LIKE_POST_ID));
				likePost.setAcc_ID(resultSet.getString(LikePost.BASE_LIKE_POST_ACCOUNT_ID));
				likePost.setRep_ID(resultSet.getString(LikePost.BASE_LIKE_POST_PUBLISHER_REP_ID));
				likePost.setPost_ID(resultSet.getString(LikePost.BASE_LIKE_POST_POST_ID));
				likePost.setLike_ryb(resultSet.getInt(LikePost.BASE_LIKE_POST_RYB_COUNT));
				likePost.setLike_addTime(resultSet.getString(LikePost.BASE_LIKE_POST_ADD_TIME));
				likePostList.add(likePost);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return likePostList;
	}

	@Override
	public void closeDBConnection() {
		this.conn.close();
	}
}
