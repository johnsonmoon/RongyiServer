package test.xuyihao.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import test.xuyihao.CommonTest;
import xuyihao.dao.AccountsDao;
import xuyihao.dao.CoursesDao;
import xuyihao.entity.Accounts;
import xuyihao.entity.Courses;
import xuyihao.tools.utils.DateUtils;
import xuyihao.tools.utils.RandomUtils;

/**
 * 
 * @Author Xuyh created at Aug 14, 2016 3:30:45 PM.
 *
 */
public class CoursesDaoTest extends CommonTest {
	private static String Acc_ID = null;
	private static String Author_ID = null;
	private static List<String> Crs_IDList = new ArrayList<>();
	private static List<String> Crs_nameList = new ArrayList<>();

	@Autowired
	private AccountsDao accountsDao;

	@Autowired
	private CoursesDao coursesDao;

	public void setAccountsDao(AccountsDao accountsDao) {
		this.accountsDao = accountsDao;
	}

	public void setCoursesDao(CoursesDao coursesDao) {
		this.coursesDao = coursesDao;
	}

	@Before
	public void setUp() {
		Acc_ID = RandomUtils.getRandomString(15) + "Acc";
		Author_ID = RandomUtils.getRandomString(15) + "Acc";
		Accounts accounts = new Accounts();
		accounts.setAcc_ID(Acc_ID);
		accounts.setAcc_name("Johnson" + RandomUtils.getRandomNumberString(4));
		accounts.setAcc_pwd("moon" + RandomUtils.getRandomNumberString(4));
		accounts.setAcc_sex("男");
		accounts.setAcc_loc("浙江工业大学屛峰校区");
		accounts.setAcc_addTime(DateUtils.currentDateTime());
		this.accountsDao.saveAccounts(accounts);
		Accounts accounts2 = new Accounts();
		accounts2.setAcc_ID(Author_ID);
		accounts2.setAcc_name("Johnson" + RandomUtils.getRandomNumberString(4));
		accounts2.setAcc_pwd("moon" + RandomUtils.getRandomNumberString(4));
		accounts2.setAcc_sex("男");
		accounts2.setAcc_loc("浙江工业大学屛峰校区");
		accounts2.setAcc_addTime(DateUtils.currentDateTime());
		this.accountsDao.saveAccounts(accounts2);
		for (int i = 0; i < 50; i++) {
			String Crs_ID = RandomUtils.getRandomString(15) + "Crs";
			String Crs_name = RandomUtils.getRandomString(30);
			Crs_IDList.add(Crs_ID);
			Crs_nameList.add(Crs_name);
			Courses course = new Courses();
			course.setCrs_ID(Crs_ID);
			course.setAcc_ID(Acc_ID);
			course.setAuthor_ID(Author_ID);
			course.setCrs_addTime(DateUtils.currentDateTime());
			course.setCrs_name(Crs_name);
			course.setCrs_route(RandomUtils.getRandomString(40));
			this.coursesDao.saveCourses(course);
		}
	}

	@Test
	public void testSaveCourses() {
		boolean flag = true;
		for (int i = 0; i < 50; i++) {
			Courses course = new Courses();
			course.setCrs_ID(RandomUtils.getRandomString(15) + "Crs");
			course.setAcc_ID(RandomUtils.getRandomString(15) + "Acc");
			course.setAuthor_ID(RandomUtils.getRandomString(15) + "Acc");
			course.setCrs_addTime(DateUtils.currentDateTime());
			course.setCrs_name(RandomUtils.getRandomString(30));
			course.setCrs_route(RandomUtils.getRandomString(40));
			flag = flag && this.coursesDao.saveCourses(course);
		}
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testDeleteCourses() {
		boolean flag = true;
		for (int i = 45; i < Crs_IDList.size(); i++) {
			flag = flag && this.coursesDao.deleteCourses(Crs_IDList.get(i));
		}
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testUpdateCoursesCourses() {
		boolean flag = true;
		for (int i = 0; i < 45; i++) {
			Courses course = this.coursesDao.queryById(Crs_IDList.get(i));
			course.setCrs_route("lalalalala");
			flag = flag && this.coursesDao.updateCourses(course);
		}
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testQueryByName() {
		for (int i = 0; i < 20; i++) {
			Courses course = this.coursesDao.queryByName(Crs_nameList.get(i));
			Assert.assertNotNull(course);
			System.out.println(course.toJSONString());
		}
	}

	@Test
	public void testQueryById() {
		for (int i = 0; i < 20; i++) {
			Courses course = this.coursesDao.queryById(Crs_IDList.get(i));
			Assert.assertNotNull(course);
			System.out.println(course.toJSONString());
		}
	}

	@Test
	public void testQueryByAccountId() {
		List<Courses> coursesList = this.coursesDao.queryByAccountId(Acc_ID);
		Assert.assertNotNull(coursesList);
		for (Courses course : coursesList) {
			System.out.println(course.toJSONString());
		}
	}

	@Test
	public void testQueryByAuthorId() {
		List<Courses> coursesList = this.coursesDao.queryByAuthorId(Author_ID);
		Assert.assertNotNull(coursesList);
		for (Courses course : coursesList) {
			System.out.println(course.toJSONString());
		}
	}
}
