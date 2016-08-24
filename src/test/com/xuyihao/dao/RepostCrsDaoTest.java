package test.com.xuyihao.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import test.com.xuyihao.CommonTest;

import com.xuyihao.dao.AccountsDao;
import com.xuyihao.dao.CoursesDao;
import com.xuyihao.dao.RepostCrsDao;
import com.xuyihao.entity.Accounts;
import com.xuyihao.entity.Courses;
import com.xuyihao.tools.utils.DateUtils;
import com.xuyihao.tools.utils.RandomUtils;

import junit.framework.Assert;

/**
 * 
 * @Author Xuyh created at Aug 14, 2016 3:38:39 PM.
 *
 */
public class RepostCrsDaoTest extends CommonTest {
	private static String Acc_ID = null;
	private static String Rep_ID = null;
	private static List<String> Crs_IDList = new ArrayList<>();

	@Autowired
	private AccountsDao accountsDao;

	@Autowired
	private CoursesDao coursesDao;

	@Autowired
	private RepostCrsDao repostCrsDao;

	public void setAccountsDao(AccountsDao accountsDao) {
		this.accountsDao = accountsDao;
	}

	public void setCoursesDao(CoursesDao coursesDao) {
		this.coursesDao = coursesDao;
	}

	public void setRepostCrsDao(RepostCrsDao repostCrsDao) {
		this.repostCrsDao = repostCrsDao;
	}

	@Before
	public void setUp() {
		Crs_IDList.clear();
		Acc_ID = RandomUtils.getRandomString(15) + "Acc";
		Rep_ID = RandomUtils.getRandomString(15) + "Acc";
		Accounts accounts = new Accounts();
		accounts.setAcc_ID(Acc_ID);
		accounts.setAcc_name("Johnson" + RandomUtils.getRandomString(5));
		accounts.setAcc_pwd("moon" + RandomUtils.getRandomString(5));
		accounts.setAcc_sex("男");
		accounts.setAcc_loc("浙江工业大学屛峰校区");
		accounts.setAcc_addTime(DateUtils.currentDateTime());
		this.accountsDao.saveAccounts(accounts);
		Accounts accounts2 = new Accounts();
		accounts2.setAcc_ID(Rep_ID);
		accounts2.setAcc_name("Johnson" + RandomUtils.getRandomString(5));
		accounts2.setAcc_pwd("moon" + RandomUtils.getRandomString(5));
		accounts2.setAcc_sex("男");
		accounts2.setAcc_loc("浙江工业大学屛峰校区");
		accounts2.setAcc_addTime(DateUtils.currentDateTime());
		this.accountsDao.saveAccounts(accounts2);
		for (int i = 0; i < 50; i++) {
			String Crs_ID = RandomUtils.getRandomString(15) + "Crs";
			String Crs_name = RandomUtils.getRandomString(30);
			Crs_IDList.add(Crs_ID);
			Courses courses = new Courses();
			courses.setCrs_ID(Crs_ID);
			courses.setAcc_ID(Acc_ID);
			courses.setAuthor_ID(Rep_ID);
			courses.setCrs_addTime(DateUtils.currentDateTime());
			courses.setCrs_name(Crs_name);
			courses.setCrs_route(RandomUtils.getRandomString(66));
			this.coursesDao.saveCourses(courses);
			// --
			this.repostCrsDao.saveRepostCrs(Acc_ID, Rep_ID, Crs_ID, DateUtils.currentDateTime());
		}
	}

	@Test
	public void testSaveRepostCrs() {
		boolean flag = true;
		for (int i = 0; i < 10; i++) {
			String Crs_ID = RandomUtils.getRandomString(15) + "Crs";
			String Crs_name = RandomUtils.getRandomString(30);
			Courses courses = new Courses();
			courses.setCrs_ID(Crs_ID);
			courses.setAcc_ID(Acc_ID);
			courses.setAuthor_ID(Rep_ID);
			courses.setCrs_addTime(DateUtils.currentDateTime());
			courses.setCrs_name(Crs_name);
			courses.setCrs_route(RandomUtils.getRandomString(66));
			this.coursesDao.saveCourses(courses);
			// --
			flag = flag && this.repostCrsDao.saveRepostCrs(Acc_ID, Rep_ID, Crs_ID, DateUtils.currentDateTime());
		}
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testDeleteRepostCrsStringStringString() {
		boolean flag = true;
		for (int i = 0; i < 20; i++) {
			flag = flag && this.repostCrsDao.deleteRepostCrs(Acc_ID, Rep_ID, Crs_IDList.get(i));
		}
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testDeleteRepostCrsStringString() {
		boolean flag = this.repostCrsDao.deleteRepostCrs(Acc_ID, Rep_ID);
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testDeleteRepostCrsAll() {
		boolean flag = this.repostCrsDao.deleteRepostCrsAll(Acc_ID);
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testQueryByAccountId() {
		List<String> Crs_IDList = this.repostCrsDao.queryByAccountId(Acc_ID);
		Assert.assertNotNull(Crs_IDList);
		for (String postId : Crs_IDList) {
			System.out.println(postId);
		}
	}

	@Test
	public void testQuery() {
		List<String> Crs_IDList = this.repostCrsDao.query(Acc_ID, Rep_ID);
		Assert.assertNotNull(Crs_IDList);
		for (String postId : Crs_IDList) {
			System.out.println(postId);
		}
	}
}
