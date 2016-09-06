package xuyihao.dao;

import java.sql.ResultSet;
import java.util.List;

import xuyihao.entity.LikePost;

/**
 * Created by Xuyh at 2016/7/21 20:23.
 */
public interface LikePostDao {
	/**
	 * 写入数据库
	 *
	 * @param likePost 打赏帖子对象
	 * @return
	 */
	public boolean saveLikePost(LikePost likePost);

	/**
	 * 从数据库删除数据
	 *
	 * @param likePost 打赏帖子对象
	 * @return true成功， false失败
	 */
	public boolean deleteLikePost(String Like_ID);

	/**
	 * 修改数据库中的内容
	 *
	 * @param likePost 打赏帖子对象
	 * @return true成功， false失败
	 */
	public boolean updateLikePost(LikePost likePost);

	/**
	 * 修改数据库中的内容
	 *
	 * @param update 更新的sql语句
	 * @return true成功， false失败
	 */
	public boolean updateLikePost(String update);

	/**
	 * 查询
	 *
	 * @param Like_ID 打赏帖子ID
	 * @return Resultset 查询结果游标
	 */
	public LikePost queryById(String Like_ID);

	/**
	 * 查询
	 *
	 * @param Acc_ID 打赏者用户ID
	 * @return Resultset 查询结果游标
	 */
	public List<LikePost> queryByAccountId(String Acc_ID);

	/**
	 * 查询
	 *
	 * @param Rep_ID 发布用户ID
	 * @return Resultset 查询结果游标
	 */
	public List<LikePost> queryByReporterId(String Rep_ID);

	/**
	 * 查询
	 *
	 * @param Post_ID 帖子ID
	 * @return Resultset 查询结果游标
	 */
	public List<LikePost> queryByPostId(String Post_ID);

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
