package xuyihao.dao;

import java.sql.ResultSet;
import java.util.List;

import xuyihao.entity.Shops;

/**
 * Created by Xuyh at 2016/7/21 20:24.
 */
public interface ShopsDao {
	/**
	 * 写入数据库
	 *
	 * @param shop 商家对象
	 * @return
	 */
	public boolean saveShops(Shops shop);

	/**
	 * 从数据库删除数据
	 *
	 * @param shop 商家对象
	 * @return true成功， false失败
	 */
	public boolean deleteShops(String Shop_ID);

	/**
	 * 修改数据库中的内容
	 *
	 * @param shop 商家对象
	 * @return true成功， false失败
	 */
	public boolean updateShops(Shops shop);

	/**
	 * 修改数据库中的内容
	 *
	 * @param update 更新的sql语句
	 * @return true成功， false失败
	 */
	public boolean updateShops(String update);

	/**
	 * 查询
	 *
	 * @param Shop_ID 商家ID
	 * @return Resultset 查询结果游标
	 */
	public Shops queryById(String Shop_ID);

	/**
	 * 查询
	 *
	 * @param Shop_name 商家名称
	 * @return Resultset 查询结果游标
	 */
	public Shops queryByName(String Shop_name);

	/**
	 * 查询
	 *
	 * @param Acc_ID 负责用户ID
	 * @return Resultset 查询结果游标
	 */
	public List<Shops> queryByAccountId(String Acc_ID);

	/**
	 * 排序分页查询
	 * 
	 * @param OrderedBy 按照哪一个属性排序
	 * @param ascOrDesc 顺序还是倒序(1顺序, -1倒序)
	 * @param page 页号
	 * @param size 页大小
	 * @return
	 */
	public List<Shops> queryByLimitOrdered(String OrderedBy, int ascOrDesc, int page, int size);

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
