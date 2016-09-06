package test.xuyihao.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import xuyihao.dao.AccountsDao;
import xuyihao.dao.AttentionDao;
import xuyihao.entity.Accounts;
import xuyihao.tools.utils.DateUtils;
import xuyihao.tools.utils.RandomUtils;
import junit.framework.Assert;
import test.xuyihao.CommonTest;

/**
 * 
 * @Author Xuyh created at Aug 14, 2016 3:16:52 PM.
 *
 */
public class AttentionDaoTest extends CommonTest {
	private static List<String> Acc_IDList = new ArrayList<>();
	private static List<String> Att_IDList = new ArrayList<>();

	@Autowired
	private AttentionDao attentionDao;

	@Autowired
	private AccountsDao accountsDao;

	public void setAccountsDao(AccountsDao accountsDao) {
		this.accountsDao = accountsDao;
	}

	public void setAttentionDao(AttentionDao attentionDao) {
		this.attentionDao = attentionDao;
	}

	@Before
	public void setUp() {
		Acc_IDList.clear();
		Att_IDList.clear();
		for (int i = 0; i < 50; i++) {
			Accounts accounts1 = new Accounts();
			String Acc_ID = RandomUtils.getRandomString(15) + "Acc";
			accounts1.setAcc_ID(Acc_ID);
			Acc_IDList.add(Acc_ID);
			accounts1.setAcc_name("Johnson" + RandomUtils.getRandomString(4));
			accounts1.setAcc_pwd("moon" + RandomUtils.getRandomString(4));
			accounts1.setAcc_sex("男");
			accounts1.setAcc_loc("浙江工业大学屛峰校区");
			accounts1.setAcc_addTime(DateUtils.currentDateTime());
			this.accountsDao.saveAccounts(accounts1);
			Accounts accounts2 = new Accounts();
			String Att_ID = RandomUtils.getRandomString(15) + "Acc";
			accounts2.setAcc_ID(Att_ID);
			Att_IDList.add(Att_ID);
			accounts2.setAcc_name("Johnson" + RandomUtils.getRandomString(4));
			accounts2.setAcc_pwd("moon" + RandomUtils.getRandomString(4));
			accounts2.setAcc_sex("男");
			accounts2.setAcc_loc("浙江工业大学屛峰校区");
			accounts2.setAcc_addTime(DateUtils.currentDateTime());
			this.accountsDao.saveAccounts(accounts2);
			String Att_addTime = DateUtils.currentDateTime();
			this.attentionDao.saveAttention(Acc_ID, Att_ID, Att_addTime);
		}
	}

	@Test
	public void testSaveAttention() {
		String Acc_Id = "a4d40c8a4hdci06Acc";
		String Att_Id = "6ihiu48gdb96840Acc";
		String Att_addTime = DateUtils.currentDateTime();
		boolean flag = this.attentionDao.saveAttention(Acc_Id, Att_Id, Att_addTime);
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testDeleteAttention() {
		boolean flag = true;
		for (int i = 44; i < Acc_IDList.size(); i++) {
			flag = flag && this.attentionDao.deleteAttention(Acc_IDList.get(i), Att_IDList.get(i));
		}
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testDeleteAttentionAll() {
		boolean flag = true;
		for (int i = 39; i < 44; i++) {
			flag = flag && this.attentionDao.deleteAttentionAll(Acc_IDList.get(i));
		}
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testQueryByAtn() {
		for (int i = 0; i < 39; i++) {
			List<String> resultList = this.attentionDao.queryByAtn(Acc_IDList.get(i));
			for (int j = 0; j < resultList.size(); j++) {
				System.out.println(resultList.get(j));
				Assert.assertNotNull(resultList);
			}
		}
	}

	@Test
	public void testQueryByAtned() {
		for (int i = 0; i < 39; i++) {
			List<String> resultList = this.attentionDao.queryByAtned(Att_IDList.get(i));
			for (int j = 0; j < resultList.size(); j++) {
				System.out.println(resultList.get(j));
				Assert.assertNotNull(resultList);
			}
		}
	}
}
