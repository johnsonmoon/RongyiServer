package xuyihao.dao;

import java.sql.ResultSet;
import java.util.List;

import xuyihao.entity.LikeCrs;

/**
 * Created by Xuyh at 2016/7/21 20:22.
 */
public interface LikeCrsDao {
	/**
	 * 写入数据库
	 *
	 * @param likeCrs 打赏视频对象
	 * @return
	 */
	public boolean saveLikeCrs(LikeCrs likeCrs);

	/**
	 * 从数据库删除数据
	 *
	 * @param likeCrs 打赏视频对象
	 * @return true成功， false失败
	 */
	public boolean deleteLikeCrs(String Like_ID);

	/**
	 * 修改数据库中的内容
	 *
	 * @param likeCrs 打赏视频对象
	 * @return true成功， false失败
	 */
	public boolean updateLikeCrs(LikeCrs likeCrs);

	/**
	 * 修改数据库中的内容
	 *
	 * @param update 更新的sql语句
	 * @return true成功， false失败
	 */
	public boolean updateLikeCrs(String update);

	/**
	 * 查询
	 *
	 * @param Like_ID 打赏视频ID
	 * @return
	 */
	public LikeCrs queryById(String Like_ID);

	/**
	 * 查询
	 *
	 * @param Acc_ID 打赏者用户ID
	 * @return
	 */
	public List<LikeCrs> queryByAccountId(String Acc_ID);

	/**
	 * 查询
	 *
	 * @param Rep_ID 发布用户ID
	 * @return
	 */
	public List<LikeCrs> queryByReporterId(String Rep_ID);

	/**
	 * 查询
	 *
	 * @param Crs_ID 视频ID
	 * @return
	 */
	public List<LikeCrs> queryByCourseId(String Crs_ID);

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
