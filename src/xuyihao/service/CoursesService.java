package xuyihao.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import xuyihao.entity.CommentCrs;
import xuyihao.entity.Courses;
import xuyihao.entity.LikeCrs;

/**
 * 视频课程服务相关接口
 * 
 * @Author Xuyh created at 2016年8月23日 上午10:48:16
 */
public interface CoursesService {
	/**
	 * XXX CoursesLogic
	 */
	/**
	 * 初始化会话信息
	 * 
	 * @param session
	 */
	public void setSessionInfo(HttpSession session);

	/**
	 * 新建发布视频课程
	 * 
	 * @param course
	 * @return
	 */
	public String addCourse(Courses course, HttpServletRequest request);

	/**
	 * 删除视频课程
	 * 
	 * @param courseId
	 * @return
	 */
	public String deleteCourse(String courseId);

	/**
	 * 修改视频课程信息
	 * 
	 * @param course
	 * @return
	 */
	public String changeCourseInformation(Courses course, HttpServletRequest request);

	/**
	 * 获取视频课程信息
	 * 
	 * @param courseId
	 * @return
	 */
	public String getCourseInformation(String courseId);

	/**
	 * 分享视频课程
	 * 
	 * @param accountId
	 * @param courseId
	 * @return
	 */
	public String shareCourse(String accountId, String courseId);

	/**
	 * XXX CommentCrsLogic
	 */
	/**
	 * 添加视频课程评论
	 * 
	 * @param commentCrs
	 * @return
	 */
	public String addCommentCourse(CommentCrs commentCrs);

	/**
	 * 删除视频课程评论
	 * 
	 * @param commentId
	 * @return
	 */
	public String deleteCommentCourse(String commentId);

	/**
	 * 获取视频课程评论信息
	 * 
	 * @param commentId
	 * @return
	 */
	public String getCommentCourseInformation(String commentId);

	/**
	 * XXX LikeCrsLogic
	 */
	/**
	 * 添加视频课程打赏
	 * 
	 * @param likeCrs
	 * @return
	 */
	public String addLikeCourse(LikeCrs likeCrs);

	/**
	 * 获取视频课程打赏信息
	 * 
	 * @param likeId
	 * @return
	 */
	public String getLikeCourseInformation(String likeId);

	/**
	 * 获取教程相关的视频ID、文件ID
	 * 
	 * @param Crs_ID
	 * @return
	 */
	public String getCoursesVedioAndPhotoIds(String Crs_ID);

	/**
	 * 获取视频课程的视频ID
	 * 
	 * @param Crs_ID
	 * @return
	 */
	public String getCoursesVedioId(String Crs_ID);

	/**
	 * 通过视频ID获取视频图片ID
	 * 
	 * @param Vedio_ID
	 * @param response
	 * @return
	 */
	public String getFirstPhotoIdByVedioId(String Vedio_ID);

	/**
	 * 通过视频ID获取视频文件, 返回完整路径名
	 * 
	 * @param Vedio_ID
	 * @param response
	 * @return
	 */
	public String getVedioByVedioId(String Vedio_ID);

	/**
	 * 通过图片ID获取图片文件, 返回完整路径名
	 * 
	 * @param Photo_ID
	 * @param response
	 * @return
	 */
	public String getPhotoByPhotoId(String Photo_ID);

	/**
	 * 通过图片ID获取图片缩略图文件, 返回完整路径名
	 * 
	 * @param Photo_ID
	 * @return
	 */
	public String getThumbnailPhotoByPhotoId(String Photo_ID);

	/**
	 * 获取定时更新的*条视频信息缓存
	 * 
	 * @return
	 */
	public String getCachedPublishingCourses();

	/**
	 * 获取由时间排序的视频列表
	 * 
	 * @param page 页号
	 * @param size 页大小
	 * @return
	 */
	public String getLatestCourses(int page, int size);
}