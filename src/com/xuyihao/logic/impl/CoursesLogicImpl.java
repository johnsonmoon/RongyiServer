package com.xuyihao.logic.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.xuyihao.dao.AccountsDao;
import com.xuyihao.dao.CoursesDao;
import com.xuyihao.dao.RepostCrsDao;
import com.xuyihao.entity.Accounts;
import com.xuyihao.entity.Courses;
import com.xuyihao.logic.CoursesLogic;
import com.xuyihao.tools.utils.DateUtils;
import com.xuyihao.tools.utils.RandomUtils;

/**
 * Created by Xuyh at 2016/7/21 20:26.
 */
@Component("CoursesLogic")
public class CoursesLogicImpl implements CoursesLogic {
	@Autowired
	private CoursesDao coursesDao;

	@Autowired
	private RepostCrsDao repostCrsDao;

	@Autowired
	private AccountsDao accountsDao;

	public void setAccountsDao(AccountsDao accountsDao) {
		this.accountsDao = accountsDao;
	}

	public void setCoursesDao(CoursesDao coursesDao) {
		this.coursesDao = coursesDao;
	}

	public void setRepostCrs(RepostCrsDao repostCrsDao) {
		this.repostCrsDao = repostCrsDao;
	}

	public String saveCourse(Courses courses) {
		boolean flag = true;
		String Crs_ID = RandomUtils.getRandomString(15) + "Crs";
		String Add_time = DateUtils.currentDateTime();
		courses.setCrs_ID(Crs_ID);
		courses.setCrs_addTime(Add_time);
		flag = flag && this.coursesDao.saveCourses(courses);
		// 账户发表加一(删除不减)
		Accounts accounts = this.accountsDao.queryById(courses.getAcc_ID());
		accounts.setAcc_pub(accounts.getAcc_pub() + 1);
		flag = flag && this.accountsDao.updateAccounts(accounts);
		if (flag) {
			return Crs_ID;
		} else {
			return "";
		}
	}

	public boolean deleteCourse(String Crs_ID) {
		boolean flag = this.coursesDao.deleteCourses(Crs_ID);
		return flag;
	}

	public boolean changeCourseInfo(Courses course) {
		Courses DBcourse = this.coursesDao.queryById(course.getCrs_ID());
		if ((course.getCrs_name() == null) || (course.getCrs_name().equals(""))) {
			course.setCrs_name(DBcourse.getCrs_name());
		}
		if ((course.getCrs_route() == null) || (course.getCrs_route().equals(""))) {
			course.setCrs_route(DBcourse.getCrs_route());
		}
		course.setAcc_ID(DBcourse.getAcc_ID());
		course.setAuthor_ID(DBcourse.getAuthor_ID());
		course.setCrs_rep(DBcourse.getCrs_rep());
		course.setCrs_comm(DBcourse.getCrs_comm());
		course.setCrs_like(DBcourse.getCrs_like());
		course.setCrs_addTime(DBcourse.getCrs_addTime());
		boolean flag = true;
		flag = flag && this.coursesDao.updateCourses(course);
		return flag;
	}

	public Courses getCoursesInfo(String Crs_ID) {
		Courses course = this.coursesDao.queryById(Crs_ID);
		return course;
	}

	public String shareCourse(String Acc_ID, String Crs_ID) {
		boolean flag = true;
		Courses courseOld = this.coursesDao.queryById(Crs_ID);
		courseOld.setCrs_rep(courseOld.getCrs_rep() + 1);// 被分享数加一
		flag = flag && this.coursesDao.updateCourses(courseOld);
		Courses courseNew = new Courses(courseOld);
		String Crs_IDNew = RandomUtils.getRandomString(15) + "Crs";
		String Add_time = DateUtils.currentDateTime();
		courseNew.setCrs_ID(Crs_IDNew);
		courseNew.setCrs_addTime(Add_time);
		courseNew.setAcc_ID(Acc_ID);
		flag = flag && this.coursesDao.saveCourses(courseNew);
		flag = flag && this.repostCrsDao.saveRepostCrs(Acc_ID, courseNew.getAuthor_ID(), Crs_IDNew, Add_time);
		// 账户发表加一(删除不减)
		Accounts accounts = this.accountsDao.queryById(Acc_ID);
		accounts.setAcc_pub(accounts.getAcc_pub() + 1);
		flag = flag && this.accountsDao.updateAccounts(accounts);
		if (flag) {
			return Crs_IDNew;
		} else {
			return "";
		}
	}
}