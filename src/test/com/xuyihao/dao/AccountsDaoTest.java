package test.com.xuyihao.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import test.com.xuyihao.CommonTest;

import com.xuyihao.dao.AccountsDao;
import com.xuyihao.entity.Accounts;
import com.xuyihao.tools.utils.DateUtils;
import com.xuyihao.tools.utils.RandomUtils;

/**
 * 
 * @Author Xuyh created at Aug 14, 2016 3:10:07 PM.
 *
 */
public class AccountsDaoTest extends CommonTest {
	private static List<String> Acc_IDList = new ArrayList<>();

	@Autowired
	private AccountsDao accountsDao;

	public void setAccountsDao(AccountsDao accountsDao) {
		this.accountsDao = accountsDao;
	}

	@Before
	public void setUp() {
		Acc_IDList.clear();
		for (int i = 0; i < 50; i++) {
			Accounts accounts = new Accounts();
			String Acc_ID = RandomUtils.getRandomString(15) + "Acc";
			accounts.setAcc_ID(Acc_ID);
			Acc_IDList.add(Acc_ID);
			accounts.setAcc_name("Johnson" + i);
			accounts.setAcc_pwd("moon" + i);
			accounts.setAcc_sex("男");
			accounts.setAcc_loc("浙江工业大学屛峰校区");
			accounts.setAcc_addTime(DateUtils.currentDateTime());
			this.accountsDao.saveAccounts(accounts);
		}
	}

	@Test
	public void testSaveAccounts() {
		boolean flag = true;
		for (int i = 0; i < 10; i++) {
			Accounts accounts = new Accounts();
			accounts.setAcc_ID(RandomUtils.getRandomString(15) + "Acc");
			accounts.setAcc_name("Johnson" + i);
			accounts.setAcc_pwd("moon" + i);
			accounts.setAcc_sex("男");
			accounts.setAcc_loc("浙江工业大学屛峰校区");
			accounts.setAcc_addTime(DateUtils.currentDateTime());
			flag = flag && this.accountsDao.saveAccounts(accounts);
		}
		Assert.assertTrue(flag);
	}

	@Test
	public void testDeleteAccounts() {
		boolean flag = true;
		for (int i = 44; i < Acc_IDList.size(); i++) {
			flag = flag && this.accountsDao.deleteAccounts(Acc_IDList.get(i));
		}
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testUpdateAccountsAccounts() {
		boolean flag = true;
		for (int i = 0; i <= 20; i++) {
			Accounts account = this.accountsDao.queryById(Acc_IDList.get(i));
			account.setAcc_pwd("11009922338877");
			account.setAcc_loc("哈哈哈哈哈哈哈");
			flag = flag && this.accountsDao.updateAccounts(account);
		}
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testQueryByName() {
		String Acc_name = "Johnson8";
		Accounts account = this.accountsDao.queryByName(Acc_name);
		Assert.assertNotNull(account);
		System.out.println(account.toJSONString());
		System.out.println();
	}

	@Test
	public void testQueryById() {
		for (int i = 0; i < 44; i++) {
			Accounts account = this.accountsDao.queryById(Acc_IDList.get(i));
			Assert.assertNotNull(account);
			System.out.println(account.toJSONString());
			System.out.println();
		}
	}
}
