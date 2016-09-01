package com.xuyihao.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xuyihao.common.DatabaseConnector;
import com.xuyihao.dao.AddressDao;
import com.xuyihao.entity.Address;

/**
 * created by xuyihao on 2016/4/29
 * 
 * @description 收货地址的数据库操作工具类
 */
public class AddressDaoImpl implements AddressDao {
	private DatabaseConnector conn = new DatabaseConnector();

	public AddressDaoImpl() {
	}

	public void setConn(DatabaseConnector conn) {
		this.conn = conn;
	}

	public boolean saveAddress(Address address) {
		boolean flag = false;
		String sql = "insert into " + Address.BASE_TABLE_NAME + " values(null, '" + address.getAdd_ID() + "', '"
				+ address.getAdd_info() + "', '" + address.getAcc_ID() + "', '" + address.getConsign() + "', '"
				+ address.getCon_tel() + "', DATE_FORMAT('" + address.getAdd_addTime() + "', '%Y-%m-%d %H:%i:%s'))";
		if (this.conn.executeUpdate(sql) != 0) {
			flag = true;
		}
		this.conn.close();
		return flag;
	}

	public boolean deleteAddress(String Add_ID) {
		boolean flag = false;
		String sql = "delete from " + Address.BASE_TABLE_NAME + " where " + Address.BASE_ADDRESS_ID + " = '" + Add_ID + "'";
		if (this.conn.executeUpdate(sql) == 0) {
			flag = false;
		} else {
			flag = true;
		}
		this.conn.close();
		return flag;
	}

	public boolean updateAddress(Address address) {
		boolean flag = false;
		String sql = "update " + Address.BASE_TABLE_NAME + " set " + Address.BASE_ADDRESS_INFORMATION + " = '"
				+ address.getAdd_info() + "', " + Address.BASE_ADDRESS_CONSIGN + " = '" + address.getConsign() + "', "
				+ Address.BASE_ADDRESS_CONSIGN_TELEPHONE + " = '" + address.getCon_tel() + "', " + Address.BASE_ADDRESS_ADD_TIME
				+ " = '" + address.getAdd_addTime() + "' where " + Address.BASE_ADDRESS_ID + " = '" + address.getAdd_ID() + "'";
		if (this.conn.executeUpdate(sql) == 0) {
			flag = false;
		} else {
			flag = true;
		}
		this.conn.close();
		return flag;
	}

	public boolean updateAddress(String update) {
		boolean flag = false;
		String sql = update;
		if (this.conn.executeUpdate(sql) == 0) {
			flag = false;
		} else {
			flag = true;
		}
		this.conn.close();
		return flag;
	}

	public List<Address> queryByAccountId(String Acc_ID) {
		String sql = "select * from " + Address.BASE_TABLE_NAME + " where " + Address.BASE_ADDRESS_ID + " = '" + Acc_ID
				+ "'";
		ResultSet rs = this.conn.executeQuery(sql);
		List<Address> addressList = getAddressListByResultSet(rs);
		this.conn.close();
		return addressList;
	}

	public Address queryById(String Add_ID) {
		String sql = "select * from " + Address.BASE_TABLE_NAME + " where " + Address.BASE_ADDRESS_ID + " = '" + Add_ID
				+ "'";
		ResultSet rs = this.conn.executeQuery(sql);
		Address address = getAddressByResultSet(rs);
		this.conn.close();
		return address;
	}

	public ResultSet queryBySql(String query) {
		ResultSet resultSet = this.conn.executeQuery(query);
		return resultSet;
	}

	/**
	 * 通过ResultSet获取Address对象
	 *
	 */
	private Address getAddressByResultSet(ResultSet resultSet) {
		Address address = new Address();
		try {
			if (resultSet.next()) {
				address.set_id(resultSet.getLong(Address.BASE_ADDRESS_PHYSICAL_ID));
				address.setAdd_ID(resultSet.getString(Address.BASE_ADDRESS_ID));
				address.setAdd_info(resultSet.getString(Address.BASE_ADDRESS_INFORMATION));
				address.setAcc_ID(resultSet.getString(Address.BASE_ADDRESS_ACCOUNT_ID));
				address.setConsign(resultSet.getString(Address.BASE_ADDRESS_CONSIGN));
				address.setCon_tel(resultSet.getString(Address.BASE_ADDRESS_CONSIGN_TELEPHONE));
				address.setAdd_addTime(resultSet.getString(Address.BASE_ADDRESS_ADD_TIME));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return address;
	}

	/**
	 * 通过ResultSet获取Address对象
	 *
	 */
	private List<Address> getAddressListByResultSet(ResultSet resultSet) {
		List<Address> addressList = new ArrayList<>();
		try {
			while (resultSet.next()) {
				Address address = new Address();
				address.set_id(resultSet.getLong(Address.BASE_ADDRESS_PHYSICAL_ID));
				address.setAdd_ID(resultSet.getString(Address.BASE_ADDRESS_ID));
				address.setAdd_info(resultSet.getString(Address.BASE_ADDRESS_INFORMATION));
				address.setAcc_ID(resultSet.getString(Address.BASE_ADDRESS_ACCOUNT_ID));
				address.setConsign(resultSet.getString(Address.BASE_ADDRESS_CONSIGN));
				address.setCon_tel(resultSet.getString(Address.BASE_ADDRESS_CONSIGN_TELEPHONE));
				address.setAdd_addTime(resultSet.getString(Address.BASE_ADDRESS_ADD_TIME));
				addressList.add(address);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return addressList;
	}

	public void closeDBConnection() {
		this.conn.close();
	}
}