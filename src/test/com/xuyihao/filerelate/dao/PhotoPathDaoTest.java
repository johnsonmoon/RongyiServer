package test.com.xuyihao.filerelate.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xuyihao.filerelate.dao.PhotoPathDao;
import com.xuyihao.filerelate.entity.PhotoPath;
import com.xuyihao.tools.utils.DateUtils;
import com.xuyihao.tools.utils.RandomUtils;

import junit.framework.Assert;
import test.com.xuyihao.CommonTest;

/**
 * 
 * @author Xuyh at 2016年9月2日 下午11:28:32.
 *
 */
public class PhotoPathDaoTest extends CommonTest {
	private List<PhotoPath> photoPathList = new ArrayList<PhotoPath>();

	@Autowired
	private PhotoPathDao photoPathDao;

	public void setPhotoPathDao(PhotoPathDao photoPathDao) {
		this.photoPathDao = photoPathDao;
	}

	@Before
	public void setUp() {
		for (int i = 0; i < 10; i++) {
			PhotoPath photo = new PhotoPath();
			photo.setPhoto_addTime(DateUtils.currentDateTime());
			photo.setPhoto_ID(RandomUtils.getRandomString(15) + "Photo");
			photo.setPhoto_pathName(RandomUtils.getRandomString(22));
			photo.setThumbnail_pathName(RandomUtils.getRandomString(40));
			photoPathList.add(photo);
			this.photoPathDao.savePhotoPath(photo);
		}
	}

	@Test
	public void test() {
		for (int i = 0; i < 10; i++) {
			PhotoPath photo = new PhotoPath();
			photo.setPhoto_addTime(DateUtils.currentDateTime());
			photo.setPhoto_ID(RandomUtils.getRandomString(15) + "Photo");
			photo.setPhoto_pathName(RandomUtils.getRandomString(22));
			photo.setThumbnail_pathName(RandomUtils.getRandomString(40));
			photoPathList.add(photo);
			Assert.assertEquals(true, this.photoPathDao.savePhotoPath(photo));
		}
		for (PhotoPath photo : photoPathList) {
			System.out.println(this.photoPathDao.queryById(photo.getPhoto_ID()).toJSINString());
			photo.setPhoto_pathName("KSHIOHF");
			Assert.assertEquals(true, this.photoPathDao.updatePhotoPath(photo));
			Assert.assertEquals(true, this.photoPathDao.deletePhotoPath(photo.getPhoto_ID()));
		}
	}
}