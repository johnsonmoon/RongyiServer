package test.xuyihao.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import test.xuyihao.CommonTest;
import xuyihao.dao.CategoryDao;
import xuyihao.entity.Category;
import xuyihao.tools.utils.DateUtils;
import xuyihao.tools.utils.RandomUtils;

/**
 * 
 * @Author Xuyh created at Aug 14, 2016 3:20:25 PM.
 *
 */
public class CategoryDaoTest extends CommonTest {
	private static List<String> Cat_IDList = new ArrayList<String>();
	private static List<String> Cat_nameList = new ArrayList<String>();

	@Autowired
	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Before
	public void setUp() {
		Cat_IDList.clear();
		Cat_nameList.clear();
		for (int i = 0; i < 50; i++) {
			Category category = new Category();
			String Cat_ID = RandomUtils.getRandomString(15) + "Cat";
			category.setCat_ID(Cat_ID);
			Cat_IDList.add(Cat_ID);
			String Cat_name = RandomUtils.getRandomString(3);
			category.setCat_name(Cat_name);
			Cat_nameList.add(Cat_name);
			category.setCat_desc("产品描述测试" + i);
			category.setCat_addTime(DateUtils.currentDateTime());
			this.categoryDao.saveCategory(category);
		}
	}

	@Test
	public void testSaveCategory() {
		boolean flag = true;
		for (int i = 0; i < 10; i++) {
			Category category = new Category();
			category.setCat_ID(RandomUtils.getRandomString(15) + "Cat");
			category.setCat_name(RandomUtils.getRandomString(3));
			category.setCat_desc("产品描述测试" + i);
			category.setCat_addTime(DateUtils.currentDateTime());
			flag = flag && this.categoryDao.saveCategory(category);
		}
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testDeleteCategory() {
		boolean flag = true;
		for (int i = 0; i < Cat_IDList.size() - 20; i++) {
			flag = flag && this.categoryDao.deleteCategory(Cat_IDList.get(i));
		}
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testUpdateCategoryCategory() {
		boolean flag = true;
		for (int i = 0; i < 50; i++) {
			Category category = this.categoryDao.queryById(Cat_IDList.get(i));
			category.setCat_desc("旺旺小馒头!");
			flag = flag && this.categoryDao.updateCategory(category);
		}
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testQueryById() {
		for (int i = 0; i < Cat_IDList.size() - 30; i++) {
			Category category = this.categoryDao.queryById(Cat_IDList.get(i));
			System.out.println(category.toJSONString());
		}
	}

	@Test
	public void testQueryByName() {
		for (int i = 0; i < Cat_nameList.size() - 30; i++) {
			Category category = this.categoryDao.queryByName(Cat_nameList.get(i));
			System.out.println(category.toJSONString());
		}
	}
}
