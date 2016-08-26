package com.xuyihao.service.impl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.xuyihao.common.ThreadLocalContext;
import com.xuyihao.entity.CommentCrs;
import com.xuyihao.entity.Courses;
import com.xuyihao.entity.LikeCrs;
import com.xuyihao.logic.CommentCrsLogic;
import com.xuyihao.logic.CoursesLogic;
import com.xuyihao.logic.LikeCrsLogic;

import net.sf.json.JSONObject;

/**
 * 
 * @Author Xuyh created at 2016年8月26日 下午1:18:45
 */
public class CoursesServiceImpl implements com.xuyihao.service.CoursesService {
	@Autowired
	private CoursesLogic coursesLogic;

	@Autowired
	private CommentCrsLogic commentCrsLogic;

	@Autowired
	private LikeCrsLogic likeCrsLogic;

	private HttpSession session = null;

	public void setCoursesLogic(CoursesLogic coursesLogic) {
		this.coursesLogic = coursesLogic;
	}

	public void setCommentCrsLogic(CommentCrsLogic commentCrsLogic) {
		this.commentCrsLogic = commentCrsLogic;
	}

	public void setLikeCrsLogic(LikeCrsLogic likeCrsLogic) {
		this.likeCrsLogic = likeCrsLogic;
	}

	public void init() {
		if (commentCrsLogic == null) {
			this.commentCrsLogic = (CommentCrsLogic) ThreadLocalContext.getBean("CommentCrsLogic");
		}
		if (coursesLogic == null) {
			this.coursesLogic = (CoursesLogic) ThreadLocalContext.getBean("CoursesLogic");
		}
		if (likeCrsLogic == null) {
			this.likeCrsLogic = (LikeCrsLogic) ThreadLocalContext.getBean("LikeCrsLogic");
		}
	}

	public void setSessionInfo(HttpSession session) {
		this.session = session;
	}

	public String addCourse(Courses course) {
		this.init();
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

	public String deleteCourse(String courseId) {
		this.init();
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

	public String changeCourseInformation(Courses course) {
		this.init();
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

	public String getCourseInformation(String courseId) {
		this.init();
		Courses course = this.coursesLogic.getCoursesInfo(courseId);
		// XXX 所有人都可以查看，因此无需检查发布者
		return course.toJSONString();
	}

	public String shareCourse(String accountId, String courseId) {
		this.init();
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

	public String addCommentCourse(CommentCrs commentCrs) {
		this.init();
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

	public String deleteCommentCourse(String commentId) {
		this.init();
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

	public String getCommentCourseInformation(String commentId) {
		this.init();
		// XXX 所有人可查看
		CommentCrs commentCrs = this.commentCrsLogic.getCommentCrsInfo(commentId);
		return commentCrs.toJSONString();
	}

	public String addLikeCourse(LikeCrs likeCrs) {
		this.init();
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

	public String getLikeCourseInformation(String likeId) {
		this.init();
		// XXX 所有人可查看
		LikeCrs likeCrs = this.likeCrsLogic.getLikeCrsInfo(likeId);
		return likeCrs.toJSONString();
	}
}
