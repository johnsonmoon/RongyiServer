package com.xuyihao.service.impl;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;

import com.xuyihao.common.ThreadLocalContext;
import com.xuyihao.entity.CommentPost;
import com.xuyihao.entity.LikePost;
import com.xuyihao.entity.Posts;
import com.xuyihao.logic.CommentPostLogic;
import com.xuyihao.logic.LikePostLogic;
import com.xuyihao.logic.PostsLogic;
import com.xuyihao.service.PostsService;

import net.sf.json.JSONObject;

@MultipartConfig
public class PostsServlet extends HttpServlet implements PostsService {

	/**
	 * <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = -6566744317226488742L;

	@Autowired
	private PostsLogic postsLogic;

	@Autowired
	private CommentPostLogic commentPostLogic;

	@Autowired
	private LikePostLogic likePostLogic;

	private HttpSession session = null;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置会话信息
		this.session = request.getSession();
		String action = request.getParameter("action").trim();
		if (action.equals("addPost")) {
			// TODO 需要完成的是Post_route 的设计，因为涉及到上传视频，需要上传之后生成route并保存
			Posts post = new Posts();
			post.setPost_name(request.getParameter("Post_name"));
			post.setAcc_ID(session.getAttribute("Acc_ID").toString());
			post.setAuthor_ID(session.getAttribute("Acc_ID").toString());
			String message = this.addPost(post);
			response.getWriter().println(message);
		} else if (action.equals("deletePost")) {
			String postId = request.getParameter("Post_ID");
			String message = this.deletePost(postId);
			response.getWriter().println(message);
		} else if (action.equals("changePostInfo")) {
			Posts post = new Posts();
			// XXX Post_ID必需量
			post.setPost_ID(request.getParameter("Post_ID"));
			post.setPost_name(request.getParameter("Post_name"));
			// TODO 这里需要考虑视频是否要更换，如果更换，需要详细设计
			String message = this.changePostInformation(post);
			response.getWriter().println(message);
		} else if (action.equals("getPostInfo")) {
			String postId = request.getParameter("Post_ID");
			String message = this.getPostInformation(postId);
			response.getWriter().println(message);
		} else if (action.equals("sharePost")) {
			String accountId = session.getAttribute("Acc_ID").toString();
			String postId = request.getParameter("Post_ID");
			String message = this.sharePost(accountId, postId);
			response.getWriter().println(message);
		} else if (action.equals("commPost")) {
			CommentPost commentPost = new CommentPost();
			commentPost.setComm_desc(request.getParameter("Comm_desc"));
			commentPost.setAcc_ID(session.getAttribute("Acc_ID").toString());
			commentPost.setRep_ID(request.getParameter("Rep_ID"));
			commentPost.setPost_ID(request.getParameter("Post_ID"));
			String message = this.addCommentPost(commentPost);
			response.getWriter().println(message);
		} else if (action.equals("deleteCommPost")) {
			String commentId = request.getParameter("Comm_ID");
			String message = this.deleteCommentPost(commentId);
			response.getWriter().println(message);
		} else if (action.equals("getCommPostInfo")) {
			String commentId = request.getParameter("Comm_ID");
			String message = this.getCommentPostInformation(commentId);
			response.getWriter().println(message);
		} else if (action.equals("likePost")) {
			// TODO 这里需要将打赏具体实现方式做详细的设计，比如说容易币系统怎么实现(个人、商户)，跟实际的网上支付怎么对接
			LikePost likePost = new LikePost();
			likePost.setAcc_ID(session.getAttribute("Acc_ID").toString());
			likePost.setPost_ID(request.getParameter("Post_ID"));
			likePost.setRep_ID(request.getParameter("Rep_ID"));
			likePost.setLike_ryb(Integer.valueOf(request.getParameter("Like_ryb")));
			String message = this.addLikePost(likePost);
			response.getWriter().println(message);
		} else if (action.equals("getLikePostInfo")) {
			String likeId = request.getParameter("Like_ID");
			String message = this.getLikePostInformation(likeId);
			response.getWriter().println(message);
		} else {
		}
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		WebApplicationContext context = ThreadLocalContext.setContextAndRet(this.getServletContext());
		this.postsLogic = (PostsLogic) context.getBean("PostsLogic");
		this.commentPostLogic = (CommentPostLogic) context.getBean("CommentPostLogic");
		this.likePostLogic = (LikePostLogic) context.getBean("LikePostLogic");
	}

	@Override
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

	@Override
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

	@Override
	public String changePostInformation(Posts post) {
		JSONObject json = new JSONObject();
		String Acc_ID = this.session.getAttribute("Acc_ID").toString();
		if (Acc_ID.equals(post.getAcc_ID())) {
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

	@Override
	public String getPostInformation(String postId) {
		Posts post = this.postsLogic.getPostInfo(postId);
		// XXX 所有人都可以查看，因此无需检查发布者
		return post.toJSONString();
	}

	@Override
	public String sharePost(String accountId, String PostId) {
		JSONObject json = new JSONObject();
		String Post_ID = this.sharePost(accountId, PostId);
		if (Post_ID == null || Post_ID.equals("")) {
			json.put("result", false);
			json.put("Post_ID", "");
		} else {
			json.put("result", true);
			json.put("Post_ID", Post_ID);
		}
		return json.toString();
	}

	@Override
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

	@Override
	public String deleteCommentPost(String commentId) {
		JSONObject json = new JSONObject();
		String Acc_ID = this.session.getAttribute("Acc_ID").toString();
		CommentPost commentPost = this.commentPostLogic.getCommentInfo(commentId);
		if (Acc_ID.equals(commentPost.getAcc_ID())) {// 是本人的视频
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

	@Override
	public String getCommentPostInformation(String commentId) {
		// XXX 所有人可查看
		CommentPost commentPost = this.commentPostLogic.getCommentInfo(commentId);
		return commentPost.toJSONString();
	}

	@Override
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

	@Override
	public String getLikePostInformation(String likeId) {
		// XXX 所有人可查看
		LikePost likePost = this.likePostLogic.getLikePostInfo(likeId);
		return likePost.toJSONString();
	}

	public void setPostsLogic(PostsLogic postsLogic) {
		this.postsLogic = postsLogic;
	}

	public void setCommentPostLogic(CommentPostLogic commentPostLogic) {
		this.commentPostLogic = commentPostLogic;
	}

	public void setLikePostLogic(LikePostLogic likePostLogic) {
		this.likePostLogic = likePostLogic;
	}
}
