package xuyihao.servlet;

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

import xuyihao.common.ThreadLocalContext;
import xuyihao.entity.CommentPost;
import xuyihao.entity.LikePost;
import xuyihao.entity.Posts;
import xuyihao.service.PostsService;
import xuyihao.tools.utils.RespondUtils;

/**
 * 
 * @Author Xuyh created at 2016年8月26日 下午1:17:24
 */
@MultipartConfig
public class PostsServlet extends HttpServlet {

	/**
	 * <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = -6566744317226488742L;

	@Autowired
	private PostsService postsService;

	private HttpSession session = null;

	public void setPostsService(PostsService postsService) {
		this.postsService = postsService;
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		WebApplicationContext context = ThreadLocalContext.setContextAndRet(this.getServletContext());
		this.postsService = (PostsService) context.getBean("PostsService");
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置会话信息
		this.session = request.getSession();
		this.postsService.setSessionInfo(session);
		String action = request.getParameter("action");
		if (action == null || action.equals("")) {
			return;
		}
		action = action.trim();
		switch (action) {
		case "addPost":
			this.addPost(request, response);
			break;
		case "deletePost":
			this.deletePost(request, response);
			break;
		case "changePostInfo":
			this.changePostInformation(request, response);
			break;
		case "getPostInfo":
			this.getPostInformation(request, response);
			break;
		case "sharePost":
			this.sharePost(request, response);
			break;
		case "commPost":
			this.addCommentPost(request, response);
			break;
		case "deleteCommPost":
			this.deleteCommentPost(request, response);
			break;
		case "getCommPostInfo":
			this.getCommentPostInformation(request, response);
			break;
		case "likePost":
			this.addLikePost(request, response);
			break;
		case "getLikePostInfo":
			this.getLikePostInformation(request, response);
			break;
		case "getCachedPostsList":
			this.getCachedPostsList(request, response);
			break;
		case "getLatestPostsList":
			this.getLatestPostsList(request, response);
			break;
		}
	}

	public void addPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Acc_ID = session.getAttribute("Acc_ID").toString();
		if (Acc_ID == null || Acc_ID.equals("")) {
			return;
		}
		// TODO 需要完成的是Post_route 的设计，因为涉及到上传视频，需要上传之后生成route并保存
		Posts post = new Posts();
		post.setPost_name(request.getParameter("Post_name"));
		post.setAcc_ID(Acc_ID);
		post.setAuthor_ID(Acc_ID);
		String message = this.postsService.addPost(post);
		response.getWriter().println(message);
	}

	public void deletePost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Acc_ID = session.getAttribute("Acc_ID").toString();
		if (Acc_ID == null || Acc_ID.equals("")) {
			return;
		}
		String postId = request.getParameter("Post_ID");
		String message = this.postsService.deletePost(postId);
		response.getWriter().println(message);
	}

	public void changePostInformation(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Acc_ID = session.getAttribute("Acc_ID").toString();
		if (Acc_ID == null || Acc_ID.equals("")) {
			return;
		}
		Posts post = new Posts();
		// XXX Post_ID必需量
		post.setPost_ID(request.getParameter("Post_ID"));
		post.setPost_name(request.getParameter("Post_name"));
		// TODO 这里需要考虑视频是否要更换，如果更换，需要详细设计
		String message = this.postsService.changePostInformation(post);
		response.getWriter().println(message);
	}

	public void getPostInformation(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String postId = request.getParameter("Post_ID");
		String message = this.postsService.getPostInformation(postId);
		response.getWriter().println(message);
	}

	public void sharePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String Acc_ID = session.getAttribute("Acc_ID").toString();
		if (Acc_ID == null || Acc_ID.equals("")) {
			return;
		}
		String accountId = Acc_ID;
		String postId = request.getParameter("Post_ID");
		String message = this.postsService.sharePost(accountId, postId);
		response.getWriter().println(message);
	}

	public void addCommentPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Acc_ID = session.getAttribute("Acc_ID").toString();
		if (Acc_ID == null || Acc_ID.equals("")) {
			return;
		}
		CommentPost commentPost = new CommentPost();
		commentPost.setComm_desc(request.getParameter("Comm_desc"));
		commentPost.setAcc_ID(Acc_ID);
		commentPost.setRep_ID(request.getParameter("Rep_ID"));
		commentPost.setPost_ID(request.getParameter("Post_ID"));
		String message = this.postsService.addCommentPost(commentPost);
		response.getWriter().println(message);
	}

	public void deleteCommentPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Acc_ID = session.getAttribute("Acc_ID").toString();
		if (Acc_ID == null || Acc_ID.equals("")) {
			return;
		}
		String commentId = request.getParameter("Comm_ID");
		String message = this.postsService.deleteCommentPost(commentId);
		response.getWriter().println(message);
	}

	public void getCommentPostInformation(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String commentId = request.getParameter("Comm_ID");
		String message = this.postsService.getCommentPostInformation(commentId);
		response.getWriter().println(message);
	}

	public void addLikePost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Acc_ID = session.getAttribute("Acc_ID").toString();
		if (Acc_ID == null || Acc_ID.equals("")) {
			return;
		}
		// TODO 这里需要将打赏具体实现方式做详细的设计，比如说容易币系统怎么实现(个人、商户)，跟实际的网上支付怎么对接
		LikePost likePost = new LikePost();
		likePost.setAcc_ID(Acc_ID);
		likePost.setPost_ID(request.getParameter("Post_ID"));
		likePost.setRep_ID(request.getParameter("Rep_ID"));
		int ryb = 0;
		try {
			ryb = Integer.parseInt(request.getParameter("Like_ryb"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		likePost.setLike_ryb(ryb);
		String message = this.postsService.addLikePost(likePost);
		response.getWriter().println(message);
	}

	public void getLikePostInformation(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String likeId = request.getParameter("Like_ID");
		String message = this.postsService.getLikePostInformation(likeId);
		response.getWriter().println(message);
	}

	public void getCachedPostsList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String message = this.postsService.getCachedPublishingPosts();
		RespondUtils.PrintString(response, message);
	}

	public void getLatestPostsList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int page = Integer.valueOf(request.getParameter("page"));
		int size = Integer.valueOf(request.getParameter("size"));
		String message = this.postsService.getLatestPosts(page, size);
		RespondUtils.PrintString(response, message);
	}
}