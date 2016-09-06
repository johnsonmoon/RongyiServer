package test.xuyihao.filerelate.dao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import junit.framework.Assert;
import test.xuyihao.CommonTest;
import xuyihao.filerelate.dao.AccountsPhotosDao;
import xuyihao.filerelate.entity.AccountsPhotos;
import xuyihao.tools.utils.DateUtils;
import xuyihao.tools.utils.RandomUtils;

public class AccountsPhotosDaoTest extends CommonTest {
	private List<AccountsPhotos> accountsPhotosList = new ArrayList<AccountsPhotos>();

	@Autowired
	private AccountsPhotosDao accountsPhotosDao;

	public void setAccountsPhotosDao(AccountsPhotosDao accountsPhotosDao) {
		this.accountsPhotosDao = accountsPhotosDao;
	}

	@Before
	public void setUp() {
		for (int i = 0; i < 10; i++) {
			AccountsPhotos accountsPhotos = new AccountsPhotos();
			accountsPhotos.setAcc_ID(RandomUtils.getRandomString(15) + "Acc");
			accountsPhotos.setAccPhoto_addTime(DateUtils.currentDateTime());
			accountsPhotos.setHeadPhoto_ID(RandomUtils.getRandomString(15) + "Photo");
			accountsPhotos.setPhoto_ID_Combine(RandomUtils.getRandomString(15) + "Photo" + "&&"
					+ RandomUtils.getRandomString(15) + "Photo" + "&&" + RandomUtils.getRandomString(15) + "Photo");
			this.accountsPhotosList.add(accountsPhotos);
			this.accountsPhotosDao.saveAccountsPhotos(accountsPhotos);
		}
	}

	@Test
	public void test() {
		for (int i = 0; i < 10; i++) {
			AccountsPhotos accountsPhotos = new AccountsPhotos();
			accountsPhotos.setAcc_ID(RandomUtils.getRandomString(15) + "Acc");
			accountsPhotos.setAccPhoto_addTime(DateUtils.currentDateTime());
			accountsPhotos.setHeadPhoto_ID(RandomUtils.getRandomString(15) + "Photo");
			accountsPhotos.setPhoto_ID_Combine(RandomUtils.getRandomString(15) + "Photo" + "&&"
					+ RandomUtils.getRandomString(15) + "Photo" + "&&" + RandomUtils.getRandomString(15) + "Photo");
			this.accountsPhotosList.add(accountsPhotos);
			Assert.assertEquals(true, this.accountsPhotosDao.saveAccountsPhotos(accountsPhotos));
		}
		for (AccountsPhotos photo : accountsPhotosList) {
			System.out.println(this.accountsPhotosDao.query(photo.getAcc_ID()).toJSONString());
			photo.setHeadPhoto_ID("Photo");
			Assert.assertEquals(true, this.accountsPhotosDao.updateAccountsPhotos(photo));
			Assert.assertEquals(true, this.accountsPhotosDao.deleteAccountsPhotos(photo.getAcc_ID()));
		}
	}
}