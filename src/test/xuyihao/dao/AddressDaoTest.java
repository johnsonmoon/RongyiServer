package test.xuyihao.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import xuyihao.dao.AccountsDao;
import xuyihao.dao.AddressDao;
import xuyihao.entity.Accounts;
import xuyihao.entity.Address;
import xuyihao.tools.utils.DateUtils;
import xuyihao.tools.utils.RandomUtils;
import junit.framework.Assert;
import test.xuyihao.CommonTest;

/**
 * 
 * @Author Xuyh created at Aug 14, 2016 3:12:10 PM.
 *
 */
public class AddressDaoTest extends CommonTest {
	private static List<String> Add_IDList = new ArrayList<>();
	private final static String Acc_ID = RandomUtils.getRandomString(15) + "Acc";

	@Autowired
	private AddressDao addressDao;

	@Autowired
	private AccountsDao accountsDao;

	public void setAccountsDao(AccountsDao accountsDao) {
		this.accountsDao = accountsDao;
	}

	public void setAddressDao(AddressDao addressDao) {
		this.addressDao = addressDao;
	}

	@Before
	public void setUp() {
		Accounts accounts = new Accounts();
		accounts.setAcc_ID(Acc_ID);
		accounts.setAcc_name("John" + RandomUtils.getRandomString(3));
		accounts.setAcc_pwd("moon");
		accounts.setAcc_sex("男");
		accounts.setAcc_loc("浙江工业大学屛峰校区");
		accounts.setAcc_addTime(DateUtils.currentDateTime());
		this.accountsDao.saveAccounts(accounts);
		Add_IDList.clear();
		for (int i = 0; i < 50; i++) {
			Address address = new Address();
			String Add_ID = RandomUtils.getRandomString(15) + "Add";
			Add_IDList.add(Add_ID);
			address.setAdd_ID(Add_ID);
			address.setAcc_ID(Add_ID);
			address.setAdd_addTime(DateUtils.currentDateTime());
			address.setAdd_info("djilkafhuag" + RandomUtils.getRandomString(10));
			address.setCon_tel(RandomUtils.getRandomNumberString(11));
			this.addressDao.saveAddress(address);
		}
	}

	@Test
	public void testSaveAddress() {
		boolean flag = true;
		for (int i = 0; i < 10; i++) {
			Address address = new Address();
			address.setAdd_ID(RandomUtils.getRandomString(15) + "Add");
			address.setAdd_info("你好吗?");
			address.setAcc_ID("gg0icac7uu07qdaAcc");
			address.setConsign("兰兰" + i);
			address.setCon_tel("15700083" + i);
			address.setAdd_addTime(DateUtils.currentDateTime());
			flag = flag && this.addressDao.saveAddress(address);
		}
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testDeleteAddress() {
		boolean flag = true;
		for (int i = 44; i < Add_IDList.size(); i++) {
			flag = flag && this.addressDao.deleteAddress(Add_IDList.get(i));
		}
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testUpdateAddressAddress() {
		boolean flag = true;
		for (int i = 0; i < 25; i++) {
			Address address = this.addressDao.queryById(Add_IDList.get(i));
			address.setAdd_info("abcd");
			address.setConsign("huahua");
			address.setCon_tel("998");
			flag = flag && this.addressDao.updateAddress(address);
		}
		Assert.assertEquals(true, flag);
	}

	@Test
	public void testQueryByAccountId() {
		List<Address> addressList = this.addressDao.queryByAccountId(Acc_ID);
		for (int i = 0; i < addressList.size(); i++) {
			System.out.println(addressList.get(i).toJSONString());
		}
	}

	@Test
	public void testQueryById() {
		for (int i = 0; i < 45; i++) {
			Address address = this.addressDao.queryById(Add_IDList.get(i));
			System.out.println(address.toJSONString());
		}
	}
}
