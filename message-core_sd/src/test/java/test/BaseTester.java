package test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations={"/spring-mybatis.xml"})
public class BaseTester extends AbstractJUnit4SpringContextTests {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
}
