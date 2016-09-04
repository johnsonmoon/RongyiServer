package test.com.xuyihao.filerelate.logic;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xuyihao.filerelate.logic.VedioPathLogic;
import com.xuyihao.tools.utils.RandomUtils;

import junit.framework.Assert;
import test.com.xuyihao.CommonTest;

public class VedioPathLogicTest extends CommonTest {
	@Autowired
	private VedioPathLogic vedioPathLogic;

	public void setVedioPathLogic(VedioPathLogic vedioPathLogic) {
		this.vedioPathLogic = vedioPathLogic;
	}

	@Test
	public void test() {
		for(int i = 0; i < 10; i ++){
			String id = this.vedioPathLogic.saveVedioPath(RandomUtils.getRandomString(15) + "Vedio",
					RandomUtils.getRandomString(15) + "Vedio");
			Assert.assertEquals(true, this.vedioPathLogic.changeVedioPathInfo(id, "LLL", "YYY"));
			System.out.println(this.vedioPathLogic.getVedioPathInfo(id).toJSONString());
			Assert.assertEquals(true, this.vedioPathLogic.deleteVedioPath(id));
		}
	}
}