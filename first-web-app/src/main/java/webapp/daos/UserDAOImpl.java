package webapp.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.Optional;

import webapp.models.User;
import webapp.util.DbUtil;

public class UserDAOImpl implements UserDAO
{

	private static final String READ_USER_BY_ID = "SELECT * FROM USER WHERE ID=?";

	private static final String UPDATE_USER_INFO_BY_ID = "UPDATE USER SET name = ?, email = ? " 
			+ "WHERE ID = ?";
	
	private static final String UPDATE_USER_PASSWORD = "UPDATE USER SET password = ? WHERE ID = ?";
	
	Connection conn = null;

	public UserDAOImpl() {
		conn = DbUtil.getConnection();
	}

	@Override
	public Optional<User> getUserById(Long id) throws Exception
	{
		try (PreparedStatement statement = conn.prepareStatement(READ_USER_BY_ID)) {
			statement.setLong(1, id);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				return Optional.of(createUser(resultSet));
			} else {
				return Optional.empty();
			}
		} catch (SQLException ex) {
			throw new Exception(ex.getMessage(), ex);
		}
	}

	@SuppressWarnings("unchecked")
	private User createUser(ResultSet rs) throws SQLException
	{
		int COLUMN_INDEX = 5;
		return new User(rs.getLong("id"), rs.getString("name"), rs.getString("email"),
				rs.getString("password"), rs.getObject(COLUMN_INDEX, ArrayList.class));
	}


	@Override
	public boolean login(String email, String password)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public User create(User user)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateUserInfo(User user)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateUserPassword(User user)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User delete(User user)
	{
		// TODO Auto-generated method stub
		return null;
	}

	/*@Override
	public User update(User user)
	{
		
	}

	@Override
	public User delete(User user)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User create(User user)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateUserInfo(User user)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateUserPassword(User user)
	{
		// TODO Auto-generated method stub
		return null;
	} */

}
