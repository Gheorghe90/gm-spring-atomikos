package tx;

import com.test.weblogic.spring.config.PersistenceContext;
import com.test.weblogic.spring.model.User;
import com.test.weblogic.spring.user.UserManager;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.transaction.jta.UserTransactionAdapter;

import javax.naming.NamingException;
import javax.transaction.TransactionManager;
import java.util.List;

import static org.mockito.Mockito.mock;

public class MainTest {


	@BeforeClass
	public static void setup() throws NamingException {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://192.168.99.100:32768/mysql");
		dataSource.setUsername("root");
		dataSource.setPassword("secret");

		SimpleNamingContextBuilder builder = new SimpleNamingContextBuilder();
		builder.bind("jdbc/testJndi", dataSource);
		builder.bind("java:comp/UserTransaction", new UserTransactionAdapter(mock(TransactionManager.class)));
		builder.activate();
	}

	@Test
	public void main() {

		ApplicationContext ctx = new AnnotationConfigApplicationContext(PersistenceContext.class);

		UserManager userManager = (UserManager) ctx.getBean("userManagerImpl");

		List<User> list = userManager.findAllUsers();
		System.out.println("User count: " + list.size());

		User user = new User();
		user.setUsername("johndoe");
		user.setName("John Doe");
		userManager.insertUser(user);
		System.out.println("User inserted!");

		list = userManager.findAllUsers();
		System.out.println("User count: " + list.size());
	}
}
