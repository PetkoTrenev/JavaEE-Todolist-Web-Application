package webapp.daos;

import java.sql.SQLException;
import java.util.List;

import webapp.models.Todo;
import webapp.models.User;

public interface TodoDAO {
	public List<Todo> getTodos() throws SQLException, Exception;
	public void addTodo(Todo todo) throws SQLException;
	public void deleteTodo(Todo todo) throws SQLException;
	public void updateTodo(Todo todo) throws SQLException;
	public List<Todo> getTodosByUser(User user) throws SQLException;
	void deleteTodoByIdAndUserId(int todoId, long userId) throws SQLException;
	Todo getTodoByIdAndUserId(int todoId, long userId) throws SQLException;
}
