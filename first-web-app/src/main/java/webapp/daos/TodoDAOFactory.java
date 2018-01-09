package webapp.daos;

public class TodoDAOFactory
{
	public static TodoDAO getInstance() {
		return new TodoDAOImpl();
	}

}
