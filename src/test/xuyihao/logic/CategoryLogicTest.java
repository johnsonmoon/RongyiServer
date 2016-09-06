package test.xuyihao.logic;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import test.xuyihao.CommonTest;
import xuyihao.entity.Category;
import xuyihao.logic.CategoryLogic;
import xuyihao.tools.utils.RandomUtils;

public class CategoryLogicTest extends CommonTest {
	private static List<Category> categoryList = new ArrayList<>();

	@Autowired
	private CategoryLogic categoryLogic;

	public void setCategoryLogic(CategoryLogic categoryLogic) {
		this.categoryLogic = categoryLogic;
	}

	@Before
	public void setUp() throws Exception {
		categoryList.clear();
		for (int i = 0; i < 10; i++) {
			Category category = new Category();
			category.setCat_name("快乐" + RandomUtils.getRandomString(10));
			category.setCat_desc(RandomUtils.getRandomString(36));
			String Cat_ID = this.categoryLogic.saveCategory(category);
			categoryList.add(this.categoryLogic.getCategoryInfoById(Cat_ID));
		}
	}

	@Test
	public void testSaveCategory() {
		for (int i = 0; i < 10; i++) {
			Category category = new Category();
			category.setCat_name("快乐" + RandomUtils.getRandomString(10));
			category.setCat_desc(RandomUtils.getRandomString(36));
			String Cat_ID = this.categoryLogic.saveCategory(category);
			Assert.assertNotSame("", Cat_ID);
		}
	}

	@Test
	public void testGetCategoryInfoById() {
		for (Category category : categoryList) {
			Category cat = this.categoryLogic.getCategoryInfoById(category.getCat_ID());
			Assert.assertNotSame("", cat.getCat_ID());
			System.out.println(cat.toJSONString());
		}
	}
}
