package test.xuyihao.filerelate.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import junit.framework.Assert;
import test.xuyihao.CommonTest;
import xuyihao.filerelate.dao.CoursesVedioDao;
import xuyihao.filerelate.entity.CoursesVedio;
import xuyihao.tools.utils.DateUtils;
import xuyihao.tools.utils.RandomUtils;

/**
 * 
 * @Author Xuyh created at 2016年9月18日 下午5:31:34
 */
public class CoursesVedioDaoTest extends CommonTest {
	@Autowired
	private CoursesVedioDao coursesVedioDao;

	private List<CoursesVedio> coursesVedioList = new ArrayList<CoursesVedio>();

	public void setCoursesVedioDao(CoursesVedioDao coursesVedioDao) {
		this.coursesVedioDao = coursesVedioDao;
	}

	@Test
	public void test() {
		for (int i = 0; i < 20; i++) {
			CoursesVedio coursesVedio = new CoursesVedio();
			coursesVedio.setCrs_ID(RandomUtils.getRandomString(15) + "Crs");
			coursesVedio.setVedio_ID(RandomUtils.getRandomString(15) + "Vedio");
			coursesVedio.setCrsVedio_addTime(DateUtils.currentDateTime());
			this.coursesVedioList.add(coursesVedio);
			boolean flag = this.coursesVedioDao.saveCoursesVedio(coursesVedio);
			Assert.assertEquals(true, flag);
		}
		for (int j = 10; j < 20; j++) {
			boolean flag = this.coursesVedioDao.deleteCoursesVedio(coursesVedioList.get(j).getCrs_ID());
			Assert.assertEquals(true, flag);
		}
		for (int p = 0; p < 10; p++) {
			System.out.println(this.coursesVedioDao.query(this.coursesVedioList.get(p).getCrs_ID()).toJSONString());
		}
		for (int k = 0; k < 10; k++) {
			CoursesVedio coursesVedio = coursesVedioList.get(k);
			coursesVedio.setVedio_ID("LLLPLPL");
			boolean flag = this.coursesVedioDao.updateCoursesVedio(coursesVedio);
			Assert.assertEquals(true, flag);
		}
		for (int p = 0; p < 10; p++) {
			System.out.println(this.coursesVedioDao.query(this.coursesVedioList.get(p).getCrs_ID()).toJSONString());
		}
	}
}