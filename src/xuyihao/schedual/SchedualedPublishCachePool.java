package xuyihao.schedual;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import xuyihao.common.AppPropertiesLoader;
import xuyihao.dao.CoursesDao;
import xuyihao.dao.PostsDao;
import xuyihao.entity.Courses;
import xuyihao.entity.Posts;

/**
 * <pre>
 * 定时更新发布模块类
 * 定时更新最新的视频教程、帖子信息到缓存,供接口调用
 * </pre>
 * 
 * @author Xuyh at 2016年9月22日 下午10:17:19.
 *
 */
public class SchedualedPublishCachePool {
	/**
	 * 最近的视频教程缓存池
	 */
	private static List<Courses> latestCoursesPool = null;
	/**
	 * 最近的帖子缓存池
	 */
	private static List<Posts> latestPostsPool = null;

	/**
	 * 缓存池大小
	 */
	private static int cachePoolSize;

	@Autowired
	private CoursesDao coursesDao;

	@Autowired
	private PostsDao postsDao;

	public void init() {
		latestCoursesPool = new ArrayList<>();
		latestPostsPool = new ArrayList<>();
		cachePoolSize = Integer.parseInt(AppPropertiesLoader.getAppProperties().getProperty("CachePoolSize"));
	}

	/**
	 * 每隔30秒更新本地缓存
	 */
	@Scheduled(cron = "0/30 * * * * *")
	public void updateCachePool() {
		updateLatestCoursesPool(
				this.coursesDao.queryByLimitOrdered(Courses.BASE_COURSES_PHYSICAL_ID, -1, 0, cachePoolSize));
		updateLatestPostsPool(this.postsDao.queryByLimitOrdered(Posts.BASE_POSTS_PHYSICAL_ID, -1, 0, cachePoolSize));
	}

	private static void updateLatestCoursesPool(List<Courses> queryCoursesList) {
		latestCoursesPool.clear();
		for (Courses course : queryCoursesList) {
			latestCoursesPool.add(course);
		}
	}

	private static void updateLatestPostsPool(List<Posts> queryPostsList) {
		latestPostsPool.clear();
		for (Posts post : queryPostsList) {
			latestPostsPool.add(post);
		}
	}

	/**
	 * 获取最近的视频教程缓存
	 * 
	 * @return
	 */
	public static List<Courses> getLatestCoursesPool() {
		List<Courses> coursesList = new ArrayList<>(latestCoursesPool);
		return coursesList;
	}

	/**
	 * 获取帖子缓存池
	 * 
	 * @return
	 */
	public static List<Posts> getLatestPostsPool() {
		List<Posts> postsList = new ArrayList<>(latestPostsPool);
		return postsList;
	}

	public void setCoursesDao(CoursesDao coursesDao) {
		this.coursesDao = coursesDao;
	}

	public void setPostsDao(PostsDao postsDao) {
		this.postsDao = postsDao;
	}
}