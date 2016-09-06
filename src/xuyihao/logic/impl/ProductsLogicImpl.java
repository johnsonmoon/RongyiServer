package xuyihao.logic.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import xuyihao.dao.ProductsDao;
import xuyihao.entity.Products;
import xuyihao.logic.ProductsLogic;
import xuyihao.tools.utils.DateUtils;
import xuyihao.tools.utils.RandomUtils;

/**
 * Created by Xuyh at 2016/7/21 20:28.
 */
@Component("ProductsLogic")
public class ProductsLogicImpl implements ProductsLogic {
	@Autowired
	private ProductsDao productsDao;

	public void setProductsDao(ProductsDao productsDao) {
		this.productsDao = productsDao;
	}

	public String saveProduct(Products products) {
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

	public Products getProductInfo(String Prod_ID) {
		Products product = this.productsDao.queryById(Prod_ID);
		return product;
	}

	public boolean changeProductSold(String Prod_ID, int sold) {
		boolean flag = true;
		Products product = this.productsDao.queryById(Prod_ID);
		product.setProd_sold(sold);
		flag = flag && this.productsDao.updateProducts(product);
		return flag;
	}

	public boolean decreaseProductNumber(String Prod_ID) {
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

	public boolean whetherProductRemain(String Prod_ID) {
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

	public boolean deleteProduct(String Prod_ID) {
		boolean flag = true;
		flag = flag && this.productsDao.deleteProducts(Prod_ID);
		return flag;
	}
}