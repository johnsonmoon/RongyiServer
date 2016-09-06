package xuyihao.dao;

import java.sql.ResultSet;
import java.util.List;

import xuyihao.entity.Courses;

/**
 * Created by Xuyh at 2016/7/21 19:28.
 */
public interface CoursesDao {
	/**
	 * 写入数据库
	 *
	 * @param course 视频对象
	 * @return
	 */
	public boolean saveCourses(Courses course);

	/**
	 * 从数据库删除数据
	 *
	 * @param course 视频对象
	 * @return true成功， false失败
	 */
	public boolean deleteCourses(String Crs_ID);

	/**
	 * 修改数据库中的数据
	 *
	 * @param course 视频对象
	 * @return true成功， false失败
	 */
	public boolean updateCourses(Courses course);

	/**
	 * 通过传入sql语句改数据
	 *
	 * @param update 更新的sql语句
	 * @return true成功， false失败
	 */
	public boolean updateCourses(String update);

	/**
	 * 查询
	 *
	 * @param Crs_name 视频名称
	 * @return
	 */
	public Courses queryByName(String Crs_name);

	/**
	 * 查询
	 *
	 * @param Crs_ID 视频ID
	 * @return
	 */
	public Courses queryById(String Crs_ID);

	/**
	 *
	 * @param Acc_ID
	 * @return
	 */
	public List<Courses> queryByAccountId(String Acc_ID);

	/**
	 *
	 * @param Author_ID
	 * @return
	 */
	public List<Courses> queryByAuthorId(String Author_ID);

	/**
	 * 查询
	 *
	 * @param query 查询sql语句
	 * @return Resultset 查询结果游标
	 */
	public ResultSet queryBySql(String query);
	
	/**
	 * 关闭数据库连接(对于返回ResultSet的查询，其余的不用)
	 * 
	 */
	public void closeDBConnection();
}