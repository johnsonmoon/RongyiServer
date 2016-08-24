package com.xuyihao.logic;

import com.xuyihao.entity.Courses;

/**
 * 视频教程逻辑
 *
 * Created by Administrator on 2016/7/20.
 */
public interface CoursesLogic {
	/**
	 * insert info into database table, create a new Course
	 *
	 * @param courses instance of Courses
	 * @return
	 */
	public String saveCourse(Courses courses);

	/**
	 * delete info from database table
	 *
	 * @param Crs_ID
	 * @return
	 */
	public boolean deleteCourse(String Crs_ID);

	/**
	 * 
	 * @param course
	 * @return
	 */
	public boolean changeCourseInfo(Courses course);

	/**
	 * get Course information by detail, complete info to parameter cou and
	 * returns the result which shows whether completed
	 *
	 * @param Crs_ID
	 * @return
	 */
	public Courses getCoursesInfo(String Crs_ID);

	/**
	 * 用户分享视频教程
	 * 
	 * @param Acc_ID
	 * @param Crs_ID
	 * @return newCrsID
	 */
	public String shareCourse(String Acc_ID, String Crs_ID);
}
