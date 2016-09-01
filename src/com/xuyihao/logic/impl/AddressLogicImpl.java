package com.xuyihao.logic.impl;

import com.xuyihao.dao.AddressDao;
import com.xuyihao.entity.Address;
import com.xuyihao.logic.AddressLogic;
import com.xuyihao.tools.utils.DateUtils;
import com.xuyihao.tools.utils.RandomUtils;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Xuyh at 2016/7/21 20:25.
 */
public class AddressLogicImpl implements AddressLogic {
	@Autowired
	private AddressDao addressDao;

	public void setAddressDao(AddressDao addressDao) {
		this.addressDao = addressDao;
	}

	public String saveAddress(Address address) {
		// 生成随机ID
		String Add_ID = RandomUtils.getRandomString(15) + "Add";
		address.setAdd_ID(Add_ID);
		// 获取当前时间
		address.setAdd_addTime(DateUtils.currentDateTime());
		if (this.addressDao.saveAddress(address)) {
			return Add_ID;
		} else {
			return "";
		}
	}

	public Address getAddressInfo(String Add_ID) {
		return this.addressDao.queryById(Add_ID);
	}

	public boolean changeAddressInfo(Address address) {
		Address DBaddress = this.addressDao.queryById(address.getAdd_ID());
		if ((DBaddress.getAdd_ID() == null) || (DBaddress.getAdd_ID().equals(""))) {
			return false;
		}
		if ((address.getAdd_info() == null) || (address.getAdd_info().equals(""))) {
			address.setAdd_info(DBaddress.getAdd_info());
		}
		address.setAcc_ID(DBaddress.getAcc_ID());
		if ((address.getConsign() == null) || (address.getConsign().equals(""))) {
			address.setConsign(DBaddress.getConsign());
		}
		if ((address.getCon_tel() == null) || (address.getCon_tel().equals(""))) {
			address.setCon_tel(DBaddress.getCon_tel());
		}
		address.setAdd_addTime(DBaddress.getAdd_addTime());
		return this.addressDao.updateAddress(address);
	}

	public boolean deleteAddress(String Add_ID) {
		return this.addressDao.deleteAddress(Add_ID);
	}
}