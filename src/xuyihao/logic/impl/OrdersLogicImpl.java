package xuyihao.logic.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import xuyihao.dao.OrdersDao;
import xuyihao.entity.Orders;
import xuyihao.logic.OrdersLogic;
import xuyihao.tools.utils.DateUtils;
import xuyihao.tools.utils.RandomUtils;

/**
 * Created by Xuyh at 2016/7/21 20:27.
 */
@Component("OrdersLogic")
public class OrdersLogicImpl implements OrdersLogic {
	@Autowired
	private OrdersDao ordersDao;

	public void setOrderOrd(OrdersDao ordersDao) {
		this.ordersDao = ordersDao;
	}

	public String saveOrder(Orders orders) {
		boolean flag = true;
		String Ord_ID = RandomUtils.getRandomString(15) + "Ord";
		String Ord_date = DateUtils.currentDate();
		String Add_time = DateUtils.currentDateTime();
		orders.setOrd_ID(Ord_ID);
		orders.setOrd_date(Ord_date);
		orders.setOrd_addTime(Add_time);
		flag = flag && this.ordersDao.saveOrders(orders);
		if (flag) {
			return Ord_ID;
		} else {
			return null;
		}
	}

	public Orders getOrderInfo(String Ord_ID) {
		Orders order = this.ordersDao.queryById(Ord_ID);
		return order;
	}

	public boolean deleteOrder(String Ord_ID) {
		boolean flag = true;
		flag = flag && this.ordersDao.deleteOrders(Ord_ID);
		return flag;
	}

	public boolean changeOrderInfo(Orders orders) {
		Orders DBorder = this.getOrderInfo(orders.getOrd_ID());
		if (!DBorder.getOrd_ID().equals(orders.getOrd_ID())) {
			return false;
		}
		orders.setOrd_date(DBorder.getOrd_date());
		orders.setAcc_ID(DBorder.getAcc_ID());
		if ((orders.getProd_ID() == null) || (orders.getProd_ID().equals(""))) {
			orders.setProd_ID(DBorder.getProd_ID());
		}
		if (orders.getProd_price() == 0.0) {
			orders.setProd_price(DBorder.getProd_price());
		}
		if (orders.getPro_num() == 0) {
			orders.setPro_num(DBorder.getPro_num());
		}
		orders.setOrd_paid(DBorder.isOrd_paid() == true ? true : orders.isOrd_paid());
		orders.setOrd_sent(DBorder.isOrd_sent() == true ? true : orders.isOrd_sent());
		orders.setOrd_received(DBorder.isOrd_received() == true ? true : orders.isOrd_received());
		orders.setOrd_comment(DBorder.isOrd_comment() == true ? true : orders.isOrd_comment());
		if ((orders.getAdd_ID() == null) || (orders.getAdd_ID().equals(""))) {
			orders.setAdd_ID(DBorder.getAdd_ID());
		}
		orders.setOrd_addTime(DBorder.getOrd_addTime());
		boolean flag = this.ordersDao.updateOrders(orders);
		return flag;
	}
}