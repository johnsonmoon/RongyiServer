package com.xuyihao.logic;

import com.xuyihao.entity.CommentProduct;

/**
 * 评论产品逻辑
 *
 *
 * Created by Administrator on 2016/7/20.
 */
public interface CommentProductLogic {
	/**
	 * insert information into database table
	 *
	 * @param commentProduct instance of CommentProduct
	 * @return
	 */
	public String saveCommentProduct(CommentProduct commentProduct);

	/**
	 * change Commen_desc into database table
	 *
	 * @param commentProduct instance of CommentProduct
	 * @return
	 */
	public boolean changeCommentDescription(String Comm_ID, String Comm_desc);

	/**
	 * get information from database table
	 *
	 * @param commentProduct instance of CommentProduct
	 * @return
	 */
	public CommentProduct getCommentProductInfo(String Comm_ID);
}
