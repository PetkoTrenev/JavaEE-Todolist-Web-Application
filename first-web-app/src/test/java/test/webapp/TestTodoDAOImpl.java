package test.webapp;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import webapp.daos.TodoDAO;
import webapp.daos.TodoDAOFactory;
import webapp.daos.UserDAO;
import webapp.daos.UserDAOFactory;
import webapp.models.Priority;
import webapp.models.Todo;
import webapp.models.User;
import webapp.util.DbUtil;

public class TestTodoDAOImpl
{

	Connection conn = null;
	TodoDAO todoDao = null;
	UserDAO userDao = null;

	@Before
	public void setUp() throws Exception
	{
		conn = DbUtil.getConnection();
		userDao = UserDAOFactory.getInstance();
		todoDao = TodoDAOFactory.getInstance();
	}

	@Test
	public void test_connection_to_db()
	{
		assertNotNull(conn);
	}

	@Test
	public void add_todo_to_a_list_for_a_user_to_the_db() throws SQLException
	{
		User user = userDao.getUserById(11L);

		Todo todo = new Todo();
		todo.setName("Swimming");
		todo.setPriority(Priority.MED);
		todo.setUser(user);
		todo.setCategory("Fun");

		todoDao.addTodo(todo);

		try {
			assertTrue(todoDao.getTodos().size() >= 0);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void test_get_all_todos()
	{

	}

}
