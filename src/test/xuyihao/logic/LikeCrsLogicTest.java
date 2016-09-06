package test.xuyihao.logic;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import test.xuyihao.CommonTest;
import xuyihao.dao.CoursesDao;
import xuyihao.entity.Courses;
import xuyihao.entity.LikeCrs;
import xuyihao.logic.LikeCrsLogic;
import xuyihao.tools.utils.DateUtils;
import xuyihao.tools.utils.RandomUtils;

public class LikeCrsLogicTest extends CommonTest {
	private static List<String> Like_IDList = new ArrayList<>();
	private static String Crs_ID = null;

	@Autowired
	private LikeCrsLogic likeCrsLogic;

	@Autowired
	private CoursesDao coursesDao;

	public void setLikeCrsLogic(LikeCrsLogic likeCrsLogic) {
		this.likeCrsLogic = likeCrsLogic;
	}

	public void setCoursesDao(CoursesDao coursesDao) {
		this.coursesDao = coursesDao;
	}

	@Test
	public void test() {
		Crs_ID = RandomUtils.getRandomString(15) + "Crs";
		Courses course = new Courses();
		course.setAcc_ID(RandomUtils.getRandomString(15) + "Acc");
		course.setAuthor_ID(RandomUtils.getRandomString(15) + "Acc");
		course.setCrs_addTime(DateUtils.currentDateTime());
		course.setCrs_ID(Crs_ID);
		course.setCrs_name(RandomUtils.getRandomString(6));
		course.setCrs_route(RandomUtils.getRandomString(33));
		this.coursesDao.saveCourses(course);
		for (int i = 0; i < 5; i++) {
			LikeCrs likeCrs = new LikeCrs();
			likeCrs.setAcc_ID(RandomUtils.getRandomString(15) + "Acc");
			likeCrs.setLike_ryb(10);
			likeCrs.setCrs_ID(Crs_ID);
			likeCrs.setRep_ID(RandomUtils.getRandomString(15) + "Acc");
			String Like_ID = this.likeCrsLogic.saveLikeCrs(likeCrs);
			Assert.assertNotSame("", Like_ID);
			Like_IDList.add(Like_ID);
		}
		Courses cou = this.coursesDao.queryById(Crs_ID);
		System.out.println(cou.getCrs_like());
		for (String id : Like_IDList) {
			LikeCrs likeCrs = this.likeCrsLogic.getLikeCrsInfo(id);
			Assert.assertNotSame(0, likeCrs.get_id());
			System.out.println(likeCrs.toJSONString());
		}
	}
}
