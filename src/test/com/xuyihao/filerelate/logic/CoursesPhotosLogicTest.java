package test.com.xuyihao.filerelate.logic;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xuyihao.filerelate.logic.CoursesPhotosLogic;
import com.xuyihao.tools.utils.RandomUtils;

import junit.framework.Assert;
import test.com.xuyihao.CommonTest;

public class CoursesPhotosLogicTest extends CommonTest {
	@Autowired
	private CoursesPhotosLogic coursesPhotosLogic;

	public void setCoursesPhotosLogic(CoursesPhotosLogic coursesPhotosLogic) {
		this.coursesPhotosLogic = coursesPhotosLogic;
	}

	@Test
	public void test() {
		for (int i = 0; i < 10; i++) {

			String Crs_ID = RandomUtils.getRandomString(15) + "Crs";
			String HeadPhoto_ID = RandomUtils.getRandomString(15) + "Photo";
			List<String> CrsPhotoIdList = new ArrayList<String>();
			for (int j = 0; j < 20; j++) {
				CrsPhotoIdList.add(RandomUtils.getRandomString(15) + "Photo");
			}
			Assert.assertEquals(true, this.coursesPhotosLogic.saveCoursesPhotos(Crs_ID, HeadPhoto_ID, CrsPhotoIdList));
			Assert.assertEquals(true,
					this.coursesPhotosLogic.changeCoursesPhotosInfo(Crs_ID, "KKK", new ArrayList<String>()));
			System.out.println(this.coursesPhotosLogic.getCoursesPhototsInfo(Crs_ID).toJSONString());
			System.out.println(this.coursesPhotosLogic.getCoursesHeadPhoto(Crs_ID));
			this.coursesPhotosLogic.getCoursesOtherPhotos(Crs_ID);
			Assert.assertEquals(true, this.coursesPhotosLogic.deleteCoursesPhotos(Crs_ID));
		}
	}
}