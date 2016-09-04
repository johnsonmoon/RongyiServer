package com.xuyihao.servlet;

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
import com.xuyihao.service.CoursesService;

/**
 * 
 * @Author Xuyh created at 2016年8月26日 下午1:17:17
 */
@MultipartConfig
public class CoursesServlet extends HttpServlet {

	/**
	 * <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = -5851930391567841977L;

	@Autowired
	private CoursesService coursesService;

	private HttpSession session = null;

	public void setCoursesService(CoursesService coursesService) {
		this.coursesService = coursesService;
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		WebApplicationContext context = ThreadLocalContext.setContextAndRet(this.getServletContext());
		this.coursesService = (CoursesService) context.getBean("CoursesService");
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置会话信息
		this.session = request.getSession();
		this.coursesService.setSessionInfo(session);
		String action = request.getParameter("action");
		if (action == null || action.equals("")) {
			return;
		}
		action = action.trim();
		switch (action) {
		case "addCrs":
			this.addCourse(request, response);
			break;
		case "deleteCrs":
			this.deleteCourse(request, response);
			break;
		case "changeCrsInfo":
			this.changeCourseInformation(request, response);
			break;
		case "getCrsInfo":
			this.getCourseInformation(request, response);
			break;
		case "shareCrs":
			this.shareCourse(request, response);
			break;
		case "commCrs":
			this.addCommentCourse(request, response);
			break;
		case "deleteCommCrs":
			this.deleteCommentCourse(request, response);
			break;
		case "getCommCrsInfo":
			this.getCommentCourseInformation(request, response);
			break;
		case "likeCrs":
			this.addLikeCourse(request, response);
			break;
		case "getLikeCrsInfo":
			this.getLikeCourseInformation(request, response);
			break;
		}
	}

	public void addCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO 需要完成的是Crs_route 的设计，因为涉及到上传视频，需要上传之后生成route并保存
		String Acc_ID = session.getAttribute("Acc_ID").toString();
		if (Acc_ID == null || Acc_ID.equals("")) {
			return;
		}
		Courses course = new Courses();
		course.setCrs_name(request.getParameter("Crs_name"));
		course.setAcc_ID(Acc_ID);
		course.setAuthor_ID(Acc_ID);
		String message = this.coursesService.addCourse(course);
		response.getWriter().println(message);
	}

	public void deleteCourse(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Acc_ID = session.getAttribute("Acc_ID").toString();
		if (Acc_ID == null || Acc_ID.equals("")) {
			return;
		}
		String courseId = request.getParameter("Crs_ID");
		String message = this.coursesService.deleteCourse(courseId);
		response.getWriter().println(message);
	}

	public void changeCourseInformation(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Acc_ID = session.getAttribute("Acc_ID").toString();
		if (Acc_ID == null || Acc_ID.equals("")) {
			return;
		}
		Courses course = new Courses();
		// XXX Crs_ID 必需量
		course.setCrs_ID(request.getParameter("Crs_ID"));
		course.setCrs_name(request.getParameter("Crs_name"));
		// TODO 这里需要考虑视频是否要更换，如果更换，需要详细设计
		String message = this.coursesService.changeCourseInformation(course);
		response.getWriter().println(message);
	}

	public void getCourseInformation(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String courseId = request.getParameter("Crs_ID");
		String message = this.coursesService.getCourseInformation(courseId);
		response.getWriter().println(message);
	}

	public void shareCourse(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Acc_ID = session.getAttribute("Acc_ID").toString();
		if (Acc_ID == null || Acc_ID.equals("")) {
			return;
		}
		String accountId = Acc_ID;
		String courseId = request.getParameter("Crs_ID");
		String message = this.coursesService.shareCourse(accountId, courseId);
		response.getWriter().println(message);
	}

	public void addCommentCourse(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Acc_ID = session.getAttribute("Acc_ID").toString();
		if (Acc_ID == null || Acc_ID.equals("")) {
			return;
		}
		CommentCrs commentCrs = new CommentCrs();
		commentCrs.setComm_desc(request.getParameter("Crs_desc"));
		commentCrs.setAcc_ID(Acc_ID);
		commentCrs.setRep_ID(request.getParameter("Rep_ID"));
		commentCrs.setCrs_ID(request.getParameter("Crs_ID"));
		String message = this.coursesService.addCommentCourse(commentCrs);
		response.getWriter().println(message);
	}

	public void deleteCommentCourse(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Acc_ID = session.getAttribute("Acc_ID").toString();
		if (Acc_ID == null || Acc_ID.equals("")) {
			return;
		}
		String commentId = request.getParameter("Comm_ID");
		String message = this.coursesService.deleteCommentCourse(commentId);
		response.getWriter().println(message);
	}

	public void getCommentCourseInformation(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String commentId = request.getParameter("Comm_ID");
		String message = this.coursesService.getCommentCourseInformation(commentId);
		response.getWriter().println(message);
	}

	public void addLikeCourse(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String Acc_ID = session.getAttribute("Acc_ID").toString();
		if (Acc_ID == null || Acc_ID.equals("")) {
			return;
		}
		// TODO 这里需要将打赏具体实现方式做详细的设计，比如说容易币系统怎么实现(个人、商户)，跟实际的网上支付怎么对接
		LikeCrs likeCrs = new LikeCrs();
		likeCrs.setAcc_ID(Acc_ID);
		likeCrs.setCrs_ID(request.getParameter("Crs_ID"));
		likeCrs.setRep_ID(request.getParameter("Rep_ID"));
		int ryb = 0;
		try {
			ryb = Integer.parseInt(request.getParameter("Like_ryb"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		likeCrs.setLike_ryb(ryb);
		String message = this.coursesService.addLikeCourse(likeCrs);
		response.getWriter().println(message);
	}

	public void getLikeCourseInformation(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String likeId = request.getParameter("Like_ID");
		String message = this.coursesService.getLikeCourseInformation(likeId);
		response.getWriter().println(message);
	}
}