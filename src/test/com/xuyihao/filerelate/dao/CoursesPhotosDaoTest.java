package test.com.xuyihao.filerelate.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xuyihao.filerelate.dao.CoursesPhotosDao;
import com.xuyihao.filerelate.entity.CoursesPhotos;
import com.xuyihao.tools.utils.DateUtils;
import com.xuyihao.tools.utils.RandomUtils;

import test.com.xuyihao.CommonTest;

public class CoursesPhotosDaoTest extends CommonTest {
	private List<CoursesPhotos> coursesPhotosList = new ArrayList<CoursesPhotos>();

	@Autowired
	private CoursesPhotosDao coursesPhotosDao;

	public void setCoursesPhotosDao(CoursesPhotosDao coursesPhotosDao) {
		this.coursesPhotosDao = coursesPhotosDao;
	}

	@Before
	public void setUp() {
		for (int i = 0; i < 10; i++) {
			CoursesPhotos coursesPhotos = new CoursesPhotos();
			coursesPhotos.setCrs_ID(RandomUtils.getRandomString(15) + "Crs");
			coursesPhotos.setCrsPhoto_addTime(DateUtils.currentDateTime());
			coursesPhotos.setHeadPhoto_ID(RandomUtils.getRandomString(15) + "Photo");
			coursesPhotos.setPhoto_ID_Combine(
					RandomUtils.getRandomString(15) + "Photo" + "&&" + RandomUtils.getRandomString(15) + "Photo" + "&&"
							+ RandomUtils.getRandomString(15) + "Photo" + "&&" + RandomUtils.getRandomString(15) + "Photo");
			this.coursesPhotosList.add(coursesPhotos);
			this.coursesPhotosDao.saveCoursesPhotos(coursesPhotos);
		}
	}

	@Test
	public void test() {
		for (int i = 0; i < 10; i++) {
			CoursesPhotos coursesPhotos = new CoursesPhotos();
			coursesPhotos.setCrs_ID(RandomUtils.getRandomString(15) + "Crs");
			coursesPhotos.setCrsPhoto_addTime(DateUtils.currentDateTime());
			coursesPhotos.setHeadPhoto_ID(RandomUtils.getRandomString(15) + "Photo");
			coursesPhotos.setPhoto_ID_Combine(
					RandomUtils.getRandomString(15) + "Photo" + "&&" + RandomUtils.getRandomString(15) + "Photo" + "&&"
							+ RandomUtils.getRandomString(15) + "Photo" + "&&" + RandomUtils.getRandomString(15) + "Photo");
			this.coursesPhotosList.add(coursesPhotos);
			Assert.assertEquals(true, this.coursesPhotosDao.saveCoursesPhotos(coursesPhotos));
		}
		for (CoursesPhotos photo : coursesPhotosList) {
			System.out.println(this.coursesPhotosDao.query(photo.getCrs_ID()).toJSONString());
			photo.setHeadPhoto_ID("P");
			Assert.assertEquals(true, this.coursesPhotosDao.updateCoursesPhotos(photo));
			Assert.assertEquals(true, this.coursesPhotosDao.deleteCoursesPhotos(photo.getCrs_ID()));
		}
	}
}