package test.xuyihao.dao;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import test.xuyihao.CommonTest;
import xuyihao.dao.AccountsDao;
import xuyihao.dao.CategoryDao;
import xuyihao.dao.CoursesDao;
import xuyihao.dao.PostsDao;
import xuyihao.dao.ProductsDao;
import xuyihao.dao.ShopsDao;
import xuyihao.entity.Accounts;
import xuyihao.entity.Category;
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

	@Autowired
	private AccountsDao accountsDao;

	@Autowired
	private CategoryDao categoryDao;

	public void setAccountsDao(AccountsDao accountsDao) {
		this.accountsDao = accountsDao;
	}

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

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
		String Cat_ID = RandomUtils.getRandomNumberString(15) + "Cat";
		String Acc_ID = RandomUtils.getRandomNumberString(15) + "Acc";
		Accounts accounts = new Accounts();
		accounts.setAcc_ID(Acc_ID);
		accounts.setAcc_name("Johnson" + RandomUtils.getRandomString(5));
		accounts.setAcc_pwd("moon" + RandomUtils.getRandomString(5));
		accounts.setAcc_sex("男");
		accounts.setAcc_loc("浙江工业大学屛峰校区");
		accounts.setAcc_addTime(DateUtils.currentDateTime());
		this.accountsDao.saveAccounts(accounts);
		Category category = new Category();
		category.setCat_ID(Cat_ID);
		category.setCat_name(RandomUtils.getRandomString(5));
		category.setCat_desc("产品描述测试" + RandomUtils.getRandomString(5));
		category.setCat_addTime(DateUtils.currentDateTime());
		this.categoryDao.saveCategory(category);
		for (int i = 0; i < 50; i++) {
			Courses course = new Courses();
			course.setAcc_ID(Acc_ID);
			course.setAuthor_ID(Acc_ID);
			course.setCrs_addTime(DateUtils.currentDateTime());
			course.setCrs_ID(RandomUtils.getRandomString(15) + "Crs");
			course.setCrs_name(RandomUtils.getRandomString(23));
			this.coursesDao.saveCourses(course);
			Posts post = new Posts();
			post.setAcc_ID(Acc_ID);
			post.setAuthor_ID(Acc_ID);
			post.setPost_addTime(DateUtils.currentDateTime());
			post.setPost_ID(RandomUtils.getRandomString(15) + "Post");
			post.setPost_name(RandomUtils.getRandomString(23));
			this.postsDao.savePosts(post);
			Shops shop = new Shops();
			shop.setAcc_ID(Acc_ID);
			String Shop_ID = RandomUtils.getRandomString(15) + "Shop";
			shop.setShop_ID(Shop_ID);
			shop.setShop_name(RandomUtils.getRandomString(12));
			shop.setShop_addTime(DateUtils.currentDateTime());
			this.shopsDao.saveShops(shop);
			Products product = new Products();
			product.setCat_ID(Cat_ID);
			product.setShop_ID(Shop_ID);
			product.setProd_ID(RandomUtils.getRandomString(15) + "Prod");
			product.setProd_name(RandomUtils.getRandomString(23));
			product.setProd_addTime(DateUtils.currentDateTime());
			this.productsDao.saveProducts(product);
		}
	}

	@Ignore
	public void testCoursesLimitQuery() {
		List<Courses> coursesList = this.coursesDao.queryByLimitOrdered(Courses.BASE_COURSES_PHYSICAL_ID, -1, 0, 5);
		for (Courses course : coursesList) {
			System.out.println(course.toJSONString());
		}
	}

	@Ignore
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
