package xuyihao.service.impl;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.sf.json.JSONObject;
import xuyihao.common.AppPropertiesLoader;
import xuyihao.entity.CommentCrs;
import xuyihao.entity.Courses;
import xuyihao.entity.LikeCrs;
import xuyihao.logic.CommentCrsLogic;
import xuyihao.logic.CoursesLogic;
import xuyihao.logic.LikeCrsLogic;

/**
 * 
 * @Author Xuyh created at 2016年8月26日 下午1:18:45
 */
@Component("CoursesService")
public class CoursesServiceImpl implements xuyihao.service.CoursesService {
	private String BASE_FILE_PATH = AppPropertiesLoader.getAppProperties().getProperty("BaseFilePath");
	private String RELATIVE_PATH = File.separator + "vedios" + File.separator + "courses" + File.separator;
	private String ABSOLUTE_PATH = BASE_FILE_PATH + RELATIVE_PATH;

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

	public void setSessionInfo(HttpSession session) {
		this.session = session;
	}

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

	public String getCourseInformation(String courseId) {
		Courses course = this.coursesLogic.getCoursesInfo(courseId);
		// XXX 所有人都可以查看，因此无需检查发布者
		return course.toJSONString();
	}

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

	public String getCommentCourseInformation(String commentId) {
		// XXX 所有人可查看
		CommentCrs commentCrs = this.commentCrsLogic.getCommentCrsInfo(commentId);
		return commentCrs.toJSONString();
	}

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

	public String getLikeCourseInformation(String likeId) {
		// XXX 所有人可查看
		LikeCrs likeCrs = this.likeCrsLogic.getLikeCrsInfo(likeId);
		return likeCrs.toJSONString();
	}

	public String saveCoursesVedio(HttpServletRequest request) {

		return null;
	}

	public String changeCoursesVedio(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getCoursesVedioId(String Crs_ID) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getThumbnailByVedioId(String Vedio_ID, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getVedioByVedioId(String Vedio_ID, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}
}