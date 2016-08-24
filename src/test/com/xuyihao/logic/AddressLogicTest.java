package test.com.xuyihao.logic;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xuyihao.dao.AccountsDao;
import com.xuyihao.entity.Accounts;
import com.xuyihao.entity.Address;
import com.xuyihao.logic.AddressLogic;
import com.xuyihao.tools.utils.RandomUtils;

import junit.framework.Assert;
import test.com.xuyihao.CommonTest;

public class AddressLogicTest extends CommonTest {
	private static List<Address> addressList = new ArrayList<>();
	private static String Acc_ID = RandomUtils.getRandomString(15) + "Acc";

	@Autowired
	private AddressLogic addressLogic;

	@Autowired
	private AccountsDao accountsDao;

	public void setAccountsDao(AccountsDao accountsDao) {
		this.accountsDao = accountsDao;
	}

	public void setAddressLogic(AddressLogic addressLogic) {
		this.addressLogic = addressLogic;
	}

	@Before
	public void setUp() throws Exception {
		addressList.clear();
		Accounts accounts = new Accounts();
		accounts.setAcc_ID(Acc_ID);
		accounts.setAcc_name(RandomUtils.getRandomString(12));
		this.accountsDao.saveAccounts(accounts);
		for (int i = 0; i < 10; i++) {
			Address address = new Address();
			address.setAcc_ID(Acc_ID);
			address.setAdd_info("lahgfoauhfgouahn");
			address.setCon_tel(RandomUtils.getRandomNumberString(11));
			address.setConsign(RandomUtils.getRandomString(6));
			String Add_ID = this.addressLogic.saveAddress(address);
			addressList.add(this.addressLogic.getAddressInfo(Add_ID));
		}
	}

	@Test
	public void testSaveAddress() {
		for (int i = 0; i < 10; i++) {
			Address address = new Address();
			address.setAcc_ID(Acc_ID);
			address.setAdd_info("lahgfoauhfgouahn");
			address.setCon_tel(RandomUtils.getRandomNumberString(11));
			address.setConsign(RandomUtils.getRandomString(6));
			String result = this.addressLogic.saveAddress(address);
			Assert.assertNotSame("", result);
		}
	}

	@Test
	public void testGetAddressInfo() {
		for (Address address : addressList) {
			Address add = this.addressLogic.getAddressInfo(address.getAdd_ID());
			Assert.assertNotSame(0, add.get_id());
			System.out.println(add.toJSONString());
		}
	}

	@Test
	public void testChangeAddressInfo() {
		for (Address address : addressList) {
			System.out.println("Before: " + address.toJSONString());
			address.setAdd_info("yes!yes!yes!");
			address.setCon_tel("14556854565");
			Assert.assertEquals(true, this.addressLogic.changeAddressInfo(address));
			System.out.println("After: " + this.addressLogic.getAddressInfo(address.getAdd_ID()).toJSONString());
		}
	}

	@Test
	public void testDeleteAddress() {
		for (Address address : addressList) {
			Assert.assertEquals(true, this.addressLogic.deleteAddress(address.getAdd_ID()));
		}
	}
}
