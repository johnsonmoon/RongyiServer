package com.xuyihao.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xuyihao.common.DatabaseConnector;
import com.xuyihao.dao.PostsDao;
import com.xuyihao.entity.Posts;

/**
 * created by xuyihao on 2016/4/26
 * 
 * @describe 帖子(帖子教程)相关数据库操作封装类
 */
public class PostsDaoImpl implements PostsDao {
	private DatabaseConnector conn = new DatabaseConnector();

	public PostsDaoImpl() {
	}

	public void setConn(DatabaseConnector conn) {
		this.conn = conn;
	}

	@Override
	public boolean savePosts(Posts post) {
		String sql = "insert into " + Posts.BASE_TABLE_NAME + " values(null, '" + post.getPost_ID() + "', '"
				+ post.getPost_name() + "', '" + post.getPost_route() + "', '" + post.getAcc_ID() + "', '" + post.getAuthor_ID()
				+ "', " + post.getPost_rep() + ", " + post.getPost_comm() + ", " + post.getPost_like() + ", DATE_FORMAT('"
				+ post.getPost_addTime() + "', '%Y-%m-%d %H:%i:%s'))";
		boolean flag = false;
		if (this.conn.executeUpdate(sql) != 0) {
			flag = true;
		}
		this.conn.close();
		return flag;
	}

	@Override
	public boolean deletePosts(String Post_ID) {
		boolean flag = false;
		String sql = "delete from " + Posts.BASE_TABLE_NAME + " where " + Posts.BASE_POSTS_ID + " = '" + Post_ID + "'";
		if (this.conn.executeUpdate(sql) == 0) {
			flag = false;
		} else {
			flag = true;
		}
		this.conn.close();
		return flag;
	}

	@Override
	public boolean updatePosts(Posts post) {
		boolean flag = false;
		String sql = "update " + Posts.BASE_TABLE_NAME + " set " + Posts.BASE_POSTS_NAME + " = '" + post.getPost_name()
				+ "', " + Posts.BASE_POSTS_ROUTE + " = '" + post.getPost_route() + "', " + Posts.BASE_POSTS_ACCOUNT_ID + " = '"
				+ post.getAcc_ID() + "', " + Posts.BASE_POSTS_AUTHOR_ACCOUNT_ID + " = '" + post.getAuthor_ID() + "', "
				+ Posts.BASE_POSTS_REPOST_COUNT + " = " + post.getPost_rep() + ", " + Posts.BASE_POSTS_COMMON_COUNT + " = "
				+ post.getPost_comm() + ", " + Posts.BASE_POSTS_LIKE_COUNT + " = " + post.getPost_like() + ", "
				+ Posts.BASE_POSTS_ADD_TIME + " = '" + post.getPost_addTime() + "' where " + Posts.BASE_POSTS_ID + " = '"
				+ post.getPost_ID() + "'";
		if (this.conn.executeUpdate(sql) != 0) {
			flag = true;
		}
		this.conn.close();
		return flag;
	}

	@Override
	public boolean updatePosts(String update) {
		boolean flag = false;
		if (this.conn.executeUpdate(update) != 0) {
			flag = true;
		}
		this.conn.close();
		return flag;
	}

	@Override
	public Posts queryById(String Post_ID) {
		String sql = "select * from " + Posts.BASE_TABLE_NAME + " where " + Posts.BASE_POSTS_ID + " = '" + Post_ID + "'";
		ResultSet resultSet = this.conn.executeQuery(sql);
		Posts posts = getPostsFromResultSet(resultSet);
		this.conn.close();
		return posts;
	}

	@Override
	public Posts queryByName(String Post_name) {
		String sql = "select * from " + Posts.BASE_TABLE_NAME + " where " + Posts.BASE_POSTS_NAME + " = '" + Post_name
				+ "'";
		ResultSet resultSet = this.conn.executeQuery(sql);
		Posts posts = getPostsFromResultSet(resultSet);
		this.conn.close();
		return posts;
	}

	@Override
	public List<Posts> queryByAuthorId(String Author_ID) {
		String sql = "select * from " + Posts.BASE_TABLE_NAME + " where " + Posts.BASE_POSTS_AUTHOR_ACCOUNT_ID + " = '"
				+ Author_ID + "'";
		ResultSet resultSet = this.conn.executeQuery(sql);
		List<Posts> postsList = getPostsListFromResultSet(resultSet);
		this.conn.close();
		return postsList;
	}

	@Override
	public List<Posts> queryByAccountId(String Acc_ID) {
		String sql = "select * from " + Posts.BASE_TABLE_NAME + " where " + Posts.BASE_POSTS_ACCOUNT_ID + " = '" + Acc_ID
				+ "'";
		ResultSet resultSet = this.conn.executeQuery(sql);
		List<Posts> postsList = getPostsListFromResultSet(resultSet);
		this.conn.close();
		return postsList;
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
	private Posts getPostsFromResultSet(ResultSet resultSet) {
		Posts posts = new Posts();
		try {
			if (resultSet.next()) {
				posts.set_id(resultSet.getLong(Posts.BASE_POSTS_PHYSICAL_ID));
				posts.setPost_ID(resultSet.getString(Posts.BASE_POSTS_ID));
				posts.setPost_name(resultSet.getString(Posts.BASE_POSTS_NAME));
				posts.setPost_route(resultSet.getString(Posts.BASE_POSTS_ROUTE));
				posts.setAcc_ID(resultSet.getString(Posts.BASE_POSTS_ACCOUNT_ID));
				posts.setAuthor_ID(resultSet.getString(Posts.BASE_POSTS_AUTHOR_ACCOUNT_ID));
				posts.setPost_rep(resultSet.getInt(Posts.BASE_POSTS_REPOST_COUNT));
				posts.setPost_comm(resultSet.getInt(Posts.BASE_POSTS_COMMON_COUNT));
				posts.setPost_like(resultSet.getInt(Posts.BASE_POSTS_LIKE_COUNT));
				posts.setPost_addTime(resultSet.getString(Posts.BASE_POSTS_ADD_TIME));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return posts;
	}

	/**
	 * 通过ResultSet获取对象
	 *
	 *
	 */
	private List<Posts> getPostsListFromResultSet(ResultSet resultSet) {
		List<Posts> postsList = new ArrayList<>();
		try {
			while (resultSet.next()) {
				Posts posts = new Posts();
				posts.set_id(resultSet.getLong(Posts.BASE_POSTS_PHYSICAL_ID));
				posts.setPost_ID(resultSet.getString(Posts.BASE_POSTS_ID));
				posts.setPost_name(resultSet.getString(Posts.BASE_POSTS_NAME));
				posts.setPost_route(resultSet.getString(Posts.BASE_POSTS_ROUTE));
				posts.setAcc_ID(resultSet.getString(Posts.BASE_POSTS_ACCOUNT_ID));
				posts.setAuthor_ID(resultSet.getString(Posts.BASE_POSTS_AUTHOR_ACCOUNT_ID));
				posts.setPost_rep(resultSet.getInt(Posts.BASE_POSTS_REPOST_COUNT));
				posts.setPost_comm(resultSet.getInt(Posts.BASE_POSTS_COMMON_COUNT));
				posts.setPost_like(resultSet.getInt(Posts.BASE_POSTS_LIKE_COUNT));
				posts.setPost_addTime(resultSet.getString(Posts.BASE_POSTS_ADD_TIME));
				postsList.add(posts);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return postsList;
	}

	@Override
	public void closeDBConnection() {
		this.conn.close();
	}
}
