package webapp.daos;

public class UserDAOFactory
{
	public static UserDAO getInstance() {
		return new UserDAOImpl();
	}

}
