package xuyihao.dao;

import java.sql.ResultSet;
import java.util.List;

import xuyihao.entity.Address;

/**
 * Created by Xuyh on 2016/7/21.
 */
public interface AddressDao {
	/**
	 * 写入数据库
	 *
	 * @param address 地址对象
	 * @return
	 */
	public boolean saveAddress(Address address);

	/**
	 * 从数据库删除数据
	 *
	 * @param address 地址对象
	 * @return true成功， false失败
	 */
	public boolean deleteAddress(String Add_ID);

	/**
	 * 修改数据库中的数据
	 *
	 * @param address 地址对象
	 * @return true成功， false失败
	 */
	public boolean updateAddress(Address address);

	/**
	 * 通过传入sql语句改数据
	 *
	 * @param update 更新的sql语句
	 * @return true成功， false失败
	 */
	public boolean updateAddress(String update);

	/**
	 * 查询
	 *
	 * @param Acc_ID 账号ID(查询账号的收货地址集)
	 * @return 
	 */
	public List<Address> queryByAccountId(String Acc_ID);

	/**
	 * 查询
	 *
	 * @param Add_ID 地址ID
	 * @return 
	 */
	public Address queryById(String Add_ID);

	/**
	 * 查询
	 *
	 * @param query 查询sql语句
	 * @return 
	 */
	public ResultSet queryBySql(String query);
	
	/**
	 * 关闭数据库连接(对于返回ResultSet的查询，其余的不用)
	 * 
	 */
	public void closeDBConnection();
}
