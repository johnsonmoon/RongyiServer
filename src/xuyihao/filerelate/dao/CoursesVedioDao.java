package xuyihao.filerelate.dao;

import xuyihao.filerelate.entity.CoursesVedio;

/**
 * 
 * @Author Xuyh created at 2016年9月18日 下午5:14:43
 */
public interface CoursesVedioDao {
	/**
	 * 添加视频教程的视频
	 * 
	 * @param coursesVedio
	 * @return
	 */
	public boolean saveCoursesVedio(CoursesVedio coursesVedio);

	/**
	 * 删除视频教程的视频
	 * 
	 * @param Crs_ID
	 * @return
	 */
	public boolean deleteCoursesVedio(String Crs_ID);

	/**
	 * 修改视频教程的视频
	 * 
	 * @param coursesVedio
	 * @return
	 */
	public boolean updateCoursesVedio(CoursesVedio coursesVedio);

	/**
	 * 修改视频教程的视频
	 * 
	 * @param update
	 * @return
	 */
	public boolean updateCoursesVedioBySql(String update);

	/**
	 * 查询视频教程的视频
	 * 
	 * @param Crs_ID
	 * @return
	 */
	public CoursesVedio query(String Crs_ID);

	/**
	 * 查询视频教程的视频
	 * 
	 * @param query
	 * @return
	 */
	public CoursesVedio queryBySql(String query);

	/**
	 * 关闭数据库连接(对于返回ResultSet的查询，其余的不用)
	 * 
	 */
	public void closeDBConnection();
}
