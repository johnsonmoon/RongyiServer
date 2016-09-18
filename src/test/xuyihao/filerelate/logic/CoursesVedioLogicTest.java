package test.xuyihao.filerelate.logic;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import junit.framework.Assert;
import test.xuyihao.CommonTest;
import xuyihao.filerelate.logic.CoursesVedioLogic;
import xuyihao.tools.utils.RandomUtils;

/**
 * 
 * @Author Xuyh created at 2016年9月18日 下午5:45:24
 */
public class CoursesVedioLogicTest extends CommonTest {
	@Autowired
	private CoursesVedioLogic coursesVedioLogic;

	private List<String> Crs_IDList = new ArrayList<String>();

	public void setCoursesVedioLogic(CoursesVedioLogic coursesVedioLogic) {
		this.coursesVedioLogic = coursesVedioLogic;
	}

	@Test
	public void test() {
		for (int i = 0; i < 20; i++) {
			String Crs_ID = RandomUtils.getRandomString(15) + "Crs";
			this.Crs_IDList.add(Crs_ID);
			boolean flag = this.coursesVedioLogic.saveCoursesVedio(Crs_ID, RandomUtils.getRandomString(15) + "Vedio");
			Assert.assertEquals(true, flag);
		}
		for (int j = 10; j < 20; j++) {
			boolean flag = this.coursesVedioLogic.deleteCoursesVedio(Crs_IDList.get(j));
			Assert.assertEquals(true, flag);
		}
		for (int p = 0; p < 10; p++) {
			System.out.println(this.coursesVedioLogic.getCoursesVedioInfo(Crs_IDList.get(p)).toJSONString());
		}
		for (int k = 0; k < 10; k++) {
			boolean flag = this.coursesVedioLogic.changeCoursesVedioInfo(Crs_IDList.get(k), "HJGHJH");
			Assert.assertEquals(true, flag);
		}
		for (int p = 0; p < 10; p++) {
			System.out.println(this.coursesVedioLogic.getCoursesVedioInfo(Crs_IDList.get(p)).toJSONString());
		}
	}
}