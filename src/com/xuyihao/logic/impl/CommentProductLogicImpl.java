package com.xuyihao.logic.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.xuyihao.dao.CommentProductDao;
import com.xuyihao.dao.OrdersDao;
import com.xuyihao.entity.CommentProduct;
import com.xuyihao.entity.Orders;
import com.xuyihao.logic.CommentProductLogic;
import com.xuyihao.tools.utils.DateUtils;
import com.xuyihao.tools.utils.RandomUtils;

/**
 * Created by Xuyh at 2016/7/21 20:26.
 */
public class CommentProductLogicImpl implements CommentProductLogic {
	@Autowired
	private CommentProductDao commentProductDao;

	@Autowired
	private OrdersDao ordersDao;

	public void setOrdersDao(OrdersDao ordersDao) {
		this.ordersDao = ordersDao;
	}

	public void setCommentProductDao(CommentProductDao commentProductDao) {
		this.commentProductDao = commentProductDao;
	}

	@Override
	public String saveCommentProduct(CommentProduct commentProduct) {
		boolean flag = true;
		String Comm_ID = RandomUtils.getRandomString(15) + "Comm";
		String Add_time = DateUtils.currentDateTime();
		commentProduct.setComm_ID(Comm_ID);
		commentProduct.setComm_addTime(Add_time);
		flag = flag && this.commentProductDao.saveCommentProduct(commentProduct);
		Orders order = this.ordersDao.queryById(commentProduct.getOrd_ID());
		if ((order.getOrd_ID() == null) || (order.getOrd_ID().equals(""))) {
			return "";
		} else {
			order.setOrd_comment(true);
			this.ordersDao.updateOrders(order);
		}
		if (flag) {
			return Comm_ID;
		} else {
			return "";
		}
	}

	@Override
	public boolean changeCommentDescription(String Comm_ID, String Comm_desc) {
		boolean flag = true;
		CommentProduct commentProduct = this.commentProductDao.queryById(Comm_ID);
		if (commentProduct == null) {
			flag = false;
		} else {
			commentProduct.setComm_desc(Comm_desc);
			flag = flag && this.commentProductDao.updateCommentProduct(commentProduct);
		}
		return flag;
	}

	@Override
	public CommentProduct getCommentProductInfo(String Comm_ID) {
		CommentProduct commentProduct = this.commentProductDao.queryById(Comm_ID);
		if (commentProduct == null) {
			return null;
		}
		return commentProduct;
	}
}