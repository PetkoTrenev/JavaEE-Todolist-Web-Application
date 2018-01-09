package test.webapp;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import webapp.daos.TodoDAO;
import webapp.daos.TodoDAOFactory;
import webapp.models.Priority;
import webapp.models.Todo;
import webapp.models.User;
import webapp.util.DbUtil;

public class TestTodoDAOImpl
{	
	
	Connection conn = null;
	TodoDAO dao = null;
	
	@Before
	public void setUp() throws Exception
	{
		conn = DbUtil.getConnection();
		dao = TodoDAOFactory.getInstance();
	}
	
	@Test
	public void test_connection_to_db()
	{
		assertNotNull(conn);
	}
	
	@Test
	public void add_user_to_the_db()
	{
		List<Todo> todos = new ArrayList<>();
		User user = new User(1l, "Gosho", "gosho@gmail.com", "p123", todos);
		Todo todo = new Todo(3, user.getId(), "Clean up code", "Errand", Priority.MED);
		try {
			dao.addTodo(todo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Test
	public void test_get_all_todos()
	{
		try {
			List<Todo> todos = dao.getTodos();
			assertEquals(2, todos.size());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
