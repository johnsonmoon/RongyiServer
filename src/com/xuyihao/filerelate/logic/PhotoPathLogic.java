package com.xuyihao.filerelate.logic;

import com.xuyihao.filerelate.entity.PhotoPath;

/**
 * 
 * @author Xuyh at 2016年9月3日 下午12:01:29.
 *
 */
public interface PhotoPathLogic {
	/**
	 * 存储图片路径
	 * 
	 * @param Photo_pathName
	 * @param Thumbnail_pathName
	 * @return
	 */
	public String savePhotoPath(String Photo_pathName, String Thumbnail_pathName);

	/**
	 * 删除图片路径
	 * 
	 * @param Photo_ID
	 * @return
	 */
	public boolean deletePhotoPath(String Photo_ID);

	/**
	 * 修改图片路径
	 * 
	 * @param Photo_pathName
	 * @param Thumbnail_pathName
	 * @return
	 */
	public boolean changePhotoPathInfo(String Photo_ID, String Photo_pathName, String Thumbnail_pathName);

	/**
	 * 获取图片路径
	 * 
	 * @param Photo_ID
	 * @return
	 */
	public PhotoPath getPhotoPathInfo(String Photo_ID);
}