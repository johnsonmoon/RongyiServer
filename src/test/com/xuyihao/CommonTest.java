package test.com.xuyihao;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 测试类的基类
 * 
 * @Author Xuyh created at 2016年8月19日 下午5:03:04
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:WebContent/WEB-INF/config/app-context-beans.xml" })
public class CommonTest extends AbstractJUnit4SpringContextTests {

}
