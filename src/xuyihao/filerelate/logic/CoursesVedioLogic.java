package xuyihao.filerelate.logic;

import xuyihao.filerelate.entity.CoursesVedio;

/**
 * 
 * @Author Xuyh created at 2016年9月18日 下午5:14:28
 */
public interface CoursesVedioLogic {
	/**
	 * 保存课程视频视频信息
	 * 
	 * @param Crs_ID
	 * @param Vedio_ID
	 * @return
	 */
	public boolean saveCoursesVedio(String Crs_ID, String Vedio_ID);

	/**
	 * 删除课程视频视频信息
	 * 
	 * @param Crs_ID
	 * @return
	 */
	public boolean deleteCoursesVedio(String Crs_ID);

	/**
	 * 修改课程视频视频信息
	 * 
	 * @param Crs_ID
	 * @param Vedio_ID
	 * @return
	 */
	public boolean changeCoursesVedioInfo(String Crs_ID, String Vedio_ID);

	/**
	 * 获取课程视频视频信息
	 * 
	 * @param Crs_ID
	 * @return
	 */
	public CoursesVedio getCoursesVedioInfo(String Crs_ID);
}