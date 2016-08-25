package com.xuyihao.logic.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.xuyihao.common.ThreadLocalContext;
import com.xuyihao.dao.CategoryDao;
import com.xuyihao.entity.Category;
import com.xuyihao.logic.CategoryLogic;
import com.xuyihao.tools.utils.DateUtils;
import com.xuyihao.tools.utils.RandomUtils;

/**
 * Created by Xuyh at 2016/7/21 20:25.
 */
public class CategoryLogicImpl implements CategoryLogic {
	@Autowired
	private CategoryDao categoryDao;

	// XXX 无法通过Autowired注解从Spring容器中获取DAO
	public void initBeans() {
		if (categoryDao == null) {
			categoryDao = (CategoryDao) ThreadLocalContext.getBean("CategoryDao");
		}
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Override
	public String saveCategory(Category category) {
		this.initBeans();
		String Cat_ID = RandomUtils.getRandomString(15) + "Cat";
		String Add_time = DateUtils.currentDateTime();
		category.setCat_ID(Cat_ID);
		category.setCat_addTime(Add_time);
		if (this.categoryDao.saveCategory(category)) {
			return Cat_ID;
		} else {
			return "";
		}
	}

	@Override
	public Category getCategoryInfoById(String Cat_ID) {
		this.initBeans();
		Category category = this.categoryDao.queryById(Cat_ID);
		return category;
	}
}
