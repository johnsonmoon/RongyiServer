package test.com.xuyihao.filerelate.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xuyihao.filerelate.dao.VedioPathDao;
import com.xuyihao.filerelate.entity.VedioPath;
import com.xuyihao.tools.utils.DateUtils;
import com.xuyihao.tools.utils.RandomUtils;

import junit.framework.Assert;
import test.com.xuyihao.CommonTest;

/**
 * 
 * @author Xuyh at 2016年9月2日 下午11:28:43.
 *
 */
public class VedioPathDaoTest extends CommonTest {
	private List<VedioPath> vedioPathList = new ArrayList<VedioPath>();

	@Autowired
	private VedioPathDao vedioPathDao;

	public void setVedioPathDao(VedioPathDao vedioPathDao) {
		this.vedioPathDao = vedioPathDao;
	}

	@Before
	public void setUp() {
		for (int i = 0; i < 10; i++) {
			VedioPath vedioPath = new VedioPath();
			vedioPath.setVedio_ID(RandomUtils.getRandomString(15) + "Vedio");
			vedioPath.setVedio_pathName(RandomUtils.getRandomString(20));
			vedioPath.setThumbnail_pathName(RandomUtils.getRandomString(40));
			vedioPath.setVedio_addTime(DateUtils.currentDateTime());
			this.vedioPathList.add(vedioPath);
			this.vedioPathDao.saveVedioPath(vedioPath);
		}
	}

	@Test
	public void test() {
		for (int i = 0; i < 10; i++) {
			VedioPath vedioPath = new VedioPath();
			vedioPath.setVedio_ID(RandomUtils.getRandomString(15) + "Vedio");
			vedioPath.setVedio_pathName(RandomUtils.getRandomString(20));
			vedioPath.setThumbnail_pathName(RandomUtils.getRandomString(40));
			vedioPath.setVedio_addTime(DateUtils.currentDateTime());
			this.vedioPathList.add(vedioPath);
			boolean flag = this.vedioPathDao.saveVedioPath(vedioPath);
			Assert.assertEquals(true, flag);
		}
		for (VedioPath vedio : vedioPathList) {
			VedioPath dbVedio = this.vedioPathDao.queryById(vedio.getVedio_ID());
			System.out.println(dbVedio.toJSONString());
			vedio.setVedio_pathName("/home/johnson/hahh");
			Assert.assertEquals(true, this.vedioPathDao.updateVedioPath(vedio));
			Assert.assertEquals(true, this.vedioPathDao.deleteVedioPath(vedio.getVedio_ID()));
		}
	}
}