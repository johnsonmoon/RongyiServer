package com.xuyihao.logic;

import com.xuyihao.entity.Category;

/**
 * 产品类别逻辑
 *
 * Created by Administrator on 2016/7/20.
 */
public interface CategoryLogic {
	/**
	 * insert information into database table
	 *
	 * @param category instance of Category
	 * @return
	 */
	public String saveCategory(Category category);

	/**
	 * get information of an Category
	 *
	 * @param Cat_ID
	 * @return
	 */
	public Category getCategoryInfoById(String Cat_ID);
}
