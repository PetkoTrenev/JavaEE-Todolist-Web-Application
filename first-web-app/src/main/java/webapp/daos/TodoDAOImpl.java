package webapp.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import webapp.models.Priority;
import webapp.models.Todo;
import webapp.util.DbUtil;

/**
 * The Job of this class is to get information from a database. To be able to
 * do CRUD operations on it.
 * 
 * @author Petko-PC
 *
 */
public class TodoDAOImpl implements TodoDAO
{
	private static final String UPDATE_TODO_QUERY = "update todo set name=?, category=?, priority=? where id=?";

	private static final String DELETE_FROM_TODOS_WHERE_NAME = "delete from todo where name = ?";

	private static final String INSERT_INTO_TODOS = "INSERT INTO todo (userId, id, name, category, priority) VALUES (?, ?, ?, ?, ?)";

	private static final String SELECT_ALL_TODOS = "select * from test.todo";

	Connection conn = null;

	public TodoDAOImpl() {
		conn = DbUtil.getConnection();
	}

	/**
	 * This method connects to the database and get all available students.
	 * 
	 * @return a list of all available todos from the database.
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public List<Todo> getTodos() throws SQLException
	{
		List<Todo> todos = new ArrayList<>();

		try (PreparedStatement ps = conn.prepareStatement(SELECT_ALL_TODOS); ResultSet rs = ps.executeQuery();) {
			while (rs.next()) {
				int todoId = rs.getInt("id");
				Long userId = rs.getLong("userId");
				String name = rs.getString("name");
				String category = rs.getString("category");
				String priorityAsString = rs.getString("priority");
				Priority priority = Priority.valueOf(priorityAsString);

				Todo todo = new Todo(todoId, userId, name, category, priority);
				todos.add(todo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return todos;
	}

	public void addTodo(Todo todo) throws SQLException
	{
		try (PreparedStatement statement = conn.prepareStatement(INSERT_INTO_TODOS);) {
			statement.setLong(1, todo.getUserId());
			statement.setInt(2, todo.getId());
			statement.setString(3, todo.getName());
			statement.setString(4, todo.getCategory());
			statement.setInt(5, todo.getPriority().getPriority());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void deleteTodo(Todo todo)
	{
		try (PreparedStatement preparedStatement = conn.prepareStatement(DELETE_FROM_TODOS_WHERE_NAME);) {
			preparedStatement.setString(1, todo.getName());

			// execute delete SQL stetement
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void updateTodo(Todo todo)
	{
		try (PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_TODO_QUERY);) {

			preparedStatement.setString(1, todo.getName());
			preparedStatement.setString(2, todo.getCategory());
			preparedStatement.setInt(3, todo.getPriority().getPriority());
			preparedStatement.setInt(4, todo.getId());
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {

		}
	}

}
