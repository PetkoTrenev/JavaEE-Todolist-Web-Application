package webapp.daos;

import java.sql.SQLException;
import java.util.List;

import webapp.models.Todo;

public interface TodoDAO {
	public List<Todo> getTodos() throws SQLException;
	public void addTodo(Todo todo) throws SQLException;
	public void deleteTodo(Todo todo);
	public void updateTodo(Todo todo);
}
