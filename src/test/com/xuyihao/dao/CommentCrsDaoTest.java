package test.com.xuyihao.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import test.com.xuyihao.CommonTest;

import com.xuyihao.dao.AccountsDao;
import com.xuyihao.dao.CommentCrsDao;
import com.xuyihao.dao.CoursesDao;
import com.xuyihao.entity.Accounts;
import com.xuyihao.entity.CommentCrs;
import com.xuyihao.entity.Courses;
import com.xuyihao.tools.utils.DateUtils;
import com.xuyihao.tools.utils.RandomUtils;

/**
 * 
 * @Author Xuyh created at Aug 14, 2016 3:21:31 PM.
 *
 */
public class CommentCrsDaoTest extends CommonTest {
	private static List<String> Comm_IDList = new ArrayList<>();
	private static String Acc_ID = null;
	private static String Rep_ID = null;
	private static String Crs_ID = null;

	@Autowired
	private AccountsDao accountsDao;

	@Autowired
	private CommentCrsDao commentCrsDao;

	@Autowired
	private CoursesDao coursesDao;

	public void setCoursesDao(CoursesDao coursesDao) {
		this.coursesDao = coursesDao;
	}

	public void setAccountsDao(AccountsDao accountsDao) {
		this.accountsDao = accountsDao;
	}

	public void setCommentCrsDao(CommentCrsDao commentCrsDao) {
		this.commentCrsDao = commentCrsDao;
	}

	@Before
	public void setUp() {
		Comm_IDList.clear();
		Acc_ID = RandomUtils.getRandomString(15) + "Acc";
		Rep_ID = RandomUtils.getRandomString(15) + "Acc";
		Crs_ID = RandomUtils.getRandomString(15) + "Crs";
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
		Courses course = new Courses();
		course.setCrs_ID(Crs_ID);
		course.setAcc_ID(Acc_ID);
		course.setAuthor_ID(Rep_ID);
		course.setCrs_addTime(DateUtils.currentDateTime());
		course.setCrs_name(RandomUtils.getRandomString(30));
		course.setCrs_route(RandomUtils.getRandomString(40));
		this.coursesDao.saveCourses(course);
		for (int i = 0; i < 50; i++) {
			String Comm_ID = RandomUtils.getRandomString(15) + "Comm";
			Comm_IDList.add(Comm_ID);
			CommentCrs commentCrs = new CommentCrs();
			commentCrs.setAcc_ID(Acc_ID);
			commentCrs.setRep_ID(Rep_ID);
			commentCrs.setComm_addTime(DateUtils.currentDateTime());
			commentCrs.setComm_desc("lalalalala");
			commentCrs.setComm_ID(Comm_ID);
			commentCrs.setCrs_ID(Crs_ID);
			this.commentCrsDao.saveCommentCrs(commentCrs);
		}
	}

	@Test
	public void testSaveCommentCrs() {
		boolean flag = true;
		for (int i = 0; i < 10; i++) {
			CommentCrs commentCrs = new CommentCrs();
			commentCrs.setAcc_ID(Acc_ID);
			commentCrs.setRep_ID(Rep_ID);
			commentCrs.setComm_addTime(DateUtils.currentDateTime());
			commentCrs.setComm_desc("lalalalala");
			commentCrs.setComm_ID(RandomUtils.getRandomString(15) + "Comm");
			commentCrs.setCrs_ID(Crs_ID);
			flag = flag && this.commentCrsDao.saveCommentCrs(commentCrs);
		}
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testDeleteCommentCrs() {
		boolean flag = true;
		for (int i = 45; i < Comm_IDList.size(); i++) {
			flag = flag && this.commentCrsDao.deleteCommentCrs(Comm_IDList.get(i));
		}
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testUpdateCommentCrsCommentCrs() {
		boolean flag = true;
		for (int i = 0; i < 20; i++) {
			CommentCrs commentCrs = this.commentCrsDao.queryById(Comm_IDList.get(i));
			commentCrs.setComm_desc("ljkahfiuhfguiaehbfvuhyieabhgfvuyhaebghrfvyuhabgfrv");
			flag = flag && this.commentCrsDao.updateCommentCrs(commentCrs);
		}
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testQueryById() {
		for (String Comm_ID : Comm_IDList) {
			CommentCrs commentCrs = this.commentCrsDao.queryById(Comm_ID);
			Assert.assertNotNull(commentCrs);
			System.out.println(commentCrs.toJSONString());
		}
	}

	@Test
	public void testQueryByAccountId() {
		List<CommentCrs> commentCrsList = this.commentCrsDao.queryByAccountId(Acc_ID);
		Assert.assertNotNull(commentCrsList);
		for (CommentCrs commentCrs : commentCrsList) {
			System.out.println(commentCrs.toJSONString());
		}
	}

	@Test
	public void testQueryByReporterId() {
		List<CommentCrs> commentCrsList = this.commentCrsDao.queryByReporterId(Rep_ID);
		Assert.assertNotNull(commentCrsList);
		for (CommentCrs commentCrs : commentCrsList) {
			System.out.println(commentCrs.toJSONString());
		}
	}

	@Test
	public void testQueryByCourseId() {
		List<CommentCrs> commentCrsList = this.commentCrsDao.queryByCourseId(Crs_ID);
		Assert.assertNotNull(commentCrsList);
		for (CommentCrs commentCrs : commentCrsList) {
			System.out.println(commentCrs.toJSONString());
		}
	}
}
