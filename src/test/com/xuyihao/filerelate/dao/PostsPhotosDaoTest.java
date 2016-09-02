package test.com.xuyihao.filerelate.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xuyihao.filerelate.dao.PostsPhotosDao;
import com.xuyihao.filerelate.entity.PostsPhotos;
import com.xuyihao.tools.utils.DateUtils;
import com.xuyihao.tools.utils.RandomUtils;

import junit.framework.Assert;
import test.com.xuyihao.CommonTest;

public class PostsPhotosDaoTest extends CommonTest {
	private List<PostsPhotos> postsPhotosList = new ArrayList<PostsPhotos>();

	public void setPostsPhotosDao(PostsPhotosDao postsPhotosDao) {
		this.postsPhotosDao = postsPhotosDao;
	}

	@Autowired
	private PostsPhotosDao postsPhotosDao;

	@Before
	public void setUp() {
		for (int i = 0; i < 10; i++) {
			PostsPhotos postsPhotos = new PostsPhotos();
			postsPhotos.setHeadPhoto_ID(RandomUtils.getRandomString(15) + "Photo");
			postsPhotos.setPhoto_ID_Combine(
					RandomUtils.getRandomString(15) + "Photo" + "&&" + RandomUtils.getRandomString(15) + "Photo" + "&&"
							+ RandomUtils.getRandomString(15) + "Photo" + "&&" + RandomUtils.getRandomString(15) + "Photo");
			postsPhotos.setPost_ID(RandomUtils.getRandomString(15) + "Post");
			postsPhotos.setPostPhoto_addTime(DateUtils.currentDateTime());
			this.postsPhotosList.add(postsPhotos);
			this.postsPhotosDao.savePostsPhotos(postsPhotos);
		}
	}

	@Test
	public void test() {
		for (int i = 0; i < 10; i++) {
			PostsPhotos postsPhotos = new PostsPhotos();
			postsPhotos.setHeadPhoto_ID(RandomUtils.getRandomString(15) + "Photo");
			postsPhotos.setPhoto_ID_Combine(
					RandomUtils.getRandomString(15) + "Photo" + "&&" + RandomUtils.getRandomString(15) + "Photo" + "&&"
							+ RandomUtils.getRandomString(15) + "Photo" + "&&" + RandomUtils.getRandomString(15) + "Photo");
			postsPhotos.setPost_ID(RandomUtils.getRandomString(15) + "Post");
			postsPhotos.setPostPhoto_addTime(DateUtils.currentDateTime());
			this.postsPhotosList.add(postsPhotos);
			Assert.assertEquals(true, this.postsPhotosDao.savePostsPhotos(postsPhotos));
		}
		for (PostsPhotos photo : postsPhotosList) {
			System.out.println(this.postsPhotosDao.query(photo.getPost_ID()).toJSONString());
			photo.setHeadPhoto_ID("PPOI");
			Assert.assertEquals(true, this.postsPhotosDao.updatePostsPhotos(photo));
			Assert.assertEquals(true, this.postsPhotosDao.deletePostsPhotos(photo.getPost_ID()));
		}
	}
}