package test.com.xuyihao.logic;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xuyihao.dao.AccountsDao;
import com.xuyihao.entity.Accounts;
import com.xuyihao.entity.Courses;
import com.xuyihao.logic.CoursesLogic;
import com.xuyihao.tools.utils.DateUtils;
import com.xuyihao.tools.utils.RandomUtils;

import test.com.xuyihao.CommonTest;

public class CoursesLogicTest extends CommonTest {
	private static List<Courses> courseList = new ArrayList<>();
	private static String Acc_ID1 = null;
	private static String Acc_ID2 = null;
	private static String Author_ID = null;

	@Autowired
	private CoursesLogic courssesLogic;

	@Autowired
	private AccountsDao accountsDao;

	public void setCourssesLogic(CoursesLogic courssesLogic) {
		this.courssesLogic = courssesLogic;
	}

	public void setAccountsDao(AccountsDao accountsDao) {
		this.accountsDao = accountsDao;
	}

	@Before
	public void setUp() throws Exception {
		courseList.clear();
		Acc_ID1 = RandomUtils.getRandomString(15) + "Acc";
		Acc_ID2 = RandomUtils.getRandomString(15) + "Acc";
		Author_ID = RandomUtils.getRandomString(15) + "Acc";
		Accounts account1 = new Accounts();
		Accounts account2 = new Accounts();
		Accounts author = new Accounts();
		account1.setAcc_ID(Acc_ID1);
		account2.setAcc_ID(Acc_ID2);
		author.setAcc_ID(Author_ID);
		account1.setAcc_addTime(DateUtils.currentDateTime());
		account2.setAcc_addTime(DateUtils.currentDateTime());
		author.setAcc_addTime(DateUtils.currentDateTime());
		account1.setAcc_name(RandomUtils.getRandomString(5));
		account2.setAcc_name(RandomUtils.getRandomString(5));
		author.setAcc_name(RandomUtils.getRandomString(5));
		this.accountsDao.saveAccounts(account1);
		this.accountsDao.saveAccounts(account2);
		this.accountsDao.saveAccounts(author);
		for (int i = 0; i < 10; i++) {
			Courses course = new Courses();
			course.setAuthor_ID(Author_ID);
			course.setCrs_name(RandomUtils.getRandomString(13));
			course.setCrs_route(RandomUtils.getRandomString(66));
			String Crs_ID = this.courssesLogic.saveCourse(course);
			courseList.add(this.courssesLogic.getCoursesInfo(Crs_ID));
		}
	}

	@Test
	public void testSaveCourse() {
		for (int i = 0; i < 10; i++) {
			Courses course = new Courses();
			course.setAuthor_ID(Author_ID);
			course.setCrs_name(RandomUtils.getRandomString(13));
			course.setCrs_route(RandomUtils.getRandomString(66));
			String Crs_ID = this.courssesLogic.saveCourse(course);
			Assert.assertNotSame("", Crs_ID);
		}
	}

	@Test
	public void testDeleteCourse() {
		boolean flag = true;
		for (Courses course : courseList) {
			flag = flag && this.courssesLogic.deleteCourse(course.getCrs_ID());
		}
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testChangeCourseInfo() {
		boolean flag = true;
		for (Courses course : courseList) {
			course.setCrs_route("啦啦啦");
			course.setCrs_name("朴飘飘");
			flag = flag && this.courssesLogic.changeCourseInfo(course);
		}
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testGetCoursesInfo() {
		for (Courses course : courseList) {
			Courses cou = this.courssesLogic.getCoursesInfo(course.getCrs_ID());
			Assert.assertNotSame("", cou.getCrs_ID());
			System.out.println(cou.toJSONString());
		}
	}

	@Test
	public void testShareCourse() {
		for (Courses course : courseList) {
			String Crs_ID1 = this.courssesLogic.shareCourse(Acc_ID1, course.getCrs_ID());
			String Crs_ID2 = this.courssesLogic.shareCourse(Acc_ID2, course.getCrs_ID());
			Assert.assertNotSame("", Crs_ID1);
			Assert.assertNotSame("", Crs_ID2);
			System.out.println(Crs_ID1);
			System.out.println(Crs_ID2);
		}
	}
}
