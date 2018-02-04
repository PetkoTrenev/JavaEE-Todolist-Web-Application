package webapp.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import webapp.models.Priority;
import webapp.models.Todo;
import webapp.models.User;
import webapp.util.DbUtil;

/**
 * The Job of this class is to get information from a database. To be able to do
 * CRUD operations on it.
 * 
 * @author Petko-PC
 *
 */
public class TodoDAOImpl implements TodoDAO
{
	private static final String UPDATE_TODO_QUERY = "update todo set name=?, category=?, priority=? where id=?";

	private static final String DELETE_TODO_BY_ID = "delete from todo where id = ?";

	private static final String DELETE_TODO_BY_ID_AND_USER_ID = "delete from todo where id = ? AND userId = ?";

	private static final String INSERT_INTO_TODOS = "INSERT INTO todo (name, category, priority, userId) VALUES (?, ?, ?, ?)";

	private static final String SELECT_ALL_TODOS = "select * from test.todo";

	private static final String SELECT_TODOS_FOR_USER = "select * from test.todo where userId = ?";

	// TODO: Those have to be private ?
	Connection conn = null;

	UserDAO userDao = null;

	public TodoDAOImpl() {
		conn = DbUtil.getConnection();
		userDao = UserDAOFactory.getInstance();
	}

	/**
	 * This method connects to the database and get all available students. This
	 * feature will be for the admin role.
	 * 
	 * @return a list of all available todos from the database.
	 * @throws Exception
	 */
	@Override
	public List<Todo> getTodos() throws Exception
	{
		List<Todo> todos = new ArrayList<>();

		try (PreparedStatement ps = conn.prepareStatement(SELECT_ALL_TODOS)) {
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Todo todo = mapResultSetToTodo(rs);
					todos.add(todo);
				}
			}

		}
		return todos;
	}

	private Todo mapResultSetToTodo(ResultSet rs) throws SQLException
	{
		int todoId = rs.getInt("id");
		Long userId = rs.getLong("userId");
		String name = rs.getString("name");
		String category = rs.getString("category");
		Integer priorityAsInt = rs.getInt("priority");
		Priority priority = Priority.ofCode(priorityAsInt);

		User user = userDao.getUserById(userId);
		Todo todo = new Todo(todoId, user, name, category, priority);
		return todo;
	}

	/**
	 * This method will return all the todos that are associated to a particular
	 * user.
	 * 
	 * @param user
	 * @return
	 * @throws SQLException
	 */
	@Override
	public List<Todo> getTodosByUser(User user) throws SQLException
	{
		List<Todo> todos = new ArrayList<>();

		try (PreparedStatement ps = conn.prepareStatement(SELECT_TODOS_FOR_USER)) {
			ps.setLong(1, user.getId());
			ResultSet rs = ps.executeQuery();

			while (rs.next())
				todos.add(mapResultSetToTodo(rs));
		}

		return todos;

	}

	@Override
	public void addTodo(Todo todo) throws SQLException
	{
		try (PreparedStatement ps = conn.prepareStatement(INSERT_INTO_TODOS)) {
			ps.setString(1, todo.getName());
			ps.setString(2, todo.getCategory());
			ps.setInt(3, todo.getPriority().getCode());
			ps.setLong(4, todo.getUser().getId());
			ps.executeUpdate();
		}

	}

	@Override
	public void deleteTodo(Todo todo) throws SQLException
	{
		try (PreparedStatement preparedStatement = conn.prepareStatement(DELETE_TODO_BY_ID)) {
			preparedStatement.setInt(1, todo.getId());

			preparedStatement.executeUpdate();
		}
	}

	@Override
	public void deleteTodoByIdAndUserId(int todoId, long userId) throws SQLException
	{
		try (PreparedStatement preparedStatement = conn.prepareStatement(DELETE_TODO_BY_ID_AND_USER_ID)) {
			preparedStatement.setInt(1, todoId);
			preparedStatement.setLong(2, userId);

			preparedStatement.executeUpdate();
		}
	}

	@Override
	public void updateTodo(Todo todo) throws SQLException
	{
		try (PreparedStatement preparedStatement = conn.prepareStatement(UPDATE_TODO_QUERY)) {

			preparedStatement.setString(1, todo.getName());
			preparedStatement.setString(2, todo.getCategory());
			preparedStatement.setInt(3, todo.getPriority().getCode());
			preparedStatement.setInt(4, todo.getId());
			preparedStatement.executeUpdate();
		}
	}

}
