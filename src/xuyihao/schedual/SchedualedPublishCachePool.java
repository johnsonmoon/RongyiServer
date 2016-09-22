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
	private static List<Courses> latestCoursesPool;
	/**
	 * 最近的帖子缓存池
	 */
	private static List<Posts> latestPostsPool;

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
		latestCoursesPool.clear();
		latestPostsPool.clear();
		latestCoursesPool = this.coursesDao.queryByLimitOrdered(Courses.BASE_COURSES_PHYSICAL_ID, -1, 0, cachePoolSize);
		latestPostsPool = this.postsDao.queryByLimitOrdered(Posts.BASE_POSTS_PHYSICAL_ID, -1, 0, cachePoolSize);
	}

	public static List<Courses> getLatestCoursesPool() {
		return latestCoursesPool;
	}

	public static List<Posts> getLatestPostsPool() {
		return latestPostsPool;
	}

	public void setCoursesDao(CoursesDao coursesDao) {
		this.coursesDao = coursesDao;
	}

	public void setPostsDao(PostsDao postsDao) {
		this.postsDao = postsDao;
	}
}