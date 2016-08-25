package com.xuyihao.logic.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.xuyihao.common.ThreadLocalContext;
import com.xuyihao.dao.ShopsDao;
import com.xuyihao.entity.Shops;
import com.xuyihao.logic.ShopsLogic;
import com.xuyihao.tools.utils.DateUtils;
import com.xuyihao.tools.utils.RandomUtils;

/**
 * Created by Xuyh at 2016/7/21 20:28.
 */
public class ShopsLogicImpl implements ShopsLogic {
	@Autowired
	private ShopsDao shopsDao;

	// XXX 无法通过Autowired注解从Spring容器中获取DAO
	public void initBeans() {
		if (shopsDao == null) {
			shopsDao = (ShopsDao) ThreadLocalContext.getBean("ShopsDao");
		}
	}

	public void setShopsDao(ShopsDao shopsDao) {
		this.shopsDao = shopsDao;
	}

	@Override
	public String saveShop(Shops shops) {
		this.initBeans();
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

	@Override
	public boolean shopNameExist(String Shop_name) {
		this.initBeans();
		Shops shop = this.shopsDao.queryByName(Shop_name);
		if ((shop.get_id() == 0) || (shop.getShop_ID().equals(""))) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public String getShopID(String name) {
		this.initBeans();
		Shops shop = this.shopsDao.queryByName(name);
		return shop.getShop_ID();
	}

	@Override
	public Shops getShopInfo(String Shop_ID) {
		this.initBeans();
		Shops shop = this.shopsDao.queryById(Shop_ID);
		return shop;
	}

	@Override
	public boolean changeShopInfo(Shops shop) {
		this.initBeans();
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

	@Override
	public boolean deleteShop(String Shop_ID) {
		this.initBeans();
		return this.shopsDao.deleteShops(Shop_ID);
	}
}