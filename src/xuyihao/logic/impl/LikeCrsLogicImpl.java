package xuyihao.logic.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import xuyihao.dao.CoursesDao;
import xuyihao.dao.LikeCrsDao;
import xuyihao.entity.Courses;
import xuyihao.entity.LikeCrs;
import xuyihao.logic.LikeCrsLogic;
import xuyihao.tools.utils.DateUtils;
import xuyihao.tools.utils.RandomUtils;

/**
 * Created by Xuyh at 2016/7/21 20:27.
 */
@Component("LikeCrsLogic")
public class LikeCrsLogicImpl implements LikeCrsLogic {
	@Autowired
	private LikeCrsDao likeCrsDao;

	@Autowired
	private CoursesDao coursesDao;

	public void setLikeCrsDao(LikeCrsDao likeCrsDao) {
		this.likeCrsDao = likeCrsDao;
	}

	public void setCoursesDao(CoursesDao coursesDao) {
		this.coursesDao = coursesDao;
	}

	public String saveLikeCrs(LikeCrs likeCrs) {
		boolean flag = true;
		String Crs_ID = likeCrs.getCrs_ID();
		Courses course = this.coursesDao.queryById(Crs_ID);
		if ((course.getCrs_ID() == null) || (course.getCrs_ID().equals(""))) {
			return "";
		}
		course.setCrs_like(course.getCrs_like() + 1);
		flag = flag && this.coursesDao.updateCourses(course);
		String Like_ID = RandomUtils.getRandomString(15) + "Like";
		String Add_time = DateUtils.currentDateTime();
		likeCrs.setLike_ID(Like_ID);
		likeCrs.setLike_addTime(Add_time);
		flag = flag && this.likeCrsDao.saveLikeCrs(likeCrs);
		if (flag) {
			return Like_ID;
		} else {
			return "";
		}
	}

	public LikeCrs getLikeCrsInfo(String Like_ID) {
		LikeCrs likeCrs = this.likeCrsDao.queryById(Like_ID);
		return likeCrs;
	}
}