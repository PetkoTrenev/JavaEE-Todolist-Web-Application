package webapp.daos;

import java.sql.SQLException;
import java.util.Optional;

import webapp.models.User;

public interface UserDAO {
	Optional<User> getUserById(Long id) throws SQLException, Exception;
	boolean login(String email, String password);
	User create(User user);
	User updateUserInfo(User user);
	User updateUserPassword(User user);
	User delete(User user);
}
