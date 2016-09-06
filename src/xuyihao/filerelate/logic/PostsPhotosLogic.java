package xuyihao.filerelate.logic;

import java.util.List;

import xuyihao.filerelate.entity.PostsPhotos;

/**
 * 
 * @author Xuyh at 2016年9月3日 下午12:04:15.
 *
 */
public interface PostsPhotosLogic {
	/**
	 * 存储帖子图片信息
	 * 
	 * @param Post_ID
	 * @param HeadPhoto_ID
	 * @param postPhotosList
	 * @return
	 */
	public boolean savePostsPhotos(String Post_ID, String HeadPhoto_ID, List<String> postPhotosList);

	/**
	 * 删除所有帖子图片
	 * 
	 * @param Post_ID
	 * @return
	 */
	public boolean deletePostsPhotos(String Post_ID);

	/**
	 * 修改帖子图片信息
	 * 
	 * @param Post_ID
	 * @param HeadPhoto_ID
	 * @param postPhotosList
	 * @return
	 */
	public boolean changePostsPhotosInfo(String Post_ID, String HeadPhoto_ID, List<String> postPhotosList);

	/**
	 * 获取帖子图片信息
	 * 
	 * @param Post_ID
	 * @return
	 */
	public PostsPhotos getPostsPhotosInfo(String Post_ID);

	/**
	 * 获取帖子封面图片
	 * 
	 * @param Acc_ID
	 * @return
	 */
	public String getPostsHeadPhoto(String Post_ID);

	/**
	 * 获取帖子其他图片
	 * 
	 * @param Acc_ID
	 * @return
	 */
	public List<String> getPostsOtherPhotos(String Post_ID);
}