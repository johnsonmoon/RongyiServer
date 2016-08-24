package com.xuyihao.logic;

import com.xuyihao.entity.LikeCrs;

/**
 * Created by Administrator on 2016/7/20.
 */
public interface LikeCrsLogic {
	/**
	 * insert information into database tables
	 *
	 * @param likeCrs instance of LikeCrs
	 * @return
	 */
	public String saveLikeCrs(LikeCrs likeCrs);

	/**
	 * 
	 * @param Like_ID
	 * @return
	 */
	public LikeCrs getLikeCrsInfo(String Like_ID);
}