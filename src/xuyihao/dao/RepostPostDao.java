package xuyihao.dao;

import java.sql.ResultSet;
import java.util.List;

/**
 * Created by Xuyh at 16-8-10 下午11:29
 */
public interface RepostPostDao {
	/**
	 *
	 * @param Acc_Id
	 * @param Rep_Id
	 * @param Post_Id
	 * @return
	 */
	public boolean saveRepostPost(String Acc_Id, String Rep_Id, String Post_Id, String Rep_addTime);

	/**
	 *
	 * @param Acc_Id
	 * @param Rep_Id
	 * @param PostId
	 * @return
	 */
	public boolean deleteRepostPost(String Acc_Id, String Rep_Id, String PostId);

	/**
	 *
	 * @param Acc_Id
	 * @param Rep_Id
	 * @return
	 */
	public boolean deleteRepostPost(String Acc_Id, String Rep_Id);

	/**
	 *
	 * @param Acc_Id
	 * @return
	 */
	public boolean deleteRepostPostAll(String Acc_Id);

	/**
	 *
	 * @param sql
	 * @return
	 */
	public boolean deleteBySql(String sql);

	/**
	 * 查询账户所有分享(不限制被分享者)
	 *
	 * @param Acc_Id
	 * @return 返回账户的所有分享帖子ID
	 */
	public List<String> queryByAccountId(String Acc_Id);

	/**
	 * 查询账户所有分享(单一被分享者)
	 * 
	 * @param Acc_Id
	 * @param Rep_Id
	 * @return 返回账户的所有分享帖子ID
	 */
	public List<String> query(String Acc_Id, String Rep_Id);

	/**
	 *
	 * @param sql
	 * @return
	 */
	public ResultSet queryBySql(String sql);

	/**
	 * 关闭数据库连接(对于返回ResultSet的查询，其余的不用)
	 * 
	 */
	public void closeDBConnection();
}
