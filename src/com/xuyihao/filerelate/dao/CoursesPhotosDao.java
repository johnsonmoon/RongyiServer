package com.xuyihao.filerelate.dao;

import com.xuyihao.filerelate.entity.CoursesPhotos;

/**
 * 
 * @author Xuyh at 2016年8月30日 上午9:53:15.
 *
 */
public interface CoursesPhotosDao {
	/**
	 * 添加视频教程图片
	 * 
	 * @param coursesPhotos
	 * @return
	 */
	public boolean saveCoursesPhotos(CoursesPhotos coursesPhotos);

	/**
	 * 删除视频教程图片
	 * 
	 * @param Crs_ID
	 * @return
	 */
	public boolean deleteCoursesPhotos(String Crs_ID);

	/**
	 * 修改视频教程图片
	 * 
	 * @param coursesPhotos
	 * @return
	 */
	public boolean updateCoursesPhotos(CoursesPhotos coursesPhotos);

	/**
	 * 修改视频教程图片
	 * 
	 * @param update
	 * @return
	 */
	public boolean updateCoursesPhotosBySql(String update);

	/**
	 * 查找视频教程图片
	 * 
	 * @param Crs_ID
	 * @return
	 */
	public CoursesPhotos query(String Crs_ID);

	/**
	 * 查找视频教程图片
	 * 
	 * @param query
	 * @return
	 */
	public CoursesPhotos queryBySql(String query);

	/**
	 * 关闭数据库连接(对于返回ResultSet的查询，其余的不用)
	 * 
	 */
	public void closeDBConnection();
}