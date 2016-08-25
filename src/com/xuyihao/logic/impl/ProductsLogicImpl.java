package com.xuyihao.logic.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.xuyihao.common.ThreadLocalContext;
import com.xuyihao.dao.ProductsDao;
import com.xuyihao.entity.Products;
import com.xuyihao.logic.ProductsLogic;
import com.xuyihao.tools.utils.DateUtils;
import com.xuyihao.tools.utils.RandomUtils;

/**
 * Created by Xuyh at 2016/7/21 20:28.
 */
public class ProductsLogicImpl implements ProductsLogic {
	@Autowired
	private ProductsDao productsDao;

	// XXX 无法通过Autowired注解从Spring容器中获取DAO
	public void initBeans() {
		if (productsDao == null) {
			productsDao = (ProductsDao) ThreadLocalContext.getBean("ProductsDao");
		}
	}

	public void setProductsDao(ProductsDao productsDao) {
		this.productsDao = productsDao;
	}

	@Override
	public String saveProduct(Products products) {
		this.initBeans();
		boolean flag = true;
		String Prod_ID = RandomUtils.getRandomString(15) + "Prod";
		String Add_time = DateUtils.currentDateTime();
		products.setProd_ID(Prod_ID);
		products.setProd_addTime(Add_time);
		flag = flag && this.productsDao.saveProducts(products);
		if (flag) {
			return Prod_ID;
		} else {
			return "";
		}
	}

	@Override
	public Products getProductInfo(String Prod_ID) {
		this.initBeans();
		Products product = this.productsDao.queryById(Prod_ID);
		return product;
	}

	@Override
	public boolean changeProductSold(String Prod_ID, int sold) {
		this.initBeans();
		boolean flag = true;
		Products product = this.productsDao.queryById(Prod_ID);
		product.setProd_sold(sold);
		flag = flag && this.productsDao.updateProducts(product);
		return flag;
	}

	@Override
	public boolean decreaseProductNumber(String Prod_ID) {
		this.initBeans();
		boolean flag = true;
		Products product = this.productsDao.queryById(Prod_ID);
		if (product.getProd_num() > 0) {
			product.setProd_num(product.getProd_num() - 1);
			flag = flag && this.productsDao.updateProducts(product);
		} else {
			flag = false;
		}
		return flag;
	}

	@Override
	public boolean whetherProductRemain(String Prod_ID) {
		this.initBeans();
		boolean flag = true;
		Products product = this.productsDao.queryById(Prod_ID);
		int remain = product.getProd_num();
		if (remain <= 0) {
			flag = false;
		} else {
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean deleteProduct(String Prod_ID) {
		this.initBeans();
		boolean flag = true;
		flag = flag && this.productsDao.deleteProducts(Prod_ID);
		return flag;
	}
}
