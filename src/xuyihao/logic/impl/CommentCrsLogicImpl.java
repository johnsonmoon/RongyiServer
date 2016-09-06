package xuyihao.logic.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import xuyihao.dao.CommentCrsDao;
import xuyihao.dao.CoursesDao;
import xuyihao.entity.CommentCrs;
import xuyihao.entity.Courses;
import xuyihao.logic.CommentCrsLogic;
import xuyihao.tools.utils.DateUtils;
import xuyihao.tools.utils.RandomUtils;

/**
 * Created by Xuyh at 2016/7/21 20:25.
 */
@Component("CommentCrsLogic")
public class CommentCrsLogicImpl implements CommentCrsLogic {
	@Autowired
	private CommentCrsDao commentCrsDao;

	@Autowired
	private CoursesDao coursesDao;

	public void setCommentCrsDao(CommentCrsDao commentCrsDao) {
		this.commentCrsDao = commentCrsDao;
	}

	public void setCoursesDao(CoursesDao coursesDao) {
		this.coursesDao = coursesDao;
	}

	public String saveCommentCrs(CommentCrs commentCrs) {
		boolean flag = true;
		String Comm_ID = RandomUtils.getRandomString(15) + "Comm";
		String Add_time = DateUtils.currentDateTime();
		commentCrs.setComm_ID(Comm_ID);
		commentCrs.setComm_addTime(Add_time);
		flag = flag && this.commentCrsDao.saveCommentCrs(commentCrs);
		Courses course = this.coursesDao.queryById(commentCrs.getCrs_ID());
		if ((course.getCrs_ID() != null) && (!course.getCrs_ID().equals(""))) {
			course.setCrs_comm(course.getCrs_comm() + 1);
			flag = flag && this.coursesDao.updateCourses(course);
		}
		if (flag) {
			return Comm_ID;
		} else {
			return "";
		}
	}

	public boolean deleteCommentCrs(String Comm_ID) {
		boolean flag = true;
		Courses course = this.coursesDao.queryById(this.commentCrsDao.queryById(Comm_ID).getCrs_ID());
		if ((course.getCrs_ID() == null) || (course.getCrs_ID().equals(""))) {
			flag = false;
		} else {
			flag = flag && this.commentCrsDao.deleteCommentCrs(Comm_ID);
			course.setCrs_comm(course.getCrs_comm() - 1);
			flag = flag && this.coursesDao.updateCourses(course);
		}
		return flag;
	}

	public CommentCrs getCommentCrsInfo(String Comm_ID) {
		CommentCrs commentCrs = this.commentCrsDao.queryById(Comm_ID);
		return commentCrs;
	}
}