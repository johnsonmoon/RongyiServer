package test.xuyihao.dao;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import test.xuyihao.CommonTest;
import xuyihao.dao.CoursesDao;
import xuyihao.dao.PostsDao;
import xuyihao.dao.ProductsDao;
import xuyihao.dao.ShopsDao;
import xuyihao.entity.Courses;
import xuyihao.entity.Posts;
import xuyihao.entity.Products;
import xuyihao.entity.Shops;
import xuyihao.tools.utils.DateUtils;
import xuyihao.tools.utils.RandomUtils;

public class TestLimitQueryTest extends CommonTest {
	@Autowired
	private CoursesDao coursesDao;

	@Autowired
	private PostsDao postsDao;

	@Autowired
	private ProductsDao productsDao;

	@Autowired
	private ShopsDao shopsDao;

	public void setCoursesDao(CoursesDao coursesDao) {
		this.coursesDao = coursesDao;
	}

	public void setPostsDao(PostsDao postsDao) {
		this.postsDao = postsDao;
	}

	public void setProductsDao(ProductsDao productsDao) {
		this.productsDao = productsDao;
	}

	public void setShopsDao(ShopsDao shopsDao) {
		this.shopsDao = shopsDao;
	}

	@Before
	public void setUp() throws Exception {
		for (int i = 0; i < 50; i++) {
			Courses course = new Courses();
			course.setAcc_ID(RandomUtils.getRandomString(15) + "Acc");
			course.setAuthor_ID(RandomUtils.getRandomString(15) + "Acc");
			course.setCrs_addTime(DateUtils.currentDateTime());
			course.setCrs_ID(RandomUtils.getRandomString(15) + "Crs");
			course.setCrs_name(RandomUtils.getRandomString(23));
			this.coursesDao.saveCourses(course);
			Posts post = new Posts();
			post.setAcc_ID(RandomUtils.getRandomString(15) + "Acc");
			post.setAuthor_ID(RandomUtils.getRandomString(15) + "Acc");
			post.setPost_addTime(DateUtils.currentDateTime());
			post.setPost_ID(RandomUtils.getRandomString(15) + "Post");
			post.setPost_name(RandomUtils.getRandomString(23));
			this.postsDao.savePosts(post);
			Products product = new Products();
			product.setProd_ID(RandomUtils.getRandomString(15) + "Prod");
			product.setProd_name(RandomUtils.getRandomString(23));
			this.productsDao.saveProducts(product);
			Shops shop = new Shops();
			shop.setShop_ID(RandomUtils.getRandomString(15) + "Shop");
			shop.setShop_name(RandomUtils.getRandomString(12));
			this.shopsDao.saveShops(shop);
		}
	}

	@Test
	public void testCoursesLimitQuery() {
		List<Courses> coursesList = this.coursesDao.queryByLimitOrdered(Courses.BASE_COURSES_PHYSICAL_ID, -1, 0, 5);
		for (Courses course : coursesList) {
			System.out.println(course.toJSONString());
		}
	}

	@Test
	public void testPostsLimitQuery() {
		List<Posts> PostsList = this.postsDao.queryByLimitOrdered(Posts.BASE_POSTS_PHYSICAL_ID, -1, 0, 5);
		for (Posts Posts : PostsList) {
			System.out.println(Posts.toJSONString());
		}
	}

	// ----------------------------------------------------------有问题
	@Test
	public void testProductsLimitQuery() {
		List<Products> ProductsList = this.productsDao.queryByLimitOrdered(Products.BASE_PRODUCT_PHYSICAL_ID, -1, 0, 5);
		for (Products Products : ProductsList) {
			System.out.println(Products.toJSONString());
		}
	}

	// -----------------------------------------------------------有问题
	@Test
	public void testShopsLimitQuery() {
		List<Shops> ShopsList = this.shopsDao.queryByLimitOrdered(Shops.BASE_SHOP_PHYSICAL_ID, -1, 0, 5);
		for (Shops Shops : ShopsList) {
			System.out.println(Shops.toJSONString());
		}
	}
}
