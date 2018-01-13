package test.webapp;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import webapp.daos.TodoDAO;
import webapp.daos.TodoDAOFactory;
import webapp.daos.UserDAO;
import webapp.daos.UserDAOFactory;
import webapp.models.Todo;
import webapp.models.User;
import webapp.util.DbUtil;

public class TestUserDAOImpl
{
	Connection conn = null;
	UserDAO userDao = null;
	TodoDAO todoDao = null;

	@Before
	public void setUp() throws Exception
	{
		conn = DbUtil.getConnection();
		userDao = UserDAOFactory.getInstance();
		todoDao = TodoDAOFactory.getInstance();
	}
	
	@Test
	public void delete_user_by_id() throws SQLException
	{
		User userFound = userDao.getUserById(10L);
		userDao.delete(userFound);
		 
		assertEquals(2, userDao.getTotalUsers());
	}

	@Test
	public void update_user_info_by_id() throws Exception
	{
		String email = generateRandomEmail();
		
		User user = userDao.getUserById(1L);
		user.setEmail(email);
		user.setName("GoshoRevamped");
		userDao.updateUserInfo(user);

		User user2 = userDao.getUserById(1L);
		assertEquals("GoshoRevamped", user2.getName());
		assertEquals(email, user2.getEmail());
	}

	@Test
	public void creating_user_in_table() throws Exception
	{
		User user = new User();
		user.setName("Gosho");
		user.setPassword("aksdkaskdak");
		user.setEmail(generateRandomEmail());

		userDao.create(user);

		assertNotNull(user);
	}

	@Test
	public void getting_user_by_id() throws Exception
	{
		User actual = userDao.getUserById(1l);
		assertNotNull(actual);
	}

	@Test
	public void get_user_todos_by_id() throws Exception
	{
		User user = userDao.getUserById(1l);
		List<Todo> todos = userDao.getUserTodos(user);
		assertEquals(4, todos.size());
	}
	
	private static String generateRandomEmail() {
		return "randomEmail" + new Random().nextInt() + "@gmail.com";
	}
}
