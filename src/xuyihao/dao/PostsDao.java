package xuyihao.dao;

import java.sql.ResultSet;
import java.util.List;

import xuyihao.entity.Posts;

/**
 * Created by Xuyh at 2016/7/21 20:23.
 */
public interface PostsDao {
	/**
	 * 写入数据库
	 *
	 * @param post 帖子对象
	 * @return
	 */
	public boolean savePosts(Posts post);

	/**
	 * 从数据库删除数据
	 *
	 * @param post 帖子对象
	 * @return true成功， false失败
	 */
	public boolean deletePosts(String Post_ID);

	/**
	 * 修改数据库中的内容
	 *
	 * @param post 帖子对象
	 * @return true成功， false失败
	 */
	public boolean updatePosts(Posts post);

	/**
	 * 修改数据库中的内容
	 *
	 * @param update 更新的sql语句
	 * @return true成功， false失败
	 */
	public boolean updatePosts(String update);

	/**
	 * 查询
	 *
	 * @param Post_ID 帖子ID
	 * @return
	 */
	public Posts queryById(String Post_ID);

	/**
	 * 查询
	 *
	 * @param Post_name 帖子名称
	 * @return
	 */
	public Posts queryByName(String Post_name);

	/**
	 * 查询
	 *
	 * @param Author_ID 原发布者ID
	 * @return
	 */
	public List<Posts> queryByAuthorId(String Author_ID);

	/**
	 * 查询
	 *
	 * @param Acc_ID 分享者ID
	 * @return
	 */
	public List<Posts> queryByAccountId(String Acc_ID);

	/**
	 * 查询
	 *
	 * @param sql 查询sql语句
	 * @return Resultset 查询结果游标
	 */
	public ResultSet queryBySql(String sql);
	
	/**
	 * 关闭数据库连接(对于返回ResultSet的查询，其余的不用)
	 * 
	 */
	public void closeDBConnection();
}
