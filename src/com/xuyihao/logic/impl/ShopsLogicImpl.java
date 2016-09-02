package com.xuyihao.logic.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xuyihao.dao.ShopsDao;
import com.xuyihao.entity.Shops;
import com.xuyihao.logic.ShopsLogic;
import com.xuyihao.tools.utils.DateUtils;
import com.xuyihao.tools.utils.RandomUtils;

/**
 * Created by Xuyh at 2016/7/21 20:28.
 */
@Component("ShopsLogic")
public class ShopsLogicImpl implements ShopsLogic {
	@Autowired
	private ShopsDao shopsDao;

	public void setShopsDao(ShopsDao shopsDao) {
		this.shopsDao = shopsDao;
	}

	public String saveShop(Shops shops) {
		boolean flag = true;
		String Shop_ID = RandomUtils.getRandomString(15) + "Shop";
		String Add_time = DateUtils.currentDateTime();
		shops.setShop_ID(Shop_ID);
		shops.setShop_addTime(Add_time);
		flag = flag && this.shopsDao.saveShops(shops);
		if (flag) {
			return Shop_ID;
		} else {
			return "";
		}
	}

	public boolean shopNameExist(String Shop_name) {
		Shops shop = this.shopsDao.queryByName(Shop_name);
		if ((shop.get_id() == 0) || (shop.getShop_ID().equals(""))) {
			return false;
		} else {
			return true;
		}
	}

	public String getShopID(String name) {
		Shops shop = this.shopsDao.queryByName(name);
		return shop.getShop_ID();
	}

	public Shops getShopInfo(String Shop_ID) {
		Shops shop = this.shopsDao.queryById(Shop_ID);
		return shop;
	}

	public boolean changeShopInfo(Shops shop) {
		boolean flag = true;
		Shops DBshop = this.shopsDao.queryById(shop.getShop_ID());
		shop.setShop_name(DBshop.getShop_name());
		if ((shop.getShop_info() == null) || (shop.getShop_info().equals(""))) {
			shop.setShop_info(DBshop.getShop_info());
		}
		if ((shop.getShop_licen() == null) || (shop.getShop_licen().equals(""))) {
			shop.setShop_licen(DBshop.getShop_licen());
		}
		if (shop.getShop_lvl() == 0) {
			shop.setShop_lvl(DBshop.getShop_lvl());
		}
		if (shop.getShop_ryb() == 0) {
			shop.setShop_ryb(DBshop.getShop_ryb());
		}
		if (shop.getShop_favo() == 0) {
			shop.setShop_favo(DBshop.getShop_favo());
		}
		shop.setAcc_ID(DBshop.getAcc_ID());
		shop.setShop_addTime(DBshop.getShop_addTime());
		flag = flag && this.shopsDao.updateShops(shop);
		return flag;
	}

	public boolean deleteShop(String Shop_ID) {
		return this.shopsDao.deleteShops(Shop_ID);
	}
}