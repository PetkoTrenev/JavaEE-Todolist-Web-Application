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

public class UserDAOImpl implements UserDAO
{

	private static final String READ_USER_BY_ID = "SELECT * FROM USER WHERE id=?";

	private static final String UPDATE_USER_INFO_BY_ID = "UPDATE USER SET name = ?, email = ? " + "WHERE ID = ?";

	private static final String UPDATE_USER_PASSWORD = "UPDATE USER SET password = ? WHERE ID = ?";

	private static final String INSERT_INTO_USER = "INSERT INTO USER (name, email, password)" + "VALUES (?, ?, ?)";

	private static final String DELETE_USER_WITH_ID = "DELETE FROM USER WHERE ID = ?";

	private static final String CHECK_LOGIN_USING_USERNAME_PASSWORD = "SELECT * FROM USER WHERE name = ? and password = ?";

	private static final String GET_USER_TODOS = "SELECT * FROM todo WHERE userId = ?";

	private static final String GET_TOTAL_USERS = "SELECT COUNT(*) FROM USER";
	
	Connection conn = null;

	public UserDAOImpl() {
		conn = DbUtil.getConnection();
	}
	
	public int getTotalUsers() throws SQLException
	{
		try (PreparedStatement ps = conn.prepareStatement(GET_TOTAL_USERS)) {
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				int numberOfRows = rs.getInt(1);
				return numberOfRows;
			}
		}
		return 0;
	}

	@Override
	public User getUserById(Long userId) throws SQLException
	{
		try (PreparedStatement ps = conn.prepareStatement(READ_USER_BY_ID)) {

			ps.setLong(1, userId);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Long id = rs.getLong("id");
					String name = rs.getString("name");
					String email = rs.getString("email");
					String password = rs.getString("password");

					return new User(id, name, email, password);
				}

				return null;
			}
		}
	}

	@Override
	public User login(String username, String password) throws SQLException
	{
		try (PreparedStatement ps = conn.prepareStatement(CHECK_LOGIN_USING_USERNAME_PASSWORD);) {
			ps.setString(1, username);
			ps.setString(2, password);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Long id = rs.getLong("id");
					String name = rs.getString("name");
					String userPass = rs.getString("password");
					String email = rs.getString("email");
					User user = new User(id, name, userPass, email);
					return user;
				}
			}
			return null;
		}
	}

	@Override
	public void create(User user) throws SQLException
	{
		try (PreparedStatement ps = conn.prepareStatement(INSERT_INTO_USER);) {
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.setString(3, user.getPassword());
			ps.executeUpdate();
		}
	}

	@Override
	public void updateUserInfo(User user) throws SQLException
	{
		try (PreparedStatement ps = conn.prepareStatement(UPDATE_USER_INFO_BY_ID);) {
			ps.setLong(3, user.getId());
			ps.setString(1, user.getName());
			ps.setString(2, user.getEmail());
			ps.executeUpdate();
		}
	}

	@Override
	public void updateUserPassword(User user) throws SQLException
	{
		try (PreparedStatement ps = conn.prepareStatement(UPDATE_USER_PASSWORD);) {
			ps.setString(1, user.getPassword());
			ps.setLong(2, user.getId());
			ps.executeUpdate();
		}
	}

	@Override
	public void delete(User user) throws SQLException
	{
		try (PreparedStatement ps = conn.prepareStatement(DELETE_USER_WITH_ID);) {
			ps.setLong(1, user.getId());
			ps.executeUpdate();
		}
	}

	public List<Todo> getUserTodos(User user) throws SQLException
	{
		List<Todo> todos = new ArrayList<>();

		try (PreparedStatement ps = conn.prepareStatement(GET_USER_TODOS)) {
			ps.setLong(1, user.getId());

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					int id = rs.getInt("id");
					String name = rs.getString("name");
					String category = rs.getString("category");
					Priority priority = Priority.ofCode(rs.getInt("priority"));

					todos.add(new Todo(id, user, name, category, priority));
				}
			}
		}

		return todos;
	}

}
