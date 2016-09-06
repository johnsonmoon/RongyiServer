package test.xuyihao.logic;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import test.xuyihao.CommonTest;
import xuyihao.dao.CoursesDao;
import xuyihao.entity.CommentCrs;
import xuyihao.entity.Courses;
import xuyihao.logic.CommentCrsLogic;
import xuyihao.tools.utils.RandomUtils;

public class CommentCrsLogicTest extends CommonTest {
	private static List<String> Comm_IDList = new ArrayList<>();
	private static String Crs_ID = null;

	@Autowired
	private CommentCrsLogic commentCrsLogic;

	@Autowired
	private CoursesDao coursesDao;

	public void setCommentCrsLogic(CommentCrsLogic commentCrsLogic) {
		this.commentCrsLogic = commentCrsLogic;
	}

	public void setCoursesDao(CoursesDao coursesDao) {
		this.coursesDao = coursesDao;
	}

	@Before
	public void setUp() throws Exception {
		Comm_IDList.clear();
		Crs_ID = RandomUtils.getRandomString(15) + "Crs";
		Courses course = new Courses();
		course.setCrs_ID(Crs_ID);
		course.setCrs_name(RandomUtils.getRandomString(12));
		this.coursesDao.saveCourses(course);
		for (int i = 0; i < 10; i++) {
			CommentCrs commentCrs = new CommentCrs();
			commentCrs.setAcc_ID(RandomUtils.getRandomString(15) + "Acc");
			commentCrs.setComm_desc(RandomUtils.getRandomString(23));
			commentCrs.setCrs_ID(Crs_ID);
			commentCrs.setRep_ID(RandomUtils.getRandomString(15) + "Acc");
			String Comm_ID = this.commentCrsLogic.saveCommentCrs(commentCrs);
			Comm_IDList.add(Comm_ID);
		}
	}

	@Test
	public void testSaveCommentCrs() {
		for (int i = 0; i < 10; i++) {
			CommentCrs commentCrs = new CommentCrs();
			commentCrs.setAcc_ID(RandomUtils.getRandomString(15) + "Acc");
			commentCrs.setComm_desc(RandomUtils.getRandomString(23));
			commentCrs.setCrs_ID(Crs_ID);
			commentCrs.setRep_ID(RandomUtils.getRandomString(15) + "Acc");
			String Comm_ID = this.commentCrsLogic.saveCommentCrs(commentCrs);
			Assert.assertNotSame("", Comm_ID);
		}
	}

	@Test
	public void testDeleteCommentCrs() {
		Courses cou = this.coursesDao.queryById(Crs_ID);
		System.out.println(cou.getCrs_comm());
		for (String id : Comm_IDList) {
			boolean flag = this.commentCrsLogic.deleteCommentCrs(id);
			Assert.assertEquals(true, flag);
		}
		Courses couNew = this.coursesDao.queryById(Crs_ID);
		System.out.println(couNew.getCrs_comm());
	}

	@Test
	public void testGetCommentCrsInfo() {
		for (String id : Comm_IDList) {
			CommentCrs commentCrs = this.commentCrsLogic.getCommentCrsInfo(id);
			Assert.assertNotSame(0, commentCrs.get_id());
			System.out.println(commentCrs.toJSONString());
		}
	}
}
