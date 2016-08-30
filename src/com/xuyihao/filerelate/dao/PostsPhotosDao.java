package com.xuyihao.filerelate.dao;

import com.xuyihao.filerelate.entity.PostsPhotos;

/**
 * 
 * @author Xuyh at 2016年8月30日 上午9:58:48.
 *
 */
public interface PostsPhotosDao {
	/**
	 * 添加帖子图片
	 * 
	 * @param postsPhotos
	 * @return
	 */
	public boolean savePostsPhotos(PostsPhotos postsPhotos);

	/**
	 * 删除帖子图片
	 * 
	 * @param Post_ID
	 * @return
	 */
	public boolean deletePostsPhotos(String Post_ID);

	/**
	 * 修改帖子图片
	 * 
	 * @param postsPhotos
	 * @return
	 */
	public boolean updatePostsPhotos(PostsPhotos postsPhotos);

	/**
	 * 修改
	 * 
	 * @param update
	 * @return
	 */
	public boolean updatePostsPhotosBySql(String update);

	/**
	 * 查询帖子图片
	 * 
	 * @param Post_ID
	 * @return
	 */
	public PostsPhotos query(String Post_ID);

	/**
	 * 查询
	 * 
	 * @param query
	 * @return
	 */
	public PostsPhotos queryBySql(String query);

	/**
	 * 关闭数据库连接(对于返回ResultSet的查询，其余的不用)
	 * 
	 */
	public void closeDBConnection();
}