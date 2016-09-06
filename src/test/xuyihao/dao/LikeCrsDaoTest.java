package test.xuyihao.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import xuyihao.dao.AccountsDao;
import xuyihao.dao.CoursesDao;
import xuyihao.dao.LikeCrsDao;
import xuyihao.entity.Accounts;
import xuyihao.entity.Courses;
import xuyihao.entity.LikeCrs;
import xuyihao.tools.utils.DateUtils;
import xuyihao.tools.utils.RandomUtils;
import junit.framework.Assert;
import test.xuyihao.CommonTest;

/**
 * 
 * @Author Xuyh created at Aug 14, 2016 3:33:23 PM.
 *
 */
public class LikeCrsDaoTest extends CommonTest {
	private static List<String> Like_IDList = new ArrayList<>();
	private static List<String> Crs_IDList = new ArrayList<>();
	private static String Acc_ID = null;
	private static String Rep_ID = null;

	@Autowired
	private AccountsDao accountsDao;

	@Autowired
	private CoursesDao coursesDao;

	@Autowired
	private LikeCrsDao likeCrsDao;

	public void setAccountsDao(AccountsDao accountsDao) {
		this.accountsDao = accountsDao;
	}

	public void setCoursesDao(CoursesDao coursesDao) {
		this.coursesDao = coursesDao;
	}

	public void setLikeCrsDao(LikeCrsDao likeCrsDao) {
		this.likeCrsDao = likeCrsDao;
	}

	@Before
	public void setUp() {
		Like_IDList.clear();
		Crs_IDList.clear();
		Acc_ID = RandomUtils.getRandomString(15) + "Acc";
		Rep_ID = RandomUtils.getRandomString(15) + "Acc";
		Accounts accounts = new Accounts();
		accounts.setAcc_ID(Acc_ID);
		accounts.setAcc_name("Johnson" + RandomUtils.getRandomNumberString(5));
		accounts.setAcc_pwd("moon" + RandomUtils.getRandomNumberString(5));
		accounts.setAcc_sex("男");
		accounts.setAcc_loc("浙江工业大学屛峰校区");
		accounts.setAcc_addTime(DateUtils.currentDateTime());
		this.accountsDao.saveAccounts(accounts);
		Accounts accounts2 = new Accounts();
		accounts2.setAcc_ID(Acc_ID);
		accounts2.setAcc_name("Johnson" + RandomUtils.getRandomNumberString(5));
		accounts2.setAcc_pwd("moon" + RandomUtils.getRandomNumberString(5));
		accounts2.setAcc_sex("男");
		accounts2.setAcc_loc("浙江工业大学屛峰校区");
		accounts2.setAcc_addTime(DateUtils.currentDateTime());
		this.accountsDao.saveAccounts(accounts2);
		for (int i = 0; i < 50; i++) {
			String Like_ID = RandomUtils.getRandomString(15) + "Like";
			String Crs_ID = RandomUtils.getRandomString(15) + "Crs";
			Like_IDList.add(Like_ID);
			Crs_IDList.add(Crs_ID);
			Courses courses = new Courses();
			courses.setCrs_ID(Crs_ID);
			courses.setAcc_ID(Acc_ID);
			courses.setAuthor_ID(Rep_ID);
			courses.setCrs_addTime(DateUtils.currentDateTime());
			courses.setCrs_name(RandomUtils.getRandomString(12));
			courses.setCrs_route(RandomUtils.getRandomString(66));
			this.coursesDao.saveCourses(courses);
			// --
			LikeCrs likeCrs = new LikeCrs();
			likeCrs.setAcc_ID(Acc_ID);
			likeCrs.setLike_addTime(DateUtils.currentDateTime());
			likeCrs.setLike_ID(Like_ID);
			likeCrs.setLike_ryb(5);
			likeCrs.setCrs_ID(Crs_ID);
			likeCrs.setRep_ID(Rep_ID);
			this.likeCrsDao.saveLikeCrs(likeCrs);
		}
	}

	@Test
	public void testSaveLikeCrs() {
		boolean flag = true;
		for (int i = 0; i < 50; i++) {
			String Like_ID = RandomUtils.getRandomString(15) + "Like";
			String Crs_ID = RandomUtils.getRandomString(15) + "Crs";
			Courses courses = new Courses();
			courses.setCrs_ID(Crs_ID);
			courses.setAcc_ID(Acc_ID);
			courses.setAuthor_ID(Rep_ID);
			courses.setCrs_addTime(DateUtils.currentDateTime());
			courses.setCrs_name(RandomUtils.getRandomString(12));
			courses.setCrs_route(RandomUtils.getRandomString(66));
			this.coursesDao.saveCourses(courses);
			// --
			LikeCrs likeCrs = new LikeCrs();
			likeCrs.setAcc_ID(Acc_ID);
			likeCrs.setLike_addTime(DateUtils.currentDateTime());
			likeCrs.setLike_ID(Like_ID);
			likeCrs.setLike_ryb(5);
			likeCrs.setCrs_ID(Crs_ID);
			likeCrs.setRep_ID(Rep_ID);
			flag = flag && this.likeCrsDao.saveLikeCrs(likeCrs);
		}
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testDeleteLikeCrs() {
		boolean flag = true;
		for (int i = 45; i < Like_IDList.size(); i++) {
			flag = flag && this.likeCrsDao.deleteLikeCrs(Like_IDList.get(i));
		}
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testUpdateLikeCrsLikeCrs() {
		boolean flag = true;
		for (int i = 0; i < 20; i++) {
			LikeCrs likeCrs = this.likeCrsDao.queryById(Like_IDList.get(i));
			likeCrs.setLike_ryb(30);
			flag = flag && this.likeCrsDao.updateLikeCrs(likeCrs);
		}
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testQueryById() {
		for (String Like_ID : Like_IDList) {
			LikeCrs likeCrs = this.likeCrsDao.queryById(Like_ID);
			Assert.assertNotNull(likeCrs);
			System.out.println(likeCrs.toJSONString());
		}
	}

	@Test
	public void testQueryByAccountId() {
		List<LikeCrs> likeCrsList = this.likeCrsDao.queryByAccountId(Acc_ID);
		Assert.assertNotNull(likeCrsList);
		for (LikeCrs likeCrs : likeCrsList) {
			System.out.println(likeCrs.toJSONString());
		}
	}

	@Test
	public void testQueryByReporterId() {
		List<LikeCrs> likeCrsList = this.likeCrsDao.queryByReporterId(Rep_ID);
		Assert.assertNotNull(likeCrsList);
		for (LikeCrs likeCrs : likeCrsList) {
			System.out.println(likeCrs.toJSONString());
		}
	}

	@Test
	public void testQueryByCourseId() {
		for (String Crs_ID : Crs_IDList) {
			List<LikeCrs> likeCrsList = this.likeCrsDao.queryByCourseId(Crs_ID);
			Assert.assertNotNull(likeCrsList);
			for (LikeCrs likeCrs : likeCrsList) {
				System.out.println(likeCrs.toJSONString());
			}
		}
	}
}
