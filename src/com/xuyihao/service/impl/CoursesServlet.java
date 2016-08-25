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
import com.xuyihao.entity.CommentCrs;
import com.xuyihao.entity.Courses;
import com.xuyihao.entity.LikeCrs;
import com.xuyihao.logic.CommentCrsLogic;
import com.xuyihao.logic.CoursesLogic;
import com.xuyihao.logic.LikeCrsLogic;
import com.xuyihao.service.CoursesService;

import net.sf.json.JSONObject;

@MultipartConfig
public class CoursesServlet extends HttpServlet implements CoursesService {

	/**
	 * <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = -5851930391567841977L;

	@Autowired
	private CoursesLogic coursesLogic;

	@Autowired
	private CommentCrsLogic commentCrsLogic;

	@Autowired
	private LikeCrsLogic likeCrsLogic;

	private HttpSession session = null;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置会话信息
		this.session = request.getSession();
		String action = request.getParameter("action").trim();
		if (action.equals("addCrs")) {
			// TODO 需要完成的是Crs_route 的设计，因为涉及到上传视频，需要上传之后生成route并保存
			Courses course = new Courses();
			course.setCrs_name(request.getParameter("Crs_name"));
			course.setAcc_ID(session.getAttribute("Acc_ID").toString());
			course.setAuthor_ID(session.getAttribute("Acc_ID").toString());
			String message = this.addCourse(course);
			response.getWriter().println(message);
		} else if (action.equals("deleteCrs")) {
			String courseId = request.getParameter("Crs_ID");
			String message = this.deleteCourse(courseId);
			response.getWriter().println(message);
		} else if (action.equals("changeCrsInfo")) {
			Courses course = new Courses();
			// XXX Crs_ID 必需量
			course.setCrs_ID(request.getParameter("Crs_ID"));
			course.setCrs_name(request.getParameter("Crs_name"));
			// TODO 这里需要考虑视频是否要更换，如果更换，需要详细设计
			String message = this.changeCourseInformation(course);
			response.getWriter().println(message);
		} else if (action.equals("getCrsInfo")) {
			String courseId = request.getParameter("Crs_ID");
			String message = this.getCourseInformation(courseId);
			response.getWriter().println(message);
		} else if (action.equals("shareCrs")) {
			String accountId = session.getAttribute("Acc_ID").toString();
			String courseId = request.getParameter("Crs_ID");
			String message = this.shareCourse(accountId, courseId);
			response.getWriter().println(message);
		} else if (action.equals("commCrs")) {
			CommentCrs commentCrs = new CommentCrs();
			commentCrs.setComm_desc(request.getParameter("Crs_desc"));
			commentCrs.setAcc_ID(session.getAttribute("Acc_ID").toString());
			commentCrs.setRep_ID(request.getParameter("Rep_Id"));
			commentCrs.setCrs_ID(request.getParameter("Crs_ID"));
			String message = this.addCommentCourse(commentCrs);
			response.getWriter().println(message);
		} else if (action.equals("deleteCommCrs")) {
			String commentId = request.getParameter("Comm_ID");
			String message = this.deleteCommentCourse(commentId);
			response.getWriter().println(message);
		} else if (action.equals("getCommCrsInfo")) {
			String commentId = request.getParameter("Comm_ID");
			String message = this.getCommentCourseInformation(commentId);
			response.getWriter().println(message);
		} else if (action.equals("likeCrs")) {
			// TODO 这里需要将打赏具体实现方式做详细的设计，比如说容易币系统怎么实现(个人、商户)，跟实际的网上支付怎么对接
			LikeCrs likeCrs = new LikeCrs();
			likeCrs.setAcc_ID(session.getAttribute("Acc_ID").toString());
			likeCrs.setCrs_ID(request.getParameter("Crs_ID"));
			likeCrs.setRep_ID(request.getParameter("Rep_ID"));
			likeCrs.setLike_ryb(Integer.parseInt(request.getParameter("Like_ryb")));
			String message = this.addLikeCourse(likeCrs);
			response.getWriter().println(message);
		} else if (action.equals("getLikeCrsInfo")) {
			String likeId = request.getParameter("Like_ID");
			String message = this.getLikeCourseInformation(likeId);
			response.getWriter().println(message);
		} else {
		}
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		WebApplicationContext context = ThreadLocalContext.setContextAndRet(this.getServletContext());
		this.commentCrsLogic = (CommentCrsLogic) context.getBean("CommentCrsLogic");
		this.coursesLogic = (CoursesLogic) context.getBean("CoursesLogic");
		this.likeCrsLogic = (LikeCrsLogic) context.getBean("LikeCrsLogic");
	}

	@Override
	public String addCourse(Courses course) {
		JSONObject json = new JSONObject();
		String Crs_ID = this.coursesLogic.saveCourse(course);
		if (Crs_ID == null || Crs_ID.equals("")) {
			json.put("result", false);
			json.put("Crs_ID", "");
		} else {
			json.put("result", true);
			json.put("Crs_ID", Crs_ID);
		}
		return json.toString();
	}

	@Override
	public String deleteCourse(String courseId) {
		JSONObject json = new JSONObject();
		String Acc_ID = this.session.getAttribute("Acc_ID").toString();
		Courses queryCourse = this.coursesLogic.getCoursesInfo(courseId);
		if (Acc_ID.equals(queryCourse.getAcc_ID())) {// 是本人的视频
			boolean flag = this.coursesLogic.deleteCourse(courseId);
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
	public String changeCourseInformation(Courses course) {
		JSONObject json = new JSONObject();
		String Acc_ID = this.session.getAttribute("Acc_ID").toString();
		if (Acc_ID.equals(course.getAcc_ID())) {
			boolean flag = this.coursesLogic.changeCourseInfo(course);
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
	public String getCourseInformation(String courseId) {
		Courses course = this.coursesLogic.getCoursesInfo(courseId);
		// XXX 所有人都可以查看，因此无需检查发布者
		return course.toJSONString();
	}

	@Override
	public String shareCourse(String accountId, String courseId) {
		JSONObject json = new JSONObject();
		String Crs_ID = this.coursesLogic.shareCourse(accountId, courseId);
		if (Crs_ID == null || Crs_ID.equals("")) {
			json.put("result", false);
			json.put("Crs_ID", "");
		} else {
			json.put("result", true);
			json.put("Crs_ID", Crs_ID);
		}
		return json.toString();
	}

	@Override
	public String addCommentCourse(CommentCrs commentCrs) {
		JSONObject json = new JSONObject();
		String Comm_ID = this.commentCrsLogic.saveCommentCrs(commentCrs);
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
	public String deleteCommentCourse(String commentId) {
		JSONObject json = new JSONObject();
		String Acc_ID = this.session.getAttribute("Acc_ID").toString();
		CommentCrs commentCrs = this.commentCrsLogic.getCommentCrsInfo(commentId);
		if (Acc_ID.equals(commentCrs.getAcc_ID())) {// 是本人的视频
			boolean flag = this.commentCrsLogic.deleteCommentCrs(commentId);
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
	public String getCommentCourseInformation(String commentId) {
		// XXX 所有人可查看
		CommentCrs commentCrs = this.commentCrsLogic.getCommentCrsInfo(commentId);
		return commentCrs.toJSONString();
	}

	@Override
	public String addLikeCourse(LikeCrs likeCrs) {
		JSONObject json = new JSONObject();
		String Like_ID = this.likeCrsLogic.saveLikeCrs(likeCrs);
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
	public String getLikeCourseInformation(String likeId) {
		// XXX 所有人可查看
		LikeCrs likeCrs = this.likeCrsLogic.getLikeCrsInfo(likeId);
		return likeCrs.toJSONString();
	}

	public void setCoursesLogic(CoursesLogic coursesLogic) {
		this.coursesLogic = coursesLogic;
	}

	public void setCommentCrsLogic(CommentCrsLogic commentCrsLogic) {
		this.commentCrsLogic = commentCrsLogic;
	}

	public void setLikeCrsLogic(LikeCrsLogic likeCrsLogic) {
		this.likeCrsLogic = likeCrsLogic;
	}
}
