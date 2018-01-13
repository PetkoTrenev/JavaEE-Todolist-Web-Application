package webapp.daos;

import java.sql.SQLException;
import java.util.List;

import webapp.models.Todo;
import webapp.models.User;

public interface UserDAO {
	User login(String username, String password) throws SQLException;
	void create(User user) throws SQLException;
	void updateUserInfo(User user) throws SQLException;
	void updateUserPassword(User user) throws SQLException;
	void delete(User user) throws SQLException;
	User getUserById(Long userId) throws SQLException;
	public List<Todo> getUserTodos(User user) throws SQLException;
	public int getTotalUsers() throws SQLException;
	//public User getUser(String username, String password) throws SQLException;
}
