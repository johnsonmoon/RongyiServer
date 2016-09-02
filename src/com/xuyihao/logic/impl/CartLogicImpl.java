package com.xuyihao.logic.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xuyihao.dao.CartDao;
import com.xuyihao.entity.Cart;
import com.xuyihao.logic.CartLogic;
import com.xuyihao.tools.utils.DateUtils;
import com.xuyihao.tools.utils.RandomUtils;

/**
 * Created by Xuyh at 2016/7/21 20:25.
 */
@Component("CartLogic")
public class CartLogicImpl implements CartLogic {
	@Autowired
	private CartDao cartDao;

	public void setCartDao(CartDao cartDao) {
		this.cartDao = cartDao;
	}

	public String saveCart(Cart cart) {
		String Cart_ID = RandomUtils.getRandomString(15) + "Cart";
		cart.setCart_ID(Cart_ID);
		String Add_time = DateUtils.currentDateTime();
		cart.setCart_addTime(Add_time);
		if (this.cartDao.saveCart(cart)) {
			return Cart_ID;
		} else {
			return "";
		}
	}

	public Cart getCartInfo(String Cart_ID) {
		Cart cart = this.cartDao.queryById(Cart_ID);
		return cart;
	}

	public boolean changeProductNumber(String Cart_ID, int Pro_num) {
		Cart cart = this.cartDao.queryById(Cart_ID);
		cart.setPro_num(Pro_num);
		if (this.cartDao.updateCart(cart)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean deleteCart(String Cart_ID) {
		boolean flag = this.cartDao.deleteCart(Cart_ID);
		return flag;
	}
}