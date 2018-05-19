package test;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;

import com.message.domain.GsUser;
import com.message.service.IGsUserService;
import com.message.utils.MysqlBackUp;
public class Test1 extends BaseTester{

	@Resource
	private IGsUserService gsUserService;
	@Value("${back.username}")
	private String userName;
	@Value("${back.password}")
	private String password;
	@Value("${back.dbname}")
	private String dbName;
	@Value("${back.path}")
	private String mysqlBackupPath;
	
	@Test
	public void testGetUsers(){
		List<GsUser> list = gsUserService.getAllGsUsers("1");
		for(GsUser user : list){
			logger.info(user.getGsName() + user.getPws() + user.getGsNum() + user.getTyFlag());
		}
	}
	@Test
	public void testBackUp(){
		String command = "mysqldump -u" + userName + " -p" + password + " " + dbName;
		MysqlBackUp.backUp(dbName, mysqlBackupPath, command);
	}

}
