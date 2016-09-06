package xuyihao.logic;

import xuyihao.entity.Posts;

/**
 * Created by Administrator on 2016/7/20.
 */
public interface PostsLogic {
	/**
	 * insert information into database table, create a new Post
	 *
	 * @param posts instance of Posts
	 * @return
	 */
	public String savePost(Posts posts);

	/**
	 * delete information from database table
	 * 
	 * @param Post_ID
	 * @return
	 */
	public boolean deletePost(String Post_ID);

	/**
	 * 
	 * @param post
	 * @return
	 */
	public boolean changePostInfo(Posts post);

	/**
	 * get Post information by detail
	 * 
	 * @param Post_ID
	 * @return
	 */
	public Posts getPostInfo(String Post_ID);

	/**
	 * share Post by an Account
	 * 
	 * @param Acc_ID
	 * @param Post_ID
	 * @return
	 */
	public String sharePost(String Acc_ID, String Post_ID);
}
