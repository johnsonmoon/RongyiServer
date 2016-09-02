package com.xuyihao.logic.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xuyihao.dao.CategoryDao;
import com.xuyihao.entity.Category;
import com.xuyihao.logic.CategoryLogic;
import com.xuyihao.tools.utils.DateUtils;
import com.xuyihao.tools.utils.RandomUtils;

/**
 * Created by Xuyh at 2016/7/21 20:25.
 */
@Component("CategoryLogic")
public class CategoryLogicImpl implements CategoryLogic {
	@Autowired
	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	public String saveCategory(Category category) {
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

	public Category getCategoryInfoById(String Cat_ID) {
		Category category = this.categoryDao.queryById(Cat_ID);
		return category;
	}
}