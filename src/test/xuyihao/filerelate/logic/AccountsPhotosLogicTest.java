package test.xuyihao.filerelate.logic;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import junit.framework.Assert;
import test.xuyihao.CommonTest;
import xuyihao.filerelate.logic.AccountsPhotosLogic;
import xuyihao.tools.utils.RandomUtils;

public class AccountsPhotosLogicTest extends CommonTest {
	@Autowired
	private AccountsPhotosLogic accountsPhotosLogic;

	public void setAccountsPhotosLogic(AccountsPhotosLogic accountsPhotosLogic) {
		this.accountsPhotosLogic = accountsPhotosLogic;
	}

	@Test
	public void test() {
		for (int i = 0; i < 10; i++) {
			String Acc_ID = RandomUtils.getRandomString(15) + "Acc";
			String headPhoto = "";
			headPhoto = RandomUtils.getRandomString(15) + "Photo";
			List<String> otherPhotos = new ArrayList<String>();
			for (int j = 0; j < 20; j++) {
				otherPhotos.add(RandomUtils.getRandomString(15) + "Photo");
			}
			Assert.assertEquals(true, this.accountsPhotosLogic.saveAccountsPhotos(Acc_ID, headPhoto, otherPhotos));
			System.out.println(this.accountsPhotosLogic.getAccountsPhotosInfo(Acc_ID).toJSONString());
			System.out.println(this.accountsPhotosLogic.getAccountsHeadPhoto(Acc_ID));
			this.accountsPhotosLogic.getAccountsOtherPhotos(Acc_ID);
			Assert.assertEquals(true,
					this.accountsPhotosLogic.changeAccountsPhotosInfo(Acc_ID, "ada", new ArrayList<String>()));
			Assert.assertEquals(true, this.accountsPhotosLogic.deleteAccountsPhotos(Acc_ID));
		}
	}
}