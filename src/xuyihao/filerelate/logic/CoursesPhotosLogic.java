package xuyihao.filerelate.logic;

import java.util.List;

import xuyihao.filerelate.entity.CoursesPhotos;

/**
 * 
 * @author Xuyh at 2016年9月3日 下午12:03:29.
 *
 */
public interface CoursesPhotosLogic {
	/**
	 * 存储视频教程图片信息
	 * 
	 * @param Crs_ID
	 * @param HeadPhoto_ID
	 * @param CrsPhotoIdList
	 * @return
	 */
	public boolean saveCoursesPhotos(String Crs_ID, String HeadPhoto_ID, List<String> CrsPhotoIdList);

	/**
	 * 删除所有视频教程图片信息
	 * 
	 * @param Crs_ID
	 * @return
	 */
	public boolean deleteCoursesPhotos(String Crs_ID);

	/**
	 * 修改视频教程图片信息
	 * 
	 * @param Crs_ID
	 * @param HeadPhoto_ID
	 * @param CrsPhotoIdList
	 * @return
	 */
	public boolean changeCoursesPhotosInfo(String Crs_ID, String HeadPhoto_ID, List<String> CrsPhotoIdList);

	/**
	 * 获取视频教程图片信息
	 * 
	 * @param Crs_ID
	 * @return
	 */
	public CoursesPhotos getCoursesPhototsInfo(String Crs_ID);

	/**
	 * 获取视频课程封面图片
	 * 
	 * @param Acc_ID
	 * @return
	 */
	public String getCoursesHeadPhoto(String Crs_ID);

	/**
	 * 获取视频课程其他图片
	 * 
	 * @param Acc_ID
	 * @return
	 */
	public List<String> getCoursesOtherPhotos(String Crs_ID);
}