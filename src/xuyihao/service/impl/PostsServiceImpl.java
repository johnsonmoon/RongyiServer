package xuyihao.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import xuyihao.entity.CommentPost;
import xuyihao.entity.LikePost;
import xuyihao.entity.Posts;
import xuyihao.logic.CommentPostLogic;
import xuyihao.logic.LikePostLogic;
import xuyihao.logic.PostsLogic;
import xuyihao.schedual.SchedualedPublishCachePool;

/**
 * 
 * @Author Xuyh created at 2016年8月26日 下午1:18:51
 */
@Component("PostsService")
public class PostsServiceImpl implements xuyihao.service.PostsService {

	@Autowired
	private PostsLogic postsLogic;

	@Autowired
	private CommentPostLogic commentPostLogic;

	@Autowired
	private LikePostLogic likePostLogic;

	private HttpSession session = null;

	public void setPostsLogic(PostsLogic postsLogic) {
		this.postsLogic = postsLogic;
	}

	public void setCommentPostLogic(CommentPostLogic commentPostLogic) {
		this.commentPostLogic = commentPostLogic;
	}

	public void setLikePostLogic(LikePostLogic likePostLogic) {
		this.likePostLogic = likePostLogic;
	}

	public void setSessionInfo(HttpSession session) {
		this.session = session;
	}

	public String addPost(Posts post) {
		JSONObject json = new JSONObject();
		String Post_ID = this.postsLogic.savePost(post);
		if (Post_ID == null || Post_ID.equals("")) {
			json.put("result", false);
			json.put("Post_ID", "");
		} else {
			json.put("result", true);
			json.put("Post_ID", Post_ID);
		}
		return json.toString();
	}

	public String deletePost(String postId) {
		JSONObject json = new JSONObject();
		String Acc_ID = this.session.getAttribute("Acc_ID").toString();
		Posts queryPost = this.postsLogic.getPostInfo(postId);
		if (Acc_ID.equals(queryPost.getAcc_ID())) {// 是本人的视频
			boolean flag = this.postsLogic.deletePost(postId);
			if (flag) {
				json.put("result", true);
			} else {
				json.put("result", false);
			}
		} else {
			json.put("result", false);
		}
		return json.toString();
	}

	public String changePostInformation(Posts post) {
		JSONObject json = new JSONObject();
		String Acc_ID = this.session.getAttribute("Acc_ID").toString();
		Posts queryPost = this.postsLogic.getPostInfo(post.getPost_ID());
		if (Acc_ID.equals(queryPost.getAcc_ID())) {
			boolean flag = this.postsLogic.changePostInfo(post);
			if (flag) {
				json.put("result", true);
			} else {
				json.put("result", false);
			}
		} else {
			json.put("result", false);
		}
		return json.toString();
	}

	public String getPostInformation(String postId) {

		Posts post = this.postsLogic.getPostInfo(postId);
		// XXX 所有人都可以查看，因此无需检查发布者
		return post.toJSONString();
	}

	public String sharePost(String accountId, String PostId) {
		JSONObject json = new JSONObject();
		String Post_ID = this.postsLogic.sharePost(accountId, PostId);
		if (Post_ID == null || Post_ID.equals("")) {
			json.put("result", false);
			json.put("Post_ID", "");
		} else {
			json.put("result", true);
			json.put("Post_ID", Post_ID);
		}
		return json.toString();
	}

	public String addCommentPost(CommentPost commentPost) {
		JSONObject json = new JSONObject();
		String Comm_ID = this.commentPostLogic.saveCommentPost(commentPost);
		if (Comm_ID == null || Comm_ID.equals("")) {
			json.put("result", false);
			json.put("Comm_ID", "");
		} else {
			json.put("result", true);
			json.put("Comm_ID", Comm_ID);
		}
		return json.toString();
	}

	public String deleteCommentPost(String commentId) {
		JSONObject json = new JSONObject();
		String Acc_ID = this.session.getAttribute("Acc_ID").toString();
		CommentPost commentPost = this.commentPostLogic.getCommentInfo(commentId);
		if (Acc_ID.equals(commentPost.getAcc_ID())) {// 是本人的
			boolean flag = this.commentPostLogic.deleteCommentPost(commentId);
			if (flag) {
				json.put("result", true);
			} else {
				json.put("result", false);
			}
		} else {
			json.put("result", false);
		}
		return json.toString();
	}

	public String getCommentPostInformation(String commentId) {
		// XXX 所有人可查看
		CommentPost commentPost = this.commentPostLogic.getCommentInfo(commentId);
		return commentPost.toJSONString();
	}

	public String addLikePost(LikePost likePost) {
		JSONObject json = new JSONObject();
		String Like_ID = this.likePostLogic.saveLikePost(likePost);
		if (Like_ID == null || Like_ID.equals("")) {
			json.put("result", false);
			json.put("Like_ID", "");
		} else {
			json.put("result", true);
			json.put("Like_ID", Like_ID);
		}
		return json.toString();
	}

	public String getLikePostInformation(String likeId) {
		// XXX 所有人可查看
		LikePost likePost = this.likePostLogic.getLikePostInfo(likeId);
		return likePost.toJSONString();
	}

	public String getCachedPublishingPosts() {
		List<Posts> postsList = SchedualedPublishCachePool.getLatestPostsPool();
		JSONArray array = this.convertPostsList(postsList);
		JSONObject json = new JSONObject();
		json.put("postsList", array);
		return json.toString();
	}

	public String getLatestPosts(int page, int size) {
		JSONArray array = this.convertPostsList(this.postsLogic.getLatestPosts(page, size));
		JSONObject json = new JSONObject();
		json.put("postsList", array);
		return json.toString();
	}

	/**
	 * <pre>
	 * 转换响应数据
	 * TODO 完善响应数据
	 * </pre>
	 * 
	 * @return
	 */
	private JSONArray convertPostsList(List<Posts> postsList) {
		JSONArray array = new JSONArray();
		// TODO 完善响应消息，待帖子模块进一步开发之后
		for (Posts post : postsList) {
			JSONObject json = new JSONObject();
			json.put(Posts.BASE_POSTS_PHYSICAL_ID, post.get_id());
			json.put(Posts.BASE_POSTS_ID, post.getPost_ID());
			json.put(Posts.BASE_POSTS_NAME, post.getPost_name());
			json.put(Posts.BASE_POSTS_ROUTE, post.getPost_route());
			json.put(Posts.BASE_POSTS_ACCOUNT_ID, post.getAcc_ID());
			json.put(Posts.BASE_POSTS_AUTHOR_ACCOUNT_ID, post.getAuthor_ID());
			json.put(Posts.BASE_POSTS_REPOST_COUNT, post.getPost_rep());
			json.put(Posts.BASE_POSTS_COMMON_COUNT, post.getPost_comm());
			json.put(Posts.BASE_POSTS_LIKE_COUNT, post.getPost_like());
			json.put(Posts.BASE_POSTS_ADD_TIME, post.getPost_addTime());
			// TODO 添加未来增加的属性
			array.add(json);
		}
		return array;
	}
}